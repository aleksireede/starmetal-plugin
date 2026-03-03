package net.jonnegaming.starMetal;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;

public class config {
    public static FileConfiguration config;

    private static String getMetadataKeyName(String path, String fallback) {
        return StarMetal.getInstance().getConfig().getString("metadata-keys." + path, fallback);
    }

    public static NamespacedKey customIdKey() {
        return new NamespacedKey(StarMetal.getInstance(), getMetadataKeyName("custom-id", "custom_id"));
    }

    public static NamespacedKey rarityKey() {
        return new NamespacedKey(StarMetal.getInstance(), getMetadataKeyName("rarity", "rarity"));
    }

    public static NamespacedKey itemTypeKey() {
        return new NamespacedKey(StarMetal.getInstance(), getMetadataKeyName("item-type", "item_type"));
    }

    public static NamespacedKey healthKey() {
        return new NamespacedKey(StarMetal.getInstance(), getMetadataKeyName("health", "health"));
    }
}
