package ru.peytob.mineville.resources.block;

import ru.peytob.mineville.model.annotation.Block;
import ru.peytob.mineville.model.graphic.TextureTile;
import ru.peytob.mineville.model.graphic.TexturesPack;
import ru.peytob.mineville.model.graphic.block.BlockTexture;
import ru.peytob.mineville.model.loader.base.BaseBlock;
import ru.peytob.mineville.model.repository.GameRegistry;

@Block
public class Sand extends BaseBlock {
    public Sand() {
        this.setUnlocalizedName("sand");
        this.setRepositoryName("sand");
        this.setModel(GameRegistry.getInstance().getModelRepository().get("mineville::solidCube"));

        TexturesPack texturesPack = GameRegistry.getInstance().getTexturesPackRepository().get("default");
        TextureTile sand = texturesPack.getTile("mineville::sand");

        BlockTexture sandTexture = new BlockTexture(
                sand,
                sand,
                sand,
                sand,
                sand,
                sand
        );

        setTexture(sandTexture);
    }
}
