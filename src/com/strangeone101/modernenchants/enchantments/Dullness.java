package com.strangeone101.modernenchants.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.nms.Rarity;

public class Dullness extends ModernEnchantment {

	public Dullness(int id) {
		super(id, "dullness");
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		return true;
	}

	@Override
	public boolean conflictsWith(Enchantment ench) {
		return ench == Enchantment.DAMAGE_ALL || ench == Enchantment.DAMAGE_UNDEAD || ench == Enchantment.DAMAGE_ARTHROPODS;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Dullness";
	}

	@Override
	public int getStartLevel() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean isCursed() {
		return true;
	}

	@Override
	public boolean isTreasure() {
		return false;
	}

	@Override
	public Rarity getRarity() {
		return Rarity.VERY_RARE;
	}

}
