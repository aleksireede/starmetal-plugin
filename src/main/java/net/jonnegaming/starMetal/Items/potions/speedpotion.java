package net.jonnegaming.starMetal.Items.potions;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class speedpotion {
    private static final PotionType base_type = PotionType.AWKWARD;
    private static final Material material_potion = Material.POTION;
    private static final Material material_splash = Material.SPLASH_POTION;
    private static final PotionEffectType speed_effect = PotionEffectType.SPEED;
    private static final int duration_normal = 6000;
    private static final int duration_extended = 12000;
    private static final int duration_super = 36000;

    public static ItemStack create_speed_potion_t1() {
        return potion_helper.create_potion(base_type, material_potion, speed_effect, "Speed III Potion", duration_normal, 2);
    }

    public static ItemStack create_splash_speed_potion_t1() {
        return potion_helper.create_potion(base_type, material_splash, speed_effect, "Splash Speed III Potion", duration_normal, 2);
    }

    public static ItemStack create_speed_potion_t1_ext() {
        return potion_helper.create_potion(base_type, material_potion, speed_effect, "Speed III Potion", duration_extended, 2);
    }

    public static ItemStack create_splash_speed_potion_t1_ext() {
        return potion_helper.create_potion(base_type, material_splash, speed_effect, "Splash Speed III Potion", duration_extended, 2);
    }

    public static ItemStack create_speed_potion_t1_sup() {
        return potion_helper.create_potion(base_type, material_potion, speed_effect, "Speed III Potion", duration_super, 2);
    }

    public static ItemStack create_splash_speed_potion_t1_sup() {
        return potion_helper.create_potion(base_type, material_splash, speed_effect, "Splash Speed III Potion", duration_super, 2);
    }

    public static ItemStack create_speed_potion_t2() {
        return potion_helper.create_potion(base_type, material_potion, speed_effect, "Speed V Potion", duration_normal, 4);
    }

    public static ItemStack create_splash_speed_potion_t2() {
        return potion_helper.create_potion(base_type, material_splash, speed_effect, "Splash Speed V Potion", duration_normal, 4);
    }

    public static ItemStack create_speed_potion_t2_ext() {
        return potion_helper.create_potion(base_type, material_potion, speed_effect, "Speed V Potion", duration_extended, 4);
    }

    public static ItemStack create_splash_speed_potion_t2_ext() {
        return potion_helper.create_potion(base_type, material_splash, speed_effect, "Splash Speed V Potion", duration_extended, 4);
    }

    public static ItemStack create_speed_potion_t2_sup() {
        return potion_helper.create_potion(base_type, material_potion, speed_effect, "Speed V Potion", duration_super, 4);
    }

    public static ItemStack create_splash_speed_potion_t2_sup() {
        return potion_helper.create_potion(base_type, material_splash, speed_effect, "Splash Speed V Potion", duration_super, 4);
    }
}
