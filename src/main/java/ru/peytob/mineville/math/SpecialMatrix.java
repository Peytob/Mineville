package ru.peytob.mineville.math;

public class SpecialMatrix {
    public final ImmutableMat4 IDENTITY = new Mat4(new float[]{
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
    });

    /**
     * Computes identity matrix. If you need immutable instance of identity matrix
     *
     * @return New instance of identity matrix.
     */
    static public Mat4 computeIdentity() {
        float[] data = new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        };

        return new Mat4(data);
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
     * Alias for Mat4.computeScaleMatrix(vec3.x, vec3.y, vec3.z).
     *
     * @param _vec3 Scale vector.
     * @return New instance of scale matrix.
     */
    static public Mat4 computeScaleMatrix(Vec3 _vec3) {
        return computeScaleMatrix(_vec3.x, _vec3.y, _vec3.z);
    }

    /**
     * Computes transposed translate matrix.
     *
     * @param _x X axis translate.
     * @param _y Y axis translate.
     * @param _z Z axis translate.
     * @return New instance of transposed translation matrix.
     */
    static public Mat4 computeTranslation(float _x, float _y, float _z) {
        float[] data = new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                _x, _y, _z, 1
        };

        return new Mat4(data);
    }

    /**
     * Alias for Mat4.computeTranslation(vec3.x, vec3.y, vec3.z).
     *
     * @param _vec3 Translation vector.
     * @return New instance of transposed translation matrix.
     */
    static public Mat4 computeTranslation(Vec3 _vec3) {
        return computeTranslation(_vec3.x, _vec3.y, _vec3.z);
    }

    /**
     * Returns transposed rotation matrix around (1, 0, 0) vector.
     *
     * @param _angle Rotate angle in radians.
     * @return New instance of transposed rotation matrix around X axis ((1, 0, 0) vector).
     */
    static public Mat4 computeRotationX(float _angle) {
        float cos = (float) Math.cos(_angle);
        float sin = (float) Math.sin(_angle);

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
     * @param _angle Rotate angle in radians.
     * @return New instance of transposed rotation matrix around Y axis ((0, 1, 0) vector).
     */
    static public Mat4 computeRotationY(float _angle) {
        float cos = (float) Math.cos(_angle);
        float sin = (float) Math.sin(_angle);

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
     * @param _angle Rotate angle in radians.
     * @return New instance of transposed rotation matrix around X axis ((0, 0, 1) vector).
     */
    static public Mat4 computeRotationZ(float _angle) {
        float cos = (float) Math.cos(_angle);
        float sin = (float) Math.sin(_angle);

        float[] data = new float[]{
                cos, sin, 0, 0,
                -sin, cos, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        };

        return new Mat4(data);
    }

    /**
     * Returns transposed rotation matrix around (x, y, z) vector.
     *
     * @param _angle Rotate angle in radians.
     * @return New instance of transposed rotation matrix around (x, y, z) vector.
     */
    static public Mat4 computeRotation(float _angle, float _x, float _y, float _z) {
        float cos = (float) Math.cos(_angle);
        float sin = (float) Math.sin(_angle);

        float[] data = new float[]{
                cos + _x * _x * (1 - cos), _y * _x * (1 - cos) + _z * sin, _z * _x * (1 - cos) - _y * sin, 0,
                _x * _y * (1 - cos) - _z * sin, cos + _y * _y * (1 - cos), _z * _y * (1 - cos) + _x * sin, 0,
                _x * _z * (1 - cos) + _y * sin, _y * _z * (1 - cos) - _x * sin, cos + _z * _z * (1 - cos), 0,
                0, 0, 0, 1
        };

        return new Mat4(data);
    }

    /**
     * Alias for Mat4.computeRotation(angle, vec3.x, vec3.y, vec3.z).
     *
     * @param _vec3 Rotation vector.
     * @return New instance of transposed rotation matrix around (x, y, z) vector.
     */
    static public Mat4 computeRotation(float _angle, Vec3 _vec3) {
        return computeRotation(_angle, _vec3.x, _vec3.y, _vec3.z);
    }

    /**
     * Returns transposed view matrix.
     *
     * @param _position Camera position.
     * @param _target   Point at camera front vector.
     * @param _up       Up vector.
     * @return New instance of transposed view matrix.
     */
    static public Mat4 computeLookAt(Vec3 _position, Vec3 _target, Vec3 _up) {
        Vec3 zAxis = _position.minus(_target);
        zAxis = zAxis.normalize();

        Vec3 upNormalize = _up.normalize();
        Vec3 crossUpAndZ = upNormalize.vectorMultiplication(zAxis);
        Vec3 xAxis = crossUpAndZ.normalize();

        Vec3 yAxis = zAxis.vectorMultiplication(xAxis);

        Mat4 translation = Mat4.computeTranslation(_position.negative());

        Mat4 rotation = Mat4.computeIdentity();
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
     * Alias for computeLookAt(positionVec3, targetVec3, upVec3).
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

    static public Mat4 computePerspective(float _fov, float _aspect, float _nearPlane, float _farPlane) {
        float zp = _farPlane + _nearPlane;
        float zm = _farPlane - _nearPlane;

        Mat4 perspective = new Mat4();
        perspective.set(1, 1, 1.0f / (float) Math.tan(_fov / 2)); // y_scale
        perspective.set(0, 0, perspective.get(1, 1) / _aspect); // x_scale = y_scale / aspect
        perspective.set(2, 2, -zp / zm);

        perspective.set(3, 2, -(2 * _farPlane * _nearPlane) / zm);
        perspective.set(2, 3, -1.0f);

        return perspective;
    }
}
