package net.jonnegaming.starMetal.Items.potions;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class hastepotion {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static ItemStack create_haste_potion_t1() {
        return createHastePotion(Material.POTION, "Haste Potion", 6000, 0);
    }

    public static ItemStack create_splash_haste_potion_t1() {
        return createHastePotion(Material.SPLASH_POTION, "Splash Haste Potion", 6000, 0);
    }

    public static ItemStack create_haste_potion_t1_ext() {
        return createHastePotion(Material.POTION, "Haste Potion", 12000, 0);
    }

    public static ItemStack create_splash_haste_potion_t1_ext() {
        return createHastePotion(Material.SPLASH_POTION, "Splash Haste Potion", 12000, 0);
    }

    public static ItemStack create_haste_potion_t1_sup() {
        return createHastePotion(Material.POTION, "Haste Potion", 36000, 0);
    }

    public static ItemStack create_splash_haste_potion_t1_sup() {
        return createHastePotion(Material.SPLASH_POTION, "Splash Haste Potion", 36000, 0);
    }

    public static ItemStack create_haste_potion_t2() {
        return createHastePotion(Material.POTION, "Haste IV Potion", 6000, 3);
    }

    public static ItemStack create_splash_haste_potion_t2() {
        return createHastePotion(Material.SPLASH_POTION, "Splash Haste IV Potion", 6000, 3);
    }

    public static ItemStack create_haste_potion_t2_ext() {
        return createHastePotion(Material.POTION, "Haste IV Potion", 12000, 3);
    }

    public static ItemStack create_splash_haste_potion_t2_ext() {
        return createHastePotion(Material.SPLASH_POTION, "Splash Haste IV Potion", 12000, 3);
    }

    public static ItemStack create_haste_potion_t2_sup() {
        return createHastePotion(Material.POTION, "Haste IV Potion", 36000, 3);
    }

    public static ItemStack create_splash_haste_potion_t2_sup() {
        return createHastePotion(Material.SPLASH_POTION, "Splash Haste IV Potion", 36000, 3);
    }

    // Base method for potion creation
    public static ItemStack createHastePotion(Material material, String displayName, int duration, int amplifier) {
        ItemStack potion = new ItemStack(material);
        PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();

        if (potionMeta != null) {
            potionMeta.setBasePotionType(PotionType.AWKWARD);
            potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.HASTE, duration, amplifier), true);
            potionMeta.customName(miniMessage.deserialize(displayName));
            potion.setItemMeta(potionMeta);
        }
        return potion;
    }
}
