package com.strangeone101.modernenchants.event;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class ItemKillEntityEvent extends CustomEvent<EntityDeathEvent> {

	private LivingEntity entity;
	
	public ItemKillEntityEvent(EntityDeathEvent event, ItemStack stack, Enchantment ench, int level) {
		super(event, stack, ench, level);
		
		this.entity = event.getEntity();
	}
	
	public LivingEntity getEntity() {
		return entity;
	}
	
	public EntityDeathEvent getEvent() {
		return event;
	}

}
