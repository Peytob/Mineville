package ru.peytob.mineville.model.loader;

import ru.peytob.mineville.model.builder.ImageBuilder;
import ru.peytob.mineville.model.builder.TextureBlockAtlasBuilder;
import ru.peytob.mineville.model.game.Resources;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.object.BlockBuilder;
import ru.peytob.mineville.model.graphic.Image;
import ru.peytob.mineville.model.graphic.TextureBlockAtlas;
import ru.peytob.mineville.model.graphic.block.BlockFace;
import ru.peytob.mineville.model.graphic.block.BlockFacePoints;
import ru.peytob.mineville.model.graphic.block.BlockModel;
import ru.peytob.mineville.model.graphic.block.BlockTexture;
import ru.peytob.mineville.model.graphic.shader.ShadersPack;
import ru.peytob.mineville.model.opengl.Texture;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class DefaultResourcesLoader {
    private final Resources resources;
    private final ModelLoader modelLoader;

    public DefaultResourcesLoader() {
        this.resources = new Resources();
        this.modelLoader = new ModelLoader();
    }

    public Resources getResources() {
        return resources;
    }

    public void loadResources() throws IOException {
        loadShadersPack();
        loadBlocksModels();
        loadTexturesBlockAtlas();
        loadBlocks();
    }

    private void loadShadersPack() throws IOException {
        ShadersLoader shadersLoader = new ShadersLoader();
        File shadersFolder = new File(resourceToURI("/shader"));
        resources.setShadersPack(shadersLoader.loadShaderPack(shadersFolder));
    }

    private void loadBlocksModels() throws IOException {
        File blockModelsFolder = new File(resourceToURI("/model/block"));

        for (File fileEntry: Objects.requireNonNull(blockModelsFolder.listFiles())) {
            modelLoader.loadModel(fileEntry);
        }
    }

    private void loadTexturesBlockAtlas() throws IOException {
        TextureBlockAtlasBuilder textureBlockAtlasBuilder = new TextureBlockAtlasBuilder(16);
        ImageLoader imageLoader = new ImageLoader();

        File blocksTexturesFolder = new File(resourceToURI("/images/blocks"));

        for (File file: Objects.requireNonNull(blocksTexturesFolder.listFiles())) {
            String modelName = file.getName();
            int pointIndex = modelName.lastIndexOf('.');
            if (pointIndex != -1) {
                modelName = file.getName().substring(0, pointIndex);
            }

            Image image = imageLoader.loadFromFile(file.getPath());
            textureBlockAtlasBuilder.putTile(modelName, image);
        }

        resources.setTexturesPack(textureBlockAtlasBuilder.buildAtlas());
    }

    private void loadBlocks() {
        BlockUntexturedModelGson untexturedModel = modelLoader.get("solidCube");
        TextureBlockAtlas atlas = resources.getTexturesPack();

        BaseBlock grass = new BaseBlock();
        BlockTexture grassTexture = new BlockTexture(
                atlas.getTile("grass_side"),
                atlas.getTile("grass_side"),
                atlas.getTile("grass_side"),
                atlas.getTile("grass_side"),
                atlas.getTile("grass_top"),
                atlas.getTile("dirt")
        );
        BlockModel grassModel = new BlockModel(
                new BlockFace(new BlockFacePoints(untexturedModel.faces.north)),
                new BlockFace(new BlockFacePoints(untexturedModel.faces.south)),
                new BlockFace(new BlockFacePoints(untexturedModel.faces.west)),
                new BlockFace(new BlockFacePoints(untexturedModel.faces.east)),
                new BlockFace(new BlockFacePoints(untexturedModel.faces.top)),
                new BlockFace(new BlockFacePoints(untexturedModel.faces.bottom))
        );
        grass.setModel(grassModel);
        grass.setTexture(grassTexture);
        grass.setRegistryName("grass");
        grass.setUnlocalizedName("grass");
        resources.getBlockRepository().addBlock(new Block(1, grass));

        BaseBlock stone = new BaseBlock();
        BlockTexture stoneTexture = new BlockTexture(
                atlas.getTile("stone"),
                atlas.getTile("stone"),
                atlas.getTile("stone"),
                atlas.getTile("stone"),
                atlas.getTile("stone"),
                atlas.getTile("stone")
        );
        BlockModel stoneModel = new BlockModel(
                new BlockFace(new BlockFacePoints(untexturedModel.faces.north)),
                new BlockFace(new BlockFacePoints(untexturedModel.faces.south)),
                new BlockFace(new BlockFacePoints(untexturedModel.faces.west)),
                new BlockFace(new BlockFacePoints(untexturedModel.faces.east)),
                new BlockFace(new BlockFacePoints(untexturedModel.faces.top)),
                new BlockFace(new BlockFacePoints(untexturedModel.faces.bottom))
        );
        stone.setModel(stoneModel);
        stone.setTexture(stoneTexture);
        stone.setRegistryName("stone");
        stone.setUnlocalizedName("stone");
        resources.getBlockRepository().addBlock(new Block(2, stone));
    }

    private URI resourceToURI(String name) throws IOException {
        URI uri;
        try {
            uri = Objects.requireNonNull(getClass().getResource(name)).toURI();
        } catch (URISyntaxException e) {
            throw new IOException("Can't find resource: " + name);
        }
        return uri;
    }
}
