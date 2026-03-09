package net.jonnegaming.starMetal.energy;

import io.github.aleksireede.hammershared.SharedText;
import net.jonnegaming.starMetal.StatusDisplay;
import net.jonnegaming.starMetal.StarMetal;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

public class cooldowns {
    public static final Map<UUID, Map<String, Long>> cooldowns_list = new HashMap<>();
    private static final HashSet<UUID> reloading = new HashSet<>(); // Track players in reload state
    private static final Map<UUID, Integer> energy = new HashMap<>();
    private static final int MAX_energy = 5;

    // Cooldown logic timer and display
    public static void run_cooldown(Player player, String cooldownKey, long COOLDOWN_TIME, String weapon) {
        UUID playerId = player.getUniqueId();
        cooldowns_list.computeIfAbsent(playerId, ignored -> new HashMap<>()).put(cooldownKey, System.currentTimeMillis());

        new BukkitRunnable() {
            double remainingTime = COOLDOWN_TIME / 1000.0;

            @Override
            public void run() {
                if (remainingTime > 0) {
                    StatusDisplay.show(
                            player,
                            SharedText.miniMessage("<!i><red>" + weapon + " Cooldown: " + String.format("%.1f", remainingTime) + "s"),
                            (float) (remainingTime / (COOLDOWN_TIME / 1000.0))
                    );
                    remainingTime -= 0.1;
                } else {
                    StatusDisplay.showTemporary(player, SharedText.miniMessage("<!i><green>" + weapon + " Ability Ready!"), 40L);
                    cancel();
                }
            }
        }.runTaskTimer(StarMetal.getInstance(), 0, 2);
    }

    public static boolean isOnCooldown(Player player, String cooldownKey, long cooldownTime) {
        Map<String, Long> playerCooldowns = cooldowns_list.get(player.getUniqueId());
        if (playerCooldowns == null) {
            return false;
        }

        Long lastUse = playerCooldowns.get(cooldownKey);
        return lastUse != null && System.currentTimeMillis() - lastUse < cooldownTime;
    }

    public static Integer getEnergy(Player player){
        UUID playerId = player.getUniqueId();
        energy.putIfAbsent(playerId,MAX_energy);
        return energy.get(playerId);
    }

    public static boolean isReloading(Player player){
        UUID playerId = player.getUniqueId();
        return reloading.contains(playerId);
    }

    public static void consumeEnergy(Player player){
        UUID playerId = player.getUniqueId();
        energy.putIfAbsent(playerId, MAX_energy);
        energy.put(playerId, energy.get(playerId) - 1);
    }
    
    public static void run_reload(Player player, long RELOAD_TIME){
        UUID playerId = player.getUniqueId();
        if (reloading.contains(playerId)) return;
        reloading.add(playerId);

        new BukkitRunnable() {
            final long startTime = System.currentTimeMillis();

            @Override
            public void run() {
                long elapsed = System.currentTimeMillis() - startTime;
                long timeLeft = RELOAD_TIME - elapsed;
                if (timeLeft <= 0) {
                    energy.put(playerId, MAX_energy);
                    reloading.remove(playerId);
                    StatusDisplay.showTemporary(player, SharedText.miniMessage("<!i><green>Energy Reload complete!"), 40L);
                    cancel();
                } else {
                    StatusDisplay.show(
                            player,
                            SharedText.miniMessage("<!i><red>Reloading Energy..." + String.format("%.1f", timeLeft / 1000.0) + "s"),
                            (float) timeLeft / RELOAD_TIME
                    );
                }
            }
        }.runTaskTimer(StarMetal.getInstance(), 0L, 2L); // 2L = 0.1 seconds in ticks
    }
}
