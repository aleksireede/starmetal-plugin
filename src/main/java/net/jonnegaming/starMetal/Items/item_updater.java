package net.jonnegaming.starMetal.Items;

import net.jonnegaming.starMetal.ItemHelper;
import net.jonnegaming.starMetal.StarMetal;
import net.jonnegaming.starMetal.enchantments.enchantment_checker;
import net.jonnegaming.starMetal.text.item_strings;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

import static net.jonnegaming.starMetal.text.custom_item_lore.*;

public class item_updater {
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private static final String CUSTOM_ID_KEY = "custom_id";
    private static final String HEALTH_KEY = "health";

    public static void updateChecker(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        updateItem(item, getLore(item), ItemHelper.getWeaponDamage(item));
    }

    private static List<Component> getLore(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return new ArrayList<>();

        NamespacedKey customIdKey = new NamespacedKey(StarMetal.getInstance(), CUSTOM_ID_KEY);
        Integer customId = meta.getPersistentDataContainer().get(customIdKey, PersistentDataType.INTEGER);

        if (customId == null) {
            return new ArrayList<>();
        }

        return switch (customId) {
            case 0 -> lightsaber_lore();
            case 1 -> anime_staff_lore();
            case 2 -> shadow_dagger_lore();
            case 3 -> rose_dagger_lore();
            case 4 -> opiskelijahaalarit_lore();
            case 5 -> doom_sword_lore();
            case 6 -> giant_sword_lore();
            case 7 -> anime_fan_lore();
            case 8 -> black_clover_lore();
            default -> new ArrayList<>();
        };
    }

    private static void updateItem(ItemStack item, List<Component> lore, int damage) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        //Damage
        if (damage > 1) {
            lore.addFirst(MINI_MESSAGE.deserialize(""));
            lore.addFirst(MINI_MESSAGE.deserialize("<reset><white>Damage: <red>+" + damage));
        }

        //Health
        NamespacedKey health_key = new NamespacedKey(StarMetal.getInstance(),HEALTH_KEY);
        Integer health = meta.getPersistentDataContainer().get(health_key,PersistentDataType.INTEGER);
        if (meta.getPersistentDataContainer().has(health_key)) {
            lore.add(MINI_MESSAGE.deserialize(""));
            lore.add(MINI_MESSAGE.deserialize("<reset><white>Health: <red>+" + health));
        }

        //Enchantments
        enchantment_checker checker = new enchantment_checker();
        List<String> enchantmentsList = checker.getEnchantments(item);
        if (!enchantmentsList.isEmpty()) {
            lore.add(MINI_MESSAGE.deserialize(""));
            enchantmentsList.forEach(enchantment ->
                    lore.add(MINI_MESSAGE.deserialize("<reset><blue>" + enchantment))
            );
        }

        //Item type
        String itemType = item_strings.getItemType(item);
        String rarity = item_strings.getRarity(item);
        lore.add(MINI_MESSAGE.deserialize(""));
        lore.add(MINI_MESSAGE.deserialize(item_strings.getColorFromRarity(rarity) + rarity.toUpperCase() + " " + itemType));
        meta.lore(lore);

        //Hide attributes and vanilla enchants
        if (!meta.hasAttributeModifiers()) {
            meta.addAttributeModifier(
                    Attribute.LUCK,
                    new AttributeModifier(
                            new NamespacedKey(StarMetal.getInstance(), "dummy"),
                            0,
                            AttributeModifier.Operation.ADD_NUMBER,
                            EquipmentSlotGroup.HAND
                    )
            );
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        }

        item.setItemMeta(meta);
    }
}
