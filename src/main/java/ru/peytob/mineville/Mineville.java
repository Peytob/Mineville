package ru.peytob.mineville;

import ru.peytob.mineville.controller.ApplicationController;
import ru.peytob.mineville.controller.WindowController;
import ru.peytob.mineville.model.loader.GameResourcesLoadManager;
import ru.peytob.mineville.model.repository.GameRegistry;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.glfwInit;

/**
 * Application entry point.
 * Runs all application layers subsequence.
 */
public class Mineville {
    public static void main(String[] args) {
        /* Layer 1: initializing libraries */

        if (!glfwInit()) {
            System.err.println("GLFW initialization error.");
            return;
        }

        WindowController windowController = new WindowController("Mineville", 800, 600);

        /* Layer 2: loading all resources */

        GameResourcesLoadManager loadManager = new GameResourcesLoadManager(GameRegistry.getInstance().getModifier());

        try {
            loadManager.loadModels();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            loadManager.loadTextures();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            loadManager.loadShaders();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadManager.loadBlocks();

        /* Layer 3: application main cycle */

        ApplicationController controller;
        try {
            controller = new ApplicationController(windowController);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        controller.setWindowCallbacks();
        controller.run();

        /* Layer 4: free all resources! [but now its dont frees repository resources like shaders and other] */

        controller.destroy();
    }
}
