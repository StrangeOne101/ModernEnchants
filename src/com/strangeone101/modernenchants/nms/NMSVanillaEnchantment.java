package com.strangeone101.modernenchants.nms;

import org.bukkit.Bukkit;

import com.strangeone101.modernenchants.ModernEnchantment;

public abstract class NMSVanillaEnchantment {

	public abstract Object generate(ModernEnchantment enchant);
	
	public static Object getVanillaEnchantment(ModernEnchantment enchant) {
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];
		if (version.equals("v1_11_R1")) {
			return new VanillaEnchantment_11RC1().generate(enchant);
		}
		
		return null;
	}
}
