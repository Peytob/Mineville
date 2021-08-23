package ru.peytob.mineville.math;

public class CollisionUtils {
    static public boolean isCollision(AABB left, AABB right) {
        return
                Math.abs(left.getCenter().getX() - right.getCenter().getX()) < left.getRadius().getX() + right.getRadius().getX() &&
                Math.abs(left.getCenter().getY() - right.getCenter().getY()) < left.getRadius().getY() + right.getRadius().getY() &&
                Math.abs(left.getCenter().getZ() - right.getCenter().getZ()) < left.getRadius().getZ() + right.getRadius().getZ();
    }

    static public boolean isPointIn(AABB aabb, ImmutableVec3 vec3) {
        return
                Math.abs(aabb.getCenter().getX() - vec3.getX()) < aabb.getRadius().getX() &&
                Math.abs(aabb.getCenter().getY() - vec3.getY()) < aabb.getRadius().getY() &&
                Math.abs(aabb.getCenter().getZ() - vec3.getZ()) < aabb.getRadius().getZ();
    }
}
