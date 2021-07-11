package ru.peytob.mineville.view.render.world;

import ru.peytob.mineville.controller.WindowController;
import ru.peytob.mineville.controller.draw.OctreeMeshBuilder;
import ru.peytob.mineville.math.Mat4;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.world.Chunk;
import ru.peytob.mineville.model.game.world.Octree;
import ru.peytob.mineville.model.game.world.World;
import ru.peytob.mineville.model.graphic.Mesh;
import ru.peytob.mineville.model.graphic.TexturesPack;
import ru.peytob.mineville.model.graphic.shader.ShadersPack;
import ru.peytob.mineville.model.graphic.shader.WorldShader;
import ru.peytob.mineville.view.render.Drawer;

import static org.lwjgl.opengl.GL33.*;

public class WorldDrawer extends Drawer {
    private World world;
    private final TexturesPack texturesPack;
    private WorldShader worldShader;

    public WorldDrawer(WindowController windowController, ShadersPack shadersPack, TexturesPack texturesPack) {
        super(windowController, shadersPack);
        this.world = null;
        this.worldShader = shadersPack.getWorldShader();
        this.texturesPack = texturesPack;
    }

    public void clear() {
        glClearColor(0.5f, 0.25f, 0.25f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void onShadersPackChanged() {
        this.worldShader = this.shadersPack.getWorldShader();
    }

    private void justDrawIt(Mat4 transform) {
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LESS);
        glActiveTexture(texturesPack.getTexture().getId());

        for (int x = 0; x < world.getWorldSide(); x++) {
            for (int z = 0; z < world.getWorldSide(); z++) {
                Mat4 position = Mat4.computeTranslation(x * Chunk.SIDE_SIZE_X, 0, z * Chunk.SIDE_SIZE_Z);
                draw(world.getChunk(x, z), position.multiplication(transform));
            }
        }
    }

    public void draw(Chunk chunk, Mat4 transform) {
        for (int y = 0; y < Chunk.OCTREES_COUNT; ++y) {
            Mat4 localTrans = Mat4.computeTranslation(0, Octree.ROOT_SIDE_SIZE * y, 0);
            draw(chunk.getOctree(y), localTrans.multiplication(transform));
        }
    }

    public void draw(Octree octree, Mat4 transform) {
        if (octree.getMesh() == null) {
            octree.setMesh(makeOctreeMesh(octree));
        }

        worldShader.setModelMatrix(transform);
        draw(octree.getMesh(), GL_TRIANGLES);
    }

    // It's controller, but in view ^^
    public Mesh makeOctreeMesh(Octree octree) {
        OctreeMeshBuilder builder = new OctreeMeshBuilder();

        for (int x = 0; x < Octree.ROOT_SIDE_SIZE; x++) {
            for (int y = 0; y < Octree.ROOT_SIDE_SIZE; y++) {
                for (int z = 0; z < Octree.ROOT_SIDE_SIZE; z++) {
                    Block block = octree.getBlock(x, y, z);

                    if (block == null) {
                        continue;
                    }

                    if (octree.getBlock(x, y - 1, z) == null) {
                        builder.addBottom(block, x, y, z);
                    }

                    if (octree.getBlock(x + 1, y, z)  == null) {
                        builder.addEast(block, x, y, z);
                    }

                    if (octree.getBlock(x, y, z + 1)  == null) {
                        builder.addNorth(block, x, y, z);
                    }

                    if (octree.getBlock(x, y, z - 1) == null) {
                        builder.addSouth(block, x, y, z);
                    }

                    if (octree.getBlock(x - 1, y, z)  == null) {
                        builder.addWest(block, x, y, z);
                    }

                    if (octree.getBlock(x, y + 1, z)  == null) {
                        builder.addTop(block, x, y, z);
                    }
                }
            }
        }

        return builder.buildMesh();
    }

    public void draw(World target, Mat4 transform) {
        world = target;
        clear();
        justDrawIt(transform);
        world = null;
    }
}
