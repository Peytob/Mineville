package ru.peytob.mineville.model.game.world;

import ru.peytob.mineville.math.Vec3i;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.graphic.Mesh;

public class Octree implements IBlockly {
    // TODO: Make octrees!
    public static final int ROOT_SIDE_SIZE = 16;

    Block[][][] blocks;
    Mesh mesh;

    public Octree() {
        this.blocks = new Block[ROOT_SIDE_SIZE][ROOT_SIDE_SIZE][ROOT_SIDE_SIZE];
        this.mesh = null;
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        if (isPointIn(x, y, z)) {
            blocks[x][y][z] = block;
        }
    }

    @Override
    public void removeBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            blocks[x][y][z] = null;
        }
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            return blocks[x][y][z];
        }

        return null;
    }

    @Override
    public Vec3i getSizes() {
        return new Vec3i(ROOT_SIDE_SIZE, ROOT_SIDE_SIZE, ROOT_SIDE_SIZE);
    }

    @Override
    public boolean isPointIn(int x, int y, int z) {
        return x >= 0 && x < ROOT_SIDE_SIZE && y >= 0 && y < ROOT_SIDE_SIZE && z >= 0 && z < ROOT_SIDE_SIZE;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}
