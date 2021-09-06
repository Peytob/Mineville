package ru.peytob.mineville.resources.block;

import ru.peytob.mineville.model.annotation.Block;
import ru.peytob.mineville.model.graphic.TextureTile;
import ru.peytob.mineville.model.graphic.TexturesPack;
import ru.peytob.mineville.model.graphic.block.BlockTexture;
import ru.peytob.mineville.model.loader.base.BaseBlock;
import ru.peytob.mineville.model.repository.GameRegistry;

@Block
public class Water extends BaseBlock {
    public Water() {
        this.setUnlocalizedName("water");
        this.setRepositoryName("water");
        this.setModel(GameRegistry.getInstance().getModelRepository().get("mineville::solidCube"));

        TexturesPack texturesPack = GameRegistry.getInstance().getTexturesPackRepository().get("default");
        TextureTile water = texturesPack.getTile("mineville::water");

        BlockTexture waterTexture = new BlockTexture(
                water,
                water,
                water,
                water,
                water,
                water
        );

        setTexture(waterTexture);
    }
}
