package com.client.client;

import java.util.Arrays;

public class TextureAnimating {

    enum Data {
        T1(1),
        WEED(14, 1),
        RAINBOW(9, 3),
        RAIN(17),
        GRAY_RADIO(18,-5),
        ICE_SHATTER(13,-7),
        T4(25),
        T5(31),
        T6(34),
        T7(40,8),
        FIRE_CAPE(40,-7),
        BLAC_YELLOW(50),
        SKYFIRE_TEXTURE(51),
        T9(52, 4),
        T10(53),
        T11(54, 2),
        T12(55, 4),
        YELLOW_LAVA(56, 4),
        T14(57),
        T15(58),
        T16(59),
        T17(60,3),
        ORANGE_LAVA(61),
        T19(62),
        T20(63,3),
        T21(64),
        T22(65),
        GRREENLAVA(66, 3),
        T23(67, 4),
        BLACK_ORANGE(68,5),
        LIGHTBLUE_GREEN_LAVA(69, 3),
    	LIGHT_BLUE_LAVA(70, 3),
        PINK(73,4),
        LIGHTNING(75, 2),
        BLACK_GREY(76, 2),
        T77(77, 2),
        T78(78, 3),
        T79(79, 2),
        T80(80, 2),
        T81(81,2),
        ICE_BLUE(82, 2),
        RAINBOWSP(83, 2),
        BLAC_GREEN(84,2),
        GALAXY(85, 4),
        MDMACUST(86, 2),
        LORIENTEXT2(87, 2),
        LORIENTEXT(89, 2);

        private static Data[] VALUES = values();

        public static Data getById(int id) {
            return Arrays.stream(VALUES)
                    .filter(data -> data.getId() == id)
                    .findFirst()
                    .orElse(null);
        }


        Data(int id) {
            this.id = id;
            this.speed = 1;
        }

        Data(int id, int speed) {
            this.id = id;
            this.speed = speed;
        }

        private int id;
        private int speed;

        public int getId() {
            return id;
        }

        public int getSpeed() {
            return speed;
        }
    }

    public static void animateTextures(int id) {
        try {
            for (Data data : Data.VALUES) {
                if (Rasterizer.anIntArray1480[data.id] >= data.id) {
                    TextureUtils.animate(data);  
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
