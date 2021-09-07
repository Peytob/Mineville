package ru.peytob.mineville.math.noise;

import ru.peytob.mineville.math.ImmutableVec2;
import ru.peytob.mineville.math.fade.IFade;
import ru.peytob.mineville.math.fade.PerlinFade;
import ru.peytob.mineville.math.interpolation.IInterpolation;
import ru.peytob.mineville.math.interpolation.LinearInterpolation;

import java.util.*;

public class Perlin2dNoise implements INoise2D {
    static protected ImmutableVec2[] gradientVectors = new ImmutableVec2[]{
            new ImmutableVec2(1, 1),
            new ImmutableVec2(-1, 1),
            new ImmutableVec2(-1, -1),
            new ImmutableVec2(1, -1)
    };

    private final IInterpolation interpolation;
    private final IFade fade;

    private final List<Integer> table;

    public Perlin2dNoise() {
        this(new Random());
    }

    public Perlin2dNoise(int seed) {
        this(new Random(seed));
    }

    protected Perlin2dNoise(Random rnd) {
        this.interpolation = new LinearInterpolation();
        this.fade = new PerlinFade();

        this.table = new ArrayList<>(512);

        for (int i = 0; i < 512; i++) {
            this.table.add(rnd.nextInt(256));
        }
    }

    protected ImmutableVec2 getGradientVector(int x, int y) {
        int h = (table.get(table.get(x) + y)) & 3;
        return gradientVectors[h];
    }

    @Override
    public float getPoint(ImmutableVec2 coordinates) {
        return getPoint(coordinates.getX(), coordinates.getY());
    }

    @Override
    public float getPoint(float x, float y) {
        int X = (int) Math.floor(x) & 255;
        int Y = (int) Math.floor(y) & 255;

        float xf = x - (float) Math.floor(x);
        float yf = y - (float) Math.floor(y);

        ImmutableVec2 topRight = new ImmutableVec2(xf - 1.0f, yf - 1.0f);
        ImmutableVec2 topLeft = new ImmutableVec2(xf, yf - 1.0f);
        ImmutableVec2 bottomRight = new ImmutableVec2(xf - 1.0f, yf);
        ImmutableVec2 bottomLeft = new ImmutableVec2(xf, yf);

        float dotTopRight = topRight.scalarMultiplication(getGradientVector(X + 1, Y + 1));
        float dotTopLeft = topLeft.scalarMultiplication(getGradientVector(X, Y + 1));
        float dotBottomRight = bottomRight.scalarMultiplication(getGradientVector(X + 1, Y));
        float dotBottomLeft = bottomLeft.scalarMultiplication(getGradientVector(X, Y));

        float u = fade.fade(xf);
        float v = fade.fade(yf);

        return interpolation.interpolate(
                u,
                interpolation.interpolate(v, dotBottomLeft, dotTopLeft),
                interpolation.interpolate(v, dotBottomRight, dotTopRight)
        );
    }
}
