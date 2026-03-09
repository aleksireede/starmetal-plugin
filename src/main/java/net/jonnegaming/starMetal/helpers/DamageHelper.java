package net.jonnegaming.starMetal.helpers;

import org.bukkit.Tag;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static net.jonnegaming.starMetal.ItemHelper.getDefaultDamageValue;
import static net.jonnegaming.starMetal.ItemHelper.getWeaponDamage;

public class DamageHelper {

    public static double WeaponDamage(Player player, Entity target) {
        ItemStack item = player.getInventory().getItemInMainHand();

        // Get weapon damage amount
        double baseDamage = getWeaponDamage(item);

        // Calculate damage modifiers
        double damageMultiplier = 1.0;

        // Strength effect
        if (player.hasPotionEffect(PotionEffectType.STRENGTH)) {
            PotionEffect strengthEffect = player.getPotionEffect(PotionEffectType.STRENGTH);
            if (strengthEffect == null) throw new AssertionError();
            int strengthLevel = strengthEffect.getAmplifier() + 1;
            damageMultiplier += 0.05 * strengthLevel;
        }

        // Weakness effect
        if (player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
            PotionEffect weaknessEffect = player.getPotionEffect(PotionEffectType.WEAKNESS);
            if (weaknessEffect == null) throw new AssertionError();
            int weaknessLevel = weaknessEffect.getAmplifier() + 1;
            damageMultiplier -= 0.05 * weaknessLevel;
        }

        // Handle sharpness enchantments in one pass with switch-case.
        if (item.hasItemMeta() && item.getItemMeta() != null && item.getItemMeta().hasEnchants()) {
            final var enchantments = item.getEnchantments();
            for (Enchantment enchantment : enchantments.keySet()) {
                int level = item.getEnchantmentLevel(enchantment);
                switch (enchantment.getKey().getKey()) {
                    case "sharpness" -> damageMultiplier += 0.05 * level;
                    case "smite" -> {
                        if (isUndead(target)) {
                            damageMultiplier += 0.05 * level;
                        }
                    }
                    case "bane_of_arthropods" -> {
                        if (isArthropod(target)) {
                            damageMultiplier += 0.05 * level;
                        }
                    }
                    default -> {
                    }
                }
            }
        }

        if (baseDamage == 0) {
            baseDamage = getDefaultDamageValue(item);
        }

        // Cap the max damage to +75%
        if (damageMultiplier > 75) {
            damageMultiplier = 75;
        }

        // Calculate final damage
        return baseDamage * damageMultiplier;
    }

    public static double estimateDamage(ItemStack item) {
        // Get weapon damage amount
        double baseDamage = getWeaponDamage(item);

        // Calculate damage modifiers
        double damageMultiplier = 1.0;


        // Handle sharpness enchantments in one pass with switch-case.
        final var enchantments = item.getEnchantments();
        if (!enchantments.isEmpty()) {
            for (Enchantment enchantment : enchantments.keySet()) {
                int level = item.getEnchantmentLevel(enchantment);
                switch (enchantment.getKey().getKey()) {
                    case "sharpness", "smite", "bane_of_arthropods" -> damageMultiplier += 0.05 * level;
                    default -> {
                    }
                }
            }
        }

        if (baseDamage == 0) {
            baseDamage = getDefaultDamageValue(item);
        }

        // Cap the max damage to *75
        if (damageMultiplier > 75) {
            damageMultiplier = 75;
        }

        // Calculate final damage
        return baseDamage * damageMultiplier;
    }

    /**
     * Check if the entity is undead (e.g., zombie, skeleton, stray, husk, zombified piglin, drowned)
     */
    private static boolean isUndead(Entity entity) {
        return Tag.ENTITY_TYPES_SENSITIVE_TO_SMITE.isTagged(entity.getType());
    }

    /**
     * Check if the entity is an arthropod (e.g., spider, cave spider, silverfish, endermite)
     */
    private static boolean isArthropod(Entity entity) {
        return Tag.ENTITY_TYPES_SENSITIVE_TO_BANE_OF_ARTHROPODS.isTagged(entity.getType());
    }
}
