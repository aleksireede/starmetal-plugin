package net.jonnegaming.starMetal.listeners;

import net.jonnegaming.starMetal.Items.item_updater;
import net.jonnegaming.starMetal.StarMetal;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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

    private void updateItem(ItemStack item) {
        if (item != null) {
            item_updater.updateChecker(item);
        }
    }

    private void updateItems(Iterable<ItemStack> items) {
        for (ItemStack item : items) {
            updateItem(item);
        }
    }

    private void updatePlayerInventory(Player player) {
        for (ItemStack item : player.getInventory().getContents()) {
            updateItem(item);
        }
    }

    // Crafting table logic
    @EventHandler
    public void onItemCraft(PrepareItemCraftEvent event) {
        updateItem(event.getInventory().getResult());
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        updateItem(event.getCurrentItem());
    }

    // Event Handler to update items when clicking on an inventory
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        updateItem(event.getCurrentItem());
    }

    // Event Handler for shift-clicking items in an inventory
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        for (int slot : event.getRawSlots()) {
            updateItem(event.getView().getItem(slot));
        }
    }

    // Triggered when a player changes the item they are holding
    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        updateItem(event.getPlayer().getInventory().getItem(event.getNewSlot()));
    }

    // Triggered when a player drops an item
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        updateItem(event.getItemDrop().getItemStack());
    }

    // Triggered when picking up items
    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        updateItem(event.getItem().getItemStack());
    }

    // Triggered when preparing anvil use
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        AnvilInventory inventory = event.getInventory();
        updateItem(inventory.getResult());
    }

    // Triggered when a player interacts with a villager
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager) {
            // Handle villager trading interaction
            ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
            updateItem(itemInHand);
        }
    }

    // Triggered when an item is moved in an inventory (e.g., shift-clicking)
    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent event) {
        updateItem(event.getItem());
    }

    // Triggered when an item is taken from a furnace
    @EventHandler
    public void onFurnaceExtract(FurnaceExtractEvent event) {
        updateItem(event.getPlayer().getPickItemStack());
    }

    // Triggered when finished brewing
    @EventHandler
    public void onBrew(BrewEvent event){
        updateItems(event.getResults());
    }

    //Triggered when generating loot XD
    @EventHandler
    public void onLootGenerate(LootGenerateEvent event){
        updateItems(event.getLoot());
    }

    //Triggered when player harvest items
    @EventHandler
    public void onPLayerHarvest(PlayerHarvestBlockEvent event){
        updateItems(event.getItemsHarvested());
    }

    //Triggered when player shears sheep
    @EventHandler
    public void onPlayerShear(PlayerShearEntityEvent event){
        updateItems(event.getDrops());
    }

    //Triggered when hopper or hopper minecart picks up item
    @EventHandler
    public void onInventoryPickup (InventoryPickupItemEvent event){
        updateItem(event.getItem().getItemStack());
    }

    //Triggered when entity drops an item
    @EventHandler
    public void onEntityItemDrop (EntityDropItemEvent event){
        updateItem(event.getItemDrop().getItemStack());
    }

    //Triggered when piglin bartering
    @EventHandler
    public void onPiglinBartering (PiglinBarterEvent event){
        updateItems(event.getOutcome());
    }

    //Triggered when preparing enchant
     @EventHandler
    public void prepareEnchantEvent (PrepareItemEnchantEvent event){
        updateItem(event.getItem());
     }

     //Triggered when enchanting
    @EventHandler
    public void onEnchant (EnchantItemEvent event){
        final ItemStack item = event.getItem();
        // Enchantments are applied after the event fires, so delay by one tick
        // to ensure the item has its new enchantments when we rebuild the lore.
        Bukkit.getScheduler().runTask(StarMetal.getInstance(), () -> updateItem(item));
    }

    //Triggered on block drops
    @EventHandler
    public void onBlockDropItem (BlockDropItemEvent event){
        updateItems(event.getBlock().getDrops());
    }

    //Triggered when item is dispensed from block
    @EventHandler
    public void onItemDispense (BlockDispenseEvent event){
        updateItem(event.getItem());
    }

    //Triggered when multiple items dispensed
    @EventHandler
    public void onMultiDispense (BlockDispenseLootEvent event){
        updateItems(event.getDispensedLoot());
    }

    //Triggered when Crafter crafts item
    @EventHandler
    public void onCrafterCraft (CrafterCraftEvent event){
        updateItem(event.getResult());
    }

    //Triggered when fishing
    @EventHandler
    public void onFish (PlayerFishEvent event){
        if (event.getCaught() != null) {
            updateItem(event.getCaught().getPickItemStack());
        }
    }

    // Triggered when a player joins with existing custom items in inventory
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        updatePlayerInventory(event.getPlayer());
    }

    // Triggered when a player swaps items between main hand and offhand
    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        updateItem(event.getMainHandItem());
        updateItem(event.getOffHandItem());
    }

    // Triggered when an item is consumed and may leave a replacement item behind
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        updateItem(event.getItem());
        updateItem(event.getReplacement());
    }

    // Triggered when preparing smithing table output
    @EventHandler
    public void onPrepareSmithing(PrepareSmithingEvent event) {
        updateItem(event.getResult());
    }

    // Triggered when taking the smithing result item
    @EventHandler
    public void onSmithItem(SmithItemEvent event) {
        updateItem(event.getCurrentItem());
    }

    // Triggered when preparing grindstone output
    @EventHandler
    public void onPrepareGrindstone(PrepareGrindstoneEvent event) {
        updateItem(event.getResult());
    }
}
