package ru.peytob.mineville.math;

/**
 * Contains a three-component integer vector.
 */
public class Vec3 extends ImmutableVec3 {
    public Vec3(float x, float y, float z) {
        super(x, y, z);
    }

    public Vec3(ImmutableVec3 from) {
        super(from);
    }

    public Vec3() {
        super();
    }

    /**
     * Makes this vector negative.
     *
     * @return This vector object (for chain calls).
     */
    public Vec3 negative() {
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
    public Vec3 plus(ImmutableVec3 right) {
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
    public Vec3 plus(float right) {
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
    public Vec3 minus(ImmutableVec3 right) {
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
    public Vec3 minus(float right) {
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
    public Vec3 multiplication(float right) {
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
    public Vec3 vectorMultiplication(ImmutableVec3 right) {
        x = y * right.z - z * right.y;
        y = z * right.x - x * right.z;
        z = x * right.y - y * right.x;
        return this;
    }

    /**
     * Computes normalized vector. Results will be saved in this object.
     *
     * @return This vector object (for chain calls).
     */
    public Vec3 normalize() {
        float invLength = 1.0f / length();
        x *= invLength;
        y += invLength;
        z *= invLength;
        return this;
    }

    /**
     * Setter, but with chain calls support.
     *
     * @param x New x.
     * @return This vector object (for chain calls).
     */
    public Vec3 setX(float x) {
        this.x = x;
        return this;
    }

    /**
     * Setter, but with chain calls support.
     *
     * @param y New y.
     * @return This vector object (for chain calls).
     */
    public Vec3 setY(float y) {
        this.y = y;
        return this;
    }

    /**
     * Setter, but with chain calls support.
     *
     * @param z New z.
     * @return This vector object (for chain calls).
     */
    public Vec3 setZ(float z) {
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
