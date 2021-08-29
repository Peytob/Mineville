package ru.peytob.mineville.model.game.object.entity;

public abstract class EntityLiving extends Entity {
    private float health;
    private float maxHealth;
    private float movingSpeed;

    public EntityLiving(String repositoryName, Integer id) {
        super(repositoryName, id);
    }

    public float getHealth() {
        return health;
    }

    public float restoringHealth(int healthAmount) {
        float healthOld = health;
        health += healthAmount;

        if (health > maxHealth) {
            health = maxHealth;
        }

        return health - healthOld;
    }

    public float gettingDamage(int damageAmount) {
        float healthOld = health;
        health -= damageAmount;

        if (health < 0.0f) {
            health = 0.0f;
        }

        return healthOld - health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    protected void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public boolean isAlive() {
        return Float.compare(health, 0.0f) > 0;
    }

    public float getMovingSpeed() {
        return movingSpeed;
    }

    protected void setMovingSpeed(float movingSpeed) {
        this.movingSpeed = movingSpeed;
    }
}
