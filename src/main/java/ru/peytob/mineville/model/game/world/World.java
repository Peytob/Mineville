package ru.peytob.mineville.model.game.world;

import ru.peytob.mineville.math.ImmutableVec2i;
import ru.peytob.mineville.math.ImmutableVec3i;
import ru.peytob.mineville.math.Vec2i;
import ru.peytob.mineville.model.game.object.Block;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class World implements IBlockly {
    static private final ImmutableVec3i SIZES = new ImmutableVec3i(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

    private final Map<ImmutableVec2i, Chunk> chunks;

    public World() {
        this.chunks = new HashMap<>();
    }

    public World(Collection<Chunk> chunks) {
        this();
        chunks.forEach(this::setChunk);
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        if (isPointIn(x, y, z)) {
            int innerX = x & (Chunk.SIDE_SIZE_X - 1);
            int innerZ = z & (Chunk.SIDE_SIZE_Z - 1);
            int outerX = x >> Chunk.SIDE_SIZE_X_POWER_2;
            int outerZ = z >> Chunk.SIDE_SIZE_Z_POWER_2;

            Chunk chunk = getChunk(outerX, outerZ);
            if (chunk != null) {
                getChunk(outerX, outerZ).setBlock(innerX, y, innerZ, block);
            }
        }
    }

    @Override
    public void removeBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            int innerX = x & (Chunk.SIDE_SIZE_X - 1);
            int innerZ = z & (Chunk.SIDE_SIZE_Z - 1);
            int outerX = x >> Chunk.SIDE_SIZE_X_POWER_2;
            int outerZ = z >> Chunk.SIDE_SIZE_Z_POWER_2;

            Chunk chunk = getChunk(outerX, outerZ);
            if (chunk != null) {
                getChunk(outerX, outerZ).removeBlock(innerX, y, innerZ);
            }
        }
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        if (isPointIn(x, y, z)) {
            int innerX = x & (Chunk.SIDE_SIZE_X - 1);
            int innerZ = z & (Chunk.SIDE_SIZE_Z - 1);
            int outerX = x >> Chunk.SIDE_SIZE_X_POWER_2;
            int outerZ = z >> Chunk.SIDE_SIZE_Z_POWER_2;
            Chunk chunk = getChunk(outerX, outerZ);
            if (chunk != null) {
                return getChunk(outerX, outerZ).getBlock(innerX, y, innerZ);
            }
        }

        return null;
    }

    @Override
    public ImmutableVec3i getSizes() {
        return SIZES;
    }

    @Override
    public boolean isPointIn(int x, int y, int z) {
        ImmutableVec2i gridPosition = new ImmutableVec2i(x >> Chunk.SIDE_SIZE_X_POWER_2, z >> Chunk.SIDE_SIZE_X_POWER_2);
        Chunk chunk = getChunk(gridPosition);

        int innerX = x & (Chunk.SIDE_SIZE_X - 1);
        int innerZ = z & (Chunk.SIDE_SIZE_Z - 1);
        return chunk != null && chunk.isPointIn(innerX, y, innerZ);
    }

    public void unloadChunk(int x, int y) {
        unloadChunk(new Vec2i(x, y));
    }

    public void unloadChunk(ImmutableVec2i gridPosition) {
        chunks.remove(gridPosition);
    }

    public int getLoadChunksCount() {
        return chunks.size();
    }

    public Chunk getChunk(int x, int z) {
        return getChunk(new ImmutableVec2i(x, z));
    }

    public Chunk getChunk(ImmutableVec2i gridPosition) {
        return chunks.get(gridPosition);
    }

    public Stream<Chunk> getChunkStream() {
        return chunks.values().stream();
    }

    public void setChunk(Chunk chunk) {
        chunks.put(chunk.getGridPosition(), chunk);
    }
}
