package com.strangeone101.modernenchants.event;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemUpdateEvent extends CustomEvent<Event> {

	private Inventory inv;
	private int slot;
	
	public ItemUpdateEvent(ItemStack stack, Inventory inventory, int slot, Enchantment ench, int level) {
		super(null, stack, ench, level);
		
		this.inv = inventory;
		this.slot = slot;
	}
	
	/**DO NOT CALL. EVER.*/
	@Override
	public void setCancelled(boolean b) {};
	
	/***
	 * Gets the inventory the updated item is in
	 * 
	 * @return The inventory
	 */
	public Inventory getInventory() {
		return inv;
	}
	
	/**
	 * Gets the slot the item is updated in
	 * 
	 * @return The slot number
	 */
	public int getSlot() {
		return slot;
	}
	
	public void setItemStack(ItemStack stack) {
		this.inv.setItem(this.slot, stack);
	}

}
