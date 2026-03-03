package net.jonnegaming.starMetal.listeners;

import net.jonnegaming.starMetal.StatusDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class status_display_listener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        StatusDisplay.clear(event.getPlayer());
    }
}
