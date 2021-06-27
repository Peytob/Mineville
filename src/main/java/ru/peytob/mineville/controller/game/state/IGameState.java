package ru.peytob.mineville.controller.game.state;

public interface IGameState {
    void onSet();

    void handleInput();

    void tick();

    void clear();

    void draw();

    void onChange();

    void onClose();

    void onMouseClick(int button, int action, int mods);

    void onMouseMove(double newX, double newY);

    void onKeyPress(int key, int scancode, int action, int mods);

    void onScroll(double xOffset, double yOffset);
}
