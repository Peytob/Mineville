package ru.peytob.mineville.model.game;

public class Resources {
    private final BlockRepository blockRepository;

    public Resources() {
        blockRepository = new BlockRepository();
    }

    public BlockRepository getBlockRepository() {
        return blockRepository;
    }
}
