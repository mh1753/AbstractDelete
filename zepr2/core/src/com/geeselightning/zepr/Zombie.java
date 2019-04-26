package com.geeselightning.zepr;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Zombie extends Character {

    private Player player = Player.getInstance();
    int attackDamage = Constant.ZOMBIEDMG;
    // Change starts: ZOMBIEOPTIMIZATION
    final float hitCooldown = Constant.ZOMBIEHITCOOLDOWN;
    // Change ends: ZOMBIEOPTIMIZATION
    // Change starts: ZOMBIESTORYENEMIES
    private Zepr parent;
    Texture humanMain;
    Texture humanAttack;
    // Change ends: ZOMBIESTORYENEMIES

    public Zombie(Sprite sprite, Vector2 zombieSpawn, Level currentLevel, Zepr parent) {
        super(sprite, zombieSpawn, currentLevel);
        // Change starts: ZOMBIESTORYENEMIES
        this.parent = parent;
        // If the player is a zombie, set textures to that of the nerdy class
        if (parent.isZombie()){
            this.humanMain = new Texture("player01.png");
            this.humanAttack = new Texture("player01_attack.png");
        }
        // Change ends: ZOMBIESTORYENEMIES
        // Added RNG to vary zombie speeds
        Random rand = new Random();
        // Change starts: SAFEAREADIFFICULTYRISE
        // After the safe area, increase difficulty by increasing health and speed
        if (parent.getProgress() > Zepr.LIBRARY){
            this.speed = Constant.ZOMBIESPEED * Constant.SAFEAREAMULT + rand.nextInt(20);
            this.maxHealth = (int) (Constant.ZOMBIEMAXHP * Constant.SAFEAREAMULT);
        } else {
            this.speed = Constant.ZOMBIESPEED + rand.nextInt(20);
            this.maxHealth = Constant.ZOMBIEMAXHP;
        }
        // Change ends: SAFEAREADIFFICULTYRISE
        this.health = maxHealth;
    }

    @Override
    public void attack(Character player, float delta) {
        // Change starts: CUREEFFECT
        // Change starts: ZOMBIEOPTIMIZATION
        if (canHitGlobal(player, Constant.ZOMBIERANGE) && hitRefresh > hitCooldown && !isRunning()) {
            // Change ends: ZOMBIEOPTIMIZATION
            // Change ends: CUREFFECT
            // Change starts: ZOMBIEAVOIDTIMERSET
            currentLevel.avoidTimer = Constant.AVOIDTIMER;
            // Change ends: ZOMBIEAVOIDTIMERSET
            player.takeDamage(attackDamage);
            // Change starts: ZOMBIESTORYENEMIES
            // If the player is a zombie, perform human animations
            if (parent.isZombie()) {
                this.setTexture(humanAttack);
            }
            // Change ends: ZOMBIESTORYENEMIES
            hitRefresh = 0;
        } else {
            hitRefresh += delta;
        }
        // Change starts: ZOMBIESTORYENEMIES
        if (parent.isZombie() && this.getTexture()!= humanMain && hitRefresh > hitCooldown/(hitCooldown*5)){
            this.setTexture(humanMain);
        }
        // Change ends: ZOMBIESTORYENEMIES
    }

    @Override
    public void update(float delta) {
        //move according to velocity
        super.update(delta);

        // Change starts: CUREEFFECT
        /* If cure powerup is active:
         * move away and face away from the player, else:
         * move to and face towards the player
         */
        // Vector2.scl scales the vector
        if (this.isRunning()){
            velocity = getDirNormVector(player.getCenter()).rotate(180).scl(speed);
            direction = getDirectionTo(player.getCenter())+Math.PI;
        } else {
            velocity = getDirNormVector(player.getCenter()).scl(speed);
            direction = getDirectionTo(player.getCenter());
        }
        // Change ends: CUREEFFECT

        if (health <= 0) {
            // Change starts: ZOMBIEPOINTGAIN
            currentLevel.parent.addPoints(Constant.ZOMBIEPOINTS);
            // Change ends: ZOMBIEPOINTGAIN
            // Change starts: CURESPAWNCONDITION
            currentLevel.parent.addCureProg(Constant.ZOMBIEPOINTS);
            // Change ends: CURESPAWNCONDITION
            currentLevel.zombiesRemaining--;
            currentLevel.aliveZombies.remove(this);
            // Change starts: ZOMBIEOPTIMIZATION
            // Change starts: ZOMBIESTORYENEMIES
            // Discard textures
            if (humanMain != null){
                humanMain.dispose();
            }
            if (humanAttack != null){
                humanAttack.dispose();
            }
            // Change ends: ZOMBIESTORYENEMIES
            this.getTexture().dispose();
            // Change ends: ZOMBIEOPTIMIZATION
        }
    }

    // Change starts: CUREEFFECT
    @Override
    public void takeDamage(int dmg){
        if (!isRunning()){
            health -= dmg;
        }
    }
    // Change ends: CUREEFFECT
}
