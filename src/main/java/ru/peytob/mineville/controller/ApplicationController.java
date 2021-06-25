package ru.peytob.mineville.controller;

import ru.peytob.mineville.controller.game.Game;
import ru.peytob.mineville.controller.loader.ResourcesLoader;
import ru.peytob.mineville.model.game.Resources;

import java.io.IOException;

public class ApplicationController {
    private final WindowController windowController;
    private final Resources resources;
    private final Game game;

    public ApplicationController() throws IOException {
        windowController = new WindowController("Mineville", 800, 600);
        windowController.show();
        resources = new ResourcesLoader().loadResources();
        game = new Game(resources, windowController);
    }

    public void run() {
        while (!windowController.isShouldClose()) {
            windowController.pullEvents();
            game.handleInput();
            game.tick();
            game.clear();
            game.draw();
            windowController.display();
        }
    }

    public void destroy() {
        windowController.destroy();
    }
}
