package ru.peytob.mineville.model.graphic.block;

import ru.peytob.mineville.model.graphic.TextureTile;

public class BlockFace {
    private final BlockFacePoints points;
    private final TextureTile textureTile;

    public BlockFace(BlockFacePoints points, TextureTile textureTile) {
        this.points = points;
        this.textureTile = textureTile;
    }

    public BlockFacePoints getPoints() {
        return points;
    }

    public TextureTile getTextureTile() {
        return textureTile;
    }
}
