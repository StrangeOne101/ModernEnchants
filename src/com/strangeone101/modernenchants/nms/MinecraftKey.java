package com.strangeone101.modernenchants.nms;

import java.lang.reflect.Constructor;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.util.ReflectionUtils;
import com.strangeone101.modernenchants.util.ReflectionUtils.PackageType;

public class MinecraftKey {
	
	/**
	 * Generates a NMS MinecraftKey object for the vanilla enchantments
	 * to use. Means people can tab with /enchant and see "modern_enchants:
	 * enchantment_name"
	 * 
	 * @param enchantmentName The name of the enchantment
	 * @return The NMS MinecraftKey object
	 */
	public static Object getKey(ModernEnchantment enchantment) {
		try {
			Constructor<?> constr = ReflectionUtils.getConstructor("MinecraftKey", PackageType.MINECRAFT_SERVER, String.class, String.class);
			
			return constr.newInstance("modern_enchants", enchantment.getKeyName());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
