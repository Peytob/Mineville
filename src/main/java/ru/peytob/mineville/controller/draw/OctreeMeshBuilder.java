package ru.peytob.mineville.controller.draw;

import ru.peytob.mineville.math.Vec3i;
import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.graphic.Mesh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class OctreeMeshBuilder {
    private final ArrayList<Float> buffer;

    public OctreeMeshBuilder() {
        this.buffer = new ArrayList<>();
    }

    public void addTop(Block block, int x, int y, int z) {
        Float[] target = block.getModel().getTopFace();
        Float[] top = Arrays.copyOf(target, target.length);

        for (int i = 0; i < top.length; i += 8) {
            top[i] += x;
            top[i + 1] += y;
            top[i + 2] += z;
        }

        Collections.addAll(buffer, top);
    }

    public void addBottom(Block block, int x, int y, int z) {
        Float[] target = block.getModel().getBottomFace();
        Float[] top = Arrays.copyOf(target, target.length);

        for (int i = 0; i < top.length; i += 8) {
            top[i] += x;
            top[i + 1] += y;
            top[i + 2] += z;
        }

        Collections.addAll(buffer, top);
    }

    public void addWest(Block block, int x, int y, int z) {
        Float[] target = block.getModel().getWestFace();
        Float[] top = Arrays.copyOf(target, target.length);

        for (int i = 0; i < top.length; i += 8) {
            top[i] += x;
            top[i + 1] += y;
            top[i + 2] += z;
        }

        Collections.addAll(buffer, top);
    }

    public void addEast(Block block, int x, int y, int z) {
        Float[] target = block.getModel().getEastFace();
        Float[] top = Arrays.copyOf(target, target.length);

        for (int i = 0; i < top.length; i += 8) {
            top[i] += x;
            top[i + 1] += y;
            top[i + 2] += z;
        }

        Collections.addAll(buffer, top);
    }

    public void addSouth(Block block, int x, int y, int z) {
        Float[] target = block.getModel().getSouthFace();
        Float[] top = Arrays.copyOf(target, target.length);

        for (int i = 0; i < top.length; i += 8) {
            top[i] += x;
            top[i + 1] += y;
            top[i + 2] += z;
        }

        Collections.addAll(buffer, top);
    }

    public void addNorth(Block block, int x, int y, int z) {
        Float[] target = block.getModel().getNorthFace();
        Float[] top = Arrays.copyOf(target, target.length);

        for (int i = 0; i < top.length; i += 8) {
            top[i] += x;
            top[i + 1] += y;
            top[i + 2] += z;
        }

        Collections.addAll(buffer, top);
    }

    public Mesh buildMesh() {
        float[] vertices = new float[buffer.size()];
        int i = 0;

        for (Float f : buffer) {
            vertices[i] = f;
            i++;
        }

        return new Mesh(vertices);
    }
}
