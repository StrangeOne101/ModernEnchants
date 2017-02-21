package com.strangeone101.modernenchants.nms;

import org.bukkit.enchantments.EnchantmentTarget;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.util.ReflectionUtils;
import com.strangeone101.modernenchants.util.ReflectionUtils.PackageType;

public class SlotType {
	
	public static Object[] getSlot(ModernEnchantment enchantment) {
		String type = "MAINHAND";
		
		if (enchantment.getItemTarget() == EnchantmentTarget.ARMOR) type = "HEAD,CHEST,LEGS,FEET";
		else if (enchantment.getItemTarget() == EnchantmentTarget.ARMOR_FEET) type = "FEET";
		else if (enchantment.getItemTarget() == EnchantmentTarget.ARMOR_LEGS) type = "LEGS";
		else if (enchantment.getItemTarget() == EnchantmentTarget.ARMOR_TORSO) type = "CHEST";
		else if (enchantment.getItemTarget() == EnchantmentTarget.ARMOR_HEAD) type = "HEAD";
		
		Object[] types = new Object[type.split(",").length];
		
		try {
			for (int i = 0; i < type.split(",").length; i++) {
				
				types[i] = ReflectionUtils.getField("EnumItemSlot", PackageType.MINECRAFT_SERVER, true, type.split(",")[i]).get(null);
			}
			
			return types;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Object[0];
	}

}
