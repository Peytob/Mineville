package ru.peytob.mineville.controller.loader;

import ru.peytob.mineville.model.game.Resources;

public class ResourcesLoader {
    Resources resources;

    public ResourcesLoader() {
        this.resources = new Resources();
    }

    public Resources loadResources() {
        new BlockLoader(resources.getBlockRepository()).loadBlocks();
        return resources;
    }
}
