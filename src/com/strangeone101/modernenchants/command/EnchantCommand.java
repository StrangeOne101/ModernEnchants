package com.strangeone101.modernenchants.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import com.strangeone101.modernenchants.EnchantmentAliases;
import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.util.GenericUtil;

public class EnchantCommand implements CommandExecutor {

	public EnchantCommand() {
		PluginCommand command = Bukkit.getPluginCommand("enchant");
		command.setExecutor(this);
		PluginCommand command2 = Bukkit.getPluginCommand("forceenchant");
		command2.setExecutor(this);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can run this command!");
			return true;
		}
		
		boolean force = name.equalsIgnoreCase("forceenchant");
		String f = force ? "force" : "";
		
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Usage: /" + f + "enchant <enchantment> [level]");
			sender.sendMessage(ChatColor.RED + "Use /" + f + "enchant list to see all avaliable enchantments for the current item you are holding");
			return true;
		}
		
		if (args[0].equalsIgnoreCase("list")) {
			Player player = (Player) sender;
			ItemStack stack = player.getInventory().getItemInMainHand();
			
			List<String> enchNames = EnchantmentAliases.getEnchantments(stack, force);
			
			if (enchNames.size() == 0) {
				sender.sendMessage(ChatColor.RED + "Error: No valid enchantments found for the item provided!");
				return true;
			}
			
			String niceNames = ChatColor.GOLD + GenericUtil.makeListFancy(enchNames)
				.replaceAll(", ", ChatColor.YELLOW + ", " + ChatColor.GOLD)
				.replace(" and ", ChatColor.YELLOW + " and " + ChatColor.GOLD);
			
			String prefix = ChatColor.YELLOW + (force ? "Enchantment names: " : "Valid Enchantment names: ");
			
			sender.sendMessage(prefix + niceNames);
			return true;
		}
		
		Enchantment ench;
		
		if (GenericUtil.isInteger(args[0])) {
			ench = Enchantment.getById(Integer.parseInt(args[0]));
			if (ench == null) {
				sender.sendMessage(ChatColor.RED + "Error: Could not find enchantment with ID " + args[0] + "!");
				sender.sendMessage(ChatColor.RED + "Use /" + f + "enchant list to see all avaliable enchantments for the current item you are holding");
				sender.sendMessage(ChatColor.RED + "Or use /enchantment list to see all a list of all global enchantments");
				return true;
			}
		} else {
			ench = Enchantment.getByName(args[0].toLowerCase());
			if (ench == null) {
				ench = EnchantmentAliases.getEnchantment(args[0]);
			}
		}
		
		if (ench == null) {
			sender.sendMessage(ChatColor.RED + "Error: Enchantment not found!");
			sender.sendMessage(ChatColor.RED + "Use /" + f + "enchant list to see all avaliable enchantments for the current item you are holding");
			sender.sendMessage(ChatColor.RED + "Or use /enchantment list to see all a list of all global enchantments");
			return true;
		}
		
		int level = ench.getStartLevel();
		if (args.length > 1) {
			if (GenericUtil.isInteger(args[1])) {
				level = Integer.parseInt(args[1]);
			} else {
				sender.sendMessage(ChatColor.RED + "Level must be a positive number!");
				return true;
			}
		}
		
		if (level > ench.getMaxLevel() && !force) {
			sender.sendMessage(ChatColor.RED + "The enchantment maximum is level " + ench.getMaxLevel() + "! Use /forceenchant if you want to surpass this!");
			return true;
		}
		
		if (level < ench.getStartLevel() && !force) {
			sender.sendMessage(ChatColor.RED + "The enchantment minimum is level " + ench.getMaxLevel() + "! Use /forceenchant if you want to surpass this!");
			return true;
		}
		Player player = (Player) sender;
		
		ItemStack stack = player.getInventory().getItemInMainHand();
		
		if (stack == null || stack.getType() == Material.AIR) {
			sender.sendMessage(ChatColor.RED + "Error: How do you expect me to enchant nothing? (Hold an item)");
			return true;
		}
		
		if (!ench.canEnchantItem(stack) && !force && stack.getType() != Material.BOOK && stack.getType() != Material.ENCHANTED_BOOK) {
			sender.sendMessage(ChatColor.RED + "Error: Cannot enchant that type of item! Use /forceenchant if you want to surpass this!");
			return true;
		}
		
		if (stack.getType() == Material.BOOK) {
			ItemStack stack2 = new ItemStack(Material.ENCHANTED_BOOK, 1);
			if (stack.hasItemMeta()) stack2.setItemMeta(stack.getItemMeta());
			stack = stack2;
		}
		
		if (stack.hasItemMeta() && stack.getItemMeta() instanceof EnchantmentStorageMeta) {
			EnchantmentStorageMeta meta = (EnchantmentStorageMeta) stack.getItemMeta();
			
			if (meta.getStoredEnchants().containsKey(ench)) {
				meta.removeStoredEnchant(ench);
			}
			meta.addStoredEnchant(ench, level, true);
		} else {
			if (stack.getEnchantments().containsKey(ench)) {
				stack.removeEnchantment(ench);
			}
			stack.addUnsafeEnchantment(ench, level);
		}
		
		ModernEnchantment.updateEnchantments(stack);
		
		sender.sendMessage(ChatColor.GREEN + "Successfully enchanted with " + ench.getName());
		
		return true;
	}

}
