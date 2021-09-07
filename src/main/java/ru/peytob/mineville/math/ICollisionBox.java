package ru.peytob.mineville.math;

public interface ICollisionBox {
    void move(float dx, float dy, float dz);
    void move(ImmutableVec3 delta);
    boolean checkCollision(ICollisionBox collisionBox);
    boolean isPointIn(ImmutableVec3 vec3);
    void setCenter(ImmutableVec3 newCenter);
}
