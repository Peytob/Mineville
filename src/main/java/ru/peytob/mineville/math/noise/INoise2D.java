package ru.peytob.mineville.math.noise;

import ru.peytob.mineville.math.ImmutableVec2;

public interface INoise2D {
    float getPoint(ImmutableVec2 coordinates);
    float getPoint(float x, float y);
}
