package com.strangeone101.modernenchants.nms;

import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_11_R1.NBTTagCompound;

public class AttributeManager_11RC1 extends NMSAttributeManager {

	@Override
	public void set(ItemStack stack, String attrName, Object obj) {
		net.minecraft.server.v1_11_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(stack);
		NBTTagCompound tag = nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound();
		
		NBTTagCompound attrTag = new NBTTagCompound();
		attrTag.setString("AttributeName", "");
		
	}

}
