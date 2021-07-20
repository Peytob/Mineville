package ru.peytob.mineville.controller.draw;

import ru.peytob.mineville.model.game.object.Block;
import ru.peytob.mineville.model.graphic.Mesh;
import ru.peytob.mineville.model.graphic.TextureTile;
import ru.peytob.mineville.model.graphic.block.BlockFace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.peytob.mineville.model.graphic.block.BlockFacePoints.*;

public class OctreeMeshBuilder {
    private final List<Float> buffer;

    public OctreeMeshBuilder() {
        this.buffer = new ArrayList<>();
    }

    private void addFace(BlockFace face, TextureTile textureTile, int x, int y, int z) {
        Float[] points = face.getPoints().getData();

        Collections.addAll(buffer, points);
        for (int i = buffer.size() - points.length; i < buffer.size(); i += VERTEX_SIZE_FLOATS) {
            buffer.set(i + POSITION_X, buffer.get(i + POSITION_X) + x);
            buffer.set(i + POSITION_Y, buffer.get(i + POSITION_Y) + y);
            buffer.set(i + POSITION_Z, buffer.get(i + POSITION_Z) + z);

            buffer.set(i + TEXTURE_X, textureTile.getX() + textureTile.getAbsoluteWidth() * buffer.get(i + TEXTURE_X));
            buffer.set(i + TEXTURE_Y, textureTile.getY() + textureTile.getAbsoluteHeight() * buffer.get(i + TEXTURE_Y));
        }
    }

    public void addTop(Block block, int x, int y, int z) {
        this.addFace(block.getModel().getTopFace(), block.getTexture().getTopFace(), x, y, z);
    }

    public void addBottom(Block block, int x, int y, int z) {
        this.addFace(block.getModel().getBottomFace(), block.getTexture().getBottomFace(), x, y, z);
    }

    public void addWest(Block block, int x, int y, int z) {
        this.addFace(block.getModel().getWestFace(), block.getTexture().getWestFace(), x, y, z);
    }

    public void addEast(Block block, int x, int y, int z) {
        this.addFace(block.getModel().getEastFace(), block.getTexture().getEastFace(), x, y, z);
    }

    public void addSouth(Block block, int x, int y, int z) {
        this.addFace(block.getModel().getSouthFace(), block.getTexture().getSouthFace(), x, y, z);
    }

    public void addNorth(Block block, int x, int y, int z) {
        this.addFace(block.getModel().getNorthFace(), block.getTexture().getNorthFace(), x, y, z);
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
