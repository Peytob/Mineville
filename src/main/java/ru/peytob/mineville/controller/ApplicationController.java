package ru.peytob.mineville.controller;

import ru.peytob.mineville.controller.game.Game;
import ru.peytob.mineville.model.repository.GameRegistry;

import java.io.IOException;

public class ApplicationController {
    private final WindowController windowController;
    private final Game game;

    public ApplicationController(WindowController windowController) throws IOException {
        this.windowController = windowController;
        this.windowController.show();
        game = new Game(GameRegistry.getInstance(), windowController);
    }

    public void setWindowCallbacks() {
        windowController.setMouseButtonCallback((window, button, action, mods) ->
                game.onMouseClick(button, action, mods));

        windowController.setCursorPositionCallback((window, newX, newY) ->
                game.onMouseMove(newX, newY));

        windowController.setKeyCallback((window, key, scancode, action, mods) ->
                game.onKeyPress(key, scancode, action, mods));

        windowController.setScrollCallback((window, xOffset, yOffset) ->
                game.onScroll(xOffset, yOffset));
    }

    public void run() {
        while (game.isRunning()) {
            windowController.pullEvents();
            game.handleInput();
            game.tick();
            game.clear();
            game.draw();
            windowController.display();
        }
    }

    public void destroy() {
        windowController.close();
        windowController.destroy();
//        resources.destroy();
    }
}
