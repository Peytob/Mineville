package ru.peytob.mineville.math;

import java.util.Objects;

/**
 * Contains a three-component float vector in read-only mode.
 */
public class ImmutableVec3 {
    /**
     * X vector component.
     */
    protected float x;

    /**
     * Y vector component.
     */
    protected float y;

    /**
     * Z vector component.
     */
    protected float z;

    /**
     * Creates a vector with specified x, y and z components.
     *
     * @param x X component.
     * @param y Y component.
     * @param z Z component.
     */
    public ImmutableVec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Copy constructor.
     *
     * @param from Copied vector.
     */
    public ImmutableVec3(ImmutableVec3 from) {
        this(from.getX(), from.getY(), from.getZ());
    }

    /**
     * Creates a zero vector.
     */
    public ImmutableVec3() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    /**
     * Computes length of vector.
     *
     * @return Length of vector.
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    /**
     * Creates new instance of Vec3i from this vector. Equals to call new Vec3i((int) this.x, (int) this.y,
     * (int) this.z).
     *
     * @return New instance of Vec3i from this vector.
     */
    public Vec3i toVec3i() {
        return new Vec3i((int) x, (int) y, (int) z);
    }

    /**
     * Computes scalar multiplication of this vector and other vector.
     *
     * @param right Right vector.
     * @return Scalar multiplication of vectors.
     */
    public float scalarMultiplication(ImmutableVec3 right) {
        return x * right.x + y * right.y + z * right.z;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableVec3 immutableVec3 = (ImmutableVec3) o;
        return Float.compare(x, immutableVec3.getX()) == 0 &&
                Float.compare(y, immutableVec3.getY()) == 0 &&
                Float.compare(z, immutableVec3.getZ()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
