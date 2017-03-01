package com.strangeone101.modernenchants.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.config.StandardConfig;
import com.strangeone101.modernenchants.event.ItemEnchantEvent;
import com.strangeone101.modernenchants.nms.NMSAttributeManager;
import com.strangeone101.modernenchants.nms.NMSAttributeManager.AttributeOperation;
import com.strangeone101.modernenchants.nms.NMSAttributeManager.AttributeType;
import com.strangeone101.modernenchants.nms.Rarity;

public class Slashing extends ModernEnchantment {

	public Slashing(int id) {
		super(id, "slashing");
	}

	@Override
	public Rarity getRarity() {
		return Rarity.RARE;
	}

	@Override
	public String getName() {
		return StandardConfig.config.getString("Slashing.Name");
	}

	@Override
	public int getMaxLevel() {
		return StandardConfig.config.getInt("Slashing.MaxLevel"); //4
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
		return paramEnchantment == SWEEPING_EDGE || paramEnchantment == DAMAGE_ALL;
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
	public void onEnchant(ItemEnchantEvent event) {
		ItemStack stack = event.getItem();
		int level = event.getLevel();
		
		double amount = 0.8 * level;
		
		if (amount > 3.5) amount = 3.5D;
		
		NMSAttributeManager.setAttribute(stack, AttributeType.ATTACK_SPEED, AttributeOperation.ADDITION, -amount);
		
	}

}
