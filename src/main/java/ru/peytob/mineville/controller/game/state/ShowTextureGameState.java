package ru.peytob.mineville.controller.game.state;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL33;
import ru.peytob.mineville.controller.game.Game;
import ru.peytob.mineville.math.Mat4;
import ru.peytob.mineville.model.graphic.Mesh;
import ru.peytob.mineville.model.graphic.shader.WorldShader;
import ru.peytob.mineville.model.opengl.Texture;
import ru.peytob.mineville.view.render.world.WorldDrawer;

public class ShowTextureGameState implements IGameState {
    private final Texture target;
    private final Game game;
    private Mesh rectangleMesh;

    public ShowTextureGameState(Game game, Texture target) {
        this.game = game;
        this.target = target;
    }

    @Override
    public void onSet() {
        System.out.println("Set state: ShowTextureGameState");
        this.rectangleMesh = new Mesh(new float[] {
                -1.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f - 1.0f,

                1.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f,
                1.0f, 1.0f - 1.0f,

                -1.0f, -1.0f, 0.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f - 0.0f,

                1.0f, -1.0f, 0.0f,
                0.0f, 0.0f, 0.0f,
                1.0f, 1.0f - 0.0f,
        });

        WorldShader shader = game.getCurrentShaders().getWorldShader();
        shader.setProjectionMatrix(Mat4.computeIdentity());
        shader.setViewMatrix(Mat4.computeIdentity());
        shader.setModelMatrix(Mat4.computeIdentity());
    }

    @Override
    public void handleInput() {
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
        WorldDrawer drawer = game.getWorldDrawer();
        drawer.draw(rectangleMesh, GL33.GL_TRIANGLE_STRIP);
    }

    @Override
    public void onChange() {
        System.out.println("Change state: ShowTextureGameState");
        rectangleMesh.destroy();
    }

    @Override
    public void onClose() {
    }

    @Override
    public void onMouseClick(int button, int action, int mods) {
    }

    @Override
    public void onMouseMove(double newX, double newY) {
    }

    @Override
    public void onKeyPress(int key, int scancode, int action, int mods) {
        if (key == GLFW.GLFW_KEY_TAB && action == GLFW.GLFW_PRESS) {
            game.setState(new RunningGameState(game));
        }
    }

    @Override
    public void onScroll(double xOffset, double yOffset) {
    }
}
