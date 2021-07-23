package ru.peytob.mineville.model.game;

import ru.peytob.mineville.model.graphic.TextureBlockAtlas;
import ru.peytob.mineville.model.graphic.shader.ShadersPack;
import ru.peytob.mineville.model.repository.BlockRepository;

public class Resources {
    private final BlockRepository blockRepository;

    private ShadersPack shadersPack;

    private TextureBlockAtlas textureBlockAtlas;

    public Resources() {
        blockRepository = new BlockRepository();
    }

    public BlockRepository getBlockRepository() {
        return blockRepository;
    }

    public ShadersPack getShadersPack() {
        return shadersPack;
    }

    public void setShadersPack(ShadersPack shadersPack) {
        this.shadersPack = shadersPack;
    }

    public TextureBlockAtlas getTexturesPack() {
        return textureBlockAtlas;
    }

    public void setTexturesPack(TextureBlockAtlas textureBlockAtlas) {
        this.textureBlockAtlas = textureBlockAtlas;
    }
}
