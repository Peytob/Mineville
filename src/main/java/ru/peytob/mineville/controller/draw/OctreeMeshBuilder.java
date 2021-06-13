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

    public void addTop(Block _block, Vec3i _position) {
        Float[] target = _block.getModel().getTopSide();
        Float[] top = Arrays.copyOf(target, target.length);

        for (int i = 0; i < top.length; i += 8) {
            top[i] += _position.x;
            top[i + 1] += _position.y;
            top[i + 2] += _position.z;
        }

        Collections.addAll(buffer, top);
    }

    public void addBottom(Block _block, Vec3i _position) {
        Float[] target = _block.getModel().getBottomSide();
        Float[] top = Arrays.copyOf(target, target.length);

        for (int i = 0; i < top.length; i += 8) {
            top[i] += _position.x;
            top[i + 1] += _position.y;
            top[i + 2] += _position.z;
        }

        Collections.addAll(buffer, top);
    }

    public void addWest(Block _block, Vec3i _position) {
        Float[] target = _block.getModel().getWestSide();
        Float[] top = Arrays.copyOf(target, target.length);

        for (int i = 0; i < top.length; i += 8) {
            top[i] += _position.x;
            top[i + 1] += _position.y;
            top[i + 2] += _position.z;
        }

        Collections.addAll(buffer, top);
    }

    public void addEast(Block _block, Vec3i _position) {
        Float[] target = _block.getModel().getEastSide();
        Float[] top = Arrays.copyOf(target, target.length);

        for (int i = 0; i < top.length; i += 8) {
            top[i] += _position.x;
            top[i + 1] += _position.y;
            top[i + 2] += _position.z;
        }

        Collections.addAll(buffer, top);
    }

    public void addSouth(Block _block, Vec3i _position) {
        Float[] target = _block.getModel().getSouthSide();
        Float[] top = Arrays.copyOf(target, target.length);

        for (int i = 0; i < top.length; i += 8) {
            top[i] += _position.x;
            top[i + 1] += _position.y;
            top[i + 2] += _position.z;
        }

        Collections.addAll(buffer, top);
    }

    public void addNorth(Block _block, Vec3i _position) {
        Float[] target = _block.getModel().getNorthSide();
        Float[] top = Arrays.copyOf(target, target.length);

        for (int i = 0; i < top.length; i += 8) {
            top[i] += _position.x;
            top[i + 1] += _position.y;
            top[i + 2] += _position.z;
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
