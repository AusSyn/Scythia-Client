package com.client.client.Interfaces;

import com.client.client.RSInterface;
import com.client.client.TextDrawingArea;
import com.client.client.util.ColorConstants;

public class CollectionLog {
	
	public static void customCollectionLog(TextDrawingArea[] tda) {
        final int STARTING_POINT = 30360;
        RSInterface main = RSInterface.addInterface(STARTING_POINT);
        RSInterface.addSpriteLoader(STARTING_POINT + 1, 1401);
        RSInterface.addHoverButtonWSpriteLoader(STARTING_POINT + 2, 1402, 159, 29, "Search NPC", -1, STARTING_POINT + 3, 1);
        RSInterface.addHoveredImageWSpriteLoader(STARTING_POINT + 3, 1403, 159, 29, STARTING_POINT + 4);
        RSInterface.addText(STARTING_POINT + 5, "NPC's", tda, 2, ColorConstants.ORANGE, false, true);
        RSInterface.addText(STARTING_POINT + 6, "Search NPC", tda, 1, ColorConstants.ORANGE, false, true);
        RSInterface.addNpc(STARTING_POINT + 7, 131);
        RSInterface.addText(STARTING_POINT + 8, "NPC Killcount", tda, 2, ColorConstants.ORANGE, false, true);
        RSInterface.addText(STARTING_POINT + 9, "Killcount: 159", tda, 1, ColorConstants.ORANGE, false, true);
        RSInterface.addText(STARTING_POINT + 10, "Collection Log", tda, 2, ColorConstants.ORANGE, false, true);
        RSInterface.addCloseButtonSmall(STARTING_POINT + 11, STARTING_POINT + 12, STARTING_POINT + 13);
        RSInterface.addText(STARTING_POINT + 14, "Collection Log", tda, 2, ColorConstants.ORANGE, false, true);
        RSInterface.addToItemGroup(STARTING_POINT + 15, 4, 5, 6, 6, true, new String[]{null, null, null, null, null});
        main.totalChildren(14);
        main.child(0, STARTING_POINT + 1, 10, 10);
        main.child(1, STARTING_POINT + 2, 18, 278);
        main.child(2, STARTING_POINT + 3, 18, 278);
        main.child(3, STARTING_POINT + 5, 75, 51);
        main.child(4, STARTING_POINT + 6, 77, 285);
        main.child(5, STARTING_POINT + 7, 191, 85);
        main.child(6, STARTING_POINT + 8, 215, 211);
        main.child(7, STARTING_POINT + 9, 217, 258);
        main.child(8, STARTING_POINT + 10, 367, 52);
        main.child(9, STARTING_POINT + 11, 465, 20);
        main.child(10, STARTING_POINT + 12, 465, 20);
        main.child(11, STARTING_POINT + 14, 214, 20);
        main.child(12, STARTING_POINT + 30, 0, 70);
        main.child(13, STARTING_POINT + 15, 339, 77);

        RSInterface scroll = RSInterface.addInterface(STARTING_POINT + 30);
        scroll.totalChildren(300);
        scroll.width = 157;
        scroll.height = 204;
        scroll.scrollMax = 3000;

        int spriteY = 0;
        for (int i = 0; i < 150; i++) { // i already did that, lol, bsp broken lol
        	RSInterface.addSpriteLoader(STARTING_POINT + 31 + i, i % 2 != 0 ? 1404 : 1405); // it has the first sprites correct
            // 
            scroll.child(i, STARTING_POINT + 31 + i, 19, spriteY);
            spriteY += 22;
        }
        
        int textY = 4;
        for (int i = 0; i < 150; i++) {
        	RSInterface.addHoverableText(STARTING_POINT + 200 + i, "", "Select", tda, 1, false, true, 110, ColorConstants.ORANGE, ColorConstants.WHITE);
            scroll.child(150 + i, STARTING_POINT + 200 + i, 23, textY);
            textY += 22;
        }
    }
}
