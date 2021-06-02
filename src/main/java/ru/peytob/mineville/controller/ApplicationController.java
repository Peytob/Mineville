package ru.peytob.mineville.controller;

import ru.peytob.mineville.controller.loader.ShadersLoader;
import ru.peytob.mineville.model.graphic.shader.ShadersPack;

import java.io.IOException;

public class ApplicationController {
    WindowController windowController;
    ShadersPack pack;

    public ApplicationController() {
        windowController = new WindowController("Mineville", 800, 600);
        try {
            pack = new ShadersLoader().loadShaderPack("src/main/resources/shaders");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
