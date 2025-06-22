package net.jonnegaming.starMetal.Recipes.potions;

import net.jonnegaming.starMetal.Items.item_creator;
import org.bukkit.Material;
import org.bukkit.event.Listener;

import static net.jonnegaming.starMetal.Items.potions.hastepotion.*;

public class haste_potion_recipe implements Listener {
    public haste_potion_recipe() {
        register_haste_potion_t1_recipe();
        register_splash_haste_potion_t1_recipe();
        register_haste_potion_t1_ext_recipe();
        register_splash_haste_potion_t1_ext_recipe();
        register_haste_potion_t1_super_ext_recipe();
        register_splash_haste_potion_t1_super_ext_recipe();
        register_haste_potion_t2_recipe();
        register_splash_haste_potion_t2_recipe();
        register_haste_potion_t2_ext_recipe();
        register_splash_haste_potion_t2_ext_recipe();
        register_haste_potion_t2_super_ext_recipe();
        register_splash_haste_potion_t2_super_ext_recipe();
    }

    // Haste 1 for 5 mins
    private void register_haste_potion_t1_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.GLOWSTONE_DUST
        };
        item_creator.registerShapelessRecipe(create_haste_potion_t1(), ingredients, "haste_potion");
    }

    // Splash Haste 1 for 5 mins
    private void register_splash_haste_potion_t1_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.GLOWSTONE_DUST,
                Material.GUNPOWDER
        };
        item_creator.registerShapelessRecipe(create_splash_haste_potion_t1(), ingredients, "splash_haste_potion");
    }

    // Haste 1 for 10 mins
    private void register_haste_potion_t1_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.GLOWSTONE_DUST,
                Material.REDSTONE
        };
        item_creator.registerShapelessRecipe(create_haste_potion_t1_ext(), ingredients, "haste_potion_ext");
    }

    // Splash Haste 1 for 10 mins
    private void register_splash_haste_potion_t1_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.GLOWSTONE_DUST,
                Material.REDSTONE,
                Material.GUNPOWDER
        };
        item_creator.registerShapelessRecipe(create_splash_haste_potion_t1_ext(), ingredients, "splash_haste_potion_ext");
    }

    // Haste 1 for 30 mins
    private void register_haste_potion_t1_super_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.GLOWSTONE_DUST,
                Material.REDSTONE_BLOCK
        };
        item_creator.registerShapelessRecipe(create_haste_potion_t1_sup(), ingredients, "haste_potion_sup");
    }

    // Splash Haste 1 for 30 mins
    private void register_splash_haste_potion_t1_super_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.GLOWSTONE_DUST,
                Material.REDSTONE_BLOCK,
                Material.GUNPOWDER
        };
        item_creator.registerShapelessRecipe(create_splash_haste_potion_t1_sup(), ingredients, "splash_haste_potion_sup");
    }

    // Haste 2 for 5 mins
    private void register_haste_potion_t2_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.GLOWSTONE
        };
        item_creator.registerShapelessRecipe(create_haste_potion_t2(), ingredients, "haste_iv_potion");
    }

    // Splash Haste 2 for 5 mins
    private void register_splash_haste_potion_t2_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.GLOWSTONE,
                Material.GUNPOWDER
        };
        item_creator.registerShapelessRecipe(create_splash_haste_potion_t2(), ingredients, "splash_haste_iv_potion");
    }

    // Haste 2 for 10 mins
    private void register_haste_potion_t2_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.GLOWSTONE,
                Material.REDSTONE
        };
        item_creator.registerShapelessRecipe(create_haste_potion_t2_ext(), ingredients, "haste_iv_potion_ext");
    }

    // Splash Haste 2 for 10 mins
    private void register_splash_haste_potion_t2_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.GLOWSTONE,
                Material.REDSTONE,
                Material.GUNPOWDER
        };
        item_creator.registerShapelessRecipe(create_splash_haste_potion_t2_ext(), ingredients, "splash_haste_iv_potion_ext");
    }

    // Haste 2 for 30 mins
    private void register_haste_potion_t2_super_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.GLOWSTONE,
                Material.REDSTONE_BLOCK
        };
        item_creator.registerShapelessRecipe(create_haste_potion_t2_sup(), ingredients, "haste_iv_potion_sup");
    }

    // Splash Haste 2 for 30 mins
    private void register_splash_haste_potion_t2_super_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.GLOWSTONE,
                Material.REDSTONE_BLOCK,
                Material.GUNPOWDER
        };
        item_creator.registerShapelessRecipe(create_splash_haste_potion_t2_sup(), ingredients, "splash_haste_iv_potion_sup");
    }
}
