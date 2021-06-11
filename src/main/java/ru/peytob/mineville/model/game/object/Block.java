package ru.peytob.mineville.model.game.object;

import ru.peytob.mineville.model.graphic.BlockModel;

public class Block {
    private final int id;
    private final BlockModel blockModel;

    public Block(BlockBuilder builder) {
        this.id = builder.getId();
        this.blockModel = builder.getModel();
    }

    public int getId() {
        return id;
    }

    public BlockModel getModel() {
        return blockModel;
    }
}
