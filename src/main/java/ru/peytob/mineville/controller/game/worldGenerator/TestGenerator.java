package ru.peytob.mineville.controller.game.worldGenerator;

import ru.peytob.mineville.math.ImmutableVec3i;
import ru.peytob.mineville.math.noise.FractalBrownianMotion;
import ru.peytob.mineville.math.noise.INoise2D;
import ru.peytob.mineville.math.noise.NoiseFacade;
import ru.peytob.mineville.math.noise.Perlin2dNoise;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.world.Chunk;
import ru.peytob.mineville.model.repository.Repository;

public class TestGenerator extends WorldGenerator {
    private final NoiseFacade noiseFacade;

    public TestGenerator(int seed, Repository<Block> repository) {
        super(seed, repository);
        INoise2D noise2D = new FractalBrownianMotion(new Perlin2dNoise(seed), 10, 0.5f);
        this.noiseFacade = new NoiseFacade(noise2D, 0.01f);
    }

    @Override
    public void generateChunk(Chunk chunk) {
        Block sand = repository.get("mineville::sand");
        Block grass = repository.get("mineville::grass");
        Block water = repository.get("mineville::water");

        ImmutableVec3i chunkPosition = chunk.getPosition();

        for (int x = 0; x < Chunk.SIDE_SIZE_X; x++) {
            for (int z = 0; z < Chunk.SIDE_SIZE_Z; z++) {
                float X = chunkPosition.getX() + x;
                float Z = chunkPosition.getZ() + z;
                float noise = noiseFacade.getPointNormalized(X, Z);
                int height = (40) + (int) (noise * 30); // 40 - 70

                if (noise < 0.45f) { // Water
                    height = 40 + (int) (0.45 * 30); // 40 - 70
                    if (noiseFacade.getPointNormalized(X + 1, Z) < 0.45f ||
                            noiseFacade.getPointNormalized(X, Z + 1) < 0.45f ||
                            noiseFacade.getPointNormalized(X + 1, Z + 1) < 0.45f ||
                            noiseFacade.getPointNormalized(X - 1, Z) < 0.45f ||
                            noiseFacade.getPointNormalized(X, Z - 1) < 0.45f ||
                            noiseFacade.getPointNormalized(X - 1, Z - 1) < 0.45f) {
                        chunk.setBlock(x, height, z, water);
                    } else {
                        chunk.setBlock(x, height, z, grass);
                    }
                } else if (noise < 0.47f) { // Sand
                    if (noiseFacade.getPointNormalized(X + 3, Z) < 0.45f ||
                            noiseFacade.getPointNormalized(X, Z + 3) < 0.45f ||
                            noiseFacade.getPointNormalized(X + 3, Z + 3) < 0.45f ||
                            noiseFacade.getPointNormalized(X - 3, Z) < 0.45f ||
                            noiseFacade.getPointNormalized(X, Z - 3) < 0.45f ||
                            noiseFacade.getPointNormalized(X - 3, Z - 3) < 0.45f) {
                        chunk.setBlock(x, height, z, sand);
                    } else {
                        chunk.setBlock(x, height, z, grass);
                    }
                } else { // Grass
                    chunk.setBlock(x, height, z, grass);
                }
            }
        }
    }
}
