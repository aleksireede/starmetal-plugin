package net.jonnegaming.starMetal.listeners;

import net.jonnegaming.starMetal.Items.item_updater;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PiglinBarterEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

public class item_listener implements Listener {

    // Crafting table logic
    @EventHandler
    public void onItemCraft(PrepareItemCraftEvent event) {
        ItemStack result = event.getInventory().getResult();
        if (result != null) {
            item_updater.updateChecker(result);
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        ItemStack item = event.getCurrentItem();
        if (item != null) {
            item_updater.updateChecker(item);
        }
    }

    // Event Handler to update items when clicking on an inventory
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if (item != null) {
            item_updater.updateChecker(item);
        }
    }

    // Event Handler for shift-clicking items in an inventory
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        for (int slot : event.getRawSlots()) {
            ItemStack item = event.getView().getItem(slot);
            if (item != null) {
                item_updater.updateChecker(item);
            }
        }
    }

    // Triggered when a player changes the item they are holding
    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItem(event.getNewSlot());
        if (item != null) {
            item_updater.updateChecker(item);
        }
    }

    // Triggered when a player drops an item
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        item_updater.updateChecker(item);
    }

    // Triggered when picking up items
    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        ItemStack item = event.getItem().getItemStack();
        item_updater.updateChecker(item);
    }

    // Triggered when preparing anvil use
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        AnvilInventory inventory = event.getInventory();
        ItemStack result = inventory.getResult();
        if (result != null) {
            item_updater.updateChecker(result);
        }
    }

    // Triggered when a player interacts with a villager
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager) {
            // Handle villager trading interaction
            ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
            item_updater.updateChecker(itemInHand);
        }
    }

    // Triggered when an item is moved in an inventory (e.g., shift-clicking)
    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent event) {
        ItemStack item = event.getItem();
        item_updater.updateChecker(item);
    }

    // Triggered when an item is taken from a furnace
    @EventHandler
    public void onFurnaceExtract(FurnaceExtractEvent event) {
        ItemStack item = event.getPlayer().getPickItemStack();
        item_updater.updateChecker(item);
    }

    // Triggered when finished brewing
    @EventHandler
    public void onBrew(BrewEvent event){
        for (ItemStack item:event.getResults()) {
            item_updater.updateChecker(item);
        }
    }

    //Triggered when generating loot XD
    @EventHandler
    public void onLootGenerate(LootGenerateEvent event){
        for (ItemStack item:event.getLoot()){
            item_updater.updateChecker(item);
        }
    }

    //Triggered when player harvest items
    @EventHandler
    public void onPLayerHarvest(PlayerHarvestBlockEvent event){
        for (ItemStack item:event.getItemsHarvested()){
            item_updater.updateChecker(item);
        }
    }

    //Triggered when player shears sheep
    @EventHandler
    public void onPlayerShear(PlayerShearEntityEvent event){
        for (ItemStack item:event.getDrops()){
            item_updater.updateChecker(item);
        }
    }

    //Triggered when hopper or hopper minecart picks up item
    @EventHandler
    public void onInventoryPickup (InventoryPickupItemEvent event){
        item_updater.updateChecker(event.getItem().getItemStack());
    }

    //Triggered when entity drops an item
    @EventHandler
    public void onEntityItemDrop (EntityDropItemEvent event){
        item_updater.updateChecker(event.getItemDrop().getItemStack());
    }

    //Triggered when piglin bartering
    @EventHandler
    public void onPiglinBartering (PiglinBarterEvent event){
        for (ItemStack item:event.getOutcome()){
            item_updater.updateChecker(item);
        }
    }

    //Triggered when preparing enchant
     @EventHandler
    public void prepareEnchantEvent (PrepareItemEnchantEvent event){
        item_updater.updateChecker(event.getItem());
     }

     //Triggered when enchanting
    @EventHandler
    public void onEnchant (EnchantItemEvent event){
        item_updater.updateChecker(event.getItem());
    }

    //Triggered on block drops
    @EventHandler
    public void onBlockDropItem (BlockDropItemEvent event){
        for (ItemStack item:event.getBlock().getDrops()){
            item_updater.updateChecker(item);
        }
    }

    //Triggered when item is dispensed from block
    @EventHandler
    public void onItemDispense (BlockDispenseEvent event){
        item_updater.updateChecker(event.getItem());
    }

    //Triggered when multiple items dispensed
    @EventHandler
    public void onMultiDispense (BlockDispenseLootEvent event){
        for (ItemStack item:event.getDispensedLoot()){
            item_updater.updateChecker(item);
        }
    }

    //Triggered when crafter crafts item
    @EventHandler
    public void onCrafterCraft (CrafterCraftEvent event){
        item_updater.updateChecker(event.getResult());
    }

    //Triggered when fishing
    @EventHandler
    public void onFish (PlayerFishEvent event){
        assert event.getCaught() != null;
        ItemStack item = event.getCaught().getPickItemStack();
        item_updater.updateChecker(item);
    }
}
