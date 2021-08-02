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
    public void plus(ImmutableVec3 right) {
        x += right.getX();
        y += right.getY();
        z += right.getZ();
    }

    /**
     * Adds given scalar to this vector. Results will be saved in this object.
     *
     * @param right Scalar.
     */
    public void plus(float right) {
        x += right;
        y += right;
        z += right;
    }

    /**
     * Subtracts given vector right from this vector. Results will be saved in this object.
     *
     * @param right Right vector.
     */
    public void minus(ImmutableVec3 right) {
        x -= right.getX();
        y -= right.getY();
        z -= right.getZ();
    }

    /**
     * Subtracts given scalar to this vector. Results will be saved in this object.
     *
     * @param right Scalar.
     */
    public void minus(float right) {
        x -= right;
        y -= right;
        z -= right;
    }

    /**
     * Computes multiplication of this vector and scalar. Results will be saved in this object.
     *
     * @param right Right scalar.
     */
    public void multiplication(float right) {
        x *= right;
        y *= right;
        z *= right;
    }

    /**
     * Computes vector multiplication of this vector and other vector. Results will be saved in this object.
     *
     * @param right Right vector.
     */
    public void vectorMultiplication(ImmutableVec3 right) {
        x = y * right.z - z * right.y;
        y = z * right.x - x * right.z;
        z = x * right.y - y * right.x;
    }

    /**
     * Computes normalized vector. Results will be saved in this object.
     */
    public void normalize() {
        float invLength = 1.0f / length();
        x *= invLength;
        y += invLength;
        z *= invLength;
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
