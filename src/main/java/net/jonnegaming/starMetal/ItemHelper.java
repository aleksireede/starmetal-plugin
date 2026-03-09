package net.jonnegaming.starMetal;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import static net.jonnegaming.starMetal.config.config;
import static net.jonnegaming.starMetal.config.customIdKey;

import java.util.Objects;

public class ItemHelper {
    /**
     * Check if the item matches the given custom id
     */
    public static boolean isCustomItem(ItemStack item, int compare) {

        // If no item meta is not custom item
        if (item == null || !item.hasItemMeta()) {
            return false;
        }

        // get custom id
        ItemMeta meta = item.getItemMeta();
        if (meta == null) throw new AssertionError();
        try {
            // Check if the persistent data container has the key
            if (meta.getPersistentDataContainer().has(customIdKey(), PersistentDataType.INTEGER)) {
                // Retrieve the value and check for null
                Integer value = meta.getPersistentDataContainer().get(customIdKey(), PersistentDataType.INTEGER);
                if (value != null && value == compare) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            // Handle the exception, e.g., log the error or return a default value
            System.out.println("Error: NullPointerException occurred while accessing persistent data.");
        }
        return false;
    }

    /**
     * Return the double value of the damage from the input item
     */
    public static int getWeaponDamage(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        int damage = 0;

        // Choose the weapon
        if (meta != null) {
            Integer weaponId = meta.getPersistentDataContainer().get(customIdKey(), PersistentDataType.INTEGER);
            if (weaponId != null) {
                damage = switch (weaponId) {
                    case 0 -> // Venom Staff
                            config.getInt("weapons.lightsaber.attack-damage");
                    case 1 -> // Dark Heart
                            config.getInt("weapons.anime_staff.attack-damage");
                    case 2 -> // Shadow Dagger
                            config.getInt("weapons.shadow_dagger.attack-damage");
                    case 3 -> // Sharp Phantom
                            config.getInt("weapons.rose_dagger.attack-damage");
                    case 5 -> // Doom Sword
                            config.getInt("weapons.doom_sword.attack-damage");
                    case 6 -> // Ruby Scythe
                            config.getInt("weapons.giant_sword.attack-damage");
                    case 7 -> // Ruby Scythe
                            config.getInt("weapons.anime_fan.attack-damage");
                    case 8 -> //Black Clover
                            config.getInt("weapons.black_clover.attack-damage");
                    case 9 -> // Crystal Reaper
                            config.getInt("weapons.crystal_reaper.attack-damage");
                    default -> getDefaultDamageValue(item);
                };
            }
        }
        return damage;
    }

    /**
     * get the default attack damage from vanilla items
     */
    public static int getDefaultDamageValue(ItemStack itemStack) {
        // Check if the item is null or air
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return 1;
        }

        var defaultAttackDamage = itemStack.getType().getDefaultAttributeModifiers().get(Attribute.ATTACK_DAMAGE);
        if (!defaultAttackDamage.isEmpty()) {
            return (int) defaultAttackDamage.iterator().next().getAmount();
        }

        // Fall back to explicit item meta for custom items that override attack damage.
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null && meta.hasAttributeModifiers()) {
            if (Objects.requireNonNull(meta.getAttributeModifiers()).containsKey(Attribute.ATTACK_DAMAGE)) {
                return (int) Objects.requireNonNull(Objects.requireNonNull(meta.getAttributeModifiers().get(Attribute.ATTACK_DAMAGE)).iterator().next()).getAmount();
            }
        }
        // if everything else fails return 1
        return 1;
    }

    // Overload method defaults to just 1 durability
    public static void reduceDurability(Player player, ItemStack item) {
        reduceDurability(player, item, 1);
    }

    /**
     * reduce the durability of an item
     */
    public static void reduceDurability(Player player, ItemStack item, Integer amount) {
        ItemMeta meta = item.getItemMeta();
        // Check if the item is damageable
        if (!(meta instanceof Damageable damageable)) {
            return;
        }

        int durabilityLoss = (amount == null || amount < 1) ? 1 : amount;
        int maxDurability = item.getType().getMaxDurability();
        int currentDamage = damageable.getDamage();

        // Break when this hit would consume the remaining durability.
        if (currentDamage + durabilityLoss >= maxDurability) {
            ItemStack brokenItem = item.clone();
            removeItemFromHands(player, item);
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1F, 1F);
            if (brokenItem.getType() != Material.AIR) {
                player.spawnParticle(Particle.ITEM, player.getLocation(), 8, brokenItem);
            }
            return;
        }

        // Reduce the durability
        int newDamage = currentDamage + durabilityLoss;
        damageable.setDamage(newDamage);

        // Set the modified ItemMeta back to the ItemStack
        item.setItemMeta(meta);
    }

    private static void removeItemFromHands(Player player, ItemStack item) {
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if (mainHand == item || mainHand.equals(item)) {
            player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            return;
        }

        ItemStack offHand = player.getInventory().getItemInOffHand();
        if (offHand == item || offHand.equals(item)) {
            player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
        }
    }
}
