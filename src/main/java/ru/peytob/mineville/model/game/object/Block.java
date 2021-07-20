package ru.peytob.mineville.model.game.object;

import ru.peytob.mineville.model.graphic.block.BlockModel;
import ru.peytob.mineville.model.graphic.block.BlockTexture;

public class Block {
    private final Integer id;
    private final String textId;
    private final BlockModel model;
    private final BlockTexture texture;

    public Block(BlockBuilder builder) {
        this.id = builder.getId();
        this.model = builder.getModel();
        this.textId = builder.getTextId();
        this.texture = builder.getTexture();
    }

    public int getId() {
        return id;
    }

    public BlockModel getModel() {
        return model;
    }

    public String getTextId() {
        return textId;
    }

    public BlockTexture getTexture() {
        return texture;
    }
}
