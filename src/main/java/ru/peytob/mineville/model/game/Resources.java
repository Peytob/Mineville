package ru.peytob.mineville.model.game;

import ru.peytob.mineville.model.graphic.shader.ShadersPack;

public class Resources {
    private final BlockRepository blockRepository;

    private ShadersPack shadersPack;

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
}
