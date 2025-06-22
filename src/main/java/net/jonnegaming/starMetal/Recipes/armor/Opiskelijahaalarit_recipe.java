package net.jonnegaming.starMetal.Recipes.armor;

import net.jonnegaming.starMetal.ItemHelper;
import net.jonnegaming.starMetal.Items.custom_items;
import net.jonnegaming.starMetal.Items.item_creator;
import net.jonnegaming.starMetal.StarMetal;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class Opiskelijahaalarit_recipe implements Listener {
    private final StarMetal plugin;
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public Opiskelijahaalarit_recipe(StarMetal plugin) {
        this.plugin = plugin;
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

                // Store the state to avoid sending the message multiple times
                if (!player.hasMetadata("opiskelijahaalaritEquipped")) {
                    player.setMetadata("opiskelijahaalaritEquipped", new FixedMetadataValue(plugin, true));

                    // Send the message only once
                    player.sendActionBar(miniMessage.deserialize("<reset><green>"+ "You have equipped Opiskelijahaalarit with Curse of Binding!"));
                }
            }
        }
    }
}
