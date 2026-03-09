package net.jonnegaming.starMetal.text;

import io.github.aleksireede.hammershared.SharedItemKeys;
import io.github.aleksireede.hammershared.SharedItemUpdater;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class item_strings {

    /** @see SharedItemUpdater#getDurabilityFromRarity(String) */
    public static int getDurabilityFromRarity(final String rarity) {
        return SharedItemUpdater.getDurabilityFromRarity(rarity);
    }

    /** @see SharedItemUpdater#getColorFromRarity(String) */
    public static String getColorFromRarity(final String rarity) {
        return SharedItemUpdater.getColorFromRarity(rarity);
    }

    /** Returns the rarity string stored on the item, defaulting to {@code "Common"}. */
    public static String getRarity(final ItemStack item) {
        final ItemMeta meta = item.getItemMeta();
        if (meta == null) return "Common";
        final String rarity = meta.getPersistentDataContainer()
                .get(SharedItemKeys.rarityKey(), PersistentDataType.STRING);
        return (rarity == null || rarity.isEmpty()) ? "Common" : rarity;
    }

    /** Returns the item-type string stored on the item, or an empty string. */
    public static String getItemType(final ItemStack item) {
        final ItemMeta meta = item.getItemMeta();
        if (meta == null) return "";
        if (!meta.getPersistentDataContainer().has(SharedItemKeys.itemTypeKey(), PersistentDataType.STRING)) {
            return "";
        }
        return Objects.requireNonNull(
                meta.getPersistentDataContainer().get(SharedItemKeys.itemTypeKey(), PersistentDataType.STRING)
        ).toUpperCase();
    }
}
