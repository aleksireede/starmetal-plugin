package net.jonnegaming.starMetal;

import io.github.aleksireede.hammershared.SharedItemKeys;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;

public class config {
    public static FileConfiguration config;

    public static NamespacedKey customIdKey() {
        return SharedItemKeys.customIdKey();
    }

    public static NamespacedKey rarityKey() {
        return SharedItemKeys.rarityKey();
    }

    public static NamespacedKey itemTypeKey() {
        return SharedItemKeys.itemTypeKey();
    }

    public static NamespacedKey healthKey() {
        return SharedItemKeys.healthKey();
    }
}
