package com.strangeone101.modernenchants.enchantments;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.ModernEnchants;
import com.strangeone101.modernenchants.config.StandardConfig;
import com.strangeone101.modernenchants.event.ItemKillEntityEvent;
import com.strangeone101.modernenchants.nms.Rarity;

public class Decapitation extends ModernEnchantment {

	private double chance;
	private boolean witherskullenabled;
	
	public Decapitation(int id) {
		super(id, "decapitation");
		
		this.chance = Double.parseDouble(StandardConfig.config.getString("Decapitation.ChancePerLevel", "20%").replaceAll("%", "")) / 100;
		this.witherskullenabled = StandardConfig.config.getBoolean("Decapitation.OnWitherSkeletons", false);
		
		ModernEnchants.PLUGIN.getLogger().info("Decapitation chance: " + chance);
	}

	@Override
	public Rarity getRarity() {
		return Rarity.RARE;
	}

	@Override
	public String getName() {
		return StandardConfig.config.getString("Decapitation.Name", "Decapitation");
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}

	@Override
	public boolean isTreasure() {
		return false;
	}

	@Override
	public boolean isCursed() {
		return false;
	}

	@Override
	public boolean conflictsWith(Enchantment paramEnchantment) {
		return false;
	}

	@Override
	public boolean canEnchantItem(ItemStack paramItemStack) {
		return true;
	}
	
	/*@EventHandler(priority = EventPriority.LOW)
	public void onDeath(EntityDeathEvent event) {
		if (event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
			if (event2.getDamager() instanceof Player) {
				Player damager = (Player) event2.getDamager();
				Bukkit.broadcastMessage("Decapitation debug - is player");
				ItemStack stack = damager.getInventory().getItemInMainHand();
				if (stack != null && stack.getEnchantments().containsKey((Enchantment)this)) {
					Bukkit.broadcastMessage("Decapitation debug - contains ench");
					int level = stack.getEnchantments().get((Enchantment)this);
					
					double chance = level * this.chance;
					
					if (Math.random() <= chance) {
						ItemStack head = getHead(event.getEntity());
						
						if (head != null) {
							event.getDrops().add(head);
						}
					}
				} else {
					Bukkit.broadcastMessage(stack.getEnchantments().keySet().toString());
					for (Enchantment e : stack.getEnchantments().keySet()) {
						if (e.getId() == this.getId()) {
							Bukkit.broadcastMessage("THIS: " + this.toString() + " | " + this.hashCode());
							Bukkit.broadcastMessage("E: " + e.toString() + " | " + e.hashCode());
							Bukkit.broadcastMessage("Equal: " + this.equals(e));
							Bukkit.broadcastMessage("==: " + (this == e));
						}
					}
				}
			}		
		}
	}*/
	
	@Override
	public void onKillEntity(ItemKillEntityEvent event) {
		int level = event.getLevel();
		
		double chance = level * this.chance;
		double random = Math.random();
		if (random <= chance) {
			ItemStack head = getHead(event.getEntity());
			
			if (head != null) {
				Bukkit.broadcastMessage("Dropped head");
				event.getEvent().getDrops().add(head);
			}
		}
	}
	
	public ItemStack getHead(LivingEntity entity) {
		EntityType type = entity.getType();
		if (type == EntityType.ZOMBIE) return new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
		if (type == EntityType.SKELETON) return new ItemStack(Material.SKULL_ITEM, 1, (short) 0);
		if (type == EntityType.WITHER_SKELETON && witherskullenabled) return new ItemStack(Material.SKULL_ITEM, 1, (short) 1);
		if (type == EntityType.CREEPER) return new ItemStack(Material.SKULL_ITEM, 1, (short) 4);
		if (type == EntityType.PLAYER) {
			
			ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta meta = (SkullMeta) stack.getItemMeta();
			if (Math.random() <= 0.01) {
				meta.setOwner("MHF_Herobrine");
			} else {
				meta.setOwner(entity.getName());
			}
			stack.setItemMeta(meta);
			return stack;
		}
		
		Map<EntityType, String> MHF_Heads = new HashMap<EntityType, String>();
		MHF_Heads.put(EntityType.CHICKEN, "MHF_Chicken");       MHF_Heads.put(EntityType.COW, "MHF_Cow");
		MHF_Heads.put(EntityType.SHEEP, "MHF_Sheep");           MHF_Heads.put(EntityType.PIG, "MHF_Pig");
		MHF_Heads.put(EntityType.MUSHROOM_COW, "MHF_MushroomCow");
		MHF_Heads.put(EntityType.BLAZE, "MHF_Blaze");           MHF_Heads.put(EntityType.SPIDER, "MHF_Spider");
		MHF_Heads.put(EntityType.CAVE_SPIDER, "MHF_CaveSpider"); MHF_Heads.put(EntityType.SQUID, "MHF_Squid");
		MHF_Heads.put(EntityType.GHAST, "MHF_Ghast");           MHF_Heads.put(EntityType.IRON_GOLEM, "MHF_Golem");
		MHF_Heads.put(EntityType.SLIME, "MHF_Slime");           MHF_Heads.put(EntityType.MAGMA_CUBE, "MHF_LavaSlime");
		MHF_Heads.put(EntityType.ENDERMAN, "MHF_Enderman");     MHF_Heads.put(EntityType.VILLAGER, "MHF_Villager");
		
		if (MHF_Heads.containsKey(type)) {
			ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta meta = (SkullMeta) stack.getItemMeta();
			meta.setOwner(MHF_Heads.get(type));
			stack.setItemMeta(meta);
			return stack;
		} 
		
		return null;
	}

}
