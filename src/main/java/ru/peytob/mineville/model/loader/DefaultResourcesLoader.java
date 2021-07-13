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

        URI blockModelUri;
        try {
            blockModelUri = Objects.requireNonNull(getClass().getResource("/model/block")).toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new IOException("Can't find resource: block models");
        }

        ModelLoader modelLoader = new ModelLoader();
        File blockModelsFolder = new File(blockModelUri);

        for (File fileEntry: Objects.requireNonNull(blockModelsFolder.listFiles())) {
            modelLoader.loadModel(fileEntry);
        }
    }
}
