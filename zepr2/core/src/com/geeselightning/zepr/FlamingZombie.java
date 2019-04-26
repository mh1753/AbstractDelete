/**
 * Added by Shaun of the Devs to meet the requirement of different zombie types
 */
package com.geeselightning.zepr;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class FlamingZombie extends Zombie {

    // Change starts: ZOMBIEOPTIMIZATION
    // Change ends: ZOMBIEOPTIMIZATION
    private boolean playerBurning = false;
    private int count = 0;
    private float timer = 0;
    // Change starts: ZOMBIESTORYENEMIES
    private Zepr parent;
    // Change ends: ZOMBIESTORYENEMIES

    public FlamingZombie(Sprite sprite, Vector2 zombieSpawn, Level currentLevel, Zepr parent) {
        super(sprite, zombieSpawn, currentLevel, parent);
        Random rand = new Random();
        // Change starts: ZOMBIESTORYENEMIES
        this.parent = parent;
        // If the player is a zombie, set textures to that of the drama class
        if (parent.isZombie()){
            this.humanMain = new Texture("player03.png");
            this.humanAttack = new Texture("player03_attack.png");
        }
        // Change ends: ZOMBIESTORYENEMIES
        // Change starts: SAFEAREADIFFICULTYRISE
        // After the safe area, increase difficulty by increasing health and speed
        if (parent.getProgress() > Zepr.LIBRARY){
            this.speed = Constant.FLAMINGZOMBIESPEED * Constant.SAFEAREAMULT + rand.nextInt(10);
            this.speed = Constant.FLAMINGZOMBIEMAXHP * Constant.SAFEAREAMULT;
        } else {
            this.speed = Constant.FLAMINGZOMBIESPEED + rand.nextInt(10);
            this.maxHealth = Constant.FLAMINGZOMBIEMAXHP;
        }
        // Change ends: SAFEAREADIFFICULTYRISE
        this.health = maxHealth;
    }

    @Override
    public void attack(Character player, float delta) {
        // Change starts: ZOMBIEOPTIMISATION
        if (canHitGlobal(player, Constant.FLAMINGZOMBIERANGE) &&
                hitRefresh > Constant.FLAMINGZOMBIEHITCOOLDOWN && !isRunning()) {
            // Change ends: ZOMBIEOPTIMIZATION
    	    // Change starts: FLAMINGZOMBIEAVOIDTIMERSET
            currentLevel.avoidTimer = Constant.AVOIDTIMER;
            // Change ends: FLAMINGZOMBIEAVOIDTIMERSET
            // Change starts: ZOMBIESTORYENEMIES
            // If the player is a zombie, perform human animations
            if (parent.isZombie()){
                this.setTexture(humanAttack);
            }
            // Change ends: ZOMBIESTORYENEMIES
            playerBurning = true;
            hitRefresh = 0;
        } else {
            hitRefresh += delta;
        }
        // Change starts: ZOMBIESTORYENEMIES
    	if (parent.isZombie() && this.getTexture() != humanMain && hitRefresh > hitCooldown /(hitCooldown *4)){
    	    this.setTexture(humanMain);
        }
    	// Change ends: ZOMBIESTORYENEMIES

    	// Added fire damage: A large amount of damage spread out over a longer time
    	timer += delta;
    	if(timer>1 && playerBurning) {
            // Change starts: ZOMBIEOPTIMIZATION
            player.takeDamage(Constant.FLAMINGZOMBIEDMG);
            // Change ends: ZOMBIEOPTIMIZATION
        	timer = 0;
        	count += 1;
    	}
        if(count==5) {
        	playerBurning = false;
            count = 0;
        }
    }
}