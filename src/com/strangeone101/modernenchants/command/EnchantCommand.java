package com.strangeone101.modernenchants.command;

import java.util.Arrays;

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

import com.strangeone101.modernenchants.EnchantmentAliases;
import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.util.GenericUtil;

public class EnchantCommand implements CommandExecutor {

	public EnchantCommand() {
		PluginCommand command = Bukkit.getPluginCommand("enchant");
		command.setAliases(Arrays.asList(new String[] {"forceenchant"}));
		command.setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can run this command!");
			return true;
		}
		
		boolean force = name.equalsIgnoreCase("forceenchant");
		
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Usage: /enchant <enchantment> [level]");
			sender.sendMessage(ChatColor.RED + "Use /enchant list to see all avaliable enchantments for the current item you are holding");
			return true;
		}
		
		Enchantment ench = Enchantment.getByName(args[0].toLowerCase());
		if (ench == null) {
			ench = EnchantmentAliases.getEnchantment(args[0]);
		}
		
		if (ench == null) {
			sender.sendMessage(ChatColor.RED + "Error: Enchantment not found!");
			sender.sendMessage(ChatColor.RED + "Use /enchant list to see all avaliable enchantments for the current item you are holding");
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
		
		if (!ench.canEnchantItem(stack) && !force) {
			sender.sendMessage(ChatColor.RED + "Error: Cannot enchant that type of item! Use /forceenchant if you want to surpass this!");
			return true;
		}
		
		stack.addEnchantment(ench, level);
		ModernEnchantment.updateEnchantments(stack);
		
		sender.sendMessage(ChatColor.GREEN + "Enchantment successful.");
		
		return true;
	}

}
