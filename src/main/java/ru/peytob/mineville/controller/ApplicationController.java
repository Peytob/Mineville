package ru.peytob.mineville.controller;

import org.lwjgl.glfw.GLFW;
import ru.peytob.mineville.controller.game.Game;
import ru.peytob.mineville.model.repository.GameRegistry;
import ru.peytob.mineville.view.input.KeyboardMouseInput;

import java.io.IOException;

public class ApplicationController {
    private final WindowController windowController;
    private final Game game;
    double lastFrameTimeSeconds;

    public ApplicationController(WindowController windowController) throws IOException {
        this.windowController = windowController;
        this.windowController.show();
        this.lastFrameTimeSeconds = 0;

        game = new Game(GameRegistry.getInstance(), windowController, this);
    }

    public void setWindowCallbacks() {
        windowController.setMouseButtonCallback((window, button, action, mods) ->
                game.onMouseClick(button, action, mods));

        windowController.setCursorPositionCallback((window, newX, newY) ->
                game.onMouseMove(newX, newY));

        windowController.setKeyCallback((window, key, scancode, action, mods) ->
                game.onKey(key, scancode, action, mods));

        windowController.setScrollCallback((window, xOffset, yOffset) ->
                game.onScroll(xOffset, yOffset));
    }

    public void run() {
        KeyboardMouseInput input = windowController.getKeyboardMouseInput();

        while (game.isRunning()) {
            double frameBegin = GLFW.glfwGetTime();
            windowController.pullEvents();
            game.handleInput(input);
            game.tick();
            game.draw();
            windowController.display();
            lastFrameTimeSeconds = GLFW.glfwGetTime() - frameBegin;
        }
    }

    public void destroy() {
        windowController.close();
        windowController.destroy();
    }

    public double getLastFrameTimeSeconds() {
        return lastFrameTimeSeconds;
    }
}
