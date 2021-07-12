package ru.peytob.mineville.model.graphic.block;

public class BlockFacePoints {
    public static final int POSITION_X = 0;
    public static final int POSITION_Y = 1;
    public static final int POSITION_Z = 2;

    public static final int NORMAL_X = 3;
    public static final int NORMAL_Y = 4;
    public static final int NORMAL_Z = 5;

    public static final int TEXTURE_X = 6;
    public static final int TEXTURE_Y = 7;

    public static final int VERTEX_SIZE_FLOATS = 3 + 3 + 2;
    public static final int VERTEX_SIZE_BYTES = VERTEX_SIZE_FLOATS * Float.BYTES;

    private final Float[] data;

    public BlockFacePoints(Float[] data) {
        this.data = data;
    }

    public Float[] getData() {
        return data;
    }
}
