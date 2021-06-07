package ru.peytob.mineville.controller.loader;

import static org.lwjgl.opengl.GL33.*;
import ru.peytob.mineville.model.graphic.shader.ShadersPack;
import ru.peytob.mineville.model.graphic.shader.WorldShader;
import ru.peytob.mineville.model.opengl.Shader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ShadersLoader {
    public ShadersLoader() {

    }

    public ShadersPack loadShaderPack(String folder) throws IOException, RuntimeException {
        ShadersPack pack = new ShadersPack();

        Shader worldVertex = loadShader(folder + "/world.vert", GL_VERTEX_SHADER);
        Shader worldFragment = loadShader(folder + "/world.frag", GL_FRAGMENT_SHADER);
        WorldShader worldShader = new WorldShader();
        worldShader.attachShader(worldVertex);
        worldShader.attachShader(worldFragment);
        worldShader.link();
        pack.setWorldShader(worldShader);
        worldVertex.destroy();
        worldFragment.destroy();

        return pack;
    }

    private Shader loadShader(String file, int type) throws IOException {
        return new Shader(Files.readString(Path.of(file)), type);
    }
}
