package ru.peytob.mineville.model.game.world;

import ru.peytob.mineville.math.Vec3i;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.graphic.Mesh;

import static ru.peytob.mineville.math.CoordinatesUtils.toFlat3D;

public class Octree implements IBlockly {
    // TODO: Make octrees!
    protected static final int ROOT_SIDE_SIZE_POWER_2 = 4;
    public static final int ROOT_SIDE_SIZE = 1 << ROOT_SIDE_SIZE_POWER_2;

    private final AbstractNode root;
    private Mesh mesh;

    public Octree() {
        this.root = new InnerNode(ROOT_SIDE_SIZE);
        this.mesh = null;
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        root.setBlock(x, y, z, block);
    }

    @Override
    public void removeBlock(int x, int y, int z) {
        root.removeBlock(x, y, z);
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return root.getBlock(x, y, z);
    }

    @Override
    public Vec3i getSizes() {
        return root.getSizes();
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

    static abstract class AbstractNode implements IBlockly {

    }

    static class InnerNode extends AbstractNode {
        private final int size;
        private final AbstractNode[] childrens;
        private final Vec3i sizes;

        public InnerNode(int size) {
            this.size = size;
            this.childrens = (size == 2) ? new LeafNode[8] : new InnerNode[8];
            this.sizes = new Vec3i(size, size, size);

            for (int i = 0; i < childrens.length; i++) {
                childrens[i] = (size == 2) ? new LeafNode() : new InnerNode(size / 2);
            }
        }

        @Override
        public void setBlock(int x, int y, int z, Block block) {
            if (isPointIn(x, y, z)) {
                int half = size / 2;

                int innerX = x % half;
                int innerY = y % half;
                int innerZ = z % half;

                int arrayIndex = toFlat3D(x / half, y / half, z / half, 2, 2);

                childrens[arrayIndex].setBlock(innerX, innerY, innerZ, block);
            }
        }

        @Override
        public void removeBlock(int x, int y, int z) {
            if (isPointIn(x, y, z)) {
                childrens[toFlat3D(x, y, z, 2, 2)].removeBlock(x % 2, y % 2, z % 2);
            }
        }

        @Override
        public Block getBlock(int x, int y, int z) {
            if (isPointIn(x, y, z)) {
                int half = size / 2;

                int innerX = x % half;
                int innerY = y % half;
                int innerZ = z % half;

                int arrayIndex = toFlat3D(x / half, y / half, z / half, 2, 2);

                return childrens[arrayIndex].getBlock(innerX, innerY, innerZ);
            }

            return null;
        }

        @Override
        public Vec3i getSizes() {
            return sizes;
        }

        @Override
        public boolean isPointIn(int x, int y, int z) {
            return x >= 0 && x < size && y >= 0 && y < size && z >= 0 && z < size;
        }
    }

    static class LeafNode extends AbstractNode {
        static final Vec3i sizes = new Vec3i(1, 1, 1);
        Block block;

        @Override
        public void setBlock(int x, int y, int z, Block block) {
            if (isPointIn(x, y, z)) {
                this.block = block;
            }
        }

        @Override
        public void removeBlock(int x, int y, int z) {
            if (isPointIn(x, y, z)) {
                this.block = null;
            }
        }

        @Override
        public Block getBlock(int x, int y, int z) {
            if (isPointIn(x, y, z)) {
                return block;
            }

            return null;
        }

        @Override
        public Vec3i getSizes() {
            return sizes;
        }

        @Override
        public boolean isPointIn(int x, int y, int z) {
            return x == 0 && y == 0 && z == 0;
        }
    }
}
