package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Zombie extends Character {

    private Player player = Player.getInstance();
    int attackDamage = Constant.ZOMBIEDMG;
    public int hitRange = Constant.ZOMBIERANGE;
    public final float hitCooldown = Constant.ZOMBIEHITCOOLDOWN;

    public Zombie(Sprite sprite, Vector2 zombieSpawn, Level currentLevel) {
        super(sprite, zombieSpawn, currentLevel);
        //Change starts ; Reference SAFEAREADIFFICULTYINCREASE
        if(currentLevel != null) {
            if (currentLevel.parent.progress > currentLevel.parent.COMPUTERSCIENCE) {
                this.speed = (int) (1.5 * Constant.ZOMBIEBASESPEED);
                this.health = (int) (1.5 * Constant.ZOMBIEBASEMAXHP);
            } else {
                this.speed = Constant.ZOMBIEBASESPEED;
                this.health = Constant.ZOMBIEBASEMAXHP;
            }
        } else {
            this.speed = Constant.ZOMBIEBASESPEED;
            this.health = Constant.ZOMBIEBASEMAXHP;
        }
        //Change ends ; Reference SAFEAREADIFFICULTYINCREASE
    }

    public void attack(Player player, float delta) {
        if (canHitGlobal(player, hitRange) && hitRefresh > hitCooldown) {
            player.takeDamage(attackDamage);
            hitRefresh = 0;
            if(currentLevel != null) {
                currentLevel.avoidTimer = Constant.AVOIDTIMER;
            }
        } else {
            hitRefresh += delta;
        }
    }

    @Override
    public void update(float delta) {
        //move according to velocity
        super.update(delta);

        // update velocity to move towards player
        // Vector2.scl scales the vector
        velocity = getDirNormVector(player.getCenter()).scl(speed);

        // update direction to face the player
        direction = getDirectionTo(player.getCenter());

        if (health <= 0) {
            currentLevel.zombiesRemaining--;
            currentLevel.aliveZombies.remove(this);
            //Change starts ; Reference GRANTZOMBIEPOINTS
            currentLevel.parent.addPoints(Constant.ZOMBIEKILLPOINTS);
            //Change ends ; Reference GRANTZOMBIEPOINTS
            this.getTexture().dispose();
        }
    }
}
