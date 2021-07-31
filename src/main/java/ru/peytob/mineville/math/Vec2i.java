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
     */
    public void negative() {
        x = -x;
        y = -y;
    }

    /**
     * Adds given vector right to this vector. Results will be saved in this object.
     *
     * @param right Right vector.
     */
    public void plus(ImmutableVec2i right) {
        x += right.getX();
        y += right.getY();
    }

    /**
     * Adds given scalar to this vector. Results will be saved in this object.
     *
     * @param right Scalar.
     */
    public void plus(int right) {
        x += right;
        y += right;
    }

    /**
     * Subtracts given vector right from this vector. Results will be saved in this object.
     *
     * @param right Right vector.
     */
    public void minus(ImmutableVec2i right) {
        x -= right.getX();
        y -= right.getY();
    }

    /**
     * Subtracts given scalar to this vector. Results will be saved in this object.
     *
     * @param right Scalar.
     */
    public void minus(int right) {
        x -= right;
        y -= right;
    }

    /**
     * Computes multiplication of this vector and scalar. Results will be saved in this object.
     *
     * @param right Right scalar.
     */
    public void multiplication(int right) {
        x *= right;
        y *= right;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
