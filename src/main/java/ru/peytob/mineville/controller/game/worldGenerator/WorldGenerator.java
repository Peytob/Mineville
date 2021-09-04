package ru.peytob.mineville.controller.game.worldGenerator;

import ru.peytob.mineville.math.noise.FractalBrownianMotion;
import ru.peytob.mineville.math.noise.Perlin2dNoise;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.game.world.Chunk;
import ru.peytob.mineville.model.repository.Repository;

public abstract class WorldGenerator {
    protected final int seed;
    protected final Repository<Block> repository;

    public WorldGenerator(int seed, Repository<Block> repository) {
        this.seed = seed;
        this.repository = repository;
    }

    public abstract void generateChunk(Chunk chunk);
}
