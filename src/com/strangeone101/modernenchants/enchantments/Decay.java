package com.strangeone101.modernenchants.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.config.StandardConfig;
import com.strangeone101.modernenchants.nms.Rarity;

public class Decay extends ModernEnchantment {

	public Decay(int id) {
		super(id, "decay");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Rarity getRarity() {
		return Rarity.VERY_RARE;
	}

	@Override
	public String getName() {
		return StandardConfig.config.getString("Decay.Name", "Curse of Decay");
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ALL;
	}

	@Override
	public boolean isTreasure() {
		return true;
	}

	@Override
	public boolean isCursed() {
		return true;
	}

	@Override
	public boolean conflictsWith(Enchantment paramEnchantment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canEnchantItem(ItemStack paramItemStack) {
		// TODO Auto-generated method stub
		return false;
	}

}
