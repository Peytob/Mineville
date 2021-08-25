package ru.peytob.mineville.math;

public class CollisionUtils {
    static public boolean isCollision(ICollisionBox first, ICollisionBox second) {
        return first.checkCollision(second);
    }

    static boolean isCollision(AABB first, AABB second) {
        return
                Math.abs(first.getCenter().getX() - second.getCenter().getX()) < first.getRadius().getX() + second.getRadius().getX() &&
                Math.abs(first.getCenter().getY() - second.getCenter().getY()) < first.getRadius().getY() + second.getRadius().getY() &&
                Math.abs(first.getCenter().getZ() - second.getCenter().getZ()) < first.getRadius().getZ() + second.getRadius().getZ();
    }

    static public boolean isPointIn(AABB aabb, ImmutableVec3 point) {
        return
                Math.abs(aabb.getCenter().getX() - point.getX()) < aabb.getRadius().getX() &&
                Math.abs(aabb.getCenter().getY() - point.getY()) < aabb.getRadius().getY() &&
                Math.abs(aabb.getCenter().getZ() - point.getZ()) < aabb.getRadius().getZ();
    }
}
