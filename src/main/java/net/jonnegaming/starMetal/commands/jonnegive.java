package net.jonnegaming.starMetal.commands;

import io.github.aleksireede.hammershared.SharedText;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.jonnegaming.starMetal.Items.custom_items;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.jspecify.annotations.NonNull;

import static net.jonnegaming.starMetal.Items.potions.hastepotion.*;
import static net.jonnegaming.starMetal.Items.potions.speedpotion.*;

public class jonnegive implements BasicCommand {

    @Override
    public void execute(CommandSourceStack commandSourceStack, String @NonNull [] args) {
        CommandSender sender = commandSourceStack.getSender();
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return;
        }

        giveCustomItems(player);
        player.sendMessage("You have been given all custom items!");
    }

    @Override
    public String permission() {
        return "starmetal.jonnegivecommand";
    }

    // Create a command that gives a shulker box full of custom items
    private static void giveCustomItems(Player player) {
        PlayerInventory inv = player.getInventory();
        inv.addItem(createCustomItemBox());
    }

    // Create the custom shulker box
    private static ItemStack createCustomItemBox() {
        ItemStack shulkerBoxItem = new ItemStack(Material.PURPLE_SHULKER_BOX);
        BlockStateMeta meta = (BlockStateMeta) shulkerBoxItem.getItemMeta();
        if (meta == null) {
            return shulkerBoxItem;
        }

        ShulkerBox shulkerBox = (ShulkerBox) meta.getBlockState();
        shulkerBox.getInventory().addItem(
                custom_items.rose_dagger(),
                custom_items.jedi_lightsaber(),
                custom_items.shadow_dagger(),
                custom_items.giant_sword(),
                custom_items.doom_sword(),
                custom_items.crystal_reaper(),
                custom_items.black_clover(),
                custom_items.anime_staff(),
                custom_items.anime_fan(),
                custom_items.opiskelijahaalarit(),
                create_haste_potion_t1(),
                create_splash_haste_potion_t1(),
                create_haste_potion_t1_ext(),
                create_splash_haste_potion_t1_ext(),
                create_haste_potion_t1_sup(),
                create_splash_haste_potion_t1_sup(),
                create_haste_potion_t2(),
                create_splash_haste_potion_t2(),
                create_haste_potion_t2_ext(),
                create_splash_haste_potion_t2_ext(),
                create_haste_potion_t2_sup(),
                create_splash_haste_potion_t2_sup(),
                create_speed_potion_t1(),
                create_splash_speed_potion_t1(),
                create_speed_potion_t1_ext(),
                create_splash_speed_potion_t1_ext(),
                create_speed_potion_t1_sup(),
                create_splash_speed_potion_t1_sup(),
                create_speed_potion_t2(),
                create_splash_speed_potion_t2(),
                create_speed_potion_t2_ext(),
                create_splash_speed_potion_t2_ext(),
                create_speed_potion_t2_sup(),
                create_splash_speed_potion_t2_sup()
        );

        meta.setBlockState(shulkerBox);
        meta.customName(SharedText.miniMessage("<rainbow>StarMetal <light_purple>Item Cache"));
        shulkerBoxItem.setItemMeta(meta);
        return shulkerBoxItem;
    }
}
