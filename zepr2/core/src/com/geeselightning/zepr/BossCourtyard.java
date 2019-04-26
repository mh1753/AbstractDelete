/*
  Added by Shaun of the Devs to meet the requirement of Bosses
  Edited by Abstract Delete for functionality
 */
package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class BossCourtyard extends Character {

    private Player player = Player.getInstance();
    // Change starts: BOSSCLASSOPTIMIZATION
    // Change ends: BOSSCLASSOPTIMIZATION
    private int zombiesToSpawn = 0;
    private boolean spawningZombies = false;
    private float spawnTimer = 0;
    // Change starts: ZOMBIESTORYENEMIES
    private Zepr parent;
    // Change ends: ZOMBIESTORYENEMIES

    public BossCourtyard(Sprite sprite, Vector2 zombieSpawn, Level currentLevel, Zepr parent) {
        super(sprite, zombieSpawn, currentLevel);
        // Change starts: ZOMBIESTORYENEMIES
        this.parent = parent;
        // Change ends: ZOMBIESTORYENEMIES
        this.speed = Constant.BOSSCOURTYARDSPEED;
        this.maxHealth = Constant.BOSSCOURTYARDMAXHP;
        this.health = maxHealth;
    }

    @Override
    public void attack(Character player, float delta) {
        // Change starts: BOSSCLASSOPTIMIZATION
        if (canHitGlobal(player, Constant.ZOMBIERANGE) && hitRefresh > Constant.ZOMBIEHITCOOLDOWN) {
            player.takeDamage(Constant.ZOMBIEDMG);
            // Change ends: BOSSCLASSOPTIMIZATION
            hitRefresh = 0;
        } else {
            hitRefresh += delta;
        }
    }

    @Override
    public void update(float delta) {
        //move according to velocity
        super.update(delta);
        
        // Spawns zombies near the boss when there are less than 3 zombies on the map
        // every 10 seconds, spawns for 2 seconds near the boss if there are no zombies in the way
        if (spawnTimer <= 12) {
        	spawnTimer += delta;
        } else {
        	spawnTimer = 0;
        }
        
        if (currentLevel.zombiesRemaining + zombiesToSpawn < 3) {
        	zombiesToSpawn++;
        }
        
        if (spawnTimer <= 2 && zombiesToSpawn > 0) {
        	spawningZombies = true;
        	// Change starts: BOSSFUNCTIONALITYFIX
            int random = (int)(Math.random() * 128 + 1);
        	if (random > 64) {
        		random = -(random - 64);
        	}
        	// Change ends: BOSSFUNCTIONALITYFIX
        	Character zombie;
        	// Change starts: ZOMBIESTORYENEMIES
        	if (currentLevel.parent.isZombie()){
                zombie = (new ZombieFast(new Sprite(new Texture("player02.png")),
                        new Vector2(getX() + random, getY() + random), currentLevel, parent));

            } else {
                zombie = (new ZombieFast(new Sprite(new Texture("FastZombie.png")),
                        new Vector2(getX() + random, getY() + random), currentLevel, parent));
            }
        	// Change ends: ZOMBIESTORYENEMIES
            boolean collides = false;

            for (Character otherZombie : currentLevel.aliveZombies) {
                if (zombie.collidesWith(otherZombie, false)) {
                    collides = true;
                }
            }

            if (!collides) {
            	currentLevel.aliveZombies.add(zombie);
                currentLevel.zombiesRemaining++;
            	zombiesToSpawn--;
            }
        }
        
        if (spawningZombies && spawnTimer > 2) {
        	spawningZombies = false;
        }

        // Change starts: CUREEFFECT
        /* If cure powerup is active:
         * move away and face away from the player, else:
         * move to and face towards the player
         */
        // Vector2.scl scales the vector
        if (this.isRunning()){
            velocity = getDirNormVector(player.getCenter()).rotate(180).scl(speed);
            direction = getDirectionTo(player.getCenter()) + Math.PI;
        } else {
            velocity = getDirNormVector(player.getCenter()).scl(speed);
            direction = getDirectionTo(player.getCenter());
        }
        // Change ends: CUREEFFECT

        if (health <= 0) {
            currentLevel.zombiesRemaining--;
            currentLevel.aliveZombies.remove(this);
            // Removed disposal of texture to prevent texture glitch
        }
    }

    // Change starts: BOSSFUNCTIONALITYFIX
    @Override
    public void takeDamage(int dmg){
        health -= dmg;
    }
    // Change ends: BOSSFUNCTIONALITYFIX
}
