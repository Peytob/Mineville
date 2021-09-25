package ru.peytob.mineville.model.game.object.entity;

import ru.peytob.mineville.math.AABB;
import ru.peytob.mineville.math.ImmutableVec3;
import ru.peytob.mineville.math.Vec3;
import ru.peytob.mineville.model.repository.AbstractRegistrable;

public abstract class Entity extends AbstractRegistrable {
    private AABB collisionBox;
    private boolean ignoreFrustum;
    private boolean ignorePhysicsCollisions;
    private boolean ignoreEntityCollisions;
    private Vec3 worldPosition;

    public Entity(String repositoryName, Integer id) {
        super(repositoryName, id);
        this.collisionBox = null;
        this.ignoreFrustum = false;
        this.ignorePhysicsCollisions = false;
        this.ignoreEntityCollisions = false;
        this.worldPosition = new Vec3();
    }

    public AABB getCollisionBox() {
        return collisionBox;
    }

    protected void setCollisionBox(AABB collisionBox) {
        this.collisionBox = collisionBox;
    }

    public boolean isIgnoreFrustum() {
        return ignoreFrustum;
    }

    protected void setIgnoreFrustum(boolean ignoreFrustum) {
        this.ignoreFrustum = ignoreFrustum;
    }

    public boolean isIgnorePhysicsCollisions() {
        return ignorePhysicsCollisions;
    }

    protected void setIgnorePhysicsCollisions(boolean ignorePhysicsCollisions) {
        this.ignorePhysicsCollisions = ignorePhysicsCollisions;
    }

    public boolean isIgnoreEntityCollisions() {
        return ignoreEntityCollisions;
    }

    protected void setIgnoreEntityCollisions(boolean ignoreEntityCollisions) {
        this.ignoreEntityCollisions = ignoreEntityCollisions;
    }

    public ImmutableVec3 getWorldPosition() {
        return worldPosition;
    }

    public void setWorldPosition(ImmutableVec3 worldPosition) {
        this.worldPosition = new Vec3(worldPosition);
        this.collisionBox.setCenter(new Vec3(worldPosition).plus(this.collisionBox.getRadius()));
    }

    public void moveWorldPosition(ImmutableVec3 delta) {
        this.worldPosition.plus(delta);
        this.collisionBox.move(delta);
    }
}
