package ru.peytob.mineville.model.export;

import ru.peytob.mineville.model.graphic.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;

public class ImageExporter {
    static public void exportAsPng(Image image, String path) throws IOException {
        IntBuffer imageBuffer = image.getData().asIntBuffer();
        int[] integerData = new int[imageBuffer.remaining()];
        imageBuffer.get(integerData);

        // Image stores data as RGBA, but BufferedImage needs data as ARGB
        for (int i = 0; i < integerData.length; i++) {
            int alpha = integerData[i] & 0xFF;
            integerData[i] >>= 8;
            integerData[i] &= ~(0xFF << 24);
            integerData[i] |= (alpha << 24);
        }

        BufferedImage bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        bi.setRGB(0, 0, image.getWidth(), image.getHeight(), integerData, 0, image.getWidth());

        File file = new File(path);
        ImageIO.write(bi, "png", file);
    }
}
