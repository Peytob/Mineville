package ru.peytob.mineville.view;

import ru.peytob.mineville.controller.WindowController;
import ru.peytob.mineville.model.graphic.Mesh;

import static org.lwjgl.opengl.GL33.*;

public class WorldDrawer {
    WindowController windowController;

    public WorldDrawer(WindowController windowController) {
        this.windowController = windowController;
    }

    public void clear() {
        glClearColor(0.5f, 0.25f, 0.25f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);
    }

    public void draw(Mesh mesh, int type) {
        glBindVertexArray(mesh.getVao());
        glDrawArrays(type, 0, mesh.getVertexesCount());
    }

    public void display() {
        windowController.display();
    }
}
