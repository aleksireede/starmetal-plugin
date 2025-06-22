package net.jonnegaming.starMetal.listeners;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import static net.jonnegaming.starMetal.config.config;

public class projectile_listener implements Listener {
    // Main Damage logic
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        switch (event.getDamager()) {
            case Fireball fireball -> {
                if (fireball.hasMetadata("doom_sword")) {
                    // Doom sword fireball strikes lighting
                    Entity damagedEntity = event.getEntity();
                    damagedEntity.getWorld().strikeLightning(damagedEntity.getLocation());
                    event.setDamage(config.getInt("weapons.doom_sword.attack-damage"));
                }
                // Ender pearl logic
            }
            case EnderPearl pearl when pearl.getShooter() instanceof Player && pearl.hasMetadata("shadow_dagger") ->
                    event.setCancelled(true); // Cancels only the damage from the pearl, allowing teleportation
            case Snowball snowball when snowball.getShooter() instanceof Player && snowball.hasMetadata("anime_fan") -> {
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
            if (snowball.getShooter() instanceof Player shooter && snowball.hasMetadata("anime_staff")) {
                // Get the snowball's current velocity
                Vector snowballVelocity = snowball.getVelocity();

                // Create a new velocity vector for the player's jump
                Vector jumpVelocity = new Vector(-snowballVelocity.getX(), 2, -snowballVelocity.getZ());

                // Apply the jump velocity to the player
                shooter.setVelocity(jumpVelocity);
            }
        }
    }

    // Event handler to cancel block damage from our TNT explosions
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntity().hasMetadata("no_block_damage")) {
            event.blockList().clear(); // Prevent block damage
        }
    }
}
