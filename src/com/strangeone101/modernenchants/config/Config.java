package com.strangeone101.modernenchants.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.strangeone101.modernenchants.ModernEnchants;

public class Config extends YamlConfiguration {
	
	public Config(String path) {
		File file = new File(ModernEnchants.PLUGIN.getDataFolder(), path);
		if (!file.exists()) {
			try {
				Files.copy(ModernEnchants.PLUGIN.getResource(path), file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			this.load(file);
		} catch (FileNotFoundException e) {
			ModernEnchants.PLUGIN.getLogger().severe("Failed to load config: File not found!");
			e.printStackTrace();
		} catch (IOException e) {
			ModernEnchants.PLUGIN.getLogger().severe("Failed to load config!");
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			ModernEnchants.PLUGIN.getLogger().severe("Failed to load config: Invalid config!");
			e.printStackTrace();
		}
	}
}
