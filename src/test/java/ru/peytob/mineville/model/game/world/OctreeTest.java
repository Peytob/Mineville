package ru.peytob.mineville.model.game.world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.object.BlockBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OctreeTest {
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
        Octree octree = new Octree();

        octree.setBlock(0, 0, 0, block1);
        assertEquals(1, octree.getBlock(0, 0, 0).getId());

        octree.setBlock(5, 9, 10, block2);
        assertEquals(2, octree.getBlock(5, 9, 10).getId());

        octree.setBlock(5, 9, 10, block2);
        assertEquals(2, octree.getBlock(5, 9, 10).getId());

        octree.setBlock(5, 9, 10, block1);
        assertEquals(1, octree.getBlock(5, 9, 10).getId());
    }

    @Test
    void removeBlock() {
        Octree octree = new Octree();

        octree.setBlock(0, 0, 0, block1);
        assertNotNull(octree.getBlock(0, 0, 0));
        octree.removeBlock(0, 0, 0);
        assertNull(octree.getBlock(0, 0, 0));

        octree.setBlock(5, 5, 5, block1);
        assertNotNull(octree.getBlock(5, 5, 5));
        octree.removeBlock(0, 0, 0);
        assertNotNull(octree.getBlock(5, 5, 5));
    }

    @Test
    void isPointIn() {
        Octree octree = new Octree();

        assertTrue(octree.isPointIn(0, 0, 0));

        assertFalse(octree.isPointIn(-1, 0, 0));
        assertFalse(octree.isPointIn(0, -1, 0));
        assertFalse(octree.isPointIn(0, 0, -1));

        assertFalse(octree.isPointIn(20, 0, 0));
        assertFalse(octree.isPointIn(0, 20, 0));
        assertFalse(octree.isPointIn(0, 0, 20));
    }
}