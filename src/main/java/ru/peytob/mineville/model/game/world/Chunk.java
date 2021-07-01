package ru.peytob.mineville.model.game.world;

import ru.peytob.mineville.math.Vec3i;
import ru.peytob.mineville.model.game.object.Block;

public class Chunk implements IBlockly {

    public static final int SIDE_SIZE_X = Octree.ROOT_SIDE_SIZE;
    public static final int SIDE_SIZE_Z = Octree.ROOT_SIDE_SIZE;
    public static final int OCTREES_COUNT = 8;
    public static final int SIDE_SIZE_Y = OCTREES_COUNT * Octree.ROOT_SIDE_SIZE;

    private final Octree[] octrees;

    public Chunk() {
        this.octrees = new Octree[OCTREES_COUNT];
        for (int i = 0; i < octrees.length; ++i) {
            octrees[i] = new Octree();
        }
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        if (isPointIn(x, y, z)) {
            octrees[y / Octree.ROOT_SIDE_SIZE].setBlock(x, y % Octree.ROOT_SIDE_SIZE, z, block);
        }
    }

    @Override
    public void removeBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            octrees[y / Octree.ROOT_SIDE_SIZE].removeBlock(x, y % Octree.ROOT_SIDE_SIZE, z);
        }
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            return octrees[y / Octree.ROOT_SIDE_SIZE].getBlock(x, y % Octree.ROOT_SIDE_SIZE, z);
        }

        return null;
    }

    @Override
    public Vec3i getSizes() {
        return new Vec3i(SIDE_SIZE_X, SIDE_SIZE_Y, SIDE_SIZE_Z);
    }

    @Override
    public boolean isPointIn(int x, int y, int z) {
        return x >= 0 && x < SIDE_SIZE_X && y >= 0 && y < SIDE_SIZE_Y && z >= 0 && z < SIDE_SIZE_Z;
    }

    public Octree getOctree(int y) {
        return octrees[y];
    }
}
