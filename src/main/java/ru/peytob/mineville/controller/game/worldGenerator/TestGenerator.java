package ru.peytob.mineville.controller.game.worldGenerator;

import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.world.Chunk;
import ru.peytob.mineville.model.game.world.World;
import ru.peytob.mineville.model.repository.Repository;

public class TestGenerator extends WorldGenerator {
    private final int seaLevel = 30;

    public TestGenerator(int seed, Repository<Block> repository) {
        super(seed, repository);
    }

    @Override
    public void generateChunk(Chunk chunk) {
        Block grass = repository.get("mineville::grass");
        Block stone = repository.get("mineville::stone");

        for (int y = 0; y < seaLevel; y++) {
            for (int xz = 0; xz < Chunk.SIDE_SIZE_X; xz++) {
                chunk.setBlock(xz, y, 0, grass);
                chunk.setBlock(0, y, xz, grass);
                chunk.setBlock(xz, y, Chunk.SIDE_SIZE_X - 1, grass);
                chunk.setBlock(Chunk.SIDE_SIZE_X - 1, y, xz, grass);
            }
        }
    }
}
