package com.strangeone101.modernenchants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class ModernEnchantsListener implements Listener {
	
	@EventHandler
	public void onEnchant(EnchantItemEvent event) {
		if (event.isCancelled()) return;
		
		if (ModernEnchantment.hasEnchantments(event.getItem())) {
			ModernEnchantment.addEnchantmentLore(event.getItem());
		}
	
	}
	
	@EventHandler
	public void onPreEnchant(PrepareItemEnchantEvent event) {
		
	}
	
	@EventHandler
	public void onItemSelect(InventoryClickEvent event) {
		if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
			ItemMeta meta = event.getCurrentItem().getItemMeta();
			
			if (meta.hasEnchants()) {
				for (Enchantment e : meta.getEnchants().keySet()) {
					if (ModernEnchantments.enchantments.values().contains(e)) {
						boolean foundLore = false;
						if (meta.hasLore()) {
							for (String loreLine : meta.getLore()) {
								if (loreLine.startsWith(ModernEnchantment.ENCH_PREFIX + e.getName())) {
									foundLore = true;
									break;
								}
							}
						}
						if (!foundLore) {
							List<String> newLore = new ArrayList<String>();
							newLore.add(ModernEnchantment.ENCH_PREFIX + e.getName() + " " + ModernEnchants.getIVX(meta.getEnchants().get(e)));
							if (meta.hasLore()) {
								newLore.addAll(meta.getLore());
							}
							meta.setLore(newLore);
							event.getCurrentItem().setItemMeta(meta);
						}
					}
				}
			}
		}
	}

}
