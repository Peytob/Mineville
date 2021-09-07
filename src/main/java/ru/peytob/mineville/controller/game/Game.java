package ru.peytob.mineville.controller.game;

import ru.peytob.mineville.controller.ApplicationController;
import ru.peytob.mineville.controller.WindowController;
import ru.peytob.mineville.controller.game.state.IGameState;
import ru.peytob.mineville.controller.game.state.RunningGameState;
import ru.peytob.mineville.controller.game.worldGenerator.TestGenerator;
import ru.peytob.mineville.controller.game.worldGenerator.WorldGenerator;
import ru.peytob.mineville.math.ImmutableVec2i;
import ru.peytob.mineville.math.Vec2;
import ru.peytob.mineville.model.game.world.Chunk;
import ru.peytob.mineville.model.game.world.World;
import ru.peytob.mineville.model.graphic.TexturesPack;
import ru.peytob.mineville.model.graphic.shader.ShadersPack;
import ru.peytob.mineville.model.repository.GameRegistry;
import ru.peytob.mineville.view.input.KeyboardMouseInput;
import ru.peytob.mineville.view.render.world.WorldDrawer;

public class Game {
    private IGameState state;
    private boolean isRunning;
    private final ShadersPack currentShaders;
    private final TexturesPack currentTextures;
    private final WindowController windowController;
    private final Vec2 cursorPosition;
    private final ApplicationController applicationController;

    public Game(GameRegistry gameRegistry, WindowController windowController, ApplicationController applicationController) {
        this.isRunning = true;
        this.currentShaders = gameRegistry.getShadersPackRepository().get("mineville::default");
        this.currentTextures = gameRegistry.getTexturesPackRepository().get("default");
        this.windowController = windowController;
        this.cursorPosition = new Vec2(windowController.getCursorPosition());
        this.applicationController = applicationController;

        setState(new RunningGameState(this));
    }

    public void tick() {
        state.tick();
    }

    public void draw() {
        state.draw();
    }

    public IGameState getState() {
        return state;
    }

    public ShadersPack getCurrentShaders() {
        return currentShaders;
    }

    public TexturesPack getCurrentTextures() {
        return currentTextures;
    }

    public double getLastFrameTimeSeconds() {
        return applicationController.getLastFrameTimeSeconds();
    }

    public void setState(IGameState state) {
        if (this.state != null) {
            this.state.onChange();
        }

        this.state = state;
        this.state.onSet();
    }

    public WindowController getWindowController() {
        return windowController;
    }

    public void handleInput(KeyboardMouseInput input) {
        state.handleInput(input);
    }

    public void onMouseClick(int button, int action, int mods) {
        state.onMouseClick(button, action, mods);
    }

    public void onMouseMove(double newX, double newY) {
        state.onMouseMove(newX, newY, cursorPosition.getX(), cursorPosition.getY());
        cursorPosition.setX((float) newX).setY((float) newY);
    }

    public void onKey(int key, int scancode, int action, int mods) {
        state.onKey(key, scancode, action, mods);
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
}
