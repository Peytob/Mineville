package ru.peytob.mineville.model.game.world;

import ru.peytob.mineville.math.Vec3i;
import ru.peytob.mineville.model.game.object.Block;

import static ru.peytob.mineville.math.CoordinatesUtils.toFlat2D;

public class World implements IBlockly {
    private final Chunk[] chunks;
    private final Vec3i sizes;
    private final int worldSide;

    public World(int sizes) {
        this.chunks = new Chunk[sizes * sizes];
        this.worldSide = sizes;

        for (int x = 0; x < worldSide; x++) {
            for (int z = 0; z < worldSide; z++) {
                setChunk(x, z, new Chunk());
            }
        }
        this.sizes = new Vec3i(Chunk.SIDE_SIZE_X * worldSide, Chunk.SIDE_SIZE_Y, Chunk.SIDE_SIZE_Z * worldSide);
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        if (isPointIn(x, y, z)) {
            int innerX = x & (Chunk.SIDE_SIZE_X - 1);
            int innerZ = z & (Chunk.SIDE_SIZE_Z - 1);
            int outerX = x >> Chunk.SIDE_SIZE_X_POWER_2;
            int outerZ = z >> Chunk.SIDE_SIZE_Z_POWER_2;
            getChunk(outerX, outerZ).setBlock(innerX, y, innerZ, block);
        }
    }

    @Override
    public void removeBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            int innerX = x & (Chunk.SIDE_SIZE_X - 1);
            int innerZ = z & (Chunk.SIDE_SIZE_Z - 1);
            int outerX = x >> Chunk.SIDE_SIZE_X_POWER_2;
            int outerZ = z >> Chunk.SIDE_SIZE_Z_POWER_2;
            getChunk(outerX, outerZ).removeBlock(innerX, y, innerZ);
        }
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            int innerX = x & (Chunk.SIDE_SIZE_X - 1);
            int innerZ = z & (Chunk.SIDE_SIZE_Z - 1);
            int outerX = x >> Chunk.SIDE_SIZE_X_POWER_2;
            int outerZ = z >> Chunk.SIDE_SIZE_Z_POWER_2;
            return getChunk(outerX, outerZ).getBlock(innerX, y, innerZ);
        }

        return null;
    }

    @Override
    public Vec3i getSizes() {
        return sizes;
    }

    @Override
    public boolean isPointIn(int x, int y, int z) {
        return
                x >= 0 && x < Chunk.SIDE_SIZE_X * worldSide &&
                y >= 0 && y < Chunk.SIDE_SIZE_Y &&
                z >= 0 && z < Chunk.SIDE_SIZE_Z * worldSide;
    }

    public Chunk getChunk(int x, int z) {
        return chunks[toFlat2D(x, z, worldSide)];
    }

    private void setChunk(int x, int z, Chunk chunk) {
        chunks[toFlat2D(x, z, worldSide)] = chunk;
    }

    public int getWorldSide() {
        return worldSide;
    }
}
