package net.jonnegaming.starMetal.Recipes.potions;

import net.jonnegaming.starMetal.Items.item_creator;
import org.bukkit.Material;
import org.bukkit.event.Listener;

import static net.jonnegaming.starMetal.Items.potions.speedpotion.*;

public class speed_potion_recipe implements Listener {
    public speed_potion_recipe() {
        register_Speed_potion_t1_recipe();
        register_splash_Speed_potion_t1_recipe();
        register_Speed_potion_t1_ext_recipe();
        register_splash_Speed_potion_t1_ext_recipe();
        register_Speed_potion_t1_super_ext_recipe();
        register_splash_Speed_potion_t1_super_ext_recipe();
        register_Speed_potion_t2_recipe();
        register_splash_Speed_potion_t2_recipe();
        register_Speed_potion_t2_ext_recipe();
        register_splash_Speed_potion_t2_ext_recipe();
        register_Speed_potion_t2_super_ext_recipe();
        register_splash_Speed_potion_t2_super_ext_recipe();
    }

    // Speed 1 for 5 mins
    private void register_Speed_potion_t1_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.COOKIE
        };
        item_creator.registerShapelessRecipe(create_speed_potion_t1(), ingredients, "speed_potion");
    }

    // Splash Speed 1 for 5 mins
    private void register_splash_Speed_potion_t1_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.COOKIE,
                Material.GUNPOWDER
        };
        item_creator.registerShapelessRecipe(create_splash_speed_potion_t1(), ingredients, "splash_speed_potion");
    }

    // Speed 1 for 10 mins
    private void register_Speed_potion_t1_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.COOKIE,
                Material.REDSTONE
        };
        item_creator.registerShapelessRecipe(create_speed_potion_t1_ext(), ingredients, "speed_potion_ext");
    }

    // Splash Speed 1 for 10 mins
    private void register_splash_Speed_potion_t1_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.COOKIE,
                Material.REDSTONE,
                Material.GUNPOWDER
        };
        item_creator.registerShapelessRecipe(create_splash_speed_potion_t1_ext(), ingredients, "splash_speed_potion_ext");
    }

    // Speed 1 for 30 mins
    private void register_Speed_potion_t1_super_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.COOKIE,
                Material.REDSTONE_BLOCK
        };
        item_creator.registerShapelessRecipe(create_speed_potion_t1_sup(), ingredients, "speed_potion_super");
    }

    // Splash Speed 1 for 30 mins
    private void register_splash_Speed_potion_t1_super_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.COOKIE,
                Material.REDSTONE_BLOCK,
                Material.GUNPOWDER
        };
        item_creator.registerShapelessRecipe(create_splash_speed_potion_t1_sup(), ingredients, "splash_speed_potion_super");
    }

    // Speed 2 for 5 mins
    private void register_Speed_potion_t2_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.PUMPKIN_PIE
        };
        item_creator.registerShapelessRecipe(create_speed_potion_t2(), ingredients, "speed_iv_potion");
    }

    // Splash Speed 2 for 5 mins
    private void register_splash_Speed_potion_t2_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.PUMPKIN_PIE,
                Material.GUNPOWDER
        };
        item_creator.registerShapelessRecipe(create_splash_speed_potion_t2(), ingredients, "splash_speed_iv_potion");
    }

    // Speed 2 for 10 mins
    private void register_Speed_potion_t2_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.PUMPKIN_PIE,
                Material.REDSTONE
        };
        item_creator.registerShapelessRecipe(create_speed_potion_t2_ext(), ingredients, "speed_iv_potion_ext");
    }

    // Splash Speed 2 for 10 mins
    private void register_splash_Speed_potion_t2_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.PUMPKIN_PIE,
                Material.GUNPOWDER,
                Material.REDSTONE
        };
        item_creator.registerShapelessRecipe(create_splash_speed_potion_t2_ext(), ingredients, "splash_speed_iv_potion_ext");
    }

    // Speed 2 for 30 mins
    private void register_Speed_potion_t2_super_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.PUMPKIN_PIE,
                Material.REDSTONE_BLOCK
        };
        item_creator.registerShapelessRecipe(create_speed_potion_t2_sup(), ingredients, "speed_iv_potion_super");
    }

    // Splash Speed 2 for 30 mins
    private void register_splash_Speed_potion_t2_super_ext_recipe() {
        Material[] ingredients = new Material[]{
                Material.POTION,
                Material.PUMPKIN_PIE,
                Material.GUNPOWDER,
                Material.REDSTONE_BLOCK
        };
        item_creator.registerShapelessRecipe(create_splash_speed_potion_t2_sup(), ingredients, "splash_speed_iv_potion_super");
    }
}
