package net.jonnegaming.starMetal.listeners;

import net.jonnegaming.starMetal.ItemHelper;
import net.jonnegaming.starMetal.StarMetal;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static net.jonnegaming.starMetal.ItemHelper.getWeaponDamage;

public class weapon_damage implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        // Check if the damager is a player and the event is not cancelled
        if (event.getDamager() instanceof Player player && !event.isCancelled()) {
            ItemStack item = player.getInventory().getItemInMainHand();

            // Ensure the damage cause is entity attack or similar to avoid thorns, etc.
            if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK || event.getCause() == EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK) {

                Location location = event.getEntity().getLocation();
                itemSoundAndPArticle(player,location);
                event.setDamage(getWeaponDamage(item));
            }
        }
    }

    private void itemSoundAndPArticle(Player player, Location location){
        ItemStack item = player.getInventory().getItemInMainHand();

        // Check if the item is the custom item in question
        if (ItemHelper.isCustomItem(item, 8)) {
            player.playSound(location, Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1, 1);
            player.spawnParticle(Particle.EXPLOSION, location, 2);
        }
        else if (ItemHelper.isCustomItem(item,1)){
            player.playSound(location, Sound.ENTITY_BREEZE_SHOOT, 1, 1);
            player.spawnParticle(Particle.ELECTRIC_SPARK, location, 5);
        }
        else if (ItemHelper.isCustomItem(item,3)){
            player.playSound(location, Sound.ENCHANT_THORNS_HIT, 1, 1);
            player.spawnParticle(Particle.GUST, location, 2);
        }
        else if (ItemHelper.isCustomItem(item,0)){
            player.playSound(location, Sound.BLOCK_CONDUIT_DEACTIVATE,1 ,1);
            player.spawnParticle(Particle.EFFECT, location,3);
        }
        else if (ItemHelper.isCustomItem(item, 6)){
            player.playSound(location,Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR,1,1);
            player.spawnParticle(Particle.EXPLOSION, location, 1);
        }
    }

    // check for held item changes
    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    custom_item_effects(player);
                }
            }
        }.runTaskTimer(StarMetal.getInstance(), 0L, 40L); // Run every 2 second (1 second = 20 ticks)
    }

    // Apply effects when holding custom items in main hand
    private void custom_item_effects(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        NamespacedKey custom_id = new NamespacedKey(StarMetal.getInstance(), "custom_id");
        if (meta == null) return;

        // choose the weapon
        if (meta.getPersistentDataContainer().has(custom_id)) {
            switch (meta.getPersistentDataContainer().get(custom_id, PersistentDataType.INTEGER)) {
                case 0, 1, 3, 6, 2, 5 -> {}
                case 7 -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE,100, 1, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,100, 1, false, false));
                }
                case 8 -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 1, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 100, 1, false, false));
                }
                case null, default -> {
                }
            }
        }
    }
}
