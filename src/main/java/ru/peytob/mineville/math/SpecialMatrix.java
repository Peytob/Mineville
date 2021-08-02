package ru.peytob.mineville.math;

/**
 * Utility class for creating special matrices, like translation or rotation matrices.
 */
public class SpecialMatrix {

    /**
     * Closed constructor.
     */
    private SpecialMatrix() {

    }

    private static final float[] IDENTITY_4_MATRIX_DATA = new float[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
    };

    public static final ImmutableMat4 IDENTITY = new Mat4(IDENTITY_4_MATRIX_DATA);

    /**
     * Computes identity matrix. If you need immutable instance of identity matrix, just use constant field IDENTITY.
     *
     * @return New instance of identity matrix.
     */
    static public Mat4 computeIdentity() {
        return new Mat4(IDENTITY_4_MATRIX_DATA);
    }

    /**
     * Computes scale matrix.
     *
     * @param x X axis scale.
     * @param y Y axis scale.
     * @param z Z axis scale.
     * @return New instance of scale matrix.
     */
    static public Mat4 computeScaleMatrix(float x, float y, float z) {
        float[] data = new float[]{
                x, 0, 0, 0,
                0, y, 0, 0,
                0, 0, z, 0,
                0, 0, 0, 1
        };

        return new Mat4(data);
    }

    /**
     * Alias for SpecialMatrix.computeScaleMatrix(vec3.x, vec3.y, vec3.z).
     *
     * @param vec3 Scale vector.
     * @return New instance of scale matrix.
     */
    static public Mat4 computeScaleMatrix(ImmutableVec3 vec3) {
        return computeScaleMatrix(vec3.x, vec3.y, vec3.z);
    }

    /**
     * Computes transposed translate matrix.
     *
     * @param x X axis translate.
     * @param y Y axis translate.
     * @param z Z axis translate.
     * @return New instance of transposed translation matrix.
     */
    static public Mat4 computeTranslation(float x, float y, float z) {
        float[] data = new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                x, y, z, 1
        };

        return new Mat4(data);
    }

    /**
     * Alias for SpecialMatrix.computeTranslation(vec3.x, vec3.y, vec3.z).
     *
     * @param vec3 Translation vector.
     * @return New instance of transposed translation matrix.
     */
    static public Mat4 computeTranslation(ImmutableVec3 vec3) {
        return computeTranslation(vec3.x, vec3.y, vec3.z);
    }

    /**
     * Returns transposed rotation matrix around (1, 0, 0) vector.
     *
     * @param angle Rotate angle in radians.
     * @return New instance of transposed rotation matrix around X axis ((1, 0, 0) vector).
     */
    static public Mat4 computeRotationX(float angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);

        float[] data = new float[]{
                1, 0, 0, 0,
                0, cos, sin, 0,
                0, -sin, cos, 0,
                0, 0, 0, 1
        };

        return new Mat4(data);
    }

    /**
     * Returns transposed rotation matrix around (0, 1, 0) vector.
     *
     * @param angle Rotate angle in radians.
     * @return New instance of transposed rotation matrix around Y axis ((0, 1, 0) vector).
     */
    static public Mat4 computeRotationY(float angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);

        float[] data = new float[]{
                cos, 0, -sin, 0,
                0, 1, 0, 0,
                sin, 0, cos, 0,
                0, 0, 0, 1
        };

        return new Mat4(data);
    }

    /**
     * Returns transposed rotation matrix around (0, 0, 1) vector.
     *
     * @param angle Rotate angle in radians.
     * @return New instance of transposed rotation matrix around X axis ((0, 0, 1) vector).
     */
    static public Mat4 computeRotationZ(float angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);

        float[] data = new float[]{
                cos, sin, 0, 0,
                -sin, cos, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        };

        return new Mat4(data);
    }

    /**
     * Returns transposed rotation matrix around (x, y, z) vector. If you need to rotate matrix around (1, 0, 0),
     * (0, 1, 0) or (0, 0, 1) vectors use computeRotationX, computeRotationY or computeRotationZ methods. It's faster!
     *
     * @param angle Rotate angle in radians.
     * @return New instance of transposed rotation matrix around (x, y, z) vector.
     */
    static public Mat4 computeRotation(float angle, float x, float y, float z) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);

        float[] data = new float[]{
                cos + x * x * (1 - cos), y * x * (1 - cos) + z * sin, z * x * (1 - cos) - y * sin, 0,
                x * y * (1 - cos) - z * sin, cos + y * y * (1 - cos), z * y * (1 - cos) + x * sin, 0,
                x * z * (1 - cos) + y * sin, y * z * (1 - cos) - x * sin, cos + z * z * (1 - cos), 0,
                0, 0, 0, 1
        };

        return new Mat4(data);
    }

    /**
     * Alias for SpecialMatrix.computeRotation(angle, vec3.x, vec3.y, vec3.z).
     *
     * @param vec3 Rotation vector.
     * @return New instance of transposed rotation matrix around (x, y, z) vector.
     */
    static public Mat4 computeRotation(float angle, ImmutableVec3 vec3) {
        return computeRotation(angle, vec3.x, vec3.y, vec3.z);
    }

    /**
     * Returns transposed view matrix.
     *
     * @param position Camera position.
     * @param target   Point at camera front vector.
     * @param up       Up vector.
     * @return New instance of transposed view matrix.
     */
    static public Mat4 computeLookAt(ImmutableVec3 position, ImmutableVec3 target, ImmutableVec3 up) {
        Vec3 zAxis = new Vec3(position).minus(target).normalize();
        Vec3 xAxis = new Vec3(up).normalize().vectorMultiplication(zAxis).normalize();
        Vec3 yAxis = new Vec3(zAxis).vectorMultiplication(xAxis);

        Mat4 translation = computeTranslation(-position.x, -position.y, -position.z);

        Mat4 rotation = computeIdentity();
        rotation.set(0, 0, xAxis.x);
        rotation.set(1, 0, xAxis.y);
        rotation.set(2, 0, xAxis.z);

        rotation.set(0, 1, yAxis.x);
        rotation.set(1, 1, yAxis.y);
        rotation.set(2, 1, yAxis.z);

        rotation.set(0, 2, zAxis.x);
        rotation.set(1, 2, zAxis.y);
        rotation.set(2, 2, zAxis.z);

        return rotation.multiplication(translation);
    }

    /**
     * Alias for SpecialMatrix.computeLookAt(positionVec3, targetVec3, upVec3).
     *
     * @param posx X component of position vector.
     * @param posy Y component of position vector.
     * @param posz Z component of position vector.
     * @param tarx X component of target vector.
     * @param tary Y component of target vector.
     * @param tarz Z component of target vector.
     * @param upx  X component of up vector.
     * @param upy  Y component of up vector.
     * @param upz  Z component of up vector.
     * @return New instance of transposed perspective matrix.
     */
    static public Mat4 computeLookAt(float posx, float posy, float posz, float tarx, float tary, float tarz,
                                     float upx, float upy, float upz) {
        return computeLookAt(new Vec3(posx, posy, posz), new Vec3(tarx, tary, tarz), new Vec3(upx, upy, upz));
    }

    static public Mat4 computePerspective(float fov, float aspect, float nearPlane, float farPlane) {
        float zp = farPlane + nearPlane;
        float zm = farPlane - nearPlane;

        Mat4 perspective = new Mat4();
        perspective.set(1, 1, 1.0f / (float) Math.tan(fov / 2)); // yscale
        perspective.set(0, 0, perspective.get(1, 1) / aspect); // xscale = yscale / aspect
        perspective.set(2, 2, -zp / zm);

        perspective.set(3, 2, -(2 * farPlane * nearPlane) / zm);
        perspective.set(2, 3, -1.0f);

        return perspective;
    }
}
