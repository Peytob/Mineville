package ru.peytob.mineville.model.graphic;

import ru.peytob.mineville.math.Vec3;

public class Camera {
    private final float fov;

    private Vec3 position;

    private float yaw;

    private float pitch;

    private Vec3 front;

    private Vec3 right;

    public Camera(Vec3 position, float pith, float yaw, float fov) {
        this.position = position;
        this.yaw = yaw;
        this.fov = fov;
        this.pitch = pith;
    }

    public Vec3 getPosition() {
        return position;
    }

    public void setPosition(Vec3 position) {
        this.position = position;
    }

    public float getFov() {
        return fov;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public Vec3 getFront() {
        return front;
    }

    public void setFront(Vec3 front) {
        this.front = front;
    }

    public Vec3 getRight() {
        return right;
    }

    public void setRight(Vec3 right) {
        this.right = right;
    }
}
