package ru.peytob.mineville.model.builder;

import ru.peytob.mineville.math.CoordinatesUtils;
import ru.peytob.mineville.model.graphic.Image;

import java.nio.ByteBuffer;

public class ImageBuilder {
    private Integer width;
    private Integer height;
    private ByteBuffer data;

    public ImageBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = ByteBuffer.allocateDirect(Image.BYTES_PER_PIXEL * width * height);
    }

    public ImageBuilder(int width, int height, byte backgroundRed, byte backgroundGreen, byte backgroundBlue,
                        byte backgroundAlpha) {
        this(width, height);

        for (int index = 0; index < data.limit(); index += 4) {
            setPixel(index, backgroundRed, backgroundGreen, backgroundBlue, backgroundAlpha);
        }
    }

    public void insert(Image image, int x, int y) {
        for (int sx = 0, tx = x; sx < image.getWidth() && tx < width; sx++, tx++) {
            for (int sy = 0, ty = y; sy < image.getHeight() && ty < height; sy++, ty++) {
                int imageIndex = CoordinatesUtils.toFlat2D(sx, sy, image.getWidth()) * 4;
                byte r = image.getData().get(imageIndex + Image.RED_BYTE_OFFSET);
                byte g = image.getData().get(imageIndex + Image.GREEN_BYTE_OFFSET);
                byte b = image.getData().get(imageIndex + Image.BLUE_BYTE_OFFSET);
                byte a = image.getData().get(imageIndex + Image.ALPHA_BYTE_OFFSET);
                setPixel(tx, ty, r, g, b, a);
            }
        }
    }

    public void setPixel(int x, int y, byte r, byte g, byte b, byte a) {
        setPixel(CoordinatesUtils.toFlat2D(x, y, width) * 4, r, g, b, a);
    }

    private void setPixel(int index, byte r, byte g, byte b, byte a) {
        data.put(index + Image.RED_BYTE_OFFSET, r);
        data.put(index + Image.GREEN_BYTE_OFFSET, g);
        data.put(index + Image.BLUE_BYTE_OFFSET, b);
        data.put(index + Image.ALPHA_BYTE_OFFSET, a);
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Image buildImage() {
        return new Image(data, width, height);
    }
}
