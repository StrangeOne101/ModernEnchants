package com.strangeone101.modernenchants.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.nms.Rarity;

public class UnnaturalWrath extends ModernEnchantment {

	public UnnaturalWrath(int id) {
		super(id, "damage_unnatural");
	}

	@Override
	public Rarity getRarity() {
		return Rarity.UNCOMMON;
	}

	@Override
	public String getName() {
		return "Wrath of Unnatural";
	}

	@Override
	public int getMaxLevel() {
		return 5;
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
	public boolean conflictsWith(Enchantment enchantment) {
		return enchantment == DAMAGE_ALL || enchantment == DAMAGE_ARTHROPODS || enchantment == DAMAGE_UNDEAD;
	}

	@Override
	public boolean canEnchantItem(ItemStack paramItemStack) {
		return true;
	}

}
