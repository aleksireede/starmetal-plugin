package net.jonnegaming.starMetal.Items;

import net.jonnegaming.starMetal.StarMetal;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;

import static net.jonnegaming.starMetal.Items.item_updater.updateChecker;
import static net.jonnegaming.starMetal.text.item_strings.getColorFromRarity;
import static net.jonnegaming.starMetal.text.item_strings.getDurabilityFromRarity;
import static net.jonnegaming.starMetal.text.strings.cleanString;

public class item_creator {
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private static final String CUSTOM_ID_KEY = "custom_id";
    private static final String RARITY_KEY = "rarity";
    private static final String ITEM_TYPE_KEY = "item_type";
    private static final String HEALTH_KEY = "health";

    /**
     * Create Shapeless recipes
     */
    public static void registerShapelessRecipe(ItemStack result, Material[] ingredients, String nameKey) {
        if (ingredients.length > 9) {
            StarMetal.getInstance().getLogger().warning("[starMetal] " + nameKey + " recipe has too many ingredients! Maximum allowed is 9.");
            return;
        }

        NamespacedKey key = new NamespacedKey(StarMetal.getInstance(), nameKey + "_recipe");
        ShapelessRecipe recipe = new ShapelessRecipe(key, result);

        for (Material ingredient : ingredients) {
            recipe.addIngredient(ingredient);
        }

        registerRecipe(recipe, nameKey);
    }

    /**
     * Register recipes
     */
    public static void registerRecipe(Recipe recipe, String nameKey) {
        if (nameKey.isEmpty()) {
            nameKey = String.valueOf(recipe.getResult().getType());
        }
        try {
            Bukkit.addRecipe(recipe);
            StarMetal.getInstance().getLogger().info("[starMetal] " + nameKey + " recipe registered successfully!");
        } catch (IllegalStateException e) {
            StarMetal.getInstance().getLogger().warning("[starMetal] Failed to register " + nameKey + " recipe!");
        }
    }

    /**
     * base function to create custom items
     */
    public static ItemStack createCustomWeapon(ItemStack baseItem, String itemName, int customId, String rarity, String itemType) {
        ItemStack item = customItemMeta(baseItem, itemName, rarity, customId, itemType);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }
        updateChecker(item);
        return item;
    }

    public static ItemStack createCustomPants(ItemStack baseItem, String name, int customId, String rarity, Color color) {
        ItemStack item = customItemMeta(baseItem, name, rarity, customId, "pants");
        ItemMeta meta = item.getItemMeta();

        if (meta instanceof LeatherArmorMeta leatherMeta) {
            leatherMeta.setColor(color);
            leatherMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DYE);
            if (customId == 4){
                leatherMeta.addEnchant(Enchantment.BINDING_CURSE,1,false);
                leatherMeta.addEnchant(Enchantment.PROTECTION,5,true);
                NamespacedKey health_key = new NamespacedKey(StarMetal.getInstance(),HEALTH_KEY);
                leatherMeta.getPersistentDataContainer().set(health_key,PersistentDataType.INTEGER,8);
            }
            item.setItemMeta(leatherMeta);
        }
        updateChecker(item);
        return item;
    }

    private static ItemStack customItemMeta(ItemStack item, String name, String rarity, int customId, String itemType) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.customName(MINI_MESSAGE.deserialize(getColorFromRarity(rarity) + name));

            NamespacedKey metaKey = new NamespacedKey(StarMetal.getInstance(), cleanString(name).toLowerCase());
            meta.setItemModel(metaKey);

            //Custom id
            NamespacedKey customIdKey = new NamespacedKey(StarMetal.getInstance(), CUSTOM_ID_KEY);
            meta.getPersistentDataContainer().set(customIdKey, PersistentDataType.INTEGER, customId);

            //Rarity
            NamespacedKey rarityKey = new NamespacedKey(StarMetal.getInstance(), RARITY_KEY);
            meta.getPersistentDataContainer().set(rarityKey, PersistentDataType.STRING, rarity.toLowerCase());

            //Item type
            NamespacedKey itemTypeKey = new NamespacedKey(StarMetal.getInstance(), ITEM_TYPE_KEY);
            meta.getPersistentDataContainer().set(itemTypeKey, PersistentDataType.STRING, itemType);

            meta.setEnchantmentGlintOverride(false);

            //Durability
            if (meta instanceof Damageable damageable) {
                int maxDurability = getDurabilityFromRarity(rarity);
                damageable.setMaxDamage(maxDurability);
            }

            item.setItemMeta(meta);
        }
        return item;
    }
}
