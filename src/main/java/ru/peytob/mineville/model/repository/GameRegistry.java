package ru.peytob.mineville.model.repository;

import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.graphic.block.BlockModel;
import ru.peytob.mineville.model.loader.base.BaseBlock;
import ru.peytob.mineville.model.loader.base.BaseBlockModel;

public class GameRegistry {
    static private final GameRegistry instance = new GameRegistry();

    static public GameRegistry getInstance() {
        return instance;
    }

    private final Repository<Block> blockRepository;
    private final Repository<BlockModel> modelRepository;

    private final GameRegistryModifier modifier;

    private GameRegistry() {
        this.blockRepository = new Repository<>();
        this.modelRepository = new Repository<>();
        this.modifier = new GameRegistryModifier();
    }

    public Repository<Block> getBlockRepository() {
        return blockRepository;
    }
    public Repository<BlockModel> getModelRepository() {
        return modelRepository;
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

        public void removeBlock(String modelRepositoryName) {
            modelRepository.remove(modelRepositoryName);
        }

        public Integer addModel(BaseBlockModel baseModel) {
            Integer id = modelRepository.getCount();
            BlockModel model = new BlockModel(id, baseModel);
            modelRepository.add(model);
            return id;
        }

        public void removeModel(BlockModel model) {
            modelRepository.remove(model);
        }

        public void removeModel(Integer modelId) {
            modelRepository.remove(modelId);
        }

        public void removeModel(String modelRepositoryName) {
            modelRepository.remove(modelRepositoryName);
        }
    }
}
