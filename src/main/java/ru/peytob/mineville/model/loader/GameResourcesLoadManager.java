package ru.peytob.mineville.model.loader;

import ru.peytob.mineville.model.builder.TextureBlockAtlasBuilder;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.graphic.TextureBlockAtlas;
import ru.peytob.mineville.model.graphic.shader.ShadersPack;
import ru.peytob.mineville.model.loader.base.BaseBlockModel;
import ru.peytob.mineville.model.loader.base.BaseShadersPack;
import ru.peytob.mineville.model.repository.GameRegistry;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class GameResourcesLoadManager {
    private final TextureBlockAtlasBuilder textureBlockAtlasBuilder;
    private final GameRegistry.GameRegistryModifier gameRegistryModifier;

    public GameResourcesLoadManager(GameRegistry.GameRegistryModifier modifier) {
        // Temporarily only 16x16 textures are supported.
        this.textureBlockAtlasBuilder = new TextureBlockAtlasBuilder(16);
        this.gameRegistryModifier = modifier;
    }

    public void loadTextures() throws IOException {
        TexturesLoader texturesLoader = new TexturesLoader("mineville", textureBlockAtlasBuilder);
        URI defaultTexturesFolderURI = resourceToURI("/images/blocks");
        TextureBlockAtlas atlas = texturesLoader.loadTextures(new File(defaultTexturesFolderURI));
        gameRegistryModifier.addTexturesPack("default", atlas);
    }

    public void loadModels() throws IOException {
        ModelLoader modelsLoader = new ModelLoader("mineville");
        URI modelsFolderURI = resourceToURI("/model/block");
        List<BaseBlockModel> models = modelsLoader.loadModels(new File(modelsFolderURI));
        models.forEach(gameRegistryModifier::addModel);
    }

    public void loadShaders() throws IOException {
        ShadersLoader shadersLoader = new ShadersLoader("mineville");
        File shadersFolder = new File(resourceToURI("/shader"));
        BaseShadersPack baseShadersPack = shadersLoader.loadShaderPack(shadersFolder, "default");
        gameRegistryModifier.addShadersPack(baseShadersPack);
    }

    public void loadBlocks() {
        BlockLoader loader = new BlockLoader("mineville", "ru.peytob.mineville.resources.block");
        loader.getBaseInstances().forEach(gameRegistryModifier::addBlock);
        loader.getBaseInstances().forEach(baseBlock -> System.out.println("Loaded block: " + baseBlock.getRepositoryName()));
    }

    private URI resourceToURI(String name) throws IOException {
        URI uri;
        try {
            uri = Objects.requireNonNull(getClass().getResource(name)).toURI();
        } catch (URISyntaxException ignored) {
            throw new IOException("I don't know how this exception can be thrown.");
        } catch (NullPointerException e) {
            throw new IOException("Resource " + name + " not found.");
        }
        return uri;
    }
}
