package ru.peytob.mineville.math;

import java.util.Objects;

/**
 * Contains a three-component integer vector in read-only mode.
 */
public class ImmutableVec2 {
    /**
     * X vector component.
     */
    protected float x;

    /**
     * Y vector component.
     */
    protected float y;

    /**
     * Creates a vector with specified x, y and z components.
     *
     * @param x X component.
     * @param y Y component.
     */
    public ImmutableVec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Copy constructor.
     *
     * @param from Copied vector.
     */
    public ImmutableVec2(ImmutableVec2 from) {
        this(from.getX(), from.getY());
    }

    /**
     * Creates a zero vector.
     */
    public ImmutableVec2() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    /**
     * Computes length of vector.
     *
     * @return Length of vector.
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableVec2 vec2 = (ImmutableVec2) o;
        return Float.compare(vec2.x, x) == 0 && Float.compare(vec2.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
