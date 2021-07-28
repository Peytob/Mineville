package ru.peytob.mineville.model.loader.base;

import ru.peytob.mineville.model.graphic.shader.WorldShader;

public class BaseShadersPack {
    private WorldShader worldShader;
    private String repositoryName;

    public WorldShader getWorldShader() {
        return worldShader;
    }

    public void setWorldShader(WorldShader worldShader) {
        this.worldShader = worldShader;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getRepositoryName() {
        return repositoryName;
    }
}
