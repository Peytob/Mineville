package ru.peytob.mineville.model.graphic;

/**
 * Contains all block model faces.
 */
public class BlockModel {
    private final Float[] northFace;
    private final Float[] southFace;
    private final Float[] westFace;
    private final Float[] eastFace;
    private final Float[] topFace;
    private final Float[] bottomFace;
    private final BlockTexture texture;

    public BlockModel(Float[] northFace, Float[] southFace, Float[] westFace, Float[] eastFace,
                      Float[] topFace, Float[] bottomFace, BlockTexture texture) {
        this.northFace = northFace;
        this.southFace = southFace;
        this.westFace = westFace;
        this.eastFace = eastFace;
        this.topFace = topFace;
        this.bottomFace = bottomFace;
        this.texture = texture;
    }

    public Float[] getNorthFace() {
        return northFace;
    }

    public Float[] getSouthFace() {
        return southFace;
    }

    public Float[] getWestFace() {
        return westFace;
    }

    public Float[] getEastFace() {
        return eastFace;
    }

    public Float[] getTopFace() {
        return topFace;
    }

    public Float[] getBottomFace() {
        return bottomFace;
    }

    public BlockTexture getTexture() {
        return texture;
    }
}
