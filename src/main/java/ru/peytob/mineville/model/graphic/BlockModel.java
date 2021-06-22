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

    public BlockModel(Float[] northFace, Float[] southFace, Float[] westFace, Float[] eastFace,
                      Float[] topFace, Float[] bottomFace) {
        this.northFace = northFace;
        this.southFace = southFace;
        this.westFace = westFace;
        this.eastFace = eastFace;
        this.topFace = topFace;
        this.bottomFace = bottomFace;
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
}
