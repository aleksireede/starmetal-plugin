package net.jonnegaming.starMetal;

import io.github.aleksireede.hammershared.SharedText;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class StatusDisplay {
    private static final String OBJECTIVE_NAME = "starmetal_status";
    private static final LegacyComponentSerializer legacySerializer = LegacyComponentSerializer.legacySection();
    private static final Map<UUID, BossBar> bossBars = new HashMap<>();
    private static final Map<UUID, Scoreboard> previousScoreboards = new HashMap<>();
    private static final Map<UUID, Scoreboard> statusScoreboards = new HashMap<>();
    private static final Map<UUID, Integer> clearTokens = new HashMap<>();

    private StatusDisplay() {
    }

    public enum Mode {
        ACTION_BAR,
        TITLE,
        SUBTITLE,
        BOSS_BAR,
        SCOREBOARD,
        CHAT;

        static Mode fromConfig(String value) {
            if (value == null) {
                return ACTION_BAR;
            }

            return switch (value.trim().toLowerCase()) {
                case "title" -> TITLE;
                case "subtitle" -> SUBTITLE;
                case "boss_bar", "bossbar" -> BOSS_BAR;
                case "scoreboard", "sidebar" -> SCOREBOARD;
                case "chat", "message" -> CHAT;
                default -> ACTION_BAR;
            };
        }
    }

    public static void show(Player player, Component message) {
        show(player, message, 1.0f);
    }

    public static void show(Player player, Component message, float progress) {
        Mode mode = Mode.fromConfig(StarMetal.getInstance().getConfig().getString("ui.status-display-mode"));
        clearTokens.merge(player.getUniqueId(), 1, Integer::sum);

        if (mode != Mode.BOSS_BAR) {
            clearBossBar(player);
        }
        if (mode != Mode.SCOREBOARD) {
            clearScoreboard(player);
        }

        switch (mode) {
            case TITLE -> player.sendTitlePart(TitlePart.TITLE, message);
            case SUBTITLE -> player.sendTitlePart(TitlePart.SUBTITLE, message);
            case BOSS_BAR -> showBossBar(player, message, progress);
            case SCOREBOARD -> showScoreboard(player, message);
            case CHAT -> player.sendMessage(message);
            case ACTION_BAR -> player.sendActionBar(message);
        }
    }

    public static void showTemporary(Player player, Component message, long durationTicks) {
        showTemporary(player, message, 1.0f, durationTicks);
    }

    public static void showTemporary(Player player, Component message, float progress, long durationTicks) {
        UUID playerId = player.getUniqueId();
        int token = clearTokens.merge(playerId, 1, Integer::sum);
        showWithoutTokenIncrement(player, message, progress);

        Bukkit.getScheduler().runTaskLater(StarMetal.getInstance(), () -> {
            if (clearTokens.getOrDefault(playerId, 0) == token && player.isOnline()) {
                clear(player);
            }
        }, durationTicks);
    }

    public static void clear(Player player) {
        clearTokens.remove(player.getUniqueId());
        player.sendActionBar(Component.empty());
        player.clearTitle();
        clearBossBar(player);
        clearScoreboard(player);
    }

    public static void clearAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            clear(player);
        }
        bossBars.clear();
        previousScoreboards.clear();
        statusScoreboards.clear();
        clearTokens.clear();
    }

    private static void showWithoutTokenIncrement(Player player, Component message, float progress) {
        Mode mode = Mode.fromConfig(StarMetal.getInstance().getConfig().getString("ui.status-display-mode"));

        if (mode != Mode.BOSS_BAR) {
            clearBossBar(player);
        }
        if (mode != Mode.SCOREBOARD) {
            clearScoreboard(player);
        }

        switch (mode) {
            case TITLE -> player.sendTitlePart(TitlePart.TITLE, message);
            case SUBTITLE -> player.sendTitlePart(TitlePart.SUBTITLE, message);
            case BOSS_BAR -> showBossBar(player, message, progress);
            case SCOREBOARD -> showScoreboard(player, message);
            case CHAT -> player.sendMessage(message);
            case ACTION_BAR -> player.sendActionBar(message);
        }
    }

    private static void showBossBar(Player player, Component message, float progress) {
        BossBar bossBar = bossBars.computeIfAbsent(player.getUniqueId(), ignored -> {
            BossBar bar = BossBar.bossBar(message, clamp(progress), BossBar.Color.WHITE, BossBar.Overlay.PROGRESS);
            player.showBossBar(bar);
            return bar;
        });

        bossBar.name(message);
        bossBar.progress(clamp(progress));
        player.showBossBar(bossBar);
    }

    private static void showScoreboard(Player player, Component message) {
        UUID playerId = player.getUniqueId();
        if (!previousScoreboards.containsKey(playerId)) {
            previousScoreboards.put(playerId, player.getScoreboard());
        }

        Scoreboard scoreboard = statusScoreboards.computeIfAbsent(playerId, ignored -> Bukkit.getScoreboardManager().getNewScoreboard());
        Objective objective = scoreboard.getObjective(OBJECTIVE_NAME);
        if (objective != null) {
            objective.unregister();
        }

        objective = scoreboard.registerNewObjective(OBJECTIVE_NAME, Criteria.DUMMY, getScoreboardTitle());
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.getScore(legacySerializer.serialize(message)).setScore(1);
        player.setScoreboard(scoreboard);
    }

    private static Component getScoreboardTitle() {
        FileConfiguration config = StarMetal.getInstance().getConfig();
        String title = config.getString("ui.scoreboard-title", "<gold>StarMetal");
        return SharedText.miniMessage(title);
    }

    private static void clearBossBar(Player player) {
        BossBar bossBar = bossBars.remove(player.getUniqueId());
        if (bossBar != null) {
            player.hideBossBar(bossBar);
        }
    }

    private static void clearScoreboard(Player player) {
        UUID playerId = player.getUniqueId();
        Scoreboard statusBoard = statusScoreboards.remove(playerId);
        if (statusBoard != null) {
            Objective objective = statusBoard.getObjective(OBJECTIVE_NAME);
            if (objective != null) {
                objective.unregister();
            }
        }
        Scoreboard previous = previousScoreboards.remove(playerId);
        if (previous != null) {
            player.setScoreboard(previous);
        }
    }

    private static float clamp(float progress) {
        return Math.clamp(progress, 0.0f, 1.0f);
    }
}
