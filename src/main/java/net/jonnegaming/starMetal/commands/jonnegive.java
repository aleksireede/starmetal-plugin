package net.jonnegaming.starMetal.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import org.bukkit.entity.Player;

import net.jonnegaming.starMetal.Items.custom_items;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;

import java.util.Objects;

public class jonnegive implements Listener {

    public static LiteralCommandNode<Player> createCommand() {
        LiteralArgumentBuilder<Player> command = LiteralArgumentBuilder.<Player>literal("givecustomitems")
                .requires(Objects::nonNull)
                .executes(context -> {
                    Player player = context.getSource();
                    giveCustomItems(player);
                    player.sendMessage("You have been given all custom items!");
                    return Command.SINGLE_SUCCESS;
                });

        return command.build();
    }


    private static void giveCustomItems(Player player) {
        PlayerInventory inv = player.getInventory();
        inv.addItem(custom_items.rose_dagger());
        inv.addItem(custom_items.jedi_lightsaber());
        inv.addItem(custom_items.shadow_dagger());
        inv.addItem(custom_items.giant_sword());
        inv.addItem(custom_items.doom_sword());
        inv.addItem(custom_items.black_clover());
        inv.addItem(custom_items.anime_staff());
        inv.addItem(custom_items.anime_fan());
        inv.addItem(custom_items.opiskelijahaalarit());
    }

}
