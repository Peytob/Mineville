package ru.peytob.mineville.controller.draw;

import ru.peytob.mineville.math.*;
import ru.peytob.mineville.model.graphic.Camera;

public class CameraController {
    private final Camera camera;

    private float aspect;

    public CameraController(Vec3 position, float pith, float yaw, float fov, float aspect) {
        this.camera = new Camera(position, pith, yaw, fov);
        this.aspect = aspect;
        updateVectors();
    }

    public Mat4 computeProjection() {
        return SpecialMatrix.computePerspective(camera.getFov(), aspect, 0.1f, 128.0f);
    }

    public Mat4 computeView() {
        updateVectors();

        Vec3 up = new Vec3(camera.getRight()).vectorMultiplication(camera.getFront()).normalize();
        Vec3 target = new Vec3(camera.getPosition()).plus(camera.getFront());

        return SpecialMatrix.computeLookAt(camera.getPosition(), target, up);
    }

    public void lookAround(float xOffset, float yOffset) {
        float yaw = camera.getYaw() + (float) Math.toRadians(xOffset);
        float pitch = camera.getPitch() + (float) Math.toRadians(yOffset);
        float borderAngle = (float) Math.toRadians(89.999f);

        if (pitch > borderAngle) {
            pitch = borderAngle;
        } else if (pitch < -borderAngle) {
            pitch = -borderAngle;
        }

        camera.setYaw(yaw);
        camera.setPitch(pitch);
    }

    private void updateVectors() {
        final float yaw = camera.getYaw();
        final float pitch = camera.getPitch();

        float pitchCos = (float) Math.cos(pitch);

        Vec3 front = new Vec3(
                (float) Math.cos(yaw) * pitchCos,
                (float) Math.sin(pitch),
                (float) Math.sin(yaw) * pitchCos
        ).normalize();

        Vec3 worldUp = new Vec3(0, 1, 0);
        Vec3 right = new Vec3(front).vectorMultiplication(worldUp).normalize();

        camera.setFront(front);
        camera.setRight(right);
    }

    public void move(ImmutableVec3 offset) {
        camera.move(offset);
    }

    public void setPosition(ImmutableVec3 position) {
        camera.setPosition(new Vec3(position));
    }

    public ImmutableVec3 getPosition() {
        return camera.getPosition();
    }

    public ImmutableVec3 getFrontVector() {
        return camera.getFront();
    }

    public ImmutableVec3 getRightVector() {
        return camera.getRight();
    }

    public void setAspect(float aspect) {
        this.aspect = aspect;
    }
}
