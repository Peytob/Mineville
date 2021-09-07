package ru.peytob.mineville.math.fade;

public class PerlinFade implements IFade {
    @Override
    public float fade(float t) {
        return ((6 * t - 15) * t + 10) * t * t * t;
    }
}
