package ru.peytob.mineville.model.game.world;

import ru.peytob.mineville.math.Vec3i;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.graphic.Mesh;

import static ru.peytob.mineville.math.CoordinatesUtils.toFlat3D;

public class Octree implements IBlockly {
    // TODO: Make octrees!
    protected static final int ROOT_SIDE_SIZE_POWER_2 = 4;
    public static final int ROOT_SIDE_SIZE = 1 << ROOT_SIDE_SIZE_POWER_2;

    private final Block[] blocks;
    private Mesh mesh;
    private static final Vec3i sizes = new Vec3i(ROOT_SIDE_SIZE, ROOT_SIDE_SIZE, ROOT_SIDE_SIZE);

    public Octree() {
        this.blocks = new Block[ROOT_SIDE_SIZE * ROOT_SIDE_SIZE * ROOT_SIDE_SIZE];
        this.mesh = null;
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        if (isPointIn(x, y, z)) {
            blocks[toFlat3D(x, y, z, ROOT_SIDE_SIZE, ROOT_SIDE_SIZE)] = block;
        }
    }

    @Override
    public void removeBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            blocks[toFlat3D(x, y, z, ROOT_SIDE_SIZE, ROOT_SIDE_SIZE)] = null;
        }
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            return blocks[toFlat3D(x, y, z, ROOT_SIDE_SIZE, ROOT_SIDE_SIZE)];
        }

        return null;
    }

    @Override
    public Vec3i getSizes() {
        return sizes;
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
