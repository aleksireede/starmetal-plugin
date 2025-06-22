package net.jonnegaming.starMetal.enchantments;

import net.jonnegaming.starMetal.text.strings;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class enchantment_checker {
    /**
     * Very long because every enchantment needs an if statement lol
     * Returns a list of all the enchantments + the level of the enchantment in a given itemstack
     */
    public List<String> getEnchantments(ItemStack itemStack) {
        List<String> enchantmentList = new ArrayList<>();

        if (itemStack == null || itemStack.getType() == Material.AIR) {
            enchantmentList.add("No item provided!");
            return enchantmentList;
        }

        // Check for Protection
        if (itemStack.containsEnchantment(Enchantment.PROTECTION)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.PROTECTION);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Protection " + val);
        }

        // Check for Fire Protection
        if (itemStack.containsEnchantment(Enchantment.FIRE_PROTECTION)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.FIRE_PROTECTION);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Fire Protection " + val);
        }

        // Check for Feather Falling
        if (itemStack.containsEnchantment(Enchantment.FEATHER_FALLING)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.FEATHER_FALLING);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Feather Falling " + val);
        }

        // Check for Blast Protection
        if (itemStack.containsEnchantment(Enchantment.BLAST_PROTECTION)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.BLAST_PROTECTION);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Blast Protection " + val);
        }

        // Check for Projectile Protection
        if (itemStack.containsEnchantment(Enchantment.PROJECTILE_PROTECTION)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.PROJECTILE_PROTECTION);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Projectile Protection " + val);
        }

        // Check for Sharpness
        if (itemStack.containsEnchantment(Enchantment.SHARPNESS)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.SHARPNESS);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Sharpness " + val);
        }

        // Check for Smite
        if (itemStack.containsEnchantment(Enchantment.SMITE)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.SMITE);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Smite " + val);
        }

        // Check for Bane of Arthropods
        if (itemStack.containsEnchantment(Enchantment.BANE_OF_ARTHROPODS)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Bane of Arthropods " + val);
        }

        // Check for Knockback
        if (itemStack.containsEnchantment(Enchantment.KNOCKBACK)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.KNOCKBACK);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Knockback " + val);
        }

        // Check for Fire Aspect
        if (itemStack.containsEnchantment(Enchantment.FIRE_ASPECT)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.FIRE_ASPECT);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Fire Aspect " + val);
        }

        // Check for Looting
        if (itemStack.containsEnchantment(Enchantment.LOOTING)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.LOOTING);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Looting " + val);
        }

        // Check for Sweeping Edge
        if (itemStack.containsEnchantment(Enchantment.SWEEPING_EDGE)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.SWEEPING_EDGE);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Sweeping Edge " + val);
        }

        // Check for Efficiency
        if (itemStack.containsEnchantment(Enchantment.EFFICIENCY)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.EFFICIENCY);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Efficiency " + val);
        }

        // Check for Silk Touch
        if (itemStack.containsEnchantment(Enchantment.SILK_TOUCH)) {
            enchantmentList.add("Silk Touch");
        }

        // Check for Unbreaking
        if (itemStack.containsEnchantment(Enchantment.UNBREAKING)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.UNBREAKING);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Unbreaking " + val);
        }

        // Check for Fortune
        if (itemStack.containsEnchantment(Enchantment.FORTUNE)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.FORTUNE);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Fortune " + val);
        }

        // Check for Power
        if (itemStack.containsEnchantment(Enchantment.POWER)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.POWER);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Power " + val);
        }

        // Check for Punch
        if (itemStack.containsEnchantment(Enchantment.PUNCH)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.PUNCH);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Punch " + val);
        }

        // Check for Flame
        if (itemStack.containsEnchantment(Enchantment.FLAME)) {
            enchantmentList.add("Flame");
        }

        // Check for Infinity
        if (itemStack.containsEnchantment(Enchantment.INFINITY)) {
            enchantmentList.add("Infinity");
        }

        // Check for Luck of the Sea
        if (itemStack.containsEnchantment(Enchantment.LUCK_OF_THE_SEA)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.LUCK_OF_THE_SEA);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Luck of the Sea " + val);
        }

        // Check for Lure
        if (itemStack.containsEnchantment(Enchantment.LURE)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.LURE);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Lure " + val);
        }

        // Check for Frost Walker
        if (itemStack.containsEnchantment(Enchantment.FROST_WALKER)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.FROST_WALKER);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Frost Walker " + val);
        }

        // Check for Mending
        if (itemStack.containsEnchantment(Enchantment.MENDING)) {
            enchantmentList.add("Mending");
        }

        // Check for Binding Curse
        if (itemStack.containsEnchantment(Enchantment.BINDING_CURSE)) {
            enchantmentList.add("Curse of Binding");
        }

        // Check for Vanishing Curse
        if (itemStack.containsEnchantment(Enchantment.VANISHING_CURSE)) {
            enchantmentList.add("Curse of Vanishing");
        }

        // Check for Impaling
        if (itemStack.containsEnchantment(Enchantment.IMPALING)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.IMPALING);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Impaling " + val);
        }

        // Check for Loyalty
        if (itemStack.containsEnchantment(Enchantment.LOYALTY)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.LOYALTY);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Loyalty " + val);
        }

        // Check for Channeling
        if (itemStack.containsEnchantment(Enchantment.CHANNELING)) {
            enchantmentList.add("Channeling");
        }

        // Check for Riptide
        if (itemStack.containsEnchantment(Enchantment.RIPTIDE)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.RIPTIDE);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Riptide " + val);
        }

        // Check for Multishot
        if (itemStack.containsEnchantment(Enchantment.MULTISHOT)) {
            enchantmentList.add("Multishot");
        }

        // Check for Quick Charge
        if (itemStack.containsEnchantment(Enchantment.QUICK_CHARGE)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.QUICK_CHARGE);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Quick Charge " + val);
        }

        // Check for Piercing
        if (itemStack.containsEnchantment(Enchantment.PIERCING)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.PIERCING);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("Piercing " + val);
        }

        // Check for BREACH
        if (itemStack.containsEnchantment(Enchantment.BREACH)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.BREACH);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("BREACH " + val);
        }

        // Check for WIND_BURST
        if (itemStack.containsEnchantment(Enchantment.WIND_BURST)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.WIND_BURST);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("WIND BURST " + val);
        }

        // Check for SWIFT_SNEAK
        if (itemStack.containsEnchantment(Enchantment.SWIFT_SNEAK)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.SWIFT_SNEAK);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("SWIFT SNEAK " + val);
        }

        // Check for SOUL_SPEED
        if (itemStack.containsEnchantment(Enchantment.SOUL_SPEED)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.SOUL_SPEED);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("SOUL SPEED " + val);
        }

        // Check for DENSITY
        if (itemStack.containsEnchantment(Enchantment.DENSITY)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.DENSITY);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("DENSITY " + val);
        }

        // Check for Aqua Affinity
        if (itemStack.containsEnchantment(Enchantment.AQUA_AFFINITY)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.AQUA_AFFINITY);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("AQUA AFFINITY " + val);
        }

        // Check for Depth Strider
        if (itemStack.containsEnchantment(Enchantment.DEPTH_STRIDER)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.DEPTH_STRIDER);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("DEPTH STRIDER " + val);
        }

        // Check for Respiration
        if (itemStack.containsEnchantment(Enchantment.RESPIRATION)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.RESPIRATION);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("RESPIRATION " + val);
        }

        // Check for Thorns
        if (itemStack.containsEnchantment(Enchantment.THORNS)) {
            int value = itemStack.getEnchantmentLevel(Enchantment.THORNS);
            String val = strings.getRomanNumeral(value);
            enchantmentList.add("THORNS " + val);
        }

        return enchantmentList;
    }
}
