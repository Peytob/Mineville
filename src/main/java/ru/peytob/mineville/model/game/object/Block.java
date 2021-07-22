package ru.peytob.mineville.model.game.object;

import ru.peytob.mineville.model.graphic.block.BlockModel;
import ru.peytob.mineville.model.graphic.block.BlockTexture;
import ru.peytob.mineville.model.loader.BaseBlock;

public class Block {
    private final Integer id;
    private final String registryName;
    private final String unlocalizedName;
    private final BlockModel model;
    private final BlockTexture texture;

    public Block(BlockBuilder builder) {
        this.id = builder.getId();
        this.model = builder.getModel();
        this.registryName = builder.getTextId();
        this.unlocalizedName = "From builder.";
        this.texture = builder.getTexture();
    }

    public Block(Integer id, BaseBlock baseBlock) {
        this.id = id;
        this.model = baseBlock.getModel();
        this.registryName = baseBlock.getRegistryName();
        this.unlocalizedName = baseBlock.getUnlocalizedName();
        this.texture = baseBlock.getTexture();
    }

    public int getId() {
        return id;
    }

    public BlockModel getModel() {
        return model;
    }

    public String getRegistryName() {
        return registryName;
    }

    public BlockTexture getTexture() {
        return texture;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }
}
