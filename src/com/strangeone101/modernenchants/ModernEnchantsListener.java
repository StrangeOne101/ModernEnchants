package com.strangeone101.modernenchants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class ModernEnchantsListener implements Listener {
	
	@EventHandler
	public void onEnchant(EnchantItemEvent event) {
		if (event.isCancelled()) return;
		
		for (Enchantment e : event.getEnchantsToAdd().keySet()) {
			event.getEnchanter().sendMessage("Test11");
			if (ModernEnchantment.isModernEnchantment(e)) {
				event.getEnchanter().sendMessage("Test22");
				ItemMeta meta = event.getItem().getItemMeta();
				List<String> list = ModernEnchantment.addEnchantmentLore(event.getItem(), event.getEnchantsToAdd());
				event.getEnchanter().sendMessage(list.size() + "");
				meta.setLore(list);
				event.getItem().setItemMeta(meta);
				break;
			}
		}
	}
	
	@EventHandler
	public void onPreEnchant(PrepareItemEnchantEvent event) {
		
	}
	
	@EventHandler
	public void onItemSelect(InventoryClickEvent event) {
		if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
			event.getWhoClicked().sendMessage("Test1");
			if (ModernEnchantment.hasEnchantments(event.getCurrentItem())) {
				event.getWhoClicked().sendMessage("Test2");
				event.getCurrentItem().setItemMeta(ModernEnchantment.updateEnchantments(event.getCurrentItem()).getItemMeta());
			}
		}
	}
	
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event) {
		if (event.isCancelled()) return;
	}

}
