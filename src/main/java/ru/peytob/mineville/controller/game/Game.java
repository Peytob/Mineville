package ru.peytob.mineville.controller.game;

import ru.peytob.mineville.controller.WindowController;
import ru.peytob.mineville.controller.game.state.IGameState;
import ru.peytob.mineville.controller.game.state.RunningGameState;
import ru.peytob.mineville.model.game.Resources;
import ru.peytob.mineville.model.game.world.Chunk;
import ru.peytob.mineville.view.WorldDrawer;
import ru.peytob.mineville.view.input.KeyboardMouseInput;

public class Game {
    private IGameState state;

    private final Resources resources;
    private Chunk world;

    private final WorldDrawer worldDrawer;
    private final KeyboardMouseInput keyboardMouseInput;

    private boolean isRunning;

    public Game(Resources resources, WindowController windowController) {
        this.isRunning = true;
        this.resources = resources;
        this.worldDrawer = new WorldDrawer(windowController);
        this.keyboardMouseInput = windowController.getKeyboardMouseInput();
        this.world = new Chunk();
        world.setBlock(0, 0, 0, resources.getBlockRepository().getBlock(1));
        setState(new RunningGameState(this));
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
        if (this.state != null) {
            this.state.onChange();
        }

        this.state = state;
        this.state.onSet();
    }

    public Resources getResources() {
        return resources;
    }

    public WorldDrawer getWorldDrawer() {
        return worldDrawer;
    }

    public void handleInput() {
        state.handleInput();
    }

    public KeyboardMouseInput getKeyboardMouseInput() {
        return keyboardMouseInput;
    }

    public void onMouseClick(int button, int action, int mods) {
        state.onMouseClick(button, action, mods);
    }

    public void onMouseMove(double newX, double newY) {
        state.onMouseMove(newX, newY);
    }

    public void onKeyPress(int key, int scancode, int action, int mods) {
        state.onKeyPress(key, scancode, action, mods);
    }

    public void onScroll(double xOffset, double yOffset) {
        state.onScroll(xOffset, yOffset);
    }

    public void close() {
        this.isRunning = false;
        this.state.onClose();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public Chunk getWorld() {
        return world;
    }
}
