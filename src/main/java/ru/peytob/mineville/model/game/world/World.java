package ru.peytob.mineville.model.game.world;

import ru.peytob.mineville.math.Vec3i;
import ru.peytob.mineville.model.game.object.Block;

public class World implements IBlockly {
    // Is Chunk[size * size] faster?
    private final Chunk[][] chunks;

    public World(int sizes) {
        this.chunks = new Chunk[sizes][sizes];
        for (int x = 0; x < chunks.length; x++) {
            for (int z = 0; z < chunks.length; z++) {
                chunks[x][z] = new Chunk();
            }
        }
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        if (isPointIn(x, y, z)) {
            int innerX = x % Chunk.SIDE_SIZE_X;
            int innerZ = z % Chunk.SIDE_SIZE_Z;
            int outerX = x / Chunk.SIDE_SIZE_X;
            int outerZ = z / Chunk.SIDE_SIZE_Z;
            chunks[outerX][outerZ].setBlock(innerX, y, innerZ, block);
        }
    }

    @Override
    public void removeBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            int innerX = x % Chunk.SIDE_SIZE_X;
            int innerZ = z % Chunk.SIDE_SIZE_Z;
            int outerX = x / Chunk.SIDE_SIZE_X;
            int outerZ = z / Chunk.SIDE_SIZE_Z;
            chunks[outerX][outerZ].removeBlock(innerX, y, innerZ);
        }
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            int innerX = x % Chunk.SIDE_SIZE_X;
            int innerZ = z % Chunk.SIDE_SIZE_Z;
            int outerX = x / Chunk.SIDE_SIZE_X;
            int outerZ = z / Chunk.SIDE_SIZE_Z;
            return chunks[outerX][outerZ].getBlock(innerX, y, innerZ);
        }

        return null;
    }

    @Override
    public Vec3i getSizes() {
        return new Vec3i(Chunk.SIDE_SIZE_X * chunks.length, Chunk.SIDE_SIZE_Y, Chunk.SIDE_SIZE_Z * chunks.length);
    }

    @Override
    public boolean isPointIn(int x, int y, int z) {
        return
                x >= 0 && x < Chunk.SIDE_SIZE_X * chunks.length &&
                y >= 0 && y < Chunk.SIDE_SIZE_Y &&
                z >= 0 && z < Chunk.SIDE_SIZE_Z * chunks.length;
    }

    public Chunk getChunk(int x, int z) {
        return chunks[x][z];
    }

    public int getWorldSide() {
        return chunks.length;
    }
}
