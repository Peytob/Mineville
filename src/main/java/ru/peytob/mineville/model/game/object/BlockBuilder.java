package ru.peytob.mineville.model.game.object;

import ru.peytob.mineville.model.graphic.BlockModel;

public class BlockBuilder {
    private int id;
    private BlockModel blockModel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BlockModel getModel() {
        return blockModel;
    }

    public void setModel(BlockModel blockModel) {
        this.blockModel = blockModel;
    }
}
