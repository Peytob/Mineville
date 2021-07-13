package ru.peytob.mineville.model.loader;

import ru.peytob.mineville.model.graphic.shader.ShadersPack;
import ru.peytob.mineville.model.graphic.shader.WorldShader;
import ru.peytob.mineville.model.opengl.Shader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.lwjgl.opengl.GL33.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL33.GL_VERTEX_SHADER;

public class ShadersLoader {
    public ShadersPack loadShaderPack(Path folder) throws IOException, RuntimeException {
        ShadersPack pack = new ShadersPack();

        Shader worldVertex = loadShader(Path.of(folder + "/world.vert"), GL_VERTEX_SHADER);
        Shader worldFragment = loadShader(Path.of(folder + "/world.frag"), GL_FRAGMENT_SHADER);
        WorldShader worldShader = new WorldShader();
        worldShader.attachShader(worldVertex);
        worldShader.attachShader(worldFragment);
        worldShader.link();
        pack.setWorldShader(worldShader);
        worldVertex.destroy();
        worldFragment.destroy();

        return pack;
    }

    public ShadersPack loadShaderPack(File folder) throws IOException, RuntimeException {
        return loadShaderPack(folder.toPath());
    }

    private Shader loadShader(Path file, int type) throws IOException {
        return new Shader(Files.readString(file), type);
    }
}
