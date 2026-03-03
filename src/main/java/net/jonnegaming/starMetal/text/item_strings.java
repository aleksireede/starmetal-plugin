package net.jonnegaming.starMetal.text;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

import static net.jonnegaming.starMetal.config.itemTypeKey;
import static net.jonnegaming.starMetal.config.rarityKey;

public class item_strings {


    /**
     * Get amount of durability from rarity
     */
    public static Integer getDurabilityFromRarity(String rarity) {
        return switch (rarity.toLowerCase()) {
            case "common", "uncommon" -> 500;
            case "rare" -> 1000;
            case "epic" -> 1500;
            case "legendary" -> 2000;
            case "mythic", "exotic" -> 3000;
            case "abyssal", "divine", "celestial" -> 4000;
            case "eternal", "ancient" -> 5000;
            default -> 0;
        };
    }


    /**
     * method to get color from rarity string
     */
    public static String getColorFromRarity(String rarity) {
        if (rarity == null) return "<white>";

        return switch (rarity.toLowerCase()) {
            case "uncommon" -> "<green>";
            case "rare" -> "<blue>";
            case "epic" -> "<dark_purple>";
            case "legendary" -> "<gold>";
            case "mythic" -> "<red>";
            case "exotic" -> "<aqua>";
            case "abyssal" -> "<dark_aqua>";
            case "divine" -> "<light_purple>";
            case "celestial" -> "<dark_blue>";
            case "eternal" -> "<dark_green>";
            case "ancient" -> "<dark_red>";
            default -> "<white>";
        };
    }


    // Get rarity from an item
    public static String getRarity(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return "";
        // If rarity not defined default to common rarity
        String rarity = meta.getPersistentDataContainer().get(rarityKey(), PersistentDataType.STRING);
        if (rarity == null || rarity.isEmpty()) {
            rarity = "Common";
        }
        return rarity;
    }

    //Get item type from persistent data container
    public static String getItemType(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if (meta == null)return "";
        String ItemType = "";
        //Get the item type from the container
        if (meta.getPersistentDataContainer().has(itemTypeKey(), PersistentDataType.STRING)) {
            ItemType = Objects.requireNonNull(meta.getPersistentDataContainer().get(itemTypeKey(), PersistentDataType.STRING)).toUpperCase();
        }
        return ItemType;
    }
}
