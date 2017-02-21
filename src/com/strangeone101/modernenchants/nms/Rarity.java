package com.strangeone101.modernenchants.nms;

import com.strangeone101.modernenchants.util.ReflectionUtils;
import com.strangeone101.modernenchants.util.ReflectionUtils.PackageType;

public enum Rarity {
	
	COMMON, UNCOMMON, RARE, VERY_RARE;
	
	public Object getObject() {
		try {
			return ReflectionUtils.getField("Enchantment$Rarity", PackageType.MINECRAFT_SERVER, true, this.toString()).get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
