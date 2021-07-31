package ru.peytob.mineville.math;

/**
 * Contains a three-component integer vector.
 */
public final class Vec3i extends ImmutableVec3i {
    public Vec3i(int x, int y, int z) {
        super(x, y, z);
    }

    public Vec3i(ImmutableVec3i from) {
        super(from);
    }

    public Vec3i() {
        super();
    }

    /**
     * Makes this vector negative.
     */
    public void negative() {
        x = -x;
        y = -y;
        z = -z;
    }

    /**
     * Adds given vector right to this vector. Results will be saved in this object.
     *
     * @param right Right vector.
     */
    public void plus(ImmutableVec3i right) {
        x += right.getX();
        y += right.getY();
        z += right.getZ();
    }

    /**
     * Adds given scalar to this vector. Results will be saved in this object.
     *
     * @param right Scalar.
     */
    public void plus(int right) {
        x += right;
        y += right;
        z += right;
    }

    /**
     * Subtracts given vector right from this vector. Results will be saved in this object.
     *
     * @param right Right vector.
     */
    public void minus(ImmutableVec3i right) {
        x -= right.getX();
        y -= right.getY();
        z -= right.getZ();
    }

    /**
     * Subtracts given scalar to this vector. Results will be saved in this object.
     *
     * @param right Scalar.
     */
    public void minus(int right) {
        x -= right;
        y -= right;
        z -= right;
    }

    /**
     * Computes multiplication of this vector and scalar. Results will be saved in this object.
     *
     * @param right Right scalar.
     */
    public void multiplication(int right) {
        x *= right;
        y *= right;
        z *= right;
    }

    /**
     * Computes vector multiplication of this vector and other vector. Results will be saved in this object.
     *
     * @param right Right vector.
     */
    public void vectorMultiplication(ImmutableVec3i right) {
        x = y * right.z - z * right.y;
        y = z * right.x - x * right.z;
        z = x * right.y - y * right.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
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