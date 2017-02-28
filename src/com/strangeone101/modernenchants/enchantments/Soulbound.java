package com.strangeone101.modernenchants.enchantments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.strangeone101.modernenchants.ModernEnchantment;
import com.strangeone101.modernenchants.ModernEnchants;
import com.strangeone101.modernenchants.config.StandardConfig;
import com.strangeone101.modernenchants.nms.Rarity;

public class Soulbound extends ModernEnchantment implements Listener {
	
	private int behaviour = 0;
	private double damage = 0.2;
	private boolean sendMessageOnDeath = true;
	
	public static final int UNENCHANT = 0;
	public static final int DAMAGE = 1;
	public static final int DAMAGE_AND_UNENCHANT = 2;
	public static final int DAMAGE_UNENCHANT_ON_BREAK = 3;
	public static final int NONE = 4;
	
	protected Map<Player, List<ItemStack>> respawnItems = new HashMap<Player, List<ItemStack>>();
	

	public Soulbound(int id) {
		super(id, "soulbound");
		
		damage = Double.valueOf(StandardConfig.config.getString("Soulbound.PercentageDamagedOnDeath", "25%").replaceAll("%", "")) / 100;
		behaviour = StandardConfig.config.getInt("Soulbound.Behaviour", behaviour);
		sendMessageOnDeath = StandardConfig.config.getBoolean("Soulbound.SendMessageOnDeath", sendMessageOnDeath);
	}

	@Override
	public Rarity getRarity() {
		return Rarity.VERY_RARE;
	}

	@Override
	public String getName() {
		return StandardConfig.config.getString("Soulbound.Name", "Soulbound");
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ALL;
	}

	@Override
	public boolean isTreasure() {
		return true;
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
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onDeath(final PlayerDeathEvent event) {
		if (event.getKeepInventory()) return;
		List<ItemStack> toRemove = new ArrayList<ItemStack>();
		for (ItemStack stack : event.getDrops()) {
			if (ModernEnchantment.hasEnchantments(stack)) {
				if (ModernEnchantment.getEnchantments(stack).containsKey(this)) {
					if (behaviour == UNENCHANT || behaviour == DAMAGE_AND_UNENCHANT) {
						stack.removeEnchantment((Enchantment)this);
					}
					
					if (stack.getType().getMaxDurability() > 1 && (behaviour == DAMAGE || behaviour == DAMAGE_AND_UNENCHANT || behaviour == DAMAGE_UNENCHANT_ON_BREAK)) {
						
						short durability = stack.getDurability();
						short takenDurability = (short) (durability * damage);
						
						if (stack.getEnchantments().containsKey(Enchantment.DURABILITY)) {
							takenDurability -= takenDurability * (stack.getEnchantments().get(Enchantment.DURABILITY) * 0.1);
						}
						
						
						
						stack.setDurability((short) (durability - takenDurability));
					}
					
					toRemove.add(stack);
				}
			}
		}
		if (toRemove.size() > 0) {
			event.getDrops().removeAll(toRemove);
			event.getEntity().sendMessage(ChatColor.RED + "" + toRemove.size() + " item(s) remained bound to you after your death.");
		}
		
		final List<ItemStack> finalToRemove = toRemove;
		
		new BukkitRunnable() {

			@Override
			public void run() {
				for (ItemStack stack : finalToRemove) {
					event.getEntity().getInventory().addItem(stack);
				}
			}
			
		}.runTaskLater(ModernEnchants.PLUGIN, 2L);
	}

}
