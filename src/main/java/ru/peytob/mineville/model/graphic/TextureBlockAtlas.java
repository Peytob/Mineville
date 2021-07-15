package ru.peytob.mineville.model.graphic;

import ru.peytob.mineville.model.opengl.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureBlockAtlas {
    private final Map<String, TextureTile> data;
    private final int tileSizePixels;
    private final Texture texture;
    private final Float absoluteWidth;
    private final Float absoluteHeight;

    public TextureBlockAtlas(int tileSizePixels, Texture texture, Float absoluteWidth,
                             Float absoluteHeight) {
        this.data = new HashMap<>();
        this.tileSizePixels = tileSizePixels;
        this.texture = texture;
        this.absoluteWidth = absoluteWidth;
        this.absoluteHeight = absoluteHeight;
    }

    public int getTileSizePixels() {
        return tileSizePixels;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getAbsoluteWidth() {
        return absoluteWidth;
    }

    public float getAbsoluteHeight() {
        return absoluteHeight;
    }

    public void destroy() {
        texture.destroy();
    }

    public TextureTile getTile(String textId) {
        return data.get(textId);
    }

    public void registerTile(String textId, int gridX, int gridY) {
        TextureTile tile = new TextureTile(gridX * absoluteWidth, gridY * absoluteHeight, this);
        data.put(textId, tile);
    }
}
