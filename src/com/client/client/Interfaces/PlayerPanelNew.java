package com.client.client.Interfaces;

import com.client.client.RSInterface;
import com.client.client.TextDrawingArea;
import com.client.client.util.ColorConstants;

public class PlayerPanelNew {

	public static void playerPanelInterface(TextDrawingArea[] tda) {
		int interfaceId = 19850;
		int children = 0;
		int totalChildren = 17;

		RSInterface Inteface = RSInterface.addInterface(interfaceId);
		RSInterface.setChildren(totalChildren, Inteface);

		interfaceId++;
		RSInterface.addText(interfaceId, "", tda, 1, ColorConstants.BLUE, false, true);
		RSInterface.setBounds(interfaceId, 60, 1, children, Inteface);

		interfaceId++;
		children++;
		RSInterface.addSpriteLoader(interfaceId, 1387);
		RSInterface.setBounds(interfaceId, 0, 32, children, Inteface);

		interfaceId++;
		children++;
		RSInterface.addSpriteLoader(interfaceId, 1387);
		RSInterface.setBounds(interfaceId, 0, 260, children, Inteface);

		interfaceId++;
		children++;
		RSInterface.addSpriteLoader(interfaceId, 809);
		RSInterface.setBounds(interfaceId, 0, 35, children, Inteface);
		
        interfaceId++;
        children++;
        RSInterface.addHoverButtonComplete(interfaceId, children, 3, 7,
        		1389, 1390, 41, 21, "Players tab", Inteface, true);
        RSInterface.setSpriteClicked(interfaceId, 1390);
        //GameClient.setInterfaceClicked(19850, interfaceId, true, true);
        
        interfaceId += 3;
        children += 2;
        RSInterface.addHoverButtonComplete(interfaceId, children, 33, 7,
        		1391, 1392, 41, 21, "World events tab", Inteface, true);
        RSInterface.setSpriteClicked(interfaceId, 1392);
        
        interfaceId += 3;
        children += 2;
        RSInterface.addHoverButtonComplete(interfaceId, children, 63, 7,
        		1393, 1394, 41, 21, "Points & Statistics tab", Inteface, true);
        RSInterface.setSpriteClicked(interfaceId, 1394);
        
        interfaceId += 3;
        children += 2;
        RSInterface.addHoverButtonComplete(interfaceId, children, 93, 7,
        		1395, 1396, 41, 21, "Kill Data", Inteface, true);
        RSInterface.setSpriteClicked(interfaceId, 1396);
        
        interfaceId += 3;
        children += 2;
        RSInterface.addHoverButtonComplete(interfaceId, children, 123, 7,
        		1397, 1397, 41, 21, "Achievement Data", Inteface, true); 
        RSInterface.setSpriteClicked(interfaceId, 1397);
        
        interfaceId += 3;
        children += 2;
        RSInterface.addHoverButtonComplete(interfaceId, children, 153, 7,
        		1398, 1399, 41, 21, "Collection Data", Inteface, true);
        RSInterface.setSpriteClicked(interfaceId, 1399);
               
        interfaceId++;
        children++;
        RSInterface.addButtonWSpriteLoader(interfaceId, 1400, "Refresh");
        RSInterface.setBounds(interfaceId, 170, 235, children, Inteface);
        
        
        children++;
		RSInterface scroll = RSInterface.addTabInterface(15016);
		RSInterface.setBounds(15016, 0, 35, children, Inteface);
		scroll.totalChildren(75);
		scroll.width = 172;
		scroll.height = 196;
		scroll.scrollMax = 1050;

		int k = 0;
		int y = 0;
		for (int i = 39159; i < 39234; i++) {
			scroll.child(k, i, 8, y);
			y += 13;
			k++;
			if (i == 39160) {
				RSInterface.addText(i, "", tda, 2, 0xff0000, false, true);
			} else {
				RSInterface.addText(i, "", tda, 0, 0xff0000, false, true);
			}
		}
	}
}
