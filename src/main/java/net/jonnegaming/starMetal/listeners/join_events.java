package net.jonnegaming.starMetal.listeners;

import net.jonnegaming.starMetal.StarMetal;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.util.UUID;

public class join_events implements Listener {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    String resource_pack_url = "https://www.dropbox.com/scl/fi/vaq8dz7sgx40yvwjq1kma/StarMetalResourcepack.zip?rlkey=yhyye5zyniamixz60ngyjd9qc&st=2iy3f2mj&dl=1";
    String prompt_message = "Tämä resurssipaketti tarvitaan näyttämään modatut esineet./This resource pack is required to show modded items.";
    private final UUID packId = UUID.randomUUID();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Send resource pack using Adventure API
        sendResourcePack(player, resource_pack_url, packId, miniMessage.deserialize(prompt_message));

        // Make player invulnerable for 300 ticks (15 seconds)
        makePlayerInvulnerable(player);

    }

    private void sendResourcePack(Player player, String url, UUID packId, Component prompt) {
        player.setResourcePack(url, String.valueOf(packId), true, prompt);

    }

    private void makePlayerInvulnerable(Player player) {
        player.setInvulnerable(true);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.setInvulnerable(false);
            }
        }.runTaskLater(StarMetal.getInstance(), 300);
    }

    @EventHandler
    public void onResourcePackStatus(PlayerResourcePackStatusEvent event) {
        Player player = event.getPlayer();
        PlayerResourcePackStatusEvent.Status status = event.getStatus();

        // Check if the resource pack was successfully loaded
        if (status == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED) {
            // Send a message to the player
            // Create the title and subtitle components with Adventure's text formatting
            Component title = Component.text("Welcome").color(NamedTextColor.AQUA);
            Component subtitle = Component.text("to Jonne Gaming Server!").color(NamedTextColor.BLUE);

            // Create a title with the specified components and timing
            Title welcomeTitle = Title.title(title, subtitle, Title.Times.times(
                    Duration.ofSeconds(1),  // fadeIn duration
                    Duration.ofSeconds(3),  // stay duration
                    Duration.ofSeconds(1)   // fadeOut duration
            ));

            // Send the title to the player
            player.showTitle(welcomeTitle);
        }
    }
}
