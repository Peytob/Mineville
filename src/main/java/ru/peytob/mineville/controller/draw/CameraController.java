package ru.peytob.mineville.controller.draw;

import ru.peytob.mineville.math.Mat4;
import ru.peytob.mineville.math.Vec3;
import ru.peytob.mineville.model.graphic.Camera;

public class CameraController {
    Camera camera;

    float aspect;

    public CameraController(Vec3 position, float pith, float yaw, float fov, float aspect) {
        this.camera = new Camera(position, pith, yaw, fov);
        this.aspect = aspect;
        updateVectors();
    }

    public Mat4 computeProjection() {
        return Mat4.computePerspective(camera.getFov(), aspect, 0.1f, 128.0f);
    }

    public Mat4 computeView() {
        updateVectors();

        Vec3 up = camera.getRight().vectorMultiplication(camera.getFront());
        up = up.normalize();

        return Mat4.computeLookAt(camera.getPosition(), camera.getPosition().plus(camera.getFront()), up);
    }

    public void lookAround(float xOffset, float yOffset) {
        float yaw = camera.getYaw() + (float) Math.toRadians(xOffset);
        float pitch = camera.getPitch() + (float) Math.toRadians(yOffset);
        float borderAngle = (float) Math.toRadians(89.0f);

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
        Vec3 right = front.vectorMultiplication(worldUp).normalize();

        camera.setFront(front);
        camera.setRight(right);
    }

    public void move(Vec3 _offset) {
        camera.setPosition(camera.getPosition().plus(_offset));
    }

    public Vec3 getPosition() {
        return camera.getPosition();
    }

    public Vec3 getFrontVector() {
        return camera.getFront();
    }

    public Vec3 getRightVector() {
        return camera.getRight();
    }
}
