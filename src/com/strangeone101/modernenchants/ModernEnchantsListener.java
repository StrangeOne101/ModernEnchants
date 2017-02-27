package com.strangeone101.modernenchants;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.strangeone101.modernenchants.event.ItemKillEntityEvent;
import com.strangeone101.modernenchants.event.ItemUpdateEvent;

public class ModernEnchantsListener implements Listener {
	
	@EventHandler
	public void onEnchant(final EnchantItemEvent event) {
		if (event.isCancelled()) return;
		
		for (Enchantment e : event.getEnchantsToAdd().keySet()) {
			event.getEnchanter().sendMessage("Test11");
			if (ModernEnchantment.isModernEnchantment(e)) {
				event.getEnchanter().sendMessage("Test22");
				
				if (event.getItem().getType() == Material.BOOK) {
					new BukkitRunnable() {

						@Override
						public void run() {
							ItemStack stack = event.getInventory().getItem(0);
							List<String> list = ModernEnchantment.addEnchantmentLore(stack, event.getEnchantsToAdd());
							ItemMeta meta = stack.getItemMeta();
							meta.setLore(list);
							stack.setItemMeta(meta);
							/*stack.getEnchantments().clear();
							stack.getEnchantments().putAll(event.getEnchantsToAdd());*/
							event.getInventory().setItem(0, stack);
						}
					}.runTaskLater(ModernEnchants.PLUGIN, 1L);
				} else {
					ItemMeta meta = event.getItem().getItemMeta();
					List<String> list = ModernEnchantment.addEnchantmentLore(event.getItem(), event.getEnchantsToAdd());
					event.getEnchanter().sendMessage(list.size() + "");
					meta.setLore(list);
					event.getItem().setItemMeta(meta);
				}
				
				break;
			}
		}
	}
	
	@EventHandler
	public void onPreEnchant(PrepareItemEnchantEvent event) {
		
	}
	
	@EventHandler
	public void onItemSelect(InventoryClickEvent event) {
		if (event.getCurrentItem() != null) {
			if (ModernEnchantment.hasEnchantments(event.getCurrentItem())) {
				//event.getWhoClicked().sendMessage("Has custom enchantments");
				event.getInventory().setItem(event.getSlot(), ModernEnchantment.updateEnchantments(event.getCurrentItem()));
			
				for (Enchantment e : event.getCurrentItem().getEnchantments().keySet()) {
					if (ModernEnchantment.isModernEnchantment(e)) {
						ModernEnchantment ench = (ModernEnchantment) e;
						ItemUpdateEvent event2 = new ItemUpdateEvent(event.getCurrentItem(), event.getClickedInventory(), event.getSlot(), ench, event.getCurrentItem().getEnchantments().get(e));
						ench.onUpdate(event2);
					}
				}
			} else {
				//event.getWhoClicked().sendMessage(event.getCurrentItem().getEnchantments().keySet().toString());
			}
		}
	}
	
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event) {
		if (event.isCancelled()) return;
		
		//if (!(event.getInventory() instanceof org.bukkit.in)) {
			ModernEnchantment.updateInventory(event.getInventory());			
		//}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		ModernEnchantment.updateInventory(event.getPlayer().getInventory());	
	}
	
	/*@EventHandler
	public void onSpawn(EntitySpawnEvent event) {
		if (event.isCancelled()) return;
		
		if (event.getEntityType() == EntityType.VILLAGER) {
			Villager villager = (Villager) event.getEntity();
			for (MerchantRecipe recipe : villager.getRecipes()) {
				//if (recipe.)
			}
		}
	}*/
	
	@EventHandler
	public void onTrade(VillagerAcquireTradeEvent event) {
		boolean shouldReplace = false;
		Bukkit.broadcastMessage("Villager got trade boi");
		if (ModernEnchantment.hasEnchantments(event.getRecipe().getResult())) {
			shouldReplace = true;
		}
		for (ItemStack stack : event.getRecipe().getIngredients()) {
			if (ModernEnchantment.hasEnchantments(stack)) {
				shouldReplace = true;
			}
		}
		
		if (shouldReplace) {
			Bukkit.broadcastMessage("Updated villager trade");
			MerchantRecipe recipe = new MerchantRecipe(ModernEnchantment.updateEnchantments(event.getRecipe().getResult()), event.getRecipe().getMaxUses());
			for (ItemStack stack : event.getRecipe().getIngredients()) {
				if (ModernEnchantment.hasEnchantments(stack)) {
					recipe.addIngredient(ModernEnchantment.updateEnchantments(stack));
				} else {
					recipe.addIngredient(stack);
				}
			}
			recipe.setExperienceReward(event.getRecipe().hasExperienceReward());
			recipe.setUses(event.getRecipe().getUses());
			
			event.setRecipe(recipe);
		}
	}
	
	
	
	@EventHandler(priority = EventPriority.LOW)
	public void onDeath(EntityDeathEvent event) {
		if (event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
			if (event2.getDamager() instanceof Player) {
				Player damager = (Player) event2.getDamager();
				ItemStack stack = damager.getInventory().getItemInMainHand();
				for (Enchantment e : stack.getEnchantments().keySet()) {
					if (ModernEnchantment.isModernEnchantment(e)) {
						ModernEnchantment ench = (ModernEnchantment) e;
						ItemKillEntityEvent event3 = new ItemKillEntityEvent(event, stack, e, stack.getEnchantments().get(e));
						ench.onKillEntity(event3);						
					}
				}
			}
		}
	}
}
