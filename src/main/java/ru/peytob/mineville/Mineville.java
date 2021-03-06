package ru.peytob.mineville;

import ru.peytob.mineville.controller.ApplicationController;
import ru.peytob.mineville.controller.WindowController;
import ru.peytob.mineville.math.ImmutableVec2;
import ru.peytob.mineville.math.ImmutableVec2i;
import ru.peytob.mineville.math.noise.FractalBrownianMotion;
import ru.peytob.mineville.math.noise.INoise2D;
import ru.peytob.mineville.math.noise.NoiseFacade;
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

        ImmutableVec2i sizes = new ImmutableVec2i(2048, 2048);

        ImageBuilder imageBuilder = new ImageBuilder(sizes.getX(), sizes.getY());

        INoise2D noise2D = new FractalBrownianMotion(new Perlin2dNoise(3), 10, 2f);
        INoise2D noise = new NoiseFacade(noise2D, 0.01f);
        ImmutableVec2 offset = new ImmutableVec2(Integer.MAX_VALUE >> 6, Integer.MAX_VALUE >> 6);

        for (int x = 0; x < imageBuilder.getWidth(); x++) {
            for (int y = 0; y < imageBuilder.getHeight(); y++) {
                float noiseColor = noise.getPoint(((offset.getX() + sizes.getX()) / 2.0f - x),
                        ((offset.getY() + sizes.getY()) / 2.0f - y));
                noiseColor += 4.0;
                noiseColor /= 8.0;

//                if (noiseColor > 1.0f || noiseColor < 0.0f) {
//                    System.out.println(noiseColor);
//                }
                byte noiseByte = (byte) Math.round(255 * noiseColor);

                if (noiseColor < 0.45f) { // Water
                    imageBuilder.setPixel(x, y, (byte) 0, (byte) 0, noiseByte, (byte) 255);
                }

                else if (noiseColor < 0.465f) { // Sand
                    imageBuilder.setPixel(x, y, noiseByte, noiseByte, (byte) 0, (byte) 255);
                }

                else { // Grass
                    imageBuilder.setPixel(x, y, (byte) 0, noiseByte, (byte) 0, (byte) 255);
                }
            }
        }

        for (int i = -10; i < 10; i++) {
            for (int j = -10; j < 10; j++) {
                int x = sizes.getX() / 2 + i;
                int y = sizes.getY() / 2 + j;

                imageBuilder.setPixel(x, y, Byte.MAX_VALUE, (byte) 0, (byte) 0, Byte.MAX_VALUE);
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
