package ru.peytob.mineville.controller.loader;

import ru.peytob.mineville.model.game.Resources;

import java.io.IOException;
import java.nio.file.Path;

public class ResourcesLoader {
    Resources resources;

    public ResourcesLoader() {
        this.resources = new Resources();
    }

    public Resources loadResources() throws IOException {
        new BlockLoader(resources).loadBlocks();
        resources.setShadersPack(new ShadersLoader().loadShaderPack(Path.of("src/main/resources/shaders")));
        return resources;
    }
}
