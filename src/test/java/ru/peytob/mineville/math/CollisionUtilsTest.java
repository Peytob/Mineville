package ru.peytob.mineville.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionUtilsTest {

    @Test
    void collisionAABtoBAABB() {
        // The first is located at a distance from the second and third. Third is located inside second.
        // Fourth contains part of all other cubes, but not all of it.

        AABB first = new AABB(new Vec3(5.12f, 3.41f, 5), new Vec3(2, 1, 3));
        AABB second = new AABB(new Vec3(0f, 0f, 0f), new Vec3(1, 1, 1));
        AABB third = new AABB(new Vec3(0.25f, 0f, 0f), new Vec3(0.5f, 0.5f, 1));
        AABB fourth = new AABB(new Vec3(3f, 4f, 2f), new Vec3(2.5f, 5f, 1.5f));

        assertTrue(CollisionUtils.isCollision(first, first));
        assertFalse(CollisionUtils.isCollision(first, second));
        assertFalse(CollisionUtils.isCollision(first, third));
        assertTrue(CollisionUtils.isCollision(first, fourth));
        assertTrue(CollisionUtils.isCollision(second, third));
        assertTrue(CollisionUtils.isCollision(second, fourth));
        assertTrue(CollisionUtils.isCollision(third, fourth));
    }

    @Test
    void isPointIn() {
        AABB cube = new AABB(new Vec3(), new Vec3(0.5f, 0.5f, 0.5f));

        assertTrue(CollisionUtils.isPointIn(cube, new Vec3(0, 0, 0)));

        assertTrue(CollisionUtils.isPointIn(cube, new Vec3(0.25f, 0.25f, 0.25f)));
        assertTrue(CollisionUtils.isPointIn(cube, new Vec3(0.25f, 0.25f, -0.25f)));
        assertTrue(CollisionUtils.isPointIn(cube, new Vec3(0.25f, -0.25f, 0.25f)));
        assertTrue(CollisionUtils.isPointIn(cube, new Vec3(0.25f, -0.25f, -0.25f)));
        assertTrue(CollisionUtils.isPointIn(cube, new Vec3(-0.25f, 0.25f, 0.25f)));
        assertTrue(CollisionUtils.isPointIn(cube, new Vec3(-0.25f, 0.25f, -0.25f)));
        assertTrue(CollisionUtils.isPointIn(cube, new Vec3(-0.25f, -0.25f, 0.25f)));
        assertTrue(CollisionUtils.isPointIn(cube, new Vec3(-0.25f, -0.25f, -0.25f)));

        assertFalse(CollisionUtils.isPointIn(cube, new Vec3(1.25f, 1.25f, 1.25f)));
        assertFalse(CollisionUtils.isPointIn(cube, new Vec3(1.25f, 1.25f, -0.25f)));
        assertFalse(CollisionUtils.isPointIn(cube, new Vec3(0.25f, -1.25f, 0.25f)));
        assertFalse(CollisionUtils.isPointIn(cube, new Vec3(0.25f, -1.25f, -0.25f)));
        assertFalse(CollisionUtils.isPointIn(cube, new Vec3(-0.25f, 0.25f, 1.25f)));
        assertFalse(CollisionUtils.isPointIn(cube, new Vec3(-1.25f, 0.25f, -1.25f)));
        assertFalse(CollisionUtils.isPointIn(cube, new Vec3(-0.25f, -1.25f, 0.25f)));
        assertFalse(CollisionUtils.isPointIn(cube, new Vec3(-1.25f, -1.25f, -1.25f)));
    }
}