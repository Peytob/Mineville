package ru.peytob.mineville.model.game.world;

import ru.peytob.mineville.math.ImmutableVec3i;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.graphic.Mesh;

import java.util.Arrays;
import java.util.Objects;

import static ru.peytob.mineville.math.CoordinatesUtils.toFlat3D;

public class Octree implements IBlockly {
    protected static final int ROOT_SIDE_SIZE_POWER_2 = 4;
    public static final int ROOT_SIDE_SIZE = 1 << ROOT_SIDE_SIZE_POWER_2;
    static final ImmutableVec3i SIZES = new ImmutableVec3i(ROOT_SIDE_SIZE, ROOT_SIDE_SIZE, ROOT_SIDE_SIZE);

    private final AbstractNode root;
    private Mesh mesh;
    private final ImmutableVec3i position;

    private boolean updateMeshFlag;

    public Octree(ImmutableVec3i position) {
        this.root = new InnerNode(ROOT_SIDE_SIZE);
        this.mesh = null;
        this.position = position;
        this.updateMeshFlag = true;
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        if (root.isPointIn(x, y, z)) {
            root.setBlock(x, y, z, block);
        }
    }

    @Override
    public void removeBlock(int x, int y, int z) {
        if (root.isPointIn(x, y, z)) {
            root.removeBlock(x, y, z);
        }
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        if (root.isPointIn(x, y, z)) {
            return root.getBlock(x, y, z);
        }

        return null;
    }

    public ImmutableVec3i getPosition() {
        return position;
    }

    @Override
    public ImmutableVec3i getSizes() {
        return SIZES;
    }

    @Override
    public boolean isPointIn(int x, int y, int z) {
        return root.isPointIn(x, y, z);
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public boolean isMeshShouldBeUpdated() {
        return updateMeshFlag;
    }

    public void markMeshAsUpdated() {
        updateMeshFlag = false;
    }

    static abstract class AbstractNode implements IBlockly {
        public abstract boolean isEmpty();
    }

    class InnerNode extends AbstractNode {
        private final int size;
        private final AbstractNode[] childrens;

        public InnerNode(int size) {
            this.size = size;
            this.childrens = (size == 2) ? new LeafNode[8] : new InnerNode[8];
        }

        @Override
        public void setBlock(int x, int y, int z, Block block) {
            int half = size >> 1;

            int innerX = x & (half - 1);
            int innerY = y & (half - 1);
            int innerZ = z & (half - 1);

            int arrayIndex = toFlat3D(x / half, y / half, z / half, 2, 2);

            if (childrens[arrayIndex] == null) {
                childrens[arrayIndex] = (size == 2) ? new LeafNode() : new InnerNode(size / 2);
            }

            childrens[arrayIndex].setBlock(innerX, innerY, innerZ, block);
        }

        @Override
        public void removeBlock(int x, int y, int z) {
            int half = size >> 1;

            int innerX = x & (half - 1);
            int innerY = y & (half - 1);
            int innerZ = z & (half - 1);

            int arrayIndex = toFlat3D(x / half, y / half, z / half, 2, 2);

            if (childrens[arrayIndex] != null) {
                childrens[arrayIndex].removeBlock(innerX, innerY, innerZ);

                if (childrens[arrayIndex].isEmpty()) {
                    childrens[arrayIndex] = null;
                }
            }
        }

        @Override
        public Block getBlock(int x, int y, int z) {
            int half = size >> 1;

            int innerX = x & (half - 1);
            int innerY = y & (half - 1);
            int innerZ = z & (half - 1);

            int arrayIndex = toFlat3D(x / half, y / half, z / half, 2, 2);

            if (childrens[arrayIndex] == null) {
                return null;
            }

            return childrens[arrayIndex].getBlock(innerX, innerY, innerZ);
        }

        @Override
        public ImmutableVec3i getSizes() {
            return null;
        }

        @Override
        public boolean isPointIn(int x, int y, int z) {
            return x >= 0 && x < size && y >= 0 && y < size && z >= 0 && z < size;
        }

        @Override
        public boolean isEmpty() {
            return Arrays.stream(childrens).allMatch(Objects::isNull);
        }
    }

    class LeafNode extends AbstractNode {
        private Block block;

        @Override
        public void setBlock(int x, int y, int z, Block block) {
            updateMeshFlag = true;
            this.block = block;
        }

        @Override
        public void removeBlock(int x, int y, int z) {
            updateMeshFlag = true;
            this.block = null;
        }

        @Override
        public Block getBlock(int x, int y, int z) {
            return block;
        }

        @Override
        public ImmutableVec3i getSizes() {
            return null;
        }

        @Override
        public boolean isPointIn(int x, int y, int z) {
            return x == 0 && y == 0 && z == 0;
        }

        @Override
        public boolean isEmpty() {
            return block == null;
        }
    }
}
