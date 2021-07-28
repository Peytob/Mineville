package ru.peytob.mineville.resources.block;

import ru.peytob.mineville.model.annotation.Block;
import ru.peytob.mineville.model.graphic.TextureTile;
import ru.peytob.mineville.model.graphic.TexturesPack;
import ru.peytob.mineville.model.graphic.block.BlockTexture;
import ru.peytob.mineville.model.loader.base.BaseBlock;
import ru.peytob.mineville.model.repository.GameRegistry;

@Block
public class Grass extends BaseBlock {
    public Grass() {
        this.setUnlocalizedName("grass");
        this.setRepositoryName("grass");
        this.setModel(GameRegistry.getInstance().getModelRepository().get("mineville::solidCube"));

        TexturesPack texturesPack = GameRegistry.getInstance().getTexturesPackRepository().get("default");
        TextureTile grassSide = texturesPack.getTile("mineville::grass_side");
        TextureTile grassTop = texturesPack.getTile("mineville::grass_top");
        TextureTile dirt = texturesPack.getTile("mineville::dirt");

        BlockTexture grassTexture = new BlockTexture(
                grassSide,
                grassSide,
                grassSide,
                grassSide,
                grassTop,
                dirt
        );

        setTexture(grassTexture);
    }
}
