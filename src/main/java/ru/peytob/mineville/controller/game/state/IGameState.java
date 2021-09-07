package ru.peytob.mineville.controller.game.state;

import ru.peytob.mineville.view.input.KeyboardMouseInput;

public interface IGameState {
    void onSet();

    void handleInput(KeyboardMouseInput input);

    void tick();

    void draw();

    void onChange();

    void onClose();

    void onMouseClick(int button, int action, int mods);

    void onMouseMove(double newX, double newY, double oldX, double oldY);

    void onKey(int key, int scancode, int action, int mods);

    void onScroll(double xOffset, double yOffset);
}
