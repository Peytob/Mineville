package ru.peytob.mineville.controller.game.state;

public interface IGameState {
    void tick();
    void clear();
    void draw();
}
