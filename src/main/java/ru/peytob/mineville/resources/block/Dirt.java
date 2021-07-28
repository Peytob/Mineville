package ru.peytob.mineville.resources.block;

import ru.peytob.mineville.model.annotation.Block;
import ru.peytob.mineville.model.graphic.TextureTile;
import ru.peytob.mineville.model.graphic.TexturesPack;
import ru.peytob.mineville.model.graphic.block.BlockTexture;
import ru.peytob.mineville.model.loader.base.BaseBlock;
import ru.peytob.mineville.model.repository.GameRegistry;

@Block
public class Dirt extends BaseBlock {
    public Dirt() {
        this.setUnlocalizedName("dirt");
        this.setRepositoryName("dirt");
        this.setModel(GameRegistry.getInstance().getModelRepository().get("mineville::solidCube"));

        TexturesPack texturesPack = GameRegistry.getInstance().getTexturesPackRepository().get("default");
        TextureTile dirt = texturesPack.getTile("mineville::dirt");

        BlockTexture dirtTexture = new BlockTexture(
                dirt,
                dirt,
                dirt,
                dirt,
                dirt,
                dirt
        );

        setTexture(dirtTexture);
    }
}
