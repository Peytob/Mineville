package ru.peytob.mineville.controller;

import ru.peytob.mineville.controller.loader.ShadersLoader;
import ru.peytob.mineville.math.Mat4;
import ru.peytob.mineville.model.graphic.Mesh;
import ru.peytob.mineville.model.graphic.shader.ShadersPack;
import ru.peytob.mineville.view.WorldDrawer;

import java.io.IOException;

import static org.lwjgl.opengl.GL33.*;

public class ApplicationController {
    WindowController windowController;
    ShadersPack pack;
    Mesh mesh;
    WorldDrawer worldDrawer;

    public ApplicationController() {
        windowController = new WindowController("Mineville", 800, 600);
        windowController.show();

        try {
            pack = new ShadersLoader().loadShaderPack("src/main/resources/shaders");
            pack.getWorldShader().use();
            pack.getWorldShader().setModelMatrix(Mat4.computeIdentity());
            pack.getWorldShader().setViewMatrix(Mat4.computeIdentity());
            pack.getWorldShader().setProjectionMatrix(Mat4.computeIdentity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mesh = new Mesh(new float[] {
                0.5f, 0.5f, 0.5f, // position
                0.0f, 0.0f, 1.0f, // normal
                0.0f, 0.0f, // texture

                -0.5f, 0.5f, 0.5f, // position
                0.0f, 0.0f, 1.0f, // normal
                0.0f, 1.0f, // texture

                -0.5f, -0.5f, 0.5f, // position
                0.0f, 0.0f, 1.0f, // normal
                1.0f, 1.0f, // texture
        });

        worldDrawer = new WorldDrawer(windowController);
    }

    public void run() {
        while (!windowController.isShouldClose()) {
            windowController.pullEvents();

            worldDrawer.clear();
            pack.getWorldShader().use();
            worldDrawer.draw(mesh, GL_TRIANGLES);
            worldDrawer.display();
        }
    }

    public void destroy() {
        mesh.destroy();
        windowController.destroy();
    }
}
