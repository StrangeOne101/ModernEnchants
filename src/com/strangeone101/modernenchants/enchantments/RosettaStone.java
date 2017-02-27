package com.strangeone101.modernenchants.enchantments;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.ModernEnchants;
import com.strangeone101.modernenchants.config.StandardConfig;
import com.strangeone101.modernenchants.event.ItemKillEntityEvent;
import com.strangeone101.modernenchants.nms.Rarity;

public class RosettaStone extends ModernEnchantment implements Listener {
	
	private Map<Entity, Player> killedEntities = new HashMap<Entity, Player>();
	public double xp_boost;

	public RosettaStone(int id) {
		super(id, "rosetta_stone");
		
		this.xp_boost = Double.valueOf(StandardConfig.config.getString("RosettaStone.XPBonusPerLevel").replaceAll("%", "")) / 100;
	}

	@Override
	public Rarity getRarity() {
		return Rarity.VERY_RARE;
	}

	@Override
	public boolean canEnchantItem(ItemStack itemstack) {
		for (Enchantment e : itemstack.getEnchantments().keySet()) {
			if (conflictsWith(e)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean conflictsWith(Enchantment enchantment) {
		return enchantment == DAMAGE_ARTHROPODS; //lol
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}

	@Override
	public int getMaxLevel() {
		return StandardConfig.config.getInt("RosettaStone.MaxLevel");
	}

	@Override
	public String getName() {
		return StandardConfig.config.getString("RosettaStone.Name", "Rosetta Stone");
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
	
	@EventHandler(priority = EventPriority.LOW)
	public void onDeath(EntityDeathEvent event) {
		if (event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
			Bukkit.broadcastMessage("instanceof");
			EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
			if (event2.getDamager() instanceof Player) {
				Player damager = (Player) event2.getDamager();
				Bukkit.broadcastMessage("player");
				ItemStack stack = damager.getInventory().getItemInMainHand();
				if (stack != null && stack.containsEnchantment(this)) {
					Bukkit.broadcastMessage("rosetta_stone");
					
				}
			
			}		
		}
	}
	
	@Override
	public void onKillEntity(ItemKillEntityEvent event) {
		int level = event.getItem().getEnchantmentLevel(this);
		
		double xpBoostAmount = level * xp_boost;
		
		event.getEvent().setDroppedExp((int) (event.getEvent().getDroppedExp() + (event.getEvent().getDroppedExp() * xpBoostAmount)));
	}

}
