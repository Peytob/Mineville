package ru.peytob.mineville.controller.game;

import ru.peytob.mineville.controller.game.worldGenerator.WorldGenerator;
import ru.peytob.mineville.math.ImmutableVec2i;
import ru.peytob.mineville.math.Vec2i;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.world.Chunk;
import ru.peytob.mineville.model.game.world.World;

import java.util.HashMap;
import java.util.Map;

public class WorldController {
    private final World world;
    private final WorldGenerator worldGenerator;
//    private final Map<Object, ChunkLoader> chunkLoaders; // Later
    private int loadRadius;
    private ImmutableVec2i origin;

    public WorldController(WorldGenerator worldGenerator) {
        this.world = new World();
        this.worldGenerator = worldGenerator;

        this.loadRadius = 3;
        this.origin = new ImmutableVec2i();
    }

    public Chunk getChunk(int x, int z) {
        return world.getChunk(x, z);
    }

    /* === TODO ВРЕМЕННАЯ ЗАТЫЧКА ДО М-ВА ЧАНКЛОУДЕРОВ */

    public void setLoadRadius(int loadRadius) {
        this.loadRadius = loadRadius;
        reload();
    }

    public void moveOrigin(ImmutableVec2i delta) {
        ImmutableVec2i newOrigin = new ImmutableVec2i(origin.getX() + delta.getX(), origin.getY() + delta.getY());

        int xMin = Math.min(newOrigin.getX() - loadRadius, origin.getX() - loadRadius);
        int xMax = Math.max(newOrigin.getX() + loadRadius, origin.getX() + loadRadius);
        int zMin = Math.min(newOrigin.getY() - loadRadius, origin.getY() - loadRadius);
        int zMax = Math.max(newOrigin.getY() + loadRadius, origin.getY() + loadRadius);

        if (xMax - xMin > loadRadius || zMax - zMin > loadRadius) {
            world.unloadAllChunks();
            origin = newOrigin;
            reload();
        }

//        System.out.println("x: " + xMin + " -> " + xMax + "; z: " + zMin + " -> " + zMax);
        for (int x = xMin; x <= xMax; x++) {
            for (int z = zMin; z <= zMax; z++) {
                if (x >= newOrigin.getX() - loadRadius && x <= newOrigin.getX() + loadRadius &&
                    z >= newOrigin.getY() - loadRadius && z <= newOrigin.getY() + loadRadius) {
                    if (world.getChunk(x, z) == null) {
//                        System.out.print('g');
                        generateChunk(x, z);
                    }

                    else {
//                        System.out.print('-');
                    }
                }

                else {
//                    System.out.print('u');
                    world.unloadChunk(x, z);
                }
            }
//            System.out.println("");
        }

        origin = newOrigin;
    }

    private void reload() {
        for (int x = origin.getX() - loadRadius; x < origin.getX() + loadRadius; x++) {
            for (int z = origin.getY() - loadRadius; z < origin.getY() + loadRadius; z++) {
                generateChunk(x, z);
            }
        }
    }

//    public void addChunkLoader(Object object, int radius, int gridX, int gridZ) {
//    }
//
//    public void removeChunkLoader(Object object) {
//    }

    protected Chunk generateChunk(int x, int z) {
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
