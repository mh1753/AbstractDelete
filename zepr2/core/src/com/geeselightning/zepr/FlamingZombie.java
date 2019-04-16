/**
 * Added by Shaun of the Devs to meet the requirement of different zombie types
 */
package com.geeselightning.zepr;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class FlamingZombie extends Zombie {

    private Player player = Player.getInstance();
    int attackDamage = Constant.FLAMINGZOMBIEDMG;
    public int hitRange = Constant.FLAMINGZOMBIERANGE;
    public final float hitCooldown = Constant.FLAMINGZOMBIEHITCOOLDOWN;
    private boolean playerBurning = false;
    private int count = 0;
    private float timer = 0;
    private Zepr parent;

    public FlamingZombie(Sprite sprite, Vector2 zombieSpawn, Level currentLevel, Zepr parent) {
        super(sprite, zombieSpawn, currentLevel, parent);
        Random rand = new Random();
        this.parent = parent;
        if (parent.isZombie()){
            this.humanMain = new Texture("player03.png");
            this.humanAttack = new Texture("player03_attack.png");
        }
        this.speed = Constant.FLAMINGZOMBIESPEED + rand.nextInt(10);
        this.maxHealth = Constant.FLAMINGZOMBIEMAXHP;
        this.health = maxHealth;
    }

    @Override
    public void attack(Character player, float delta) {
    	if (canHitGlobal(player, hitRange) && hitRefresh > hitCooldown && !isRunning()) {
    	    //Change starts: FLAMINGZOMBIEAVOIDTIMERSET
            currentLevel.avoidTimer = Constant.AVOIDTIMER;
            //Change ends: FLAMINGZOMBIEAVOIDTIMERSET
            if (parent.isZombie()){
                this.setTexture(humanAttack);
            }
            playerBurning = true;
            hitRefresh = 0;
        } else {
            hitRefresh += delta;
        }
    	if (hitRefresh > hitCooldown/6 && parent.isZombie()){
    	    this.setTexture(humanMain);
        }

    	// Added fire damage: A large amount of damage spread out over a longer time       
    	timer += delta; 
    	if(timer>1 && playerBurning) {
    		player.takeDamage(attackDamage);  
        	timer = 0;
        	count += 1;
    	}
        if(count==5) {
        	playerBurning = false;
            count = 0;	      
        }
    }
}