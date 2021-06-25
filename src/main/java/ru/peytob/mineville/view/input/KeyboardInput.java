package ru.peytob.mineville.view.input;

import static org.lwjgl.glfw.GLFW.*;
import ru.peytob.mineville.view.Window;

public class KeyboardInput {
    final Window window;

    public KeyboardInput(Window window) {
        this.window = window;
    }

    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(window.getPointer(), keyCode) == GLFW_PRESS;
    }
}
