package ru.peytob.mineville.controller.game.state;

public interface IGameState {
    void handleInput();
    void tick();
    void clear();
    void draw();
}
