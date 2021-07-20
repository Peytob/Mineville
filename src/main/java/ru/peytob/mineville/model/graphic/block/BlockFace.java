package ru.peytob.mineville.model.graphic.block;

public class BlockFace {
    private final BlockFacePoints points;

    public BlockFace(BlockFacePoints points) {
        this.points = points;
    }

    public BlockFacePoints getPoints() {
        return points;
    }
}
