package ru.peytob.mineville.model.graphic;

import java.nio.ByteBuffer;

public class Image {
    public static final int BYTES_PER_PIXEL = 4;
    public static final int RED_BYTE_OFFSET = 0;
    public static final int GREEN_BYTE_OFFSET = 1;
    public static final int BLUE_BYTE_OFFSET = 2;
    public static final int ALPHA_BYTE_OFFSET = 3;

    private final ByteBuffer data;
    private final Integer height;
    private final Integer width;

    public Image(ByteBuffer data, Integer width, Integer height) {
        this.data = data;
        this.height = height;
        this.width = width;
    }

    public ByteBuffer getData() {
        return data;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }
}
