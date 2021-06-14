package ru.peytob.mineville.controller;

import org.lwjgl.glfw.GLFW;
import ru.peytob.mineville.controller.draw.CameraController;
import ru.peytob.mineville.controller.loader.ResourcesLoader;
import ru.peytob.mineville.controller.loader.ShadersLoader;
import ru.peytob.mineville.math.Mat4;
import ru.peytob.mineville.math.Vec3;
import ru.peytob.mineville.model.game.Resources;
import ru.peytob.mineville.model.game.world.Chunk;
import ru.peytob.mineville.model.game.world.Octree;
import ru.peytob.mineville.model.graphic.Camera;
import ru.peytob.mineville.model.graphic.shader.ShadersPack;
import ru.peytob.mineville.view.WorldDrawer;

import java.io.IOException;
import java.nio.file.Path;

import static org.lwjgl.glfw.GLFW.*;

public class ApplicationController {
    WindowController windowController;
    CameraController cameraController;
    ShadersPack pack;
    Resources resources;
    WorldDrawer worldDrawer;
    Chunk chunk;
    Vec3 cursorPosition;

    public ApplicationController() {
        windowController = new WindowController("Mineville", 800, 600);
        windowController.show();
        this.cursorPosition = new Vec3(0, 0, 0);

        this.cameraController = new CameraController(new Vec3(0, 0, -10 ), 0,  (float) Math.toRadians(90),
                (float) Math.toRadians(75), 800.0f / 600.0f);

        try {
            pack = new ShadersLoader().loadShaderPack(Path.of("src/main/resources/shaders"));
            pack.getWorldShader().use();
            pack.getWorldShader().setModelMatrix(Mat4.computeIdentity());
            pack.getWorldShader().setViewMatrix(cameraController.computeView());
            pack.getWorldShader().setProjectionMatrix(cameraController.computeProjection());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResourcesLoader loader = new ResourcesLoader();
        this.resources = loader.loadResources();
        chunk = new Chunk();

        for (int j = 0; j < 16 * chunk.getOctrees().length; j += 16) {
            for (int i = 0; i < 16; ++i) {
                chunk.setBlock(i, i + j, i, resources.getBlockRepository().getBlock("grass"));
                chunk.setBlock(i, i + j, 15 - i, resources.getBlockRepository().getBlock("grass"));
                chunk.setBlock(15 - i, i + j, i, resources.getBlockRepository().getBlock("grass"));
                chunk.setBlock(15 - i, i + j, 15 - i, resources.getBlockRepository().getBlock("grass"));
            }
        }

        resources.getBlockRepository().getBlocksStream().forEach(block -> System.out.println(block.getTextId()));

        GLFW.glfwSetCursorPosCallback(windowController.window.getPointer(),
                (window, dx, dy) -> {
                    cameraController.lookAround((float) (dx - cursorPosition.x) * 0.1f,
                            (float) -(dy - cursorPosition.y) * 0.1f);

                   pack.getWorldShader().setViewMatrix(cameraController.computeView());

                    cursorPosition.x = (float) dx;
                    cursorPosition.y = (float) dy;
                });

        worldDrawer = new WorldDrawer(windowController);
    }

    public void run() {
        while (!windowController.isShouldClose()) {
            windowController.pullEvents();

            float speed = 0.15f;
            Vec3 cameraOffset = new Vec3(0 ,0, 0);
            long windowPointer = windowController.window.getPointer();

            if (glfwGetKey(windowPointer, GLFW_KEY_UP) == GLFW_PRESS || glfwGetKey(windowPointer, GLFW_KEY_W) == GLFW_PRESS)
                cameraOffset = cameraOffset.plus(cameraController.getFrontVector().multiplication(speed));

            if (glfwGetKey(windowPointer, GLFW_KEY_DOWN) == GLFW_PRESS || glfwGetKey(windowPointer, GLFW_KEY_S) == GLFW_PRESS)
                cameraOffset = cameraOffset.minus(cameraController.getFrontVector().multiplication(speed));

            if (glfwGetKey(windowPointer, GLFW_KEY_LEFT) == GLFW_PRESS || glfwGetKey(windowPointer, GLFW_KEY_A) == GLFW_PRESS)
                cameraOffset = cameraOffset.minus(cameraController.getRightVector().multiplication(speed));

            if (glfwGetKey(windowPointer, GLFW_KEY_RIGHT) == GLFW_PRESS || glfwGetKey(windowPointer, GLFW_KEY_D) == GLFW_PRESS)
                cameraOffset = cameraOffset.plus(cameraController.getRightVector().multiplication(speed));

            if (!cameraOffset.equals(new Vec3())) {
                cameraController.move(cameraOffset);
                pack.getWorldShader().setViewMatrix(cameraController.computeView());
            }

            worldDrawer.clear();
            pack.getWorldShader().use();
            worldDrawer.draw(chunk, pack.getWorldShader());
            worldDrawer.display();
        }
    }

    public void destroy() {
        windowController.destroy();
    }
}
