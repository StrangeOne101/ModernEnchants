package com.strangeone101.modernenchants.command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.ModernEnchantments;
import com.strangeone101.modernenchants.ModernEnchants;
import com.strangeone101.modernenchants.Permissions;

public class ModernEnchantsCommand implements CommandExecutor {

	public ModernEnchantsCommand() {
		
		PluginCommand command = Bukkit.getPluginCommand("modernenchants");
		command.setAliases(Arrays.asList(new String[] {"mec", "modernenchant"}));
		command.setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		if (args.length == 0 || args[0].equalsIgnoreCase("info")) {
			if (!sender.hasPermission(Permissions.MEC_COMMAND_INFO)) {
				sender.sendMessage(ChatColor.RED + "You don't have permission to run this command!");
				return true;
			}
			
			sender.sendMessage(ChatColor.YELLOW + "===== " + ChatColor.BLUE + ChatColor.BOLD + " ModernEnchants " + ChatColor.RESET + ChatColor.YELLOW + " =====");
			sender.sendMessage(ChatColor.BLUE + "" + ChatColor.ITALIC + "Custom enchantments at their finest.");
			sender.sendMessage(ChatColor.YELLOW + "Version: " + ChatColor.BLUE + ModernEnchants.PLUGIN.getDescription().getVersion());
			sender.sendMessage(ChatColor.YELLOW + "There are " + ChatColor.BLUE + ModernEnchantments.enchantments.size() + ChatColor.YELLOW + " custom enchantments loaded.");
			sender.sendMessage(ChatColor.YELLOW + "Created by " + ChatColor.BLUE + "StrangeOne101");
		} else if (args[0].equalsIgnoreCase("reload")) {
			if (!sender.hasPermission(Permissions.MEC_COMMAND_RELOAD)) {
				sender.sendMessage(ChatColor.RED + "You don't have permission to run this command!");
				return true;
			}
			
			sender.sendMessage(ChatColor.YELLOW + "Command comming soon!");
		} else if (args[0].equalsIgnoreCase("help")) {
			sender.sendMessage(ChatColor.YELLOW + "===== " + ChatColor.BLUE + ChatColor.BOLD + " ModernEnchants " + ChatColor.RESET + ChatColor.YELLOW + " =====");
			sender.sendMessage(ChatColor.BLUE + "/mec reload " + ChatColor.YELLOW + "- Reloads the plugin");
			sender.sendMessage(ChatColor.BLUE + "/mec info " + ChatColor.YELLOW + "- Displays the version, author, and the amount of custom enchantments loaded");
			sender.sendMessage(ChatColor.BLUE + "/enchant " + ChatColor.YELLOW + "- Enchant an item");
			sender.sendMessage(ChatColor.BLUE + "/forceenchant " + ChatColor.YELLOW + "- Enchant an item (but without limits)");
			sender.sendMessage(ChatColor.BLUE + "/disenchant " + ChatColor.YELLOW + "- Remove enchantments from items");
			sender.sendMessage(ChatColor.BLUE + "/enchantment " + ChatColor.YELLOW + "- List or find out more information about enchantments");
		} else if (args[0].equalsIgnoreCase("debug")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Oi, u can't use the console, silly.");
				return true;
			}
			
			Player player = (Player) sender;
			
			if (args.length == 1 || (!args[1].equalsIgnoreCase("has") && !args[1].equalsIgnoreCase("update") && !args[1].equalsIgnoreCase("update2"))) {
				sender.sendMessage(ChatColor.RED + "Args are: has, update, update2");
			} else if (args[1].equalsIgnoreCase("has")) {
				sender.sendMessage(ChatColor.BLUE + "Has custom enchants: " + ModernEnchantment.hasEnchantments(player.getInventory().getItemInMainHand()));
			} else if (args[1].equalsIgnoreCase("update")) {
				ModernEnchantment.updateEnchantments(player.getInventory().getItemInMainHand());
				sender.sendMessage(ChatColor.BLUE + "Updated " + player.getInventory().getItemInMainHand().toString());
			} else if (args[1].equalsIgnoreCase("update2")) {
				player.getInventory().setItemInMainHand(ModernEnchantment.updateEnchantments(player.getInventory().getItemInMainHand()));
				sender.sendMessage(ChatColor.BLUE + "Updated (2nd method)" + player.getInventory().getItemInMainHand().toString());
			}
		}
		
		return true;
	}

}
