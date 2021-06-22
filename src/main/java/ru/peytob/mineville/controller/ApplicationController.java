package ru.peytob.mineville.controller;

import ru.peytob.mineville.controller.game.Game;
import ru.peytob.mineville.controller.loader.ResourcesLoader;
import ru.peytob.mineville.model.game.Resources;
import ru.peytob.mineville.view.WorldDrawer;

import java.io.IOException;

public class ApplicationController {
    private final WindowController windowController;
    private final WorldDrawer worldDrawer; // Is it should be in some game state?
    private final Resources resources;
    private final Game game;

    public ApplicationController() throws IOException {
        windowController = new WindowController("Mineville", 800, 600);
        windowController.show();
        worldDrawer = new WorldDrawer(windowController);
        resources = new ResourcesLoader().loadResources();
        game = new Game(resources, worldDrawer);
    }

    public void run() {
        while (!windowController.isShouldClose()) {
            windowController.pullEvents();
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
