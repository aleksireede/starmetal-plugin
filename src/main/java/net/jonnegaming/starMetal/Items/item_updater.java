package net.jonnegaming.starMetal.Items;

import io.github.aleksireede.hammershared.SharedItemUpdater;
import org.bukkit.inventory.ItemStack;

import static net.jonnegaming.starMetal.helpers.DamageHelper.estimateDamage;

/**
 * Thin bridge that computes starmetal weapon damage before delegating lore
 * enrichment to the shared {@link SharedItemUpdater}.
 */
public class item_updater {

    public static void updateChecker(final ItemStack item) {
        SharedItemUpdater.updateChecker(item, estimateDamage(item));
    }
}
