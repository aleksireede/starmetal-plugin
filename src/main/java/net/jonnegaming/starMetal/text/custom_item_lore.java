package net.jonnegaming.starMetal.text;

import io.github.aleksireede.hammershared.SharedText;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class custom_item_lore {
    
    // Dark heart lore
    public static List<Component> anime_staff_lore() {
        List<Component> lore = new ArrayList<>();

        // Add lore lines with MiniMessage formatting
        lore.add(SharedText.miniMessage("<!i><gold>Ability: Rocket Jump"));
        lore.add(SharedText.miniMessage("<!i><white>When right clicked sends a projectile"));
        lore.add(SharedText.miniMessage("<!i><white>Has a 2.5 second cooldown"));
        lore.add(SharedText.miniMessage("<!i><white>When held disables fall damage"));

        return lore;

    }

    // Venom staff lore
    public static List<Component> lightsaber_lore() {
        List<Component> lore = new ArrayList<>();
        lore.add(SharedText.miniMessage("<!i><white>Laser so sharp it can cut most metals."));
        return lore;
    }

    // Sharp phantom lore
    public static List<Component> rose_dagger_lore() {
        List<Component> lore = new ArrayList<>();
        lore.add(SharedText.miniMessage("<!i><white>No special effects."));
        return lore;
    }

    // Shadow dagger lore
    public static List<Component> shadow_dagger_lore() {
        List<Component> lore = new ArrayList<>();
        lore.add(SharedText.miniMessage("<!i><gold>Ability: Teleport"));
        lore.add(SharedText.miniMessage("<!i><white>Launches an ender pearl when right clicked."));
        lore.add(SharedText.miniMessage("<!i><white>Has a 2,5 second cooldown"));
        return lore;
    }

    // Opiskelijahaalarit lore
    public static List<Component> opiskelijahaalarit_lore() {
        List<Component> lore = new ArrayList<>();
        lore.add(SharedText.miniMessage(""));
        lore.add(SharedText.miniMessage("<!i><white>Opiskelijoiden Haalarit")); // Set the custom lore
        return lore;
    }

    // Doom Sword lore
    public static List<Component> doom_sword_lore() {
        List<Component> lore = new ArrayList<>();
        lore.add(SharedText.miniMessage("<!i><gold>Ability: Shoot Special Fireball"));
        lore.add(SharedText.miniMessage("<!i><white>Launches a fire ball when right clicked."));
        lore.add(SharedText.miniMessage("<!i><white>The fireball will strike lightning on impact."));
        lore.add(SharedText.miniMessage("<!i><white>Has a 2 second cooldown"));
        return lore;
    }

    // lore for ruby scythe
    public static List<Component> giant_sword_lore() {
        List<Component> lore = new ArrayList<>();
        lore.add(SharedText.miniMessage("<!i><white>Huge Sword"));
        return lore;
    }

    // anime fan lore
    public static List<Component> anime_fan_lore() {
        List<Component> lore = new ArrayList<>();
        lore.add(SharedText.miniMessage("<!i><green>Gives you ninja abilities:"));
        lore.add(SharedText.miniMessage("<!i><white>This item can be thrown"));
        lore.add(SharedText.miniMessage("<!i><white>You will gain speed boost while using this weapon."));
        return lore;
    }

    // anime fan lore
    public static List<Component> black_clover_lore() {
        List<Component> lore = new ArrayList<>();
        lore.add(SharedText.miniMessage("<!i><white>Ancient heavy weapon."));
        lore.add(SharedText.miniMessage("<!i><red>Warning:"));
        lore.add(SharedText.miniMessage("<!i><white>Due to weapons big size you move slower."));
        return lore;
    }

    public static List<Component> crystal_reaper_lore() {
        List<Component> lore = new ArrayList<>();
        lore.add(SharedText.miniMessage("<!i><gold>Ability: Crystal Skull"));
        lore.add(SharedText.miniMessage("<!i><white>Launches a giant charged wither skull on right click."));
        lore.add(SharedText.miniMessage("<!i><white>Creates a larger explosion on impact."));
        lore.add(SharedText.miniMessage("<!i><white>Has a 0.5 second cooldown"));
        return lore;
    }
}
