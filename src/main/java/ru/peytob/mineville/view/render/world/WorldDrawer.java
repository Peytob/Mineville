package ru.peytob.mineville.view.render.world;

import ru.peytob.mineville.controller.WindowController;
import ru.peytob.mineville.controller.draw.OctreeMeshBuilder;
import ru.peytob.mineville.math.ImmutableMat4;
import ru.peytob.mineville.math.Mat4;
import ru.peytob.mineville.math.SpecialMatrix;
import ru.peytob.mineville.math.Vec3i;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.world.Chunk;
import ru.peytob.mineville.model.game.world.Octree;
import ru.peytob.mineville.model.game.world.World;
import ru.peytob.mineville.model.graphic.Mesh;
import ru.peytob.mineville.model.graphic.TextureBlockAtlas;
import ru.peytob.mineville.model.graphic.TexturesPack;
import ru.peytob.mineville.model.graphic.shader.ShadersPack;
import ru.peytob.mineville.model.graphic.shader.WorldShader;
import ru.peytob.mineville.view.render.Drawer;

import static org.lwjgl.opengl.GL33.*;

public class WorldDrawer extends Drawer {
    private World world;
    private final TexturesPack textureBlockAtlas;
    private WorldShader worldShader;

    public WorldDrawer(WindowController windowController, ShadersPack shadersPack, TexturesPack textureBlockAtlas) {
        super(windowController, shadersPack);
        this.world = null;
        this.worldShader = shadersPack.getWorldShader();
        this.textureBlockAtlas = textureBlockAtlas;
    }

    public void clear() {
        glClearColor(0.5f, 0.25f, 0.25f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void onShadersPackChanged() {
        this.worldShader = this.shadersPack.getWorldShader();
    }

    private void justDrawIt(ImmutableMat4 transform) {
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LESS);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);

        // Fix for white lines between tiles. But it is not perfect.
        glBlendEquationSeparate(GL_FUNC_ADD, GL_FUNC_ADD);
        glBlendFuncSeparate(GL_ONE, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ONE_MINUS_SRC_ALPHA);

        glActiveTexture(textureBlockAtlas.getBlockAtlasTexture().getId());

        for (int x = 0; x < world.getWorldSide(); x++) {
            for (int z = 0; z < world.getWorldSide(); z++) {
                ImmutableMat4 position = SpecialMatrix.computeTranslation(x * Chunk.SIDE_SIZE_X, 0, z * Chunk.SIDE_SIZE_Z);
                draw(world.getChunk(x, z), position.multiplication(transform));
            }
        }
    }

    public void draw(Chunk chunk, ImmutableMat4 transform) {
        for (int y = 0; y < Chunk.OCTREES_COUNT; ++y) {
            ImmutableMat4 localTrans = SpecialMatrix.computeTranslation(0, Octree.ROOT_SIDE_SIZE * y, 0);
            draw(chunk.getOctree(y), localTrans.multiplication(transform));
        }
    }

    public void draw(Octree octree, ImmutableMat4 transform) {
        if (octree.getMesh() == null) {
            octree.setMesh(makeOctreeMesh(octree));
        }

        worldShader.setModelMatrix(transform);
        draw(octree.getMesh(), GL_TRIANGLES);
    }

    // It's controller, but in view ^^
    public Mesh makeOctreeMesh(Octree octree) {
        OctreeMeshBuilder builder = new OctreeMeshBuilder();
        Vec3i octreePosition = octree.getPosition();

        for (int x = 0; x < Octree.ROOT_SIDE_SIZE; x++) {
            for (int y = 0; y < Octree.ROOT_SIDE_SIZE; y++) {
                for (int z = 0; z < Octree.ROOT_SIDE_SIZE; z++) {
                    Block block = octree.getBlock(x, y, z);

                    if (block == null) {
                        continue;
                    }

                    if (world.getBlock(octreePosition.x + x, octreePosition.y + y - 1, octreePosition.z + z) == null) {
                        builder.addBottom(block, x, y, z);
                    }

                    if (world.getBlock(octreePosition.x + x + 1, octreePosition.y + y, octreePosition.z + z) == null) {
                        builder.addEast(block, x, y, z);
                    }

                    if (world.getBlock(octreePosition.x + x, octreePosition.y + y, octreePosition.z + z + 1) == null) {
                        builder.addNorth(block, x, y, z);
                    }

                    if (world.getBlock(octreePosition.x + x, octreePosition.y + y, octreePosition.z + z - 1) == null) {
                        builder.addSouth(block, x, y, z);
                    }

                    if (world.getBlock(octreePosition.x + x - 1, octreePosition.y + y, octreePosition.z + z) == null) {
                        builder.addWest(block, x, y, z);
                    }

                    if (world.getBlock(octreePosition.x + x, octreePosition.y + y + 1, octreePosition.z + z) == null) {
                        builder.addTop(block, x, y, z);
                    }
                }
            }
        }

        return builder.buildMesh();
    }

    public void changeDrawMode() {
        if (glGetInteger(GL_POLYGON_MODE) == GL_FILL) {
            glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        } else {
            glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        }
    }

    public void draw(World target, ImmutableMat4 transform) {
        world = target;
        clear();
        justDrawIt(transform);
        world = null;
    }
}
