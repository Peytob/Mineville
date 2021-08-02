package ru.peytob.mineville.math;

/**
 * Contains a two-component float vector in read-only mode.
 */
public class Vec2 extends ImmutableVec2 {
    public Vec2(float x, float y) {
        super(x, y);
    }

    public Vec2(ImmutableVec2 from) {
        super(from);
    }

    public Vec2() {
        super();
    }

    /**
     * Makes this vector negative.
     *
     * @return This vector object (for chain calls).
     */
    public Vec2 negative() {
        x = -x;
        y = -y;
        return this;
    }

    /**
     * Adds given vector right to this vector. Results will be saved in this object.
     *
     * @param right Right vector.
     * @return This vector object (for chain calls).
     */
    public Vec2 plus(ImmutableVec2 right) {
        x += right.getX();
        y += right.getY();
        return this;
    }

    /**
     * Adds given scalar to this vector. Results will be saved in this object.
     *
     * @param right Scalar.
     * @return This vector object (for chain calls).
     */
    public Vec2 plus(float right) {
        x += right;
        y += right;
        return this;
    }

    /**
     * Subtracts given vector right from this vector. Results will be saved in this object.
     *
     * @param right Right vector.
     * @return This vector object (for chain calls).
     */
    public Vec2 minus(ImmutableVec2 right) {
        x -= right.getX();
        y -= right.getY();
        return this;
    }

    /**
     * Subtracts given scalar to this vector. Results will be saved in this object.
     *
     * @param right Scalar.
     * @return This vector object (for chain calls).
     */
    public Vec2 minus(float right) {
        x -= right;
        y -= right;
        return this;
    }

    /**
     * Computes multiplication of this vector and scalar. Results will be saved in this object.
     *
     * @param right Right scalar.
     * @return This vector object (for chain calls).
     */
    public Vec2 multiplication(float right) {
        x *= right;
        y *= right;
        return this;
    }

    /**
     * Computes normalized vector. Results will be saved in this object.
     *
     * @return This vector object (for chain calls).
     */
    public Vec2 normalize() {
        float invLength = 1.0f / length();
        x *= invLength;
        y += invLength;
        return this;
    }

    /**
     * Setter, but with chain calls support.
     *
     * @param x New x.
     * @return This vector object (for chain calls).
     */
    public Vec2 setX(float x) {
        this.x = x;
        return this;
    }

    /**
     * Setter, but with chain calls support.
     *
     * @param y New y.
     * @return This vector object (for chain calls).
     */
    public Vec2 setY(float y) {
        this.y = y;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
