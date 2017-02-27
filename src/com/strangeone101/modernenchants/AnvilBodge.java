package com.strangeone101.modernenchants;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class AnvilBodge implements Listener {
	
	@EventHandler
	public void onClick(final InventoryClickEvent event) {
		if (event.isCancelled()) return;
		if (!(event.getInventory() instanceof AnvilInventory)) return;
		
		new BukkitRunnable() {

			@Override
			public void run() {
				AnvilInventory inventory = (AnvilInventory) event.getInventory();
				
				if (inventory.getItem(0) != null && inventory.getItem(1) != null && inventory.getItem(0).getType() == Material.ENCHANTED_BOOK && inventory.getItem(1).getType() == Material.ENCHANTED_BOOK) {
					
				} else if (inventory.getItem(2) != null) {
					ItemStack stack = inventory.getItem(2);
					if (ModernEnchantment.hasEnchantments(stack)) {
						ModernEnchantment.updateEnchantments(stack);
					}
				}
			}
			
		}.runTaskLater(ModernEnchants.PLUGIN, 2L);
		
		
	}
	
	

}
