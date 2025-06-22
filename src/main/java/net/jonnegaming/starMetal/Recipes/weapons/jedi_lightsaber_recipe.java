package net.jonnegaming.starMetal.Recipes.weapons;

import net.jonnegaming.starMetal.Items.custom_items;
import net.jonnegaming.starMetal.Items.item_creator;
import net.jonnegaming.starMetal.StarMetal;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class jedi_lightsaber_recipe implements Listener {
    private final StarMetal plugin;

    public jedi_lightsaber_recipe(StarMetal plugin) {
        this.plugin = plugin;
        registerCraftingRecipe();
    }

    private void registerCraftingRecipe() {
        ItemStack result = custom_items.jedi_lightsaber();

        NamespacedKey key = new NamespacedKey(plugin, "lightsaber_recipe");
        ShapedRecipe sr = new ShapedRecipe(key, result);
        sr.shape(" X ", " X ", " Y ");

        sr.setIngredient('X', Material.EMERALD_BLOCK);
        sr.setIngredient('Y', Material.STICK);

        item_creator.registerRecipe(sr, "Lightsaber");
    }
}
