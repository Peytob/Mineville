package ru.peytob.mineville.model.game.world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.object.BlockBuilder;

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
        World world = new World(4);

        for (int x = 0; x < world.getSizes().getX(); x++) {
            for (int y = 0; y < world.getSizes().getY(); y++) {
                for (int z = 5; z < world.getSizes().getZ(); z += 16) {
                    world.setBlock(x, y, z, block1);
                    world.setBlock(x, y, z - 2, block2);
                }
            }
        }

        for (int x = 0; x < world.getSizes().getX(); x++) {
            for (int y = 0; y < world.getSizes().getY(); y++) {
                for (int z = 5; z < world.getSizes().getZ(); z += 16) {
                    assertEquals(block1.getId(), world.getBlock(x, y, z).getId());
                    assertEquals(block2.getId(), world.getBlock(x, y, z - 2).getId());
                }
            }
        }
    }

    @Test
    void removeBlock() {
        World world = new World(4);

        world.setBlock(0, 0, 0, block1);
        assertNotNull(world.getBlock(0, 0, 0));
        world.removeBlock(0, 0, 0);
        assertNull(world.getBlock(0, 0, 0));

        world.setBlock(5, 5, 5, block1);
        assertNotNull(world.getBlock(5, 5, 5));
        world.removeBlock(0, 0, 0);
        assertNotNull(world.getBlock(5, 5, 5));
    }

    @Test
    void isPointIn() {
        World world = new World(4);

        for (int y = 0; y < world.getSizes().getY(); y += 8) {
            assertTrue(world.isPointIn(0, y, 0));
        }

        for (int x = 0; x < world.getWorldSide(); x += 8) {
            for (int y = 0; y < world.getSizes().getY(); y += 8) {
                assertTrue(world.isPointIn(x, y, x));
            }
        }

        assertFalse(world.isPointIn(world.getSizes().getX() + 1, 0, 0));
        assertFalse(world.isPointIn(-1, 0, 0));
        assertFalse(world.isPointIn(0, world.getSizes().getY() + 1, 0));
        assertFalse(world.isPointIn(0, -1, 0));
        assertFalse(world.isPointIn(0, 0, world.getSizes().getZ() + 1));
        assertFalse(world.isPointIn(0, 0, -1));
    }
}
