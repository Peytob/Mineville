package ru.peytob.mineville.view.input;

import static org.lwjgl.glfw.GLFW.*;

import ru.peytob.mineville.math.Vec2;
import ru.peytob.mineville.view.Window;

public class KeyboardMouseInput {
    final Window window;

    public KeyboardMouseInput(Window window) {
        this.window = window;
    }

    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(window.getPointer(), keyCode) == GLFW_PRESS;
    }

    public Vec2 getCursorPosition() {
        return window.getCursorPosition();
    }

    public boolean isMouseButtonPressed(int buttonCode) {
        return glfwGetMouseButton(window.getPointer(), buttonCode) == GLFW_PRESS;
    }
}
