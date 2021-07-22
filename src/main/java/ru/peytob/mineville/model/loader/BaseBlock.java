package ru.peytob.mineville.model.loader;

import ru.peytob.mineville.model.graphic.block.BlockModel;
import ru.peytob.mineville.model.graphic.block.BlockTexture;

public class BaseBlock {
    private String unlocalizedName;
    private String registryName;
    private BlockModel model;
    private BlockTexture texture;

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public void setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
    }

    public String getRegistryName() {
        return registryName;
    }

    public void setRegistryName(String registryName) {
        this.registryName = registryName;
    }

    public BlockModel getModel() {
        return model;
    }

    public void setModel(BlockModel model) {
        this.model = model;
    }

    public BlockTexture getTexture() {
        return texture;
    }

    public void setTexture(BlockTexture texture) {
        this.texture = texture;
    }
}
