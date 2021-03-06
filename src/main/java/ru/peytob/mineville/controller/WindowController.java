package ru.peytob.mineville.controller;

import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;
import org.lwjgl.glfw.GLFWScrollCallbackI;
import ru.peytob.mineville.math.ImmutableVec2;
import ru.peytob.mineville.math.ImmutableVec2i;
import ru.peytob.mineville.math.Vec2;
import ru.peytob.mineville.math.Vec2i;
import ru.peytob.mineville.view.Window;
import ru.peytob.mineville.view.input.KeyboardMouseInput;

public class WindowController {
    private final Window window;

    public WindowController(String title, int width, int height) {
        window = new Window(title, width, height);
    }

    public void close() {
        window.close();
    }

    public boolean isShouldClose() {
        return window.isShouldClose();
    }

    public void pullEvents() {
        window.pollEvents();
    }

    public void destroy() {
        window.destroy();
    }

    public void display() {
        window.display();
    }

    public void show() {
        window.show();
    }

    public ImmutableVec2 getCursorPosition() {
        return window.getCursorPosition();
    }

    public ImmutableVec2i getWindowSizes() {
        return window.getWindowSizes();
    }

    public KeyboardMouseInput getKeyboardMouseInput() {
        return window.getKeyboardMouseInput();
    }

    public void setMouseButtonCallback(GLFWMouseButtonCallbackI callback) {
        window.setMouseButtonCallback(callback);
    }

    public void setCursorPositionCallback(GLFWCursorPosCallbackI callback) {
        window.setCursorPositionCallback(callback);
    }

    public void setKeyCallback(GLFWKeyCallbackI callback) {
        window.setKeyCallback(callback);
    }

    public void setScrollCallback(GLFWScrollCallbackI callback) {
        window.setScrollCallback(callback);
    }
}
