package ru.peytob.mineville.model.graphic.block;

public class BlockModel {
    private final BlockFace northFace;
    private final BlockFace southFace;
    private final BlockFace westFace;
    private final BlockFace eastFace;
    private final BlockFace topFace;
    private final BlockFace bottomFace;

    public BlockModel(BlockFace northFace, BlockFace southFace, BlockFace westFace, BlockFace eastFace,
                      BlockFace topFace, BlockFace bottomFace) {
        this.northFace = northFace;
        this.southFace = southFace;
        this.westFace = westFace;
        this.eastFace = eastFace;
        this.topFace = topFace;
        this.bottomFace = bottomFace;
    }

    public BlockFace getNorthFace() {
        return northFace;
    }

    public BlockFace getSouthFace() {
        return southFace;
    }

    public BlockFace getWestFace() {
        return westFace;
    }

    public BlockFace getEastFace() {
        return eastFace;
    }

    public BlockFace getTopFace() {
        return topFace;
    }

    public BlockFace getBottomFace() {
        return bottomFace;
    }
}
