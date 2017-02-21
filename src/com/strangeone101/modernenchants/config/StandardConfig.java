package com.strangeone101.modernenchants.config;

public class StandardConfig extends Config {

	public static Config config;
	
	public StandardConfig() {
		super("config.yml");
		
		config = this;
	}

}
