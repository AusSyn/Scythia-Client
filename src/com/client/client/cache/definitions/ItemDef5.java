package com.client.client.cache.definitions;

import com.client.client.cache.definitions.ItemDef3;


public class ItemDef5 {
	public static void defaultConfig(ItemDefinition itemDef, int groundModel, int maleModel, String name) {
		defaultConfig(itemDef, groundModel, maleModel, maleModel, name);
	}

	public static void defaultConfig(ItemDefinition itemDef, int groundModel, int maleModel, int femaleModel, String name) {
		itemDef.modelID = groundModel;
		itemDef.maleEquip1 = maleModel;
		itemDef.femaleEquip2 = femaleModel;
		itemDef.name = name;
		itemDef.description = ("It's an " + itemDef.name);


		itemDef.modelZoom = 1500;
		itemDef.modelOffset1 = 0;
		itemDef.rotationY = 900;
		itemDef.rotationX = 1200;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";

		itemDef.stackable = false;
	}
    public static ItemDefinition forDef(ItemDefinition itemDef, int ID) {
    	
		switch (ID){

        }
		

		return ItemDef4.forDef(itemDef, ID);
    }

}
