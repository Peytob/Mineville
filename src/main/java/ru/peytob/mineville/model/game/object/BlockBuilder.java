package ru.peytob.mineville.model.game.object;

import ru.peytob.mineville.model.graphic.block.BlockModel;
import ru.peytob.mineville.model.graphic.block.BlockTexture;

@Deprecated
public class BlockBuilder {
    private Integer id;
    private BlockModel model;
    private String textId;
    private BlockTexture texture;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BlockModel getModel() {
        return model;
    }

    public void setModel(BlockModel blockModel) {
        this.model = blockModel;
    }

    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
    }

    public BlockTexture getTexture() {
        return texture;
    }

    public void setBlockTexture(BlockTexture blockTexture) {
        this.texture = blockTexture;
    }
}
