package ru.peytob.mineville.model.game.world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.peytob.mineville.math.ImmutableVec2i;
import ru.peytob.mineville.math.Vec2i;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.object.BlockBuilder;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {
    Block block1;
    Block block2;
    Block block3;

    @BeforeEach
    void initBlocks() {
        BlockBuilder blockBuilder = new BlockBuilder();
        blockBuilder.setId(1);
        this.block1 = new Block(blockBuilder);
        blockBuilder.setId(2);
        this.block2 = new Block(blockBuilder);
        blockBuilder.setId(3);
        this.block3 = new Block(blockBuilder);
    }

    @Test
    void setAndGetBlock() {
        setAndGetBlock(1, 1, -51);
        setAndGetBlock(2, 0, 0);
    }

    void setAndGetBlock(int radius, int originX, int originY) {
        World world = emptyWorld(radius, originX, originY);
        originX *= Chunk.SIDE_SIZE_X;
        originY *= Chunk.SIDE_SIZE_Z;

        for (int x = -radius * Chunk.SIDE_SIZE_X; x < radius * Chunk.SIDE_SIZE_X; x += 5) {
            for (int y = 0; y < Chunk.SIDE_SIZE_Y; y++) {
                for (int z = -radius * Chunk.SIDE_SIZE_X + 5; z < radius * Chunk.SIDE_SIZE_Z; z += 16) {
                    world.setBlock(originX + x, y, originY + z, block1);
                    world.setBlock(originX + x, y, originY + z - 2, block2);
                }
            }
        }

        for (int x = -radius * Chunk.SIDE_SIZE_X; x < radius * Chunk.SIDE_SIZE_X; x += 5) {
            for (int y = 0; y < Chunk.SIDE_SIZE_Y; y++) {
                for (int z = -radius * Chunk.SIDE_SIZE_X + 5; z < radius * Chunk.SIDE_SIZE_Z; z += 16) {
                    assertEquals(block1.getId(), world.getBlock(originX + x, y, originY + z).getId());
                    assertEquals(block2.getId(), world.getBlock(originX + x, y, originY + z - 2).getId());
                }
            }
        }
    }

    @Test
    void removeBlock() {
        removeBlock(1, 1, -51);
        removeBlock(12, 0, 0);
    }

    void removeBlock(int radius, int originX, int originY) {
        World world = emptyWorld(radius, originX, originY);
        originX *= Chunk.SIDE_SIZE_X;
        originY *= Chunk.SIDE_SIZE_Z;

        world.setBlock(originX, 0, originY, block1);
        assertNotNull(world.getBlock(originX, 0, originY));
        world.removeBlock(originX, 0, originY);
        assertNull(world.getBlock(originX, 0, originY));

        world.setBlock(originX + 5, 5, originY + 5, block1);
        assertNotNull(world.getBlock(originX + 5, 5, originY + 5));
        world.removeBlock(originX, 0, originY);
        assertNotNull(world.getBlock(originX + 5, 5, originY + 5));
    }

    @Test
    void isPointIn() {
        isPointIn(5, 0, 0);
        isPointIn(2, 6, -4);
    }

    void isPointIn(int radius, int originX, int originY) {
        World world = emptyWorld(radius, originX, originY);
        originX *= Chunk.SIDE_SIZE_X;
        originY *= Chunk.SIDE_SIZE_Z;

        for (int y = 0; y < Chunk.SIDE_SIZE_Y; y += 8) {
            assertTrue(world.isPointIn(originX, y, originY));
            assertTrue(world.isPointIn(originX - 15, y, originY + 31));
            assertTrue(world.isPointIn(originX + 16, y, originY - 32));
        }

        for (int x = -radius * Chunk.SIDE_SIZE_X; x < radius * Chunk.SIDE_SIZE_X; x += 8) {
            for (int y = 0; y < Chunk.SIDE_SIZE_Y; y += 8) {
                assertTrue(world.isPointIn(originX + x, y, originY + x));
            }
        }

        assertFalse(world.isPointIn(originX + (radius + 1) * Chunk.SIDE_SIZE_X + 1, 0, originY));
        assertFalse(world.isPointIn(originX - (radius + 1) * Chunk.SIDE_SIZE_X - 1, 0, originY));
        assertFalse(world.isPointIn(originX, Chunk.SIDE_SIZE_Y + 1, originY));
        assertFalse(world.isPointIn(originX, -1, originY));
        assertFalse(world.isPointIn(originX, 0, originY + (radius + 1) * Chunk.SIDE_SIZE_Z + 1));
        assertFalse(world.isPointIn(originX, 0, originY - (radius + 1) * Chunk.SIDE_SIZE_Z - 1));
    }

    World emptyWorld(int radius, int originX, int originY) {
        List<Chunk> chunks = new LinkedList<>();

        for (int x = originX - radius; x <= originX + radius; ++x) {
            for (int y = originY - radius; y <= originY + radius; ++y) {
                chunks.add(new Chunk(new ImmutableVec2i(x, y)));
            }
        }

        return new World(chunks);
    }

    World emptyWorld(int radius) {
        return emptyWorld(radius, 0, 0);
    }
}
