package com.strangeone101.modernenchants.event;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public class CustomEvent<T extends Event> {
	
	protected T event;
	private ItemStack stack;
	private Enchantment enchantment;
	private int level;
	
	public CustomEvent(T event, ItemStack stack, Enchantment ench, int level) {
		this.event = event;
		this.stack = stack;
		this.enchantment = ench;
		this.level = level;
	}
	
	public ItemStack getItem() {
		return stack;
	}
	
	public Enchantment getEnchantment() {
		return enchantment;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setCancelled(boolean bool) {
		try {
			if (event.getClass().getMethod("setCancelled", Boolean.TYPE) != null) {
				event.getClass().getMethod("setCancelled", Boolean.TYPE).invoke(event, bool);
			}
		} catch (NoSuchMethodException e) {
			//Do nothing. This may happen.
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
