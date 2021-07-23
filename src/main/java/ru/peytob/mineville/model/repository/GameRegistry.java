package ru.peytob.mineville.model.repository;

import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.loader.BaseBlock;

public class GameRegistry {
    static private final GameRegistry instance = new GameRegistry();

    static public GameRegistry getInstance() {
        return instance;
    }

    private final Repository<Block> blockRepository;
    private final GameRegistryModifier modifier;

    private GameRegistry() {
        this.blockRepository = new Repository<>();
        this.modifier = new GameRegistryModifier();
    }

    public ImmutableRepository<Block> getBlockRepository() {
        return blockRepository;
    }

    public GameRegistryModifier getModifier() {
        return modifier;
    }

    class GameRegistryModifier {
        public Integer addBlock(BaseBlock baseBlock) {
            Integer id = blockRepository.getCount();
            Block block = new Block(id, baseBlock);
            blockRepository.add(block);
            return id;
        }

        public void removeBlock(Block block) {
            blockRepository.remove(block);
        }

        public void removeBlock(Integer blockId) {
            blockRepository.remove(blockId);
        }

        public void removeBlock(String blockRepositoryName) {
            blockRepository.remove(blockRepositoryName);
        }
    }
}
