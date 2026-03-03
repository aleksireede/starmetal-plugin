package net.jonnegaming.starMetal.projectiles;

import net.jonnegaming.starMetal.StarMetal;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.WitherSkull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

public class projectile_launcher {

    public static void launchCustomProjectile(Player player, ItemStack custom_item, String metadata_key, Sound custom_sound) {
        launchCustomProjectile(player, custom_item, metadata_key, custom_sound, false);
    }

    public static void launchCustomProjectile(Player player, ItemStack custom_item, String metadata_key, Sound custom_sound, boolean is_jump_pad) {
        Snowball snowball = player.launchProjectile(Snowball.class);
        snowball.setShooter(player);
        markProjectile(snowball, metadata_key);
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
        markProjectile(Fireball, metadata_key);
    }

    public static void launchWitherSkull(Player player, String metadata_key, double speed, boolean charged, Sound custom_sound) {
        WitherSkull witherSkull = player.launchProjectile(WitherSkull.class, player.getEyeLocation().getDirection().multiply(speed));
        witherSkull.setSilent(true);
        witherSkull.setGlowing(true);
        witherSkull.setCharged(charged);
        witherSkull.setShooter(player);
        markProjectile(witherSkull, metadata_key);
        player.playSound(player.getLocation(), custom_sound, 1f, 1f);
    }

    private static void markProjectile(org.bukkit.entity.Projectile projectile, String key) {
        projectile.getPersistentDataContainer().set(new NamespacedKey(StarMetal.getInstance(), key), PersistentDataType.BYTE, (byte) 1);
    }
}
