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
        // Change starts: SAFEAREADIFFICULTYRISE
        if (parent.getProgress() > Zepr.LIBRARY){
            this.speed = Constant.ZOMBIESPEED * Constant.SAFEAREAMULT + rand.nextInt(20);
            this.maxHealth = (int) (Constant.ZOMBIEMAXHP * Constant.SAFEAREAMULT);
        } else {
            this.speed = Constant.ZOMBIESPEED + rand.nextInt(20);
            this.maxHealth = Constant.ZOMBIEMAXHP;
        }
        this.health = maxHealth;
        // Change ends: SAFEAREADIFFICULTYRISE
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
        if (this.getTexture()!= humanMain && hitRefresh > hitCooldown/(hitCooldown*5) && parent.isZombie()){
            this.setTexture(humanMain);
        }
    }

    @Override
    public void update(float delta) {
        //move according to velocity
        super.update(delta);

        // update velocity to move towards or away from the player
        // update direction to face towards or away from the player
        // Vector2.scl scales the vector
        if (this.isRunning()){
            velocity = getDirNormVector(player.getCenter()).rotate(180).scl(speed);
            direction = getDirectionTo(player.getCenter())+Math.PI;
        } else {
            velocity = getDirNormVector(player.getCenter()).scl(speed);
            direction = getDirectionTo(player.getCenter());
        }

        if (health <= 0) {
            //Change starts: ZOMBIEPOINTGAIN
            currentLevel.parent.addPoints(Constant.ZOMBIEPOINTS);
            //Change ends: ZOMBIEPOINTGAIN
            currentLevel.parent.addCureProg(Constant.ZOMBIEPOINTS);
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
