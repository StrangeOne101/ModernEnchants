package com.strangeone101.modernenchants.enchantments;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.config.StandardConfig;
import com.strangeone101.modernenchants.nms.Rarity;

public class Decapitation extends ModernEnchantment implements Listener {

	private double chance;
	private boolean witherskullenabled;
	
	public Decapitation(int id) {
		super(id, "decapitation");
		
		this.chance = Double.parseDouble(StandardConfig.config.getString("Decapitation.ChancePerLevel", "20%").replaceAll("%", "")) / 100;
		this.witherskullenabled = StandardConfig.config.getBoolean("Decapitation.OnWitherSkeletons", false);
	}

	@Override
	public Rarity getRarity() {
		return Rarity.RARE;
	}

	@Override
	public String getName() {
		return StandardConfig.config.getString("Decapitation.Name", "Decapitation");
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}

	@Override
	public boolean isTreasure() {
		return false;
	}

	@Override
	public boolean isCursed() {
		return false;
	}

	@Override
	public boolean conflictsWith(Enchantment paramEnchantment) {
		return false;
	}

	@Override
	public boolean canEnchantItem(ItemStack paramItemStack) {
		return true;
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onDeath(EntityDeathEvent event) {
		if (event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
			if (event2.getDamager() instanceof Player) {
				Player damager = (Player) event2.getDamager();
				Bukkit.broadcastMessage("Decapitation debug - is player");
				ItemStack stack = damager.getInventory().getItemInMainHand();
				if (stack != null && stack.containsEnchantment(this)) {
					Bukkit.broadcastMessage("Decapitation debug - contains ench");
					int level = stack.getEnchantmentLevel(this);
					
					double chance = level * this.chance;
					
					if (Math.random() <= chance) {
						
					}
				}
			
			}		
		}
	}
	
	public ItemStack getHead(EntityType type) {
		if (type == EntityType.ZOMBIE) return new ItemStack(Material.SKULL_ITEM, 2);
	}

}
