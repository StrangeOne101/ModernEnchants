package com.strangeone101.modernenchants.nms;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public abstract class NMSAttributeManager {
	
	private static NMSAttributeManager manager;
	
	static {
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];
		if (version.equals("v1_11_R1")) {
			manager = new AttributeManager_11RC1();
		}
	}
	
	
	public enum AttributeType {
		
		HEALTH("generic.maxHealth"),
		ATTACK_SPEED("generic.attackSpeed"),
		ATTACK_DAMAGE("generic.attackDamage"),
		ARMOR("generic.armor"),
		ARMOR_TOUGHNESS("generic.armorToughness"),
		LUCK("generic.luck"),
		SPEED("generic.movementSpeed");
		
		private String attributeName;
		
		AttributeType(String name) {
			this.attributeName = name;
		}
		
		public String getAttributeName() {
			return attributeName;
		}
	}
	
	public enum AttributeOperation {
		ADDITION, MULTIPLY;
	}
	
	public static void setAttribute(ItemStack stack, AttributeType attr, AttributeOperation operation, double amount) {
		manager.set(stack, attr, operation, amount);
	}
	
	public abstract void set(ItemStack stack, AttributeType attr, AttributeOperation operation, double amount);

}
