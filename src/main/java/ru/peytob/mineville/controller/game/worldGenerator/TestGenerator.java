package ru.peytob.mineville.controller.game.worldGenerator;

import ru.peytob.mineville.model.repository.BlockRepository;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.world.Chunk;
import ru.peytob.mineville.model.game.world.World;

public class TestGenerator extends WorldGenerator {
    private final int seaLevel = 30;
    private final int hillsSize;

    public TestGenerator(int seed, BlockRepository repository) {
        super(seed, repository);
        this.hillsSize = seed % 4;
    }

    protected void generateChunk(Chunk chunk) {
        Block grass = repository.getBlock("grass");
        Block stone = repository.getBlock("stone");

        for (int x = 0; x < Chunk.SIDE_SIZE_X; ++x) {
            for (int z = 0; z < Chunk.SIDE_SIZE_Z; ++z) {
                double sinArg = chunk.getPosition().x + x;
                int height = (int) (seaLevel + hillsSize * Math.sin(sinArg));
                for (int y = 0; y < height - 1; ++y) {
                    chunk.setBlock(x, y, z, stone);
                }
                chunk.setBlock(x, height - 1, z, grass);
            }
        }
    }

    public World generate() {
        World world = new World(4);
        for (int x = 0; x < world.getWorldSide(); ++x) {
            for (int z = 0; z < world.getWorldSide(); ++z) {
                generateChunk(world.getChunk(x, z));
            }
        }
        return world;
    }
}
