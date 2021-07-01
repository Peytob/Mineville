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

    public void draw(Octree octree, WorldShader shader, Mat4 transform) {
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

        shader.setModelMatrix(transform);
        draw(octree.getMesh(), GL_TRIANGLES);
    }

    public void draw(Chunk chunk, WorldShader shader, Mat4 transform) {
        for (int y = 0; y < Chunk.OCTREES_COUNT; ++y) {
            Mat4 localTrans = Mat4.computeTranslation(0, Octree.ROOT_SIDE_SIZE * y, 0);
            draw(chunk.getOctree(y), shader, localTrans.multiplication(transform));
        }
    }

    public void draw(World world, WorldShader worldShader, Mat4 transform) {
        for (int x = 0; x < world.getWorldSide(); x++) {
            for (int z = 0; z < world.getWorldSide(); z++) {
                Mat4 position = Mat4.computeTranslation(x * Chunk.SIDE_SIZE_X, 0, z * Chunk.SIDE_SIZE_Z);
                draw(world.getChunk(x, z), worldShader, position.multiplication(transform));
            }
        }
    }

    public WindowController getWindowController() {
        return windowController;
    }
}
