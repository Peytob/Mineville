package ru.peytob.mineville.model.loader.base;

import ru.peytob.mineville.model.graphic.block.BlockModel;
import ru.peytob.mineville.model.graphic.block.BlockTexture;

/**
 * Base class for all blocks.
 * If you need to implement a block with complex logic (for example, a specific event handling), you must inherit from
 * this class. If it is not needed, it is permissible to create objects of this class and implement them. However,
 * this approach is not recommended.
 */
public class BaseBlock {
    private String unlocalizedName;
    private String repositoryName;
    private BlockModel model;
    private BlockTexture texture;

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public void setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
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
