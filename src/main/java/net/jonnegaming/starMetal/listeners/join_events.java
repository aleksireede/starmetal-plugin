package net.jonnegaming.starMetal.listeners;

import net.jonnegaming.starMetal.StarMetal;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
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
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = StarMetal.getInstance().getConfig();

        // Send resource pack using Adventure API
        sendResourcePack(
                player,
                config.getString("resource-pack.url", ""),
                config.getString("resource-pack.hash", ""),
                config.getBoolean("resource-pack.required", true),
                miniMessage.deserialize(config.getString("resource-pack.prompt", ""))
        );

        // Make player invulnerable for 300 ticks (15 seconds)
        makePlayerInvulnerable(player, config.getInt("resource-pack.invulnerable-ticks", 300));

    }

    private void sendResourcePack(Player player, String url, String packHash, boolean required, Component prompt) {
        if (url == null || url.isBlank()) {
            return;
        }

        player.setResourcePack(url, packHash, required, prompt);
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

        // Check if the resource pack was successfully loaded
        if (status == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED) {
            FileConfiguration config = StarMetal.getInstance().getConfig();
            // Send a message to the player
            // Create the title and subtitle components with Adventure's text formatting
            Component title = miniMessage.deserialize("<aqua>" + config.getString("resource-pack.welcome-title", "Welcome"));
            Component subtitle = miniMessage.deserialize("<rainbow>" + config.getString("resource-pack.welcome-subtitle", "to Jonne Gaming Server!"));

            // Create a title with the specified components and timing
            Title welcomeTitle = Title.title(title, subtitle, Title.Times.times(
                    Duration.ofSeconds(config.getLong("resource-pack.title-fade-in-seconds", 1)),
                    Duration.ofSeconds(config.getLong("resource-pack.title-stay-seconds", 3)),
                    Duration.ofSeconds(config.getLong("resource-pack.title-fade-out-seconds", 1))
            ));

            // Send the title to the player
            player.showTitle(welcomeTitle);
        }
    }
}
