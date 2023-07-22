package com.client.client.cache.definitions;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.client.client.DrawingArea;
import com.client.client.Model;
import com.client.client.Rasterizer;
import com.client.client.Sprite;
import com.client.Configuration;
import com.client.client.*;

public final class ItemDefinition {
    private static int[] prices;
    private static List<Integer> untradeableItems = new ArrayList<Integer>();
    private static int size;
    public static List<Integer> priorityModels = new ArrayList<>();

    public static void nullLoader() {
        modelCache = null;
        spriteCache = null;
        streamIndices = null;
        cache = null;
        stream = null;
    }

    public static void applyTexturing(Model model, int id) {
        switch (id) {
            case 50_303:
                //model.setTexture(40);
                break;
        }
    }

    public boolean dialogueModelFetched(int j) {
        int k = maleDialogue;
        int l = maleDialogueModel;
        if (j == 1) {
            k = femaleDialogue;
            l = femaleDialogueModel;
        }
        if (k == -1) {
            return true;
        }
        boolean flag = true;
        if (!Model.modelIsFetched(k)) {
            flag = false;
        }
        if (l != -1 && !Model.modelIsFetched(l)) {
            flag = false;
        }

        return flag;
    }

    public Model getDialogueModel(int gender) {
        int k = maleDialogue;
        int l = maleDialogueModel;
        if (gender == 1) {
            k = femaleDialogue;
            l = femaleDialogueModel;
        }
        if (k == -1) {
            return null;
        }
        Model model = Model.fetchModel(k);
        if (l != -1) {
            Model model_1 = Model.fetchModel(l);
            Model models[] = {model, model_1};
            model = new Model(2, models);
        }
        if (editedModelColor != null) {
            for (int i1 = 0; i1 < editedModelColor.length; i1++) {
                model.recolour(editedModelColor[i1], newModelColor[i1]);
            }
        }

        applyTexturing(model, id);

        return model;
    }

    public boolean equipModelFetched(int gender) {

        int fistModel = maleEquip1;
        int secondModel = maleEquip2;
        int thirdModel = maleEquip3;
        Model model = Model.fetchModel(fistModel);

        if (gender == 1) {
            fistModel = femaleEquip1;
            secondModel = femaleEquip2;
            thirdModel = femaleEquip3;
        }
        if (fistModel == -1) {
            return true;
        }
        boolean flag = true;
        if (!Model.modelIsFetched(fistModel)) {
            flag = false;
        }
        if (secondModel != -1 && !Model.modelIsFetched(secondModel)) {
            flag = false;
        }
        if (thirdModel != -1 && !Model.modelIsFetched(thirdModel)) {
            flag = false;
        }

        return flag;
    }

    public Model getEquipModel(int gender) {
        int j = maleEquip1;
        int k = maleEquip2;
        int l = maleEquip3;
        if (gender == 1) {
            j = femaleEquip1;
            k = femaleEquip2;
            l = femaleEquip3;
        }
        if (j == -1) {
            return null;
        }
        Model model = Model.fetchModel(j);
        if (k != -1) {
            if (l != -1) {
                Model model_1 = Model.fetchModel(k);
                Model model_3 = Model.fetchModel(l);
                Model model_1s[] = {model, model_1, model_3};
                model = new Model(3, model_1s);
            } else {
                Model model_2 = Model.fetchModel(k);
                Model models[] = {model, model_2};
                model = new Model(2, models);
            }
        }
        //if (j == 62367)
        //model.translate(68, 7, -8);
        if (gender == 0 && maleYOffset != 0) {
            model.translate(0, maleYOffset, 0);
        } else if (gender == 1 && femaleYOffset != 0) {
            model.translate(0, femaleYOffset, 0);
        }
        if (editedModelColor != null && newModelColor != null) {
            for (int i1 = 0; i1 < editedModelColor.length; i1++) {
                model.recolour(editedModelColor[i1], newModelColor[i1]);
            }
        }

        applyTexturing(model, id);
        return model;
    }

    public void setDefaults() {
        untradeable = false;
        modelID = 0;
        name = null;
        description = null;
        editedModelColor = null;
        newModelColor = null;
        modelZoom = 2000;
        rotationY = 0;
        rotationX = 0;
        modelOffsetX = 0;
        modelOffset1 = 0;
        modelOffsetY = 0;
        stackable = false;
        value = 0;
        membersObject = false;
        groundActions = null;
        actions = null;
        maleEquip1 = -1;
        maleEquip2 = -1;
        maleYOffset = 0;
        maleXOffset = 0;
        femaleEquip1 = -1;
        femaleEquip2 = -1;
        femaleYOffset = 0;
        maleEquip3 = -1;
        femaleEquip3 = -1;
        maleDialogue = -1;
        maleDialogueModel = -1;
        femaleDialogue = -1;
        femaleDialogueModel = -1;
        stackIDs = null;
        stackAmounts = null;
        certID = -1;
        certTemplateID = -1;
        sizeX = 128;
        sizeY = 128;
        sizeZ = 128;
        shadow = 0;
        lightness = 0;
        team = 0;
        lendID = -1;
        lentItemID = -1;
    }

    public static void unpackConfig(CacheArchive streamLoader) {

        stream = new Stream(streamLoader.getDataForName("obj.dat"));
        Stream stream = new Stream(streamLoader.getDataForName("obj.idx"));
        totalItems = stream.readUnsignedWord();
        streamIndices = new int[totalItems + 1000];
        int i = 2;
        for (int j = 0; j < totalItems; j++) {
            streamIndices[j] = i;
            i += stream.readUnsignedWord();
        }
        cache = new ItemDefinition[10];
        for (int k = 0; k < 10; k++) {
            cache[k] = new ItemDefinition();
        }
        setSettings();
        priorityModels.add(65300);
        priorityModels.add(6639);
        priorityModels.add(6698);
        priorityModels.add(6681);

    }

    public static ItemDefinition forID(int i) {
        for (int j = 0; j < 10; j++) {
            if (cache[j].id == i) {
                if (Configuration.DISCO_ITEMS) {
                    if (i == 5572 || i == 5573 || i == 640 || i == 650 || i == 630) {
                        ItemDefinition.cache[j].newModelColor[0] = RandomColor.currentColour;
                    }
                }
                return cache[j];
            }
        }
        cacheIndex = (cacheIndex + 1) % 10;
        ItemDefinition itemDef = cache[cacheIndex];
        if (i >= streamIndices.length) {
            itemDef.id = 1;
            itemDef.setDefaults();
            return itemDef;
        }
        for (int a : Configuration.ITEMS_WITH_BLACK) {
            if (itemDef.id == a) {
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 10;
                itemDef.newModelColor[0] = 0;
            }
        }
        stream.pos = streamIndices[i];
        itemDef.id = i;
        itemDef.setDefaults();
        itemDef.readValues(stream);
        if (itemDef.certTemplateID != -1) {
            itemDef.toNote();
        }
        if (itemDef.lentItemID != -1) {
            itemDef.toLend();
        }
        if (itemDef.id == i && itemDef.editedModelColor == null) {
            itemDef.editedModelColor = new int[1];
            itemDef.newModelColor = new int[1];
            itemDef.editedModelColor[0] = 0;
            itemDef.newModelColor[0] = 1;
        }
        if (untradeableItems.contains(itemDef.id)) {
            itemDef.untradeable = true;
        }
        itemDef.value = prices[itemDef.id];
        switch (i) {

            case 6570:
                itemDef.editedModelColor = new int[]{40};
                itemDef.newModelColor = new int[]{50};
                break;
            case 8421:
                itemDef.setDefaults();
                itemDef.name = "Dragonbone spirit shield";
                itemDef.modelZoom = 1616; // Model Zoom
                itemDef.maleEquip1 = 38941; // Male Equip 1
                itemDef.femaleEquip1 = 38941; // Male Equip 2
                itemDef.modelID = 38942; // Model ID
                itemDef.rotationY = 396; // Model Rotation 1
                itemDef.rotationX = 1050; // Model Rotation 2
                itemDef.modelOffset1 = -3; // Model Offset 1
                itemDef.modelOffsetY = 16; // Model Offset 2
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 13247:
                itemDef.modelID = 29240;
                itemDef.name = "Hellpuppy";
                itemDef.description = "It's a Hellpuppy.";
                itemDef.modelZoom = 1616;
                itemDef.rotationY = 3;
                itemDef.rotationX = 213;
                itemDef.groundActions = new String[]{null, null, null, null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 12655:
                itemDef.modelID = 28869;
                itemDef.name = "Pet kraken";
                itemDef.description = "It's a Pet kraken.";
                itemDef.modelZoom = 1744;
                itemDef.rotationY = 330;
                itemDef.rotationX = 1940;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 5022:
                itemDef.name = "AFK Ticket";
                break;
            case 18939:
                itemDef.modelID = 50674;
                itemDef.name = "Plat Minigame Token";
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 13020:
                itemDef.editedModelColor = new int[]{9};
                itemDef.newModelColor = new int[]{75};
                itemDef.modelZoom = 1400;
                itemDef.rotationX = 0;
                itemDef.modelID = 50674;
                itemDef.name = "Diamond Minigame Token";
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                break;
            case 19864:
                itemDef.name = "Battle Royal Token";
                break;
            case 20061:
                itemDef.modelID = 10247;
                itemDef.name = "Abyssal vine whip";
                itemDef.description = "Abyssal vine whip";
                itemDef.modelZoom = 848;
                itemDef.rotationY = 324;
                itemDef.rotationX = 1808;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = 38;
                itemDef.maleEquip1 = 10253;
                itemDef.femaleEquip1 = 10253;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                break;
            case 19960:
                itemDef.modelID = 64000;
                itemDef.name = "Vegeta Boots";
                itemDef.modelZoom = 848;
                itemDef.rotationY = 141;
                itemDef.rotationX = 141;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 64000;
                itemDef.femaleEquip1 = 64000;
                break;
            case 19970:
                itemDef.modelID = 64009;
                itemDef.name = "Goku boots";
                itemDef.modelZoom = 848;
                itemDef.rotationY = 141;
                itemDef.rotationX = 141;
                itemDef.modelOffset1 = -9;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 64009;
                itemDef.femaleEquip1 = 64009;
                break;
            case 621:
                itemDef.name = "Donor Ticket ($1)";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Claim";
                break;
            case 18998:
                itemDef.modelID = 64892;
                itemDef.name = "Sully boots";
                itemDef.modelZoom = 848;
                itemDef.rotationY = 141;
                itemDef.rotationX = 141;
                itemDef.modelOffset1 = -9;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 64892;
                itemDef.femaleEquip1 = 64892;
                break;

            case 14460:
                itemDef.modelID = 64936;
                itemDef.name = "Squidward boots";
                itemDef.modelZoom = 700;
                itemDef.rotationY = 141;
                itemDef.rotationX = 141;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 64936;
                itemDef.femaleEquip1 = 64936;
                break;
            case 20156:
                itemDef.modelID = 64887;
                itemDef.name = "Sora boots";
                itemDef.modelZoom = 848;
                itemDef.rotationY = 141;
                itemDef.rotationX = 141;
                itemDef.modelOffset1 = -9;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 64887;
                itemDef.femaleEquip1 = 64887;
                break;
            case 20136:
                itemDef.modelID = 41718;
                itemDef.name = "ryuk boots";
                itemDef.modelZoom = 848;
                itemDef.rotationY = 141;
                itemDef.rotationX = 141;
                itemDef.modelOffset1 = -9;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 41718;
                itemDef.femaleEquip1 = 41718;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 18852:
                itemDef.modelID = 64818;
                itemDef.name = "@gre@Solara's Boots";
                itemDef.modelZoom = 700;
                itemDef.rotationY = 141;
                itemDef.rotationX = 141;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 64818;
                itemDef.femaleEquip1 = 64818;
                break;
            case 20114:
                itemDef.editedModelColor = new int[]{56};
                itemDef.newModelColor = new int[]{18};
                itemDef.modelID = 64843;
                itemDef.name = "Simba Boots";
                itemDef.modelZoom = 848;
                itemDef.rotationY = 141;
                itemDef.rotationX = 141;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -12;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 64843;
                itemDef.femaleEquip1 = 64843;
                break;
            case 11111:
                itemDef.editedModelColor = new int[]{56};
                itemDef.newModelColor = new int[]{40};
                itemDef.modelID = 64843;
                itemDef.name = "Simba Boots";
                itemDef.modelZoom = 848;
                itemDef.rotationY = 141;
                itemDef.rotationX = 141;
                itemDef.modelOffset1 = -9;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 64843;
                itemDef.femaleEquip1 = 64843;
                break;
            case 18874:
                itemDef.modelID = 64022;
                itemDef.name = "Kevin's Boots";
                itemDef.modelZoom = 848;
                itemDef.rotationY = 141;
                itemDef.rotationX = 141;
                itemDef.modelOffset1 = -9;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 64022;
                itemDef.femaleEquip1 = 64022;
                break;
            case 14462:
                itemDef.modelID = 64937;
                itemDef.name = "Squidward gloves";
                itemDef.modelZoom = 700;
                itemDef.rotationX = 0;
                itemDef.rotationY = 300;
                itemDef.modelOffset1 = -2;
                itemDef.modelOffsetY = 3;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 64938;
                itemDef.femaleEquip1 = 64938;
                break;
            case 19000:
                itemDef.modelID = 64893;
                itemDef.name = "Sully gloves";
                itemDef.modelZoom = 830;
                itemDef.rotationX = 150;
                itemDef.rotationY = 536;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = 3;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 64894;
                itemDef.femaleEquip1 = 64894;
                break;
            case 20138:
                itemDef.modelID = 41719;
                itemDef.name = "Ryuk gloves";
                itemDef.modelZoom = 830;
                itemDef.rotationX = 150;
                itemDef.rotationY = 536;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = 3;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 41720;
                itemDef.femaleEquip1 = 42720;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 20116:
                itemDef.editedModelColor = new int[]{56};
                itemDef.newModelColor = new int[]{18};
                itemDef.modelID = 64841;
                itemDef.name = "Simba gloves";
                itemDef.modelZoom = 830;
                itemDef.rotationX = 150;
                itemDef.rotationY = 536;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = 3;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 64842;
                itemDef.femaleEquip1 = 64842;
                break;
            case 18872:
                itemDef.modelID = 64020;
                itemDef.name = "Blood Knight Gloves";
                itemDef.modelZoom = 830;
                itemDef.rotationX = 150;
                itemDef.rotationY = 536;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = 3;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleEquip1 = 64021;
                itemDef.femaleEquip1 = 64021;
                break;
            case 15249:
                itemDef.modelID = 42077;
                itemDef.name = "Deadly Robot PlateLegs";
                itemDef.description = "Deadly Robot plateLegs";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65423;
                itemDef.femaleEquip1 = 65423;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 15247:
                itemDef.modelID = 42079;
                itemDef.name = "Deadly Robot Wings";
                itemDef.description = "Deadly Robot Wings";
                itemDef.modelZoom = 1900;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65427;
                itemDef.femaleEquip1 = 65427;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 15248:
                itemDef.modelID = 42076;
                itemDef.name = "Deadly Robot PlateBody";
                itemDef.description = "Deadly Robot platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65422;
                itemDef.femaleEquip1 = 65422;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 15253:
                itemDef.modelID = 65425;
                itemDef.name = "Deadly Robot Gloves";
                itemDef.description = "Deadly Robot Gloves";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65425;
                itemDef.femaleEquip1 = 65425;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 15255:
                itemDef.modelID = 42078;
                itemDef.name = "Deadly Robot Head";
                itemDef.description = "Deadly Robot Head";
                itemDef.modelZoom = 700;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65424;
                itemDef.femaleEquip1 = 65424;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 15254:
                itemDef.modelID = 65426;
                itemDef.name = "Deadly Robot Boots";
                itemDef.description = "Deadly Robot Boots";
                itemDef.modelZoom = 700;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65426;
                itemDef.femaleEquip1 = 65426;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 79:
                itemDef.modelID = 42082;
                itemDef.name = "Dark Knight Helm";
                itemDef.description = "Dark Knight Helm";
                ItemDefinition itemDef72111239 = ItemDefinition.forID(1163);
                itemDef.modelOffsetY = itemDef72111239.modelOffsetY;
                itemDef.modelOffsetX = itemDef72111239.modelOffsetX;
                itemDef.modelOffsetY = itemDef72111239.modelOffsetY;
                itemDef.modelOffset1 = itemDef72111239.modelOffset1;
                itemDef.modelZoom = itemDef72111239.modelZoom;
                itemDef.rotationX = itemDef72111239.rotationX;
                itemDef.rotationY = itemDef72111239.rotationY;
                itemDef.maleEquip1 = 65522;
                itemDef.femaleEquip1 = 65522;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8520:
                itemDef.modelID = 65523;
                itemDef.name = "Shadow Whip";
                itemDef.description = "Shadow Whip";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65523;
                itemDef.femaleEquip1 = 65523;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 81:
                itemDef.modelID = 42080;
                itemDef.name = "Dark Knight Platebody";
                itemDef.description = "Dark Knight Platebody";
                ItemDefinition itemDef721112410 = ItemDefinition.forID(1069);
                itemDef.modelOffsetY = itemDef721112410.modelOffsetY;
                itemDef.modelOffsetX = itemDef721112410.modelOffsetX;
                itemDef.modelOffsetY = itemDef721112410.modelOffsetY;
                itemDef.modelOffset1 = itemDef721112410.modelOffset1;
                itemDef.modelZoom = itemDef721112410.modelZoom;
                itemDef.rotationX = itemDef721112410.rotationX;
                itemDef.rotationY = itemDef721112410.rotationY;
                itemDef.maleEquip1 = 65520;
                itemDef.femaleEquip1 = 65520;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 82:
                itemDef.modelID = 65524;
                itemDef.name = "Dark Knight Boots";
                itemDef.description = "Dark Knight Boots";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65524;
                itemDef.femaleEquip1 = 65524;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 83:
                itemDef.modelID = 42083;
                itemDef.name = "Dark Knight Wings";
                itemDef.description = "Dark Knight Wings";
                itemDef.modelOffsetY = 20;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelZoom = 2200;
                itemDef.rotationX = 1200;
                itemDef.rotationY = 400;
                itemDef.maleEquip1 = 65525;
                itemDef.femaleEquip1 = 65525;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8517:
                itemDef.modelID = 42084;
                itemDef.name = "Dark Knight Shield";
                itemDef.description = "Dark Knight Shield";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65526;
                itemDef.femaleEquip1 = 65526;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8518:
                itemDef.modelID = 42081;
                itemDef.name = "Dark Knight PlateLegs";
                itemDef.description = "Dark Knight PlateLegs";
                ItemDefinition itemDef721112412 = ItemDefinition.forID(1069);
                itemDef.modelOffsetY = itemDef721112412.modelOffsetY;
                itemDef.modelOffsetX = itemDef721112412.modelOffsetX;
                itemDef.modelOffsetY = itemDef721112412.modelOffsetY;
                itemDef.modelOffset1 = itemDef721112412.modelOffset1;
                itemDef.modelZoom = itemDef721112412.modelZoom;
                itemDef.rotationX = itemDef721112412.rotationX;
                itemDef.rotationY = itemDef721112412.rotationY;
                itemDef.maleEquip1 = 65521;
                itemDef.femaleEquip1 = 65521;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8666:
                itemDef.modelID = 42063;
                itemDef.name = "Chucky Doll";
                itemDef.description = "Its Chucky";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65527;
                itemDef.femaleEquip1 = 65527;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20660:
                itemDef.modelID = 65448;
                itemDef.name = "@mag@Amulet Of @yel@God @whi@Strength";
                itemDef.description = "Its Chucky";
                itemDef.modelZoom = 600;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65449;
                itemDef.femaleEquip1 = 65449;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20659:
                itemDef.modelID = 65450;
                itemDef.name = "@mag@Amulet Of @yel@God @red@Range";
                itemDef.description = "Its Chucky";
                itemDef.modelZoom = 700;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65451;
                itemDef.femaleEquip1 = 65451;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20658:
                itemDef.modelID = 65452;
                itemDef.name = "@mag@Amulet Of @yel@God @blu@Magic";
                itemDef.description = "Its Chucky";
                itemDef.modelZoom = 600;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65453;
                itemDef.femaleEquip1 = 65453;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20657:
                itemDef.modelID = 65454;
                itemDef.name = "@mag@Tri-Brid Amulet";
                itemDef.description = "Its Chucky";
                itemDef.modelZoom = 600;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 5;
                itemDef.maleEquip1 = 65455;
                itemDef.femaleEquip1 = 65455;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20656:
                itemDef.modelID = 65473;
                itemDef.name = "@yel@Thanos Gauntlets @blu@(AOE)";
                itemDef.description = "Thanos's Gauntles";
                itemDef.modelZoom = 750;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65474;
                itemDef.femaleEquip1 = 65474;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 13080:
                itemDef.modelID = 50704;
                itemDef.name = "@yel@Thanos Gauntlets Infused @blu@(AOE)";
                itemDef.description = "Thanos's Gauntles Infused";
                itemDef.modelZoom = 750;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 50705;
                itemDef.femaleEquip1 = 50705;
                itemDef.groundActions = new String[5];
                itemDef.stackable = false;
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 13084:
                itemDef.editedModelColor = new int[]{54};
                itemDef.newModelColor = new int[]{52};
                itemDef.modelID = 50704;
                itemDef.name = "@yel@Thanos Gauntlets Upgraded (i) @blu@(AOE)";
                itemDef.description = "Thanos's Gauntles Infused";
                itemDef.modelZoom = 750;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 50705;
                itemDef.femaleEquip1 = 50705;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8860:
                itemDef.modelID = 65431;
                itemDef.name = "Cannonbolt Hood";
                itemDef.description = "";
                itemDef.modelZoom = 600;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -10;
                itemDef.maleEquip1 = 65432;
                itemDef.femaleEquip1 = 65432;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8861:
                itemDef.modelID = 65433;
                itemDef.name = "Cannonbolt Body";
                itemDef.description = "";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65434;
                itemDef.femaleEquip1 = 65434;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8862:
                itemDef.modelID = 65435;
                itemDef.name = "Cannonbolt Legs";
                itemDef.description = "";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65436;
                itemDef.femaleEquip1 = 65436;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8863:
                itemDef.modelID = 65437;
                itemDef.name = "Red Assassin Hood";
                itemDef.description = "";
                itemDef.modelZoom = 600;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -10;
                itemDef.maleEquip1 = 65438;
                itemDef.femaleEquip1 = 65438;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8864:
                itemDef.modelID = 65439;
                itemDef.name = "Red Assassin Body";
                itemDef.description = "";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65440;
                itemDef.femaleEquip1 = 65440;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8865:
                itemDef.modelID = 65441;
                itemDef.name = "Red Assassin Legs";
                itemDef.description = "";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65442;
                itemDef.femaleEquip1 = 65442;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8871:
                itemDef.modelID = 65444;
                itemDef.name = "@red@Tatical @whi@MP5 @gre@(AOE)";
                itemDef.description = "";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65445;
                itemDef.femaleEquip1 = 65445;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8653:
                itemDef.modelID = 65467;
                itemDef.name = "Spiderman Head";
                itemDef.description = "";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65467;
                itemDef.femaleEquip1 = 65467;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8654:
                itemDef.modelID = 65468;
                itemDef.name = "Spiderman Legs";
                itemDef.description = "";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65468;
                itemDef.femaleEquip1 = 65468;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8655:
                itemDef.modelID = 65469;
                itemDef.name = "Spiderman Body";
                itemDef.description = "";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65469;
                itemDef.femaleEquip1 = 65469;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8656:
                itemDef.modelID = 65534;
                itemDef.name = "Its Head";
                itemDef.description = "";
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelZoom = 700;
                itemDef.rotationX = 0;
                itemDef.rotationY = 500;
                itemDef.maleEquip1 = 45323;
                itemDef.femaleEquip1 = 45323;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8657:
                itemDef.modelID = 65530;
                itemDef.name = "Its Body";
                itemDef.description = "";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65531;
                itemDef.femaleEquip1 = 65531;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8658:
                itemDef.modelID = 65532;
                itemDef.name = "Its Gloves";
                itemDef.description = "";
                itemDef.modelZoom = 700;
                itemDef.rotationY = 150;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65533;
                itemDef.femaleEquip1 = 65533;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8659:
                itemDef.modelID = 45319;
                itemDef.name = "Its Legs";
                itemDef.description = "";
                itemDef.modelZoom = 1700;
                itemDef.rotationX = 0;
                itemDef.rotationY = 500;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                itemDef.maleEquip1 = 45320;
                itemDef.femaleEquip1 = 45320;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 8660:
                itemDef.modelID = 65528;
                itemDef.name = "Its Boots";
                itemDef.description = "";
                itemDef.modelZoom = 750;
                itemDef.rotationY = 0;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65529;
                itemDef.femaleEquip1 = 65529;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 12437:
                itemDef.modelID = 28648;
                itemDef.name = "3rd age cloak";
                itemDef.description = "3rd age cloak";
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 282;
                itemDef.rotationX = 962;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 28483;
                itemDef.femaleEquip1 = 28560;
                itemDef.maleXOffset = -3;
                itemDef.maleYOffset = -3;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{null, "Wear", null, null, null, null};
                break;
            case 12282:
                itemDef.name = "Serpentine helm";
                itemDef.modelID = 19220;
                itemDef.modelZoom = 700;
                itemDef.rotationX = 1724;
                itemDef.rotationY = 215;
                itemDef.modelOffsetX = 17;
                itemDef.femaleEquip1 = 14398;
                itemDef.maleEquip1 = 14395;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 12279:
                itemDef.name = "Magma helm";
                itemDef.modelID = 29205;
                itemDef.modelZoom = 700;
                itemDef.rotationX = 1724;
                itemDef.rotationY = 215;
                itemDef.modelOffsetX = 17;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.femaleEquip1 = 14426;
                itemDef.maleEquip1 = 14424;
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 12278:
                itemDef.name = "Tanzanite helm";
                itemDef.modelID = 29213;
                itemDef.modelZoom = 700;
                itemDef.rotationX = 1724;
                itemDef.rotationY = 215;
                itemDef.modelOffsetX = 17;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.femaleEquip1 = 23994;
                itemDef.maleEquip1 = 14421;
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case -1:
                itemDef.name = "Pet King black dragon";
                ItemDefinition itemDef2 = ItemDefinition.forID(12458);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                break;
            case 13239:
                itemDef.name = "Primordial boots";
                itemDef.modelID = 29397;
                itemDef.modelZoom = 976;
                itemDef.rotationY = 147;
                itemDef.rotationX = 279;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = 5;
                itemDef.maleEquip1 = 29250;
                itemDef.femaleEquip1 = 29255;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                break;
            case 12708:
                itemDef.name = "Pegasian boots";
                itemDef.modelID = 29396;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.groundActions = new String[5];
                itemDef.groundActions[1] = "Take";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 165;
                itemDef.rotationX = 279;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY = -7;
                itemDef.maleEquip1 = 29252;
                itemDef.femaleEquip1 = 29253;
                break;
            case 13235:
                itemDef.name = "Eternal boots";
                itemDef.modelID = 29394;
                itemDef.modelZoom = 976;
                itemDef.rotationY = 147;
                itemDef.rotationX = 279;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = 5;
                itemDef.maleEquip1 = 29249;
                itemDef.femaleEquip1 = 29254;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                break;
            case 19670:
                itemDef.name = "Vote scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef.actions[2] = "Claim-All";
                break;
            case 6821:
                itemDef.name = "Magic Coin Orb";
                break;
            case 13727:
                itemDef.actions = new String[]{null, null, null, null, "Drop"};
                break;
            case 6500:
                itemDef.setDefaults();
                itemDef.imitate(forID(9952));
                itemDef.name = "Charming imp";
                itemDef.stackable = false;
                itemDef.actions = new String[]{null, null, "Check", "Config", "Drop"};
                break;
            case 13045:
                itemDef.name = "Abyssal bludgeon";
                itemDef.modelZoom = 2611;
                itemDef.rotationY = 552;
                itemDef.rotationX = 1508;
                itemDef.modelOffsetY = 3;
                itemDef.modelOffset1 = -17;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check", "Uncharge", "Drop"};
                itemDef.modelID = 29597;
                itemDef.maleEquip1 = 29424;
                itemDef.femaleEquip1 = 29424;
                break;
            case 13047:
                itemDef.name = "Abyssal dagger";
                itemDef.modelZoom = 1347;
                itemDef.rotationY = 1589;
                itemDef.rotationX = 781;
                itemDef.modelOffsetY = 3;
                itemDef.modelOffset1 = -5;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", "Check", "Uncharge", "Drop"};
                itemDef.modelID = 29598;
                itemDef.maleEquip1 = 29425;
                itemDef.femaleEquip1 = 29425;
                break;
            case 500:
                itemDef.modelID = 2635;
                itemDef.name = "Black Party Hat";
                itemDef.description = "Black Party Hat";
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1845;
                itemDef.rotationY = 121;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 1;
                itemDef.stackable = false;
                itemDef.value = 1;
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor = new int[]{926};
                break;
            case 12284:
                itemDef.name = "Toxic staff of the dead";
                itemDef.modelID = 19224;
                itemDef.modelZoom = 2150;
                itemDef.rotationX = 1010;
                itemDef.rotationY = 512;
                itemDef.femaleEquip1 = 14402;
                itemDef.maleEquip1 = 49001;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[2] = "Check";
                itemDef.actions[4] = "Uncharge";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.editedModelColor = new int[1];
                itemDef.editedModelColor[0] = 17467;
                itemDef.newModelColor = new int[1];
                itemDef.newModelColor[0] = 21947;
                break;
            case 18689:
                itemDef.name = "Donor Chest Key #1";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Teleport";
                itemDef.actions[4] = "Drop";
                itemDef.description = "This takes me to the Donor Chest Zone!";
                break;
            case 19645:
                itemDef.name = "Donor Chest Key #2";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Teleport";
                itemDef.actions[4] = "Drop";
                itemDef.description = "This takes me to the Donor Chest Zone!";
                break;
            case 18665:
                itemDef.name = "Donor Chest Key #3";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Teleport";
                itemDef.actions[4] = "Drop";
                itemDef.description = "This takes me to the Donor Chest Zone!";
                break;
            case 18647:
                itemDef.name = "Donor Chest Key #4";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Teleport";
                itemDef.actions[4] = "Drop";
                itemDef.description = "This takes me to the Donor Chest Zone!";
                break;
            case 14471:
                itemDef.name = "Donor Chest Key #5";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Teleport";
                itemDef.actions[4] = "Drop";
                itemDef.description = "This takes me to the Donor Chest Zone!";
                break;
            case 2714:
                itemDef.name = "Casket";
                break;
            case 6183:
                itemDef.name = "@red@Donation Box";
                break;
            case 12632:
                itemDef.name = "100m Note";
                itemDef.actions = new String[]{"Claim", null, null, null, "Drop"};
                break;
            case 4202:
                itemDef.name = "Ring of Coins";
                break;
            case 2568:
                itemDef.name = "Ring of Wealthier";
                break;

            case 12601:
                itemDef.name = "Ring of the gods";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 393;
                itemDef.rotationX = 1589;
                itemDef.modelOffset1 = -9;
                itemDef.modelOffsetY = -12;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                itemDef.modelID = 33009;
                break;
            case 12603:
                itemDef.name = "Tyrannical ring";
                itemDef.modelZoom = 592;
                itemDef.rotationY = 285;
                itemDef.rotationX = 1163;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                itemDef.modelID = 28823;
                break;
            case 11613:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 13701;
                itemDef.modelZoom = 1560;
                itemDef.rotationY = 344;
                itemDef.rotationX = 1104;
                itemDef.modelOffsetY = -14;
                itemDef.modelOffsetX = 0;
                itemDef.maleEquip1 = 13700;
                itemDef.femaleEquip1 = 13700;
                itemDef.name = "Dragon Kiteshield";
                itemDef.description = "A Dragon Kiteshield!";
                break;
            case 4151:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.name = "Abyssal whip";
                itemDef.description = "Lowest powered whip";
                itemDef.modelID = 5412;//Inv & Ground
                itemDef.modelZoom = 840;
                itemDef.rotationY = 280;
                itemDef.rotationX = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 56;
                itemDef.maleEquip1 = 5409;//Male Wield View
                itemDef.femaleEquip1 = 5409;//Female Wield View
                break;
            case 11614:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 3288;
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 500;
                itemDef.rotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 20;
                itemDef.maleEquip1 = 3287;
                itemDef.femaleEquip1 = 3287;
                itemDef.name = "Death Cape";
                break;
            case 11995:
                itemDef.name = "Pet Chaos elemental";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 1284;
                itemDef.rotationX = 175;
                itemDef.rotationY = 0;
                itemDef.modelID = 40852;
                itemDef.modelOffset1 = -66;
                itemDef.modelOffsetY = 75;
                itemDef.modelOffsetX = 1939;
                break;
            case 11996:
                itemDef.name = "Pet King black dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 2000;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 40858;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11896:
                itemDef.name = "Beginner Digimon Pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 2500;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 64803;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;

            case 18985:
                itemDef.name = "Yoda Pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 3500;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 65443;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;

            case 18984:
                itemDef.name = "Thanos Pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 6500;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 65482;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 2542:
                itemDef.name = "Darth Vader Pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 2500;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 41610;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 18987:
                itemDef.name = "Charizard Pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 4500;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 64814;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;

            case 18989:
                itemDef.name = "Squirtle Pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 4500;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 41399;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;

            case 18986:
                itemDef.name = "Pikachu Pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 2500;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 41402;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;

            case 19868:
                itemDef.name = "Vegeta Pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 3500;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 64798;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 1685:
                itemDef.name = "Heli Pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 2500;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 41386;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11948:
                itemDef.name = "Evil Sayian";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 2500;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 41322;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11949:
                itemDef.name = "Donald Trump";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 3200;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 41010;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 15256:
                itemDef.name = "Deadly Robot Pet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 2500;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 64798;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11997:
                itemDef.name = "Pet General graardor";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 1872;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.modelID = 40853;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11978:
                itemDef.name = "Pet TzTok-Jad";
                itemDef.groundActions = new String[]{null, null, null, null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 10000;
                itemDef.rotationX = 553;
                itemDef.rotationY = 0;
                itemDef.modelID = 40854;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = -30;
                itemDef.modelOffsetX = 0;
                break;
            case 12001:
                itemDef.name = "Pet Corporeal beast";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 10000;
                itemDef.rotationX = 553;
                itemDef.rotationY = 0;
                itemDef.modelID = 40955;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = -30;
                itemDef.modelOffsetX = 0;
                break;
            case 12002:
                itemDef.name = "Pet Kree'arra";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 976;
                itemDef.rotationX = 1892;
                itemDef.rotationY = 2042;
                itemDef.modelID = 40855;
                itemDef.modelOffset1 = -20;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 12003:
                itemDef.name = "Pet K'ril tsutsaroth";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 1168;
                itemDef.rotationX = 0;
                itemDef.rotationY = 2012;
                itemDef.modelID = 40856;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 12004:
                itemDef.name = "Pet Commander zilyana";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 1000;
                itemDef.rotationX = 1931;
                itemDef.rotationY = 9;
                itemDef.modelID = 40857;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 12005:
                itemDef.name = "Pet Dagannoth supreme";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 4560;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 9941;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 12006:
                itemDef.name = "Pet Dagannoth prime";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 4560;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 9941;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                itemDef.newModelColor = new int[]{5931, 1688, 21530, 21534};
                itemDef.editedModelColor = new int[]{11930, 27144, 16536, 16540};
                break;
            case 11990:
                itemDef.name = "Pet Dagannoth rex";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 4560;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 9941;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                itemDef.newModelColor = new int[]{7322, 7326, 10403, 2595};
                itemDef.editedModelColor = new int[]{16536, 16540, 27144, 2477};
                break;
            case 11991:
                itemDef.name = "Pet Frost dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 5060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 56767;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11992:
                itemDef.name = "Pet Tormented demon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 5060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 44733;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11993:
                itemDef.name = "Pet Kalphite queen";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 24597;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11994:
                itemDef.name = "Pet Slash bash";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 46141;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 18338:
                itemDef.name = "Goodie bag";
                itemDef.actions = new String[]{"Open", null, "Open-all", null, "Drop"};
                break;
            case 9850:
                itemDef.name = "Goku Package";
                itemDef.actions = new String[]{"Open", null, null, null, "Drop"};
                break;
            case 11989:
                itemDef.name = "Pet Phoenix";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 45412;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11988:
                itemDef.name = "Pet Bandos avatar";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 6060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 46058;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11987:
                itemDef.name = "Pet Nex";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 5000;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 62717;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11986:
                itemDef.name = "Pet Jungle strykewyrm";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 51852;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11985:
                itemDef.name = "Pet Desert strykewyrm";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 51848;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11984:
                itemDef.name = "Pet Ice strykewyrm";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 7060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 51847;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11983:
                itemDef.name = "Pet Green dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 5060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 49142;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11982:
                itemDef.name = "Pet Baby blue dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 3060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 57937;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11981:
                itemDef.name = "Pet Blue dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 5060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 49137;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11979:
                itemDef.name = "Pet Black dragon";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 5060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 14294;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;

            case 11967:
                itemDef.name = "Pet Skotizo";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 13000;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 31653;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11969:
                itemDef.name = "Pet Lizardman Shaman";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 8060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 4039;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11970:
                itemDef.name = "Pet WildyWyrm";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 6060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 63604;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11971:
                itemDef.name = "Pet Bork";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 6560;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 40590;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11972:
                itemDef.name = "Pet Barrelchest";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 5560;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 22790;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11973:
                itemDef.name = "Pet Abyssal Sire";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 12060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 29477;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11974:
                itemDef.name = "Pet Rock Crab";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 2060;
                itemDef.rotationX = 1868;
                itemDef.rotationY = 2042;
                itemDef.modelID = 4400;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                break;
            case 11975:
                itemDef.name = "Pet Scorpia";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 3347;
                itemDef.rotationX = 189;
                itemDef.rotationY = 121;
                itemDef.modelID = 28293;
                itemDef.modelOffset1 = 12;
                itemDef.modelOffsetY = -100;
                itemDef.modelOffsetX = 0;
                break;

            case 11976:
                itemDef.name = "Pet Venenatis";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 4080;
                itemDef.rotationX = 67;
                itemDef.rotationY = 67;
                itemDef.modelID = 28294;
                itemDef.modelOffset1 = 9;
                itemDef.modelOffsetY = -4;
                itemDef.modelOffsetX = 0;
                break;
            case 14667:
                itemDef.name = "Zombie fragment";
                itemDef.modelID = ItemDefinition.forID(14639).modelID;
                break;
            case 15182:
                itemDef.actions[0] = "Bury";
                break;
            case 15084:
                itemDef.actions[0] = "Roll";
                itemDef.name = "Dice (up to 100)";
                itemDef2 = ItemDefinition.forID(15098);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                break;
            case 2996:
                itemDef.name = "Agility ticket";
                break;
            case 10025:
                itemDef.name = "Charm Box";
                itemDef.actions = new String[]{"Open", null, null, null, null};
                break;
            case 1389:
                itemDef.name = "Staff";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                break;
            case 1390:
                itemDef.name = "Staff";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                break;
            case 8465:
                itemDef.name = "Red slayer helmet";
                itemDef.description = "You really don't want to wear it inside-out.";
                itemDef.modelID = 20861;
                itemDef.maleEquip1 = 20862;
                itemDef.femaleEquip1 = 20862;
                itemDef.modelZoom = 750;
                itemDef.actions = new String[]{null, "Wear", "Revert", null, "Drop"};
                itemDef.groundActions = new String[]{null, null, "Take", null, null,};
                itemDef.rotationX = 1743;
                itemDef.rotationY = 69;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = -3;

                break;

            case 8467:
                itemDef.name = "Green slayer helmet";
                itemDef.description = "You really don't want to wear it inside-out.";
                itemDef.modelID = 20859;
                itemDef.maleEquip1 = 20860;
                itemDef.femaleEquip1 = 20860;
                itemDef.modelZoom = 750;
                itemDef.actions = new String[]{null, "Wear", "Revert", null, "Drop"};
                itemDef.groundActions = new String[]{null, null, "Take", null, null,};
                itemDef.rotationX = 1743;
                itemDef.rotationY = 69;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = -3;

                break;
            case 8469:
                itemDef.name = "Black slayer helmet";
                itemDef.description = "You really don't want to wear it inside-out.";
                itemDef.modelID = 20863;
                itemDef.maleEquip1 = 20864;
                itemDef.femaleEquip1 = 20864;
                itemDef.modelZoom = 750;
                itemDef.actions = new String[]{null, "Wear", "Revert", null, "Drop"};
                itemDef.groundActions = new String[]{null, null, "Take", null, null,};
                itemDef.rotationX = 1743;
                itemDef.rotationY = 69;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = -3;

                break;
            case 18876:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 64023;
                itemDef.modelOffsetY = 20;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelZoom = 2200;
                itemDef.rotationX = 1200;
                itemDef.rotationY = 400;
                itemDef.maleEquip1 = 64024;
                itemDef.femaleEquip1 = 64024;
                itemDef.stackable = false;
                itemDef.name = "Blood Knight Cape";
                itemDef.description = "Master attack cape";
                break;

            case 13327:
                itemDef.name = "Olmlet";
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, null, "Summon", null, "Drop"};
                itemDef.modelZoom = 968;
                itemDef.rotationX = 1778;
                itemDef.rotationY = 67;
                itemDef.modelID = 32798;
                itemDef.modelOffsetY = 16;
                itemDef.modelOffset1 = 1;
                break;

            case 11884:
                itemDef.actions = new String[]{"Open", null, null, null, null, null};
                break;

            case 14428:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Saradomin brew flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14427;
                itemDef.certTemplateID = 799;
                break;
            case 14427:
                itemDef.setDefaults();
                itemDef.groundActions = new String[5];
                itemDef.name = "Saradomin brew flask (6)";
                itemDef.stackable = false;
                itemDef.description = "6 doses of saradomin brew.";
                itemDef.modelZoom = 804;
                itemDef.rotationX = 131;
                itemDef.rotationY = 198;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffset1 = -1;
                itemDef.newModelColor = new int[]{10939};
                itemDef.editedModelColor = new int[]{33715};
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[]{"Drink", null, null, null, null, "Drop"};
                itemDef.modelID = 61732;
                itemDef.lightness = 200;
                itemDef.shadow = 40;
                break;
            case 14426:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Saradomin brew flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14425;
                itemDef.certTemplateID = 799;
                break;
            case 14424:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Saradomin brew flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14423;
                itemDef.certTemplateID = 799;
                break;
            case 14422:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Saradomin brew flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14421;
                itemDef.certTemplateID = 799;
                break;
            case 14420:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Saradomin brew flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14419;
                itemDef.certTemplateID = 799;
                break;
            case 14418:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Saradomin brew flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14417;
                itemDef.certTemplateID = 799;
                break;
            case 14416:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super restore flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14415;
                itemDef.certTemplateID = 799;
                break;
            case 14414:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super restore flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14413;
                itemDef.certTemplateID = 799;
                break;
            case 14412:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super restore flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14411;
                itemDef.certTemplateID = 799;
                break;
            case 14410:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super restore flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14409;
                itemDef.certTemplateID = 799;
                break;
            case 14408:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super restore flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14407;
                itemDef.certTemplateID = 799;
                break;
            case 14406:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super restore flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14405;
                itemDef.certTemplateID = 799;
                break;
            case 14404:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Magic flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14403;
                itemDef.certTemplateID = 799;
                break;
            case 14402:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Magic flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14401;
                itemDef.certTemplateID = 799;
                break;
            case 14400:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Magic flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14399;
                itemDef.certTemplateID = 799;
                break;
            case 14398:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Magic flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14397;
                itemDef.certTemplateID = 799;
                break;
            case 14396:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Magic flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14395;
                itemDef.certTemplateID = 799;
                break;
            case 14394:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Magic flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14393;
                itemDef.certTemplateID = 799;
                break;
            case 14189:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super attack flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14188;
                itemDef.certTemplateID = 799;
                break;
            case 14187:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super attack flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14186;
                itemDef.certTemplateID = 799;
                break;
            case 14185:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super attack flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14184;
                itemDef.certTemplateID = 799;
                break;
            case 14183:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super attack flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14182;
                itemDef.certTemplateID = 799;
                break;
            case 14181:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super attack flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14180;
                itemDef.certTemplateID = 799;
                break;
            case 14179:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super attack flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14178;
                itemDef.certTemplateID = 799;
                break;
            case 14177:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super strength flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14176;
                itemDef.certTemplateID = 799;
                break;
            case 14175:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super strength flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14174;
                itemDef.certTemplateID = 799;
                break;
            case 14173:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super strength flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14172;
                itemDef.certTemplateID = 799;
                break;
            case 14171:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super strength flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14170;
                itemDef.certTemplateID = 799;
                break;
            case 14169:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super strength flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14168;
                itemDef.certTemplateID = 799;
                break;
            case 14167:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Super strength flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14166;
                itemDef.certTemplateID = 799;
                break;
            case 14153:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Ranging flask (6)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14152;
                itemDef.certTemplateID = 799;
                break;
            case 14151:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Ranging flask (5)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14150;
                itemDef.certTemplateID = 799;
                break;
            case 14149:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Ranging flask (4)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14148;
                itemDef.certTemplateID = 799;
                break;
            case 14147:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Ranging flask (3)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14146;
                itemDef.certTemplateID = 799;
                break;
            case 14145:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Ranging flask (2)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14144;
                itemDef.certTemplateID = 799;
                break;
            case 14143:
                itemDef.setDefaults();
                itemDef.modelID = 2429;
                itemDef.name = "Ranging flask (1)";
                itemDef.actions = new String[]{null, null, null, null, null, null};
                itemDef.modelZoom = 760;
                itemDef.rotationX = 28;
                itemDef.rotationY = 552;
                itemDef.modelOffsetX = itemDef.modelOffset1 = 0;
                itemDef.stackable = true;
                itemDef.certID = 14142;
                itemDef.certTemplateID = 799;
                break;
            case 4705:
                itemDef.modelID = 6701;
                itemDef.name = "Abyssal vine whip";
                itemDef.description = "A weapon from the Abyss, interlaced with a vicious jade vine.";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 324;
                itemDef.rotationX = 1808;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 3;
                itemDef.maleEquip1 = 6700;
                itemDef.femaleEquip1 = 6700;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                break;
            case 15262:
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.actions[2] = "Open-All";
                break;

            case 4155:
                itemDef.name = "Slayer gem";
                itemDef.actions = new String[]{"Activate", null, "Social-Slayer", null, "Destroy"};
                break;
            case 13663:
                itemDef.name = "Stat reset cert.";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Open";
                break;
            case 13653:
                itemDef.name = "Energy fragment";
                break;
            case 14044:
                itemDef.name = "Black Party Hat";
                itemDef.modelID = 2635;
                itemDef.description = "A rare black party hat";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 0;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14045:
                itemDef.name = "Lime Party Hat";
                itemDef.modelID = 2635;
                itemDef.description = "A rare lime party hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 17350;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14046:
                itemDef.name = "Pink Party Hat";
                itemDef.modelID = 2635;
                itemDef.description = "A rare pink party hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 57300;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14047:
                itemDef.name = "Sky Blue Party Hat";
                itemDef.modelID = 2635;
                itemDef.description = "A rare sky blue party hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 689484;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14048:
                itemDef.name = "Lava Party Hat";
                itemDef.modelID = 2635;
                itemDef.description = "A rare lava party hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 6073;
                itemDef.modelZoom = 440;
                itemDef.rotationX = 1852;
                itemDef.rotationY = 76;
                itemDef.modelOffsetX = 1;
                itemDef.modelOffsetY = 1;
                itemDef.maleEquip1 = 187;
                itemDef.femaleEquip1 = 363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14049:
                itemDef.name = "Pink Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare pink santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 57300;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14050:
                itemDef.name = "Black Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare black santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 0;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14051:
                itemDef.name = "Lime Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare lime santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 17350;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14052:
                itemDef.name = "Lava Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare lava santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 6073;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 14053:
                itemDef.name = "Lava Santa Hat";
                itemDef.modelID = 2537;
                itemDef.description = "A rare lava santa hat!";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 6073;
                itemDef.modelZoom = 540;
                itemDef.rotationX = 136;
                itemDef.rotationY = 72;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 189;
                itemDef.femaleEquip1 = 366;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 19111:
                itemDef.name = "TokHaar-Kal";
                itemDef.value = 60000;
                itemDef.maleEquip1 = 62575;
                itemDef.femaleEquip1 = 62582;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.modelOffset1 = -4;
                itemDef.modelID = 62592;
                itemDef.stackable = false;
                itemDef.description = "A cape made of ancient, enchanted rocks.";
                itemDef.modelZoom = 1616;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelOffset1 = 0;
                itemDef.rotationY = 339;
                itemDef.rotationX = 192;
                break;
            case 13262:
                itemDef2 = ItemDefinition.forID(20072);
                itemDef.modelID = itemDef2.modelID;
                itemDef.maleEquip1 = itemDef2.maleEquip1;
                itemDef.femaleEquip1 = itemDef2.femaleEquip1;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.rotationY = itemDef2.rotationY;
                itemDef.rotationX = itemDef2.rotationX;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.name = itemDef2.name;
                itemDef.actions = itemDef2.actions;
                break;
            case 10942:
                itemDef.name = "$10 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDefinition.forID(761);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                break;
            case 10934:
                itemDef.name = "$20 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDefinition.forID(761);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                break;
            case 10935:
                itemDef.name = "$50 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDefinition.forID(761);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                break;
            case 10943:
                itemDef.name = "$100 Scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef2 = ItemDefinition.forID(761);
                itemDef.modelID = itemDef2.modelID;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelZoom = itemDef2.modelZoom;
                break;
            case 995:
                itemDef.name = "Coins";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[3] = "Add-to-pouch";
                break;
            case 17291:
                itemDef.name = "Blood necklace";

                itemDef.actions = new String[]{null, "Wear", null, null, null, null};
                break;
            case 20084:
                itemDef.name = "Golden Maul";
                break;
            case 6199:
                itemDef.name = "Mystery Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 15501:
                itemDef.name = "Legendary Mystery Box";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 14019:
                itemDef.modelID = 65262;
                itemDef.name = "Max Cape";
                itemDef.description = "A cape worn by those who've achieved 99 in all skills.";
                itemDef.modelZoom = 1385;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                itemDef.maleEquip1 = 65300;
                itemDef.femaleEquip1 = 65322;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Customize";
                itemDef.editedModelColor = new int[4];
                itemDef.newModelColor = new int[4];
                itemDef.editedModelColor[0] = 65214; //red
                itemDef.editedModelColor[1] = 65200; // darker red
                itemDef.editedModelColor[2] = 65186; //dark red
                itemDef.editedModelColor[3] = 62995; //darker red
                itemDef.newModelColor[0] = 65214;//cape
                itemDef.newModelColor[1] = 65200;//cape
                itemDef.newModelColor[2] = 65186;//outline
                itemDef.newModelColor[3] = 62995;//cape
                break;
            case 14020:
                itemDef.name = "Veteran hood";
                itemDef.description = "A hood worn by Chivalry's veterans.";
                itemDef.modelZoom = 760;
                itemDef.rotationY = 11;
                itemDef.rotationX = 81;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -3;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                itemDef.modelID = 65271;
                itemDef.maleEquip1 = 65289;
                itemDef.femaleEquip1 = 65314;
                break;
            case 14021:
                itemDef.modelID = 65261;
                itemDef.name = "Veteran Cape";
                itemDef.description = "A cape worn by Chivalry's veterans.";
                itemDef.modelZoom = 760;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                itemDef.maleEquip1 = 65305;
                itemDef.femaleEquip1 = 65318;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 14022:
                itemDef.modelID = 65270;
                itemDef.name = "Completionist Cape";
                itemDef.description = "We'd pat you on the back, but this cape would get in the way.";
                itemDef.modelZoom = 1385;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 24;
                itemDef.rotationY = 279;
                itemDef.rotationX = 948;
                itemDef.maleEquip1 = 65297;
                itemDef.femaleEquip1 = 65297;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Customize";
                itemDef.editedModelColor = new int[4];
                itemDef.newModelColor = new int[4];
                itemDef.editedModelColor[0] = 65214; //red
                itemDef.editedModelColor[1] = 65200; // darker red
                itemDef.editedModelColor[2] = 65186; //dark red
                itemDef.editedModelColor[3] = 62995; //darker red
                itemDef.newModelColor[0] = 65214;//cape
                itemDef.newModelColor[1] = 65200;//cape
                itemDef.newModelColor[2] = 65186;//outline
                itemDef.newModelColor[3] = 62995;//cape
                break;
            case 14003:
                itemDef.name = "Robin hood hat";
                itemDef.modelID = 3021;
                itemDef.editedModelColor = new int[3];
                itemDef.newModelColor = new int[3];
                itemDef.editedModelColor[0] = 15009;
                itemDef.newModelColor[0] = 30847;
                itemDef.editedModelColor[1] = 17294;
                itemDef.newModelColor[1] = 32895;
                itemDef.editedModelColor[2] = 15252;
                itemDef.newModelColor[2] = 30847;
                itemDef.modelZoom = 650;
                itemDef.rotationY = 2044;
                itemDef.rotationX = 256;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 3378;
                itemDef.femaleEquip1 = 3382;
                itemDef.maleDialogue = 3378;
                itemDef.femaleDialogue = 3382;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20000:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 53835;
                itemDef.name = "Steadfast boots";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 165;
                itemDef.rotationX = 99;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -7;
                itemDef.maleEquip1 = 53327;
                itemDef.femaleEquip1 = 53643;
                itemDef.description = "A pair of Steadfast boots.";
                break;

            case 20001:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 53828;
                itemDef.name = "Glaiven boots";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 165;
                itemDef.rotationX = 99;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -7;
                itemDef.femaleEquip1 = 53309;
                itemDef.maleEquip1 = 53309;
                itemDef.description = "A pair of Glaiven boots.";
                break;

            case 20002:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.description = "A pair of Ragefire boots.";
                itemDef.modelID = 53897;
                itemDef.name = "Ragefire boots";
                itemDef.modelZoom = 900;
                itemDef.rotationY = 165;
                itemDef.rotationX = 99;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -7;
                itemDef.maleEquip1 = 53330;
                itemDef.femaleEquip1 = 53651;
                break;

            case 14018:
                itemDef.modelID = 42036;
                itemDef.name = "Ornate katana";
                ItemDefinition itemDef81113 = ItemDefinition.forID(13477);
                itemDef.modelZoom = 4200;
                itemDef.rotationY = 400;
                itemDef.rotationX = 230;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 100;
                itemDef.value = 50000;
                itemDef.membersObject = true;
                itemDef.maleEquip1 = 5324;
                itemDef.femaleEquip1 = 5324;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Destroy";
                break;
            case 14008:
                itemDef.modelID = 62714;
                itemDef.name = "Torva full helm";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 62738;
                itemDef.femaleEquip1 = 62754;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 18890:
                itemDef.modelID = 64027;
                itemDef.name = "Shadow Knight Full Helmet";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 64028;
                itemDef.femaleEquip1 = 64028;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 19972:
                itemDef.modelID = 64014;
                itemDef.name = "Goku Head";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 64010;
                itemDef.femaleEquip1 = 64010;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 6663:
                itemDef.modelID = 64905;
                itemDef.name = "Al-Kharid FullHelmet";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 64906;
                itemDef.femaleEquip1 = 64906;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 6665:
                itemDef.modelID = 64911;
                itemDef.name = "Kounai Cape";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 64912;
                itemDef.femaleEquip1 = 64912;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 19980:
                itemDef.modelID = 64895;
                itemDef.name = "Sully FullHelmet";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 64896;
                itemDef.femaleEquip1 = 64896;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 6667:
                itemDef.modelID = 64913;
                itemDef.name = "Kounai FullHelmet";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 64914;
                itemDef.femaleEquip1 = 64914;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 18854:
                itemDef.modelID = 64821;
                itemDef.name = "Solara's Fullhelm";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 64822;
                itemDef.femaleEquip1 = 64822;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 20120:
                itemDef.modelID = 64848;
                itemDef.name = "Abominable Fullhelm";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 64846;
                itemDef.femaleEquip1 = 64847;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 20140:
                itemDef.modelID = 41713;
                itemDef.name = "Ryuk Fullhelm";
                itemDef.description = "Torva full helm";
                ItemDefinition itemDef72 = ItemDefinition.forID(1127);
                itemDef.groundActions = itemDef72.groundActions;
                itemDef.actions = itemDef72.actions;
                itemDef.modelOffsetY = itemDef72.modelOffsetY;
                itemDef.modelOffsetX = itemDef72.modelOffsetX;
                itemDef.modelOffsetY = itemDef72.modelOffsetY;
                itemDef.modelOffset1 = itemDef72.modelOffset1;
                itemDef.modelZoom = itemDef72.modelZoom;
                itemDef.rotationX = itemDef72.rotationX;
                itemDef.rotationY = itemDef72.rotationY;
                itemDef.maleEquip1 = 41713;
                itemDef.femaleEquip1 = 41713;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 20110:
                itemDef.editedModelColor = new int[]{56};
                itemDef.newModelColor = new int[]{18};
                itemDef.modelID = 64835;
                itemDef.name = "Simba Fullhelm";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 64836;
                itemDef.femaleEquip1 = 64836;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 18850:
                itemDef.modelID = 64008;
                itemDef.name = "Ice Wizard Hat";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 64009;
                itemDef.femaleEquip1 = 64009;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 18866:
                itemDef.modelID = 64793;
                itemDef.name = "Goku Fullhelm";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 64792;
                itemDef.femaleEquip1 = 64792;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 18900:
                itemDef.modelID = 64033;
                itemDef.name = "Squidward Full Helm";
                itemDef.description = "Torva full helm";
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelZoom = 600;
                itemDef.rotationX = 0;
                itemDef.rotationY = 400;
                itemDef.maleEquip1 = 64034;
                itemDef.femaleEquip1 = 64034;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 18942:
                itemDef.modelID = 65006;
                itemDef.name = "Sauron Full Helm";
                itemDef.description = "Sauron full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 65007;
                itemDef.femaleEquip1 = 65007;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 18944:
                itemDef.modelID = 65012;
                itemDef.name = "Ice Demon FullHelmet";
                itemDef.description = "Torva full helm";
                ItemDefinition itemDef721112 = ItemDefinition.forID(1163);
                itemDef.modelOffsetY = itemDef721112.modelOffsetY;
                itemDef.modelOffsetX = itemDef721112.modelOffsetX;
                itemDef.modelOffsetY = itemDef721112.modelOffsetY;
                itemDef.modelOffset1 = itemDef721112.modelOffset1;
                itemDef.modelZoom = itemDef721112.modelZoom;
                itemDef.rotationX = itemDef721112.rotationX;
                itemDef.rotationY = 300;
                itemDef.maleEquip1 = 65013;
                itemDef.femaleEquip1 = 65013;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 20654:
                itemDef.modelID = 65518;
                itemDef.name = "Heat Blast Helm";
                itemDef.description = "A Custom Item";
                ItemDefinition itemDef72111290 = ItemDefinition.forID(1163);
                itemDef.modelOffsetY = itemDef72111290.modelOffsetY;
                itemDef.modelOffsetX = itemDef72111290.modelOffsetX;
                itemDef.modelOffsetY = itemDef72111290.modelOffsetY;
                itemDef.modelOffset1 = itemDef72111290.modelOffset1;
                itemDef.modelZoom = itemDef72111290.modelZoom;
                itemDef.rotationX = itemDef72111290.rotationX;
                itemDef.rotationY = itemDef72111290.rotationY;
                itemDef.maleEquip1 = 65414;
                itemDef.femaleEquip1 = 65414;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 20653:
                itemDef.modelID = 65417;
                itemDef.name = "Heat Blast Legs";
                itemDef.description = "A Custom item";
                ItemDefinition itemDef72111298 = ItemDefinition.forID(1069);
                itemDef.modelOffsetY = itemDef72111298.modelOffsetY;
                itemDef.modelOffsetX = itemDef72111298.modelOffsetX;
                itemDef.modelOffsetY = itemDef72111298.modelOffsetY;
                itemDef.modelOffset1 = itemDef72111298.modelOffset1;
                itemDef.modelZoom = itemDef72111298.modelZoom;
                itemDef.rotationX = itemDef72111298.rotationX;
                itemDef.rotationY = itemDef72111298.rotationY;
                itemDef.maleEquip1 = 65519;
                itemDef.femaleEquip1 = 65519;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 20652:
                itemDef.modelID = 65415;
                itemDef.name = "Heat Blast Body";
                itemDef.description = "A custom item";
                ItemDefinition itemDef72111299 = ItemDefinition.forID(1069);
                itemDef.modelOffsetY = itemDef72111299.modelOffsetY;
                itemDef.modelOffsetX = itemDef72111299.modelOffsetX;
                itemDef.modelOffsetY = itemDef72111299.modelOffsetY;
                itemDef.modelOffset1 = itemDef72111299.modelOffset1;
                itemDef.modelZoom = itemDef72111299.modelZoom;
                itemDef.rotationX = itemDef72111299.rotationX;
                itemDef.rotationY = itemDef72111299.rotationY;
                itemDef.maleEquip1 = 65416;
                itemDef.femaleEquip1 = 65416;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 20651:
                itemDef.modelID = 65419;
                itemDef.name = "Heat Blast Gloves";
                itemDef.description = " A Custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 65420;
                itemDef.femaleEquip1 = 65420;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 20650:
                itemDef.modelID = 65421;
                itemDef.name = "Heat Blast Boots";
                itemDef.description = "A Custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 65421;
                itemDef.femaleEquip1 = 65421;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 18948:
                itemDef.modelID = 65023;
                itemDef.name = "Dragon Leather FullHelmet";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 65024;
                itemDef.femaleEquip1 = 65024;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10477:
                itemDef.modelID = 41003;
                itemDef.name = "Trainer Helm";
                itemDef.description = "Trainer helm";
                ItemDefinition itemDef8111 = ItemDefinition.forID(10286);
                itemDef.modelOffsetY = itemDef8111.modelOffsetY;
                itemDef.modelOffsetX = itemDef8111.modelOffsetX;
                itemDef.modelOffset1 = itemDef8111.modelOffset1;
                itemDef.modelZoom = itemDef8111.modelZoom;
                itemDef.rotationX = itemDef8111.rotationX;
                itemDef.rotationY = itemDef8111.rotationY;
                itemDef.maleEquip1 = 41027;
                itemDef.femaleEquip1 = 41027;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10478:
                itemDef.modelID = 41001;
                itemDef.name = "Trainer Platelegs";
                itemDef.description = "Trainer platelegs";
                ItemDefinition itemDef22222222 = ItemDefinition.forID(1079);
                itemDef.modelOffsetY = itemDef22222222.modelOffsetY;
                itemDef.modelOffsetX = itemDef22222222.modelOffsetX;
                itemDef.modelOffset1 = itemDef22222222.modelOffset1;
                itemDef.modelZoom = itemDef22222222.modelZoom;
                itemDef.rotationX = itemDef22222222.rotationX;
                itemDef.rotationY = itemDef22222222.rotationY;
                itemDef.maleEquip1 = 41026;
                itemDef.femaleEquip1 = 41026;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10479:
                itemDef.modelID = 41002;
                itemDef.name = "Trainer PlateBody";
                itemDef.description = "Trainer PlateBody";
                ItemDefinition itemDef222222221 = ItemDefinition.forID(1127);
                itemDef.modelOffsetY = itemDef222222221.modelOffsetY;
                itemDef.modelOffsetX = itemDef222222221.modelOffsetX;
                itemDef.modelOffset1 = itemDef222222221.modelOffset1;
                itemDef.modelZoom = itemDef222222221.modelZoom;
                itemDef.rotationX = itemDef222222221.rotationX;
                itemDef.rotationY = itemDef222222221.rotationY;
                itemDef.maleEquip1 = 41025;
                itemDef.femaleEquip1 = 41025;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10480:
                itemDef.modelID = 41004;
                itemDef.name = "Thors Ring";
                itemDef.description = "Thors Ring";
                ItemDefinition itemDef812 = ItemDefinition.forID(2550);
                itemDef.modelOffsetY = -7;
                itemDef.modelOffsetX = itemDef812.modelOffsetX;
                itemDef.modelOffset1 = itemDef812.modelOffset1;
                itemDef.modelZoom = 600;
                itemDef.rotationX = itemDef812.rotationX;
                itemDef.rotationY = itemDef812.rotationY;
                itemDef.maleEquip1 = 41004;
                itemDef.femaleEquip1 = 41004;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10482:
                itemDef.modelID = 41005;
                itemDef.name = "Golden Jordans";
                itemDef.description = "Golden Jordans";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41005;
                itemDef.femaleEquip1 = 41005;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10481:
                itemDef.modelID = 41146;
                itemDef.name = "Bob Marley Head";
                itemDef.description = "420 Noob";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41147;
                itemDef.femaleEquip1 = 41147;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 912:
                itemDef.modelID = 41144;
                itemDef.name = "Bob Marley Bong";
                itemDef.description = "420 Noob";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41145;
                itemDef.femaleEquip1 = 41145;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
            case 913:
                itemDef.modelID = 41148;
                itemDef.name = "Bob Marley Body";
                itemDef.description = "420 Noob";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41149;
                itemDef.femaleEquip1 = 41149;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 914:
                itemDef.modelID = 41150;
                itemDef.name = "Bob Marley Gloves";
                itemDef.description = "420 Noob";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41151;
                itemDef.femaleEquip1 = 41151;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 916:
                itemDef.modelID = 41154;
                itemDef.name = "Bob Marley Weed Cape";
                itemDef.description = "420 Noob";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41155;
                itemDef.femaleEquip1 = 41155;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 915:
                itemDef.modelID = 41156;
                itemDef.name = "Solara MBox";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                break;
            case 21055:
                itemDef.modelID = 50133;
                itemDef.name = "Solara Secret Speical";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.modelZoom = 2800;
                itemDef.rotationY = 230;
                itemDef.rotationX = 150;

                break;
            case 21056:
                itemDef.modelID = 50204;
                itemDef.name = "Owner Cape Mystery Chest";
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.modelZoom = 1800;
                itemDef.rotationY = 230;
                itemDef.rotationX = 150;
                break;
            case 917:
                itemDef.modelID = 41159;
                itemDef.name = "Supermans Head";
                itemDef.description = "420 Noob";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41160;
                itemDef.femaleEquip1 = 41160;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 918:
                itemDef.modelID = 41161;
                itemDef.name = "Superman Gloves";
                itemDef.description = "420 Noob";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41158;
                itemDef.femaleEquip1 = 41158;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 937:
                itemDef.modelID = 41180;
                itemDef.name = "@cya@Lightning Katana";
                itemDef.description = "420 Noob";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41181;
                itemDef.femaleEquip1 = 41181;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 938:
                itemDef.modelID = 42037;
                itemDef.name = "Lightning Hammer @red@(AOE)";
                itemDef.description = "AOE";
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 4;
                itemDef.modelOffset1 = 0;
                itemDef.modelZoom = 2500;
                itemDef.rotationX = 55;
                itemDef.rotationY = 350;
                itemDef.maleEquip1 = 41183;
                itemDef.femaleEquip1 = 41183;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 939:
                itemDef.modelID = 41184;
                itemDef.name = "Lightning hammer off-hand";
                itemDef.description = "420 Noob";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41184;
                itemDef.femaleEquip1 = 41184;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 940:
                itemDef.modelID = 41190;
                itemDef.name = "Thors Hammer";
                itemDef.description = "420 Noob";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41191;
                itemDef.femaleEquip1 = 41191;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 941:
                itemDef.modelID = 41297;
                itemDef.name = "Dope Boots";
                itemDef.description = "420 Noob";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41297;
                itemDef.femaleEquip1 = 41297;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 944:
                itemDef.modelID = 41298;
                itemDef.name = "Dope Gloves";
                itemDef.description = "420 Noob";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41339;
                itemDef.femaleEquip1 = 41339;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10483:
                itemDef.modelID = 41009;
                itemDef.name = "Bob Marley Boots";
                itemDef.description = "420 Noob";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41009;
                itemDef.femaleEquip1 = 41009;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10484:
                itemDef.modelID = 41012;
                itemDef.name = "Flaming Katana";
                itemDef.description = "Hot katana";
                itemDef.modelZoom = 1272;
                itemDef.rotationY = 250;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41013;
                itemDef.femaleEquip1 = 41013;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10502:
                itemDef.modelID = 41014;
                itemDef.name = "Lazer Powered Gun";
                itemDef.description = "Its a Custom item";
                itemDef.modelZoom = 1300;
                itemDef.rotationY = 100;
                itemDef.rotationX = 900;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41015;
                itemDef.femaleEquip1 = 41015;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10503:
                itemDef.modelID = 41016;
                itemDef.name = "Almata Katana";
                itemDef.description = "a custom katana";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41017;
                itemDef.femaleEquip1 = 41017;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10504:
                itemDef.modelID = 42055;
                itemDef.name = "Purple Dragon Cape";
                itemDef.description = "a custom Big fucking Gun";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41232;
                itemDef.femaleEquip1 = 41232;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10597:
                itemDef.modelID = 41192;
                itemDef.name = "IronMan Gloves";
                itemDef.description = "a custom Big fucking Gun";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41193;
                itemDef.femaleEquip1 = 41193;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10598:
                itemDef.modelID = 41196;
                itemDef.name = "Unforgiven Cape";
                itemDef.description = "a custom Big fucking Gun";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41197;
                itemDef.femaleEquip1 = 41197;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10599:
                itemDef.modelID = 41199;
                itemDef.name = "100m Bag";
                itemDef.description = "100m Bag";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41199;
                itemDef.femaleEquip1 = 41199;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10682:
                itemDef.modelID = 41200;
                itemDef.name = "Blood Torva Helm";
                itemDef.description = "Custom Armour";
                ItemDefinition itemDef7211124 = ItemDefinition.forID(1163);
                itemDef.modelOffsetY = itemDef7211124.modelOffsetY;
                itemDef.modelOffsetX = itemDef7211124.modelOffsetX;
                itemDef.modelOffsetY = itemDef7211124.modelOffsetY;
                itemDef.modelOffset1 = itemDef7211124.modelOffset1;
                itemDef.modelZoom = itemDef7211124.modelZoom;
                itemDef.rotationX = itemDef7211124.rotationX;
                itemDef.rotationY = itemDef7211124.rotationY;
                itemDef.maleEquip1 = 41201;
                itemDef.femaleEquip1 = 41201;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10683:
                itemDef.modelID = 41202;
                itemDef.name = "Blood Torva PlateBody";
                itemDef.description = "Custom Torva PlateBody";
                ItemDefinition itemDef72111245 = ItemDefinition.forID(1069);
                itemDef.modelOffsetY = itemDef72111245.modelOffsetY;
                itemDef.modelOffsetX = itemDef72111245.modelOffsetX;
                itemDef.modelOffsetY = itemDef72111245.modelOffsetY;
                itemDef.modelOffset1 = itemDef72111245.modelOffset1;
                itemDef.modelZoom = itemDef72111245.modelZoom;
                itemDef.rotationX = itemDef72111245.rotationX;
                itemDef.rotationY = itemDef72111245.rotationY;
                itemDef.maleEquip1 = 41206;
                itemDef.femaleEquip1 = 41206;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10684:
                itemDef.modelID = 41203;
                itemDef.name = "Blood Torva PlateLegs";
                itemDef.description = "Custom Torva";
                ItemDefinition itemDef72111246 = ItemDefinition.forID(1069);
                itemDef.modelOffsetY = itemDef72111246.modelOffsetY;
                itemDef.modelOffsetX = itemDef72111246.modelOffsetX;
                itemDef.modelOffsetY = itemDef72111246.modelOffsetY;
                itemDef.modelOffset1 = itemDef72111246.modelOffset1;
                itemDef.modelZoom = itemDef72111246.modelZoom;
                itemDef.rotationX = itemDef72111246.rotationX;
                itemDef.rotationY = itemDef72111246.rotationY;
                itemDef.maleEquip1 = 41204;
                itemDef.femaleEquip1 = 41204;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10685:
                itemDef.modelID = 41207;
                itemDef.name = "Blood Torva Boots";
                itemDef.description = "Custom Torva";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41207;
                itemDef.femaleEquip1 = 41207;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10822:
                itemDef.modelID = 41208;
                itemDef.name = "Legolas Boots";
                itemDef.description = "Custom Range";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41208;
                itemDef.femaleEquip1 = 41208;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 11208:
                itemDef.modelID = 41226;
                itemDef.name = "Legolas PlateBody";
                itemDef.description = "a custom Big fucking Gun";
                ItemDefinition itemDef721 = ItemDefinition.forID(1127);
                itemDef.modelOffsetY = itemDef721.modelOffsetY;
                itemDef.modelOffsetX = itemDef721.modelOffsetX;
                itemDef.modelOffset1 = itemDef721.modelOffset1;
                itemDef.modelZoom = itemDef721.modelZoom;
                itemDef.rotationX = itemDef721.rotationX;
                itemDef.rotationY = itemDef721.rotationY;
                itemDef.maleEquip1 = 41227;
                itemDef.femaleEquip1 = 41227;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 10824:
                itemDef.modelID = 41228;
                itemDef.name = "Legolas PlateLegs";
                itemDef.description = "a custom Big fucking Gun";
                ItemDefinition itemDef7211 = ItemDefinition.forID(1069);
                itemDef.modelOffsetY = itemDef7211.modelOffsetY;
                itemDef.modelOffsetX = itemDef7211.modelOffsetX;
                itemDef.modelOffsetY = itemDef7211.modelOffsetY;
                itemDef.modelOffset1 = itemDef7211.modelOffset1;
                itemDef.modelZoom = itemDef7211.modelZoom;
                itemDef.rotationX = itemDef7211.rotationX;
                itemDef.rotationY = itemDef7211.rotationY;
                itemDef.maleEquip1 = 41229;
                itemDef.femaleEquip1 = 41229;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 11206:
                itemDef.modelID = 41224;
                itemDef.name = "Legolas Helm";
                itemDef.description = "a custom Big fucking Gun";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41225;
                itemDef.femaleEquip1 = 41225;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 10826:
                itemDef.modelID = 41209;
                itemDef.name = "Legolas Gloves";
                itemDef.description = "Custom item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41210;
                itemDef.femaleEquip1 = 41210;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 10991:
                itemDef.modelID = 41216;
                itemDef.name = "Rayquaza Gloves";
                itemDef.description = "Monster'z Gloves";
                itemDef.modelZoom = 700;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = -7;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41217;
                itemDef.femaleEquip1 = 41217;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 10992:
                itemDef.modelID = 41218;
                itemDef.name = "Rayquaza Boots";
                itemDef.description = "Monster'z Boots";
                itemDef.modelZoom = 700;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41218;
                itemDef.femaleEquip1 = 41218;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 10993:
                itemDef.modelID = 41219;
                itemDef.name = "Rayquaza PlateBody";
                itemDef.description = "Monster'z PlateBody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41336;
                itemDef.femaleEquip1 = 41336;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 10994:
                itemDef.modelID = 41220;
                itemDef.name = "Rayquaza Helm";
                itemDef.description = "Monster'z Helm";
                itemDef.modelOffsetY = -7;
                itemDef.modelOffset1 = 0;
                itemDef.modelZoom = 650;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.maleEquip1 = 41221;
                itemDef.femaleEquip1 = 41221;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 10995:
                itemDef.modelID = 41222;
                itemDef.name = "Rayquaza PlateLegs";
                itemDef.description = "Monster'z PlateLegs";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41223;
                itemDef.femaleEquip1 = 41223;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 10996:
                itemDef.modelID = 41233;
                itemDef.name = "Forbidden Pickaxes";
                itemDef.description = "Forbidden Pickaxes";
                itemDef.modelZoom = 2800;
                itemDef.rotationY = 400;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41234;
                itemDef.femaleEquip1 = 41234;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 10997:
                itemDef.modelID = 41235;
                itemDef.name = "Forbidden Helm";
                itemDef.description = "A Forbidden Piece";
                itemDef.modelZoom = 700;
                itemDef.rotationY = 50;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41236;
                itemDef.femaleEquip1 = 41236;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 10998:
                itemDef.modelID = 41237;
                itemDef.name = "Forbidden PlateBody";
                itemDef.description = "A Forbidden Piece";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41238;
                itemDef.femaleEquip1 = 41238;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 10999:
                itemDef.modelID = 41239;
                itemDef.name = "Forbidden Legs";
                itemDef.description = "A Forbidden Piece";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41240;
                itemDef.femaleEquip1 = 41240;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11000:
                itemDef.modelID = 41249;
                itemDef.name = "Tribrid-Boots";
                itemDef.description = "All Three Abilities in one";
                itemDef.modelZoom = 750;
                itemDef.rotationY = 0;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41249;
                itemDef.femaleEquip1 = 41249;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11039:
                itemDef.modelID = 41252;
                itemDef.name = "Aragorn Helm";
                itemDef.description = "A Tron Piece";
                ItemDefinition itemDef72111247 = ItemDefinition.forID(1163);
                itemDef.modelOffsetY = itemDef72111247.modelOffsetY;
                itemDef.modelOffsetX = itemDef72111247.modelOffsetX;
                itemDef.modelOffsetY = itemDef72111247.modelOffsetY;
                itemDef.modelOffset1 = itemDef72111247.modelOffset1;
                itemDef.modelZoom = itemDef72111247.modelZoom;
                itemDef.rotationX = itemDef72111247.rotationX;
                itemDef.rotationY = itemDef72111247.rotationY;
                itemDef.maleEquip1 = 41253;
                itemDef.femaleEquip1 = 41253;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11040:
                itemDef.modelID = 41254;
                itemDef.name = "Aragorn PlateBody";
                itemDef.description = "A Tron Piece";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41255;
                itemDef.femaleEquip1 = 41255;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 11041:
                itemDef.modelID = 41256;
                itemDef.name = "Aragorn PlateLegs";
                itemDef.description = "A Tron Piece";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41257;
                itemDef.femaleEquip1 = 41257;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 11042:
                itemDef.modelID = 41258;
                itemDef.name = "Aragorn Wings";
                itemDef.description = "A Tron Piece";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41259;
                itemDef.femaleEquip1 = 41259;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11142:
                itemDef.modelID = 41289;
                itemDef.name = "Leviathan Helm";
                itemDef.description = "A Leviathan Piece";
                itemDef.modelZoom = 800;
                itemDef.rotationY = 0;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 41290;
                itemDef.femaleEquip1 = 41290;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11143:
                itemDef.modelID = 41291;
                itemDef.name = "Leviathan PlateBody";
                itemDef.description = "A Leviathan Piece";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41292;
                itemDef.femaleEquip1 = 41292;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11144:
                itemDef.modelID = 41293;
                itemDef.name = "Leviathan Platelegs";
                itemDef.description = "A Leviathan Piece";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41294;
                itemDef.femaleEquip1 = 41294;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11145:
                itemDef.modelID = 41295;
                itemDef.name = "Leviathan Boots";
                itemDef.description = "A Leviathan Piece";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41295;
                itemDef.femaleEquip1 = 41295;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11146:
                itemDef.modelID = 41341;
                itemDef.name = "Leviathan Gloves";
                itemDef.description = "A Leviathan Piece";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41296;
                itemDef.femaleEquip1 = 41296;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11147:
                itemDef.modelID = 41269;
                itemDef.name = "Tiki Helm";
                itemDef.description = "A Greater Abyss Piece";
                itemDef.modelZoom = 1506;
                ItemDefinition itemDef72111248 = ItemDefinition.forID(1163);
                itemDef.modelOffsetY = itemDef72111248.modelOffsetY;
                itemDef.modelOffsetX = itemDef72111248.modelOffsetX;
                itemDef.modelOffsetY = itemDef72111248.modelOffsetY;
                itemDef.modelOffset1 = itemDef72111248.modelOffset1;
                itemDef.modelZoom = itemDef72111248.modelZoom;
                itemDef.rotationX = itemDef72111248.rotationX;
                itemDef.rotationY = itemDef72111248.rotationY;
                itemDef.maleEquip1 = 41270;
                itemDef.femaleEquip1 = 41270;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11148:
                itemDef.modelID = 41271;
                itemDef.name = "Tiki PlateBody";
                itemDef.description = "A Greater Abyss Piece";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41272;
                itemDef.femaleEquip1 = 41272;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11149:
                itemDef.modelID = 41273;
                itemDef.name = "Tiki Platelegs";
                itemDef.description = "A Greater Abyss Piece";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41274;
                itemDef.femaleEquip1 = 41274;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 10505:
                itemDef.modelID = 41031;
                itemDef.name = "Golden Double Knifes";
                itemDef.description = "a custom Knife";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41032;
                itemDef.femaleEquip1 = 41032;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10867:
                itemDef.modelID = 41033;
                itemDef.name = "@whi@White Samurai Hate";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41034;
                itemDef.femaleEquip1 = 41034;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10868:
                itemDef.modelID = 41035;
                itemDef.name = "@whi@White Samurai Legs";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41036;
                itemDef.femaleEquip1 = 41036;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10869:
                itemDef.modelID = 41037;
                itemDef.name = "@whi@White Samurai Body";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41038;
                itemDef.femaleEquip1 = 41038;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10900:
                itemDef.modelID = 41039;
                itemDef.name = "@bla@Black Samurai Helm";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41040;
                itemDef.femaleEquip1 = 41040;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 10901:
                itemDef.modelID = 41041;
                itemDef.name = "@bla@Black Samurai Legs";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41042;
                itemDef.femaleEquip1 = 41042;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 10902:
                itemDef.modelID = 41333;
                itemDef.name = "@bla@Black Samurai Body";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41043;
                itemDef.femaleEquip1 = 41043;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 10903:
                itemDef.modelID = 41044;
                itemDef.name = "@whi@White Boxing Gloves";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41045;
                itemDef.femaleEquip1 = 41045;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10905:
                itemDef.modelID = 41046;
                itemDef.name = "@ora@Orange Boxing Gloves";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41047;
                itemDef.femaleEquip1 = 41047;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10906:
                itemDef.modelID = 41048;
                itemDef.name = "@mag@Purple Boxing Gloves";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41049;
                itemDef.femaleEquip1 = 41049;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10907:
                itemDef.modelID = 41053;
                itemDef.name = "@whi@Ice Torva Helm";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41054;
                itemDef.femaleEquip1 = 41054;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10908:
                itemDef.modelID = 41055;
                itemDef.name = "@whi@Ice Torva Legs";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41056;
                itemDef.femaleEquip1 = 41056;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10946:
                itemDef.modelID = 41057;
                itemDef.name = "@whi@Ice Torva Whip";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41058;
                itemDef.femaleEquip1 = 41058;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 10947:
                itemDef.modelID = 41060;
                itemDef.name = "@whi@Ice Torva Wings";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41059;
                itemDef.femaleEquip1 = 41059;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 11004:
                itemDef.modelID = 41062;
                itemDef.name = "Extended Claws";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41061;
                itemDef.femaleEquip1 = 41061;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 11005:
                itemDef.modelID = 41063;
                itemDef.name = "God Rangers Ring";
                itemDef.description = "a custom Item";
                ItemDefinition itemDef82 = ItemDefinition.forID(2550);
                itemDef.modelOffsetY = itemDef82.modelOffsetY;
                itemDef.modelOffsetX = itemDef82.modelOffsetX;
                itemDef.modelOffset1 = itemDef82.modelOffset1;
                itemDef.modelZoom = itemDef82.modelZoom;
                itemDef.rotationX = itemDef82.rotationX;
                itemDef.rotationY = itemDef82.rotationY;
                itemDef.maleEquip1 = 41063;
                itemDef.femaleEquip1 = 41063;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 11067:
                itemDef.modelID = 41068;
                itemDef.name = "Storm Trooper PlateBody";
                itemDef.description = "a custom Item";
                ItemDefinition itemDef8 = ItemDefinition.forID(1127);
                itemDef.modelOffsetY = itemDef8.modelOffsetY;
                itemDef.modelOffsetX = itemDef8.modelOffsetX;
                itemDef.modelOffset1 = itemDef8.modelOffset1;
                itemDef.modelZoom = itemDef8.modelZoom;
                itemDef.rotationX = itemDef8.rotationX;
                itemDef.rotationY = itemDef8.rotationY;
                itemDef.maleEquip1 = 41067;
                itemDef.femaleEquip1 = 41067;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 9583;
                break;
            case 11068:
                itemDef.modelID = 41070;
                itemDef.name = "Storm Trooper PlateLegs";
                itemDef.description = "a custom Item";
                ItemDefinition itemDef823 = ItemDefinition.forID(1069);
                itemDef.modelOffsetY = itemDef823.modelOffsetY;
                itemDef.modelOffsetX = itemDef823.modelOffsetX;
                itemDef.modelOffset1 = itemDef823.modelOffset1;
                itemDef.modelZoom = itemDef823.modelZoom;
                itemDef.rotationX = itemDef823.rotationX;
                itemDef.rotationY = itemDef823.rotationY;
                itemDef.maleEquip1 = 41069;
                itemDef.femaleEquip1 = 41069;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 9583;
                break;
            case 11071:
                itemDef.modelID = 41072;
                itemDef.name = "Storm Trooper Helm";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41071;
                itemDef.femaleEquip1 = 41071;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 9583;
                break;
            case 11078:
                itemDef.modelID = 41074;
                itemDef.name = "Storm Trooper Gloves";
                itemDef.description = "a custom Item";
                ItemDefinition itemDef88 = ItemDefinition.forID(1580);
                itemDef.modelOffsetY = itemDef88.modelOffsetY;
                itemDef.modelOffsetX = itemDef88.modelOffsetX;
                itemDef.modelOffset1 = itemDef88.modelOffset1;
                itemDef.modelZoom = itemDef88.modelZoom;
                itemDef.rotationX = itemDef88.rotationX;
                itemDef.rotationY = itemDef88.rotationY;
                itemDef.maleEquip1 = 41073;
                itemDef.femaleEquip1 = 41073;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 11087:
                itemDef.modelID = 41075;
                itemDef.name = "Storm Trooper Boots";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41075;
                itemDef.femaleEquip1 = 41075;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 9583;
                break;
            case 11063:
                itemDef.modelID = 41064;
                itemDef.name = "@cya@Bling Amulet of Solara's";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 950;
                itemDef.rotationY = 400;
                itemDef.rotationX = 1050;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 41065;
                itemDef.femaleEquip1 = 41065;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 798:
                itemDef.modelID = 41115;
                itemDef.name = "@whi@Bad Bitch Body";
                itemDef.description = "a custom Item";
                ItemDefinition itemDef89 = ItemDefinition.forID(1127);
                itemDef.modelOffsetY = itemDef89.modelOffsetY;
                itemDef.modelOffsetX = itemDef89.modelOffsetX;
                itemDef.modelOffset1 = itemDef89.modelOffset1;
                itemDef.modelZoom = itemDef89.modelZoom;
                itemDef.rotationX = itemDef89.rotationX;
                itemDef.rotationY = itemDef89.rotationY;
                itemDef.maleEquip1 = 41105;
                itemDef.femaleEquip1 = 41105;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 799:
                itemDef.modelID = 41117;
                itemDef.name = "@whi@Bad Bitch Gloves";
                itemDef.description = "a custom Item";
                ItemDefinition itemDef6 = ItemDefinition.forID(1580);
                itemDef.modelOffsetY = itemDef6.modelOffsetY;
                itemDef.modelOffsetX = itemDef6.modelOffsetX;
                itemDef.modelOffset1 = itemDef6.modelOffset1;
                itemDef.modelZoom = itemDef6.modelZoom;
                itemDef.rotationX = itemDef6.rotationX;
                itemDef.rotationY = itemDef6.rotationY;
                itemDef.maleEquip1 = 41106;
                itemDef.femaleEquip1 = 41106;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 894:
                itemDef.modelID = 41116;
                itemDef.name = "@whi@Bad Bitch Head";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41107;
                itemDef.femaleEquip1 = 41107;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 895:
                itemDef.modelID = 41114;
                itemDef.name = "@whi@Bad Bitch Skirt";
                itemDef.description = "a custom Item";
                ItemDefinition itemDef811 = ItemDefinition.forID(1069);
                itemDef.modelOffsetY = itemDef811.modelOffsetY;
                itemDef.modelOffsetX = itemDef811.modelOffsetX;
                itemDef.modelOffset1 = itemDef811.modelOffset1;
                itemDef.modelZoom = itemDef811.modelZoom;
                itemDef.rotationX = itemDef811.rotationX;
                itemDef.rotationY = itemDef811.rotationY;
                itemDef.maleEquip1 = 41108;
                itemDef.femaleEquip1 = 41108;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 896:
                itemDef.modelID = 41109;
                itemDef.name = "@whi@Bad Bitch Boots";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41109;
                itemDef.femaleEquip1 = 41109;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 897:
                itemDef.modelID = 41120;
                itemDef.name = "@mag@Bikini Girl Body";
                itemDef.description = "a custom Item";
                ItemDefinition itemDef895 = ItemDefinition.forID(1127);
                itemDef.modelOffsetY = itemDef895.modelOffsetY;
                itemDef.modelOffsetX = itemDef895.modelOffsetX;
                itemDef.modelOffset1 = itemDef895.modelOffset1;
                itemDef.modelZoom = itemDef895.modelZoom;
                itemDef.rotationX = itemDef895.rotationX;
                itemDef.rotationY = itemDef895.rotationY;
                itemDef.maleEquip1 = 41111;
                itemDef.femaleEquip1 = 41111;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 898:
                itemDef.modelID = 41118;
                itemDef.name = "@mag@Bikini Girl Legs";
                itemDef.description = "a custom Item";
                ItemDefinition itemDef898 = ItemDefinition.forID(1127);
                itemDef.modelOffsetY = itemDef898.modelOffsetY;
                itemDef.modelOffsetX = itemDef898.modelOffsetX;
                itemDef.modelOffset1 = itemDef898.modelOffset1;
                itemDef.modelZoom = itemDef898.modelZoom;
                itemDef.rotationX = itemDef898.rotationX;
                itemDef.rotationY = itemDef898.rotationY;
                itemDef.maleEquip1 = 41110;
                itemDef.femaleEquip1 = 41110;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 899:
                itemDef.modelID = 41119;
                itemDef.name = "@mag@Bikini Girl Gloves";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41124;
                itemDef.femaleEquip1 = 41124;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 900:
                itemDef.modelID = 41122;
                itemDef.name = "@mag@Bikini Girl Head";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41113;
                itemDef.femaleEquip1 = 41113;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 901:
                itemDef.modelID = 41121;
                itemDef.name = "@mag@Bikini Girl Boots";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41121;
                itemDef.femaleEquip1 = 41121;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 902:
                itemDef.modelID = 41129;
                itemDef.name = "@cya@Mermaid Body";
                itemDef.description = "a custom Item";
                itemDef.modelOffsetY = 1;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelZoom = 1300;
                itemDef.rotationX = 0;
                itemDef.rotationY = 600;
                itemDef.maleEquip1 = 41125;
                itemDef.femaleEquip1 = 41125;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 903:
                itemDef.modelID = 41131;
                itemDef.name = "@mag@Mermaid Legs";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 1200;
                itemDef.rotationY = 400;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41123;
                itemDef.femaleEquip1 = 41123;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 904:
                itemDef.modelID = 41128;
                itemDef.name = "@mag@Mermaid Head";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41127;
                itemDef.femaleEquip1 = 41127;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 905:
                itemDef.modelID = 41130;
                itemDef.name = "@mag@Mermaid Gloves";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41124;
                itemDef.femaleEquip1 = 41124;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 906:
                itemDef.modelID = 50918;
                itemDef.name = "@mag@Mermaid boots";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 50918;
                itemDef.femaleEquip1 = 50918;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 18950:
                itemDef.editedModelColor = new int[]{52};
                itemDef.newModelColor = new int[]{68};
                itemDef.modelID = 65029;
                itemDef.name = "Magma FullHelmet";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65030;
                itemDef.femaleEquip1 = 65030;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 907:
                itemDef.modelID = 41133;
                itemDef.name = "@blu@Super@red@man Legs";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41132;
                itemDef.femaleEquip1 = 41132;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 908:
                itemDef.modelID = 41136;
                itemDef.name = "@blu@Super@red@man Body";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41134;
                itemDef.femaleEquip1 = 41134;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 909:
                itemDef.modelID = 41135;
                itemDef.name = "@blu@Super@red@man Boots";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41135;
                itemDef.femaleEquip1 = 41135;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 910:
                itemDef.modelID = 41141;
                itemDef.name = "Legends Cape";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41141;
                itemDef.femaleEquip1 = 41141;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 911:
                itemDef.modelID = 41142;
                itemDef.name = "@cya@Platinum Cape";
                itemDef.description = "a custom Item";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41142;
                itemDef.femaleEquip1 = 41142;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 18952:
                itemDef.modelID = 65035;
                itemDef.name = "Magma FullHelmet (u)";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 65036;
                itemDef.femaleEquip1 = 65036;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 18954:
                itemDef.modelID = 65041;
                itemDef.name = "Skeleton Cosmetic FullHelmet";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 65042;
                itemDef.femaleEquip1 = 65042;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 18956:
                itemDef.modelID = 65047;
                itemDef.name = "Ghost FullHelmet";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 65048;
                itemDef.femaleEquip1 = 65048;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 18902:
                itemDef.modelID = 64039;
                itemDef.name = "Earth Guardian FullHelmet";
                itemDef.description = "Torva full helm";
                itemDef.modelZoom = 672;
                itemDef.rotationY = 85;
                itemDef.rotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleEquip1 = 64040;
                itemDef.femaleEquip1 = 64040;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 4080:
                itemDef.modelID = 65301;
                itemDef.name = "@blu@Swifter's Sword";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65302;
                itemDef.femaleEquip1 = 65302;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 7080:
                itemDef.modelID = 65357;
                itemDef.name = "@cya@Royal sicle";
                itemDef.description = "Custom item";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65358;
                itemDef.femaleEquip1 = 65358;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 7081:
                itemDef.modelID = 65360;
                itemDef.name = "Starlight Sword";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.stackable = false;
                itemDef.maleEquip1 = 65359;
                itemDef.femaleEquip1 = 65359;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 7082:
                itemDef.modelID = 65361;
                itemDef.name = "@whi@White Mage Gloves";
                itemDef.description = "a custom item";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65362;
                itemDef.femaleEquip1 = 65362;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 7083:
                itemDef.modelID = 65367;
                itemDef.name = "@bla@DarkLight Sword";
                itemDef.description = "a custom item";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65363;
                itemDef.femaleEquip1 = 65363;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 7084:
                itemDef.modelID = 65369;
                itemDef.name = "@red@Rebel @whi@GodSword";
                itemDef.description = "a custom item";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65368;
                itemDef.femaleEquip1 = 65368;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 7085:
                itemDef.modelID = 65370;
                itemDef.name = "@yel@Golden Katana Offhand";
                itemDef.description = "a custom item";
                itemDef.stackable = false;
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65371;
                itemDef.femaleEquip1 = 65371;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11650:
                itemDef.modelID = 65372;
                itemDef.name = "@bla@RedLight Sword";
                itemDef.description = "a custom item";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65373;
                itemDef.femaleEquip1 = 65373;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11651:
                itemDef.modelID = 65374;
                itemDef.name = "@red@Blood Sycthe";
                itemDef.description = "a custom item";
                ItemDefinition itemDef85 = ItemDefinition.forID(13045);
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = itemDef85.modelOffsetX;
                itemDef.modelOffset1 = itemDef85.modelOffset1;
                itemDef.modelZoom = 3250;
                itemDef.rotationX = itemDef85.rotationX;
                itemDef.rotationY = 1350;
                itemDef.maleEquip1 = 65375;
                itemDef.femaleEquip1 = 65375;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11652:
                itemDef.modelID = 65376;
                itemDef.name = "@whi@Light Glaive";
                itemDef.description = "a custom item";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = -10;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65377;
                itemDef.femaleEquip1 = 65377;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11653:
                itemDef.modelID = 65381;
                itemDef.name = "@whi@Light Staff";
                itemDef.description = "a custom item";
                itemDef.modelZoom = 2300;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65382;
                itemDef.femaleEquip1 = 65382;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11654:
                itemDef.modelID = 65384;
                itemDef.name = "Gimlee Warhammer";
                itemDef.description = "a custom item";
                itemDef.modelZoom = 2000;
                itemDef.rotationY = 473;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65383;
                itemDef.femaleEquip1 = 65383;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19913:
                itemDef.modelID = 65340;
                itemDef.name = "@gre@Desert Eagle";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65341;
                itemDef.femaleEquip1 = 65341;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19914:
                itemDef.modelID = 65342;
                itemDef.name = "@gre@M19";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65343;
                itemDef.femaleEquip1 = 65343;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19915:
                itemDef.modelID = 65344;
                itemDef.name = "@gre@Ak-47";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65345;
                itemDef.femaleEquip1 = 65345;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19916:
                itemDef.modelID = 65346;
                itemDef.name = "@gre@Grey Ak-47";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1605;
                itemDef.rotationY = 573;
                itemDef.rotationX = 1500;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65347;
                itemDef.femaleEquip1 = 65347;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19918:
                itemDef.modelID = 65348;
                itemDef.name = "@red@AR-15";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1400;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65349;
                itemDef.femaleEquip1 = 65349;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19919:
                itemDef.modelID = 65000;
                itemDef.name = "@gre@MP5 Sub";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1000;
                itemDef.rotationY = 300;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65351;
                itemDef.femaleEquip1 = 65351;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19920:
                itemDef.modelID = 65353;
                itemDef.name = "@gre@Lazer Gun @gre@(AOE)";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 700;
                itemDef.rotationY = 250;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65354;
                itemDef.femaleEquip1 = 65354;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19921:
                itemDef.modelID = 65355;
                itemDef.name = "@gre@Lava AK-47";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65356;
                itemDef.femaleEquip1 = 65356;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11658:
                itemDef.modelID = 65396;
                itemDef.name = "Double-Ended Katana";
                itemDef.description = "a custom item";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 17350;
                itemDef.editedModelColor[0] = 926;
                itemDef.maleEquip1 = 65397;
                itemDef.femaleEquip1 = 65397;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 17916:
                itemDef.modelID = 65418;
                itemDef.name = "Mod Icon";
                itemDef.description = "a custom item";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41276;
                itemDef.femaleEquip1 = 41276;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11659:
                itemDef.modelID = 65398;
                itemDef.name = "@red@Blood Katana";
                itemDef.description = "a custom item";
                ItemDefinition itemDef811213 = ItemDefinition.forID(13477);
                itemDef.modelZoom = 1000;
                itemDef.rotationY = 750;
                itemDef.rotationX = 1;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65399;
                itemDef.femaleEquip1 = 65399;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11660:
                itemDef.modelID = 65400;
                itemDef.name = "Oak Staff";
                itemDef.description = "a custom item";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65401;
                itemDef.femaleEquip1 = 65401;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 4081:
                itemDef.modelID = 41338;
                itemDef.name = "@gre@BFG @red@Off-Hand";
                itemDef.description = "Big Fucking Gun";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41301;
                itemDef.femaleEquip1 = 41301;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 5082:
                itemDef.modelID = 65306;
                itemDef.name = "Sun God FullHelm";
                itemDef.description = "Torva FullHelm";
                ItemDefinition itemDef72111296 = ItemDefinition.forID(1163);
                itemDef.modelOffsetY = itemDef72111296.modelOffsetY;
                itemDef.modelOffsetX = itemDef72111296.modelOffsetX;
                itemDef.modelOffsetY = itemDef72111296.modelOffsetY;
                itemDef.modelOffset1 = itemDef72111296.modelOffset1;
                itemDef.modelZoom = itemDef72111296.modelZoom;
                itemDef.rotationX = itemDef72111296.rotationX;
                itemDef.rotationY = itemDef72111296.rotationY;
                itemDef.maleEquip1 = 65307;
                itemDef.femaleEquip1 = 65307;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 5083:
                itemDef.modelID = 65308;
                itemDef.name = "Sun God Platebody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65309;
                itemDef.femaleEquip1 = 65309;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 5084:
                itemDef.modelID = 65310;
                itemDef.name = "Sun God Platelegs";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1700;
                itemDef.rotationX = 0;
                itemDef.rotationY = 500;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                itemDef.maleEquip1 = 65311;
                itemDef.femaleEquip1 = 65311;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 5085:
                itemDef.modelID = 65312;
                itemDef.name = "Kevin's Platebody";
                itemDef.description = "Kevin's platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65313;
                itemDef.femaleEquip1 = 65313;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 5086:
                itemDef.modelID = 65314;
                itemDef.name = "Kevin's Platelegs";
                itemDef.description = "Kevin's Platelegs";
                itemDef.modelZoom = 1700;
                itemDef.rotationX = 0;
                itemDef.rotationY = 500;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                itemDef.maleEquip1 = 65315;
                itemDef.femaleEquip1 = 65315;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 5087:
                itemDef.modelID = 65316;
                itemDef.name = "Kevin's Full Helm";
                itemDef.description = "Kevin's Full Helm";
                ItemDefinition itemDef72111293 = ItemDefinition.forID(1163);
                itemDef.modelOffsetY = 2000;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = -10;
                itemDef.modelOffset1 = 0;
                itemDef.modelZoom = 700;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.maleEquip1 = 65317;
                itemDef.femaleEquip1 = 65317;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 5088:
                itemDef.modelID = 65318;
                itemDef.name = "Kevin's Gloves";
                itemDef.description = "Kevin's Gloves";
                itemDef.modelZoom = 706;
                itemDef.rotationY = 460;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65319;
                itemDef.femaleEquip1 = 65319;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 5089:
                itemDef.modelID = 65320;
                itemDef.name = "Kevin's Boots";
                itemDef.description = "Its A Custom";
                itemDef.modelZoom = 700;
                itemDef.rotationY = 100;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65320;
                itemDef.femaleEquip1 = 65320;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 5090:
                itemDef.modelID = 65321;
                itemDef.name = "Zeldorado Helm";
                itemDef.description = "Torva platebody";
                ItemDefinition itemDef72111297 = ItemDefinition.forID(1163);
                itemDef.modelOffsetY = itemDef72111297.modelOffsetY;
                itemDef.modelOffsetX = itemDef72111297.modelOffsetX;
                itemDef.modelOffsetY = itemDef72111297.modelOffsetY;
                itemDef.modelOffset1 = itemDef72111297.modelOffset1;
                itemDef.modelZoom = itemDef72111297.modelZoom;
                itemDef.rotationX = itemDef72111297.rotationX;
                itemDef.rotationY = itemDef72111297.rotationY;
                itemDef.maleEquip1 = 65322;
                itemDef.femaleEquip1 = 65322;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 5091:
                itemDef.modelID = 65323;
                itemDef.name = "Zeldorado platebody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65324;
                itemDef.femaleEquip1 = 65324;
                itemDef.sizeX = 128;
                itemDef.sizeY = 128;
                itemDef.sizeZ = 128;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 5092:
                itemDef.modelID = 65325;
                itemDef.name = "Zeldorado Platelegs";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65326;
                itemDef.femaleEquip1 = 65326;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19906:
                itemDef.modelID = 65327;
                itemDef.name = "Ironman Helm";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65328;
                itemDef.femaleEquip1 = 65328;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19908:
                itemDef.modelID = 65330;
                itemDef.name = "Ironman Platebody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65329;
                itemDef.femaleEquip1 = 65329;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19909:
                itemDef.modelID = 65332;
                itemDef.name = "Ironman Platelegs";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65331;
                itemDef.femaleEquip1 = 65331;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19910:
                itemDef.modelID = 65333;
                itemDef.name = "Ironman Boots";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65333;
                itemDef.femaleEquip1 = 65333;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 5081:
                itemDef.modelID = 65334;
                itemDef.name = "Eve helm";
                itemDef.description = "Eve helm";
                ItemDefinition itemDef72111 = ItemDefinition.forID(1163);
                itemDef.modelOffsetY = itemDef72111.modelOffsetY;
                itemDef.modelOffsetX = itemDef72111.modelOffsetX;
                itemDef.modelOffsetY = itemDef72111.modelOffsetY;
                itemDef.modelOffset1 = itemDef72111.modelOffset1;
                itemDef.modelZoom = itemDef72111.modelZoom;
                itemDef.rotationX = itemDef72111.rotationX;
                itemDef.rotationY = itemDef72111.rotationY;
                itemDef.maleEquip1 = 65335;
                itemDef.femaleEquip1 = 65335;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 5080:
                itemDef.modelID = 65336;
                itemDef.name = "Eve legs";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65337;
                itemDef.femaleEquip1 = 65337;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 5079:
                itemDef.modelID = 65338;
                itemDef.name = "Eve Body";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65339;
                itemDef.femaleEquip1 = 65339;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor = new int[]{926};
                break;
            case 19962:
                itemDef.modelID = 64008;
                itemDef.name = "Vegeta platebody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64007;
                itemDef.femaleEquip1 = 64007;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19964:
                itemDef.modelID = 64006;
                itemDef.name = "Vegeta platelegs";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64005;
                itemDef.femaleEquip1 = 64005;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 14464:
                itemDef.modelID = 64941;
                itemDef.name = "Squidward Bow";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64942;
                itemDef.femaleEquip1 = 64942;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18994:
                itemDef.modelID = 64897;
                itemDef.name = "Sully platelegs";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64898;
                itemDef.femaleEquip1 = 64898;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19974:
                itemDef.modelID = 64015;
                itemDef.name = "Goku platelegs";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64011;
                itemDef.femaleEquip1 = 64011;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19976:
                itemDef.modelID = 64013;
                itemDef.name = "Goku Platebody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64012;
                itemDef.femaleEquip1 = 64012;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18992:
                itemDef.modelID = 64899;
                itemDef.name = "Sully platebody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64900;
                itemDef.femaleEquip1 = 64900;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18990:
                itemDef.modelID = 64901;
                itemDef.name = "Sully Wings";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64902;
                itemDef.femaleEquip1 = 64902;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20158:
                itemDef.modelID = 64888;
                itemDef.name = "Sora Wings";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64889;
                itemDef.femaleEquip1 = 64889;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20160:
                itemDef.modelID = 64890;
                itemDef.name = "Sora Sword";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64891;
                itemDef.femaleEquip1 = 64891;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20142:
                itemDef.modelID = 41714;
                itemDef.name = "Ryuk platebody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41715;
                itemDef.femaleEquip1 = 41715;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 20144:
                itemDef.modelID = 41716;
                itemDef.name = "Ryuk platelegs";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41717;
                itemDef.femaleEquip1 = 41717;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 20146:
                itemDef.modelID = 64874;
                itemDef.name = "Ryuk Sword(Upgraded)";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64875;
                itemDef.femaleEquip1 = 64875;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 20122:
                itemDef.modelID = 64851;
                itemDef.name = "Abominable Platebody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64849;
                itemDef.femaleEquip1 = 64850;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.newModelColor = new int[1];
                itemDef.newModelColor[0] = 21947;
                break;
            case 20124:
                itemDef.modelID = 64854;
                itemDef.name = "Abominable Platelegs";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64852;
                itemDef.femaleEquip1 = 64853;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.newModelColor = new int[1];
                itemDef.newModelColor[0] = 21947;
                break;
            case 18856:
                itemDef.modelID = 64816;
                itemDef.name = "@gre@Solara's 2H Sword";
                itemDef.description = "Solara's 2H Sword";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64817;
                itemDef.femaleEquip1 = 64817;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18868:
                itemDef.modelID = 64819;
                itemDef.name = "@gre@Solara's Wings";
                itemDef.description = "Solara's 2H Sword";
                itemDef.modelOffsetY = 20;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelZoom = 2200;
                itemDef.rotationX = 1200;
                itemDef.rotationY = 400;
                itemDef.maleEquip1 = 64820;
                itemDef.femaleEquip1 = 64820;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20100:
                itemDef.modelID = 64830;
                itemDef.name = "Darth Maul Platebody";
                itemDef.description = "Solara's 2H Sword";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64829;
                itemDef.femaleEquip1 = 64829;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20106:
                itemDef.editedModelColor = new int[]{56};
                itemDef.newModelColor = new int[]{18};
                itemDef.modelID = 64837;
                itemDef.name = "Simba Platebody";
                itemDef.description = "Solara's 2H Sword";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64838;
                itemDef.femaleEquip1 = 64838;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20108:
                itemDef.editedModelColor = new int[]{56};
                itemDef.newModelColor = new int[]{18};
                itemDef.modelID = 64839;
                itemDef.name = "Simba Platelegs";
                itemDef.description = "Solara's 2H Sword";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64840;
                itemDef.femaleEquip1 = 64840;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20112:
                itemDef.editedModelColor = new int[]{56};
                itemDef.newModelColor = new int[]{18};
                itemDef.modelID = 64844;
                itemDef.name = "Simba Cape";
                itemDef.description = "Solara's 2H Sword";
                itemDef.modelOffsetY = 20;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelZoom = 2200;
                itemDef.rotationX = 1200;
                itemDef.rotationY = 400;
                itemDef.maleEquip1 = 64845;
                itemDef.femaleEquip1 = 64845;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20126:
                itemDef.modelID = 64855;
                itemDef.name = "Ryuk Cape";
                itemDef.description = "Solara's 2H Sword";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64856;
                itemDef.femaleEquip1 = 64856;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 20128:
                itemDef.modelID = 64857;
                itemDef.name = "Ryuk Shield";
                itemDef.description = "Ryuk Shield";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64858;
                itemDef.femaleEquip1 = 64858;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;

            case 20130:
                itemDef.modelID = 64859;
                itemDef.name = "Ryuk Amulet";
                itemDef.description = "Solara's 2H Sword";
                itemDef.modelZoom = 600;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 5;
                itemDef.maleEquip1 = 64860;
                itemDef.femaleEquip1 = 64860;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;

            case 20132:
                itemDef.modelID = 64861;
                itemDef.name = "Ryuk Ring";
                itemDef.description = "Solara's 2H Sword";
                itemDef.modelZoom = 700;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -10;
                itemDef.maleEquip1 = 64861;
                itemDef.femaleEquip1 = 64861;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;

            case 20134:
                itemDef.modelID = 64862;
                itemDef.name = "Ryuk Sword (UnUpgraded)";
                itemDef.description = "Solara's 2H Sword";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64863;
                itemDef.femaleEquip1 = 64863;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;

            case 20102:
                itemDef.modelID = 64831;
                itemDef.name = "Darth Maul Platelegs";
                itemDef.description = "Solara's 2H Sword";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64832;
                itemDef.femaleEquip1 = 64832;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;

            case 20104:
                itemDef.modelID = 64834;
                itemDef.name = "Darth Maul Fullhelm";
                itemDef.description = "Solara's 2H Sword";
                ItemDefinition itemDef7211129 = ItemDefinition.forID(1163);
                itemDef.modelOffsetY = itemDef7211129.modelOffsetY;
                itemDef.modelOffsetX = itemDef7211129.modelOffsetX;
                itemDef.modelOffsetY = itemDef7211129.modelOffsetY;
                itemDef.modelOffset1 = itemDef7211129.modelOffset1;
                itemDef.modelZoom = itemDef7211129.modelZoom;
                itemDef.rotationX = itemDef7211129.rotationX;
                itemDef.maleEquip1 = 64833;
                itemDef.femaleEquip1 = 64833;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;

            case 19990://thats Item ing id
                itemDef.modelID = 64799;//model inventory model id
                itemDef.name = "@red@Infernal Coin";
                itemDef.modelZoom = 1800;//zoom is pretty much the zoom you can change it
                itemDef.rotationY = 473;//rotation is side roations x/y
                itemDef.rotationX = 2035;
                itemDef.stackable = true;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Exchange";
                itemDef.actions[3] = "Add-to-pouch";
                break;
            case 19992://thats Item ing id
                itemDef.modelID = 64800;//model inventory model id
                itemDef.name = "@red@Diamond Coin";
                itemDef.modelZoom = 1800;//zoom is pretty much the zoom you can change it
                itemDef.rotationY = 473;//rotation is side roations x/y
                itemDef.rotationX = 2035;
                itemDef.stackable = true;
                itemDef.actions = new String[5];
                itemDef.actions[3] = "Add-to-pouch";
                break;
            case 19994://thats Item ing id
                itemDef.modelID = 64801;//model inventory model id
                itemDef.name = "@red@Ice Coin";
                itemDef.modelZoom = 1800;//zoom is pretty much the zoom you can change it
                itemDef.rotationY = 473;//rotation is side roations x/y
                itemDef.rotationX = 2035;
                itemDef.stackable = true;
                itemDef.actions = new String[5];
                itemDef.actions[3] = "Add-to-pouch";
                break;
            case 18904:
                itemDef.modelID = 64041;
                itemDef.name = "Earth Guardian PlateBody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64042;
                itemDef.femaleEquip1 = 64042;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18858:
                itemDef.modelID = 64823;
                itemDef.name = "Solara's Gloves";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 700;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64824;
                itemDef.femaleEquip1 = 64824;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19958:
                itemDef.modelID = 64002;
                itemDef.name = "Vegeta Gloves";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 500;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64001;
                itemDef.femaleEquip1 = 64001;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19978:
                itemDef.modelID = 64017;
                itemDef.name = "Goku Gloves";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64016;
                itemDef.femaleEquip1 = 64016;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18860:
                itemDef.modelID = 64825;
                itemDef.name = "Solara's Platebody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64826;
                itemDef.femaleEquip1 = 64826;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;

            case 18862:
                itemDef.modelID = 64010;
                itemDef.name = "Ice Wizard PlateBody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64011;
                itemDef.femaleEquip1 = 64011;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18898:
                itemDef.modelID = 64035;
                itemDef.name = "Squidward PlateBody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64036;
                itemDef.femaleEquip1 = 64036;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18926:
                itemDef.modelID = 64934;
                itemDef.name = "X PlateBody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64935;
                itemDef.femaleEquip1 = 64935;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18928:
                itemDef.modelID = 64931;
                itemDef.name = "X Platelegs";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64932;
                itemDef.femaleEquip1 = 64932;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18930:
                itemDef.modelID = 64927;
                itemDef.name = "X Cape";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 64928;
                itemDef.femaleEquip1 = 64928;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 14010:
                itemDef.modelID = 62701;
                itemDef.name = "Torva platelegs";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 62743;
                itemDef.femaleEquip1 = 62760;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20150:
                itemDef.modelID = 64881;
                itemDef.name = "Sora Fullhelm";
                itemDef.description = "Torva platelegs";
                ItemDefinition itemDef7211121 = ItemDefinition.forID(1163);
                itemDef.modelOffsetY = itemDef7211121.modelOffsetY;
                itemDef.modelOffsetX = itemDef7211121.modelOffsetX;
                itemDef.modelOffsetY = itemDef7211121.modelOffsetY;
                itemDef.modelOffset1 = itemDef7211121.modelOffset1;
                itemDef.modelZoom = itemDef7211121.modelZoom;
                itemDef.rotationX = itemDef7211121.rotationX;
                itemDef.rotationY = itemDef7211121.rotationY;
                itemDef.maleEquip1 = 64882;
                itemDef.femaleEquip1 = 64882;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 19956:
                itemDef.modelID = 64004;
                itemDef.name = "Vegeta Head";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 850;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 64003;
                itemDef.femaleEquip1 = 64003;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18446:
                itemDef.modelID = 65008;
                itemDef.name = "Sauron PlateBody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65009;
                itemDef.femaleEquip1 = 65009;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18444:
                itemDef.modelID = 65014;
                itemDef.name = "Ice Demon PlateBody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65015;
                itemDef.femaleEquip1 = 65015;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18946:
                itemDef.modelID = 41344;
                itemDef.name = "Darth Vader helm";
                itemDef.description = "Darth Vader Helm";
                itemDef.modelZoom = 800;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -2;
                itemDef.maleEquip1 = 41343;
                itemDef.femaleEquip1 = 41343;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20527:
                itemDef.modelID = 41077;
                itemDef.name = "Darth Vader Gloves";
                itemDef.description = "Darth Vader Gloves";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41076;
                itemDef.femaleEquip1 = 41076;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 964:
                itemDef.modelID = 41394;
                itemDef.name = "Yellow Dragon Cape";
                itemDef.description = "Yellow Custom dragon";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41395;
                itemDef.femaleEquip1 = 41395;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 965:
                itemDef.modelID = 41385;
                itemDef.name = "OP Ring";
                itemDef.description = "OP Ring";
                ItemDefinition itemDef87 = ItemDefinition.forID(2550);
                itemDef.modelOffsetY = itemDef87.modelOffsetY;
                itemDef.modelOffsetX = itemDef87.modelOffsetX;
                itemDef.modelOffset1 = itemDef87.modelOffset1;
                itemDef.modelZoom = itemDef87.modelZoom;
                itemDef.rotationX = itemDef87.rotationX;
                itemDef.rotationY = itemDef87.rotationY;
                itemDef.maleEquip1 = 41385;
                itemDef.femaleEquip1 = 41385;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 996:
                itemDef.modelID = 41387;
                itemDef.name = "OP Amulet @yel@(Collector)";
                itemDef.description = "OP Amulet";
                itemDef.modelZoom = 600;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41388;
                itemDef.femaleEquip1 = 41388;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 997:
                itemDef.modelID = 41389;
                itemDef.name = "Infernal Main-Hand M16";
                itemDef.description = "Infernal Main-Hand M16";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41390;
                itemDef.femaleEquip1 = 41390;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 998:
                itemDef.modelID = 41389;
                itemDef.name = "Infernal Off-Hand M16";
                itemDef.description = "Infernal Off-Hand m16";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41391;
                itemDef.femaleEquip1 = 41391;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 999:
                itemDef.modelID = 41392;
                itemDef.name = "Infernal Cape";
                itemDef.description = "Infernal Cape";
                itemDef.modelOffsetY = 20;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelZoom = 2200;
                itemDef.rotationX = 1000;
                itemDef.rotationY = 400;
                itemDef.maleEquip1 = 41393;
                itemDef.femaleEquip1 = 41393;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 1000:
                itemDef.modelID = 41396;
                itemDef.name = "Infernal Skull";
                itemDef.description = "Infernal Skull";
                itemDef.modelZoom = 500;
                itemDef.rotationY = 0;
                itemDef.rotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41397;
                itemDef.femaleEquip1 = 41397;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 1002:
                itemDef.modelID = 41456;
                itemDef.name = "Burning Amulet @yel@(Collector)";
                itemDef.description = "Burning Amulet";
                ItemDefinition itemDef81 = ItemDefinition.forID(1704);
                itemDef.modelOffsetY = 10;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelZoom = 400;
                itemDef.rotationX = 0;
                itemDef.rotationY = 400;
                itemDef.maleEquip1 = 41455;
                itemDef.femaleEquip1 = 41455;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;

            case 1413:
                itemDef.modelID = 41482;
                itemDef.name = "Owner Cape";
                itemDef.description = "Owner Cape";
                itemDef.modelOffsetY = 20;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelZoom = 2200;
                itemDef.rotationX = 1200;
                itemDef.rotationY = 400;
                itemDef.maleEquip1 = 41483;
                itemDef.femaleEquip1 = 41483;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 2894:
                itemDef.modelID = 42057;
                itemDef.name = "Crazy Boots";
                itemDef.description = "Crazy Boots";
                ItemDefinition itemDef8112 = ItemDefinition.forID(1061);
                itemDef.modelOffsetY = itemDef8112.modelOffsetY;
                itemDef.modelOffsetX = itemDef8112.modelOffsetX;
                itemDef.modelOffset1 = itemDef8112.modelOffset1;
                itemDef.modelZoom = 1000;
                itemDef.rotationX = itemDef8112.rotationX;
                itemDef.rotationY = itemDef8112.rotationY;
                itemDef.maleEquip1 = 41534;
                itemDef.femaleEquip1 = 41534;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 127;
                itemDef.newModelColor[0] = 2059;
                break;
            case 1009:
                itemDef.modelID = 42064;
                itemDef.name = "Crazy Gloves";
                itemDef.description = "Crazy Gloves";
                itemDef.modelZoom = 600;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41535;
                itemDef.femaleEquip1 = 41535;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 127;
                itemDef.newModelColor[0] = 2059;
                break;
            case 1686:
                itemDef.modelID = 42067;
                itemDef.name = "Crazy PlateLegs";
                itemDef.description = "Crazy PlateLegs";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41538;
                itemDef.femaleEquip1 = 41538;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[]{127};
                itemDef.newModelColor = new int[]{2059};
                break;
            case 1015:
                itemDef.modelID = 42066;
                itemDef.name = "Crazy PlateBody";
                itemDef.description = "Crazy PlateBody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41537;
                itemDef.femaleEquip1 = 41537;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 127;
                itemDef.newModelColor[0] = 2059;
                break;
            case 1666:
                itemDef.modelID = 42068;
                itemDef.name = "Crazy Wings";
                itemDef.description = "Crazy Wings";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41539;
                itemDef.femaleEquip1 = 41539;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 127;
                itemDef.newModelColor[0] = 2059;
                break;
            case 1013:
                itemDef.modelID = 42069;
                itemDef.name = "Crazy Bow";
                itemDef.description = "Crazy Bow";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41540;
                itemDef.femaleEquip1 = 41540;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 127;
                itemDef.newModelColor[0] = 2059;
                break;
            case 1667:
                itemDef.modelID = 42065;
                itemDef.name = "Crazy Helmet";
                itemDef.description = "Crazy Helmet";
                itemDef.modelZoom = 600;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41536;
                itemDef.femaleEquip1 = 41536;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 127;
                itemDef.newModelColor[0] = 2059;
                break;
            case 1005:
                itemDef.modelID = 41468;
                itemDef.name = "@cya@Portal Gun";
                itemDef.description = "Portal Gun";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41469;
                itemDef.femaleEquip1 = 41469;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2059;
                break;
            case 1007:
                itemDef.modelID = 41398;
                itemDef.name = "Op Boots";
                itemDef.description = "Op Boots";
                ItemDefinition itemDef81123 = ItemDefinition.forID(1061);
                itemDef.modelOffsetY = itemDef81123.modelOffsetY;
                itemDef.modelOffsetX = itemDef81123.modelOffsetX;
                itemDef.modelOffset1 = itemDef81123.modelOffset1;
                itemDef.modelZoom = 1000;
                itemDef.rotationX = itemDef81123.rotationX;
                itemDef.rotationY = itemDef81123.rotationY;
                itemDef.maleEquip1 = 41398;
                itemDef.femaleEquip1 = 41398;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 2060;
                break;
            case 20528:
                itemDef.modelID = 41079;
                itemDef.name = "Darth Vader Cape";
                itemDef.description = "Darth Vader Cape";
                itemDef.modelZoom = 2250;
                itemDef.rotationY = 260;
                itemDef.rotationX = 1270;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41078;
                itemDef.femaleEquip1 = 41078;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20529:
                itemDef.modelID = 41080;
                itemDef.name = "Darth Vader Boots";
                itemDef.description = "Darth Vader Boots";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41080;
                itemDef.femaleEquip1 = 41080;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20530:
                itemDef.modelID = 41082;
                itemDef.name = "Darth Vader LightSaber";
                itemDef.description = "Darth Vader LightSaber";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41081;
                itemDef.femaleEquip1 = 41081;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20531:
                itemDef.modelID = 41084;
                itemDef.name = "Obi-Wan Kenobi LightSaber";
                itemDef.description = "Obi-Wan Kenobi LightSaber";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41083;
                itemDef.femaleEquip1 = 41083;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 9583;
                break;
            case 20532:
                itemDef.modelID = 41086;
                itemDef.name = "Obi-Wan Kenobi PlateBody";
                itemDef.description = "Obi-Wan Kenobi PlateBody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41085;
                itemDef.femaleEquip1 = 41085;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 9583;
                break;
            case 20525:
                itemDef.modelID = 41088;
                itemDef.name = "Obi-Wan Kenobi PlateLegs";
                itemDef.description = "Obi-Wan Kenobi PlateLegs";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41087;
                itemDef.femaleEquip1 = 41087;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 9583;
                break;
            case 20524:
                itemDef.modelID = 41089;
                itemDef.name = "Obi-Wan Kenobi Head";
                itemDef.description = "Obi-Wan Kenobi Head";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41334;
                itemDef.femaleEquip1 = 41334;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20518:
                itemDef.modelID = 41103;
                itemDef.name = "Obi-Wan Kenobi Gloves";
                itemDef.description = "Obi-Wan Kenobi Gloves";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41102;
                itemDef.femaleEquip1 = 41102;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20517:
                itemDef.modelID = 41104;
                itemDef.name = "Obi-Wan Kenobi Boots";
                itemDef.description = "Obi-Wan Kenobi Boots";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41104;
                itemDef.femaleEquip1 = 41104;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20523:
                itemDef.modelID = 41091;
                itemDef.name = "Yellow Blaster";
                itemDef.description = "Yellow Blaster";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41090;
                itemDef.femaleEquip1 = 41090;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20520:
                itemDef.modelID = 41093;
                itemDef.name = "Purple Blaster";
                itemDef.description = "Purple Blaster";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41092;
                itemDef.femaleEquip1 = 41092;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20519:
                itemDef.modelID = 41099;
                itemDef.name = "White Blaster @gre@(AOE)";
                itemDef.description = "White Blaster";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41100;
                itemDef.femaleEquip1 = 41100;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 933;
                itemDef.newModelColor[0] = 9583;
                break;
            case 20521:
                itemDef.modelID = 41095;
                itemDef.name = "Orange Blaster";
                itemDef.description = "Orange Blaster";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41094;
                itemDef.femaleEquip1 = 41094;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20522:
                itemDef.modelID = 41097;
                itemDef.name = "Blue Blaster";
                itemDef.description = "Blue Blaster";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 41096;
                itemDef.femaleEquip1 = 41096;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18440:
                itemDef.modelID = 65020;
                itemDef.name = "Darth Vader PlateBody";
                itemDef.description = "Darth Vader platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65021;
                itemDef.femaleEquip1 = 65021;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18442:
                itemDef.modelID = 65025;
                itemDef.name = "Dragon Leather PlateBody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65026;
                itemDef.femaleEquip1 = 65026;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18448:
                itemDef.editedModelColor = new int[]{52};
                itemDef.newModelColor = new int[]{68};
                itemDef.modelID = 65031;
                itemDef.name = "Magma PlateBody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65032;
                itemDef.femaleEquip1 = 65032;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18936:
                itemDef.modelID = 65037;
                itemDef.name = "Magma PlateBody (u)";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65038;
                itemDef.femaleEquip1 = 65038;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18938:
                itemDef.modelID = 65043;
                itemDef.name = "Skeleton Cosmetic PlateBody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65044;
                itemDef.femaleEquip1 = 65044;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18940:
                itemDef.modelID = 65049;
                itemDef.name = "Ghost PlateBody";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65050;
                itemDef.femaleEquip1 = 65050;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18932:
                itemDef.modelID = 64929;
                itemDef.name = "X Fullhelm";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 64930;
                itemDef.femaleEquip1 = 64930;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20162:
                itemDef.modelID = 64883;
                itemDef.name = "Sora Platebody";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 64884;
                itemDef.femaleEquip1 = 64884;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18934:
                itemDef.modelID = 64939;
                itemDef.name = "X Katana";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 64940;
                itemDef.femaleEquip1 = 64940;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 6657:
                itemDef.modelID = 64903;
                itemDef.name = "Al-Kharid Cape";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 64904;
                itemDef.femaleEquip1 = 64904;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 6659:
                itemDef.modelID = 64907;
                itemDef.name = "Al-Kharid Platelegs";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 64908;
                itemDef.femaleEquip1 = 64908;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 6661:
                itemDef.modelID = 64909;
                itemDef.name = "Al-Kharid Platebody";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 64910;
                itemDef.femaleEquip1 = 64910;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20154:
                itemDef.modelID = 64885;
                itemDef.name = "Sora Platelegs";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 64886;
                itemDef.femaleEquip1 = 64886;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18864:
                itemDef.modelID = 64827;
                itemDef.name = "Solara's Platelegs";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 64828;
                itemDef.femaleEquip1 = 64828;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18906:
                itemDef.modelID = 64043;
                itemDef.name = "Earth Guardian PlateLegs";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 64044;
                itemDef.femaleEquip1 = 64044;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18894:
                itemDef.modelID = 64031;
                itemDef.name = "Shadow Knight PlateLegs";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 64032;
                itemDef.femaleEquip1 = 64032;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18896:
                itemDef.modelID = 64037;
                itemDef.name = "Squidward PlateLegs";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 64038;
                itemDef.femaleEquip1 = 64038;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18910:
                itemDef.modelID = 65010;
                itemDef.name = "Sauron PlateLegs";
                itemDef.description = "Sauron platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 65011;
                itemDef.femaleEquip1 = 65011;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18912:
                itemDef.modelID = 65016;
                itemDef.name = "Ice Demon PlateLegs";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 65017;
                itemDef.femaleEquip1 = 65017;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18914:
                itemDef.modelID = 42035;
                itemDef.name = "Darth Vader PlateLegs";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 65022;
                itemDef.femaleEquip1 = 65022;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18916:
                itemDef.modelID = 65027;
                itemDef.name = "Dragon Leather PlateLegs";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 65028;
                itemDef.femaleEquip1 = 65028;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18918:
                itemDef.editedModelColor = new int[]{52};
                itemDef.newModelColor = new int[]{68};
                itemDef.modelID = 65033;
                itemDef.name = "Magma PlateLegs";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 65034;
                itemDef.femaleEquip1 = 65034;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18920:
                itemDef.modelID = 65039;
                itemDef.name = "Magma PlateLegs (u)";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 65040;
                itemDef.femaleEquip1 = 65040;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11661:
                itemDef.modelID = 65402;
                itemDef.name = "Darius Helm";
                itemDef.description = "A Custom item";
                itemDef.modelOffsetY = 0;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelZoom = 1000;
                itemDef.rotationX = 0;
                itemDef.rotationY = 0;
                itemDef.maleEquip1 = 65403;
                itemDef.femaleEquip1 = 65403;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11662:
                itemDef.modelID = 65405;
                itemDef.name = "Darius Body";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65404;
                itemDef.femaleEquip1 = 65404;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11679:
                itemDef.modelID = 65407;
                itemDef.name = "Darius platelegs";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65406;
                itemDef.femaleEquip1 = 65406;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11680:
                itemDef.modelID = 65409;
                itemDef.name = "Darius Gloves";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 450;
                itemDef.rotationY = 200;
                itemDef.rotationX = 200;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65408;
                itemDef.femaleEquip1 = 65408;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11681:
                itemDef.modelID = 65410;
                itemDef.name = "Darius Boots";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 650;
                itemDef.rotationY = 0;
                itemDef.rotationX = 1000;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65410;
                itemDef.femaleEquip1 = 65410;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 11682:
                itemDef.modelID = 65412;
                itemDef.name = "Darius Wings";
                itemDef.description = "Torva platebody";
                itemDef.modelZoom = 1506;
                itemDef.rotationY = 473;
                itemDef.rotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleEquip1 = 65411;
                itemDef.femaleEquip1 = 65411;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18922:
                itemDef.modelID = 65045;
                itemDef.name = "Skeleton Cosmetic PlateLegs";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 65046;
                itemDef.femaleEquip1 = 65046;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 18924:
                itemDef.modelID = 65051;
                itemDef.name = "Ghost Skirt";
                itemDef.description = "Torva platelegs";
                itemDef.modelZoom = 1740;
                itemDef.rotationY = 474;
                itemDef.rotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleEquip1 = 65052;
                itemDef.femaleEquip1 = 65052;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 9924:
            case 9923:
            case 9925:
            case 9921:
            case 9922:
                itemDef.editedModelColor = new int[]{1};
                break;
        }
        return ItemDef4.forDef(itemDef, i);
    }

    public void imitate(ItemDefinition other) {
        name = other.name;
        description = other.description;
        editedModelColor = other.editedModelColor;
        newModelColor = other.newModelColor;
        sizeX = other.sizeX;
        sizeY = other.sizeY;
        sizeZ = other.sizeZ;
        rotationY = other.rotationY;
        rotationX = other.rotationX;
        modelOffset1 = other.modelOffset1;
        modelOffsetY = other.modelOffsetY;
        modelOffsetX = other.modelOffsetX;
        modelZoom = other.modelZoom;
        modelID = other.modelID;
        actions = other.actions;
        maleEquip1 = other.maleEquip1;
        maleEquip2 = other.maleEquip2;
        maleEquip3 = other.maleEquip3;
        femaleEquip1 = other.femaleEquip1;
        femaleEquip2 = other.femaleEquip2;
        femaleEquip3 = other.femaleEquip3;
        maleDialogue = other.maleDialogue;
        maleDialogueModel = other.maleDialogueModel;
        femaleDialogue = other.femaleDialogue;
        femaleDialogue = other.femaleDialogueModel;
    }

    void readValues(Stream stream) {
        do {
            int i = stream.readUnsignedByte();
            if (i == 0) {
                return;
            }
            if (i == 1) {
                modelID = stream.readUnsignedWord();
            } else if (i == 2) {
                name = stream.readString();
            } else if (i == 3) {
                description = stream.readString();
            } else if (i == 4) {
                modelZoom = stream.readUnsignedWord();
            } else if (i == 5) {
                rotationY = stream.readUnsignedWord();
            } else if (i == 6) {
                rotationX = stream.readUnsignedWord();
            } else if (i == 7) {
                modelOffset1 = stream.readUnsignedWord();
                if (modelOffset1 > 32767) {
                    modelOffset1 -= 0x10000;
                }
            } else if (i == 8) {
                modelOffsetY = stream.readUnsignedWord();
                if (modelOffsetY > 32767) {
                    modelOffsetY -= 0x10000;
                }
            } else if (i == 10) {
                stream.readUnsignedWord();
            } else if (i == 11) {
                stackable = true;
            } else if (i == 12) {
                value = stream.readUnsignedWord();
            } else if (i == 16) {
                membersObject = true;
            } else if (i == 23) {
                maleEquip1 = stream.readUnsignedWord();
                maleYOffset = stream.readSignedByte();
            } else if (i == 24) {
                maleEquip2 = stream.readUnsignedWord();
            } else if (i == 25) {
                femaleEquip1 = stream.readUnsignedWord();
                femaleYOffset = stream.readSignedByte();
            } else if (i == 26) {
                femaleEquip2 = stream.readUnsignedWord();
            } else if (i >= 30 && i < 35) {
                if (groundActions == null) {
                    groundActions = new String[5];
                }
                groundActions[i - 30] = stream.readString();
                if (groundActions[i - 30].equalsIgnoreCase("hidden")) {
                    groundActions[i - 30] = null;
                }
            } else if (i >= 35 && i < 40) {
                if (actions == null) {
                    actions = new String[5];
                }
                actions[i - 35] = stream.readString();
                if (actions[i - 35].equalsIgnoreCase("null")) {
                    actions[i - 35] = null;
                }
            } else if (i == 40) {
                int j = stream.readUnsignedByte();
                editedModelColor = new int[j];
                newModelColor = new int[j];
                for (int k = 0; k < j; k++) {
                    editedModelColor[k] = stream.readUnsignedWord();
                    newModelColor[k] = stream.readUnsignedWord();
                }
            } else if (i == 78) {
                maleEquip3 = stream.readUnsignedWord();
            } else if (i == 79) {
                femaleEquip3 = stream.readUnsignedWord();
            } else if (i == 90) {
                maleDialogue = stream.readUnsignedWord();
            } else if (i == 91) {
                femaleDialogue = stream.readUnsignedWord();
            } else if (i == 92) {
                maleDialogueModel = stream.readUnsignedWord();
            } else if (i == 93) {
                femaleDialogueModel = stream.readUnsignedWord();
            } else if (i == 95) {
                modelOffsetX = stream.readUnsignedWord();
            } else if (i == 97) {
                certID = stream.readUnsignedWord();
            } else if (i == 98) {
                certTemplateID = stream.readUnsignedWord();
            } else if (i >= 100 && i < 110) {
                if (stackIDs == null) {
                    stackIDs = new int[10];
                    stackAmounts = new int[10];
                }
                stackIDs[i - 100] = stream.readUnsignedWord();
                stackAmounts[i - 100] = stream.readUnsignedWord();
            } else if (i == 110) {
                sizeX = stream.readUnsignedWord();
            } else if (i == 111) {
                sizeY = stream.readUnsignedWord();
            } else if (i == 112) {
                sizeZ = stream.readUnsignedWord();
            } else if (i == 113) {
                shadow = stream.readSignedByte();
            } else if (i == 114) {
                lightness = stream.readSignedByte() * 5;
            } else if (i == 115) {
                team = stream.readUnsignedByte();
            } else if (i == 116) {
                lendID = stream.readUnsignedWord();
            } else if (i == 117) {
                lentItemID = stream.readUnsignedWord();
            }
        } while (true);
    }

    public static void setSettings() {
        try {
            prices = new int[22694];
            int index = 0;
            for (String line : Files.readAllLines(Paths.get(signlink.findcachedir() + "data/data.txt"), Charset.defaultCharset())) {
                prices[index] = Integer.parseInt(line);
                index++;
            }
            for (int i : UNTRADEABLE_ITEMS) {
                untradeableItems.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void toNote() {
        ItemDefinition itemDef = forID(certTemplateID);
        modelID = itemDef.modelID;
        modelZoom = itemDef.modelZoom;
        rotationY = itemDef.rotationY;
        rotationX = itemDef.rotationX;
        modelOffsetX = itemDef.modelOffsetX;
        modelOffset1 = itemDef.modelOffset1;
        modelOffsetY = itemDef.modelOffsetY;
        editedModelColor = itemDef.editedModelColor;
        newModelColor = itemDef.newModelColor;
        ItemDefinition itemDef_1 = forID(certID);
        name = itemDef_1.name;
        membersObject = itemDef_1.membersObject;
        value = itemDef_1.value;
        String s = "a";
        char c = itemDef_1.name.charAt(0);
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            s = "an";
        }
        description = ("Swap this note at any bank for " + s + " " + itemDef_1.name + ".");
        stackable = true;
    }

    void toLend() {
        ItemDefinition itemDef = forID(lentItemID);
        actions = new String[5];
        modelID = itemDef.modelID;
        modelOffset1 = itemDef.modelOffset1;
        rotationX = itemDef.rotationX;
        modelOffsetY = itemDef.modelOffsetY;
        modelZoom = itemDef.modelZoom;
        rotationY = itemDef.rotationY;
        modelOffsetX = itemDef.modelOffsetX;
        value = 0;
        ItemDefinition itemDef_1 = forID(lendID);
        maleDialogueModel = itemDef_1.maleDialogueModel;
        editedModelColor = itemDef_1.editedModelColor;
        maleEquip3 = itemDef_1.maleEquip3;
        maleEquip2 = itemDef_1.maleEquip2;
        femaleDialogueModel = itemDef_1.femaleDialogueModel;
        maleDialogue = itemDef_1.maleDialogue;
        groundActions = itemDef_1.groundActions;
        maleEquip1 = itemDef_1.maleEquip1;
        name = itemDef_1.name;
        femaleEquip1 = itemDef_1.femaleEquip1;
        membersObject = itemDef_1.membersObject;
        femaleDialogue = itemDef_1.femaleDialogue;
        femaleEquip2 = itemDef_1.femaleEquip2;
        femaleEquip3 = itemDef_1.femaleEquip3;
        newModelColor = itemDef_1.newModelColor;
        team = itemDef_1.team;
        if (itemDef_1.actions != null) {
            for (int i_33_ = 0; i_33_ < 4; i_33_++) {
                actions[i_33_] = itemDef_1.actions[i_33_];
            }
        }
        actions[4] = "Discard";
    }

    public static Sprite getBiggerSprite(int id, int amount, int color, int size, int scale) {
        if (color == 0) {
            Sprite sprite = (Sprite) spriteCache.get(id);
            if (sprite != null && sprite.maxHeight != amount && sprite.maxHeight != -1) {
                sprite.unlink();
                sprite = null;
            }
            if (sprite != null) {
                return sprite;
            }
        }
        ItemDefinition item = forID(id);
        if (item.stackIDs == null)
            amount = -1;
        if (amount > 1) {
            int i1 = -1;
            for (int j1 = 0; j1 < 50; j1++)
                if (amount >= item.stackAmounts[j1] && item.stackAmounts[j1] != 0)
                    i1 = item.stackIDs[j1];
            if (i1 != -1)
                item = forID(i1);
        }
        Model model = item.getItemModelFinalised(1);

        if (model == null) {
            return null;
        }
        Sprite sprite = null;
        if (item.certTemplateID != -1) {
            sprite = getBiggerSprite(item.certID, 10, -1, size, scale);
            if (sprite == null)
                return null;
        }

        Sprite sprite2 = new Sprite(size, size);
        int k1 = Rasterizer.textureInt1;
        int l1 = Rasterizer.textureInt2;
        int ai[] = Rasterizer.anIntArray1472;
        int ai1[] = DrawingArea.pixels;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.aBoolean1464 = false;
        DrawingArea.initDrawingArea(size, size, sprite2.myPixels, new float[size * size]);
        DrawingArea.drawPixels(size, 0, 0, 0, size);
        Rasterizer.setDefaultBounds();
        int k3 = item.modelZoom;
        if (scale != -1) {
            if (scale > item.modelZoom) {
                k3 = 0;
            } else {
                k3 -= scale;
            }
            model.scaleT(145, 145, 145);
        }
        if (color == -1)
            k3 = (int) ((double) k3 * 1.5D);
        if (color > 0)
            k3 = (int) ((double) k3 * 1.04D);

        int l3 = Rasterizer.anIntArray1470[item.rotationY] * k3 >> 16;
        int i4 = Rasterizer.anIntArray1471[item.rotationY] * k3 >> 16;

        model.renderSingle(item.rotationX, item.modelOffsetX, item.rotationY, item.modelOffset1, l3 + model.modelHeight / 2 + item.modelOffsetY, i4 + item.modelOffsetY);
        sprite2.outline(1);
        if (color > 0)
            sprite2.outline(16777215);

        if (color == 0)
            sprite2.shadow(0x0A0A0A);
        DrawingArea.initDrawingArea(size, size, sprite2.myPixels, new float[size * size]);
        if (item.certTemplateID != -1) {
            int old_w = sprite.maxWidth;
            int old_h = sprite.maxHeight;
            sprite.maxWidth = size;
            sprite.maxHeight = size;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = old_w;
            sprite.maxHeight = old_h;
        }

        if (color == 0) {
            spriteCache.put(sprite2, id);
        }
        DrawingArea.initDrawingArea(j2, i2, ai1, new float[size * size]);
        DrawingArea.setDrawingArea(j3, k2, l2, i3);
        Rasterizer.textureInt1 = k1;
        Rasterizer.textureInt2 = l1;
        Rasterizer.anIntArray1472 = ai;
        Rasterizer.aBoolean1464 = true;
        if (item.stackable)
            sprite2.maxWidth = size + 1;
        else
            sprite2.maxWidth = size;
        sprite2.maxHeight = amount;
        return sprite2;
    }

    public static Sprite getSprite(int i, int j, int k, int zoom) {
        if (k == 0 && zoom != -1) {
            Sprite sprite = (Sprite) spriteCache.get(i);
            if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
                sprite.unlink();
                sprite = null;
            }
            if (sprite != null) {
                return sprite;
            }
        }
        ItemDefinition itemDef = forID(i);
        if (itemDef.stackIDs == null) {
            j = -1;
        }
        if (j > 1) {
            int i1 = -1;
            for (int j1 = 0; j1 < 10; j1++) {
                if (j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0) {
                    i1 = itemDef.stackIDs[j1];
                }
            }

            if (i1 != -1) {
                itemDef = forID(i1);
            }
        }
        Model model = itemDef.getItemModelFinalised(1);
        if (model == null) {
            return null;
        }
        Sprite sprite = null;
        if (itemDef.certTemplateID != -1) {
            sprite = getSprite(itemDef.certID, 10, -1);
            if (sprite == null) {
                return null;
            }
        }
        if (itemDef.lendID != -1) {
            sprite = getSprite(itemDef.lendID, 50, 0);
            if (sprite == null) {
                return null;
            }
        }
        Sprite sprite2 = new Sprite(32, 32);
        int k1 = Rasterizer.textureInt1;
        int l1 = Rasterizer.textureInt2;
        int ai[] = Rasterizer.anIntArray1472;
        int ai1[] = DrawingArea.pixels;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        float depthBuffer[] = DrawingArea.depthBuffer;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.aBoolean1464 = false;
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels, new float[32 * 32]);
        DrawingArea.drawPixels(32, 0, 0, 0, 32);
        Rasterizer.setDefaultBounds();
        int k3 = itemDef.modelZoom;
        if (zoom != -1 && zoom != 0) {
            k3 = (itemDef.modelZoom * 100) / zoom;
        }
        if (k == -1) {
            k3 = (int) ((double) k3 * 1.5D);
        }
        if (k > 0) {
            k3 = (int) ((double) k3 * 1.04D);
        }
        int l3 = Rasterizer.anIntArray1470[itemDef.rotationY] * k3 >> 16;
        int i4 = Rasterizer.anIntArray1471[itemDef.rotationY] * k3 >> 16;
        model.renderSingle(itemDef.rotationX, itemDef.modelOffsetX, itemDef.rotationY, itemDef.modelOffset1, l3 + model.modelHeight / 2 + itemDef.modelOffsetY, i4 + itemDef.modelOffsetY);
        for (int i5 = 31; i5 >= 0; i5--) {
            for (int j4 = 31; j4 >= 0; j4--) {
                if (sprite2.myPixels[i5 + j4 * 32] != 0) {
                    continue;
                }
                if (i5 > 0 && sprite2.myPixels[(i5 - 1) + j4 * 32] > 1) {
                    sprite2.myPixels[i5 + j4 * 32] = 1;
                    continue;
                }
                if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1) {
                    sprite2.myPixels[i5 + j4 * 32] = 1;
                    continue;
                }
                if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1) {
                    sprite2.myPixels[i5 + j4 * 32] = 1;
                    continue;
                }
                if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1) {
                    sprite2.myPixels[i5 + j4 * 32] = 1;
                }
            }

        }

        if (k > 0) {
            for (int j5 = 31; j5 >= 0; j5--) {
                for (int k4 = 31; k4 >= 0; k4--) {
                    if (sprite2.myPixels[j5 + k4 * 32] != 0) {
                        continue;
                    }
                    if (j5 > 0 && sprite2.myPixels[(j5 - 1) + k4 * 32] == 1) {
                        sprite2.myPixels[j5 + k4 * 32] = k;
                        continue;
                    }
                    if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1) {
                        sprite2.myPixels[j5 + k4 * 32] = k;
                        continue;
                    }
                    if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1) {
                        sprite2.myPixels[j5 + k4 * 32] = k;
                        continue;
                    }
                    if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1) {
                        sprite2.myPixels[j5 + k4 * 32] = k;
                    }
                }

            }

        } else if (k == 0) {
            for (int k5 = 31; k5 >= 0; k5--) {
                for (int l4 = 31; l4 >= 0; l4--) {
                    if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && sprite2.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0) {
                        sprite2.myPixels[k5 + l4 * 32] = 0x302020;
                    }
                }

            }

        }
        if (itemDef.certTemplateID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (itemDef.lendID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (k == 0 && i != 5572 && i != 5573 && i != 640 && i != 650 && i != 630) {
            spriteCache.put(sprite2, i);
        }
        DrawingArea.initDrawingArea(j2, i2, ai1, depthBuffer);
        DrawingArea.setDrawingArea(j3, k2, l2, i3);
        Rasterizer.textureInt1 = k1;
        Rasterizer.textureInt2 = l1;
        Rasterizer.anIntArray1472 = ai;
        Rasterizer.aBoolean1464 = true;
        sprite2.maxWidth = itemDef.stackable ? 33 : 32;
        sprite2.maxHeight = j;
        return sprite2;
    }


    public static Sprite getSprite(int i, int j, int k) {
        if (k == 0) {
            Sprite sprite = (Sprite) spriteCache.get(i);
            if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
                sprite.unlink();
                sprite = null;
            }
            if (sprite != null) {
                return sprite;
            }
        }
        ItemDefinition itemDef = forID(i);
        if (itemDef.stackIDs == null) {
            j = -1;
        }
        if (j > 1) {
            int i1 = -1;
            for (int j1 = 0; j1 < 10; j1++) {
                if (j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0) {
                    i1 = itemDef.stackIDs[j1];
                }
            }
            if (i1 != -1) {
                itemDef = forID(i1);
            }
        }
        Model model = itemDef.getItemModelFinalised(1);
        if (model == null) {
            return null;
        }
        Sprite sprite = null;
        if (itemDef.certTemplateID != -1) {
            sprite = getSprite(itemDef.certID, 10, -1);
            if (sprite == null) {
                return null;
            }
        }
        if (itemDef.lentItemID != -1) {
            sprite = getSprite(itemDef.lendID, 50, 0);
            if (sprite == null) {
                return null;
            }
        }
        Sprite sprite2 = new Sprite(32, 32);
        int k1 = Rasterizer.textureInt1;
        int l1 = Rasterizer.textureInt2;
        int ai[] = Rasterizer.anIntArray1472;
        int ai1[] = DrawingArea.pixels;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.aBoolean1464 = false;
        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels, new float[32 * 32]);
        DrawingArea.drawPixels(32, 0, 0, 0, 32);
        Rasterizer.setDefaultBounds();
        int k3 = itemDef.modelZoom;
        if (k == -1) {
            k3 = (int) ((double) k3 * 1.5D);
        }
        if (k > 0) {
            k3 = (int) ((double) k3 * 1.04D);
        }
        int l3 = Rasterizer.anIntArray1470[itemDef.rotationY] * k3 >> 16;
        int i4 = Rasterizer.anIntArray1471[itemDef.rotationY] * k3 >> 16;
        model.renderSingle(itemDef.rotationX, itemDef.modelOffsetX, itemDef.rotationY, itemDef.modelOffset1, l3 + model.modelHeight / 2 + itemDef.modelOffsetY, i4 + itemDef.modelOffsetY);
        for (int i5 = 31; i5 >= 0; i5--) {
            for (int j4 = 31; j4 >= 0; j4--) {
                if (sprite2.myPixels[i5 + j4 * 32] == 0) {
                    if (i5 > 0 && sprite2.myPixels[(i5 - 1) + j4 * 32] > 1) {
                        sprite2.myPixels[i5 + j4 * 32] = 1;
                    } else if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1) {
                        sprite2.myPixels[i5 + j4 * 32] = 1;
                    } else if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1) {
                        sprite2.myPixels[i5 + j4 * 32] = 1;
                    } else if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1) {
                        sprite2.myPixels[i5 + j4 * 32] = 1;
                    }
                }
            }
        }
        if (k > 0) {
            for (int j5 = 31; j5 >= 0; j5--) {
                for (int k4 = 31; k4 >= 0; k4--) {
                    if (sprite2.myPixels[j5 + k4 * 32] == 0) {
                        if (j5 > 0 && sprite2.myPixels[(j5 - 1) + k4 * 32] == 1) {
                            sprite2.myPixels[j5 + k4 * 32] = k;
                        } else if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1) {
                            sprite2.myPixels[j5 + k4 * 32] = k;
                        } else if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1) {
                            sprite2.myPixels[j5 + k4 * 32] = k;
                        } else if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1) {
                            sprite2.myPixels[j5 + k4 * 32] = k;
                        }
                    }
                }
            }
        } else if (k == 0) {
            for (int k5 = 31; k5 >= 0; k5--) {
                for (int l4 = 31; l4 >= 0; l4--) {
                    if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && sprite2.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0) {
                        sprite2.myPixels[k5 + l4 * 32] = 0x302020;
                    }
                }
            }
        }
        if (itemDef.certTemplateID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (itemDef.lentItemID != -1) {
            int l5 = sprite.maxWidth;
            int j6 = sprite.maxHeight;
            sprite.maxWidth = 32;
            sprite.maxHeight = 32;
            sprite.drawSprite(0, 0);
            sprite.maxWidth = l5;
            sprite.maxHeight = j6;
        }
        if (k == 0 && i != 5572 && i != 5573 && i != 640 && i != 650 && i != 630 && model.texturedFaces == 0) {
            spriteCache.put(sprite2, i);
        }
        DrawingArea.initDrawingArea(j2, i2, ai1, new float[size * size]);
        DrawingArea.setDrawingArea(j3, k2, l2, i3);
        Rasterizer.textureInt1 = k1;
        Rasterizer.textureInt2 = l1;
        Rasterizer.anIntArray1472 = ai;
        Rasterizer.aBoolean1464 = true;
        if (itemDef.stackable) {
            sprite2.maxWidth = 33;
        } else {
            sprite2.maxWidth = 32;
        }
        sprite2.maxHeight = j;
        return sprite2;
    }


    public Model getItemModelFinalised(int amount) {
        if (stackIDs != null && amount > 1) {
            int stackId = -1;
            for (int k = 0; k < 10; k++) {
                if (amount >= stackAmounts[k] && stackAmounts[k] != 0) {
                    stackId = stackIDs[k];
                }
            }
            if (stackId != -1) {
                return forID(stackId).getItemModelFinalised(1);
            }
        }
        Model model = (Model) modelCache.get(id);
        if (model != null) {
            return model;
        }
        model = Model.fetchModel(modelID);
        if (model == null) {
            return null;
        }
        if (sizeX != 128 || sizeY != 128 || sizeZ != 128) {
            model.scaleT(sizeX, sizeZ, sizeY);
        }
        if (editedModelColor != null) {
            for (int l = 0; l < editedModelColor.length; l++) {
                model.recolour(editedModelColor[l], newModelColor[l]);
            }
        }
        model.light(64 + shadow, 768 + lightness, -50, -10, -50, true);
        model.rendersWithinOneTile = true;
        if (id != 5572 && id != 5573 && id != 640 && id != 650 && id != 630 && id != 20126 && id != 20144) {
            modelCache.put(model, id);
        }

        //applyTexturing(model, id);
        return model;
    }

    public Model getItemModel(int i) {
        if (stackIDs != null && i > 1) {
            int j = -1;
            for (int k = 0; k < 10; k++) {
                if (i >= stackAmounts[k] && stackAmounts[k] != 0) {
                    j = stackIDs[k];
                }
            }
            if (j != -1) {
                return forID(j).getItemModel(1);
            }
        }
        Model model = Model.fetchModel(modelID);
        if (model == null) {
            return null;
        }
        if (editedModelColor != null) {
            for (int l = 0; l < editedModelColor.length; l++) {
                model.recolour(editedModelColor[l], newModelColor[l]);
            }
        }

        //applyTexturing(model, id);
        return model;
    }

    public static final int[] UNTRADEABLE_ITEMS
            = {13661, 13262,
            6529, 6950, 1464, 2996, 2677, 2678, 2679, 2680, 2682,
            2683, 2684, 2685, 2686, 2687, 2688, 2689, 2690,
            6570, 12158, 12159, 12160, 12163, 12161, 12162,
            19143, 19149, 19146, 19157, 19162, 19152, 4155,
            8850, 10551, 8839, 8840, 8842, 11663, 11664,
            11665, 3842, 3844, 3840, 8844, 8845, 8846, 8847,
            8848, 8849, 8850, 10551, 7462, 7461, 7460,
            7459, 7458, 7457, 7456, 7455, 7454, 7453, 8839,
            8840, 8842, 11663, 11664, 11665, 10499, 9748,
            9754, 9751, 9769, 9757, 9760, 9763, 9802, 9808,
            9784, 9799, 9805, 9781, 9796, 9793, 9775, 9772,
            9778, 9787, 9811, 9766, 9749, 9755, 9752, 9770,
            9758, 9761, 9764, 9803, 9809, 9785, 9800, 9806,
            9782, 9797, 9794, 9776, 9773, 9779, 9788, 9812,
            9767, 9747, 9753, 9750, 9768, 9756, 9759, 9762,
            9801, 9807, 9783, 9798, 9804, 9780, 9795, 9792,
            9774, 9771, 9777, 9786, 9810, 9765, 9948, 9949,
            9950, 12169, 12170, 12171, 20671, 14641, 14642,
            6188, 10954, 10956, 10958,
            3057, 3058, 3059, 3060, 3061,
            7594, 7592, 7593, 7595, 7596,
            14076, 14077, 14081,
            10840, 10836, 6858, 6859, 10837, 10838, 10839,
            9925, 9924, 9923, 9922, 9921,
            4084, 4565, 20046, 20044, 20045,
            1050, 14595, 14603, 14602, 14605, 11789,
            19708, 19706, 19707,
            4860, 4866, 4872, 4878, 4884, 4896, 4890, 4896, 4902,
            4932, 4938, 4944, 4950, 4908, 4914, 4920, 4926, 4956,
            4926, 4968, 4994, 4980, 4986, 4992, 4998,
            18778, 18779, 18780, 18781,
            13450, 13444, 13405, 15502,
            10548, 10549, 10550, 10551, 10555, 10552, 10553, 2412, 2413, 2414,
            20747,
            18365, 18373, 18371, 15246, 12964, 12971, 12978, 14017,
            757, 8851,
            13855, 13848, 13857, 13856, 13854, 13853, 13852, 13851, 13850, 5509, 13653, 14021, 14020, 19111, 14019, 14022,
            19785, 19786, 18782, 18351, 18349, 18353, 18357, 18355, 18359, 18335
    };

    public ItemDefinition() {
        id = -1;
    }

    public byte femaleYOffset;
    public int value;
    public int[] editedModelColor;
    public int id;
    public static MemCache spriteCache = new MemCache(100);
    public static MemCache modelCache = new MemCache(50);
    public int[] newModelColor;
    public boolean membersObject;
    public int femaleEquip3;
    public int certTemplateID;
    public int femaleEquip2;
    public int maleEquip1;
    public int maleDialogueModel;
    public int sizeX;
    public String groundActions[];
    public int modelOffset1;
    public String name;
    public static ItemDefinition[] cache;
    public int femaleDialogueModel;
    public int modelID;
    public int maleDialogue;
    public boolean stackable;
    public String description;
    public int certID;
    public static int cacheIndex;
    public int modelZoom;
    public static Stream stream;
    public int lightness;
    public int maleEquip3;
    public int maleEquip2;
    public String actions[];
    public int rotationY;
    public int sizeZ;
    public int sizeY;
    public int[] stackIDs;
    public int modelOffsetY;
    public static int[] streamIndices;
    public int shadow;
    public int femaleDialogue;
    public int rotationX;
    public int femaleEquip1;
    public int[] stackAmounts;
    public int team;
    public static int totalItems;
    public int modelOffsetX;
    public byte maleYOffset;
    public byte maleXOffset;
    public int lendID;
    public int lentItemID;
    public boolean untradeable;

    public static String itemModels(int itemID) {
        int inv = forID(itemID).modelID;
        int male = forID(itemID).maleEquip1;
        int female = forID(itemID).femaleEquip1;
        String name = forID(itemID).name;
        return "<col=225>" + name + "</col> (<col=800000000>" + itemID + "</col>) - [inv: <col=800000000>" + inv + "</col>] - [male: <col=800000000>" + male + "</col>] - [female: <col=800000000>" + female + "</col>]";
    }
}
