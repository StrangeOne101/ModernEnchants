package com.strangeone101.modernenchants.nms;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import com.strangeone101.modernenchants.util.ReflectionUtils;
import com.strangeone101.modernenchants.util.ReflectionUtils.PackageType;

public abstract class NMSAttributeManager {
	
	public enum AttributeType {
		
		HEALTH("generic.maxHealth"),
		ATTACK_SPEED("generic.attackSpeed"),
		ATTACK_DAMAGE("generic.attackDamage"),
		ARMOR("generic.armor"),
		SPEED("generic.movementSpeed"),
		ARMOR_TOUGHNESS("generic.armorToughness"),
		
		LUCK("generic.luck");
		
		private String attributeName;
		
		AttributeType(String name) {
			this.attributeName = name;
		}
		
		public String getAttributeName() {
			return attributeName;
		}
	}
	
	public static void setAttribute(ItemStack stack, String attrName, Object object) {
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];
		if (version.equals("v1_11_R1")) {
			
		}
	}
	
	public abstract void set(ItemStack stack, String attrName, Object obj);

}
