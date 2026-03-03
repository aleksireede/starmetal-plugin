package net.jonnegaming.starMetal.Recipes.weapons;

import net.jonnegaming.starMetal.ItemHelper;
import net.jonnegaming.starMetal.Items.custom_items;
import net.jonnegaming.starMetal.Items.item_creator;
import net.jonnegaming.starMetal.StarMetal;
import net.jonnegaming.starMetal.energy.cooldowns;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.persistence.PersistentDataType;

public class shadow_dagger_recipe implements Listener {
    private static final String COOLDOWN_KEY = "shadow_dagger";
    private static final long COOLDOWN_TIME = 2500; // 2.5 seconds in milliseconds
    private final StarMetal plugin;
    private final NamespacedKey shadowDaggerProjectileKey;

    public shadow_dagger_recipe(StarMetal plugin) {
        this.plugin = plugin;
        this.shadowDaggerProjectileKey = new NamespacedKey(plugin, "shadow_dagger_projectile");
        registerCraftingRecipe();
    }

    private void registerCraftingRecipe() {
        ItemStack result = custom_items.shadow_dagger();

        NamespacedKey key = new NamespacedKey(plugin, "shadow_dagger_recipe");
        ShapedRecipe sr = new ShapedRecipe(key, result);
        sr.shape(" X ", " X ", " Y ");

        sr.setIngredient('X', Material.ENDER_PEARL);
        sr.setIngredient('Y', Material.STICK);

        item_creator.registerRecipe(sr, "Shadow Dagger");
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {

        // Check for right click in air
        if ((event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)) return;

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        //Check for custom item
        if (ItemHelper.isCustomItem(item, 2)) {
            // Don't shoot if on cooldown
            if (cooldowns.isOnCooldown(player, COOLDOWN_KEY, COOLDOWN_TIME)) {
                return;
            }

            //Reduce durability
            ItemHelper.reduceDurability(player, item);

            //Shooting logic
            shoot_pearl(player);

            //Cooldown logic
            cooldowns.run_cooldown(player, COOLDOWN_KEY, COOLDOWN_TIME, "Shadow Dagger");
        }
    }

    private void shoot_pearl(Player player) {
        EnderPearl pearl = player.launchProjectile(EnderPearl.class);
        pearl.getPersistentDataContainer().set(shadowDaggerProjectileKey, PersistentDataType.BYTE, (byte) 1);
        pearl.setShooter(player);
    }
}
