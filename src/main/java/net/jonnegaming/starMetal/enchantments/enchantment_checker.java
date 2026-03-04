package net.jonnegaming.starMetal.enchantments;

import net.jonnegaming.starMetal.text.strings;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class enchantment_checker {
    
    private String enchant_lvl(ItemStack item, Enchantment enchantment)
    {
        int raw_lvl = item.getEnchantmentLevel(enchantment);
        return strings.getRomanNumeral(raw_lvl);
    }
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
            String val = enchant_lvl(itemStack,Enchantment.PROTECTION);
            enchantmentList.add("Protection " + val);
        }

        // Check for Fire Protection
        if (itemStack.containsEnchantment(Enchantment.FIRE_PROTECTION)) {
            String val = enchant_lvl(itemStack,Enchantment.FIRE_PROTECTION);
            enchantmentList.add("Fire Protection " + val);
        }

        // Check for Feather Falling
        if (itemStack.containsEnchantment(Enchantment.FEATHER_FALLING)) {
            String val = enchant_lvl(itemStack,Enchantment.FEATHER_FALLING);
            enchantmentList.add("Feather Falling " + val);
        }

        // Check for Blast Protection
        if (itemStack.containsEnchantment(Enchantment.BLAST_PROTECTION)) {
            String val = enchant_lvl(itemStack,Enchantment.BLAST_PROTECTION);
            enchantmentList.add("Blast Protection " + val);
        }

        // Check for Projectile Protection
        if (itemStack.containsEnchantment(Enchantment.PROJECTILE_PROTECTION)) {
            String val = enchant_lvl(itemStack,Enchantment.PROJECTILE_PROTECTION);
            enchantmentList.add("Projectile Protection " + val);
        }

        // Check for Sharpness
        if (itemStack.containsEnchantment(Enchantment.SHARPNESS)) {
            String val = enchant_lvl(itemStack,Enchantment.SHARPNESS);
            enchantmentList.add("Sharpness " + val);
        }

        // Check for Smite
        if (itemStack.containsEnchantment(Enchantment.SMITE)) {
            String val = enchant_lvl(itemStack,Enchantment.SMITE);
            enchantmentList.add("Smite " + val);
        }

        // Check for Bane of Arthropods
        if (itemStack.containsEnchantment(Enchantment.BANE_OF_ARTHROPODS)) {
            String val = enchant_lvl(itemStack,Enchantment.BANE_OF_ARTHROPODS);
            enchantmentList.add("Bane of Arthropods " + val);
        }

        // Check for Knockback
        if (itemStack.containsEnchantment(Enchantment.KNOCKBACK)) {
            String val = enchant_lvl(itemStack,Enchantment.KNOCKBACK);
            enchantmentList.add("Knockback " + val);
        }

        // Check for Fire Aspect
        if (itemStack.containsEnchantment(Enchantment.FIRE_ASPECT)) {
            String val = enchant_lvl(itemStack,Enchantment.FIRE_ASPECT);
            enchantmentList.add("Fire Aspect " + val);
        }

        // Check for Looting
        if (itemStack.containsEnchantment(Enchantment.LOOTING)) {
            String val = enchant_lvl(itemStack,Enchantment.LOOTING);
            enchantmentList.add("Looting " + val);
        }

        // Check for Sweeping Edge
        if (itemStack.containsEnchantment(Enchantment.SWEEPING_EDGE)) {
            String val = enchant_lvl(itemStack,Enchantment.SWEEPING_EDGE);
            enchantmentList.add("Sweeping Edge " + val);
        }

        // Check for Efficiency
        if (itemStack.containsEnchantment(Enchantment.EFFICIENCY)) {
            String val = enchant_lvl(itemStack,Enchantment.EFFICIENCY);
            enchantmentList.add("Efficiency " + val);
        }

        // Check for Silk Touch
        if (itemStack.containsEnchantment(Enchantment.SILK_TOUCH)) {
            enchantmentList.add("Silk Touch");
        }

        // Check for Unbreaking
        if (itemStack.containsEnchantment(Enchantment.UNBREAKING)) {
            String val = enchant_lvl(itemStack,Enchantment.UNBREAKING);
            enchantmentList.add("Unbreaking " + val);
        }

        // Check for Fortune
        if (itemStack.containsEnchantment(Enchantment.FORTUNE)) {
            String val = enchant_lvl(itemStack,Enchantment.FORTUNE);
            enchantmentList.add("Fortune " + val);
        }

        // Check for Power
        if (itemStack.containsEnchantment(Enchantment.POWER)) {
            String val = enchant_lvl(itemStack,Enchantment.POWER);
            enchantmentList.add("Power " + val);
        }

        // Check for Punch
        if (itemStack.containsEnchantment(Enchantment.PUNCH)) {
            String val = enchant_lvl(itemStack,Enchantment.PUNCH);
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
            String val = enchant_lvl(itemStack,Enchantment.LUCK_OF_THE_SEA);
            enchantmentList.add("Luck of the Sea " + val);
        }

        // Check for Lure
        if (itemStack.containsEnchantment(Enchantment.LURE)) {
            String val = enchant_lvl(itemStack,Enchantment.LURE);
            enchantmentList.add("Lure " + val);
        }

        // Check for Frost Walker
        if (itemStack.containsEnchantment(Enchantment.FROST_WALKER)) {
            String val = enchant_lvl(itemStack,Enchantment.FROST_WALKER);
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
            String val = enchant_lvl(itemStack,Enchantment.IMPALING);
            enchantmentList.add("Impaling " + val);
        }

        // Check for Loyalty
        if (itemStack.containsEnchantment(Enchantment.LOYALTY)) {
            String val = enchant_lvl(itemStack,Enchantment.LOYALTY);
            enchantmentList.add("Loyalty " + val);
        }

        // Check for Channeling
        if (itemStack.containsEnchantment(Enchantment.CHANNELING)) {
            enchantmentList.add("Channeling");
        }

        // Check for Riptide
        if (itemStack.containsEnchantment(Enchantment.RIPTIDE)) {
            String val = enchant_lvl(itemStack,Enchantment.RIPTIDE);
            enchantmentList.add("Riptide " + val);
        }

        // Check for Multishot
        if (itemStack.containsEnchantment(Enchantment.MULTISHOT)) {
            enchantmentList.add("Multishot");
        }

        // Check for Quick Charge
        if (itemStack.containsEnchantment(Enchantment.QUICK_CHARGE)) {
            String val = enchant_lvl(itemStack,Enchantment.QUICK_CHARGE);
            enchantmentList.add("Quick Charge " + val);
        }

        // Check for Piercing
        if (itemStack.containsEnchantment(Enchantment.PIERCING)) {
            String val = enchant_lvl(itemStack,Enchantment.PIERCING);
            enchantmentList.add("Piercing " + val);
        }

        // Check for BREACH
        if (itemStack.containsEnchantment(Enchantment.BREACH)) {
            String val = enchant_lvl(itemStack,Enchantment.BREACH);
            enchantmentList.add("BREACH " + val);
        }

        // Check for WIND_BURST
        if (itemStack.containsEnchantment(Enchantment.WIND_BURST)) {
            String val = enchant_lvl(itemStack,Enchantment.WIND_BURST);
            enchantmentList.add("WIND BURST " + val);
        }

        // Check for LUNGE
        if (itemStack.containsEnchantment(Enchantment.LUNGE)) {
            String val = enchant_lvl(itemStack,Enchantment.LUNGE);
            enchantmentList.add("LUNGE " + val);
        }

        // Check for SWIFT_SNEAK
        if (itemStack.containsEnchantment(Enchantment.SWIFT_SNEAK)) {
            String val = enchant_lvl(itemStack,Enchantment.SWIFT_SNEAK);
            enchantmentList.add("SWIFT SNEAK " + val);
        }

        // Check for SOUL_SPEED
        if (itemStack.containsEnchantment(Enchantment.SOUL_SPEED)) {
            String val = enchant_lvl(itemStack,Enchantment.SOUL_SPEED);
            enchantmentList.add("SOUL SPEED " + val);
        }

        // Check for DENSITY
        if (itemStack.containsEnchantment(Enchantment.DENSITY)) {
            String val = enchant_lvl(itemStack,Enchantment.DENSITY);
            enchantmentList.add("DENSITY " + val);
        }

        // Check for Aqua Affinity
        if (itemStack.containsEnchantment(Enchantment.AQUA_AFFINITY)) {
            String val = enchant_lvl(itemStack,Enchantment.AQUA_AFFINITY);
            enchantmentList.add("AQUA AFFINITY " + val);
        }

        // Check for Depth Strider
        if (itemStack.containsEnchantment(Enchantment.DEPTH_STRIDER)) {
            String val = enchant_lvl(itemStack,Enchantment.DEPTH_STRIDER);
            enchantmentList.add("DEPTH STRIDER " + val);
        }

        // Check for Respiration
        if (itemStack.containsEnchantment(Enchantment.RESPIRATION)) {
            String val = enchant_lvl(itemStack,Enchantment.RESPIRATION);
            enchantmentList.add("RESPIRATION " + val);
        }

        // Check for Thorns
        if (itemStack.containsEnchantment(Enchantment.THORNS)) {
            String val = enchant_lvl(itemStack,Enchantment.THORNS);
            enchantmentList.add("THORNS " + val);
        }

        return enchantmentList;
    }
}
