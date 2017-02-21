package com.strangeone101.modernenchants.nms;

import org.bukkit.enchantments.EnchantmentTarget;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.util.ReflectionUtils;
import com.strangeone101.modernenchants.util.ReflectionUtils.PackageType;

public class ItemType {
	
	/***
	 * Returns the NMS object that vanilla enchantments need to define
	 * what type of item the enchantment is
	 * @param enchantment The enchantment =
	 * @return The NMS slot type object
	 */
	public static Object getSlot(ModernEnchantment enchantment) {
		String type = "BREAKABLE";
		
		if (enchantment.getItemTarget() == EnchantmentTarget.TOOL) type = "DIGGER";
		else if (enchantment.getItemTarget() == EnchantmentTarget.ARMOR_TORSO) type = "ARMOR_CHEST";
		else type = enchantment.getItemTarget().name();
		
		try {
			return ReflectionUtils.getField("EnchantmentSlotType", PackageType.MINECRAFT_SERVER, true, type).get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
