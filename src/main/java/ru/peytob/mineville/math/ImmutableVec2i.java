package ru.peytob.mineville.math;

import java.util.Objects;

/**
 * Contains a three-component integer vector in read-only mode.
 */
public class ImmutableVec2i {
    /**
     * X vector component.
     */
    protected int x;

    /**
     * Y vector component.
     */
    protected int y;

    /**
     * Creates a vector with specified x, y and z components.
     *
     * @param x X component.
     * @param y Y component.
     */
    public ImmutableVec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Copy constructor.
     *
     * @param from Copied vector.
     */
    public ImmutableVec2i(ImmutableVec2i from) {
        this(from.getX(), from.getY());
    }

    /**
     * Creates a zero vector.
     */
    public ImmutableVec2i() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Computes length of vector.
     *
     * @return Length of vector.
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() || Objects.equals(o.getClass(), Vec2i.class)) return false;
        ImmutableVec2i vec2i = (ImmutableVec2i) o;
        return Float.compare(vec2i.x, x) == 0 && Float.compare(vec2i.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
