package com.client.client.cache.definitions;

import com.client.client.*;

public final class MobDefinition {

	public int frontLight = 68;
	public int backLight = 820;
	public int rightLight = 0;
	public int middleLight = -1; // Cannot be 0
	public int leftLight = 0;
	
	public static MobDefinition forID(int i) {
		for (int j = 0; j < 20; j++)
			if (cache[j].type == (long) i)
				return cache[j];
		cacheIndex = (cacheIndex + 1) % 20;
		MobDefinition npc = cache[cacheIndex] = new MobDefinition();
		if (i >= streamIndices.length)
			return null;
		stream.pos = streamIndices[i];
		npc.type = i;
		npc.readValues(stream);
	
		if(npc.name != null && npc.name.toLowerCase().contains("bank")) {
			if(npc.actions != null) {
				for(int l = 0; l < npc.actions.length; l++) {
					if(npc.actions[l] != null && npc.actions[l].equalsIgnoreCase("Collect"))
						npc.actions[l] = null;
				}
			}
		}
		npc.id = i;

		switch (i) {
		
		case 700:
			npc.name = "Battle Royale Master";
			break;
		case 1100:
			npc.name = "Battle Royale Shop";
			break;
		case 5843:
			npc.actions = new String[] {"Talk-to", null, null, null, null};
			npc.name = "Plat Minigame Shop";
			break;
		case 702:
			npc.name = "B00NY Admirer";
			npc.actions = new String[5];
            npc.actions[0] = "Question?";
            npc.actions[1] = "Trade";
			break;
        case 132:
        	npc.name = "Blitz";
        	npc.description = "A master attacker of Zanaris-Ps.";
            npc.combatLevel = 913;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[9];
            npc.models[0] = 14395; // Hat
            npc.models[1] = 62746; // Platebody
            npc.models[2] = 62743; // Platelegs
            npc.models[3] = 62582; // Cape
            npc.models[4] = 13307; // Gloves
            npc.models[5] = 53327; // Boots
            npc.models[6] = 9642; // Amulet
            npc.models[7] = 2295; // Weapon
            npc.models[8] = 26423; // Shield
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.npcHeadModels = MobDefinition.forID(517).npcHeadModels;
            npc.sizeXZ = 200;
            npc.sizeY = 200;
            npc.squaresNeeded = 2;
            break;
        
        case 4600:
        	npc.name = "Vegeta";
        	npc.description = "A master attacker of Zanaris-Ps.";
            npc.combatLevel = 913;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[9];
            npc.models[0] = 64003; // Hat
            npc.models[1] = 64007; // Platebody
            npc.models[2] = 64005; // Platelegs
            npc.models[4] = 64001; // Gloves
            npc.models[5] = 64000; // Boots
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.sizeXZ = 100;
            npc.sizeY = 100;
            npc.squaresNeeded = 4;
            break;
        case 4601:
        	npc.name = "Vegeta 2";
        	npc.description = "A master attacker of Zanaris-Ps.";
            npc.combatLevel = 913;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[9];
            npc.models[0] = 64003; // Hat
            npc.models[1] = 64007; // Platebody
            npc.models[2] = 64005; // Platelegs
            npc.models[4] = 64001; // Gloves
            npc.models[5] = 64000; // Boots
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.sizeXZ = 150;
            npc.sizeY = 150;
            npc.squaresNeeded = 4;
            break;
        case 4602:
        	npc.name = "Vegeta 3";
        	npc.description = "A master attacker of Zanaris-Ps.";
            npc.combatLevel = 913;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[9];
            npc.models[0] = 64003; // Hat
            npc.models[1] = 64007; // Platebody
            npc.models[2] = 64005; // Platelegs
            npc.models[4] = 64001; // Gloves
            npc.models[5] = 64000; // Boots
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.sizeXZ = 200;
            npc.sizeY = 200;
            npc.squaresNeeded = 4;
            break;
        case 4603:
        	npc.name = "Goku 1";
        	npc.description = "A master attacker of ScythiaRsps.";
            npc.combatLevel = 913;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[9];
            npc.models[0] = 64010; // Hat
            npc.models[1] = 64012; // Platebody
            npc.models[2] = 64011; // Platelegs
            npc.models[4] = 64016; // Gloves
            npc.models[5] = 64009; // Boots
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.sizeXZ = 100;
            npc.sizeY = 100;
            npc.squaresNeeded = 4;
            break;
        case 4604:
        	npc.name = "Goku 2";
        	npc.description = "A master attacker of Zanaris-Ps.";
            npc.combatLevel = 913;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[9];
            npc.models[0] = 64010; // Hat
            npc.models[1] = 64012; // Platebody
            npc.models[2] = 64011; // Platelegs
            npc.models[4] = 64016; // Gloves
            npc.models[5] = 64009; // Boots
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.sizeXZ = 150;
            npc.sizeY = 150;
            npc.squaresNeeded = 4;
            break;
        case 4605:
        	npc.name = "Boss Goku";
        	npc.description = "A master attacker of Zanaris-Ps.";
            npc.combatLevel = 913;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[9];
            npc.models[0] = 64010; // Hat
            npc.models[1] = 64012; // Platebody
            npc.models[2] = 64011; // Platelegs
            npc.models[4] = 64016; // Gloves
            npc.models[5] = 64009; // Boots
            npc.walkAnim = 1660;
			npc.standAnim = 11973;
            npc.walkAnim = 819;
            npc.sizeXZ = 200;
            npc.sizeY = 200;
            npc.squaresNeeded = 4;
            break;
			case 1014:
			npc.name = "Valentines Event Master";
			npc.description = "Valentines Event Master";
			npc.models = new int[]{50749}; //npc.models = new int[]{50749,50751,50753,50755,50758,50760};
			npc.walkAnim = 819;
			npc.standAnim = 808;
			npc.sizeXZ = 150;
			npc.sizeY = 150;
			npc.squaresNeeded = 1;
			npc.actions = new String[] {"Talk to", null, null, null, null};
			break;
        case 133:
            npc.name = "Cobra";
            npc.description = "A master mager of Zanaris-Ps.";
            npc.combatLevel = 903;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[10];
            npc.models[0] = 3188; // Hat
            npc.models[1] = 58366; // Platebody
            npc.models[2] = 58333; // Platelegs
            npc.models[3] = 65297; // Cape
            npc.models[4] = 179; // Gloves
            npc.models[5] = 27738; // Boots
            npc.models[6] = 9642; // Amulet
            npc.models[7] = 56022; // Weapon
            npc.models[8] = 40942; // Shield
            npc.models[9] = 58316;
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.npcHeadModels = MobDefinition.forID(517).npcHeadModels;
            npc.sizeXZ = 200;
            npc.sizeY = 200;
            npc.squaresNeeded = 2;
            npc.destColours = new int[]{226770, 34503, 34503, 34503, 34503};
            npc.originalColours = new int[]{926, 65214, 65200, 65186, 62995};
            break;
        case 135:
            npc.name = "Fear";
            npc.description = "A master ranger of Velkora.";
            npc.combatLevel = 844;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[9];
            npc.models[0] = 26632; // Hat
            npc.models[1] = 20157; // Platebody
            npc.models[2] = 20139; // Platelegs
            npc.models[3] = 65297; // Cape
            npc.models[4] = 20129; // Gloves
            npc.models[5] = 27738; // Boots
            npc.models[6] = 9642; // Amulet
            npc.models[7] = 58380; // Weapon
            npc.models[8] = 20121;
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.npcHeadModels = MobDefinition.forID(517).npcHeadModels;
            npc.sizeXZ = 200;
            npc.sizeY = 200;
            npc.destColours = ItemDefinition.forID(10372).newModelColor;
            npc.originalColours = ItemDefinition.forID(10372).editedModelColor;
            npc.squaresNeeded = 2;
            break;
        case 1472:
            npc.name = "Death";
            npc.description = "A master Attacker of Velkora.";
            npc.combatLevel = 941;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[9];
            npc.models[0] = 55770; // Hat
            npc.models[1] = 55851; // Platebody
            npc.models[2] = 55815; // Platelegs
            npc.models[3] = 65297; // Cape
            npc.models[4] = 55728; // Gloves
            npc.models[5] = 55673; // Boots
            npc.models[6] = 9642; // Amulet
            npc.models[7] = 56046; // Weapon
            npc.models[8] = 38941; // Shield
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.npcHeadModels = MobDefinition.forID(517).npcHeadModels;
            npc.sizeXZ = 200;
            npc.sizeY = 200;
            npc.squaresNeeded = 2;
            npc.destColours = new int[]{127, 127, 127, 127};
            npc.originalColours = new int[]{65214, 65200, 65186, 62995};
            break;
		     
		case 3334:
			npc.name = "WildyWyrm";
			npc.models = new int[] { 63604 };
			//npc.boundDim = 1;
			npc.standAnim = 12790;
			npc.walkAnim = 12790;
			npc.combatLevel = 382;
			npc.actions = new String[5];
			npc.actions = new String[] {null, "Attack",  null, null, null};
			npc.sizeXZ = 225;
			npc.sizeY = 200;
			//npc.sizeXZ = 35;
			//npc.sizeY = 75;
		break;
//		
		
		case 1:
			npc.name = "Poison";
			npc.actions = new String[] {null, null, null, null, null};
			npc.sizeXZ = 1;
			npc.sizeY = 1;
			npc.drawMinimapDot = false;
			break;
		case 0:
			npc.name = " ";
			npc.actions = new String[] {null, null, null, null, null};
			npc.sizeXZ = 1;
			npc.sizeY = 1;
			npc.drawMinimapDot = false;
			break;
		case 6723:
			npc.name = "Rock Golem";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 29755;
			npc.actions = new String[5];
			npc.actions[0] = "Pick-up";
			npc.walkAnim = 7181;
			npc.standAnim = 7180;
			npc.description = "Its a Rock Golem.";
			npc.squaresNeeded = 1;
			npc.sizeXZ = npc.sizeY = 110;
			break;
		case 6724:
			npc.name = "Heron";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 29756;
			npc.actions = new String[5];
			npc.actions[0] = "Pick-up";
			npc.walkAnim = 6774;
			npc.standAnim = 6772;
			npc.description = "Its a Heron.";
			npc.squaresNeeded = 1;
			break;
			
		case 568:
			npc.name = "Coin trader";
			npc.actions = new String[] {"Trade", "Diamond Conversion", null, null, null};
			break;
			
		case 6726:
			npc.name = "Beaver";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 29754;
			npc.actions = new String[5];
			npc.actions[0] = "Pick-up";
			npc.walkAnim = 7178;
			npc.standAnim = 7177;
			npc.description = "Its a Beaver.";
			npc.squaresNeeded = 1;
			break;
		
		case 6640:
			npc.name = "Kraken";
			npc.models = new int[] { 28231 };
			//npc.boundDim = 1;
			npc.standAnim = 3989;
			npc.walkAnim = 3989;
			npc.sizeXZ = 25;
			npc.sizeY = 25;
			npc.actions = new String[5];
			npc.drawMinimapDot = false;
			npc.actions[0] = "Pick-up";
			npc.combatLevel = 0;
			npc.squaresNeeded = 1;
		break;
		
		case 963:
			npc.name = "Hellpupy";
			npc.models = new int[] { 29240 };
			//npc.boundDim = 1;
			npc.standAnim = 6561;
			npc.walkAnim = 6560;
			npc.originalColours = new int[] {29270};
			npc.actions = new String[5];
			npc.drawMinimapDot = false;
			npc.combatLevel = 0;
			npc.squaresNeeded = 1;
		break;
		
		case 5048:
			npc.name = "Darth Maul";
			npc.models = new int[] { 64790 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 240;
			npc.sizeY = 240;
		break;
		case 4263:
			npc.name = "Darius";
			npc.models = new int[] { 65482 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 1415:
			npc.name = "Magic Minigame";
			npc.models = new int[] { 50697 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 150;
			npc.sizeY = 150;
		break;
		case 1416:
			npc.name = "Witch";
			npc.models = new int[] { 50721 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 250;
			npc.sizeY = 250;
		break;
		case 1414:
			npc.name = "Range Minigame";
			npc.models = new int[] { 50698 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 120;
			npc.sizeY = 120;
		break;
		case 5148:
			npc.name = "UFO SHIP";
			npc.models = new int[] { 41995 };
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 5147:
			npc.name = "Voltorb";
			npc.models = new int[] { 41994 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 5149:
			npc.name = "Cloyster";
			npc.models = new int[] { 41997 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 5150:
			npc.name = "Beedrill";
			npc.models = new int[] { 41998 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 5151:
			npc.name = "Starmie";
			npc.models = new int[] { 41999 };
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 5152:
			npc.name = "Haunter";
			npc.models = new int[] { 42000 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 5153:
			npc.name = "Mega-Gengar";
			npc.models = new int[] { 42001 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 5154:
			npc.name = "Mew";
			npc.models = new int[] { 42002 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 5155:
			npc.name = "Rotom";
			npc.models = new int[] { 42003 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 5156:
			npc.name = "Seadra";
			npc.models = new int[] { 42004 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 5157:
			npc.name = "Mega Charizard";
			npc.models = new int[] { 64814 };
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
			npc.originalColours = new int[]{127};
			npc.destColours = new int[]  {69};
		break;
		case 5158:
			npc.name = "Lucario";
			npc.models = new int[] { 41400 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		break;
		case 5159:
			npc.name = "Lugia";
			npc.models = new int[] { 41931 };
			MobDefinition MobDefinition11111111111111 = MobDefinition.forID(4972);
            npc.standAnim = MobDefinition11111111111111.standAnim;
            npc.walkAnim = MobDefinition11111111111111.walkAnim;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 120;
			npc.sizeY = 120;
			break;
		case 4387:
			npc.name = "Mt. Dweller Jr.";
			npc.models = new int[] { 41965 };
			npc.standAnim = 808;
			npc.walkAnim = 11975;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 250;
			npc.sizeY = 250;
		break;
		
		case 4388:
			npc.name = "Mountain Dweller";
			npc.models = new int[] { 41965 };
			npc.standAnim = 808;
			npc.walkAnim = 11975;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		break;
		case 4389:
			npc.name = "Grand B00NY";
			npc.models = new int[] { 41965 };
			npc.standAnim = 808;
			npc.walkAnim = 11975;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 225;
			npc.sizeY = 225;
		break;
		case 4390:
			npc.name = "Pet B00NY";
			npc.models = new int[] { 41965 };
			npc.standAnim = 808;
			npc.walkAnim = 11975;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 80;
			npc.sizeY = 80;
		break;
		case 4340:
			npc.name = "Vote Boss";
			npc.models = new int[] { 41984 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 1015:
			npc.name = "Lover's Titan";
			npc.models = new int[] { 50762 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 1016:
			npc.name = "Lover's Keeper";
			npc.models = new int[] { 50761 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 50;
			npc.sizeY = 50;
		break;
		case 1017:
			npc.name = "Lt. Limes";
			npc.models = new int[] { 50765 };
			npc.standAnim = 4484;
			npc.walkAnim = 4488;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1018:
			npc.name = "Slifer the Sky Dragon";
			npc.models = new int[] { 50766 };
			MobDefinition NPCDef11111a = MobDefinition.forID(4972);
	        npc.standAnim = NPCDef11111a.standAnim;
	        npc.walkAnim = NPCDef11111a.walkAnim;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 130;
			npc.sizeY = 130;
		break;
		case 4341:
			npc.name = "Vote Boss Minion";
			npc.models = new int[] { 41984 };
			npc.standAnim = 2106;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[0] = "@gre@Speak with:";
			npc.combatLevel = 777;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 70;
			npc.sizeY = 70;
		break;
		case 29:
			npc.name = "Blessed Spartan";
			npc.models = new int[] { 41937, 41938, 41939, 41928, 41612, 34322 };
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.combatLevel = 777;
			npc.sizeXZ = 300;
			npc.sizeY = 300;
			MobDefinition lol = MobDefinition.forID(8133);
            npc.actions = lol.actions;
		break;
		case 30:
			npc.name = "Pet Blessed Spartan";
			npc.models = new int[] { 41937, 41938, 41939, 41928, 41612, 34322 };
			npc.actions = new String[] {null, null, null, null, null};
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.combatLevel = 777;
			npc.sizeXZ = 60;
			npc.sizeY = 60;
			MobDefinition loll = MobDefinition.forID(8133);
            npc.actions = loll.actions;
		break;
		case 31:
			npc.name = "Minion Spartan";
			npc.models = new int[] { 41937, 41938, 41939, 41928, 41612, 34322 };
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.combatLevel = 777;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
			MobDefinition lolll = MobDefinition.forID(8133);
            npc.actions = lolll.actions;
		break;
		case 1417:
			npc.name = "Octane";
			npc.models = new int[] { 50732, 50734, 50736, 50738, 50740, 50741, 50743, 50745 };
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.combatLevel = 777;
			npc.sizeXZ = 300;
			npc.sizeY = 300;
			MobDefinition lol1 = MobDefinition.forID(8133);
            npc.actions = lol1.actions;
            npc.destColours = new int[]{70};
            npc.originalColours = new int[]{14};
		break;
		case 3168:
			npc.name = "Sapphire Telos";
			npc.models = new int[] { 41705 };
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.turn180AnimIndex = -1;
			npc.turn90CCWAnimIndex = -1;
			npc.turn90CWAnimIndex = -1;
			npc.degreesToTurn = 32 ;
			npc.sizeXZ = 80;
			npc.sizeY = 80;
			npc.squaresNeeded = 5;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
		break;
		case 3167:
			npc.name = "Emerald Telos";
			npc.models = new int[] { 41930 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 3169:
			npc.name = "Ruby Telos";
			npc.models = new int[] { 41935 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 120;
			npc.sizeY = 120;
		break;
		case 3170:
			npc.name = "Lugia";
			npc.models = new int[] { 41931 };
			MobDefinition MobDefinition1111111111111 = MobDefinition.forID(4972);
            npc.standAnim = MobDefinition1111111111111.standAnim;
            npc.walkAnim = MobDefinition1111111111111.walkAnim;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 184:
			npc.name = "Green Storm Trooper";
			npc.models = new int[] { 41583 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 183:
			npc.name = "Blood Queen";
			npc.models = new int[] { 41573 };
			npc.standAnim = 6320;
			npc.walkAnim = 6319;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 280;
			npc.sizeY = 280;
			npc.destColours = new int[]{226770};
	        npc.originalColours = new int[]{127};
		break;
		case 185:
			npc.name = "DoomsDay";
			npc.models = new int[] { 41576 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 280;
			npc.sizeY = 280;
		break;
		case 294:
			npc.name = "Luigia";
			npc.models = new int[] { 41931 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 280;
			npc.sizeY = 280;
		break;
		case 178:
			npc.name = "Storm Trooper Shop";
			npc.models = new int[] { 41593 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 80;
			npc.sizeY = 80;
		break;
		case 179:
			npc.name = "Cyan Storm Trooper";
			npc.models = new int[] { 41594 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 190:
			npc.name = "Yvaltal";
			npc.models = new int[] { 41595 };
			MobDefinition MobDefinition1111111 = MobDefinition.forID(4972);
            npc.standAnim = MobDefinition1111111.standAnim;
            npc.walkAnim = MobDefinition1111111.walkAnim;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 163:
			npc.name = "Diamond Dragon";
			npc.models = new int[] { 41652 };
			MobDefinition MobDefinition111111 = MobDefinition.forID(4972);
            npc.standAnim = MobDefinition111111.standAnim;
            npc.walkAnim = MobDefinition111111.walkAnim;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 164:
			npc.name = "Emerald Dragon";
			npc.models = new int[] { 41653 };
			MobDefinition MobDefinition11111 = MobDefinition.forID(4972);
            npc.standAnim = MobDefinition11111.standAnim;
            npc.walkAnim = MobDefinition11111.walkAnim;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 165:
			npc.name = "Sapphire Dragon";
			npc.models = new int[] { 41654 };
			MobDefinition MobDefinition1111 = MobDefinition.forID(4972);
            npc.standAnim = MobDefinition1111.standAnim;
            npc.walkAnim = MobDefinition1111.walkAnim;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 175:
			npc.name = "Ruby Red Dragon";
			npc.models = new int[] { 41655 };
			MobDefinition MobDefinition11111111 = MobDefinition.forID(4972);
            npc.standAnim = MobDefinition11111111.standAnim;
            npc.walkAnim = MobDefinition11111111.walkAnim;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 174:
			npc.name = "Ruby Red Dragon Pet";
			npc.models = new int[] { 41655 };
			MobDefinition MobDefinition111111111 = MobDefinition.forID(4972);
            npc.standAnim = MobDefinition111111111.standAnim;
            npc.walkAnim = MobDefinition111111111.walkAnim;
            npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 50;
			npc.sizeY = 60;
		break;
		case 176:
			npc.name = "Diamond Dragon Pet";
			npc.models = new int[] { 41652 };
			MobDefinition MobDefinition11111111111 = MobDefinition.forID(4972);
            npc.standAnim = MobDefinition11111111111.standAnim;
            npc.walkAnim = MobDefinition11111111111.walkAnim;
            npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 50;
			npc.sizeY = 60;
		break;
		case 466:
			npc.name = "@bla@Darth @red@Vader";
			npc.models = new int[] { 41610 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 280;
			npc.sizeY = 280;
		break;
		case 224:
			npc.name = "@red@Emperor's @bla@Guard";
			npc.models = new int[] { 41589 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 4265:
			npc.name = "Gimlee";
			npc.models = new int[] { 65497 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 4266:
			npc.name = "@whi@Heatblast";
			npc.models = new int[] { 65495 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 4267:
			npc.name = "Blood Elemental";
			npc.models = new int[] { 65493 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 4268:
			npc.name = "Tiki Demon";
			npc.models = new int[] { 65494 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 3015:
			npc.name = "Neo Chronic";
			npc.models = new int[] { 41007 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 3016:
			npc.name = "Donald Trump";
			npc.models = new int[] { 41010 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 5053:
			npc.name = "Trump Pet";
			npc.models = new int[] { 41010 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 110;
			npc.sizeY = 110;
		break;
		case 3000:
			npc.name = "Foggy Boi Pet";
			npc.models = new int[] { 36091 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 110;
			npc.sizeY = 110;
		break;
		case 5277:
			npc.name = "Eve Pet";
			npc.models = new int[] { 65443 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 110;
			npc.sizeY = 110;
		break;
		case 5279:
			npc.name = "Darius Pet";
			npc.models = new int[] { 65482 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 110;
			npc.sizeY = 110;
		break;
		case 1514:
			npc.name = "Lava Demon Pet";
			npc.models = new int[] { 50672 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 35;
			npc.sizeY = 35;
		break;
		case 10_001:
			npc.name = "x2 KC Pet (Metagross)";
			npc.models = new int[] { 50624 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 110;
			npc.sizeY = 110;
			break;
		case 1042:
			npc.name = "Blue Eyes White Dragon Pet";
			npc.models = new int[] {50825};
			MobDefinition MobDefinition11111112111111214 = MobDefinition.forID(4972);
			npc.standAnim = MobDefinition11111112111111214.standAnim;
			npc.walkAnim = MobDefinition11111112111111214.walkAnim;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 35;
			npc.sizeY = 35;
			break;
		case 465:
			npc.name = "Darth Vader Pet";
			npc.models = new int[] { 41610 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 5272:
			npc.name = "Emperor's Guard pet";
			npc.models = new int[] { 41589 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 80;
			npc.sizeY = 80;
		break;
		case 182:
			npc.name = "Baby Yoda pet";
			npc.models = new int[] { 41741 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 70;
			npc.sizeY = 70;
		break;
		case 5276:
			npc.name = "Charizard Pet";
			npc.models = new int[] { 64814 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 90;
			npc.sizeY = 130;
		break;
		case 10002:
			npc.name = "Pinata Pet";
			npc.models = new int[] { 65510 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 60;
			npc.sizeY = 60;
		break;
		case 5278:
			npc.name = "Squirtle Pet";
			npc.models = new int[] { 41399 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 110;
			npc.sizeY = 110;
		break;
		case 5281:
			npc.name = "Pikachu Pet";
			npc.models = new int[] { 41402 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 110;
			npc.sizeY = 110;
		break;
		case 3017:
			npc.name = "Sayian Helper";
			npc.models = new int[] { 41018 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 3662:
			npc.name = "Pink Sayian";
			npc.models = new int[] { 41320 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		break;
		case 3663:
			npc.name = "Shirtless Sayian";
			npc.models = new int[] { 41321 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		break;
		case 3664:
			npc.name = "Evil Super Sayian";
			npc.models = new int[] { 41322 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		break;
		case 3018:
			npc.name = "Diablo";
			npc.models = new int[] { 41030 };
			npc.standAnim = 10921;
			npc.walkAnim = 10920;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 80;
			npc.sizeY = 80;
		break;
		case 3076:
			npc.name = "Vorago";
			npc.models = new int[] { 41051 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 80;
			npc.sizeY = 80;
		break;
		case 3074:
			npc.name = "Zeus";
			npc.models = new int[] { 41050 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 3094:
			npc.name = "Purple Insect ";
			npc.models = new int[] { 41066 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 80;
			npc.sizeY = 80;
		break;
		case 4269:
			npc.name = "Kevin Four Arms";
			npc.models = new int[] { 65491 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 250;
			npc.sizeY = 250;
		break;
		case 4270:
			npc.name = "Aragorn";
			npc.models = new int[] { 65492 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 13458:
			npc.name = "MetaGross";
			npc.models = new int[] { 50624 };
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 120;
			npc.sizeY = 120;
		break;
		case 4866:
			npc.name = "Plat Minigame Boss";
			npc.models = new int[] { 50672 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 120;
			npc.sizeY = 120;
		break;
		case 4867:
			npc.name = "Lava Demon Boss";
			npc.models = new int[] { 50672 };
			//npc.boundDim = 1;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 120;
			npc.sizeY = 120;
		break;
			case 1037:
				npc.name = "Fallen Lord";
				npc.models = new int[] { 50819 };
				//npc.boundDim = 1;
				npc.walkAnim = 1660;
				npc.standAnim = 11973;
				npc.actions = new String[5];
				npc.drawMinimapDot = true;
				npc.actions[1] = "@gre@Attack";
				npc.combatLevel = 999;
				npc.squaresNeeded = 2;
				npc.sizeXZ = 250;
				npc.sizeY = 250;
				break;
		case 5763:
			npc.name = "Defenderz Mob";
			npc.models = new int[] { 50625 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 120;
			npc.sizeY = 120;
		break;
		case 2237:
			copyDef(13458, npc);
			npc.name = "Weaponz Mob";
			npc.models = new int[] { 50628 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 120;
			npc.sizeY = 120;
		break;
		
		case 4271:
			npc.name = "Weaponized Eve";
			npc.models = new int[] { 65443 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 4272:
			npc.name = "Ra the Sun God";
			npc.models = new int[] { 45321 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 250;
			npc.sizeY = 250;
		break;
		case 4273:
			npc.name = "Alien";
			npc.models = new int[] { 45322 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 4274:
			npc.name = "Bad Bitch";
			npc.models = new int[] { 65456 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 8495:
			npc.name = "Erwin The Titan";
			npc.models = new int[] { 42085 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 8496:
			npc.name = "Armoured Titan";
			npc.models = new int[] { 42086 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 8497:
			npc.name = "Girl Titan";
			npc.models = new int[] { 42087 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 8498:
			npc.name = "Itachi";
			npc.models = new int[] { 42088 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 8499:
			npc.name = "Meliodas";
			npc.models = new int[] { 42089 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 8500:
			npc.name = "The Giant Flower";
			npc.models = new int[] { 42090 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 8501:
			npc.name = "Rayquaza";
			npc.models = new int[] { 42091 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 4275:
			npc.name = "Rayquaza";
			npc.models = new int[] { 65464 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 300;
			npc.sizeY = 300;
		break;
		case 4276:
			npc.name = "Thor";
			npc.models = new int[] { 65465 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		break;
		case 47:
			npc.name = "Blue pokemon";
			npc.models = new int[] { 41557 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		break;
		case 4396:
			npc.name = "Bear Pokemon";
			npc.models = new int[] { 41556 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		break;
		case 87:
			npc.name = "Heli Pet";
			npc.models = new int[] { 41386 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 86:
			npc.name = "Squirtle";
			npc.models = new int[] { 41399 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		break;
		case 88:
			npc.name = "Lucario";
			npc.models = new int[] { 41400 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		break;
		case 89:
			npc.name = "Pikachu";
			npc.models = new int[] { 41402 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		break;
		case 3007:
			npc.name = "Dr Strange";
			npc.models = new int[] { 65466 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 150;
			npc.sizeY = 150;
		break;
		case 3008:
			npc.name = "Groudon";
			npc.models = new int[] { 65490 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		break;
		case 3013:
			npc.name = "Pinata";
			npc.models = new int[] { 65486 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
        case 4264:
        	npc.name = "Deadly Robot";
        	npc.description = "A Deadly Robot.";
            npc.combatLevel = 913;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[9];
            npc.models[0] = 65424; // Hat
            npc.models[1] = 65422; // Platebody
            npc.models[2] = 65423; // Platelegs
            npc.models[3] = 65427; // Cape
            npc.models[4] = 65425; // Gloves
            npc.models[5] = 65426; // Boots
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.sizeXZ = 250;
            npc.sizeY = 250;
            npc.squaresNeeded = 2;
            break;
        case 3009:
        	npc.name = "Dark Knight";
        	npc.description = "A Deadly Knight.";
            npc.combatLevel = 913;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[9];
            npc.models[0] = 65522; // Hat
            npc.models[1] = 65520; // Platebody
            npc.models[2] = 65521; // Platelegs
            npc.models[3] = 65525; // Cape
            npc.models[4] = 64016; // Gloves
            npc.models[5] = 65524; // Boots
            npc.models[8] = 65527; // Shield
            npc.models[7] = 65523; // weapon
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.sizeXZ = 250;
            npc.sizeY = 250;
            npc.squaresNeeded = 2;
			npc.drawMinimapDot = true;
            break;
        case 3010:
        	npc.name = "Cannonbolt";
        	npc.description = "bzzzzt!";
            npc.combatLevel = 913;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[9];
            npc.models[0] = 65432; 
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.sizeXZ = 250;
            npc.sizeY = 250;
            npc.squaresNeeded = 2;
			npc.drawMinimapDot = true;
            break;
        case 3011:
        	npc.name = "Red Assasian";
        	npc.description = "A Deadly Assasian.";
            npc.combatLevel = 913;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[9];
            npc.models[0] = 65438; // Hat
            npc.models[1] = 65440; // Platebody
            npc.models[2] = 65442; // Platelegs
            npc.models[3] = 65430; // Cape
            npc.models[4] = 64016; // Gloves
            npc.models[5] = 65524; // Boots
            npc.models[7] = 65445; // Weapon
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.sizeXZ = 250;
            npc.sizeY = 250;
            npc.squaresNeeded = 2;
			npc.drawMinimapDot = true;
            break;
        case 3012:
        	npc.name = "Spiderman";
        	npc.description = "A Deadly Assasian.";
            npc.combatLevel = 913;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[9];
            npc.models[0] = 65467; // Hat
            npc.models[1] = 65469; // Platebody
            npc.models[2] = 65468; // Platelegs
            npc.models[4] = 64016; // Gloves
            npc.models[5] = 65524; // Boots
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.sizeXZ = 250;
            npc.sizeY = 250;
            npc.squaresNeeded = 2;
			npc.drawMinimapDot = true;
            break;
        case 3014:
        	npc.name = "The Evil Clown It";
        	npc.description = "A Deadly Clown.";
            npc.combatLevel = 913;
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
            npc.models = new int[9];
            npc.models[0] = 45323; // Hat
            npc.models[1] = 65531; // Platebody
            npc.models[2] = 45320; // Platelegs
            npc.models[4] = 65533; // Gloves
            npc.models[5] = 65529; // Boots
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.sizeXZ = 250;
            npc.sizeY = 250;
            npc.squaresNeeded = 2;
			npc.drawMinimapDot = true;
            break;
		case 5049:
			npc.name = "@bla@Abominable Snowman";
			npc.models = new int[] { 64803 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		case 5089:
			npc.name = "@bla@Snowman Pet";
			npc.models = new int[] { 64803 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 5051:
			npc.name = "@blu@Vegeta Pet";
			npc.models = new int[] { 64798 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.drawMinimapDot = true;
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 110;
			npc.sizeY = 110;
		break;
		case 5052:
			npc.name = "EVIL SAYIAN";
			npc.models = new int[] { 41322 };
			//npc.boundDim = 1;
			npc.standAnim = 1500;
			npc.walkAnim = 1500;
			npc.actions = new String[5];
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 110;
			npc.sizeY = 110;
		break;
		
		case 4999:
			npc.name = "@whi@Kid Sora";
			npc.models = new int[] { 64809 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
		break;
		case 4990:
			npc.name = "@red@Ryuk";
			npc.models = new int[] { 64806 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 4980:
			npc.name = "@cya@Ice Demon";
			npc.models = new int[] { 64815 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 4981:
			npc.name = "@yel@Mega Charizard";
			npc.models = new int[] { 64814 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
			npc.originalColours = new int[]{40};
			npc.destColours = new int[]  {69};
		break;
		case 4606:
			npc.name = "@blu@Zeldorado";
			npc.models = new int[] { 65303 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 450;
			npc.sizeY = 450;
		break;
		case 4997:
			npc.name = "@whi@Sauron";
			npc.models = new int[] { 64813 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 240;
			npc.sizeY = 240;
		break;
		case 4998:
			npc.name = "@whi@Diamond Head";
			npc.models = new int[] { 64812 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 220;
			npc.sizeY = 220;
		break;
		case 4993:
			npc.name = "@gre@Squidward-o-Saurus";
			npc.models = new int[] { 64811 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		break;
		case 4994:
			npc.name = "@blu@Sully";
			npc.models = new int[] { 64810 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 4991:
			npc.name = "@gre@Jesus";
			npc.models = new int[] { 64807 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		break;
		case 4992:
			npc.name = "@cya@Simba";
			npc.models = new int[] { 64808 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		break;
		
		case 5050:
			npc.name = "@blu@Vegeta";
			npc.models = new int[] { 64798 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
		break;
		
		
		case 5781:
			npc.name = "Baby mole";
			npc.models = new int[] { 12073 };
			//npc.boundDim = 1;
			npc.standAnim = 3309;
			npc.walkAnim = 3313;
			npc.actions = new String[5];
			npc.drawMinimapDot = false;
			npc.actions[0] = "Pick-up";
			npc.combatLevel = 0;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 80;
			npc.sizeY = 80;
		break;
		
		case 6727:
			npc.name = "Tangleroot";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 32202;
			npc.actions = new String[5];
			npc.actions[0] = "Pick-up";
			npc.walkAnim = 7313;
			npc.standAnim = 7312;
			npc.description = "Its a Tangleroot.";
			npc.squaresNeeded = 1;
			break;
		case 6728:
			npc.name = "Rocky";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 32203;
			npc.actions = new String[5];
			npc.actions[0] = "Pick-up";
			npc.walkAnim = 7316;
			npc.standAnim = 7315;
			npc.description = "Its a Rocky.";
			npc.squaresNeeded = 1;
			break;
		case 6729:
			npc.name = "Giant squirrel";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 32206;
			npc.actions = new String[5];
			npc.actions[0] = "Pick-up";
			npc.walkAnim = 7310;
			npc.standAnim = 7309;
			npc.description = "Its a Giant squirrel.";
			npc.squaresNeeded = 1;
			break;
		case 6730:
			npc.name = "Rift guardian";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 32204;
			npc.actions = new String[5];
			npc.actions[0] = "Pick-up";
			npc.walkAnim = 7306;
			npc.standAnim = 7307;
			npc.description = "Its a Rift guardian.";
			npc.squaresNeeded = 1;
			break;
		case 6731:
			npc.models = new int[1];
			npc.models[0] = 32697;
			npc.name = "Olmlet";
			npc.description = "Its a Olmlet.";
			npc.actions = new String[5];
			npc.actions[0] = "Pick-up";
			npc.squaresNeeded = 1;
			npc.standAnim = 7396;
			npc.walkAnim = 7395;
			npc.sizeXZ = npc.sizeY = 65;
			break;
			
		case 2000:
			npc.models = new int[2];
			npc.models[0] = 28294;
			npc.models[1] = 28295;
			npc.name = "Venenatis";
			npc.actions = new String[] {null, "Attack", null, null, null};
			npc.sizeXZ = 200;
			npc.sizeY = 200;
			MobDefinition ven = forID(60);
			npc.standAnim = ven.standAnim;
			npc.walkAnim = ven.walkAnim;
			npc.combatLevel = 464;
			npc.squaresNeeded = 3;
			break;
		case 4995:
			npc.models = new int[1];
			npc.models[0] = 64025;
			npc.name = "Frost Dragon";
			npc.actions = new String[] {null, "@cya@Attack", null, null, null};
			npc.sizeXZ = 50;
			npc.sizeY = 50;
			npc.standAnim = 5022;
			npc.walkAnim = 5022;
			npc.combatLevel = 464;
			npc.squaresNeeded = 3;
			break;
		case 4996:
			npc.models = new int[1];
			npc.models[0] = 83077;
			npc.name = "Blood Dragon";
			npc.actions = new String[] {null, "@cya@Attack", null, null, null};
			npc.sizeXZ = 50;
			npc.sizeY = 50;
			npc.standAnim = 5022;
			npc.walkAnim = 5022;
			npc.combatLevel = 464;
			npc.squaresNeeded = 3;
			break;
		case 2042://turgoise
			npc.name = "Zulrah";
			npc.actions = new String[] {null, "Attack", null, null, null};
			npc.models = new int[1];
			npc.models[0] = 14407;
			npc.standAnim = 5070;
			npc.walkAnim = 5070;
			npc.combatLevel = 725;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
			break;
		case 2043://regular
			npc.name = "Zulrah";
			npc.actions = new String[] {null, "Attack", null, null, null};
			npc.models = new int[1];
			npc.models[0] = 14408;
			npc.standAnim = 5070;
			npc.walkAnim = 5070;
			npc.combatLevel = 725;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
			break;
		case 2044://melee
			npc.name = "Zulrah";
			npc.actions = new String[] {null, "Attack", null, null, null};
			npc.models = new int[1];
			npc.models[0] = 14409;
			npc.standAnim = 5070;
			npc.walkAnim = 5070;
			npc.combatLevel = 725;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
			break;
		case 2001:
			npc.models = new int[1];
			npc.models[0] = 28293;
			npc.name = "Scorpia";
			npc.actions = new String[] {null, "Attack", null, null, null};
			MobDefinition scor = forID(107);
			npc.standAnim = scor.standAnim;
			npc.walkAnim = scor.walkAnim;
			npc.combatLevel = 464;
			npc.squaresNeeded = 3;
			break;
		case 7286:
			npc.name = "Skotizo";
			npc.description = "Badass from the depths of hell";
			npc.combatLevel = 321;
			MobDefinition skotizo = forID(4698);
			npc.standAnim = skotizo.standAnim;
			npc.walkAnim = skotizo.walkAnim;
			npc.actions = new String[5];
			npc.actions[1] = "Attack";
			npc.models = new int[1];
			npc.models[0] = 31653;
			npc.sizeXZ = 100; //resize if you wish hes a bit small cause personal preference
			npc.sizeY = 100; // resize
			npc.squaresNeeded = 3;
			break;
		case 6766:
			npc.name = "Lizardman shaman";
			npc.description = "It's a lizardman.";
			npc.combatLevel = 150;
			npc.walkAnim = 7195;
			npc.standAnim = 7191;
			npc.actions = new String[5];
			npc.actions[1] = "Attack";
			npc.models = new int[1];
			npc.models[0] = 4039;
			npc.sizeXZ = 108;
			npc.sizeY = 108;
			npc.squaresNeeded = 3;
			break;
		case 5886:
			npc.name = "Abyssal Sire";
			npc.description = "It's an abyssal sire.";
			npc.combatLevel = 350;
			npc.walkAnim = 4534;
			npc.standAnim = 4533;
			npc.actions = new String[5];
			npc.actions[1] = "Attack";
			npc.models = new int[1];
			npc.models[0] = 29477;
			npc.sizeXZ = 108;
			npc.sizeY = 108;
			npc.squaresNeeded = 6;
			break;
		case 499:
			npc.name = "Thermonuclear smoke devil";
			npc.description = "It looks like its glowing";
			npc.combatLevel = 301;
			npc.walkAnim = 1828;
			npc.standAnim = 1829;
			npc.actions = new String[5];
			npc.actions[1] = "Attack";
			npc.models = new int[1];
			npc.models[0] = 28442;
			npc.sizeXZ = 240;
			npc.sizeY = 240;
			npc.squaresNeeded = 4;
			break;
		case 1999:
			npc.models = new int[2];
			npc.name = "Cerberus";
			npc.models[1] = 29270;
			npc.combatLevel = 318;
			npc.standAnim = 4484;
			npc.walkAnim = 4488;
			npc.actions = new String[5];
			npc.originalColours = new int[] {29270};
			npc.actions = new String[] {null, "Attack", null, null, null};
			npc.sizeXZ = 120;
			npc.sizeY = 120;
		 break;
		case 2009:
			npc.name = "Callisto";
			npc.models = new int[] { 28298 };
			npc.actions = new String[] { null, "Attack", null, null, null};
			npc.combatLevel = 470;
			MobDefinition callisto = forID(105);
			npc.standAnim = callisto.standAnim;
			npc.walkAnim = callisto.walkAnim;
			npc.actions = callisto.actions;
			npc.sizeXZ = npc.sizeY = 110;
			npc.squaresNeeded = 4;
            break;
		case 2006:
			npc.models = new int[1];
			npc.models[0] = 28300;
			npc.name = "Vet'ion";
			npc.actions = new String[] {null, "Attack", null, null, null};
			MobDefinition vet = forID(90);
			npc.standAnim = vet.standAnim;
			npc.walkAnim = vet.walkAnim;
			npc.combatLevel = 464;
			break;
		
		case 3847:
			npc.name = "Kraken";
			npc.combatLevel = 291;
			npc.models = new int[] { 28231 };
			npc.standAnim = 3989;
			npc.walkAnim = forID(3847).walkAnim;
			npc.sizeXZ = npc.sizeY = 130;
			npc.lightning = 30;
			npc.shadow = 150;
			break;
			
		
		case 2004:
			npc.models = new int[1];
			npc.models[0] = 28231;
			npc.name = "Cave kraken";
			npc.actions = new String[] {null, "Attack", null, null, null};
			MobDefinition cave = forID(3847);
			npc.models = new int[1];
			npc.models[0] = 28233;
			npc.combatLevel = 127;
			npc.standAnim = 3989;
			npc.walkAnim = cave.walkAnim;
			npc.sizeXZ = npc.sizeY = 42;
			break;
		case 457:
			npc.name = "Ghost Town Citizen";
			npc.actions = new String[]{"Talk-to", null, "Teleport", null, null};
			break;
		case 241:
			npc.name = "Boss Point Shop";
			break;
		case 543:
			npc.name = "Decanter";
			break;
		case 4902:
			npc.name = "Expert Miner";
			npc.actions = new String[]{"Talk-To", null, "Trade", null, null};
			break;
		case 5417:
			npc.combatLevel = 210;
			break;
		case 5418:
			npc.combatLevel = 90;
			break;
		case 6715:
			npc.combatLevel = 91;
			break;
		case 6716:
			npc.combatLevel = 128;
			break;
		case 6701:
			npc.combatLevel = 173;
			break;
		case 6725:
			npc.combatLevel = 224;
			break;
		case 6691:
			npc.squaresNeeded = 2;
			npc.combatLevel = 301;
			break;
		case 1552:
			npc.name = "Donator Shop 3";
			break;
		case 741:
			npc.name = "Donator Shop 1";
			break;
		case 367:
			npc.name = "Item Gambler";
			break;
		
		case 725:
			npc.name = "Trivia Point Shop";
			break;
		case 8710:
		case 8707:
		case 8706:
		case 8705:
			npc.name = "Musician";
			npc.actions = new String[]{"Listen-to", null, null, null, null};
			break;
		case 10091:
			npc.name = "Rocktails for Rockbois";
			copyDef(318, npc);
			break;
		case 10_000:
			npc.name = "Player Owned Shop Manager";
			copyDef(947, npc);
			npc.actions = new String[] {"Talk-to", null, "View Shops", "My Shop", "Claim Earnings"};
			break;
		case 9939:
			npc.combatLevel = 607;
			break;
		case 149:
			npc.name = "Whirlpool";
			npc.models = new int[] {26699};
			npc.actions = new String[] { null, "Disturb", null, null, null};
			npc.standAnim = 6737;
			npc.walkAnim = 6737;
			npc.squaresNeeded = 4;
			npc.combatLevel = 0;
			npc.sizeY = 130;
			npc.sizeXZ = 130;
			npc.lightning = 30;
			npc.shadow = 150;
		break;
		case 148:
			npc.name = "Enormous Tentacle";
			npc.models = new int[] {13201, };
			npc.actions = new String[] { null, "Attack", null, null, null};
			npc.standAnim = 3617;
			npc.walkAnim = 3617;
			npc.combatLevel = 0;
			npc.sizeY = 200;
			npc.sizeXZ = 200;
		break;
		case 150://small
			npc.name = "Whirlpool";
			npc.models = new int[] {26699};
			npc.actions = new String[] { null, "Disturb", null, null, null};
			npc.standAnim = 6737;
			npc.walkAnim = 6737;
			npc.combatLevel = 0;
			npc.sizeY = 55;
			npc.sizeXZ = 55;
			npc.lightning = 30;
			npc.shadow = 150;
		break;
		case 688:
			npc.name = "Archer";
			break;
		case 279:
			npc.name = "AFK TICKETS SHOP";
			npc.actions = new String[]{"Talk-To", null, "Trade", null, null};
			break;
		case 454:
			npc.name = "Raids Point Shop";
			npc.actions = new String[]{"Talk-To", null, "Trade", null, null};
			break;
		case 278:
			npc.name = "Potions and Food Shop";
			break;
		case 500:
			npc.name = "Battle Royal Tokens Shop";
			npc.actions = new String[]{"Talk-To", null, "Trade", null, null};
			break;
		case 4540:
			npc.combatLevel = 299;
			break;
		case 3101:
			npc.sizeY = npc.sizeXZ = 80;
			npc.squaresNeeded = 1;
			npc.actions = new String[]{"Talk-to", null, "Start", "Rewards", null};
			break;
		case 6222:
			npc.name = "Kree'arra";
			npc.squaresNeeded = 5;
			npc.standAnim = 6972;
			npc.walkAnim = 6973;
			npc.actions = new String[]{null, "Attack", null, null, null};
			npc.sizeY = npc.sizeXZ = 110;
			break;
		case 6203:
			npc.models = new int[] {27768, 27773, 27764, 27765, 27770};
			npc.name = "K'ril Tsutsaroth";
			npc.squaresNeeded = 5;
			npc.standAnim = 6943;
			npc.walkAnim = 6942;
			npc.actions = new String[]{null, "Attack", null, null, null};
			npc.sizeY = npc.sizeXZ = 110;
			break;
		case 1610:
		case 491:
		case 10216:
			npc.actions = new String[]{null, "Attack", null, null, null};
			break;
		case 7969:
			npc.actions = new String[]{"Talk-to", null, "Trade", null, null};
			break;
		case 1382:
			npc.name = "Glacor";
			npc.models = new int[]{58940};
			npc.squaresNeeded = 3;
			//	npc.anInt86 = 475;
			npc.sizeXZ = npc.sizeY = 180;
			npc.standAnim = 10869;
			npc.walkAnim = 10867;
			npc.actions = new String[]{null, "Attack", null, null, null};
			npc.combatLevel = 123;
			npc.drawMinimapDot = true;
			npc.combatLevel = 188;
			break;
		case 1500:
			npc.name = "HunterxHunter";
			npc.models = new int[] { 50073 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1501:
			npc.name = "One Piece";
			npc.models = new int[] { 50075 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1502:
			npc.name = "Deadly Goul";
			npc.models = new int[] { 50074 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1503:
			npc.name = "The Reaper";
			npc.models = new int[] { 50071 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1504:
			npc.name = "Asserted Deamon";
			npc.models = new int[] { 50072 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1505:
			npc.name = "Black Demon King";
			npc.models = new int[] { 50076 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 40;
			npc.sizeY = 40;
		break;
		case 1506:
			npc.name = "Miss Magician";
			npc.models = new int[] { 50113 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 5;
			npc.sizeXZ = 120;
			npc.sizeY = 120;
		break;
		case 1507:
			npc.name = "Winged Monster";
			npc.models = new int[] { 50079 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1508:
			npc.name = "Blood Queen";
			npc.models = new int[] { 50069 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1509:
			npc.name = "Four Arms";
			npc.models = new int[] { 50192 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 80;
			npc.sizeY = 80;
		break;
		case 1510:
			npc.name = "Oblelisk the Tormentor";
			npc.models = new int[] { 50137 };
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1511:
			npc.name = "Gods Ruler";
			npc.models = new int[] { 50136 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1512:
			npc.name = "Dark goddess";
			npc.models = new int[] { 50134 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1488:
			npc.name = "Goro";
			npc.models = new int[] { 50563 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1489:
			npc.name = "Green Goro";
			npc.models = new int[] { 50564 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1490:
			npc.name = "Mileena";
			npc.models = new int[] { 50565 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1491:
			npc.name = "Cyan Mileena";
			npc.models = new int[] { 50566 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1492:
			npc.name = "Scorpion";
			npc.models = new int[] { 50567 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1493:
			npc.name = "SubZero";
			npc.models = new int[] { 50568 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1494:
			npc.name = "King of The UnderWorld";
			npc.models = new int[] { 50305 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1495:
			npc.name = "Deadly Scythia";
			npc.models = new int[] { 50393 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1496:
			npc.name = "Shild";
			npc.models = new int[] { 50332 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1497:
			npc.name = "Winged Goat";
			npc.models = new int[] { 50395 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1498:
			npc.name = "Armoured Mew";
			npc.models = new int[] { 50391 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		case 1499:
			npc.name = "Summoned Skull";
			npc.models = new int[] { 50394 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;		
		case 6820:
			npc.name = "Scythia's Shooter";
			npc.models = new int[] { 50307 };
			//npc.boundDim = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.actions = new String[5];
			npc.drawMinimapDot = true;
			npc.actions[1] = "@gre@Attack";
			npc.combatLevel = 999;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 140;
			npc.sizeY = 140;
		break;
		
			/*case 1383:
			npc.name = "Unstable glacyte";
			npc.models = new int[]{58942};
			npc.standAnim = 10867;
			npc.walkAnim = 10901;
			npc.actions = new String[]{null, "Attack", null, null, null};
			npc.combatLevel = 101;
			npc.drawMinimapDot = false;
			break;
		case 1384:
			npc.name = "Sapping glacyte";
			npc.models = new int[]{58939};
			npc.standAnim = 10867;
			npc.walkAnim = 10901;
			npc.actions = new String[]{null, "Attack", null, null, null};
			npc.combatLevel = 101;
			npc.drawMinimapDot = true;
			break;
		case 1385:
			npc.name = "Enduring glacyte";
			npc.models = new int[]{58937};
			npc.standAnim = 10867;
			npc.walkAnim = 10901;
			npc.actions = new String[]{null, "Attack", null, null, null};
			npc.combatLevel = 101;
			npc.drawMinimapDot = true;
			break;*/
		case 4249:
			npc.name = "Gambler";
			break;
		case 3535:
			npc.name = "Max";
			npc.description = "He's mastered the many skills of Velkora.";
            npc.actions[0] = "Talk-to";
            npc.actions[2] = "Trade";
            break;
		case 710:
			npc.name = "Donator Shop 2";
			break;
		case 6970:
			npc.actions = new String[] {"Trade", null, "Exchange Shards", null, null};
			break;
		case 4657:
			npc.actions = new String[] {"Talk-to", null, "Claim Items", "Check Total", "Teleport"};
			break;
		case 605:
			npc.name = "VOTE SHOP";
			npc.actions = new String[] {"Talk-to", null, "Vote Rewards", "Loyalty Titles", null};
			break;
		case 8591:
			npc.actions = new String[] {"Talk-to", null, "Trade", null, null};
			break;
		case 316:
		case 315:
		case 309:
		case 310:
		case 314:
		case 312:
		case 313:
			npc.sizeXZ = 30;
			break;
		case 318:
			npc.sizeXZ = 30;
			npc.actions = new String[] {"Net", null, "Lure", null, null};
			break;
		case 805:
			npc.actions = new String[] {"Trade", null, "Tan hide", null, null};
			break;
		case 461:
		case 844:
		case 650:
		case 5112:
		case 3789:
		case 802:
		case 520:
		case 521:
		case 11226:
			npc.actions = new String[] {"Trade", null, null, null, null};
			break;
		case 8022:
		case 8028:
			String color = i == 8022 ? "Yellow" : "Green";
			npc.name = ""+color+" energy source";
			npc.actions = new String[] {"Siphon", null, null, null, null};
			break;
		case 8444:
			npc.actions = new String[5];
			npc.actions[0] = "Trade";
			break;
		case 2579:
			npc.name = "Max";
			npc.description = "He's mastered the many skills of Velkora.";
			npc.combatLevel = 138;
            npc.actions = new String[5];
            npc.actions[0] = "Talk-to";
            npc.actions[2] = "Trade";
            npc.standAnim = 808;
            npc.walkAnim = 819;
            npc.models = new int[]{65291, 65322, 506, 529, 252, 9642, 62746, 13307, 62743, 53327};
            npc.npcHeadModels = new int[]{39332, 39235};
			break;
		case 6830:
		case 6841:
		case 6796:
		case 7331:
		case 6831:
		case 7361:
		case 6847:
		case 6872:
		case 7353:
		case 6835:
		case 6845:
		case 6808:
		case 7370:
		case 7333:
		case 7351:
		case 7367:
		case 6853:
		case 6855:
		case 6857:
		case 6859:
		case 6861:
		case 6863:
		case 9481:
		case 6827:
		case 6889:
		case 6813:
		case 6817:
		case 7372:
		case 6839:
		case 8575:
		case 7345:
		case 6799:
		case 7335:
		case 7347:
		case 6800:
		case 9488:
		case 6804:
		case 6822:
		case 6849:
		case 7355:
		case 7357:
		case 7359:
		case 7341:
		case 7329:
		case 7339:
		case 7349:
		case 7375:
		case 7343:
		case 6865:
		case 6809:
		case 7363:
		case 7337:
		case 7365:
		case 6991:
		case 6992:
		case 6869:
		case 6818:
		case 6843:
		case 6823:
		case 7377:
		case 6887:
		case 6885:
		case 6883:
		case 6881:
		case 6879:
		case 6877:
		case 6875:
		case 6833:
		case 6851:
		case 5079:
		case 5080:
		case 6824:
			npc.actions = new String[] {null, null, null, null, null};
			break;
		case 6806: // thorny snail
		case 6807:
		case 6994: // spirit kalphite
		case 6995:
		case 6867: // bull ant
		case 6868:
		case 6794: // spirit terrorbird
		case 6795:
		case 6815: // war tortoise
		case 6816:
		case 6874:// pack yak
		case 6873: // pack yak
		case 3594: // yak
		case 3590: // war tortoise
		case 3596: // terrorbird
			npc.actions = new String[] {"Store", null, null, null, null};
			break;
		case 548:
			npc.actions = new String[] {"Trade", null, null, null, null};
			break;
		case 3299:
		case 437:
			npc.actions = new String[] {"Trade", null, null, null, null};
			break;
	
		case 1267:
		case 8459:
			npc.drawMinimapDot = true;
			break;
		case 961:
			npc.actions = new String[] {null, null, "Buy Consumables", "Restore Stats", null};
			npc.name = "Healer";
			break;
		case 705:
			npc.actions = new String[] {null, null, "Buy Armour", "Buy Weapons", "Buy Jewelries"};
			npc.name = "Warrior";
			break;
		case 1861:
			npc.actions = new String[] {null, null, "Buy Equipment", "Buy Ammunition", null};
			npc.name = "Archer";
			break;
		case 946:
			npc.actions = new String[] {null, null, "Buy Equipment", "Buy Runes", null};
			npc.name = "Mage";
			break;
		case 2253:
			npc.actions = new String[] {null, null, "Buy Skillcapes", "Buy Skillcapes (t)", "Buy Hoods"};
			break;
		case 2292:
			npc.actions = new String[] {"Trade", null, null, null, null};
			npc.name = "Merchant";
			break;
		case 2676:
			npc.actions = new String[] {"Makeover", null, null, null, null};
			break;
		case 494:
		case 1360:
			npc.actions = new String[] {"Talk-to", null, null, null, null};
			break;
		case 1685:
			npc.name = "Pure";
			npc.actions = new String[] {"Trade", null, null, null, null};
			break;
		case 3030:
			npc.name = "King black dragon";
			npc.models = new int[] {17414, 17415, 17429, 17422};
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 276;
			npc.standAnim = 90;
			npc.walkAnim = 4635;
			npc.sizeY = 40;
			npc.sizeXZ = 40;
			npc.squaresNeeded = 3;
			break;

		case 3031:
			npc.name = "General graardor";
			npc.models = new int[] {27785, 27789};
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 624;
			npc.standAnim = 7059;
			npc.walkAnim = 7058;
			npc.sizeY = 29;
			npc.sizeXZ = 33;
			break;	

		case 3032:
			npc.name = "TzTok-Jad";
			npc.models = new int[] {34131};
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 702;
			npc.standAnim = 9274;
			npc.walkAnim = 9273;
			npc.sizeY = 25;
			npc.sizeXZ = 27;
			npc.squaresNeeded = 1;
			break;

		case 3033:
			npc.name = "Chaos elemental";
			npc.models = new int[] {11216};
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 305;
			npc.standAnim = 3144;
			npc.walkAnim = 3145;
			npc.sizeY = 49;
			npc.sizeXZ = 45;
			break;

		case 3034:
			npc.name = "Corporeal beast";
			npc.models = new int[] {40955};
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 785;
			npc.standAnim = 10056;
			npc.walkAnim = 10055;
			npc.sizeY = 24;
			npc.sizeXZ = 25;
			npc.squaresNeeded = 1;
			break;
			case 1038:
				npc.name = "Knight of Torment";
				npc.models = new int[] {50858};
				npc.actions = new String[5];
				npc.actions[1] = "Attack";
				npc.combatLevel = 785;
				npc.walkAnim = 1660;
				npc.standAnim = 11973;
				npc.sizeY = 150;
				npc.sizeXZ = 150;
				npc.squaresNeeded = 1;
				break;
			case 1039:
				npc.name = "Fallen God";
				npc.models = new int[] {50859};
				npc.actions = new String[5];
				npc.actions[1] = "Attack";
				npc.combatLevel = 785;
				npc.walkAnim = 1660;
				npc.standAnim = 11973;
				npc.sizeY = 150;
				npc.sizeXZ = 150;
				npc.squaresNeeded = 1;
				break;
			case 1040:
				npc.name = "Blue Eye White Dragon";
				npc.models = new int[] {50825};
				npc.actions = new String[5];
				npc.actions[1] = "Attack";
				npc.combatLevel = 785;
				MobDefinition MobDefinition1111111111111214 = MobDefinition.forID(4972);
				npc.standAnim = MobDefinition1111111111111214.standAnim;
				npc.walkAnim = MobDefinition1111111111111214.walkAnim;
				npc.sizeY = 150;
				npc.sizeXZ = 150;
				npc.squaresNeeded = 3;
				break;
			case 1041:
				npc.name = "Smoozies Event Boss";
				npc.models = new int[] {50931};
				npc.actions = new String[5];
				npc.actions[1] = "Attack";
				npc.combatLevel = 785;
				npc.walkAnim = 1660;
				npc.standAnim = 11973;
				npc.sizeY = 250;
				npc.sizeXZ = 250;
				npc.squaresNeeded = 1;
				break;
		case 4862:
			npc.name = "Shark Beast";
			npc.models = new int[] {50662};
            npc.actions = new String[5];
            npc.actions[1] = "Attack";
			npc.combatLevel = 785;
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.sizeY = 250;
			npc.sizeXZ = 250;
			npc.squaresNeeded = 1;
			break;
		case 3035:
			npc.name = "Kree'arra";
			npc.models = new int[] {28003, 28004};
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 580;
			npc.standAnim = 6972;
			npc.walkAnim = 6973;
			npc.sizeY = 23;
			npc.sizeXZ = 23;
			npc.squaresNeeded = 1;
			break;

		case 3036:
			npc.name = "K'ril tsutsaroth";
			npc.models = new int[] {27768, 27773, 27764, 27765, 27770};
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 650;
			npc.standAnim = 6943;
			npc.walkAnim = 6942;
			npc.sizeY = 24;
			npc.sizeXZ = 24;
			npc.squaresNeeded = 1;
			break;
		case 3037:
			npc.name = "Commander zilyana";
			npc.models = new int[] {28057, 28071, 28078, 28056};
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 596;
			npc.standAnim = 6963;
			npc.walkAnim = 6962;
			npc.sizeY = 60;
			npc.sizeXZ = 60;
			npc.squaresNeeded = 1;
			break;
		case 3038:
			npc.name = "Dagannoth supreme";
			npc.models = new int[] {9941, 9943};
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 303;
			npc.standAnim = 2850;
			npc.walkAnim = 2849;
			npc.sizeY = 60;
			npc.sizeXZ = 60;
			npc.squaresNeeded = 1;
			break;

		case 3039:
			npc.name = "Dagannoth prime"; //9940, 9943, 9942
			npc.models = new int[] {9940, 9943, 9942};
			npc.originalColours = new int[]{11930, 27144, 16536, 16540};
			npc.destColours = new int[]{5931, 1688, 21530, 21534};
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 303;
			npc.standAnim = 2850;
			npc.walkAnim = 2849;
			npc.sizeY = 60;
			npc.sizeXZ = 60;
			npc.squaresNeeded = 1;
			break;

		case 3040:
			npc.name = "Dagannoth rex";
			npc.models = new int[] {9941};
			npc.originalColours = new int[]{16536, 16540, 27144, 2477};
			npc.destColours = new int[]{7322, 7326, 10403, 2595};
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 303;
			npc.standAnim = 2850;
			npc.walkAnim = 2849;
			npc.sizeY = 60;
			npc.sizeXZ = 60;
			npc.squaresNeeded = 1;
			break;
		case 3047:
			npc.name = "Frost dragon";
			npc.combatLevel = 166;
			npc.standAnim = 13156;
			npc.walkAnim = 13157;
			npc.turn180AnimIndex = -1;
			npc.turn90CCWAnimIndex = -1;
			npc.turn90CWAnimIndex = -1;
			npc.actions = new String[] {null, null, null, null, null};
			//npc.type = 51;
			npc.degreesToTurn = 32 ;
			npc.models = new int[] {56767, 55294};
			npc.sizeY = 45;
			npc.sizeXZ = 45;
			npc.squaresNeeded = 1;
			break;

		case 3048:
			npc.models = new int[]{44733};
			npc.name = "Tormented demon";
			npc.actions = new String[] {null, null, null, null, null};
			npc.combatLevel = 450;
			npc.standAnim = 10921;
			npc.walkAnim = 10920;
			npc.turn180AnimIndex = -1;
			npc.turn90CCWAnimIndex = -1;
			npc.turn90CWAnimIndex = -1;
		//	npc.type = 8349;
			npc.degreesToTurn = 32;
			npc.sizeY = 40;
			npc.sizeXZ = 40;
			npc.squaresNeeded = 1;
			break;
		case 3050:
			npc.models = new int[] {24602, 24605, 24606};
			npc.actions = new String[] {null, null, null, null, null};
			npc.name = "Kalphite queen";
			npc.combatLevel = 333;
			npc.standAnim = 6236;
			npc.walkAnim = 6236;
			npc.sizeY = 40;
			npc.sizeXZ = 40;
			npc.squaresNeeded = 1;
			break;
		case 3051:
			npc.models = new int[] {46141};
			npc.actions = new String[] {null, null, null, null, null};
			npc.name = "Slash bash";
			npc.combatLevel = 111;
			npc.standAnim = 11460;
			npc.walkAnim = 11461;
			npc.sizeY = 45;
			npc.sizeXZ = 45;
			npc.squaresNeeded = 1;
			break;
		case 3052:
			npc.models = new int[] {45412};
			npc.actions = new String[] {null, null, null, null, null};
			npc.name = "Phoenix";
			npc.combatLevel = 235;
			npc.standAnim = 11074;
			npc.walkAnim = 11075;
			npc.sizeY = 45;
			npc.sizeXZ = 45;
			npc.squaresNeeded = 1;
			break;
		case 3053:
			npc.models = new int[] {46058, 46057};
			npc.actions = new String[] {null, null, null, null, null};
			npc.name = "Bandos avatar";
			npc.combatLevel = 299;
			npc.standAnim = 11242;
			npc.walkAnim = 11255;
			npc.sizeY = 45;
			npc.sizeXZ = 45;
			npc.squaresNeeded = 1;
			break;
		case 3054:
			npc.models = new int[] {62717};
			npc.actions = new String[] {null, null, null, null, null};
			npc.name = "Nex";
			npc.combatLevel = 565;
			npc.standAnim = 6320;
			npc.walkAnim = 6319;
			npc.sizeY = 55;
			npc.sizeXZ = 55;
			npc.squaresNeeded = 1;
			break;
		case 3055:
			npc.models = new int[] {51852, 51853};
			npc.actions = new String[] {null, null, null, null, null};
			npc.name = "Jungle strykewyrm";
			npc.combatLevel = 110;
			npc.standAnim = 12790;
			npc.walkAnim = 12790;
			npc.sizeY = 39;
			npc.sizeXZ = 35;
			npc.squaresNeeded = 1;
			break;
		case 3056:
			npc.models = new int[] {51848, 51850};
			npc.actions = new String[] {null, null, null, null, null};
			npc.name = "Desert strykewyrm";
			npc.combatLevel = 130;
			npc.standAnim = 12790;
			npc.walkAnim = 12790;
			npc.sizeY = 39;
			npc.sizeXZ = 35;
			npc.squaresNeeded = 1;
			break;
		case 3057:
			npc.models = new int[] {51847, 51849};
			npc.actions = new String[] {null, null, null, null, null};
			npc.name = "Ice strykewyrm";
			npc.combatLevel = 210;
			npc.standAnim = 12790;
			npc.walkAnim = 12790;
			npc.sizeY = 39;
			npc.sizeXZ = 35;
			npc.squaresNeeded = 1;
			break;
		case 3058:
			npc.models = new int[] {49142, 49144};
			npc.actions = new String[] {null, null, null, null, null};
			npc.name = "Green dragon";
			npc.combatLevel = 79;
			npc.standAnim = 12248;
			npc.walkAnim = 12246;
			npc.sizeY = 40;
			npc.sizeXZ = 40;
			npc.squaresNeeded = 1;
			break;
		case 3059:
			npc.actions = new String[] {null, null, null, null, null};
			npc.models = new int[] {57937};
			npc.name = "Baby blue dragon";
			npc.combatLevel = 48;
			npc.standAnim = 14267;
			npc.walkAnim = 14268;
			npc.sizeY = 70;
			npc.sizeXZ = 70;
			npc.squaresNeeded = 1;
			break;
		case 3060:
			npc.models = new int[] {49137, 49144};
			npc.actions = new String[] {null, null, null, null, null};
			npc.name = "Blue dragon";
			npc.combatLevel = 111;
			npc.standAnim = 12248;
			npc.walkAnim = 12246;
			npc.sizeY = 45;
			npc.sizeXZ = 45;
			npc.squaresNeeded = 1;
			break;
		case 3061:
			npc.models = new int[] {14294, 49144};
			npc.actions = new String[] {null, null, null, null, null};
			npc.name = "Black dragon";
			npc.combatLevel = 227;
			npc.standAnim = 12248;
			npc.walkAnim = 12246;
			npc.sizeY = 45;
			npc.sizeXZ = 45;
			npc.squaresNeeded = 1;
			break;
		case 3062:
			npc.models = new int[2];
			npc.actions = new String[] {null, null, null, null, null};
			npc.models[0] = 28294;
			npc.models[1] = 28295;
			npc.name = "Venenatis";
			npc.sizeXZ = 45;
			npc.sizeY = 45;
			MobDefinition ven2 = forID(60);
			npc.standAnim = ven2.standAnim;
			npc.walkAnim = ven2.walkAnim;
			npc.combatLevel = 464;
			npc.squaresNeeded = 2;
			break;
		case 3063:
			npc.actions = new String[] {null, null, null, null, null};
			npc.models = new int[1];
			npc.models[0] = 28293;
			npc.name = "Scorpia";
			MobDefinition scor2 = forID(107);
			npc.standAnim = scor2.standAnim;
			npc.walkAnim = scor2.walkAnim;
			npc.sizeXZ = 55;
			npc.sizeY = 55;
			npc.combatLevel = 464;
			npc.squaresNeeded = 1;
			break;
		case 3064:
			npc.name = "Skotizo";
			npc.combatLevel = 321;
			npc.actions = new String[] {null, null, null, null, null};
			MobDefinition skotizo2 = forID(4698);
			npc.standAnim = skotizo2.standAnim;
			npc.walkAnim = skotizo2.walkAnim;
			npc.models = new int[1];
			npc.models[0] = 31653;
			npc.sizeXZ = 22;
			npc.sizeY = 22;
			npc.squaresNeeded = 1;
			break;
		case 3065:
			npc.actions = new String[] {null, null, null, null, null};
			npc.name = "Lizardman Shaman";
			npc.description = "It's a lizardman.";
			npc.combatLevel = 150;
			npc.walkAnim = 7195;
			npc.standAnim = 7191;
			npc.models = new int[1];
			npc.models[0] = 4039;
			npc.sizeXZ = 38;
			npc.sizeY = 38;
			npc.squaresNeeded = 1;
		 break;
		 
		case 3066:
			npc.name = "WildyWyrm";
			npc.actions = new String[] {null, null, null, null, null};
			npc.models = new int[] { 63604 };
			//npc.boundDim = 1;
			npc.standAnim = 12790;
			npc.walkAnim = 12790;
			npc.combatLevel = 382;
			npc.sizeXZ = 30;
			npc.sizeY = 40;
			npc.squaresNeeded = 1;
			//npc.sizeXZ = 35;
			//npc.sizeY = 75;
		break;
		case 3067:
			npc.name = "Bork";
			npc.actions = new String[] {null, null, null, null, null};
			npc.models = new int[] { 40590 };
			//npc.boundDim = 1;
			npc.standAnim = 8753;
			npc.walkAnim = 8752;
			npc.combatLevel = 267;
			npc.sizeXZ = 40;
			npc.sizeY = 40;
			npc.squaresNeeded = 1;
			//npc.sizeXZ = 35;
			//npc.sizeY = 75;
		break;
	
		case 3068:
			npc.name = "Barrelchest";
			npc.actions = new String[] {null, null, null, null, null};
			npc.models = new int[] { 22790 };
			//npc.boundDim = 1;
			npc.standAnim = 5893;
			npc.walkAnim = 5892;
			npc.combatLevel = 267;
			npc.sizeXZ = 40;
			npc.sizeY = 40;
			npc.squaresNeeded = 1;
		  		     break;
		case 3069:
			npc.name = "Rock Crab";
			npc.actions = new String[] {null, null, null, null, null};
			npc.models = new int[2];
			npc.models[0] = 4399;
			npc.models[1] = 4400;
			//npc.boundDim = 1;
			npc.standAnim = 1310;
			npc.walkAnim = 1311;
			npc.combatLevel = 13;
			npc.sizeXZ = 80;
			npc.sizeY = 80;
			npc.squaresNeeded = 1;
		  		     break;
		case 3070:
			npc.name = "Abyssal Sire";
			npc.actions = new String[] {null, null, null, null, null};
			npc.description = "It's an abyssal sire.";
			npc.combatLevel = 350;
			npc.walkAnim = 4534;
			npc.standAnim = 4533;
			npc.models = new int[1];
			npc.models[0] = 29477;
			npc.sizeXZ = 28;
			npc.sizeY = 28;
			npc.squaresNeeded = 1;
			break;
			

		/*  		     
		case 1265:
			 System.out.println("Models: " + npc.models[1]);
			 System.out.println("Stand animation: "  +npc.standAnim);
			 System.out.println("Walk animation: " + npc.walkAnim);

				break; */
		
		}
		return npc;

	}
	public static String NpcModels(int itemID) {
		int inv = forID(itemID).models[1];
		String name = forID(itemID).name;
		return "<col=225>"+name+"</col> (<col=800000000>"+itemID+"</col>) - [Model: <col=800000000>"+inv+"</col>]";
	}

	public Model getHeadModel() {
		if (childrenIDs != null) {
			MobDefinition altered = getAlteredNPCDef();
			if (altered == null)
				return null;
			else
				return altered.getHeadModel();
		}
		if (npcHeadModels == null)
			return null;
		boolean everyFetched = false;
		for (int i = 0; i < npcHeadModels.length; i++)
			if (!Model.modelIsFetched(npcHeadModels[i]))
				everyFetched = true;
		if (everyFetched)
			return null;
		Model parts[] = new Model[npcHeadModels.length];
		for (int j = 0; j < npcHeadModels.length; j++)
			parts[j] = Model.fetchModel(npcHeadModels[j]);
		Model completeModel;
		if (parts.length == 1)
			completeModel = parts[0];
		else
			completeModel = new Model(parts.length, parts);
		if (originalColours != null) {
			for (int k = 0; k < originalColours.length; k++)
				completeModel.recolour(originalColours[k], destColours[k]);
		}
		return completeModel;
	}
	

	public MobDefinition getAlteredNPCDef() {
		try {
			int j = -1;
			if (varbitId != -1) {
				VarBit varBit = VarBit.cache[varbitId];
				int k = varBit.configId;
				int l = varBit.leastSignificantBit;
				int i1 = varBit.mostSignificantBit;
				int j1 = GameClient.anIntArray1232[i1 - l];
				j = clientInstance.variousSettings[k] >> l & j1;
			} else if (varSettingsId != -1) {
				j = clientInstance.variousSettings[varSettingsId];
			}
			if (j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1) {
				return null;
			} else {
				return forID(childrenIDs[j]);
			}
		} catch (Exception e) {
			return null;
		}
	}

	public static int NPCAMOUNT = 11599;

	public static void unpackConfig(CacheArchive streamLoader) {
		stream = new Stream(streamLoader.getDataForName("npc.dat"));
		Stream stream2 = new Stream(streamLoader.getDataForName("npc.idx"));
		int totalNPCs = stream2.readUnsignedWord();
		streamIndices = new int[totalNPCs];
		int i = 2;
		for (int j = 0; j < totalNPCs; j++) {
			streamIndices[j] = i;
			i += stream2.readUnsignedWord();
		}
		cache = new MobDefinition[20];
		for (int k = 0; k < 20; k++)
			cache[k] = new MobDefinition();
		//NPCDefThing2.initialize();
	}

	public static void nullLoader() {
		modelCache = null;
		streamIndices = null;
		cache = null;
		stream = null;
	}

	public Model getAnimatedModel(int j, int k, int ai[]) {
		if (childrenIDs != null) {
			MobDefinition npc = getAlteredNPCDef();
			if (npc == null)
				return null;
			else
				return npc.getAnimatedModel(j, k, ai);
		}
		Model completedModel = (Model) modelCache.get(type);
		if (completedModel == null) {
			boolean everyModelFetched = false;
			for (int ptr = 0; ptr < models.length; ptr++)
				if (!Model.modelIsFetched(models[ptr]))
					everyModelFetched = true;

			if (everyModelFetched)
				return null;
			Model parts[] = new Model[models.length];
			for (int j1 = 0; j1 < models.length; j1++)
				parts[j1] = Model.fetchModel(models[j1]);
			if (parts.length == 1)
				completedModel = parts[0];
			else
				completedModel = new Model(parts.length, parts);
			if (originalColours != null) {
				for (int k1 = 0; k1 < originalColours.length; k1++)
					completedModel.recolour(originalColours[k1], destColours[k1]);
			}
			completedModel.createBones();
			completedModel.light(frontLight, backLight, rightLight, middleLight, leftLight, true);
			modelCache.put(completedModel, type);
		}
		Model animatedModel = Model.entityModelDesc;
		animatedModel.method464(completedModel, FrameReader.isNullFrame(k) & FrameReader.isNullFrame(j));
		if (k != -1 && j != -1)
			animatedModel.method471(ai, j, k);
		else if (k != -1)
			animatedModel.applyTransform(k);
		if (sizeXZ != 128 || sizeY != 128)
			animatedModel.scaleT(sizeXZ, sizeXZ, sizeY);
		animatedModel.calculateDiagonals();
		animatedModel.groupedTriangleLabels = null;
		animatedModel.groupedLabels = null;
		if (squaresNeeded == 1)
			animatedModel.rendersWithinOneTile = true;

		return animatedModel;
	}

	public Model method164(int j, int frame, int ai[], int nextFrame, int idk, int idk2) {
		if (childrenIDs != null) {
			MobDefinition npc = getAlteredNPCDef();
			if (npc == null)
				return null;
			else
				return npc.method164(j, frame, ai, nextFrame, idk, idk2);
		}
		Model completedModel = (Model) modelCache.get(type);
		if (completedModel == null) {
			boolean everyModelFetched = false;
			for (int ptr = 0; ptr < models.length; ptr++)
				if (!Model.modelIsFetched(models[ptr]))
					everyModelFetched = true;

			if (everyModelFetched)
				return null;
			Model parts[] = new Model[models.length];
			for (int j1 = 0; j1 < models.length; j1++)
				parts[j1] = Model.fetchModel(models[j1]);
			if (parts.length == 1)
				completedModel = parts[0];
			else
				completedModel = new Model(parts.length, parts);
			if (originalColours != null) {
				for (int k1 = 0; k1 < originalColours.length; k1++)
					completedModel.recolour(originalColours[k1], destColours[k1]);
			}
			completedModel.createBones();
			completedModel.light(frontLight, backLight, rightLight, middleLight, leftLight, true);
			modelCache.put(completedModel, type);
		}
		Model animatedModel = Model.entityModelDesc;
		animatedModel.method464(completedModel, FrameReader.isNullFrame(frame) & FrameReader.isNullFrame(j));

		if (frame != -1 && j != -1)
			animatedModel.method471(ai, j, frame);
		else if (frame != -1 && nextFrame != -1)
			animatedModel.applyTransform(frame, nextFrame, idk, idk2);
		else if (frame != -1)
			animatedModel.applyTransform(frame);
		if (sizeXZ != 128 || sizeY != 128)
			animatedModel.scaleT(sizeXZ, sizeXZ, sizeY);
		animatedModel.calculateDiagonals();
		animatedModel.groupedTriangleLabels = null;
		animatedModel.groupedLabels = null;
		if (squaresNeeded == 1)
			animatedModel.rendersWithinOneTile = true;
		return animatedModel;
	}

	public void readValues(Stream stream) {
		do {
			int i = stream.readUnsignedByte();
			if (i == 0)
				return;
			if (i == 1) {
				int j = stream.readUnsignedByte();
				models = new int[j];
				for (int j1 = 0; j1 < j; j1++)
					models[j1] = stream.readUnsignedWord();
			} else if (i == 2)
				name = stream.readNewString();
			else if (i == 3) {
				description = stream.readNewString();
			} else if (i == 12)
				squaresNeeded = stream.readSignedByte();
			else if (i == 13)
				standAnim = stream.readUnsignedWord();
			else if (i == 14) {
				walkAnim = stream.readUnsignedWord();
				runAnim = walkAnim;
			} else if (i == 17) {
				walkAnim = stream.readUnsignedWord();
				turn180AnimIndex = stream.readUnsignedWord();
				turn90CWAnimIndex = stream.readUnsignedWord();
				turn90CCWAnimIndex = stream.readUnsignedWord();
				if (walkAnim == 65535)
					walkAnim = -1;
				if (turn180AnimIndex == 65535)
					turn180AnimIndex = -1;
				if (turn90CWAnimIndex == 65535)
					turn90CWAnimIndex = -1;
				if (turn90CCWAnimIndex == 65535)
					turn90CCWAnimIndex = -1;
			} else if (i >= 30 && i < 40) {
				if (actions == null)
					actions = new String[5];
				actions[i - 30] = stream.readNewString();
				if (actions[i - 30].equalsIgnoreCase("hidden"))
					actions[i - 30] = null;
			} else if (i == 40) {
				int k = stream.readUnsignedByte();
				destColours = new int[k];
				originalColours = new int[k];
				for (int k1 = 0; k1 < k; k1++) {
					originalColours[k1] = stream.readUnsignedWord();
					destColours[k1] = stream.readUnsignedWord();
				}
			} else if (i == 60) {
				int l = stream.readUnsignedByte();
				npcHeadModels = new int[l];
				for (int l1 = 0; l1 < l; l1++)
					npcHeadModels[l1] = stream.readUnsignedWord();
			} else if (i == 90)
				stream.readUnsignedWord();
			else if (i == 91)
				stream.readUnsignedWord();
			else if (i == 92)
				stream.readUnsignedWord();
			else if (i == 93)
				drawMinimapDot = false;
			else if (i == 95)
				combatLevel = stream.readUnsignedWord();
			else if (i == 97)
				sizeXZ = stream.readUnsignedWord();
			else if (i == 98)
				sizeY = stream.readUnsignedWord();
			else if (i == 99)
				hasRenderPriority = true;
			else if (i == 100)
				lightning = stream.readSignedByte();
			else if (i == 101)
				shadow = stream.readSignedByte() * 5;
			else if (i == 102)
				headIcon = stream.readUnsignedWord();
			else if (i == 103)
				degreesToTurn = stream.readUnsignedWord();
			else if (i == 106) {
				varbitId = stream.readUnsignedWord();
				if (varbitId == 65535)
					varbitId = -1;
				varSettingsId = stream.readUnsignedWord();
				if (varSettingsId == 65535)
					varSettingsId = -1;
				int i1 = stream.readUnsignedByte();
				childrenIDs = new int[i1 + 1];
				for (int i2 = 0; i2 <= i1; i2++) {
					childrenIDs[i2] = stream.readUnsignedWord();
					if (childrenIDs[i2] == 65535)
						childrenIDs[i2] = -1;
				}
			} else if (i == 107)
				clickable = false;
		} while (true);
	}

	public MobDefinition() {
		turn90CCWAnimIndex = -1;
		varbitId = -1;
		turn180AnimIndex = -1;
		varSettingsId = -1;
		combatLevel = -1;
		walkAnim = -1;
		squaresNeeded = 1;
		headIcon = -1;
		standAnim = -1;
		type = -1L;
		degreesToTurn = 32;
		turn90CWAnimIndex = -1;
		clickable = true;
		sizeY = 128;
		drawMinimapDot = true;
		sizeXZ = 128;
		hasRenderPriority = false;
	}

	public int turn90CCWAnimIndex;
	public static int cacheIndex;
	public int varbitId;
	public int turn180AnimIndex;
	public int varSettingsId;
	public static Stream stream;
	public int combatLevel;
	public String name;
	public String actions[];
	public int walkAnim;
	public int runAnim;
	public byte squaresNeeded;
	public int[] destColours;
	public static int[] streamIndices;
	public int[] npcHeadModels;
	public int headIcon;
	public int[] originalColours;
	public int standAnim;
	public long type;
	public int degreesToTurn;
	public static MobDefinition[] cache;
	public static GameClient clientInstance;
	public int turn90CWAnimIndex;
	public boolean clickable;
	public int lightning;
	public int sizeY;
	public boolean drawMinimapDot;
	public int childrenIDs[];
	public String description;
	public int sizeXZ;
	public int shadow;
	public boolean hasRenderPriority;
	public int[] models;
	public static MemCache modelCache = new MemCache(30);
	public int id;
	private int configChild = -1;

	public static void copyDef(MobDefinition copyFrom, MobDefinition copyTo) {
		copyTo.models = copyFrom.models;
		copyTo.runAnim = copyFrom.runAnim;
		copyTo.standAnim = copyFrom.standAnim;
		copyTo.walkAnim = copyFrom.walkAnim;
		copyTo.actions = copyFrom.actions;
		//copyTo.name = copyFrom.name;
		copyTo.npcHeadModels = copyFrom.npcHeadModels;
	}
	
	public static void copyDef(int copyFromId, MobDefinition copyTo) {
		copyDef(MobDefinition.forID(copyFromId), copyTo);
	}



	   public MobDefinition method161() {
		      int j = -1;

		      try {
		         if (this.varbitId != -1) {
		            VarBit varBit = VarBit.cache[this.varbitId];
		            int k = varBit.configId;
		            int l = varBit.configValue;
		            int i1 = varBit.anInt650;
		            int j1 = GameClient.anIntArray1232[i1 - l];
		            j = clientInstance.variousSettings[k] >> l & j1;
		         } else if (this.configChild  != -1) {
		            j = clientInstance.variousSettings[this.configChild];
		         }
		      } catch (Exception var7) {
		         var7.printStackTrace();
		      }

		      return j >= 0 && j < this.childrenIDs.length && this.childrenIDs[j] != -1 ? forID(this.childrenIDs[j]) : null;
		   }
}