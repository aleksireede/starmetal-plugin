package net.jonnegaming.starMetal.Recipes.weapons;

import net.jonnegaming.starMetal.ItemHelper;
import net.jonnegaming.starMetal.Items.custom_items;
import net.jonnegaming.starMetal.Items.item_creator;
import net.jonnegaming.starMetal.StarMetal;
import net.jonnegaming.starMetal.energy.cooldowns;
import net.jonnegaming.starMetal.projectiles.projectile_launcher;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class crystal_reaper_recipe implements Listener {
    private static final String COOLDOWN_KEY = "crystal_reaper";
    private static final long COOLDOWN_TIME = 500;
    private final StarMetal plugin;

    public crystal_reaper_recipe(StarMetal plugin) {
        this.plugin = plugin;
        registerCraftingRecipe();
    }

    private void registerCraftingRecipe() {
        ItemStack result = custom_items.crystal_reaper();

        NamespacedKey key = new NamespacedKey(plugin, "crystal_reaper_recipe");
        ShapedRecipe sr = new ShapedRecipe(key, result);
        sr.shape(" X ", " Y ", " Y ");
        sr.setIngredient('X', Material.END_CRYSTAL);
        sr.setIngredient('Y', Material.STICK);

        item_creator.registerRecipe(sr, "Crystal Reaper");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (event.getHand() != EquipmentSlot.HAND) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;

        if (ItemHelper.isCustomItem(item, 9)) {
            if (cooldowns.isOnCooldown(player, COOLDOWN_KEY, COOLDOWN_TIME)) {
                return;
            }

            event.setCancelled(true);
            projectile_launcher.launchWitherSkull(player, "crystal_reaper", 1.5, true, Sound.ENTITY_WITHER_SHOOT);
            ItemHelper.reduceDurability(player, item, 4);
            cooldowns.run_cooldown(player, COOLDOWN_KEY, COOLDOWN_TIME, "Crystal Reaper");
        }
    }
}
