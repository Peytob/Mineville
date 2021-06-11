package ru.peytob.mineville.model.graphic;

public class BlockModel {
    protected final Float[] northSide;
    protected final Float[] southSide;
    protected final Float[] westSide;
    protected final Float[] eastSide;
    protected final Float[] topSide;
    protected final Float[] bottomSide;

    public BlockModel(Float[] northSide, Float[] southSide, Float[] westSide, Float[] eastSide,
                      Float[] topSide, Float[] bottomSide) {
        this.northSide = northSide;
        this.southSide = southSide;
        this.westSide = westSide;
        this.eastSide = eastSide;
        this.topSide = topSide;
        this.bottomSide = bottomSide;
    }

    public Float[] getNorthSide() {
        return northSide;
    }

    public Float[] getSouthSide() {
        return southSide;
    }

    public Float[] getWestSide() {
        return westSide;
    }

    public Float[] getEastSide() {
        return eastSide;
    }

    public Float[] getTopSide() {
        return topSide;
    }

    public Float[] getBottomSide() {
        return bottomSide;
    }
}
