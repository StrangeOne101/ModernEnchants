package com.strangeone101.modernenchants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.strangeone101.modernenchants.config.StandardConfig;
import com.strangeone101.modernenchants.nms.Rarity;

public abstract class ModernEnchantment extends Enchantment {
	
	public static final String ENCH_PREFIX = "§k§l§X§Y§r"; //X and Y are the enchantment ID but in hex
	public static final String ENCH_PREFIX_REGEX = "§k§l§(?i)([0-9]|[A-F])§([0-9]|[A-F])§r";
	
	private String keyName;
	
	/***
	 * Create a new ModernEnchantment
	 * 
	 * @param id The enchantment ID
	 * @param key_name The name that the enchantment should be defined 
	 * as (not shown). Should be all lowercase with no spaces, only
	 * underscores.
	 */
	public ModernEnchantment(int id, String key_name) {
		super(id);
		
		this.keyName = key_name;
		
		if (this instanceof Listener) {
			Bukkit.getPluginManager().registerEvents((Listener) this, ModernEnchants.PLUGIN);
		}
	}
	
	/***
	 * Checks whether this item SHOULD have enchantments on them.
	 * 
	 * @param stack The itemstack to check
	 * @return Whether the item has the enchantments in the lore
	 */
	public static boolean hasEnchantments(ItemStack stack) {
		if (!stack.hasItemMeta()) return false;
		if (!stack.getItemMeta().hasLore()) return false;
		
		for (String s : stack.getItemMeta().getLore()) {
			if (Pattern.compile(ENCH_PREFIX_REGEX).matcher(s).find()) {
				return true;
			}
		}
		return false;		
	}
	
	/***
	 * Gets all the enchantments off the item from the lore. Will return 
	 * <code>null</code> if the stack provided has none. Use {@link hasEnchantments}
	 * first!
	 * 
	 * @param stack The ItemStack
	 * @return The list of enchantments, or null if invalid itemstack
	 */
	public static List<ModernEnchantment> getEnchantments(ItemStack stack) {
		if (!stack.hasItemMeta()) return null;
		if (!stack.getItemMeta().hasLore()) return null;
		
		List<ModernEnchantment> enchs = new ArrayList<ModernEnchantment>();
		
		for (String s : stack.getItemMeta().getLore()) {
			if (Pattern.compile(ENCH_PREFIX_REGEX).matcher(s).find()) { //If the line is one with an enchantment
				int id = Integer.valueOf("" + s.charAt(5) + "" + s.charAt(7), 16);
				
				if (ModernEnchantments.enchantments.containsKey(id)) {
					enchs.add(ModernEnchantments.enchantments.get(id));
				}
			}
		}
		
		return enchs;
	}
	
	/***
	 * Adds the enchantments in the lore for a freshly enchanted item
	 * 
	 * @param itemstack The itemstack being updated
	 * @return The new lore
	 */
	@SuppressWarnings("deprecation")
	public static ItemStack addEnchantmentLore(ItemStack itemstack) {
		List<String> newlore = new ArrayList<String>();
		
		for (Enchantment e : itemstack.getEnchantments().keySet()) {
			if (ModernEnchantments.enchantments.containsKey(e)) {
				String line = ENCH_PREFIX;
				line = line.replace('X', toHex(e.getId()).charAt(0)).replace('Y', toHex(e.getId()).charAt(1));
				
				line = line + (e.isCursed() ? ChatColor.RED : ChatColor.GRAY) + e.getName();
				
				int level = itemstack.getEnchantmentLevel(e);
				
				if (!getLevelString(level).equals("")) {
					line = line + " " + getLevelString(level);
				}
				
				newlore.add(line);
			}
		}
		
		if (itemstack.hasItemMeta() && itemstack.getItemMeta().hasLore()) {
			newlore.addAll(itemstack.getItemMeta().getLore());
		}
		
		ItemMeta meta = itemstack.getItemMeta();
		meta.setLore(newlore);
		itemstack.setItemMeta(meta);
		
		return itemstack;
	}
	
	
	/***
	 * Updates the item's lore with the latest enchantment data (since
	 * it can be changed in the config)
	 * 
	 * @param itemstack The itemstack being updated
	 * @return The new lore
	 */
	@SuppressWarnings("deprecation")
	public static ItemStack updateEnchantments(ItemStack itemstack) {
		List<String> newlore = new ArrayList<String>();
		
		for (Enchantment e : itemstack.getEnchantments().keySet()) {
			if (ModernEnchantments.enchantments.containsKey(e)) {
				String line = ENCH_PREFIX;
				line = line.replace('X', toHex(e.getId()).charAt(0)).replace('Y', toHex(e.getId()).charAt(1));
				
				line = line + (e.isCursed() ? ChatColor.RED : ChatColor.GRAY) + e.getName();
				
				int level = itemstack.getEnchantmentLevel(e);
				
				if (!getLevelString(level).equals("")) {
					line = line + " " + getLevelString(level);
				}
				
				newlore.add(line);
			}
		}
		
		if (itemstack.hasItemMeta() && itemstack.getItemMeta().hasLore()) {
			newlore.addAll(itemstack.getItemMeta().getLore());
		}
		
		ItemMeta meta = itemstack.getItemMeta();
		meta.setLore(newlore);
		itemstack.setItemMeta(meta);
		
		return itemstack;
	}
	
	/***
	 * Converts a number to hex
	 * 
	 * @param number The number
	 * @return The string of characters in hex. E.g. A7
	 */
	public static String toHex(int number) {
		return String.format("%02X ", number);
	}
	
	public static String getLevelString(int number) {
		if (number <= 0) return "";
		if (StandardConfig.config.contains("EnchantLevelNumber." + number)) {
			return StandardConfig.config.getString("EnchantLevelNumber." + number);
		}
		return number + "";
	}
	
	/***
	 * Gets the keyname. Used when defining the MinecraftKey object
	 * @return The Keyname
	 */
	public String getKeyName() {
		return keyName;
	}

	public abstract Rarity getRarity();
}
