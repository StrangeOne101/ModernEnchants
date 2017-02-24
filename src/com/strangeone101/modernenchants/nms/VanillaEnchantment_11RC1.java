package com.strangeone101.modernenchants.nms;

import java.util.Arrays;

import org.bukkit.craftbukkit.v1_11_R1.enchantments.CraftEnchantment;

import com.strangeone101.modernenchants.ModernEnchantment;

import net.minecraft.server.v1_11_R1.Enchantment;
import net.minecraft.server.v1_11_R1.Enchantment.Rarity;
import net.minecraft.server.v1_11_R1.EnchantmentSlotType;
import net.minecraft.server.v1_11_R1.EnumItemSlot;

public class VanillaEnchantment_11RC1 extends NMSVanillaEnchantment {

	@Override
	public Object generate(final ModernEnchantment enchant) {
		Object[] slotArray = SlotType.getSlot(enchant);
		EnumItemSlot[] newArray = Arrays.copyOf(slotArray, slotArray.length, EnumItemSlot[].class);
		
		Enchantment enchantment = new Enchantment((Rarity)enchant.getRarity().getObject(), (EnchantmentSlotType)ItemType.getSlot(enchant), newArray) {
			@Override
			public int getMaxLevel() {
				return enchant.getMaxLevel();
			}
			
			@Override
			public int getStartLevel() {
				return enchant.getStartLevel();
			}
			
			@Override
			public boolean isTreasure() {
				return enchant.isTreasure();
			}
			
			@Override
			public boolean d() {
				return enchant.isCursed();
			}
			
			/*@Override
			public boolean canEnchant(ItemStack itemstack) {
				return enchant.canEnchantItem(CraftItemStack.asBukkitCopy(itemstack));
			}*/
			
			@Override
			protected boolean a(Enchantment enchantment) {
				return super.a(enchantment) && !enchant.conflictsWith(new CraftEnchantment(enchantment));
			}
		};
		enchantment.c(enchant.getName().toLowerCase());
		
		return enchantment;
	}	
}
