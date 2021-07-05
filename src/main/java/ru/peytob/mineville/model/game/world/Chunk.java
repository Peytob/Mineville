package ru.peytob.mineville.model.game.world;

import ru.peytob.mineville.math.Vec3i;
import ru.peytob.mineville.model.game.object.Block;

public class Chunk implements IBlockly {

    protected static final int SIDE_SIZE_X_POWER_2 = Octree.ROOT_SIDE_SIZE_POWER_2;
    public static final int SIDE_SIZE_X = 1 << SIDE_SIZE_X_POWER_2;
    protected static final int SIDE_SIZE_Z_POWER_2 = Octree.ROOT_SIDE_SIZE_POWER_2;
    public static final int SIDE_SIZE_Z = 1 << SIDE_SIZE_Z_POWER_2;

    public static final int OCTREES_COUNT = 8;
    public static final int SIDE_SIZE_Y = OCTREES_COUNT * Octree.ROOT_SIDE_SIZE;
    private static final Vec3i sizes = new Vec3i(SIDE_SIZE_X, SIDE_SIZE_Y, SIDE_SIZE_Z);

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
            octrees[y >> Octree.ROOT_SIDE_SIZE_POWER_2].setBlock(x, y & (Octree.ROOT_SIDE_SIZE - 1), z, block);
        }
    }

    @Override
    public void removeBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            octrees[y >> Octree.ROOT_SIDE_SIZE_POWER_2].removeBlock(x, y & (Octree.ROOT_SIDE_SIZE - 1), z);
        }
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            return octrees[y >> Octree.ROOT_SIDE_SIZE_POWER_2].getBlock(x, y & (Octree.ROOT_SIDE_SIZE - 1), z);
        }

        return null;
    }

    @Override
    public Vec3i getSizes() {
        return sizes;
    }

    @Override
    public boolean isPointIn(int x, int y, int z) {
        return x >= 0 && x < SIDE_SIZE_X && y >= 0 && y < SIDE_SIZE_Y && z >= 0 && z < SIDE_SIZE_Z;
    }

    public Octree getOctree(int y) {
        return octrees[y];
    }
}
