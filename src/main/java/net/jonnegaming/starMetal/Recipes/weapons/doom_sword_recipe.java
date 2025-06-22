package net.jonnegaming.starMetal.Recipes.weapons;

import net.jonnegaming.starMetal.ItemHelper;
import net.jonnegaming.starMetal.Items.custom_items;
import net.jonnegaming.starMetal.Items.item_creator;
import net.jonnegaming.starMetal.StarMetal;
import net.jonnegaming.starMetal.energy.cooldowns;
import net.jonnegaming.starMetal.projectiles.projectile_launcher;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.UUID;

public class doom_sword_recipe implements Listener {
    private static final long COOLDOWN_TIME = 2000;
    private final StarMetal plugin;

    public doom_sword_recipe(StarMetal plugin) {
        this.plugin = plugin;
        registerCraftingRecipe();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private void registerCraftingRecipe() {
        ItemStack result = custom_items.doom_sword();

        NamespacedKey key = new NamespacedKey(plugin, "doom_sword_recipe");
        ShapedRecipe sr = new ShapedRecipe(key, result);
        sr.shape(" X ", " X ", " Y ");

        sr.setIngredient('X', Material.NETHERITE_BLOCK);
        sr.setIngredient('Y', Material.STICK);

        item_creator.registerRecipe(sr, "Doom Sword");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        UUID playerId = player.getUniqueId();

        if (event.getHand() != EquipmentSlot.HAND) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() == Action.RIGHT_CLICK_BLOCK) return;

        // Only allow custom item
        if (ItemHelper.isCustomItem(item, 5)) {
            // Don't shoot if on cooldown
            if (cooldowns.cooldowns_list.containsKey(playerId) && (System.currentTimeMillis() - cooldowns.cooldowns_list.get(playerId) < COOLDOWN_TIME)) {
                return;
            }

            // override vanilla event
            event.setCancelled(true);

            //Shooting logic
            projectile_launcher.launchFireball(player, "doom_sword");

            // reduce durability
            ItemHelper.reduceDurability(player, item, 3);

            // Put the item into cooldown
            cooldowns.run_cooldown(player, COOLDOWN_TIME, "Doom Sword");
        }
    }
}
