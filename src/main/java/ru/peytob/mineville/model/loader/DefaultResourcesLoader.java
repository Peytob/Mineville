package ru.peytob.mineville.model.loader;

import ru.peytob.mineville.model.builder.ImageBuilder;
import ru.peytob.mineville.model.game.Resources;
import ru.peytob.mineville.model.graphic.Image;
import ru.peytob.mineville.model.opengl.Texture;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class DefaultResourcesLoader {
    private final Resources resources;

    public DefaultResourcesLoader() {
        this.resources = new Resources();
    }

    public Resources getResources() {
        return resources;
    }

    public void loadResources() throws IOException {
        // Shaders loading

        ShadersLoader shadersLoader = new ShadersLoader();
        File shadersFolder = new File(resourceToURI("/shader"));
        resources.setShadersPack(shadersLoader.loadShaderPack(shadersFolder));

        // Models loading

        ModelLoader modelLoader = new ModelLoader();
        File blockModelsFolder = new File(resourceToURI("/model/block"));

        for (File fileEntry: Objects.requireNonNull(blockModelsFolder.listFiles())) {
            modelLoader.loadModel(fileEntry);
        }

        // Block textures loading

        ImageBuilder builder = new ImageBuilder(128, 128);
        ImageLoader loader = new ImageLoader();
        builder.insert(loader.loadFromFile(resourceToURI("/images/blocks/smtTile.png").getPath()), 16, 16);
        builder.insert(loader.loadFromFile(resourceToURI("/images/blocks/grass_side.png").getPath()), 120, 50);
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
