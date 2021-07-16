package ru.peytob.mineville.model.builder;

import ru.peytob.mineville.math.Vec2i;
import ru.peytob.mineville.model.graphic.Image;
import ru.peytob.mineville.model.graphic.TextureBlockAtlas;
import ru.peytob.mineville.model.graphic.TextureTile;
import ru.peytob.mineville.model.opengl.Texture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class TextureBlockAtlasBuilder {
    private final Map<String, Image> tilesImages;
    private final int tileSizePixels;

    public TextureBlockAtlasBuilder(int tileSizePixels) {
        this.tilesImages = new HashMap<>();
        this.tileSizePixels = tileSizePixels;
    }

    public void putTile(String textId, Image image) throws IllegalArgumentException {
        if (tilesImages.containsKey(textId)) {
            throw new IllegalArgumentException("TextureAtlasBuilder: key " + image + " already in map.");
        }

        tilesImages.put(textId, image);
    }

    public TextureBlockAtlas buildAtlas() {
        int atlasSideSize = computeAtlasSideSize(tilesImages.size());
        ImageBuilder imageBuilder = new ImageBuilder(atlasSideSize * tileSizePixels,
                atlasSideSize * tileSizePixels);

        Map<String, Vec2i> tilesPositions = new HashMap<>();

        AtomicReference<Integer> index = new AtomicReference<>(0);
        tilesImages.forEach((textId, image) -> {
            int x = index.get() % atlasSideSize;
            int y = index.get() / atlasSideSize;
            index.getAndSet(index.get() + 1);

            Vec2i position = new Vec2i(x, y);
            imageBuilder.insert(image, x * tileSizePixels, y * tileSizePixels);
            tilesPositions.put(textId, new Vec2i(x, y));
        });

        Texture texture = new Texture(imageBuilder.buildImage());
        TextureBlockAtlas atlas = new TextureBlockAtlas(tileSizePixels, texture,
                (float) tileSizePixels / imageBuilder.getWidth(),
                (float) tileSizePixels / imageBuilder.getHeight());

        tilesPositions.forEach((textId, position) -> {
            atlas.registerTile(textId, position.x, position.y);
            TextureTile tile = atlas.getTile(textId);
            System.out.println(textId + " " + tile.getX() + " " + tile.getY());
        });

        return atlas;
    }

    private int computeAtlasSideSize(int n) {
        return (int) Math.ceil(Math.sqrt(n));
    }
}
