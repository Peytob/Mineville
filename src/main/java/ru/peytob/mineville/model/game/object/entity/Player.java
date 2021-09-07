package ru.peytob.mineville.model.game.object.entity;

import ru.peytob.mineville.math.AABB;
import ru.peytob.mineville.math.Vec3;

public class Player extends EntityLiving {
    public Player(String repositoryName, Integer id) {
        super(repositoryName, id);

        this.setCollisionBox(new AABB(new Vec3(getWorldPosition()), new Vec3(0.45f, 0.9f, 0.45f)));
        this.setMaxHealth(20.0f);
        this.setMovingSpeed(5.7f);
    }
}
