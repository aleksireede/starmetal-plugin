package net.jonnegaming.starMetal;

import io.github.aleksireede.hammershared.SharedItemUpdater;
import net.jonnegaming.starMetal.Recipes.armor.Opiskelijahaalarit_recipe;
import net.jonnegaming.starMetal.Recipes.potions.haste_potion_recipe;
import net.jonnegaming.starMetal.Recipes.potions.speed_potion_recipe;
import net.jonnegaming.starMetal.Recipes.weapons.*;
import net.jonnegaming.starMetal.commands.jonnegive;
import net.jonnegaming.starMetal.listeners.*;
import net.jonnegaming.starMetal.text.custom_item_lore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import static net.jonnegaming.starMetal.config.config;

@SuppressWarnings("UnstableApiUsage")
public final class StarMetal extends JavaPlugin {
    private static StarMetal instance;
    public static StarMetal getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
        // Paper 1.21+: brigadier commands must be registered via JavaPlugin.registerCommand in onLoad()
        registerCommand("givecustomitems", "Give all StarMetal custom items", new jonnegive());
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        syncMissingConfigValues();
        config = getConfig();
        VersionChecker.checkForUpdates(this);

        SharedItemUpdater.init(this);

        // Register item-specific description lore for each custom item ID
        SharedItemUpdater.registerLore(0, custom_item_lore::lightsaber_lore);
        SharedItemUpdater.registerLore(1, custom_item_lore::anime_staff_lore);
        SharedItemUpdater.registerLore(2, custom_item_lore::shadow_dagger_lore);
        SharedItemUpdater.registerLore(3, custom_item_lore::rose_dagger_lore);
        SharedItemUpdater.registerLore(4, custom_item_lore::opiskelijahaalarit_lore);
        SharedItemUpdater.registerLore(5, custom_item_lore::doom_sword_lore);
        SharedItemUpdater.registerLore(6, custom_item_lore::giant_sword_lore);
        SharedItemUpdater.registerLore(7, custom_item_lore::anime_fan_lore);
        SharedItemUpdater.registerLore(8, custom_item_lore::black_clover_lore);
        SharedItemUpdater.registerLore(9, custom_item_lore::crystal_reaper_lore);

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
        Bukkit.getPluginManager().registerEvents(new crystal_reaper_recipe(this),this);
        Bukkit.getPluginManager().registerEvents(new black_clover_recipe(this),this);
        Bukkit.getPluginManager().registerEvents(new giant_sword_recipe(this),this);
        Bukkit.getPluginManager().registerEvents(new custom_armor_checker(this),this);
        Bukkit.getPluginManager().registerEvents(new item_listener(),this);
        Bukkit.getPluginManager().registerEvents(new join_events(),this);
        Bukkit.getPluginManager().registerEvents(new player_damage_listener(),this);
        Bukkit.getPluginManager().registerEvents(new projectile_listener(),this);
        Bukkit.getPluginManager().registerEvents(new status_display_listener(),this);
        Bukkit.getPluginManager().registerEvents(new weapon_damage(),this);
    }

    @Override
    public void onDisable() {
        StatusDisplay.clearAll();
    }

    private void syncMissingConfigValues() {
        FileConfiguration liveConfig = getConfig();
        ConfigurationSection defaults = liveConfig.getDefaults();
        if (defaults == null) {
            return;
        }

        int missingValues = 0;
        for (String path : defaults.getKeys(true)) {
            if (!liveConfig.isSet(path)) {
                missingValues++;
            }
        }

        if (missingValues == 0) {
            return;
        }

        liveConfig.options().copyDefaults(true);
        saveConfig();
        reloadConfig();
        getLogger().info("Added " + missingValues + " missing config value(s) from default config.yml");
    }
}
