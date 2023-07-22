package com.client.client;

import com.client.Client;

public final class TextureUtils {

    private static int[] pixels = new int[16384];

    public static void animate(TextureAnimating.Data data) {
        IndexedImage indexedImage = Rasterizer.textures[data.getId()];
        int indexes = indexedImage.imgWidth * indexedImage.imgHeight - 1;
        int noise = indexedImage.imgWidth * Client.instance.cycleTimer * data.getSpeed();
        int[] current = indexedImage.imgPixels;
        int[] next = pixels;
        for (int i2 = 0; i2 <= indexes; i2++)
            next[i2] = current[i2 - noise & indexes];

        indexedImage.imgPixels = next;
        pixels = current;
        Rasterizer.method370(data.getId());
    }

    public static void animate(int id) {
        TextureAnimating.Data data = TextureAnimating.Data.getById(id);
        if (data == null) {
            return;
        }
        animate(data);
    }
}
