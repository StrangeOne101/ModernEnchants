package com.strangeone101.modernenchants.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.nms.Rarity;

public class Dashing extends ModernEnchantment {

	public Dashing(int id, String key_name) {
		super(id, "fast_walk");
	}

	@Override
	public Rarity getRarity() {
		return Rarity.RARE;
	}

	@Override
	public String getName() {
		return "Dashing";
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
		return EnchantmentTarget.ARMOR_FEET;
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
	public boolean conflictsWith(Enchantment enchantment) {
		return enchantment == DEPTH_STRIDER;
	}

	@Override
	public boolean canEnchantItem(ItemStack paramItemStack) {
		return true;
	}

}
