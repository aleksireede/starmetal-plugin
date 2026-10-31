package net.jonnegaming.starMetal.text;

import io.github.aleksireede.hammershared.SharedItemUpdater;

public class item_strings {

    /** @see SharedItemUpdater#getDurabilityFromRarity(String) */
    public static int getDurabilityFromRarity(final String rarity) {
        return SharedItemUpdater.getDurabilityFromRarity(rarity);
    }

    /** @see SharedItemUpdater#getColorFromRarity(String) */
    public static String getColorFromRarity(final String rarity) {
        return SharedItemUpdater.getColorFromRarity(rarity);
    }

}
