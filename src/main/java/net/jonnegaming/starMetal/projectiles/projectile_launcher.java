package net.jonnegaming.starMetal.projectiles;

import net.jonnegaming.starMetal.StarMetal;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class projectile_launcher {

    public static void launchCustomProjectile(Player player, ItemStack custom_item, String metadata_key, Sound custom_sound) {
        launchCustomProjectile(player, custom_item, metadata_key, custom_sound, false);
    }

    public static void launchCustomProjectile(Player player, ItemStack custom_item, String metadata_key, Sound custom_sound, boolean is_jump_pad) {
        Snowball snowball = player.launchProjectile(Snowball.class);
        snowball.setShooter(player);
        snowball.setMetadata(metadata_key, new FixedMetadataValue(StarMetal.getInstance(), true));
        snowball.setItem(custom_item);
        if (is_jump_pad) {
            Vector direction = player.getEyeLocation().getDirection();
            Vector velocity = direction.multiply(2);
            velocity.setY(-1);
            snowball.setVelocity(velocity);
        } else {
            snowball.setVelocity(player.getLocation().getDirection().multiply(1.5)); // Adjust velocity as needed
            snowball.setGravity(false);
        }
        snowball.setSilent(true);
        snowball.setRotation(0f, 90f);
        player.playSound(player.getLocation(), custom_sound, 1f, 1f);
    }

    public static void launchFireball(Player player, String metadata_key) {
        Fireball Fireball = player.launchProjectile(Fireball.class, player.getEyeLocation().getDirection().multiply(2));
        Fireball.setSilent(true);
        Fireball.setGlowing(true);
        Fireball.setShooter(player);
        Fireball.setMetadata(metadata_key, new FixedMetadataValue(StarMetal.getInstance(), true));
    }
}
