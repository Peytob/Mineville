package ru.peytob.mineville.controller;

import ru.peytob.mineville.controller.game.Game;
import ru.peytob.mineville.controller.loader.ResourcesLoader;
import ru.peytob.mineville.model.game.Resources;
import ru.peytob.mineville.model.loader.DefaultResourcesLoader;

import java.io.IOException;

public class ApplicationController {
    private final WindowController windowController;
    private final Resources resources;
    private final Game game;

    public ApplicationController() throws IOException {
        windowController = new WindowController("Mineville", 800, 600);
        windowController.show();

        Resources loadedResources;
        try {
            DefaultResourcesLoader defaultResourcesLoader = new DefaultResourcesLoader();
            defaultResourcesLoader.loadResources();
            loadedResources = defaultResourcesLoader.getResources();
        }

        catch (Exception exception) {
            loadedResources = null;
            exception.printStackTrace();
        }

        this.resources = loadedResources;
        game = new Game(resources, windowController);
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
