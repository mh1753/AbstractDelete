/**
 * Added by Shaun of the Devs to meet the requirement of different zombie types
 */
package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ZombieFast extends Zombie {

    int attackDamage = Constant.ZOMBIEFASTDMG;
    public int hitRange = Constant.ZOMBIERANGE;
    public final float hitCooldown = Constant.ZOMBIEHITCOOLDOWN;
    private Zepr parent;

    public ZombieFast(Sprite sprite, Vector2 zombieSpawn, Level currentLevel, Zepr parent) {
        super(sprite, zombieSpawn, currentLevel, parent);
        this.parent = parent;
        if (parent.isZombie()){
            this.humanMain = new Texture("player02.png");
            this.humanAttack = new Texture("player02_attack.png");
        }
        this.speed = Constant.ZOMBIEFASTSPEED;
        this.maxHealth = Constant.ZOMBIEFASTMAXHP;
        this.health = maxHealth;
    }

    @Override
    public void attack(Character player, float delta) {
        if (canHitGlobal(player, hitRange) && hitRefresh > hitCooldown && !isRunning()) {
            //Change starts: FASTZOMBIEAVOIDTIMERSET
            currentLevel.avoidTimer = Constant.AVOIDTIMER;
            //Change ends: FASTZOMBIEAVOIDTIMERSET
            player.takeDamage(attackDamage);
            if (parent.isZombie()){
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

}