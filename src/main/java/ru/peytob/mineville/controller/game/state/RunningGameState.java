package ru.peytob.mineville.controller.game.state;

import org.lwjgl.glfw.GLFW;
import ru.peytob.mineville.controller.draw.CameraController;
import ru.peytob.mineville.controller.game.Game;
import ru.peytob.mineville.math.*;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.world.World;
import ru.peytob.mineville.model.repository.GameRegistry;
import ru.peytob.mineville.view.input.KeyboardMouseInput;
import ru.peytob.mineville.view.render.world.WorldDrawer;

public class RunningGameState implements IGameState {
    private final Game game;
    private final Vec2 cursorPosition;
    private final CameraController cameraController;

    public RunningGameState(Game game) {
        this.game = game;
        this.cursorPosition = new Vec2(game.getWorldDrawer().getWindowController().getCursorPosition());

        ImmutableVec2i windowSizes = game.getWorldDrawer().getWindowController().getWindowSizes();
        this.cameraController = new CameraController(new Vec3(0, 50, 0 ), 0, (float) Math.toRadians(90),
                (float) Math.toRadians(75), (float) windowSizes.getX() / (float) windowSizes.getY());
    }

    @Override
    public void onSet() {
        System.out.println("Set state: RunningGameState");
        this.game.getWorldController().setLoadRadius(3);
    }

    @Override
    public void handleInput() {
        KeyboardMouseInput input = game.getWorldDrawer().getWindowController().getKeyboardMouseInput();

        Vec3 cameraOffset = new Vec3(0 ,0, 0);
        float speed = 0.15f;

        if (input.isKeyPressed(GLFW.GLFW_KEY_W)) {
            cameraOffset.plus(cameraController.getFrontVector());
        }

        if (input.isKeyPressed(GLFW.GLFW_KEY_A)) {
            cameraOffset.minus(cameraController.getRightVector());
        }

        if (input.isKeyPressed(GLFW.GLFW_KEY_D)) {
            cameraOffset.plus(cameraController.getRightVector());
        }

        if (input.isKeyPressed(GLFW.GLFW_KEY_S)) {
            cameraOffset.minus(cameraController.getFrontVector());
        }

        cameraOffset.multiplication(speed);
        int oldChunkGridX = (int) cameraController.getPosition().getX() / 16;
        if (cameraController.getPosition().getX() < 0) {
            oldChunkGridX -= 1;
        }

        int oldChunkGridZ = (int) cameraController.getPosition().getZ() / 16;
        if (cameraController.getPosition().getZ() < 0) {
            oldChunkGridZ -= 1;
        }

        cameraController.move(cameraOffset);

        int chunkGridX = (int) cameraController.getPosition().getX() / 16;
        if (cameraController.getPosition().getX() < 0) {
            chunkGridX -= 1;
        }

        int chunkGridZ = (int) cameraController.getPosition().getZ() / 16;
        if (cameraController.getPosition().getZ() < 0) {
            chunkGridZ -= 1;
        }

        if (chunkGridX != oldChunkGridX || chunkGridZ != oldChunkGridZ) {
            ImmutableVec2i delta = new ImmutableVec2i(chunkGridX - oldChunkGridX, chunkGridZ - oldChunkGridZ);
            System.out.println("Move: " + delta);
            game.getWorldController().moveOrigin(delta);
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
        game.getCurrentShaders().getWorldShader().use();
        game.getCurrentShaders().getWorldShader().setProjectionMatrix(cameraController.computeProjection());
        game.getCurrentShaders().getWorldShader().setViewMatrix(cameraController.computeView());

        final WorldDrawer drawer = game.getWorldDrawer();
        drawer.draw(game.getWorldController().getWorld(), SpecialMatrix.IDENTITY);
    }

    @Override
    public void onChange() {
        System.out.println("Change state: RunningGameState");
    }

    @Override
    public void onClose() {
    }

    @Override
    public void onMouseClick(int button, int action, int mods) {
        if (action == GLFW.GLFW_PRESS) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                try {
                    System.out.println("Camera global position: " + cameraController.getPosition());
                    World world = game.getWorldController().getWorld();
                    ImmutableVec3 pos = cameraController.getPosition();
                    Block block = GameRegistry.getInstance().getBlockRepository().get("mineville::stone");
                    world.setBlock((int) pos.getX(), (int) pos.getY(), (int) pos.getZ(), block);
                }

                 catch (Exception exp) {
                    exp.printStackTrace();
                 }
            }
        }
    }

    @Override
    public void onMouseMove(double newX, double newY) {
        float dx = (float) newX - cursorPosition.getX();
        float dy = (float) newY - cursorPosition.getY();
        cursorPosition.setX((float) newX).setY((float) newY);

        cameraController.lookAround(dx * 0.1f, -dy * 0.1f);
    }

    @Override
    public void onKeyPress(int key, int scancode, int action, int mods) {
        if (action == GLFW.GLFW_PRESS) {
            switch (key) {
                case GLFW.GLFW_KEY_Q:
                    game.close();
                    break;

                case GLFW.GLFW_KEY_E:
                    game.getWorldDrawer().changeDrawMode();
                    break;

                case GLFW.GLFW_KEY_TAB:
                    game.setState(new ShowTextureGameState(game, game.getCurrentTextures().getBlockAtlasTexture()));
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void onScroll(double xOffset, double yOffset) {
    }
}
