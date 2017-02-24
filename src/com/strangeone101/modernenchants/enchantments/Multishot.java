package com.strangeone101.modernenchants.enchantments;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.config.StandardConfig;
import com.strangeone101.modernenchants.nms.Rarity;

public class Multishot extends ModernEnchantment implements Listener {

	public int arrows;
	
	public Multishot(int id) {
		super(id, "multishot");
		
		arrows = StandardConfig.config.getInt("Multishot.Arrows", 3);
	}

	@Override
	public Rarity getRarity() {
		return Rarity.VERY_RARE;
	}

	@Override
	public String getName() {
		return "Multishot";
	}

	@Override
	public int getMaxLevel() {
		return 0;
	}

	@Override
	public int getStartLevel() {
		return 0;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.BOW;
	}

	@Override
	public boolean isTreasure() {
		return true;
	}

	@Override
	public boolean isCursed() {
		return false;
	}

	@Override
	public boolean conflictsWith(Enchantment enchantment) {
		return enchantment == ARROW_INFINITE;
	}

	@Override
	public boolean canEnchantItem(ItemStack paramItemStack) {
		return true;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onShoot(EntityShootBowEvent event) {
		if (event.isCancelled()) return;
		
		for (Enchantment e : event.getBow().getEnchantments().keySet()) {
			if (ModernEnchantment.isModernEnchantment(e)) {
				if (e.getId() == this.getId()) {
					Random random = new Random();
					
					int fireCount = arrows - 1;
					
					if (event.getEntity() instanceof Player) {
						Player p = (Player) event.getEntity();
						while (!p.getInventory().contains(Material.ARROW, fireCount + 1) && fireCount > 0) {
							fireCount--;
						}
					}
					
					for (int i = 0; i < fireCount; i++) {
						Vector vec = event.getProjectile().getVelocity();
						/*double x = (random.nextDouble() - 0.5) * 0.1;
						double y = (random.nextDouble() - 0.5) * 0.1;
						double z = (random.nextDouble() - 0.5) * 0.1;
						vec.add(new Vector(x, y, z));*/
						Arrow arrow = event.getEntity().getWorld().spawnArrow(event.getProjectile().getLocation(), vec, event.getForce(), 0.1F);
						event.getBow().setDurability((short) (event.getBow().getDurability() - 1));
					}
				}
			}
		}
	}

}
