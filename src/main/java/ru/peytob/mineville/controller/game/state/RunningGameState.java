package ru.peytob.mineville.controller.game.state;

import ru.peytob.mineville.controller.game.Game;

public class RunningGameState implements IGameState {
    private final Game game;

    public RunningGameState(Game game) {
        this.game = game;
    }

    @Override
    public void tick() {

    }

    @Override
    public void clear() {
        game.getWorldDrawer().clear();
    }

    @Override
    public void draw() {

    }
}
