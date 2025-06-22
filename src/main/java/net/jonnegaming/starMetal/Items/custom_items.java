package net.jonnegaming.starMetal.Items;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class custom_items {

    public static ItemStack jedi_lightsaber() {
        return item_creator.createCustomWeapon(new ItemStack(Material.DIAMOND_SWORD), "Jedi Lightsaber", 0, "Eternal", "Laser Sword");
    }

    public static ItemStack rose_dagger() {
        return item_creator.createCustomWeapon(new ItemStack(Material.IRON_SWORD), "Rose Dagger", 3, "Epic", "Sword");
    }

    public static ItemStack shadow_dagger() {
        return item_creator.createCustomWeapon(new ItemStack(Material.IRON_SWORD), "Shadow Dagger", 2, "Rare", "Sword");
    }

    public static ItemStack giant_sword() {
        return item_creator.createCustomWeapon(new ItemStack(Material.DIAMOND_SWORD), "Giant Sword", 6, "Legendary", "Sword");
    }

    public static ItemStack doom_sword() {
        return item_creator.createCustomWeapon(new ItemStack(Material.NETHERITE_SWORD), "Sword of Doom", 5, "Exotic", "Sword");
    }

    public static ItemStack black_clover() {
        return item_creator.createCustomWeapon(new ItemStack(Material.NETHERITE_SWORD), "Black Clover", 8, "Ancient", "Sword");
    }

    public static ItemStack anime_staff() {
        return item_creator.createCustomWeapon(new ItemStack(Material.IRON_SWORD), "Anime Staff", 1, "Divine", "Staff");
    }

    public static ItemStack anime_fan() {
        return item_creator.createCustomWeapon(new ItemStack(Material.IRON_SWORD), "Anime Fan", 7, "Epic", "Ninja Weapon");
    }

    public static ItemStack opiskelijahaalarit(){
        return item_creator.createCustomPants(new ItemStack(Material.LEATHER_LEGGINGS),"Opiskelijahaalarit",4,"Epic", Color.fromRGB(0,0,0));
    }
}
