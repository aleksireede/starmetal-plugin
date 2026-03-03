package net.jonnegaming.starMetal.Recipes.armor;

import net.jonnegaming.starMetal.ItemHelper;
import net.jonnegaming.starMetal.Items.custom_items;
import net.jonnegaming.starMetal.Items.item_creator;
import net.jonnegaming.starMetal.StatusDisplay;
import net.jonnegaming.starMetal.StarMetal;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Opiskelijahaalarit_recipe implements Listener {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private final Set<UUID> notifiedPlayers = new HashSet<>();

    public Opiskelijahaalarit_recipe(StarMetal plugin) {
        registerCraftingRecipe();
    }

    private void registerCraftingRecipe() {
        ItemStack result = custom_items.opiskelijahaalarit();
        Material[] ingredients = new Material[]{
                Material.LEATHER_LEGGINGS,
                Material.BLACK_WOOL,
                Material.GREEN_WOOL,
                Material.PURPLE_WOOL
        };
        item_creator.registerShapelessRecipe(result, ingredients, "opiskelijahaalarit");
    }

    // Send a message to the player that they have equipped the opiskelijahaalarit with curse of binding
    @EventHandler
    public void onEquip(InventoryClickEvent event) {
        if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.LEATHER_LEGGINGS) {
            ItemStack item = event.getCurrentItem();
            LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();

            // Check for correct item
            if (meta != null && ItemHelper.isCustomItem(item, 4)) {
                // Check if Curse of Binding is applied

                // Check if the player hasn't already been notified
                event.getWhoClicked();
                Player player = (Player) event.getWhoClicked();
                UUID playerId = player.getUniqueId();

                // Store the state to avoid sending the message multiple times
                if (notifiedPlayers.add(playerId)) {
                    // Send the message only once
                    StatusDisplay.showTemporary(player, miniMessage.deserialize("<!i><green>You have equipped Opiskelijahaalarit with Curse of Binding!"), 40L);
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        notifiedPlayers.remove(event.getPlayer().getUniqueId());
    }
}
