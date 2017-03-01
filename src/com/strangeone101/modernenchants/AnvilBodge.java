package com.strangeone101.modernenchants;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.strangeone101.modernenchants.event.ItemEnchantEvent;
import com.strangeone101.modernenchants.event.ItemUpdateEvent;

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
					EnchantmentStorageMeta bookMeta1 = (EnchantmentStorageMeta) inventory.getItem(0).getItemMeta();
					EnchantmentStorageMeta bookMeta2 = (EnchantmentStorageMeta) inventory.getItem(1).getItemMeta();
					
					Map<Enchantment, Integer> newEnchantments = bookMeta1.getStoredEnchants();
					
					for (Enchantment ench : bookMeta2.getStoredEnchants().keySet()) {
						if (!newEnchantments.containsKey(ench)) {
							newEnchantments.put(ench, bookMeta2.getStoredEnchants().get(ench));
						} else {
							int level1 = newEnchantments.get(ench);
							int level2 = bookMeta2.getStoredEnchants().get(ench);
							
							if (level2 > level1) {
								newEnchantments.put(ench, level2);
							} else if (level1 == level2 && level1 < ench.getMaxLevel()) {
								newEnchantments.put(ench, level1 + 1);
							}							
						}
					}
					
					EnchantmentStorageMeta bookMeta3 = (EnchantmentStorageMeta) inventory.getItem(2).getItemMeta();
					
					bookMeta3.getStoredEnchants().clear();
					bookMeta3.getStoredEnchants().putAll(newEnchantments);
					
					inventory.getItem(2).setItemMeta(bookMeta3);
					
					if (ModernEnchantment.hasEnchantments(inventory.getItem(2))) {
						ModernEnchantment.updateEnchantments(inventory.getItem(2));
					}
					
				} else if (inventory.getItem(2) != null) {
					ItemStack stack = inventory.getItem(2);
					if (ModernEnchantment.hasEnchantments(stack)) {
						ModernEnchantment.updateEnchantments(stack);
						
						for (Enchantment e : event.getCurrentItem().getEnchantments().keySet()) {
							if (ModernEnchantment.isModernEnchantment(e)) {
								ModernEnchantment ench = (ModernEnchantment) e;
								ItemEnchantEvent event2 = new ItemEnchantEvent(null, event.getCurrentItem(), e, event.getCurrentItem().getEnchantments().get(e));
								ench.onEnchant(event2);
							}
						}
					}
				}
			}
			
		}.runTaskLater(ModernEnchants.PLUGIN, 2L);
		
		
	}
	
	

}
