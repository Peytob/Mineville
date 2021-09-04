package ru.peytob.mineville.math.noise;

import ru.peytob.mineville.math.ImmutableVec2;

public class NoiseFacade implements INoise2D {
    private final INoise2D noise;
    private final ImmutableVec2 offset;
    private final float scale;

    public NoiseFacade(INoise2D noise, float scale, ImmutableVec2 offset) {
        this.noise = noise;
        this.offset = offset;
        this.scale = scale;
    }

    public NoiseFacade(INoise2D noise, float scale) {
        this(noise, scale, new ImmutableVec2(1000, 1000));
    }

    public INoise2D getNoise() {
        return noise;
    }

    @Override
    public float getPoint(ImmutableVec2 coordinates) {
        return getPoint(coordinates.getX(), coordinates.getX());
    }

    @Override
    public float getPoint(float x, float y) {
        return noise.getPoint(offset.getX() + x * scale, offset.getY() + y * scale);
    }

    public float getPointNormalized(ImmutableVec2 coordinates) {
        return getPointNormalized(coordinates.getX(), coordinates.getY());
    }

    public float getPointNormalized(float x, float y) {
        float point = getPoint(x, y);
        return (point + 1.0f) / 2.0f;
    }

    public float getScale() {
        return scale;
    }
}
