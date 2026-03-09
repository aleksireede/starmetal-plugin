package net.jonnegaming.starMetal.listeners;

import io.github.aleksireede.hammershared.SharedResourcePackManager;
import io.github.aleksireede.hammershared.SharedText;
import net.jonnegaming.starMetal.StarMetal;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;

public class join_events implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = StarMetal.getInstance().getConfig();

        SharedResourcePackManager packManager = SharedResourcePackManager.fromConfig(StarMetal.getInstance(), config);
        if (packManager.isEnabled() && packManager.shouldSendOnJoin()) {
            packManager.sendToPlayer(player);
        }

        makePlayerInvulnerable(player, config.getInt("resource-pack.invulnerable-ticks", 300));
    }

    private void makePlayerInvulnerable(Player player, long ticks) {
        player.setInvulnerable(true);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.setInvulnerable(false);
            }
        }.runTaskLater(StarMetal.getInstance(), ticks);
    }

    @EventHandler
    public void onResourcePackStatus(PlayerResourcePackStatusEvent event) {
        Player player = event.getPlayer();
        PlayerResourcePackStatusEvent.Status status = event.getStatus();

        if (status == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED) {
            FileConfiguration config = StarMetal.getInstance().getConfig();
            Component title = SharedText.miniMessage("<aqua>" + config.getString("resource-pack.welcome-title", "Welcome"));
            Component subtitle = SharedText.miniMessage("<rainbow>" + config.getString("resource-pack.welcome-subtitle", "to Jonne Gaming Server!"));

            Title welcomeTitle = Title.title(title, subtitle, Title.Times.times(
                    Duration.ofSeconds(config.getLong("resource-pack.title-fade-in-seconds", 1)),
                    Duration.ofSeconds(config.getLong("resource-pack.title-stay-seconds", 3)),
                    Duration.ofSeconds(config.getLong("resource-pack.title-fade-out-seconds", 1))
            ));

            player.showTitle(welcomeTitle);
        }
    }
}
