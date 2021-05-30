package ru.peytob.mineville.controller;

import static org.lwjgl.opengl.GL11.*;

public class ApplicationController {
    WindowController windowController;

    public ApplicationController() {
        windowController = new WindowController("Mineville", 800, 600);
        System.out.println("GL_RENDERER = " + glGetString(GL_RENDERER));
        System.out.println("GL_VENDOR = " + glGetString(GL_VENDOR));
        System.out.println("GL_VERSION = " + glGetString(GL_VERSION));
        System.out.println("GL_EXTENSIONS = " + glGetString(GL_EXTENSIONS));
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
