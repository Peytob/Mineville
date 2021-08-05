package ru.peytob.mineville.model.game.world;

import ru.peytob.mineville.math.ImmutableVec3i;
import ru.peytob.mineville.model.game.object.Block;

public interface IBlockly {
    void setBlock(int x, int y, int z, Block block);
    void removeBlock(int x, int y, int z);
    Block getBlock(int x, int y, int z);
    ImmutableVec3i getSizes();
    boolean isPointIn(int x, int y, int z);
}
