package ru.peytob.mineville.model.game.world;

import ru.peytob.mineville.math.Vec3i;
import ru.peytob.mineville.model.game.object.Block;

public class Chunk implements IBlockly {
    Octree[] octrees;

    public Chunk() {
        this.octrees = new Octree[8];
        for (int i = 0; i < octrees.length; ++i) {
            octrees[i] = new Octree();
        }
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        if (isPointIn(x, y, z)) {
            octrees[y / 16].setBlock(x, y % 16, z, block);
        }
    }

    @Override
    public void removeBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            octrees[y / 16].removeBlock(x, y % 16, z);
        }
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            return octrees[y / 16].getBlock(x, y % 16, z);
        }

        return null;
    }

    @Override
    public Vec3i getSizes() {
        return new Vec3i(16, octrees.length * 16, 16);
    }

    @Override
    public boolean isPointIn(int x, int y, int z) {
        return x >= 0 && x < 16 && y >= 0 && y < octrees.length * 16 && z >= 0 && z < 16;
    }

    public Octree[] getOctrees() {
        return octrees;
    }
}
