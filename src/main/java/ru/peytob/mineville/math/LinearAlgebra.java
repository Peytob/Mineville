package ru.peytob.mineville.math;

public class LinearAlgebra {
    private LinearAlgebra() {

    }

    /* LENGTH */
    static public float length(ImmutableVec2 immutableVec2) {
        return immutableVec2.length();
    }

    static public float length(ImmutableVec3 immutableVec3) {
        return immutableVec3.length();
    }

    static public float length(ImmutableVec2i immutableVec2i) {
        return immutableVec2i.length();
    }

    static public float length(ImmutableVec3i immutableVec3i) {
        return immutableVec3i.length();
    }

    /* NORMALIZATION */

    static public Vec2 normalize(ImmutableVec2 immutableVec2) {
        float invLength = 1.0f / length(immutableVec2);
        return new Vec2(immutableVec2.getX() * invLength, immutableVec2.getY() * invLength);
    }

    static public Vec3 normalize(ImmutableVec3 immutableVec3) {
        float invLength = 1.0f / length(immutableVec3);
        return new Vec3(immutableVec3.getX() * invLength, immutableVec3.getY() * invLength,
                immutableVec3.getZ() * invLength);
    }

    /* MINUS (VECTORS) */

    static public Vec2 minus(ImmutableVec2 left, ImmutableVec2 right) {
        return new Vec2(left.getX() - right.getX(), left.getY() - right.getY());
    }

    static public Vec3 minus(ImmutableVec3 left, ImmutableVec3 right) {
        return new Vec3(left.getX() - right.getX(), left.getY() - right.getY(), left.getZ() - right.getZ());
    }

    static public ImmutableVec2i minus(ImmutableVec2i left, ImmutableVec2i right) {
        return new Vec2i(left.getX() - right.getX(), left.getY() - right.getY());
    }

    static public Vec3i minus(ImmutableVec3i left, ImmutableVec3i right) {
        return new Vec3i(left.getX() - right.getX(), left.getY() - right.getY(), left.getZ() - right.getZ());
    }

    /* PLUS (VECTORS) */

    static public Vec2 plus(ImmutableVec2 left, ImmutableVec2 right) {
        return new Vec2(left.getX() + right.getX(), left.getY() + right.getY());
    }

    static public Vec3 plus(ImmutableVec3 left, ImmutableVec3 right) {
        return new Vec3(left.getX() + right.getX(), left.getY() + right.getY(), left.getZ() + right.getZ());
    }

    static public Vec2i plus(ImmutableVec2i left, ImmutableVec2i right) {
        return new Vec2i(left.getX() + right.getX(), left.getY() + right.getY());
    }

    static public Vec3i plus(ImmutableVec3i left, ImmutableVec3i right) {
        return new Vec3i(left.getX() + right.getX(), left.getY() + right.getY(), left.getZ() + right.getZ());
    }

    /* VECTOR MULTIPLICATION /
}
