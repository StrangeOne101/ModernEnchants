package com.strangeone101.modernenchants.enchantments;

import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.ModernEnchantments;
import com.strangeone101.modernenchants.ModernEnchants;
import com.strangeone101.modernenchants.Util;
import com.strangeone101.modernenchants.nms.Rarity;

public class Pierce extends ModernEnchantment implements Listener {

	public Pierce(int id) {
		super(id, "piercing");
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		return true;
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.BOW;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public String getName() {
		return "Piercing";
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

	@Override
	public boolean isCursed() {
		return false;
	}

	@Override
	public boolean isTreasure() {
		return false;
	}
	
	@EventHandler
	public void onFire(EntityShootBowEvent event) {
		if (event.isCancelled()) return;
		if (event.getBow().containsEnchantment(ModernEnchantments.PIERCE)) {
			int level = event.getBow().getEnchantmentLevel(ModernEnchantments.PIERCE);
			event.getProjectile().setMetadata("MEC_Piercing", new FixedMetadataValue(ModernEnchants.PLUGIN, level));		
		}
	}
	
	@EventHandler
	public void onHit(ProjectileHitEvent event) {
		if (event.getEntityType() == EntityType.ARROW && event.getHitEntity() != null && event.getEntity().hasMetadata("MEC_Piercing")) {
			Arrow arrow = (Arrow) event.getEntity();
			
			double arrowDamage = 1;
			
			try {
                Method getHandleMethod = arrow.getClass().getMethod("getHandle");
                Object handle = getHandleMethod.invoke(arrow);
                Method getDamage = handle.getClass().getMethod("k");
                arrowDamage = (double) getDamage.invoke(handle);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
			
			//Get the level, remove the old metadata then set the new level
			int level = event.getEntity().getMetadata("MEC_Piercing").get(0).asInt();
			
			int hits = 0;
			double radius = 0.9 + (level * 0.2);
			//if (radius > 0.5) radius = 0.5;
			
			Bukkit.broadcastMessage("Before");
			
			for (Entity e : Util.getEntitiesWithRadius(event.getEntity().getLocation(), radius)) {
				Bukkit.broadcastMessage("Loop");
				if (e instanceof LivingEntity) {
					LivingEntity le = (LivingEntity) e;
					
					if (e.getEntityId() == event.getHitEntity().getEntityId()) continue;
					if (e.getEntityId() == arrow.getEntityId()) continue;
					
					double damage = arrowDamage * (radius - Math.sqrt(e.getLocation().distanceSquared(arrow.getLocation())));
					if (damage < 0.5) damage = 0.5;
					
					le.damage(damage, arrow);
					Bukkit.broadcastMessage("Hit");
					
					hits++;
					
					if (hits >= level * 2.4) {
						break;
					}
				}
			}
		}
	}

	@Override
	public Rarity getRarity() {
		return Rarity.RARE;
	}
}
