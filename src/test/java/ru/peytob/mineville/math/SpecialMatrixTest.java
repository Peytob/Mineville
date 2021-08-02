package ru.peytob.mineville.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialMatrixTest {

    @Test
    void computeIdentity() {
        ImmutableMat4 excepted = new Mat4(new float[] {
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        });

        assertEquals(excepted, SpecialMatrix.computeIdentity());
        assertEquals(excepted, SpecialMatrix.IDENTITY);
        assertEquals(SpecialMatrix.IDENTITY, SpecialMatrix.computeIdentity());
    }

    @Test
    void computeScaleMatrix() {
        Mat4 expected = new Mat4(new float[]{
                53.2f, 0, 0, 0,
                0, 65.0f, 0, 0,
                0, 0, -12.11f, 0,
                0, 0, 0, 1
        });

        assertEquals(expected, SpecialMatrix.computeScaleMatrix(53.2f, 65.0f, -12.11f));
    }

    @Test
    void computeTranslation() {
        Mat4 expected = new Mat4(new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                -53.2f, 12.66f, 76f, 1
        });

        assertEquals(expected, SpecialMatrix.computeTranslation(-53.2f, 12.66f, 76));
    }

    @Test
    void computeRotationX() {
        Mat4 expected = new Mat4(new float[]{
                1, 0, 0, 0,
                0, 0.4999999f, -0.86602545f, 0,
                0, 0.86602545f, 0.4999999f, 0,
                0, 0, 0, 1
        });

        float angle = (float) Math.PI / 3.0f * 5.0f;
        assertEquals(expected, SpecialMatrix.computeRotationX(angle));
    }

    @Test
    void computeRotationY() {
        Mat4 expected = new Mat4(new float[]{
                0.4999999f, 0, 0.86602545f, 0,
                0, 1, 0, 0,
                -0.86602545f, 0, 0.4999999f, 0,
                0, 0, 0, 1
        });

        float angle = (float) Math.PI / 3.0f * 5.0f;
        assertEquals(expected, SpecialMatrix.computeRotationY(angle));
    }

    @Test
    void computeRotationZ() {
        Mat4 expected = new Mat4(new float[]{
                0.4999999f, -0.86602545f, 0, 0,
                0.86602545f, 0.4999999f, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        });

        float angle = (float) Math.PI / 3.0f * 5.0f;
        assertEquals(expected, SpecialMatrix.computeRotationZ(angle));
    }

    @Test
    void computeRotation() {
        Mat4 expected = new Mat4(new float[]{
                0.719122f, -0.55907035f, 0.41218716f, 0,
                0.6914704f, 0.5199999f, -0.5011088f, 0,
                0.065776974f, 0.6455088f, 0.76064193f, 0,
                0, 0, 0, 1
        });

        float angle = (float) Math.PI / 3.0f * 5.0f;
        assertEquals(expected, SpecialMatrix.computeRotation(angle, 0.662f, 0.2f, 0.722f));
    }

    @Test
    void computeLookAt() {
        Mat4 expected = new Mat4(new float[]{
                -0.9783499f, -0.13016832f, -0.1608963f, 0,
                0, 0.7774364f, -0.62896156f, 0,
                0.2069575f, -0.6153445f, -0.76060486f, 0,
                42.18349f, 13.739656f, 57.685097f, 1,
        });

        Vec3 front = new Vec3(0.160897f, 0.628962f, 0.760605f);
        Vec3 position = new Vec3(52.34f, 25.6f, 43.6f);
        Vec3 up = new Vec3(0.0f, 1.0f, 0.0f);

        assertEquals(expected, SpecialMatrix.computeLookAt(position, new Vec3(position).plus(front), up));
    }

    @Test
    void computePerspective() {
        Mat4 expected = new Mat4(new float[]{
                0.46039617f, 0, 0, 0,
                0, 0.76732695f, 0, 0,
                0, 0, -1.002002f, -1,
                0, 0, -0.2002002f, 0
        });

        float fov = (float) Math.toRadians(105.0);
        assertEquals(expected, SpecialMatrix.computePerspective(fov, 5.0f / 3.0f, 0.1f, 100.0f));
    }
}