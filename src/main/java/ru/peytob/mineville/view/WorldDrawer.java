package ru.peytob.mineville.view;

import ru.peytob.mineville.controller.WindowController;
import ru.peytob.mineville.controller.draw.OctreeMeshBuilder;
import ru.peytob.mineville.math.Mat4;
import ru.peytob.mineville.math.Vec3i;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.world.Chunk;
import ru.peytob.mineville.model.game.world.Octree;
import ru.peytob.mineville.model.game.world.World;
import ru.peytob.mineville.model.graphic.Mesh;
import ru.peytob.mineville.model.graphic.shader.WorldShader;

import static org.lwjgl.opengl.GL33.*;

public class WorldDrawer {
    private final WindowController windowController;

    public WorldDrawer(WindowController windowController) {
        this.windowController = windowController;
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LESS);
    }

    public void clear() {
        glClearColor(0.5f, 0.25f, 0.25f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void draw(Mesh mesh, int mode) {
        glBindVertexArray(mesh.getVao());
        glDrawArrays(mode, 0, mesh.getVertexesCount());
    }

    public void draw(Octree octree, Mat4 transform) {
        // TODO move it to controller!!!
        if (octree.getMesh() == null) {
            OctreeMeshBuilder builder = new OctreeMeshBuilder();

            for (int x = 0; x < 16; x++) {
                for (int y = 0; y < 16; y++) {
                    for (int z = 0; z < 16; z++) {
                        Block block = octree.getBlock(x, y, z);
                        if (block == null) {
                            continue;
                        }
                        Vec3i position = new Vec3i(x, y, z);
                        builder.addBottom(block, position);
                        builder.addEast(block, position);
                        builder.addNorth(block, position);
                        builder.addSouth(block, position);
                        builder.addWest(block, position);
                        builder.addTop(block, position);
                    }
                }
            }

            octree.setMesh(builder.buildMesh());
        }

        draw(octree.getMesh(), GL_TRIANGLES);
    }

    public void draw(Chunk chunk, WorldShader shader, Mat4 transform) {
        Octree[] octrees = chunk.getOctrees();

        for (int i = 0; i < 8; ++i) {
            shader.setModelMatrix(Mat4.computeTranslation(0, 16 * i, 0).multiplication(transform));
            draw(octrees[i], null);
        }
    }

    public void draw(World world, WorldShader worldShader, Mat4 transform) {
        Chunk[][] chunks = world.getChunks();

        for (int x = 0; x < chunks.length; x++) {
            for (int z = 0; z < chunks.length; z++) {
                Mat4 position = Mat4.computeTranslation(x * Chunk.SIDE_SIZE_X, 0, z * Chunk.SIDE_SIZE_Z);
                draw(chunks[x][z], worldShader, position.multiplication(transform));
            }
        }
    }

    public void display() {
        windowController.display();
    }

    public WindowController getWindowController() {
        return windowController;
    }
}
