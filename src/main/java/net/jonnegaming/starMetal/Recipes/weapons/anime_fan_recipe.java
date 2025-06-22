package net.jonnegaming.starMetal.Recipes.weapons;

import net.jonnegaming.starMetal.ItemHelper;
import net.jonnegaming.starMetal.Items.custom_items;
import net.jonnegaming.starMetal.Items.item_creator;
import net.jonnegaming.starMetal.StarMetal;
import net.jonnegaming.starMetal.projectiles.projectile_launcher;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.UUID;

import static net.jonnegaming.starMetal.energy.cooldowns.*;

public class anime_fan_recipe implements Listener {
    private static final long COOLDOWN_TIME = 500; // 2.5 seconds in milliseconds
    private final StarMetal plugin;

    public anime_fan_recipe(StarMetal plugin) {
        this.plugin = plugin;
        registerCraftingRecipe();
    }

    private void registerCraftingRecipe() {
        ItemStack result = custom_items.anime_fan();

        NamespacedKey key = new NamespacedKey(plugin, "anime_fan_recipe");
        ShapedRecipe sr = new ShapedRecipe(key, result);
        sr.shape("XXX", "XYX", "XXX");

        sr.setIngredient('X', Material.IRON_INGOT);
        sr.setIngredient('Y', Material.STICK);

        item_creator.registerRecipe(sr, "Anime Fan");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        //Abort when clicking a block
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) return;
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            Player player = event.getPlayer();
            UUID playerId = player.getUniqueId();
            ItemStack item = player.getInventory().getItemInMainHand();

            // Check for correct weapon
            if (ItemHelper.isCustomItem(item, 7)) {
                // Don't shoot if on cooldown
                if (cooldowns_list.containsKey(playerId) && (System.currentTimeMillis() - cooldowns_list.get(playerId) < COOLDOWN_TIME))
                    return;

                // Override vanilla event
                event.setCancelled(true);
                ItemHelper.reduceDurability(player, item, 2);
                projectile_launcher.launchCustomProjectile(player, custom_items.anime_fan(), "anime_fan", Sound.ENTITY_PLAYER_ATTACK_NODAMAGE);
                run_cooldown(player, COOLDOWN_TIME, "Anime Fan");
            }
        }
    }
}
