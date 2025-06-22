package net.jonnegaming.starMetal.Recipes.weapons;

import net.jonnegaming.starMetal.Items.custom_items;
import net.jonnegaming.starMetal.Items.item_creator;
import net.jonnegaming.starMetal.StarMetal;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class black_clover_recipe implements Listener {
    private final StarMetal plugin;

    public black_clover_recipe(StarMetal plugin) {
        this.plugin = plugin;
        registerCraftingRecipe();
    }

    private void registerCraftingRecipe() {
        ItemStack result = custom_items.black_clover();

        NamespacedKey key = new NamespacedKey(plugin, "black_clover_recipe");
        ShapedRecipe sr = new ShapedRecipe(key, result);
        sr.shape(" X ", " X ", " Y ");

        sr.setIngredient('X', Material.GOLD_BLOCK);
        sr.setIngredient('Y', Material.STICK);

        item_creator.registerRecipe(sr, "Black Clover");
    }
}
