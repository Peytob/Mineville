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
     *
     * @return This vector object (for chain calls).
     */
    public Vec3i negative() {
        x = -x;
        y = -y;
        z = -z;
        return this;
    }

    /**
     * Adds given vector right to this vector. Results will be saved in this object.
     *
     * @param right Right vector.
     * @return This vector object (for chain calls).
     */
    public Vec3i plus(ImmutableVec3i right) {
        x += right.getX();
        y += right.getY();
        z += right.getZ();
        return this;
    }

    /**
     * Adds given scalar to this vector. Results will be saved in this object.
     *
     * @param right Scalar.
     * @return This vector object (for chain calls).
     */
    public Vec3i plus(int right) {
        x += right;
        y += right;
        z += right;
        return this;
    }

    /**
     * Subtracts given vector right from this vector. Results will be saved in this object.
     *
     * @param right Right vector.
     * @return This vector object (for chain calls).
     */
    public Vec3i minus(ImmutableVec3i right) {
        x -= right.getX();
        y -= right.getY();
        z -= right.getZ();
        return this;
    }

    /**
     * Subtracts given scalar to this vector. Results will be saved in this object.
     *
     * @param right Scalar.
     * @return This vector object (for chain calls).
     */
    public Vec3i minus(int right) {
        x -= right;
        y -= right;
        z -= right;
        return this;
    }

    /**
     * Computes multiplication of this vector and scalar. Results will be saved in this object.
     *
     * @param right Right scalar.
     * @return This vector object (for chain calls).
     */
    public Vec3i multiplication(int right) {
        x *= right;
        y *= right;
        z *= right;
        return this;
    }

    /**
     * Computes vector multiplication of this vector and other vector. Results will be saved in this object.
     *
     * @param right Right vector.
     * @return This vector object (for chain calls).
     */
    public Vec3i vectorMultiplication(ImmutableVec3i right) {
        x = y * right.z - z * right.y;
        y = z * right.x - x * right.z;
        z = x * right.y - y * right.x;
        return this;
    }

    /**
     * Setter, but with chain calls support.
     *
     * @param x New x.
     * @return This vector object (for chain calls).
     */
    public Vec3i setX(int x) {
        this.x = x;
        return this;
    }

    /**
     * Setter, but with chain calls support.
     *
     * @param y New y.
     * @return This vector object (for chain calls).
     */
    public Vec3i setY(int y) {
        this.y = y;
        return this;
    }

    /**
     * Setter, but with chain calls support.
     *
     * @param z New z.
     * @return This vector object (for chain calls).
     */
    public Vec3i setZ(int z) {
        this.z = z;
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