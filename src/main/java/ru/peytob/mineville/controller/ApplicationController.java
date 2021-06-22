package ru.peytob.mineville.controller;

import org.lwjgl.glfw.GLFW;
import ru.peytob.mineville.controller.draw.CameraController;
import ru.peytob.mineville.controller.loader.ResourcesLoader;
import ru.peytob.mineville.controller.loader.ShadersLoader;
import ru.peytob.mineville.math.Mat4;
import ru.peytob.mineville.math.Vec2;
import ru.peytob.mineville.math.Vec3;
import ru.peytob.mineville.model.game.Resources;
import ru.peytob.mineville.model.game.world.Chunk;
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
    Vec2 cursorPosition;

    public ApplicationController() {
        windowController = new WindowController("Mineville", 800, 600);
        windowController.show();
        worldDrawer = new WorldDrawer(windowController);
    }

    public void run() {
        while (!windowController.isShouldClose()) {
            windowController.pullEvents();
        }
    }

    public void destroy() {
        windowController.destroy();
    }
}
