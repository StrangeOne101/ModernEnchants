package com.strangeone101.modernenchants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;

public class EnchantmentAliases {
	
	private static Map<Enchantment, List<String>> names = new HashMap<Enchantment, List<String>>();
	
	static {
		names.put(Enchantment.ARROW_DAMAGE, Arrays.asList(new String[] {"Power", "arrow_damage"}));
		names.put(Enchantment.ARROW_FIRE, Arrays.asList(new String[] {"Flame", "arrow_fire"}));
		names.put(Enchantment.ARROW_INFINITE, Arrays.asList(new String[] {"Infinity", "arrow_damage"}));
		names.put(Enchantment.ARROW_KNOCKBACK, Arrays.asList(new String[] {"Punch", "arrow_knockback"}));
		names.put(Enchantment.BINDING_CURSE, Arrays.asList(new String[] {"CurseOfBinding", "binding", "curse_of_binding"}));
		names.put(Enchantment.DAMAGE_ALL, Arrays.asList(new String[] {"Sharpness", "damage_all", "damage"}));
		names.put(Enchantment.DAMAGE_ARTHROPODS, Arrays.asList(new String[] {"BaneOfArthopods", "damage_arthropods", "bane_of_arthropods"}));
		names.put(Enchantment.DAMAGE_UNDEAD, Arrays.asList(new String[] {"Smite", "damage_undead"}));
		names.put(Enchantment.DEPTH_STRIDER, Arrays.asList(new String[] {"DepthStrider", "depth_strider", "fastswim"}));
		names.put(Enchantment.DIG_SPEED, Arrays.asList(new String[] {"Effeciency", "dig_speed", "fastswim"}));
		
		
		
		
	}
	
	public static Enchantment getEnchantment(String name) {
		for (Enchantment ench : names.keySet()) {
			List<String> list = names.get(ench);
			for (String enchName : list) {
				if (enchName.equalsIgnoreCase(name)) {
					return ench;
				}
			}
		}
		return null;
	}

}
