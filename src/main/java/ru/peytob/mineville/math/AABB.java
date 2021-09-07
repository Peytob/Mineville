package ru.peytob.mineville.math;

public class AABB implements ICollisionBox {
    private final Vec3 center;
    private final Vec3 radius;

    public AABB(Vec3 radius, Vec3 center) {
        this.center = center;
        this.radius = radius;
    }

    public AABB(Vec3 radius) {
        this(radius, new Vec3());
    }

    public ImmutableVec3 getCenter() {
        return center;
    }

    public ImmutableVec3 getRadius() {
        return radius;
    }

    @Override
    public void move(float dx, float dy, float dz) {
        move(new ImmutableVec3(dx, dy, dz));
    }

    @Override
    public void move(ImmutableVec3 delta) {
        center.plus(delta);
    }

    @Override
    public void setCenter(ImmutableVec3 newCenter) {
        center.setX(newCenter.getX()).setY(newCenter.getY()).setZ(newCenter.getZ());
    }

    @Override
    public boolean checkCollision(ICollisionBox collisionBox) {
        if (collisionBox instanceof AABB) {
            return CollisionUtils.isCollision(this, (AABB) collisionBox);
        }

        else {
            return false;
        }
    }

    @Override
    public boolean isPointIn(ImmutableVec3 point) {
        return CollisionUtils.isPointIn(this, point);
    }
}
