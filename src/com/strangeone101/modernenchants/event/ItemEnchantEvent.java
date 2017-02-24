package com.strangeone101.modernenchants.event;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemEnchantEvent extends CustomEvent<org.bukkit.event.enchantment.EnchantItemEvent> {

	public ItemEnchantEvent(EnchantItemEvent event, ItemStack stack, Enchantment ench, int level) {
		super(event, stack, ench, level);
	}

}
