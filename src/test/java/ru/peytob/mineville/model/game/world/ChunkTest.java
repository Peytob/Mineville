package ru.peytob.mineville.model.game.world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.peytob.mineville.math.Vec3i;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.object.BlockBuilder;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ChunkTest {
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
        Chunk chunk = new Chunk(new Vec3i(0, 0, 0));

        for (int y = 0; y < chunk.getSizes().getY(); y++) {
            int x = y % chunk.getSizes().getX();
            int z = y % chunk.getSizes().getZ();

            chunk.setBlock(x, y, z, block1);
            assertEquals(block1.getId(), chunk.getBlock(x, y, z).getId());
        }

        // chunk.getSizes().getY() blocks inside

        int blocksInsideCount = 0;
        for (int x = 0; x < chunk.getSizes().getX(); x++) {
            for (int y = 0; y < chunk.getSizes().getY(); y++) {
                for (int z = 0; z < chunk.getSizes().getZ(); z++) {
                    if (chunk.getBlock(x, y, z) != null) {
                        blocksInsideCount++;
                    }
                }
            }
        }

        assertEquals(chunk.getSizes().getY(), blocksInsideCount);
    }

    @Test
    void removeBlock() {
        Chunk chunk = new Chunk(new Vec3i(0, 0, 0));

        chunk.setBlock(0, 0, 0, block1);
        assertNotNull(chunk.getBlock(0, 0, 0));
        chunk.removeBlock(0, 0, 0);
        assertNull(chunk.getBlock(0, 0, 0));

        chunk.removeBlock(0, 0, 0);
        assertNull(chunk.getBlock(0, 0, 0));

        chunk.setBlock(5, 5, 5, block1);
        assertNotNull(chunk.getBlock(5, 5, 5));
        chunk.removeBlock(0, 0, 0);
        assertNotNull(chunk.getBlock(5, 5, 5));
    }

    @Test
    void isPointIn() {
        Chunk chunk = new Chunk(new Vec3i(0, 0, 0));
        Random random = new Random();

        for (int y = 0; y < chunk.getSizes().getY(); y++) {
            assertTrue(chunk.isPointIn(random.nextInt(16), y, random.nextInt(16)));
        }

        assertTrue(chunk.isPointIn(0, 0, 0));
        assertTrue(chunk.isPointIn(chunk.getSizes().getX() - 1, chunk.getSizes().getY() - 1, chunk.getSizes().getZ() - 1));

        assertFalse(chunk.isPointIn(-1, 0, 0));
        assertFalse(chunk.isPointIn(0, 0, -1));
        assertFalse(chunk.isPointIn(0, -1, 0));
        assertFalse(chunk.isPointIn(chunk.getSizes().getX() + 1, chunk.getSizes().getY() + 1, chunk.getSizes().getZ() + 1));

    }
}