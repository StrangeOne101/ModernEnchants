package com.strangeone101.modernenchants.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.config.StandardConfig;
import com.strangeone101.modernenchants.nms.Rarity;

public class MagicProtection extends ModernEnchantment {

	public MagicProtection(int id) {
		super(id, "magic_protection");
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
	public boolean conflictsWith(Enchantment ench) {
		return ench == Enchantment.PROTECTION_ENVIRONMENTAL || 
				ench == Enchantment.PROTECTION_EXPLOSIONS || 
				ench == Enchantment.PROTECTION_FIRE ||
				ench == Enchantment.PROTECTION_PROJECTILE;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ARMOR;
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}

	@Override
	public String getName() {
		return StandardConfig.config.getString("MagicProt.Name");
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
