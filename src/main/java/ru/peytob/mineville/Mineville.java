package ru.peytob.mineville;

import ru.peytob.mineville.controller.ApplicationController;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Application entry point.
 * Initializes all libraries used by the application.
 */
public class Mineville {
    public static void main(String[] args) {
        if (!glfwInit()) {
            System.out.println("GLFW initialization error.");
            return;
        }

        ApplicationController controller;
        try {
            controller = new ApplicationController();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        controller.run();
        controller.destroy();
    }
}
