package com.client.client;

import java.util.HashMap;
import java.util.Map;

import com.client.client.cache.definitions.ItemDefinition;

public class ItemDescriptionHandler
{
    private Map<Integer, String> effects;
    private boolean loaded;
    
    public ItemDescriptionHandler() {
        this.effects = null;
        this.loaded = false;
        this.effects = new HashMap<Integer, String>();
    }
    
    public void addEffect(final int weapon, String effect) {
        if (!effect.endsWith(".") && !effect.endsWith("!") && !effect.endsWith("?") && !effect.endsWith("^")) {
            effect += ".";
        }
        this.effects.put(weapon, effect);
    }
    
    public void addEffect(final int weapon, final int copyFrom) {
        final String effect = this.effects.get(copyFrom);
        if (effect == null) {
            System.err.println("[ItemDescriptionHandler]: error copying " + weapon + " from " + copyFrom);
        }
        else {
            this.effects.put(weapon, effect);
        }
    }
    
    public String getEffect(final int item) {
        return this.effects.get(item);
    }
    
    public void load() {
        if (this.loaded) {
            return;
        }
        this.loaded = true;
        final String DMG = "Special attack deals additional damage";
        this.addEffect(11730, "Special attack inflicts an additional fixed hit");
        this.addEffect(4587, "Special attack disables your opponents' prayers");
        this.addEffect(4081, "Additional 20% damage against undeads");
        this.addEffect(4151, "Special attack deals 10% more damage and shortly binds your target");
        this.addEffect(10180, 4151);
        for (int item = 10180; item <= 10189; ++item) {
            this.addEffect(item, 4151);
        }
        this.addEffect(10233, 4151);
        this.addEffect(11698, "Special attack heals and increases prayer points for the amount hit.");
        this.addEffect(11700, "Special attack binds your enemy for a long time.");
        this.addEffect(11696, "Special attack decreases your opponent's defence.");
        this.addEffect(11694, DMG);
        this.addEffect(1215, "Special attack quickly strikes your enemy twice.");
        this.addEffect(1231, 1215);
        this.addEffect(5680, 1215);
        this.addEffect(5698, 1215);
        this.addEffect(1377, "Special attack boosts your strength level by 10%.");
        this.addEffect(10200, "Special attack inflicts an additional hit");
        for (int item = 10235; item <= 10241; ++item) {
            this.addEffect(item, 10200);
        }
        this.addEffect(4587, "Special attack disables all enemy prayers.");
        this.addEffect(3204, "Special attack hits large targets especially hard.");
        this.addEffect(1305, DMG);
        this.addEffect(19780, "Special attack deals additional magic damage");
        this.addEffect(10887, DMG);
        this.addEffect(1434, DMG);
        this.addEffect(861, "Special attack quickly fires two arrows in a row at your enemy.");
        this.addEffect(11235, "Special attack fires two arrows at once at your enemy.");
        for (int item = 15701; item <= 15704; ++item) {
            this.addEffect(item, 11235);
        }
        this.addEffect(6724, "Special attack decreases your opponent's combat power.");
        this.addEffect(4153, "Special attack allows another immediate hit on your opponent.");
        this.addEffect(6739, "Special attack deals additional damage. When used on trees, two logs are immediately cut.");
        this.addEffect(15259, "Special attack deals additional damage. When used on rocks, two ores are immediately mined.");
        this.addEffect(20786, "Special attack deals additional damage. When used on rocks, two ores are immediately mined.");
        this.addEffect(805, DMG);
        this.addEffect(13879, "Special attack deals additional damage and applies a damage over time effect for 6 seconds.");
        for (int item = 13880; item <= 13882; ++item) {
            this.addEffect(item, 13879);
        }
        this.addEffect(13883, DMG);
        this.addEffect(13902, "Special attack deals additional damage and decreases your opponent's defence.");
        this.addEffect(13904, 13902);
        this.addEffect(13899, DMG);
        this.addEffect(13901, 13899);
        this.addEffect(13905, "Special deals additional damage in one strong and two weak hits.");
        this.addEffect(13907, 13905);
        this.addEffect(7158, DMG);
        this.addEffect(3101, DMG);
        this.addEffect(14484, "Special attack deals additional damage in four hits.");
        this.addEffect(4708, "Set effect: Increases spell effects by 25%.");
        this.addEffect(4710, 4708);
        this.addEffect(4712, 4708);
        this.addEffect(4714, 4708);
        for (int item = 4856; item <= 4879; ++item) {
            this.addEffect(item, 4708);
        }
        this.addEffect(4716, "Set effect: Increases max hits the lower your hitpoints become.");
        this.addEffect(4718, 4716);
        this.addEffect(4720, 4716);
        this.addEffect(4722, 4716);
        for (int item = 4880; item <= 4903; ++item) {
            this.addEffect(item, 4716);
        }
        this.addEffect(4724, "Set effect: Provides a 20% chance to heal yourself equal to 50% of your current hit.");
        this.addEffect(4726, 4724);
        this.addEffect(4728, 4724);
        this.addEffect(4730, 4724);
        for (int item = 4904; item <= 4927; ++item) {
            this.addEffect(item, 4724);
        }
        this.addEffect(4732, "Set effect: Provides a 20% chance to let your next auto attack be 0.5 seconds faster.");
        this.addEffect(4734, 4732);
        this.addEffect(4736, 4732);
        this.addEffect(4738, 4732);
        this.addEffect(4740, 4732);
        for (int item = 4928; item <= 4951; ++item) {
            this.addEffect(item, 4732);
        }
        this.addEffect(4745, "Set effect: Reduces all incoming damage by 10%.");
        this.addEffect(4747, 4745);
        this.addEffect(4749, 4745);
        this.addEffect(4751, 4745);
        for (int item = 4952; item <= 4975; ++item) {
            this.addEffect(item, 4745);
        }
        this.addEffect(4753, "Set effect: Provides a 20% chance to ignore 75% of your opponent's defence and protection prayers.");
        this.addEffect(4755, 4753);
        this.addEffect(4757, 4753);
        this.addEffect(4759, 4753);
        for (int item = 4976; item <= 4999; ++item) {
            this.addEffect(item, 4753);
        }
        this.addEffect(7639, "Autocasts storm of armadyl.");
        for (int item = 7640; item <= 7649; ++item) {
            this.addEffect(item, 7639);
        }
        this.addEffect(13867, "Autocasts zuriel barrage.");
        this.addEffect(13869, 13867);
        this.addEffect(10034, "Hits multiple targets at once.");
        this.addEffect(20767, "All levels on 99. Well done! ^_^");
        this.addEffect(20768, 20767);
        final String VOID = "Set effect: Provides 30% more accuracy and 10% more damage.";
        this.addEffect(8842, VOID);
        this.addEffect(11663, 8842);
        this.addEffect(11664, 8842);
        this.addEffect(11665, 8842);
        this.addEffect(8839, 8842);
        this.addEffect(19785, 8842);
        this.addEffect(19787, 8842);
        this.addEffect(19789, 8842);
        this.addEffect(8840, 8842);
        this.addEffect(19786, 8842);
        this.addEffect(19788, 8842);
        this.addEffect(19790, 8842);
        this.addEffect(11128, "Provides 10% more damage when using obsidian weapons.");
        this.addEffect(13736, "Operate option deflects 25% of any next incoming hit.");
        this.addEffect(13738, "Deflects 10% melee damage. Operate option deflects 25% of any next incoming hit.");
        this.addEffect(13740, "Deflects 10% range damage. Operate option deflects 25% of any next incoming hit.");
        this.addEffect(13742, "Deflects 10% magic damage. Operate option deflects 25% of any next incoming hit.");
        this.addEffect(13744, "Increases maximum hitpoints by 11%.");
        this.addEffect(19669, "Special attacks require 10% less energy.");
        final String dfs = "Can be charged with dragonfire. Clicking operate when equipped unleashes the fire.";
        this.addEffect(11283, dfs);
        this.addEffect(11284, dfs);
        this.addEffect(11285, dfs);
        this.addEffect(15486, "Special attack reduces damage taken by 20% for the duration of 1 minute.");
        final String crystalBow = "Doesn't require arrows and degrades with every shot.";
        for (int i = 4212; i <= 4223; ++i) {
            this.addEffect(i, crystalBow);
        }
        this.addEffect(13263, "Gives 10% bonus damage and accuracy when fighting slayer tasks. Also increases slayer experience gains by 10%.");
        this.addEffect(15492, "Gives 15% bonus damage and accuracy when fighting slayer tasks. Also increases slayer experience gains by 15%.");
        this.addEffect(8921, "Gives 15% bonus damage and accuracy when fighting slayer tasks with melee.");
        this.addEffect(15490, "Gives 15% bonus damage and accuracy when fighting slayer tasks with range.");
        this.addEffect(15488, "Gives 15% bonus damage and accuracy when fighting slayer tasks with magic.");
        for (int i = 9747; i <= 9812; ++i) {
            final String name = ItemDefinition.forID(i).name;
            final String desc = name.replace("(t)", "").replace("cape", "").replace("hood", "").replaceAll("  ", " ").trim();
            this.addEffect(i, "Requires level 99 " + GameClient.capitalize(desc) + " to be worn.");
        }
        for (int i = 22435; i <= 22456; ++i) {
            final String name = ItemDefinition.forID(i).name;
            final String desc = name.replace("master", "").replace("cape", "").replace("hood", "").replaceAll("  ", " ").trim();
            this.addEffect(i, "Requires 200M " + GameClient.capitalize(desc) + " exp. to be worn.");
        }
        this.addEffect(22400, "Requires level 99 Magic and Runecrafting to be worn.");
        this.addEffect(22401, "Requires level 99 Crafting and Fletching to be worn.");
        this.addEffect(22402, "Requires level 99 Herblore and Farming to be worn.");
        this.addEffect(22403, "Requires level 99 Woodcutting and Firemaking to be worn.");
        this.addEffect(22404, "Requires level 99 Slayer, Hunter and Range to be worn.");
        this.addEffect(22405, "Requires level 99 Mining and Smithing to be worn.");
        this.addEffect(22406, "Requires level 99 Attack, Strength and Hitpoints to be worn.");
        this.addEffect(22407, "Requires level 99 Fishing and Cooking to be worn.");
        this.addEffect(22408, "Requires level 99 Defence and Prayer to be worn.");
        this.addEffect(22409, "Requires level 99 Agility and Thieving to be worn.");
        this.addEffect(9240, "Drains prayer points from human opponents.");
        this.addEffect(9241, "Has the chance to poison.");
        this.addEffect(9242, "Does additional 5% of the target's current health (max +30).");
        this.addEffect(9243, "Lowers the enemy's defence level.");
        this.addEffect(9244, "Inflicts additional dragon breath damage (max +25)");
        this.addEffect(9245, "Inflicts additional damage and heals you for 25% of the hit.");
        this.addEffect(22633, "Increases the duration of freezing abilities by 5 seconds.");
        this.addEffect(22635, "Decreases the duration of freezes cast on you by 5 seconds.");
    }
}
