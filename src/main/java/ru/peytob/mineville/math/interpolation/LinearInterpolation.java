package ru.peytob.mineville.math.interpolation;

public class LinearInterpolation implements IInterpolation {
    @Override
    public float interpolate(float t, float a1, float a2) {
        return a1 + t*(a2-a1);
    }
}
