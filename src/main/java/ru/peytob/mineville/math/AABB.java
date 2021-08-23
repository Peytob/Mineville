package ru.peytob.mineville.math;

public class AABB {
    private final Vec3 center;
    private final Vec3 radius;

    public AABB(Vec3 center, Vec3 radius) {
        this.center = center;
        this.radius = radius;
    }

    public ImmutableVec3 getCenter() {
        return center;
    }

    public ImmutableVec3 getRadius() {
        return radius;
    }

    public void move(float dx, float dy, float dz) {
        move(new ImmutableVec3(dx, dy, dz));
    }

    public void move(ImmutableVec3 delta) {
        center.plus(delta);
    }
}
