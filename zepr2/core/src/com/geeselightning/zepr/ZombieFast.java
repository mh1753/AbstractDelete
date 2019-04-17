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
        if (parent.isZombie()){
            this.humanMain = new Texture("player02.png");
            this.humanAttack = new Texture("player02_attack.png");
        }
        this.speed = Constant.ZOMBIEFASTSPEED;
        this.maxHealth = Constant.ZOMBIEFASTMAXHP;
        this.health = maxHealth;
    }

}