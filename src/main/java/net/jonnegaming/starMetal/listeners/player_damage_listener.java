package net.jonnegaming.starMetal.listeners;

import net.jonnegaming.starMetal.StarMetal;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

import static net.jonnegaming.starMetal.ItemHelper.getDefaultDamageValue;
import static net.jonnegaming.starMetal.ItemHelper.getWeaponDamage;

public class player_damage_listener implements Listener {
    private final StarMetal plugin;
    private static final String CUSTOM_ID_KEY = "custom_id";

    public player_damage_listener(StarMetal plugin) {
        this.plugin = plugin;
    }

    // Event handler, that disables fall damage when an item is held
    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        if (!(event.getEntity() instanceof Player player)) return;

        // get the necessary variables
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        NamespacedKey custom_id = new NamespacedKey(plugin, "custom_id");

        //disable fall damage when holding a Dark heart weapon
        if (item.hasItemMeta()) {
            if (meta == null) throw new AssertionError();
            if (Objects.equals(meta.getPersistentDataContainer().get(custom_id, PersistentDataType.INTEGER), 1)) {
                event.setCancelled(true);
            }
        }
    }

    /**
     * Check if the entity is undead (e.g., zombie, skeleton, stray, husk, zombified piglin, drowned)
     */
    private boolean isUndead(Entity entity) {
        return entity instanceof LivingEntity && (
                entity.getType().name().contains("ZOMBIE") ||
                        entity.getType().name().contains("SKELETON") ||
                        entity.getType().name().contains("STRAY") ||
                        entity.getType().name().contains("HUSK") ||
                        entity.getType().name().contains("PIGLIN") ||
                        entity.getType().name().contains("DROWNED") ||
                        entity.getType().name().contains("PHANTOM")
        );
    }

    /**
     * Check if the entity is an arthropod (e.g., spider, cave spider, silverfish, endermite)
     */
    private boolean isArthropod(Entity entity) {
        return entity instanceof LivingEntity && (
                entity.getType().name().contains("SPIDER") ||
                        entity.getType().name().contains("SILVERFISH") ||
                        entity.getType().name().contains("ENDERMITE")
        );
    }

    /**
     * Event handler for calculating weapon damage on hit
     */
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player) {
            ItemStack item = player.getInventory().getItemInMainHand();

            // Get weapon damage amount
            double baseDamage = getWeaponDamage(item);

            // Calculate damage modifiers
            double damageMultiplier = 1.0;

            // Strength effect
            if (player.hasPotionEffect(PotionEffectType.STRENGTH)) {
                PotionEffect strengthEffect = player.getPotionEffect(PotionEffectType.STRENGTH);
                if (strengthEffect == null) throw new AssertionError();
                int strengthLevel = strengthEffect.getAmplifier() + 1;
                damageMultiplier += 0.05 * strengthLevel;
            }

            // Weakness effect
            if (player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
                PotionEffect weaknessEffect = player.getPotionEffect(PotionEffectType.WEAKNESS);
                if (weaknessEffect == null) throw new AssertionError();
                int weaknessLevel = weaknessEffect.getAmplifier() + 1;
                damageMultiplier -= 0.05 * weaknessLevel;
            }

            // Sharpness enchantment
            if (item.hasItemMeta() && Objects.requireNonNull(item.getItemMeta()).hasEnchants()) {
                int sharpnessLevel = item.getEnchantmentLevel(Enchantment.SHARPNESS);
                damageMultiplier += 0.05 * sharpnessLevel;
            }

            // Smite enchantment
            if (item.hasItemMeta() && Objects.requireNonNull(item.getItemMeta()).hasEnchants()) {
                int smiteLevel = item.getEnchantmentLevel(Enchantment.SMITE);
                if (smiteLevel > 0 && isUndead(event.getEntity())) {
                    damageMultiplier += 0.05 * smiteLevel;
                }
            }

            // Bane of Arthropods enchantment
            if (item.hasItemMeta() && Objects.requireNonNull(item.getItemMeta()).hasEnchants()) {
                int baneOfArthropodsLevel = item.getEnchantmentLevel(Enchantment.BANE_OF_ARTHROPODS);
                if (baneOfArthropodsLevel > 0 && isArthropod(event.getEntity())) {
                    damageMultiplier += 0.05 * baneOfArthropodsLevel;
                }
            }

            if (baseDamage == 0) {
                baseDamage = getDefaultDamageValue(item);
            }

            // Cap the max damage to +75%
            if (damageMultiplier > 75) {
                damageMultiplier = 75;
            }

            // Calculate final damage
            double finalDamage = baseDamage * damageMultiplier;
            event.setDamage(finalDamage);

            // Calculate armor protections
        }
    }

    /**
     * Calculate damage to players based on the armor and enchants the player has
     */
    @EventHandler
    public void onPlayerHit(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            double damage = event.getDamage();
            EntityDamageEvent.DamageCause cause = event.getCause();

            // Calculate armor protection
            double armorProtection = calculateArmorProtection(player, cause);

            // Reduce the damage percentage
            damage -= damage * (armorProtection / 100);
            if (damage < 0) {
                damage = 0;
            }
            event.setDamage(damage);
        }
    }

    /**
     * Calculate armor protection based on custom defined values
     */
    private double calculateArmorProtection(Player player, EntityDamageEvent.DamageCause cause) {
        double protection = 0;
        NamespacedKey customIdKey = new NamespacedKey(StarMetal.getInstance(), CUSTOM_ID_KEY);

        //Check every armor from player
        for (ItemStack armorPiece : player.getInventory().getArmorContents()) {
            if (armorPiece != null) {
                ItemMeta meta = armorPiece.getItemMeta();

                // Get the base protection value of the armor piece
                if (meta.getPersistentDataContainer().has(customIdKey) && !meta.getPersistentDataContainer().isEmpty() && meta.getPersistentDataContainer().get(customIdKey,PersistentDataType.INTEGER) != null){
                    int custom_id = meta.getPersistentDataContainer().get(customIdKey,PersistentDataType.INTEGER);
                    protection += getCustomArmorProtectionValue(custom_id);
                } else {
                    protection += getArmorProtectionValue(armorPiece.getType());
                }

                // Protection protects from any damage source
                if (armorPiece.containsEnchantment(Enchantment.PROTECTION)) {
                    int level = armorPiece.getEnchantmentLevel(Enchantment.PROTECTION);
                    protection += level; // Each level of protection adds 1% damage reduction
                }

                // Add protection from enchantments
                switch (cause) {
                    case PROJECTILE:
                        if (armorPiece.containsEnchantment(Enchantment.PROJECTILE_PROTECTION)) {
                            int level = armorPiece.getEnchantmentLevel(Enchantment.PROJECTILE_PROTECTION);
                            protection += level; // Each level of projectile protection adds 1% damage reduction
                        }
                        break;
                    case ENTITY_EXPLOSION:
                    case BLOCK_EXPLOSION:
                        if (armorPiece.containsEnchantment(Enchantment.BLAST_PROTECTION)) {
                            int level = armorPiece.getEnchantmentLevel(Enchantment.BLAST_PROTECTION);
                            protection += level; // Each level of blast protection adds 1% damage reduction
                        }
                        break;
                    case FIRE:
                    case FIRE_TICK:
                    case LAVA:
                        if (armorPiece.containsEnchantment(Enchantment.FIRE_PROTECTION)) {
                            int level = armorPiece.getEnchantmentLevel(Enchantment.FIRE_PROTECTION);
                            protection += level; // Each level of fire protection adds 1% damage reduction
                        }
                        break;
                    case FALL:
                        if (armorPiece.containsEnchantment(Enchantment.FEATHER_FALLING)) {
                            int level = armorPiece.getEnchantmentLevel(Enchantment.FEATHER_FALLING);
                            protection += level;
                        }
                    default:
                        break;
                }
            }
        }

        // Cap the protection at 75% of damage
        if (protection > 75) {
            protection = 75;
        }

        return protection;
    }


    /**
     * Get the initial armor value from custom items
     * @param custom_id custom id (int)
     */
    private double getCustomArmorProtectionValue(Integer custom_id){
        int val = 0;
        switch (custom_id){
            case 4: val = 8;
            case null, default: {};
        }
        return val;
    }

    /**
     * Get the initial armor value from items based on vanilla armor values
     */
    private double getArmorProtectionValue(Material material) {
        return switch (material) {
            case NETHERITE_CHESTPLATE, DIAMOND_CHESTPLATE -> 8;
            case NETHERITE_LEGGINGS, DIAMOND_LEGGINGS, IRON_CHESTPLATE -> 6;
            case IRON_LEGGINGS, CHAINMAIL_CHESTPLATE, GOLDEN_CHESTPLATE -> 5;
            case CHAINMAIL_LEGGINGS -> 4;
            case DIAMOND_BOOTS, DIAMOND_HELMET, NETHERITE_HELMET, NETHERITE_BOOTS, GOLDEN_LEGGINGS,
                 LEATHER_CHESTPLATE -> 3;
            case LEATHER_LEGGINGS, GOLDEN_HELMET, IRON_HELMET, IRON_BOOTS, CHAINMAIL_HELMET -> 2;
            case CHAINMAIL_BOOTS, LEATHER_HELMET, LEATHER_BOOTS, GOLDEN_BOOTS -> 1;
            default -> 0;
        };
    }
}
