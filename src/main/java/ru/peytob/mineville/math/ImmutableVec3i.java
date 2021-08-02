package ru.peytob.mineville.math;

import java.util.Objects;

/**
 * Contains a three-component integer vector in read-only mode.
 */
public class ImmutableVec3i {
    /**
     * X vector component.
     */
    protected int x;

    /**
     * Y vector component.
     */
    protected int y;

    /**
     * Z vector component.
     */
    protected int z;

    /**
     * Creates a vector with specified x, y and z components.
     *
     * @param x X component.
     * @param y Y component.
     * @param z Z component.
     */
    public ImmutableVec3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Copy constructor.
     *
     * @param from Copied vector.
     */
    public ImmutableVec3i(ImmutableVec3i from) {
        this(from.getX(), from.getY(), from.getZ());
    }

    /**
     * Creates a zero vector.
     */
    public ImmutableVec3i() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /**
     * Computes length of vector.
     *
     * @return Length of vector.
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    /**
     * Creates new instance of Vec3 from this vector. Equals to call new Vec3(this.x, this.y, this.z).
     *
     * @return New instance of Vec3 from this vector.
     */
    public Vec3 toVec3() {
        return new Vec3(x, y, z);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableVec3i immutableVec3 = (ImmutableVec3i) o;
        return x == immutableVec3.getX() && y == immutableVec3.getY() && z == immutableVec3.getZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
