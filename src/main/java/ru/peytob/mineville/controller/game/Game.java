package ru.peytob.mineville.controller.game;

import ru.peytob.mineville.controller.game.state.IGameState;
import ru.peytob.mineville.controller.game.state.RunningGameState;
import ru.peytob.mineville.model.game.Resources;
import ru.peytob.mineville.view.WorldDrawer;

public class Game {
    private IGameState state;
    private final Resources resources;
    private final WorldDrawer worldDrawer;

    public Game(Resources resources, WorldDrawer worldDrawer) {
        this.resources = resources;
        this.worldDrawer = worldDrawer;
        this.state = new RunningGameState(this);
    }

    public void tick() {
        state.tick();
    }

    public void clear() {
        state.clear();
    }

    public void draw() {
        state.draw();
    }

    public IGameState getState() {
        return state;
    }

    public void setState(IGameState state) {
        this.state = state;
    }

    public Resources getResources() {
        return resources;
    }

    public WorldDrawer getWorldDrawer() {
        return worldDrawer;
    }
}
