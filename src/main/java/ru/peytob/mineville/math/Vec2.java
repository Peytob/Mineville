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
    public void plus(ImmutableVec2 right) {
        x += right.getX();
        y += right.getY();
    }

    /**
     * Adds given scalar to this vector. Results will be saved in this object.
     *
     * @param right Scalar.
     */
    public void plus(float right) {
        x += right;
        y += right;
    }

    /**
     * Subtracts given vector right from this vector. Results will be saved in this object.
     *
     * @param right Right vector.
     */
    public void minus(ImmutableVec2 right) {
        x -= right.getX();
        y -= right.getY();
    }

    /**
     * Subtracts given scalar to this vector. Results will be saved in this object.
     *
     * @param right Scalar.
     */
    public void minus(float right) {
        x -= right;
        y -= right;
    }

    /**
     * Computes multiplication of this vector and scalar. Results will be saved in this object.
     *
     * @param right Right scalar.
     */
    public void multiplication(float right) {
        x *= right;
        y *= right;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
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
