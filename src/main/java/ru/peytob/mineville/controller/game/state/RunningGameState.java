package ru.peytob.mineville.controller.game.state;

import org.lwjgl.glfw.GLFW;
import ru.peytob.mineville.controller.draw.CameraController;
import ru.peytob.mineville.controller.game.Game;
import ru.peytob.mineville.controller.game.WorldController;
import ru.peytob.mineville.controller.game.worldGenerator.TestGenerator;
import ru.peytob.mineville.math.*;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.world.World;
import ru.peytob.mineville.model.repository.GameRegistry;
import ru.peytob.mineville.view.input.KeyboardMouseInput;
import ru.peytob.mineville.view.render.world.WorldDrawer;

public class RunningGameState implements IGameState {
    private final Game game;
    private final WorldDrawer worldDrawer;
    private final WorldController worldController;
    private final CameraController cameraController;

    public RunningGameState(Game game) {
        this.game = game;
        this.worldDrawer = new WorldDrawer(game.getWindowController(), game.getCurrentShaders(),
                game.getCurrentTextures());
        this.worldController = new WorldController(new TestGenerator(1,
                GameRegistry.getInstance().getBlockRepository()));
        ImmutableVec2i windowSizes = game.getWindowController().getWindowSizes();
        this.cameraController = new CameraController(new Vec3(0, 50, 0 ), 0, (float) Math.toRadians(90),
                (float) Math.toRadians(75), (float) windowSizes.getX() / (float) windowSizes.getY());
    }

    @Override
    public void onSet() {
        System.out.println("Set state: RunningGameState");

        int radius = 5;
        for (int x = -radius; x < radius; x++) {
            for (int z = -radius; z < radius; z++) {
                worldController.generateChunk(x, z);
            }
        }

        System.out.println("Test world: " + worldController.getLoadChunksCount());
    }

    @Override
    public void handleInput(KeyboardMouseInput input) {
        Vec3 cameraOffset = new Vec3(0 ,0, 0);
        float speed = 5.7f;

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

        cameraOffset.multiplication(speed * (float) game.getLastFrameTimeSeconds());
        cameraController.move(cameraOffset);
    }

    @Override
    public void tick() {
    }

    @Override
    public void draw() {
        worldDrawer.clear();

        game.getCurrentShaders().getWorldShader().use();
        game.getCurrentShaders().getWorldShader().setProjectionMatrix(cameraController.computeProjection());
        game.getCurrentShaders().getWorldShader().setViewMatrix(cameraController.computeView());

        worldDrawer.draw(worldController.getWorld(), SpecialMatrix.IDENTITY);
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
                    World world = worldController.getWorld();
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
    public void onMouseMove(double newX, double newY, double oldX, double oldY) {
        float dx = (float) (newX - oldX);
        float dy = (float) (newY - oldY);

        cameraController.lookAround(dx * 0.1f, -dy * 0.1f);
    }

    @Override
    public void onKey(int key, int scancode, int action, int mods) {
        if (action == GLFW.GLFW_PRESS) {
            switch (key) {
                case GLFW.GLFW_KEY_Q:
                    game.close();
                    break;

                case GLFW.GLFW_KEY_E:
                    worldDrawer.changeDrawMode();
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
