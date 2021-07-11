package ru.peytob.mineville.model.graphic;

public class BlockTexture {
    private final TextureTile northFace;
    private final TextureTile southFace;
    private final TextureTile westFace;
    private final TextureTile eastFace;
    private final TextureTile topFace;
    private final TextureTile bottomFace;

    public BlockTexture(TextureTile northFace, TextureTile southFace, TextureTile westFace,
                        TextureTile eastFace,  TextureTile topFace, TextureTile bottomFace) {
        this.northFace = northFace;
        this.southFace = southFace;
        this.westFace = westFace;
        this.eastFace = eastFace;
        this.topFace = topFace;
        this.bottomFace = bottomFace;
    }

    public TextureTile getNorthFace() {
        return northFace;
    }

    public TextureTile getSouthFace() {
        return southFace;
    }

    public TextureTile getWestFace() {
        return westFace;
    }

    public TextureTile getEastFace() {
        return eastFace;
    }

    public TextureTile getTopFace() {
        return topFace;
    }

    public TextureTile getBottomFace() {
        return bottomFace;
    }
}
