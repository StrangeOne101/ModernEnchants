package com.strangeone101.modernenchants;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;


public class ModernEnchants extends JavaPlugin {
	
	public static ModernEnchants PLUGIN;
	
	private static boolean loaded = false;
	
	@SuppressWarnings("unchecked")
	@Override 
	public void onEnable() {
		super.onEnable();
	
		PLUGIN = this;
		
		Bukkit.getPluginManager().registerEvents(new ModernEnchantsListener(), this);
	
		/**Open up the field*/
		try {
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			@SuppressWarnings("deprecation")
			Field f2 = org.bukkit.command.defaults.EnchantCommand.class.getDeclaredField("ENCHANTMENT_NAMES");
			
			f.setAccessible(true);
			f2.setAccessible(true);
			
			f.set(null, true);
			((List<String>)f2.get(null)).clear();
			
			ModernEnchantments.register();
			
			Enchantment.stopAcceptingRegistrations(); //Rebuilds the command
			
			f.setAccessible(false);
			f2.setAccessible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.getLogger().info("ModernEnchants " + getDescription().getVersion() + " enabled with " + (ModernEnchantments.enchantments.size()) + " enchantments registered!");
		
		loaded = true;
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		
		//This unregisters the enchantments to prevent conflicts on /reload
		
		/*try {
			Field byIdField = Enchantment.class.getDeclaredField("byId");
			Field byNameField = Enchantment.class.getDeclaredField("byName");
			 
			byIdField.setAccessible(true);
			byNameField.setAccessible(true);
			 
			@SuppressWarnings("unchecked")
			HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdField.get(null);
			@SuppressWarnings("unchecked")
			HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) byNameField.get(null);
			 
			for (int id : ModernEnchantments.enchantments.keySet()) {
				if (byId.containsKey(id)) {
					byId.remove(id); 
				}
				
				if (byName.containsKey(ModernEnchantments.enchantments.get(id).getName())) {
					byName.remove(getName());
				}
			}
			
		} catch (Exception ignored) { }*/
	}
	
	@Deprecated
	public static String getIVX(int num) {
		TreeMap<Integer, String> romanNumerals = new TreeMap<Integer, String>();
		romanNumerals.put(10, "X");
		romanNumerals.put(9, "IX");
		romanNumerals.put(5, "V");
		romanNumerals.put(4, "IV");
		romanNumerals.put(1, "I");

		int l =  romanNumerals.floorKey(num);
	    if ( num == l ) {
	        return romanNumerals.get(num);
	    }
	    return romanNumerals.get(l) + getIVX(num - l);
	}

}
