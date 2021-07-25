package ru.peytob.mineville.model.graphic.block;

import ru.peytob.mineville.model.loader.base.BaseBlockModel;
import ru.peytob.mineville.model.repository.AbstractRegistrable;

public class BlockModel extends AbstractRegistrable {
    private final BlockFace northFace;
    private final BlockFace southFace;
    private final BlockFace westFace;
    private final BlockFace eastFace;
    private final BlockFace topFace;
    private final BlockFace bottomFace;

    public BlockModel(Integer id, BaseBlockModel baseModel) {
        super(baseModel.getRepositoryName(), id);
        this.northFace = baseModel.getNorthFace();
        this.southFace = baseModel.getSouthFace();
        this.westFace = baseModel.getWestFace();
        this.eastFace = baseModel.getEastFace();
        this.topFace = baseModel.getTopFace();
        this.bottomFace = baseModel.getBottomFace();
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
