package ru.peytob.mineville.model.loader;

import ru.peytob.mineville.model.builder.TextureBlockAtlasBuilder;
import ru.peytob.mineville.model.graphic.Image;
import ru.peytob.mineville.model.graphic.TextureBlockAtlas;
import ru.peytob.mineville.model.loader.utils.ImageLoader;

import java.io.File;
import java.io.IOException;

public class TexturesLoader extends AbstractResourcesLoader {
    private final TextureBlockAtlasBuilder textureBlockAtlasBuilder;

    public TexturesLoader(String namespace, TextureBlockAtlasBuilder builder) {
        super(namespace);
        this.textureBlockAtlasBuilder = builder;
    }

    public TextureBlockAtlas loadTextures(File texturesDirectory) throws IOException {
        ImageLoader imageLoader = new ImageLoader();

        // Checks if directory is empty.
        File[] fileList = texturesDirectory.listFiles();
        if (fileList == null) {
            return textureBlockAtlasBuilder.buildAtlas();
        }

        for (File file : fileList) {
            if (file.isFile()) {
                String imageName = file.getName();
                int pointIndex = imageName.lastIndexOf('.');
                if (pointIndex != -1) {
                    imageName = file.getName().substring(0, pointIndex);
                }

                Image image = imageLoader.loadFromFile(file.getPath());
                textureBlockAtlasBuilder.putTile(getNamespace() + "::" + imageName, image);
            }

            else if (file.isDirectory()) {
                loadTextures(file);
            }
        }

        return textureBlockAtlasBuilder.buildAtlas();
    }
}
