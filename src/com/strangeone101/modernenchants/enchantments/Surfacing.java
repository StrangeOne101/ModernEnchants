package com.strangeone101.modernenchants.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.config.StandardConfig;
import com.strangeone101.modernenchants.nms.Rarity;

public class Surfacing extends ModernEnchantment {

	public Surfacing(int id) {
		super(id, "surfacing");
	}

	@Override
	public boolean canEnchantItem(ItemStack stack) {
		return true;
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ARMOR_HEAD;
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String getName() {
		return StandardConfig.config.getString("Surfacing.Name");
		//return "Bubble";
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

	@Override
	public Rarity getRarity() {
		return Rarity.RARE;
	}

}
