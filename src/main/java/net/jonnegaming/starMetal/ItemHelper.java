package net.jonnegaming.starMetal;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import static net.jonnegaming.starMetal.config.config;

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
        NamespacedKey customIdKey = new NamespacedKey(StarMetal.getInstance(), "custom_id");
        if (meta == null) throw new AssertionError();
        try {
            // Check if the persistent data container has the key
            if (meta.getPersistentDataContainer().has(customIdKey, PersistentDataType.INTEGER)) {
                // Retrieve the value and check for null
                Integer value = meta.getPersistentDataContainer().get(customIdKey, PersistentDataType.INTEGER);
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
        NamespacedKey custom_id = new NamespacedKey(StarMetal.getInstance(), "custom_id");
        int damage = 0;

        // Choose the weapon
        if (meta != null) {
            Integer weaponId = meta.getPersistentDataContainer().get(custom_id, PersistentDataType.INTEGER);
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

        // Check for specific items and return their default damage values
        return (int) switch (itemStack.getType()) {
            case IRON_SWORD, MACE, NETHERITE_PICKAXE, NETHERITE_SHOVEL -> 6;
            case IRON_AXE, DIAMOND_AXE, STONE_AXE, TRIDENT -> 9;
            case WOODEN_SWORD, GOLDEN_SWORD, IRON_PICKAXE, IRON_SHOVEL -> 4;
            case STONE_SWORD, DIAMOND_PICKAXE, DIAMOND_SHOVEL -> 5;
            case DIAMOND_SWORD, GOLDEN_AXE, WOODEN_AXE -> 7;
            case NETHERITE_SWORD -> 8;
            case NETHERITE_AXE -> 10;
            case WOODEN_SHOVEL, WOODEN_PICKAXE, GOLDEN_PICKAXE, GOLDEN_SHOVEL -> 2;
            case STONE_SHOVEL, STONE_PICKAXE -> 3;
            // Add more cases for other items as needed
            default -> {
                // Check if the item has item meta and attribute modifiers
                ItemMeta meta = itemStack.getItemMeta();
                if (meta != null && meta.hasAttributeModifiers()) {
                    if (Objects.requireNonNull(meta.getAttributeModifiers()).containsKey(Attribute.ATTACK_DAMAGE)) {
                        yield Objects.requireNonNull(meta.getAttributeModifiers().get(Attribute.ATTACK_DAMAGE)).iterator().next().getAmount();
                    }
                }
                yield 1;
            }
        };
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

        // Break item when 0 or less durability left
        if (item.getType().getMaxDurability() <= damageable.getDamage()) {
            player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            // Get the item in the main hand
            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            // Check if the item is not air (empty hand)
            if (itemInHand.getType() != Material.AIR) {
                // Set the particle to match the item in hand
                player.spawnParticle(Particle.BLOCK, player.getLocation(), 1);
                // Play the sound
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1F, 1F);
            } else {
                // If the hand is empty, you might want to handle it differently
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1F, 1F);
                player.spawnParticle(Particle.BLOCK, player.getLocation(), 1);
            }
            return;
        }

        // Reduce the durability
        int newDamage = damageable.getDamage() + amount; // Increase damage by 1
        damageable.setDamage(newDamage);

        // Set the modified ItemMeta back to the ItemStack
        item.setItemMeta(meta);
    }
}
