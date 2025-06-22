package net.jonnegaming.starMetal.Items.potions;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class speedpotion {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static ItemStack create_speed_potion_t1() {
        return create_speed_potion(Material.POTION, "Speed III Potion", 6000, 2);
    }

    public static ItemStack create_splash_speed_potion_t1() {
        return create_speed_potion(Material.SPLASH_POTION, "Splash Speed III Potion", 6000, 2);
    }

    public static ItemStack create_speed_potion_t1_ext() {
        return create_speed_potion(Material.POTION, "Speed III Potion", 12000, 2);
    }

    public static ItemStack create_splash_speed_potion_t1_ext() {
        return create_speed_potion(Material.SPLASH_POTION, "Splash Speed III Potion", 12000, 2);
    }

    public static ItemStack create_speed_potion_t1_sup() {
        return create_speed_potion(Material.POTION, "Speed III Potion", 36000, 2);
    }

    public static ItemStack create_splash_speed_potion_t1_sup() {
        return create_speed_potion(Material.SPLASH_POTION, "Splash Speed III Potion", 36000, 2);
    }

    public static ItemStack create_speed_potion_t2() {
        return create_speed_potion(Material.POTION, "Speed V Potion", 6000, 4);
    }

    public static ItemStack create_splash_speed_potion_t2() {
        return create_speed_potion(Material.SPLASH_POTION, "Splash Speed V Potion", 6000, 4);
    }

    public static ItemStack create_speed_potion_t2_ext() {
        return create_speed_potion(Material.POTION, "Speed V Potion", 12000, 4);
    }

    public static ItemStack create_splash_speed_potion_t2_ext() {
        return create_speed_potion(Material.SPLASH_POTION, "Splash Speed V Potion", 12000, 4);
    }

    public static ItemStack create_speed_potion_t2_sup() {
        return create_speed_potion(Material.POTION, "Speed V Potion", 36000, 4);
    }

    public static ItemStack create_splash_speed_potion_t2_sup() {
        return create_speed_potion(Material.SPLASH_POTION, "Splash Speed V Potion", 36000, 4);
    }

    // Base method for potion creation
    public static ItemStack create_speed_potion(Material material, String displayName, int duration, int amplifier) {
        ItemStack potion = new ItemStack(material);
        PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();

        if (potionMeta != null) {
            potionMeta.setBasePotionType(PotionType.AWKWARD);
            potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, duration, amplifier), true);
            potionMeta.customName(miniMessage.deserialize(displayName));
            potion.setItemMeta(potionMeta);
        }
        return potion;
    }
}
