package ru.peytob.mineville.model.loader;

import ru.peytob.mineville.model.game.Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
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
        // Models loading

        ModelLoader modelLoader = new ModelLoader();
        File blockModelsFolder = new File(resourceToURI("/model/block"));

        for (File fileEntry: Objects.requireNonNull(blockModelsFolder.listFiles())) {
            modelLoader.loadModel(fileEntry);
        }

        // Shaders loading

        ShadersLoader shadersLoader = new ShadersLoader();
        File shadersFolder = new File(resourceToURI("/shader"));
        resources.setShadersPack(shadersLoader.loadShaderPack(shadersFolder));
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
