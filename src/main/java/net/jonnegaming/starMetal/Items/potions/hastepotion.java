package net.jonnegaming.starMetal.Items.potions;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class hastepotion {
    private static final PotionType base_type = PotionType.AWKWARD;
    private static final Material material_potion = Material.POTION;
    private static final Material material_splash = Material.SPLASH_POTION;
    private static final PotionEffectType haste_effect = PotionEffectType.HASTE;
    private static final int duration_normal = 6000;
    private static final int duration_extended = 12000;
    private static final int duration_super = 36000;

    public static ItemStack create_haste_potion_t1() {
        return potion_helper.create_potion(base_type, material_potion, haste_effect, "Haste Potion", duration_normal, 0);
    }

    public static ItemStack create_splash_haste_potion_t1() {
        return potion_helper.create_potion(base_type, material_splash, haste_effect, "Splash Haste Potion", duration_normal, 0);
    }

    public static ItemStack create_haste_potion_t1_ext() {
        return potion_helper.create_potion(base_type, material_potion, haste_effect, "Haste Potion", duration_extended, 0);
    }

    public static ItemStack create_splash_haste_potion_t1_ext() {
        return potion_helper.create_potion(base_type, material_splash, haste_effect, "Splash Haste Potion", duration_extended, 0);
    }

    public static ItemStack create_haste_potion_t1_sup() {
        return potion_helper.create_potion(base_type, material_potion, haste_effect, "Haste Potion", duration_super, 0);
    }

    public static ItemStack create_splash_haste_potion_t1_sup() {
        return potion_helper.create_potion(base_type, material_splash, haste_effect, "Splash Haste Potion", duration_super, 0);
    }

    public static ItemStack create_haste_potion_t2() {
        return potion_helper.create_potion(base_type, material_potion, haste_effect, "Haste IV Potion", duration_normal, 3);
    }

    public static ItemStack create_splash_haste_potion_t2() {
        return potion_helper.create_potion(base_type, material_splash, haste_effect, "Splash Haste IV Potion", duration_normal, 3);
    }

    public static ItemStack create_haste_potion_t2_ext() {
        return potion_helper.create_potion(base_type, material_potion, haste_effect, "Haste IV Potion", duration_extended, 3);
    }

    public static ItemStack create_splash_haste_potion_t2_ext() {
        return potion_helper.create_potion(base_type, material_splash, haste_effect, "Splash Haste IV Potion", duration_extended, 3);
    }

    public static ItemStack create_haste_potion_t2_sup() {
        return potion_helper.create_potion(base_type, material_potion, haste_effect, "Haste IV Potion", duration_super, 3);
    }

    public static ItemStack create_splash_haste_potion_t2_sup() {
        return potion_helper.create_potion(base_type, material_splash, haste_effect, "Splash Haste IV Potion", duration_super, 3);
    }
}
