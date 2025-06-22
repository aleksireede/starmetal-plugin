package net.jonnegaming.starMetal;

import com.mojang.brigadier.CommandDispatcher;
import net.jonnegaming.starMetal.Recipes.armor.Opiskelijahaalarit_recipe;
import net.jonnegaming.starMetal.Recipes.potions.haste_potion_recipe;
import net.jonnegaming.starMetal.Recipes.potions.speed_potion_recipe;
import net.jonnegaming.starMetal.Recipes.weapons.*;
import net.jonnegaming.starMetal.commands.jonnegive;
import net.jonnegaming.starMetal.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static net.jonnegaming.starMetal.config.config;

public final class StarMetal extends JavaPlugin {
    private static StarMetal instance;
    public static StarMetal getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Custom Crafting Plugin Enabled!");
        Bukkit.getPluginManager().registerEvents(new haste_potion_recipe(),this);
        Bukkit.getPluginManager().registerEvents(new speed_potion_recipe(),this);
        Bukkit.getPluginManager().registerEvents(new animestaff_recipe(this),this);
        Bukkit.getPluginManager().registerEvents(new anime_fan_recipe(this),this);
        Bukkit.getPluginManager().registerEvents(new shadow_dagger_recipe(this),this);
        Bukkit.getPluginManager().registerEvents(new rose_dagger_recipe(this),this);
        Bukkit.getPluginManager().registerEvents(new jedi_lightsaber_recipe(this),this);
        Bukkit.getPluginManager().registerEvents(new Opiskelijahaalarit_recipe(this),this);
        Bukkit.getPluginManager().registerEvents(new doom_sword_recipe(this),this);
        Bukkit.getPluginManager().registerEvents(new black_clover_recipe(this),this);
        Bukkit.getPluginManager().registerEvents(new giant_sword_recipe(this),this);
        Bukkit.getPluginManager().registerEvents(new animestaff_recipe(this),this);
        Bukkit.getPluginManager().registerEvents(new custom_armor_checker(this),this);
        Bukkit.getPluginManager().registerEvents(new jonnegive(),this);
        Bukkit.getPluginManager().registerEvents(new item_listener(),this);
        Bukkit.getPluginManager().registerEvents(new join_events(),this);
        Bukkit.getPluginManager().registerEvents(new player_damage_listener(this),this);
        Bukkit.getPluginManager().registerEvents(new projectile_listener(),this);
        Bukkit.getPluginManager().registerEvents(new weapon_damage(),this);
        CommandDispatcher<Player> dispatcher = new CommandDispatcher<>();
        dispatcher.register(jonnegive.createCommand().createBuilder());

        // Assuming you have a way to register Brigadier commands with Paper's command system
        // This step is conceptual as Paper does not directly expose Brigadier registration
        getLogger().info("Custom items command registered!");


        config = getConfig();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
