package com.strangeone101.modernenchants;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;

import com.strangeone101.modernenchants.enchantments.Surfacing;
import com.strangeone101.modernenchants.enchantments.UnnaturalWrath;
import com.strangeone101.modernenchants.enchantments.Dashing;
import com.strangeone101.modernenchants.enchantments.Decapitation;
import com.strangeone101.modernenchants.enchantments.Decay;
import com.strangeone101.modernenchants.enchantments.MagicProtection;
import com.strangeone101.modernenchants.enchantments.Multishot;
import com.strangeone101.modernenchants.enchantments.Pierce;
import com.strangeone101.modernenchants.enchantments.RosettaStone;
import com.strangeone101.modernenchants.enchantments.Slashing;
import com.strangeone101.modernenchants.enchantments.Soulbound;
import com.strangeone101.modernenchants.nms.MinecraftKey;
import com.strangeone101.modernenchants.nms.NMSVanillaEnchantment;
import com.strangeone101.modernenchants.util.ReflectionUtils;
import com.strangeone101.modernenchants.util.ReflectionUtils.PackageType;

public class ModernEnchantments {
	
	public static Map<Integer, ModernEnchantment> enchantments = new HashMap<Integer, ModernEnchantment>();
	
	public static ModernEnchantment SURFACING;
	public static ModernEnchantment PROTECTION_MAGIC;
	public static ModernEnchantment PIERCE;
	public static ModernEnchantment CURSE_OF_DECAY;
	public static ModernEnchantment SLASHING;
	public static ModernEnchantment ROSETTA_STONE;
	public static ModernEnchantment DECAPITATION;
	public static ModernEnchantment WALK_SPEED;
	public static ModernEnchantment MULTISHOT;
	public static ModernEnchantment DAMAGE_UNNATURAL;
	public static ModernEnchantment SOULBOUND;
	
	private static boolean registered = false;
	
	
	/***
	 * Registers all the enchantments of the plugin
	 * 
	 * @return If the registrations were successful
	 */
	public static boolean register() {
		if (registered) return false;
		
		ModernEnchantments.SURFACING = register(Surfacing.class, 140);
		ModernEnchantments.PIERCE = register(Pierce.class, 141);
		ModernEnchantments.DECAPITATION = register(Decapitation.class, 142);
		ModernEnchantments.PROTECTION_MAGIC = register(MagicProtection.class, 143);
		ModernEnchantments.CURSE_OF_DECAY = register(Decay.class, 144);
		ModernEnchantments.SLASHING = register(Slashing.class, 145);
		ModernEnchantments.ROSETTA_STONE = register(RosettaStone.class, 146);
		ModernEnchantments.WALK_SPEED = register(Dashing.class, 147);
		ModernEnchantments.MULTISHOT = register(Multishot.class, 148);
		ModernEnchantments.DAMAGE_UNNATURAL = register(UnnaturalWrath.class, 149);
		ModernEnchantments.SOULBOUND = register(Soulbound.class, 150);
		
		
		registered = true;
		
		return true;
		
	}
	
	protected static ModernEnchantment register(Class<? extends ModernEnchantment> enchant, int id) {
		try {
			Constructor<? extends ModernEnchantment> constr = enchant.getConstructor(Integer.TYPE);
			
			ModernEnchantment ench = constr.newInstance(id);
			
			try {
				Enchantment.registerEnchantment(ench);
				
				enchantments.put(id, ench);
				
				try {
					Field field = ReflectionUtils.getField("Enchantment", PackageType.MINECRAFT_SERVER, true, "enchantments");
					Object minecraftKey = MinecraftKey.getKey(ench);
					Class<?> nmsEnchantmentClass = field.getDeclaringClass();
					Method register = ReflectionUtils.getMethod(field.getType(), "a", Integer.TYPE, minecraftKey.getClass(), nmsEnchantmentClass);
					register.invoke(field.get(null), id, minecraftKey, NMSVanillaEnchantment.getVanillaEnchantment(ench));
				
				} catch (Exception e) {
					ModernEnchants.PLUGIN.getLogger().severe("Failed to register enchantment in vanilla!");
					e.printStackTrace();
				}
				
				return ench;
			} catch (IllegalArgumentException e){
				ModernEnchants.PLUGIN.getLogger().warning("Failed to create new enchantment " + enchant.getName() + " with ID " + id + "! ID Already taken!");
				
				return null;
			}
		} catch (Exception e) {
			ModernEnchants.PLUGIN.getLogger().severe("Failed to create new enchantment for " + enchant.getName());
			e.printStackTrace();
		} 

		return null;
	}

}
