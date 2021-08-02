package ru.peytob.mineville.math;

/**
 * Contains a three-component integer vector.
 */
public final class Vec2i extends ImmutableVec2i {
    public Vec2i(int x, int y) {
        super(x, y);
    }

    public Vec2i(ImmutableVec2i from) {
        super(from);
    }

    public Vec2i() {
        super();
    }

    /**
     * Makes this vector negative.
     *
     * @return This vector object (for chain calls).
     */
    public Vec2i negative() {
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
    public Vec2i plus(ImmutableVec2i right) {
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
    public Vec2i plus(int right) {
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
    public Vec2i minus(ImmutableVec2i right) {
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
    public Vec2i minus(int right) {
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
    public Vec2i multiplication(int right) {
        x *= right;
        y *= right;
        return this;
    }

    /**
     * Setter, but with chain calls support.
     *
     * @param x New x.
     * @return This vector object (for chain calls).
     */
    public Vec2i setX(int x) {
        this.x = x;
        return this;
    }

    /**
     * Setter, but with chain calls support.
     *
     * @param y New y.
     * @return This vector object (for chain calls).
     */
    public Vec2i setY(int y) {
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
