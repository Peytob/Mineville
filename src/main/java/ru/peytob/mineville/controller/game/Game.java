package ru.peytob.mineville.controller.game;

import ru.peytob.mineville.controller.WindowController;
import ru.peytob.mineville.controller.game.state.IGameState;
import ru.peytob.mineville.controller.game.state.RunningGameState;
import ru.peytob.mineville.controller.game.worldGenerator.TestGenerator;
import ru.peytob.mineville.controller.game.worldGenerator.WorldGenerator;
import ru.peytob.mineville.math.ImmutableVec2i;
import ru.peytob.mineville.model.game.world.Chunk;
import ru.peytob.mineville.model.game.world.World;
import ru.peytob.mineville.model.graphic.TexturesPack;
import ru.peytob.mineville.model.graphic.shader.ShadersPack;
import ru.peytob.mineville.model.repository.GameRegistry;
import ru.peytob.mineville.view.render.world.WorldDrawer;

public class Game {
    private IGameState state;
    private final WorldController world;
    private final WorldDrawer worldDrawer;
    private boolean isRunning;
    private final ShadersPack currentShaders;
    private final TexturesPack currentTextures;

    public Game(GameRegistry gameRegistry, WindowController windowController) {
        this.isRunning = true;
        this.currentShaders = gameRegistry.getShadersPackRepository().get("mineville::default");
        this.currentTextures = gameRegistry.getTexturesPackRepository().get("default");
        this.worldDrawer = new WorldDrawer(windowController,
                this.currentShaders,
                this.currentTextures);

        WorldGenerator generator = new TestGenerator(123, gameRegistry.getBlockRepository());
        this.world = new WorldController(generator);
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

    public ShadersPack getCurrentShaders() {
        return currentShaders;
    }

    public TexturesPack getCurrentTextures() {
        return currentTextures;
    }

    public void setState(IGameState state) {
        if (this.state != null) {
            this.state.onChange();
        }

        this.state = state;
        this.state.onSet();
    }

    public WorldDrawer getWorldDrawer() {
        return worldDrawer;
    }

    public void handleInput() {
        state.handleInput();
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

    public WorldController getWorldController() {
        return world;
    }
}
