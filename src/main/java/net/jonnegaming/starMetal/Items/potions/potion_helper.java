package net.jonnegaming.starMetal.Items.potions;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class potion_helper {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    /**
     * Creates a potion item with a base potion type, one custom effect, and a custom display name.
     *
     * @param type the base {@link PotionType} shown on the potion item, for example {@code PotionType.AWKWARD}
     * @param material the potion item material, typically {@link Material#POTION}, {@link Material#SPLASH_POTION},
     *                 or {@link Material#LINGERING_POTION}
     * @param effect_type the custom {@link PotionEffectType} applied when the potion is used
     * @param displayName the custom item name in MiniMessage format
     * @param duration the custom effect duration in ticks; 20 ticks = 1 second
     * @param amplifier the custom effect amplifier using Bukkit's zero-based scale:
     *                  {@code 0 = level I}, {@code 1 = level II}, {@code 2 = level III}, and so on
     * @return a potion {@link ItemStack} with the requested base type, custom effect, and custom name
     */
    public static ItemStack create_potion(PotionType type ,Material material, PotionEffectType effect_type, String displayName, int duration, int amplifier) {
        ItemStack potion = new ItemStack(material);
        PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();

        if (potionMeta != null) {
            potionMeta.setBasePotionType(type);
            potionMeta.addCustomEffect(new PotionEffect(effect_type, duration, amplifier), true);
            potionMeta.customName(miniMessage.deserialize(displayName));
            potion.setItemMeta(potionMeta);
        }
        return potion;
    }
}
