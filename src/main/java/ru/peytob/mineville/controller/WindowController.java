package ru.peytob.mineville.controller;

import ru.peytob.mineville.math.Vec2;
import ru.peytob.mineville.model.graphic.Window;

public class WindowController {
    final Window window;

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
}
