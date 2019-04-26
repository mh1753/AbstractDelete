/**
 * Added by Shaun of the Devs to meet the requirement of different zombie types
 */
package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ZombieFast extends Zombie {

    public ZombieFast(Sprite sprite, Vector2 zombieSpawn, Level currentLevel, Zepr parent) {
        super(sprite, zombieSpawn, currentLevel, parent);
        // Change starts: ZOMBIESTORYENEMIES
        // If the player is a zombie, set textures to that of the sporty class
        if (parent.isZombie()){
            this.humanMain = new Texture("player02.png");
            this.humanAttack = new Texture("player02_attack.png");
        }
        // Change ends: ZOMBIESTORYENEMIES
        // Change starts: SAFEAREADIFFICULTYRISE
        // After the safe area, increase difficulty by increasing health and speed
        if (parent.getProgress() > Zepr.LIBRARY){
            this.speed = Constant.ZOMBIEFASTSPEED * Constant.SAFEAREAMULT;
            this.maxHealth = (int) (Constant.ZOMBIEFASTMAXHP * Constant.SAFEAREAMULT);
        } else {
            this.speed = Constant.ZOMBIEFASTSPEED;
            this.maxHealth = Constant.ZOMBIEFASTMAXHP;
        }
        // Change ends: SAFEAREADIFFICULTYRISE
        this.attackDamage = Constant.ZOMBIEFASTDMG;
        this.health = maxHealth;
    }
    // Change starts: ZOMBIEOPTIMIZATION
    // Change ends: ZOMBIEOPTIMIZATION
}