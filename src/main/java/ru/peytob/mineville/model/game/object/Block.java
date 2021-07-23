package ru.peytob.mineville.model.game.object;

import ru.peytob.mineville.model.graphic.block.BlockModel;
import ru.peytob.mineville.model.graphic.block.BlockTexture;
import ru.peytob.mineville.model.loader.BaseBlock;
import ru.peytob.mineville.model.repository.AbstractRegistrable;

public class Block extends AbstractRegistrable {
    private final String unlocalizedName;
    private final BlockModel model;
    private final BlockTexture texture;

    public Block(BlockBuilder builder) {
        super(builder.getTextId(), builder.getId());
        this.model = builder.getModel();
        this.unlocalizedName = "From builder.";
        this.texture = builder.getTexture();
    }

    public Block(Integer id, BaseBlock baseBlock) {
        super(baseBlock.getRepositoryName(), id);
        this.model = baseBlock.getModel();
        this.unlocalizedName = baseBlock.getUnlocalizedName();
        this.texture = baseBlock.getTexture();
    }

    public BlockModel getModel() {
        return model;
    }

    public BlockTexture getTexture() {
        return texture;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }
}
