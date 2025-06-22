package net.jonnegaming.starMetal.Recipes.weapons;

import net.jonnegaming.starMetal.ItemHelper;
import net.jonnegaming.starMetal.Items.custom_items;
import net.jonnegaming.starMetal.Items.item_creator;
import net.jonnegaming.starMetal.StarMetal;
import net.jonnegaming.starMetal.projectiles.projectile_launcher;
import net.jonnegaming.starMetal.energy.cooldowns;
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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class animestaff_recipe implements Listener {
    private static final long RELOAD_TIME = 5000;
    private static final long SHOT_DELAY = 25;
    private final StarMetal plugin;
    private final Map<UUID, Long> lastShotTime = new HashMap<>();

    public animestaff_recipe(StarMetal plugin) {
        this.plugin = plugin;
        registerCraftingRecipe();
    }

    private void registerCraftingRecipe() {
        ItemStack anime_staff = custom_items.anime_staff();

        NamespacedKey key = new NamespacedKey(plugin, "anime_staff_recipe");
        ShapedRecipe sr = new ShapedRecipe(key, anime_staff);
        sr.shape(" X ", " Y ", " Z ");

        sr.setIngredient('X', Material.LAPIS_BLOCK);
        sr.setIngredient('Y', Material.REDSTONE_BLOCK);
        sr.setIngredient('Z', Material.STICK);

        item_creator.registerRecipe(sr, "Anime_Staff");
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        UUID playerId = player.getUniqueId();

        // Check if correct event and hand slot and clicking air
        if (event.getHand() != EquipmentSlot.HAND) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        // Only allow custom item
        if (ItemHelper.isCustomItem(item, 1)) {
            long currentTime = System.currentTimeMillis();
            if (cooldowns.isReloading(player)) return;

            if (lastShotTime.containsKey(playerId) && currentTime - lastShotTime.get(playerId) < SHOT_DELAY) return;
            lastShotTime.put(playerId, currentTime);

            ItemHelper.reduceDurability(player, item);

            // override vanilla event
            event.setCancelled(true);

            // shooting logic
            projectile_launcher.launchCustomProjectile(player, new ItemStack(Material.SLIME_BALL), "anime_staff", Sound.ENTITY_BREEZE_WIND_BURST, true);

            cooldowns.consumeEnergy(player);
            if (cooldowns.getEnergy(player) <=0){
                cooldowns.run_reload(player,RELOAD_TIME);
            }
        }
    }

}
