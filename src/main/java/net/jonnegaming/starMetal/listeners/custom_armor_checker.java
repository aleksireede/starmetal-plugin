package net.jonnegaming.starMetal.listeners;

import net.jonnegaming.starMetal.StarMetal;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class custom_armor_checker implements Listener {
    private final StarMetal plugin;

    public custom_armor_checker(StarMetal plugin){
        this.plugin = plugin;
        registerTask();
    }
    private void registerTask(){
        // Schedule a repeating task that runs every 20 ticks (1 second)
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::checkAllPlayersLeggings, 0L, 20L);
    }

    private void checkAllPlayersLeggings() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            checkLeggings(player);
        }
    }

    private void checkLeggings(Player player) {
        NamespacedKey custom_id = new NamespacedKey(StarMetal.getInstance(), "custom_id");
        ItemStack leggings = player.getInventory().getLeggings();
        if (leggings == null)return;
        ItemMeta pants_meta = leggings.getItemMeta();
        if (pants_meta == null)return;
        if (pants_meta.getPersistentDataContainer().has(custom_id)) {
            switch (pants_meta.getPersistentDataContainer().get(custom_id, PersistentDataType.INTEGER)) {
                case 4 -> player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 100, 1, false, false));
                case null, default -> {
                }
            }
        }
    }
}
