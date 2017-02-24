package com.strangeone101.modernenchants.event;

import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public class ItemDisenchantEvent extends CustomEvent<Event> {

	private Map<Enchantment, Integer> removed;
	
	public ItemDisenchantEvent(ItemStack stack, Map<Enchantment, Integer> toRemove, Enchantment ench, int level) {
		super(null, stack, ench, level);
		
		this.removed = toRemove;
	}
	
	public Map<Enchantment, Integer> getRemovedEnchantments() {
		return removed;
	}

}
