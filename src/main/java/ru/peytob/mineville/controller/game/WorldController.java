package ru.peytob.mineville.controller.game;

import ru.peytob.mineville.controller.game.worldGenerator.WorldGenerator;
import ru.peytob.mineville.math.ImmutableVec2i;
import ru.peytob.mineville.math.Vec2i;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.world.Chunk;
import ru.peytob.mineville.model.game.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class WorldController {
    private final World world;
    private final WorldGenerator worldGenerator;

    public WorldController(WorldGenerator worldGenerator) {
        this.world = new World();
        this.worldGenerator = worldGenerator;
    }

    public boolean isChunkLoaded(int x, int z) {
        return world.getChunk(x, z) != null;
    }

    public Chunk getChunk(int x, int z) {
        return world.getChunk(x, z);
    }

    public Stream<Chunk> getChunkStream() {
        return world.getChunkStream();
    }

    public Chunk generateChunk(int x, int z) {
        Chunk chunk = new Chunk(new ImmutableVec2i(x, z));
        worldGenerator.generateChunk(chunk);
        world.loadChunk(chunk);
        return chunk;
    }

    public World getWorld() {
        return world;
    }

    public void setBlock(int x, int y, int z, Block block) {
        world.setBlock(x, y, z, block);
    }

    public void removeBlock(int x, int y, int z) {
        world.removeBlock(x, y, z);
    }

    public Block getBlock(int x, int y, int z) {
        return world.getBlock(x, y, z);
    }

    public boolean isPointIn(int x, int y, int z) {
        return world.isPointIn(x, y, z);
    }

    public int getLoadChunksCount() {
        return world.getLoadChunksCount();
    }
}
