package ru.peytob.mineville.controller.game.state;

import org.lwjgl.glfw.GLFW;
import ru.peytob.mineville.controller.game.Game;
import ru.peytob.mineville.view.input.KeyboardInput;

public class RunningGameState implements IGameState {
    private final Game game;

    public RunningGameState(Game game) {
        this.game = game;
    }

    @Override
    public void handleInput() {
        KeyboardInput input = game.getKeyboardInput();

        if (input.isKeyPressed(GLFW.GLFW_KEY_W)) {
            System.out.println("Move forward");
        }

        else if (input.isKeyPressed(GLFW.GLFW_KEY_A)) {
            System.out.println("Move left");
        }

        else if (input.isKeyPressed(GLFW.GLFW_KEY_D)) {
            System.out.println("Move right");
        }

        else if (input.isKeyPressed(GLFW.GLFW_KEY_S)) {
            System.out.println("Move back");
        }
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
