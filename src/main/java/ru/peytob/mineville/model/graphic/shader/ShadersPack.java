package ru.peytob.mineville.model.graphic.shader;

import ru.peytob.mineville.model.loader.base.BaseShadersPack;
import ru.peytob.mineville.model.repository.AbstractRegistrable;

public class ShadersPack extends AbstractRegistrable {
    private WorldShader worldShader;

    public ShadersPack(Integer id, BaseShadersPack baseShadersPack) {
        super(baseShadersPack.getRepositoryName(), id);
        this.worldShader = baseShadersPack.getWorldShader();
    }

    public WorldShader getWorldShader() {
        return worldShader;
    }

    public void setWorldShader(WorldShader worldShader) {
        this.worldShader = worldShader;
    }
}
