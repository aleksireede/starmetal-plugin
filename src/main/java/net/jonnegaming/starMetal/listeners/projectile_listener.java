package net.jonnegaming.starMetal.listeners;

import net.jonnegaming.starMetal.StarMetal;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import static net.jonnegaming.starMetal.config.config;

public class projectile_listener implements Listener {
    private static final NamespacedKey SHADOW_DAGGER_PROJECTILE_KEY = new NamespacedKey(StarMetal.getInstance(), "shadow_dagger_projectile");
    private static final NamespacedKey DOOM_SWORD_PROJECTILE_KEY = new NamespacedKey(StarMetal.getInstance(), "doom_sword");
    private static final NamespacedKey CRYSTAL_REAPER_PROJECTILE_KEY = new NamespacedKey(StarMetal.getInstance(), "crystal_reaper");
    private static final NamespacedKey ANIME_FAN_PROJECTILE_KEY = new NamespacedKey(StarMetal.getInstance(), "anime_fan");
    private static final NamespacedKey ANIME_STAFF_PROJECTILE_KEY = new NamespacedKey(StarMetal.getInstance(), "anime_staff");

    // Main Damage logic
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        switch (event.getDamager()) {
            case WitherSkull witherSkull when witherSkull.getPersistentDataContainer().has(CRYSTAL_REAPER_PROJECTILE_KEY, PersistentDataType.BYTE) ->
                    event.setDamage(config.getInt("weapons.crystal_reaper.attack-damage"));
            case Fireball fireball -> {
                if (fireball.getPersistentDataContainer().has(DOOM_SWORD_PROJECTILE_KEY, PersistentDataType.BYTE)) {
                    // Doom sword fireball strikes lighting
                    Entity damagedEntity = event.getEntity();
                    damagedEntity.getWorld().strikeLightning(damagedEntity.getLocation());
                    event.setDamage(config.getInt("weapons.doom_sword.attack-damage"));
                }
                // Ender pearl logic
            }
            case EnderPearl pearl when pearl.getShooter() instanceof Player
                    && pearl.getPersistentDataContainer().has(SHADOW_DAGGER_PROJECTILE_KEY, PersistentDataType.BYTE) ->
                    event.setCancelled(true); // Cancels only the damage from the pearl, allowing teleportation
            case Snowball snowball when snowball.getShooter() instanceof Player
                    && snowball.getPersistentDataContainer().has(ANIME_FAN_PROJECTILE_KEY, PersistentDataType.BYTE) -> {
                Player player = ((Player) snowball.getShooter()).getPlayer();
                event.setDamage(config.getInt("weapons.anime_fan.attack-damage"));
                Entity enemy = event.getEntity();
                if (player == null) throw new AssertionError();
                ItemStack itemInHand = player.getInventory().getItemInMainHand();
                // Check if the item is not air (empty hand)
                if (itemInHand.getType() != Material.AIR) {
                    // Spawn the particle at the player's location
                    player.spawnParticle(Particle.ASH, enemy.getLocation(), 1);

                    // Play the sound
                    player.playSound(enemy.getLocation(), Sound.ENTITY_SKELETON_DEATH, 1F, 1F);
                } else {
                    // If the hand is empty, you might want to handle it differently
                    player.playSound(enemy.getLocation(), Sound.ENTITY_SKELETON_DEATH, 1F, 1F);
                    player.spawnParticle(Particle.BLOCK, enemy.getLocation(), 1);
                }
            }
            default -> {
            }
        }
    }

    // Anime Staff jump logic
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Snowball snowball) {
            if (snowball.getShooter() instanceof Player shooter
                    && snowball.getPersistentDataContainer().has(ANIME_STAFF_PROJECTILE_KEY, PersistentDataType.BYTE)) {
                // Get the snowball's current velocity
                Vector snowballVelocity = snowball.getVelocity();

                // Create a new velocity vector for the player's jump
                Vector jumpVelocity = new Vector(-snowballVelocity.getX(), 2, -snowballVelocity.getZ());

                // Apply the jump velocity to the player
                shooter.setVelocity(jumpVelocity);
            }
        }

        if (event.getEntity() instanceof WitherSkull witherSkull
                && witherSkull.getPersistentDataContainer().has(CRYSTAL_REAPER_PROJECTILE_KEY, PersistentDataType.BYTE)) {
            witherSkull.getWorld().createExplosion(witherSkull.getLocation(), 7.0F, false, true, witherSkull);
        }
    }

    // Event handler to cancel block damage from our TNT explosions
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntity() instanceof WitherSkull witherSkull
                && witherSkull.getPersistentDataContainer().has(CRYSTAL_REAPER_PROJECTILE_KEY, PersistentDataType.BYTE)) {
            event.setYield(7.0F);
        }
        if (event.getEntity().hasMetadata("no_block_damage")) {
            event.blockList().clear(); // Prevent block damage
        }
    }
}
