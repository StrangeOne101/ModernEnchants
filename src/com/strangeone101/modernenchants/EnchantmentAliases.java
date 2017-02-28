package com.strangeone101.modernenchants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class EnchantmentAliases {
	
	private static Map<Enchantment, List<String>> names = new HashMap<Enchantment, List<String>>();
	
	static {
		names.put(Enchantment.ARROW_DAMAGE, Arrays.asList(new String[] {"Power", "arrow_damage"}));
		names.put(Enchantment.ARROW_FIRE, Arrays.asList(new String[] {"Flame", "arrow_fire"}));
		names.put(Enchantment.ARROW_INFINITE, Arrays.asList(new String[] {"Infinity", "arrow_damage"}));
		names.put(Enchantment.ARROW_KNOCKBACK, Arrays.asList(new String[] {"Punch", "arrow_knockback"}));
		names.put(Enchantment.BINDING_CURSE, Arrays.asList(new String[] {"CurseOfBinding", "binding", "curse_of_binding", "cob"}));
		names.put(Enchantment.DAMAGE_ALL, Arrays.asList(new String[] {"Sharpness", "damage_all", "damage"}));
		names.put(Enchantment.DAMAGE_ARTHROPODS, Arrays.asList(new String[] {"BaneOfArthopods", "damage_arthropods", "bane_of_arthropods"}));
		names.put(Enchantment.DAMAGE_UNDEAD, Arrays.asList(new String[] {"Smite", "damage_undead"}));
		names.put(Enchantment.DEPTH_STRIDER, Arrays.asList(new String[] {"DepthStrider", "depth_strider", "fastswim"}));
		names.put(Enchantment.DIG_SPEED, Arrays.asList(new String[] {"Effeciency", "dig_speed", "speed"}));
		names.put(Enchantment.DURABILITY, Arrays.asList(new String[] {"Unbreaking", "durability"}));
		names.put(Enchantment.FIRE_ASPECT, Arrays.asList(new String[] {"FireAspect", "fire_aspect", "burning"}));
		names.put(Enchantment.FROST_WALKER, Arrays.asList(new String[] {"FrostWalker", "frost_walker", "water_walking"}));
		names.put(Enchantment.KNOCKBACK, Arrays.asList(new String[] {"Knockback"}));
		names.put(Enchantment.LOOT_BONUS_BLOCKS, Arrays.asList(new String[] {"Fortune", "loot_bonus_blocks"}));
		names.put(Enchantment.LOOT_BONUS_MOBS, Arrays.asList(new String[] {"Looting", "loot_bonus_mobs"}));
		names.put(Enchantment.LUCK, Arrays.asList(new String[] {"Luck"}));
		names.put(Enchantment.LURE, Arrays.asList(new String[] {"Lure"}));
		names.put(Enchantment.MENDING, Arrays.asList(new String[] {"Mending", "repair"}));
		names.put(Enchantment.OXYGEN, Arrays.asList(new String[] {"Respiration", "oxygen"}));
		names.put(Enchantment.PROTECTION_ENVIRONMENTAL, Arrays.asList(new String[] {"Protection", "protecting", "damageresistance", "damage_resistance"}));
		names.put(Enchantment.PROTECTION_EXPLOSIONS, Arrays.asList(new String[] {"BlastProtection", "blast_protection", "blastresistance", "blast_resistance"}));
		names.put(Enchantment.PROTECTION_FALL, Arrays.asList(new String[] {"FeatherFalling", "feather_falling", "fallresistance", "fall_resistance", "fall", "falling"}));
		names.put(Enchantment.PROTECTION_FIRE, Arrays.asList(new String[] {"FireProtection", "fire_protection", "fireresistance", "fire_resistance"}));
		names.put(Enchantment.PROTECTION_PROJECTILE, Arrays.asList(new String[] {"ProjectileProtection", "projectile_protection", "arrowresistance", "arrow_resistance"}));
		names.put(Enchantment.SILK_TOUCH, Arrays.asList(new String[] {"SilkTouch", "silktouch"}));
		names.put(Enchantment.SWEEPING_EDGE, Arrays.asList(new String[] {"SweepingEdge", "sweeping_edge", "swordsweeping", "sword_sweeping", "sweeping"}));
		names.put(Enchantment.THORNS, Arrays.asList(new String[] {"Thorns", "damagereflection", "reflection", "cactus"}));
		names.put(Enchantment.VANISHING_CURSE, Arrays.asList(new String[] {"CurseOfVanishing", "curse_of_vanishing", "vanishing", "cov"}));
		
		names.put(ModernEnchantments.CURSE_OF_DECAY, Arrays.asList(new String[] {"CurseOfDecay", "curse_of_decay", "decay", "cod"}));
		names.put(ModernEnchantments.DAMAGE_UNNATURAL, Arrays.asList(new String[] {"WrathOfUnnatural", "wrath_of_unnatural", "wou", "unnatural", "wrath"}));
		names.put(ModernEnchantments.DECAPITATION, Arrays.asList(new String[] {"Decapitation", "beheading"}));
		names.put(ModernEnchantments.MULTISHOT, Arrays.asList(new String[] {"Multishot"}));
		names.put(ModernEnchantments.PIERCE, Arrays.asList(new String[] {"Piercing", "pierce"}));
		names.put(ModernEnchantments.PROTECTION_MAGIC, Arrays.asList(new String[] {"MagicProtection", "magic_protection", "magicalprotection", "potionprotection", "magical_protection", "potion_protection"}));
		names.put(ModernEnchantments.ROSETTA_STONE, Arrays.asList(new String[] {"RosettaStone", "rosetta_stone", "exp", "xp", "rosetta"}));
		names.put(ModernEnchantments.SLASHING, Arrays.asList(new String[] {"Slashing", "fast_swing", "swinging", "fastswing"}));
		names.put(ModernEnchantments.SOULBOUND, Arrays.asList(new String[] {"Soulbound", "soul"}));
		names.put(ModernEnchantments.SURFACING, Arrays.asList(new String[] {"Surfacing", "bubble"}));
		names.put(ModernEnchantments.WALK_SPEED, Arrays.asList(new String[] {"Dashing", "walk_speed", "walkspeed", "running", "rosetta"}));		
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
	
	/**
	 * Gets a list of enchantment names that can be enchanted on
	 * the provided ItemStack. 
	 * 
	 * @param stack The ItemStack
	 * @param force Set to true to ignore the canEnchant(itemstack)
	 * method
	 * @return A list of enchantment names
	 */
	public static List<String> getEnchantments(ItemStack stack, boolean force) {
		if (stack == null) return new ArrayList<String>();
		
		List<String> enchantmentNames = new ArrayList<String>();
		for (Enchantment ench : names.keySet()) {
			if (ench == null) continue;
			if (ench.canEnchantItem(stack) || force) {
				enchantmentNames.add(names.get(ench).get(0)); //Gets the first name
			}
		}
		
		Collections.sort(enchantmentNames);
		
		return enchantmentNames;
	}

}
