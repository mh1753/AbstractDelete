/*
 * Added by Abstract Delete to meet new requirements
 */
package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;

public class PowerUpCure extends PowerUp{

    private Texture zombieHuman;
    private Texture zombieFastHuman;
    private Texture flamingZombieHuman;
    Zepr parent;

    public PowerUpCure(Level currentLevel,Zepr parent) {
        super(6, new Texture("immunity.png"), currentLevel);
        timeRemaining = 5;
        this.parent = parent;
        this.name = "Cure!";
    }

    @Override
    public void activate(){
        // Change starts: CUREEFFECT
        super.activate();
        // Change starts: CUREZOMBIESTORY
        // If the player is a zombie, restores them to human form
        if (parent.isZombie()){
            player.setType(parent.getLastKnownCharacter());
            parent.setZombie(false);
            player.respawn(player.getCenter(), currentLevel);
        }
        // Change ends: CUREZOMBIESTORY
        // The player will not be damaged by fleeing humans
        super.player.isImmune = true;
        // Load textures for human zombies
        zombieHuman = new Texture("player01.png");
        flamingZombieHuman = new Texture("player03.png");
        zombieFastHuman = new Texture("player02.png");
        // Make the current wave spawn no more zombies. Prevents lag and easier functionality
        currentLevel.zombiesToSpawn = 0;
        currentLevel.zombiesRemaining = currentLevel.aliveZombies.size();
        // Set zombie textures to their respective human textures
        for (Character zombie : currentLevel.aliveZombies) {
            if (!parent.isZombie()) {
                if (zombie instanceof FlamingZombie) {
                    zombie.setTexture(flamingZombieHuman);
                } else if (zombie instanceof ZombieFast) {
                    zombie.setTexture(zombieFastHuman);
                } else {
                    zombie.setTexture(zombieHuman);
                }
            }
            // Send humans running
            if (!zombie.isRunning()) {
                zombie.toggleRunning();
            }
        }
        // Change ends: CUREEFFECT
        this.getTexture().dispose();
    }

    @Override
    public void deactivate(){
        super.deactivate();
        // Change starts: CUREEFFECT
        super.player.isImmune = false;
        // Finish off the current wave
        for (Character zombie : currentLevel.aliveZombies){
            zombie.setHealth(0);
        }
        // Change ends: CUREEFFECT
        zombieHuman.dispose();
        zombieFastHuman.dispose();
        flamingZombieHuman.dispose();
    }

    @Override
    public void update(float delta){
        if (timeRemaining < 0){
            deactivate();
        } else if (active) {
            timeRemaining -= delta;
        }
    }

}
