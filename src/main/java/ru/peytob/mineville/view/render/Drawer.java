package ru.peytob.mineville.view.render;

import ru.peytob.mineville.controller.WindowController;
import ru.peytob.mineville.model.graphic.Mesh;
import ru.peytob.mineville.model.graphic.shader.ShadersPack;

import static org.lwjgl.opengl.GL33.*;

public abstract class Drawer {
    final protected WindowController windowController;
    protected ShadersPack shadersPack;

    public Drawer(WindowController windowController, ShadersPack shadersPack) {
        this.windowController = windowController;
        this.shadersPack = shadersPack;
    }

    public void draw(Mesh mesh, int mode) {
        glBindVertexArray(mesh.getVao());
        glDrawArrays(mode, 0, mesh.getVertexesCount());
    }

    public abstract void onShadersPackChanged();

    public void setShadersPack(ShadersPack shadersPack) {
        this.shadersPack = shadersPack;
        onShadersPackChanged();
    }

    public WindowController getWindowController() {
        return windowController;
    }
}
