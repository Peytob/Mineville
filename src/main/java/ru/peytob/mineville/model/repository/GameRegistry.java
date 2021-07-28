package ru.peytob.mineville.model.repository;

import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.graphic.TextureBlockAtlas;
import ru.peytob.mineville.model.graphic.TexturesPack;
import ru.peytob.mineville.model.graphic.block.BlockModel;
import ru.peytob.mineville.model.graphic.shader.ShadersPack;
import ru.peytob.mineville.model.loader.base.BaseBlock;
import ru.peytob.mineville.model.loader.base.BaseBlockModel;
import ru.peytob.mineville.model.loader.base.BaseShadersPack;

public class GameRegistry {
    static private final GameRegistry instance = new GameRegistry();

    static public GameRegistry getInstance() {
        return instance;
    }

    private final Repository<Block> blockRepository;
    private final Repository<BlockModel> modelRepository;
    private final Repository<TexturesPack> texturesPackRepository;
    private final Repository<ShadersPack> shadersPackRepository;

    private final GameRegistryModifier modifier;

    private GameRegistry() {
        this.blockRepository = new Repository<>();
        this.modelRepository = new Repository<>();
        this.texturesPackRepository = new Repository<>();
        this.shadersPackRepository = new Repository<>();
        this.modifier = new GameRegistryModifier();
    }

    public Repository<Block> getBlockRepository() {
        return blockRepository;
    }

    public Repository<BlockModel> getModelRepository() {
        return modelRepository;
    }

    public Repository<TexturesPack> getTexturesPackRepository() {
        return texturesPackRepository;
    }

    public Repository<ShadersPack> getShadersPackRepository() {
        return shadersPackRepository;
    }

    public GameRegistryModifier getModifier() {
        return modifier;
    }

    public class GameRegistryModifier {
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

        public Integer addTexturesPack(String repositoryName, TextureBlockAtlas textureBlockAtlas) {
            Integer id = texturesPackRepository.getCount();
            TexturesPack pack = new TexturesPack(repositoryName, id, textureBlockAtlas);
            texturesPackRepository.add(pack);
            return id;
        }

        public void removeTexturesPack(TexturesPack texturesPack) {
            texturesPackRepository.remove(texturesPack);
        }

        public void removeTexturesPack(Integer texturesPackId) {
            texturesPackRepository.remove(texturesPackId);
        }

        public void removeTexturesPack(String texturesPackRepositoryName) {
            texturesPackRepository.remove(texturesPackRepositoryName);
        }

        public Integer addShadersPack(BaseShadersPack baseShadersPack) {
            Integer id = shadersPackRepository.getCount();
            ShadersPack pack = new ShadersPack(id, baseShadersPack);
            shadersPackRepository.add(pack);
            return id;
        }

        public void removeShadersPack(ShadersPack shadersPack) {
            shadersPackRepository.remove(shadersPack);
        }

        public void removeShadersPack(Integer shadersPackId) {
            shadersPackRepository.remove(shadersPackId);
        }

        public void removeShadersPack(String shadersPackRepositoryName) {
            shadersPackRepository.remove(shadersPackRepositoryName);
        }
    }
}
