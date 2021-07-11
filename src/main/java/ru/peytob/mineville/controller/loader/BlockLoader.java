package ru.peytob.mineville.controller.loader;

import ru.peytob.mineville.model.game.BlockRepository;
import ru.peytob.mineville.model.game.Resources;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.object.BlockBuilder;
import ru.peytob.mineville.model.graphic.*;
import ru.peytob.mineville.model.opengl.Texture;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class BlockLoader {
    private final Resources resources;
    private final Map<String, BlockModel> models;
    private final TexturesPack texturesPack;

    public BlockLoader(Resources resources) {
        this.resources = resources;
        this.models = new HashMap<>();
        this.texturesPack = loadTextures();
        this.resources.setTexturesPack(this.texturesPack);
    }

    public TexturesPack loadTextures() {
        final String folder = "src/main/resources/images/blocks/";

        ImageLoader imageLoader = new ImageLoader();

        Texture tilemapTexture;
        Image tilemapImage;
        try {
            tilemapImage = imageLoader.loadFromFile(folder + "tilemap.png");
            tilemapTexture = new Texture(tilemapImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        TexturesPack pack = new TexturesPack(16, tilemapTexture,
                16.0f / tilemapImage.getWidth(), 16.0f / tilemapImage.getHeight());

        pack.registerTile("grass_side", 0, 0);
        pack.registerTile("grass_top", 1, 1);
        pack.registerTile("grass_bottom", 2, 2);

        return pack;
    }

    public void loadBlocks() {
        loadModel(); // Now just add one model to models.

        BlockBuilder air = new BlockBuilder();
        air.setModel(models.get("solidFullBlock"));
        air.setId(0);
        air.setTextId("air");
        resources.getBlockRepository().addBlock(new Block(air));

        BlockBuilder grass = new BlockBuilder();
        grass.setModel(models.get("solidFullBlock"));
        grass.setId(1);
        grass.setTextId("grass");
        resources.getBlockRepository().addBlock(new Block(grass));
    }

    private void loadModel() {
        Float[] northSide = new Float[]{
                0.5f, 0.5f, 0.5f, // position
                0.0f, 0.0f, 1.0f, // normal
                1.0f, 0.0f, // texture

                -0.5f, 0.5f, 0.5f, // position
                0.0f, 0.0f, 1.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, -0.5f, 0.5f, // position
                0.0f, 0.0f, 1.0f, // normal
                0.0f, 1.0f, // texture

                0.5f, 0.5f, 0.5f, // position
                0.0f, 0.0f, 1.0f, // normal
                1.0f, 0.0f, // texture

                -0.5f, -0.5f, 0.5f, // position
                0.0f, 0.0f, 1.0f, // normal
                0.0f, 1.0f, // texture

                0.5f, -0.5f, 0.5f, // position
                0.0f, 0.0f, 1.0f, // normal
                1.0f, 1.0f, // texture
        };

        Float[] southSide = new Float[]{
                -0.5f, 0.5f, -0.5f, // position
                0.0f, 0.0f, -1.0f, // normal
                1.0f, 0.0f, // texture

                0.5f, 0.5f, -0.5f, // position
                0.0f, 0.0f, -1.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, -0.5f, -0.5f, // position
                0.0f, 0.0f, -1.0f, // normal
                0.0f, 1.0f, // texture

                -0.5f, 0.5f, -0.5f, // position
                0.0f, 0.0f, -1.0f, // normal
                1.0f, 0.0f, // texture

                0.5f, -0.5f, -0.5f, // position
                0.0f, 0.0f, -1.0f, // normal
                0.0f, 1.0f, // texture

                -0.5f, -0.5f, -0.5f, // position
                0.0f, 0.0f, -1.0f, // normal
                1.0f, 1.0f, // texture
        };

        Float[] westSide = new Float[]{
                -0.5f, 0.5f, 0.5f, // position
                -1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, 0.5f, -0.5f, // position
                -1.0f, 0.0f, 0.0f, // normal
                1.0f, 0.0f, // texture

                -0.5f, -0.5f, -0.5f, // position
                -1.0f, 0.0f, 0.0f, // normal
                1.0f, 1.0f, // texture

                -0.5f, 0.5f, 0.5f, // position
                -1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, -0.5f, -0.5f, // position
                -1.0f, 0.0f, 0.0f, // normal
                1.0f, 1.0f, // texture

                -0.5f, -0.5f, 0.5f, // position
                -1.0f, 0.0f, 0.0f, // normal
                0.0f, 1.0f, // texture
        };

        Float[] eastSide = new Float[]{
                0.5f, 0.5f, -0.5f, // position
                1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, 0.5f, 0.5f, // position
                1.0f, 0.0f, 0.0f, // normal
                1.0f, 0.0f, // texture

                0.5f, -0.5f, 0.5f, // position
                1.0f, 0.0f, 0.0f, // normal
                1.0f, 1.0f, // texture

                0.5f, 0.5f, -0.5f, // position
                1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, -0.5f, 0.5f, // position
                1.0f, 0.0f, 0.0f, // normal
                1.0f, 1.0f, // texture

                0.5f, -0.5f, -0.5f, // position
                1.0f, 0.0f, 0.0f, // normal
                0.0f, 1.0f, // texture
        };

        Float[] topSide = new Float[]{
                -0.5f, 0.5f, -0.5f, // position
                0.0f, 1.0f, 0.0f, // normal
                1.0f, 1.0f, // texture

                -0.5f, 0.5f, 0.5f, // position
                0.0f, 1.0f, 0.0f, // normal
                1.0f, 0.0f, // texture

                0.5f, 0.5f, 0.5f, // position
                0.0f, 1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, 0.5f, -0.5f, // position
                0.0f, 1.0f, 0.0f, // normal
                1.0f, 1.0f, // texture

                0.5f, 0.5f, 0.5f, // position
                0.0f, 1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, 0.5f, -0.5f, // position
                0.0f, 1.0f, 0.0f, // normal
                0.0f, 1.0f, // texture
        };

        Float[] bottomSide = new Float[]{
                0.5f, -0.5f, -0.5f, // position
                0.0f, -1.0f, 0.0f, // normal
                1.0f, 1.0f, // texture

                0.5f, -0.5f, 0.5f, // position
                0.0f, -1.0f, 0.0f, // normal
                1.0f, 0.0f, // texture

                -0.5f, -0.5f, 0.5f, // position
                0.0f, -1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, -0.5f, -0.5f, // position
                0.0f, -1.0f, 0.0f, // normal
                1.0f, 1.0f, // texture

                -0.5f, -0.5f, 0.5f, // position
                0.0f, -1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, -0.5f, -0.5f, // position
                0.0f, -1.0f, 0.0f, // normal
                0.0f, 1.0f, // texture
        };

        TextureTile side = texturesPack.getTile("grass_side");
        TextureTile top = texturesPack.getTile("grass_top");
        TextureTile bottom = texturesPack.getTile("grass_bottom");
        BlockTexture cubeTexture = new BlockTexture(side, side, side, side, top, bottom);
        BlockModel cubeModel = new BlockModel(northSide, southSide, westSide, eastSide, topSide, bottomSide, cubeTexture);

        models.put("solidFullBlock", cubeModel);
    }
}
