package ru.peytob.mineville;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.peytob.mineville.controller.ApplicationController;
import ru.peytob.mineville.controller.WindowController;
import ru.peytob.mineville.model.loader.GameResourcesLoadManager;
import ru.peytob.mineville.model.repository.GameRegistry;
import ru.peytob.mineville.view.GlGlobalContext;

import java.io.IOException;
import java.util.Properties;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;

/**
 * Application entry point.
 * Runs all application layers subsequence.
 */
public class Mineville {
    public static void glfwErrorCallback(int code, long pointer) {
        String description = GLFWErrorCallback.getDescription(pointer);
        Logger logger = LoggerFactory.getLogger("glfwErrorCallback");
        logger.error("GLFW error with code {}. Description: ", description);
        throw new IllegalStateException(description);
    }

    public static void main(String[] args) {
        /* Layer 1: initializing libraries and creating main window */

        final Logger logger = LoggerFactory.getLogger(Mineville.class);
        Properties properties = System.getProperties();

        logger.info("Application started.");

        logger.info("JVM: {} {}", properties.getProperty("java.vm.name"), properties.getProperty("java.version"));
        logger.info("JVM vendor: {}", properties.getProperty("java.vendor"));
        logger.info("JRE: {} {}", properties.getProperty("java.runtime.name"), properties.getProperty("java.runtime.version"));

        logger.info("OS name: {}", properties.getProperty("os.name"));
        logger.info("OS version: {}", properties.getProperty("os.version"));
        logger.info("OS architecture: {}", properties.getProperty("os.arch"));

        logger.info("Available processors: {}", Runtime.getRuntime().availableProcessors());
        logger.info("Max memory: {} Mb", (float) Runtime.getRuntime().maxMemory() / 1024f / 1024f);
        logger.info("Total memory: {} Mb", Runtime.getRuntime().totalMemory() / 1024f / 1024f);

        glfwSetErrorCallback(Mineville::glfwErrorCallback);
        try {
            if (!glfwInit()) {
                return;
            }
        }

        catch (IllegalStateException exp) {
            return; // Error description is already added to log file in error callback.
        }

        WindowController windowController = new WindowController("Mineville", 1200, 800);

        GlGlobalContext glContext = new GlGlobalContext();

        logger.info("GL renderer: {}", glContext.getRenderer());
        logger.info("GL version: {}", glContext.getVersion());
        logger.info("GL extensions number: {}", glContext.getExtensionsNumber());

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
