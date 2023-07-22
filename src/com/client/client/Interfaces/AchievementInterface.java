package com.client.client.Interfaces;

import static com.client.client.RSInterface.*;

import com.client.client.RSInterface;
import com.client.client.TextDrawingArea;

public class AchievementInterface {
    private static final int ORANGE = 0xFF8624;
    private static final int GREY = 0x858585;
    private static final int LIGHT_GREY = 0xC2C2C2;
    private static final int PROGRESS_BAR_COLOR = 0xFFAE00;

    public static void unpack(TextDrawingArea[] tda) {
        int id = 14291;
        int frame = 0;
        RSInterface rs = addInterface(id++);
        rs.totalChildren(26);

        //background
        addSpriteLoader(id, 1378);
        rs.child(frame++, id++, 14, 15);

        //title 1365
        addText(id, "Achievement Diary", fonts, 2, ORANGE, true, true);
        rs.child(frame++, id++, 259, 24);


        //title
        addText(id, "Achievements", fonts, 2, ORANGE, true, true);
        rs.child(frame++, id++, 107, 76);

        addText(id, "Points/Exp you'll receive", fonts, 0, ORANGE, true, true);
        rs.child(frame++, id++, 266, 176);

        addText(id, "Items you'll receive", fonts, 0, ORANGE, true, true);
        rs.child(frame++, id++, 413, 176);

        //Close Button
        addHoverButtonWSpriteLoader(id, 1379, 16, 16, "Close Window", 0, id+1, 1);
        addHoveredImageWSpriteLoader(id+1, 1380, 16, 16, id+2);
        rs.child(frame++, id, 472, 24);
        rs.child(frame++, id + 1, 472, 24);
        id += 3;

        //collect button
        addHoverButtonWSpriteLoader(id, 1381, 35, 25, "Collect Reward", 0, id+1, 1);
        addHoveredImageWSpriteLoader(id+1, 1382, 35, 25, id+2);
        rs.child(frame++, id, 199, 59);
        rs.child(frame++, id + 1, 199, 59);
        id += 3;

        //achievement title
        addText(id, "Achievement Name", fonts, 2, ORANGE, false, true);
        rs.child(frame++, id++, 240, 55);

        //achievement subtitle
        addText(id, "Easy", fonts, 1, GREY, false, true);
        rs.child(frame++, id++, 240, 70);


        //tabs
        addConfigButtonWSpriteLoader(id, id, 1383, 1384, 42, 20, "Easy", 0, 5, 1086);
        addConfigButtonWSpriteLoader(id + 1, id, 1383, 1384, 42, 20, "Medium", 1, 5, 1086);
        addConfigButtonWSpriteLoader(id + 2, id, 1383, 1384, 42, 20, "Hard", 2, 5, 1086);
        addConfigButtonWSpriteLoader(id + 3, id, 1383, 1384, 42, 20, "Expert", 3, 5, 1086);
        rs.child(frame++, id++, 24, 53);
        rs.child(frame++, id++, 65, 53);
        rs.child(frame++, id++, 106, 53);
        rs.child(frame++, id++, 147, 53);

        //tabs text
        addText(id, "Easy", fonts, 1, GREY, true, true);
        rs.child(frame++, id++, 44, 56);
        addText(id, "Med.", fonts, 1, GREY, true, true);
        rs.child(frame++, id++, 85, 56);
        addText(id, "Hard", fonts, 1, GREY, true, true);
        rs.child(frame++, id++, 126, 56);
        addText(id, "Expert", fonts, 1, GREY, true, true);
        rs.child(frame++, id++, 167, 56);

        //wrappable description
        addWrappingText(id, "A simple description of the achievement. A simple description of the achievement. A simple description of the achievement.", tda, 1, LIGHT_GREY, false, true, 279, 14);

        rs.child(frame++, id++, 200, 95);


        //wrappable points/exp description
        addWrappingText(id, "-X Points.\\n-X Points.\\n-X Points.", tda, 1, LIGHT_GREY, false, true, 129, 14);
        rs.child(frame++, id++, 200, 194);

        //item container
        addContainer(id, 0, 3, 3, 10, 6, false, new String[] {null, null, null, null, null});
        for (int i = 0; i < 9; i++) {
            interfaceCache[id].inv[i] = i % 2 == 0 ? 4152 : 4154;
            interfaceCache[id].invStackSizes[i] = 1;
        }
        rs.child(frame++, id++, 357, 194);

        //progress bar
        addRectangle(id, 0, 0x000000, true, 276, 6);
        rs.child(frame++, id++, 202, 156);
        createProgressBar(14790);
        rs.child(frame++, 14790, 203, 157);

        //progress text
        addText(id, "45% | 45/100", fonts, 2, 0xffff00, true, true);
        rs.child(frame++, id++, 341, 152);
        interfaceCache[id - 1].shadowed = true;

        createList();
        rs.child(frame++, 14690, 26, 95);
    }

    private static void createProgressBar(int id) {
        int frame = 0;
        RSInterface rs = addInterface(id++);
        rs.totalChildren(1);
        rs.width = 280;
        rs.height = 4;

        addRectangle(id, 0, PROGRESS_BAR_COLOR, true, 274, 4);
        rs.child(frame++, id++, 0, 0);
    }

    private static void createList() {
        int id = 14690;
        int frame = 0;
        RSInterface rs = addInterface(id++);
        int totalSlots = 80;
        rs.totalChildren(totalSlots*2);
        rs.width = 146;
        rs.height = 212;
        rs.scrollMax = totalSlots*22;

        for (int i = 0; i < totalSlots; i++) {
            addButtonWSpriteLoader(id, i % 2 == 0 ? 1385 : 1386, "Select");
            rs.child(frame++, id++, 0, i * 22);
            addText(id, "Achievement " + i, fonts, 0, ORANGE, false, true);
            rs.child(frame++, id++, 4, i * 22 + 6);
        }
    } 
}

