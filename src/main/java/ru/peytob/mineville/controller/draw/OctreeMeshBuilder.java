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

            top[i + 6] = block.getModel().getTexture().getTopFace().getX() + top[i + 6] * block.getModel().getTexture().getTopFace().getAbsoluteWidth();
            top[i + 7] = block.getModel().getTexture().getTopFace().getY() + top[i + 7] * block.getModel().getTexture().getTopFace().getAbsoluteHeight();
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

            top[i + 6] = block.getModel().getTexture().getBottomFace().getX() + top[i + 6] * block.getModel().getTexture().getBottomFace().getAbsoluteWidth();
            top[i + 7] = block.getModel().getTexture().getBottomFace().getY() + top[i + 7] * block.getModel().getTexture().getBottomFace().getAbsoluteHeight();
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

            top[i + 6] = block.getModel().getTexture().getWestFace().getX() + top[i + 6] * block.getModel().getTexture().getWestFace().getAbsoluteWidth();
            top[i + 7] = block.getModel().getTexture().getWestFace().getY() + top[i + 7] * block.getModel().getTexture().getWestFace().getAbsoluteHeight();
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

            top[i + 6] = block.getModel().getTexture().getEastFace().getX() + top[i + 6] * block.getModel().getTexture().getEastFace().getAbsoluteWidth();
            top[i + 7] = block.getModel().getTexture().getEastFace().getY() + top[i + 7] * block.getModel().getTexture().getEastFace().getAbsoluteHeight();
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

            top[i + 6] = block.getModel().getTexture().getSouthFace().getX() + top[i + 6] * block.getModel().getTexture().getSouthFace().getAbsoluteWidth();
            top[i + 7] = block.getModel().getTexture().getSouthFace().getY() + top[i + 7] * block.getModel().getTexture().getSouthFace().getAbsoluteHeight();
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

            top[i + 6] = block.getModel().getTexture().getNorthFace().getX() + top[i + 6] * block.getModel().getTexture().getNorthFace().getAbsoluteWidth();
            top[i + 7] = block.getModel().getTexture().getNorthFace().getY() + top[i + 7] * block.getModel().getTexture().getNorthFace().getAbsoluteHeight();
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
