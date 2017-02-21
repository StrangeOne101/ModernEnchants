package com.strangeone101.modernenchants.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.strangeone101.modernenchants.ModernEnchantment;
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
		return "Slashing";
	}

	@Override
	public int getMaxLevel() {
		return 4;
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
	public boolean canEnchantItem(ItemStack paramItemStack) {
		return true;
	}

}
