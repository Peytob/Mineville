package ru.peytob.mineville.model.game;

import ru.peytob.mineville.model.game.object.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class BlockRepository {
    private final Map<Integer, Block> blocksById;
    private final Map<String, Block> blocksByTextId;

    public BlockRepository() {
        blocksById = new HashMap<>();
        blocksByTextId = new HashMap<>();
    }

    public Block getBlock(String id) {
        return blocksByTextId.get(id);
    }

    public Block getBlock(Integer id) {
        return blocksById.get(id);
    }

    public boolean containsId(String id) {
        return blocksByTextId.containsKey(id);
    }

    public boolean containsId(Integer id) {
        return blocksById.containsKey(id);
    }

    public void addBlock(Block block) {
        blocksById.put(block.getId(), block);
        blocksByTextId.put(block.getRegistryName(), block);
    }

    public void removeBlock(Block block) {
        blocksById.remove(block.getId());
        blocksByTextId.remove(block.getRegistryName());
    }

    public int getBlocksCount() {
        return blocksById.size();
    }

    public Stream<Block> getBlocksStream() {
        return blocksById.values().stream();
    }

    public Stream<Block> getBlocksParallelStream() {
        return blocksById.values().parallelStream();
    }
}
