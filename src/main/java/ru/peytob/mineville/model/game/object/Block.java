package ru.peytob.mineville.model.game.object;

import ru.peytob.mineville.model.graphic.BlockModel;

public class Block {
    private final Integer id;
    private final String textId;
    private final BlockModel blockModel;

    public Block(BlockBuilder builder) {
        this.id = builder.getId();
        this.blockModel = builder.getModel();
        this.textId = builder.getTextId();
    }

    public int getId() {
        return id;
    }

    public BlockModel getModel() {
        return blockModel;
    }

    public String getTextId() {
        return textId;
    }
}
