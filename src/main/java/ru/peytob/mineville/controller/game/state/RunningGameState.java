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
    public void onSet() {
    }

    @Override
    public void handleInput() {
        KeyboardInput input = game.getKeyboardInput();

        if (input.isKeyPressed(GLFW.GLFW_KEY_W)) {
            System.out.println("Move forward");
        } else if (input.isKeyPressed(GLFW.GLFW_KEY_A)) {
            System.out.println("Move left");
        } else if (input.isKeyPressed(GLFW.GLFW_KEY_D)) {
            System.out.println("Move right");
        } else if (input.isKeyPressed(GLFW.GLFW_KEY_S)) {
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

    @Override
    public void onChange() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onMouseClick(int button, int action, int mods) {
        String actionStr;

        switch (action) {
            case GLFW.GLFW_RELEASE:
                actionStr = "Release";
                break;

            case GLFW.GLFW_PRESS:
                actionStr = "Press";
                break;

            case GLFW.GLFW_REPEAT:
                actionStr = "Repeat";
                break;

            default:
                actionStr = "Undef";
        }

        String buttonStr;

        switch (button) {
            case GLFW.GLFW_MOUSE_BUTTON_LEFT:
                buttonStr = "left";
                break;

            case GLFW.GLFW_MOUSE_BUTTON_RIGHT:
                buttonStr = "right";
                break;

            case GLFW.GLFW_MOUSE_BUTTON_MIDDLE:
                buttonStr = "middle";
                break;

            default:
                buttonStr = "Undef";
        }

        System.out.println("Mouse " + buttonStr + " button: " + actionStr);
    }

    @Override
    public void onMouseMove(double newX, double newY) {
        System.out.println("Mouse move to " + newX + "; " + newY);
    }

    @Override
    public void onKeyPress(int key, int scancode, int action, int mods) {
        String actionStr;

        switch (action) {
            case GLFW.GLFW_RELEASE:
                actionStr = "Release";
                break;

            case GLFW.GLFW_PRESS:
                actionStr = "Press";
                break;

            case GLFW.GLFW_REPEAT:
                actionStr = "Repeat";
                break;

            default:
                actionStr = "Undef";
        }

        System.out.println("Key" + actionStr + ": " + GLFW.glfwGetKeyName(key,scancode));
    }

    @Override
    public void onScroll(double xOffset, double yOffset) {
        System.out.println("Scroll: " + xOffset + "; " + yOffset);
    }
}
