package ru.peytob.mineville.model.graphic;

import java.nio.ByteBuffer;

public class Image {
    public static final int BYTES_PER_PIXEL = 4;

    private final ByteBuffer data;
    private final Integer height;
    private final Integer width;

    public Image(ByteBuffer data, Integer height, Integer width) {
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
