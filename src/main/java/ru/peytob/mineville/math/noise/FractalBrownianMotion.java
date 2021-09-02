package ru.peytob.mineville.math.noise;

import ru.peytob.mineville.math.ImmutableVec2;

public class FractalBrownianMotion implements INoise2D {
    private final float amplitude;
    private final INoise2D noise;
    private final int octaves;

    public FractalBrownianMotion(INoise2D noise, int octaves, float amplitude) {
        this.noise = noise;
        this.octaves = octaves;
        this.amplitude = amplitude;
    }

    public int getOctaves() {
        return octaves;
    }

    @Override
    public float getPoint(ImmutableVec2 coordinates) {
        return getPoint(coordinates.getX(), coordinates.getY());
    }

    @Override
    public float getPoint(float x, float y) {
        float value = 0.0f;
        float localAmplitude = amplitude;

        for (int i = 0; i < octaves; i++) {
            value += localAmplitude * noise.getPoint(x, y);
            x *= 2.0f;
            y *= 2.0f;
            localAmplitude *= 0.5f;
        }

        return value;
    }
}
