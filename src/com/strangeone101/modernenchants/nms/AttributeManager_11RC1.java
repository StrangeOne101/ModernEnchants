package com.strangeone101.modernenchants.nms;

import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_11_R1.NBTTagCompound;
import net.minecraft.server.v1_11_R1.NBTTagList;

public class AttributeManager_11RC1 extends NMSAttributeManager {

	@Override
	public void set(ItemStack stack, AttributeType attr, AttributeOperation type, double amount) {
		net.minecraft.server.v1_11_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(stack);
		NBTTagCompound tag = nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound();
		
		NBTTagCompound attrTag = new NBTTagCompound();
		attrTag.setString("AttributeName", attr.getAttributeName());
		attrTag.setString("Name", attr.getAttributeName());
		if (attr == AttributeType.ATTACK_DAMAGE) {
			attrTag.setString("Slot", "mainhand");
		}
		attrTag.setInt("Operation", type == AttributeOperation.ADDITION ? 0 : 1);
		attrTag.setDouble("Amount", amount);
		attrTag.setInt("UUIDLeast", 1111);
		attrTag.setInt("UUIDMost", 2222);
		
		NBTTagList list = new NBTTagList();
		if (tag.hasKey("AttributeModifiers")) {
			list = (NBTTagList) tag.get("AttributeModifiers");
		}
		
		list.add(attrTag);
		
		tag.set("AttributeModifiers", list);
		
		nmsStack.setTag(tag);
	}

}
