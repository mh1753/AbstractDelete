package com.geeselightning.zepr;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Zombie extends Character {

    private Player player = Player.getInstance();
    int attackDamage = Constant.ZOMBIEDMG;
    public int hitRange = Constant.ZOMBIERANGE;
    public final float hitCooldown = Constant.ZOMBIEHITCOOLDOWN;
    private Zepr parent;
    Texture humanMain;
    Texture humanAttack;

    public Zombie(Sprite sprite, Vector2 zombieSpawn, Level currentLevel, Zepr parent) {
        super(sprite, zombieSpawn, currentLevel);
        this.parent = parent;
        if (parent.isZombie()){
            this.humanMain = new Texture("player01.png");
            this.humanAttack = new Texture("player01_attack.png");
        }
        // Added RNG to vary zombie speeds
        Random rand = new Random();
        this.speed = Constant.ZOMBIESPEED + rand.nextInt(20);
        this.maxHealth = Constant.ZOMBIEMAXHP;
        this.health = maxHealth;
    }

    @Override
    public void attack(Character player, float delta) {
        if (canHitGlobal(player, hitRange) && hitRefresh > hitCooldown && !isRunning()) {
            //Change starts: ZOMBIEAVOIDTIMERSET
            currentLevel.avoidTimer = Constant.AVOIDTIMER;
            //Change ends: ZOMBIEAVOIDTIMERSET
            player.takeDamage(attackDamage);
            if (parent.isZombie()) {
                this.setTexture(humanAttack);
            }
            hitRefresh = 0;
        } else {
            hitRefresh += delta;
        }
        if (hitRefresh > hitCooldown/6 && parent.isZombie()){
            this.setTexture(humanMain);
        }
    }

    @Override
    public void update(float delta) {
        //move according to velocity
        super.update(delta);

        // update velocity to move towards player
        // Vector2.scl scales the vector
        if (this.isRunning()){
            velocity = getDirNormVector(player.getCenter()).rotate(180).scl(speed);
        } else {
            velocity = getDirNormVector(player.getCenter()).scl(speed);
        }

        // update direction to face the player
        if (this.isRunning()){
            direction = getDirectionTo(player.getCenter())+Math.PI;
        } else {
            direction = getDirectionTo(player.getCenter());
        }

        if (health <= 0) {
            //Change starts: ZOMBIEPOINTGAIN
            currentLevel.parent.addPoints(Constant.ZOMBIEPOINTS);
            //Change ends: ZOMBIEPOINTGAIN
            currentLevel.zombiesRemaining--;
            currentLevel.aliveZombies.remove(this);
            if (humanMain != null){
                humanMain.dispose();
            }
            if (humanAttack != null){
                humanAttack.dispose();
            }
            this.getTexture().dispose();
            // Removed disposal of texture to prevent texture glitch
        }
    }

    @Override
    public void takeDamage(int dmg){
        if (!isRunning()){
            health -= dmg;
        }
    }
}
