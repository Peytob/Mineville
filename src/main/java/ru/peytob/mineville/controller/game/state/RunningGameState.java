package ru.peytob.mineville.controller.game.state;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL33;
import ru.peytob.mineville.controller.draw.CameraController;
import ru.peytob.mineville.controller.game.Game;
import ru.peytob.mineville.controller.loader.ImageLoader;
import ru.peytob.mineville.math.*;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.graphic.Image;
import ru.peytob.mineville.model.opengl.Texture;
import ru.peytob.mineville.view.input.KeyboardMouseInput;
import ru.peytob.mineville.view.render.world.WorldDrawer;

import java.io.IOException;

public class RunningGameState implements IGameState {
    private final Game game;
    private final Vec2 cursorPosition;
    private final CameraController cameraController;

    public RunningGameState(Game game) {
        this.game = game;
        this.cursorPosition = game.getWorldDrawer().getWindowController().getCursorPosition();

        Vec2i windowSizes = game.getWorldDrawer().getWindowController().getWindowSizes();
        this.cameraController = new CameraController(new Vec3(0, 0, -10 ), 0, (float) Math.toRadians(90),
                (float) Math.toRadians(75), (float) windowSizes.x / (float) windowSizes.y);
    }

    @Override
    public void onSet() {
        Vec3i sizes = game.getWorld().getSizes();
        Block block = game.getResources().getBlockRepository().getBlock(1);

        for (int y = 8; y < sizes.y; y += 8) {
            for (int x = 0; x < sizes.x; x++) {
                for (int z = 0; z < sizes.z; z++) {
                    game.getWorld().setBlock(x, y, z, block);
                }
            }
        }

        game.getWorld().setBlock(0, 0, 0, block);
    }

    @Override
    public void handleInput() {
        KeyboardMouseInput input = game.getWorldDrawer().getWindowController().getKeyboardMouseInput();

        Vec3 cameraOffset = new Vec3(0 ,0, 0);
        float speed = 0.15f;

        if (input.isKeyPressed(GLFW.GLFW_KEY_W)) {
            cameraOffset = cameraOffset.plus(cameraController.getFrontVector().multiplication(speed));
        }

        if (input.isKeyPressed(GLFW.GLFW_KEY_A)) {
            cameraOffset = cameraOffset.minus(cameraController.getRightVector().multiplication(speed));
        }

        if (input.isKeyPressed(GLFW.GLFW_KEY_D)) {
            cameraOffset = cameraOffset.plus(cameraController.getRightVector().multiplication(speed));
        }

        if (input.isKeyPressed(GLFW.GLFW_KEY_S)) {
            cameraOffset = cameraOffset.minus(cameraController.getFrontVector().multiplication(speed));
        }

        cameraController.move(cameraOffset);
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
        game.getResources().getShadersPack().getWorldShader().use();
        game.getResources().getShadersPack().getWorldShader().setProjectionMatrix(cameraController.computeProjection());
        game.getResources().getShadersPack().getWorldShader().setViewMatrix(cameraController.computeView());

        final WorldDrawer drawer = game.getWorldDrawer();
        drawer.draw(game.getWorld(), Mat4.computeIdentity());
    }

    @Override
    public void onChange() {
    }

    @Override
    public void onClose() {
    }

    @Override
    public void onMouseClick(int button, int action, int mods) {
    }

    @Override
    public void onMouseMove(double newX, double newY) {
        float dx = (float) newX - cursorPosition.x;
        float dy = (float) newY - cursorPosition.y;
        cursorPosition.x = (float) newX;
        cursorPosition.y = (float) newY;

        cameraController.lookAround(dx * 0.1f, -dy * 0.1f);
    }

    @Override
    public void onKeyPress(int key, int scancode, int action, int mods) {
        switch (key) {
            case GLFW.GLFW_KEY_Q:
                game.close();
                break;

            default:
                break;
        }
    }

    @Override
    public void onScroll(double xOffset, double yOffset) {
    }
}
