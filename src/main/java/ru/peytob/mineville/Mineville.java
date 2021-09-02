package ru.peytob.mineville;

import ru.peytob.mineville.controller.ApplicationController;
import ru.peytob.mineville.controller.WindowController;
import ru.peytob.mineville.math.noise.FractalBrownianMotion;
import ru.peytob.mineville.math.noise.Perlin2dNoise;
import ru.peytob.mineville.model.builder.ImageBuilder;
import ru.peytob.mineville.model.export.ImageExporter;
import ru.peytob.mineville.model.graphic.Image;
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
        /* Layer 0: Test stuff */

        ImageBuilder imageBuilder = new ImageBuilder(1024 * 4, 1024 * 4);

        Perlin2dNoise perlinNoise = new Perlin2dNoise(15);
        FractalBrownianMotion noise = new FractalBrownianMotion(perlinNoise, 8, 1f);
        for (int x = 0; x < imageBuilder.getWidth(); x++) {
            for (int y = 0; y < imageBuilder.getHeight(); y++) {
                float noiseColor = noise.getPoint(x * 0.01f, y * 0.01f);
                noiseColor += 1.0;
                noiseColor /= 2.0;
                byte noiseByte = (byte) Math.round(255 * noiseColor);

                if (noiseColor < 0.45f) { // Water
                    imageBuilder.setPixel(x, y, (byte) 0, (byte) 0, noiseByte, (byte) 255);
                }

                else if (noiseColor < 0.47f) { // Sand
                    imageBuilder.setPixel(x, y, noiseByte, noiseByte, (byte) 0, (byte) 255);
                }

                else { // Grass
                    imageBuilder.setPixel(x, y, (byte) 0, noiseByte, (byte) 0, (byte) 255);
                }
            }
        }

        Image image = imageBuilder.buildImage();
        try {
            ImageExporter.exportAsPng(image, "smt.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
