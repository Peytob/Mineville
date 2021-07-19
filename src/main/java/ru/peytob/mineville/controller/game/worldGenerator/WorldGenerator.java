package ru.peytob.mineville.controller.game.worldGenerator;

import ru.peytob.mineville.model.game.BlockRepository;

public abstract class WorldGenerator {
    protected final int seed;
    protected final BlockRepository repository;

    public WorldGenerator(int seed, BlockRepository repository) {
        this.seed = seed;
        this.repository = repository;
    }
}
