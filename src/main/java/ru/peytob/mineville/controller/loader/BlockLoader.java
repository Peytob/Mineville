package ru.peytob.mineville.controller.loader;

import ru.peytob.mineville.model.game.BlockRepository;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.object.BlockBuilder;
import ru.peytob.mineville.model.graphic.BlockModel;

import java.util.HashMap;
import java.util.Map;

class BlockLoader {
    private final BlockRepository target;
    private final Map<String, BlockModel> models;

    public BlockLoader(BlockRepository repository) {
        this.target = repository;
        this.models = new HashMap<>();
    }

    public void loadBlocks() {
        loadModel(); // Now just add one model to models.

        BlockBuilder air = new BlockBuilder();
        air.setModel(models.get("solidFullBlock"));
        air.setId(0);
        air.setTextId("air");
        target.addBlock(new Block(air));

        BlockBuilder grass = new BlockBuilder();
        grass.setModel(models.get("solidFullBlock"));
        grass.setId(1);
        grass.setTextId("grass");
        target.addBlock(new Block(grass));
    }

    private void loadModel() {
        Float[] northSide = new Float[]{
                0.5f, 0.5f, 0.5f, // position
                0.0f, 0.0f, 1.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, 0.5f, 0.5f, // position
                0.0f, 0.0f, 1.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, -0.5f, 0.5f, // position
                0.0f, 0.0f, 1.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, 0.5f, 0.5f, // position
                0.0f, 0.0f, 1.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, -0.5f, 0.5f, // position
                0.0f, 0.0f, 1.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, -0.5f, 0.5f, // position
                0.0f, 0.0f, 1.0f, // normal
                0.0f, 0.0f, // texture
        };

        Float[] southSide = new Float[]{
                -0.5f, 0.5f, -0.5f, // position
                0.0f, 0.0f, -1.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, 0.5f, -0.5f, // position
                0.0f, 0.0f, -1.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, -0.5f, -0.5f, // position
                0.0f, 0.0f, -1.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, 0.5f, -0.5f, // position
                0.0f, 0.0f, -1.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, -0.5f, -0.5f, // position
                0.0f, 0.0f, -1.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, -0.5f, -0.5f, // position
                0.0f, 0.0f, -1.0f, // normal
                0.0f, 0.0f, // texture
        };

        Float[] westSide = new Float[]{
                -0.5f, 0.5f, 0.5f, // position
                -1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, 0.5f, -0.5f, // position
                -1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, -0.5f, -0.5f, // position
                -1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, 0.5f, 0.5f, // position
                -1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, -0.5f, -0.5f, // position
                -1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, -0.5f, 0.5f, // position
                -1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture
        };

        Float[] eastSide = new Float[]{
                0.5f, 0.5f, -0.5f, // position
                1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, 0.5f, 0.5f, // position
                1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, -0.5f, 0.5f, // position
                1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, 0.5f, -0.5f, // position
                1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, -0.5f, 0.5f, // position
                1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, -0.5f, -0.5f, // position
                1.0f, 0.0f, 0.0f, // normal
                0.0f, 0.0f, // texture
        };

        Float[] topSide = new Float[]{
                -0.5f, 0.5f, -0.5f, // position
                0.0f, 1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, 0.5f, 0.5f, // position
                0.0f, 1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, 0.5f, 0.5f, // position
                0.0f, 1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, 0.5f, -0.5f, // position
                0.0f, 1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, 0.5f, 0.5f, // position
                0.0f, 1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, 0.5f, -0.5f, // position
                0.0f, 1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture
        };

        Float[] bottomSide = new Float[]{
                0.5f, -0.5f, -0.5f, // position
                0.0f, -1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, -0.5f, 0.5f, // position
                0.0f, -1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, -0.5f, 0.5f, // position
                0.0f, -1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                0.5f, -0.5f, -0.5f, // position
                0.0f, -1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, -0.5f, 0.5f, // position
                0.0f, -1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, -0.5f, -0.5f, // position
                0.0f, -1.0f, 0.0f, // normal
                0.0f, 0.0f, // texture
        };

        BlockModel cubeModel = new BlockModel(northSide, southSide, westSide, eastSide, topSide, bottomSide);

        models.put("solidFullBlock", cubeModel);
    }
}
