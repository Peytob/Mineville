package ru.peytob.mineville.model.graphic;

import ru.peytob.mineville.model.opengl.Texture;
import ru.peytob.mineville.model.repository.AbstractRegistrable;

public class TexturesPack extends AbstractRegistrable {
    private final TextureBlockAtlas textureBlockAtlas;

    public TexturesPack(String repositoryName, Integer id, TextureBlockAtlas textureBlockAtlas) {
        super(repositoryName, id);
        this.textureBlockAtlas = textureBlockAtlas;
    }

    public int getTileSizePixels() {
        return textureBlockAtlas.getTileSizePixels();
    }

    public Texture getBlockAtlasTexture() {
        return textureBlockAtlas.getTexture();
    }

    public float getAbsoluteTileWidth() {
        return textureBlockAtlas.getAbsoluteWidth();
    }

    public float getAbsoluteTileHeight() {
        return textureBlockAtlas.getAbsoluteHeight();
    }

    public TextureTile getTile(String textId) {
        return textureBlockAtlas.getTile(textId);
    }

    public void destroy() {
        textureBlockAtlas.destroy();
    }
}
