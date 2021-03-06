package ru.peytob.mineville.resources.block;

import ru.peytob.mineville.model.annotation.Block;
import ru.peytob.mineville.model.graphic.TextureTile;
import ru.peytob.mineville.model.graphic.TexturesPack;
import ru.peytob.mineville.model.graphic.block.BlockTexture;
import ru.peytob.mineville.model.loader.base.BaseBlock;
import ru.peytob.mineville.model.repository.GameRegistry;

@Block
public class Stone extends BaseBlock {
    public Stone() {
        this.setUnlocalizedName("stone");
        this.setRepositoryName("stone");
        this.setModel(GameRegistry.getInstance().getModelRepository().get("mineville::solidCube"));

        TexturesPack texturesPack = GameRegistry.getInstance().getTexturesPackRepository().get("default");
        TextureTile stone = texturesPack.getTile("mineville::stone");

        BlockTexture stoneTexture = new BlockTexture(
                stone,
                stone,
                stone,
                stone,
                stone,
                stone
        );

        setTexture(stoneTexture);
    }
}
