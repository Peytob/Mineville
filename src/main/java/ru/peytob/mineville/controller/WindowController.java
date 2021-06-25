package ru.peytob.mineville.controller;

import ru.peytob.mineville.math.Vec2;
import ru.peytob.mineville.view.Window;
import ru.peytob.mineville.view.input.KeyboardInput;

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

    public Vec2 getCursorPosition() {
        return window.getCursorPosition();
    }

    public KeyboardInput getKeyboardInput() {
        return window.getKeyboardInput();
    }
}
