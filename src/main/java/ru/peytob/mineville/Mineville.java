package ru.peytob.mineville;

//import ru.peytob.mineville.controller.ApplicationController;
//import ru.peytob.mineville.controller.WindowController;
//import ru.peytob.mineville.model.loader.GameResourcesLoadManager;
//import ru.peytob.mineville.model.repository.GameRegistry;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.glfwInit;

/**
 * Application entry point.
 * Initializes all libraries used by the application.
 */
public class Mineville {
    public static void main(String[] args) {
//        if (!glfwInit()) {
//            System.out.println("GLFW initialization error.");
//            return;
//        }
//
//        WindowController windowController = new WindowController("Mineville", 800, 600);
//
//        GameResourcesLoadManager loadManager = new GameResourcesLoadManager(GameRegistry.getInstance().getModifier());
//
//        try {
//            loadManager.loadModels();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            loadManager.loadTextures();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            loadManager.loadShaders();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        loadManager.loadBlocks();
//
//        ApplicationController controller;
//        try {
//            controller = new ApplicationController(windowController);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        controller.setWindowCallbacks();
//        controller.run();
//        controller.destroy();
    }
}
