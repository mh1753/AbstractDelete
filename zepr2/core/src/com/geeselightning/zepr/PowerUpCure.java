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
        super.activate();
        if (parent.isZombie()){
            player.setType(parent.getLastKnownCharacter());
            parent.setZombie(false);
            player.respawn(player.getCenter(), currentLevel);
        }
        super.player.isImmune = true;
        zombieHuman = new Texture("player01.png");
        flamingZombieHuman = new Texture("player03.png");
        zombieFastHuman = new Texture("player02.png");
        currentLevel.zombiesToSpawn = 0;
        currentLevel.zombiesRemaining = currentLevel.aliveZombies.size();
        for (Character zombie : currentLevel.aliveZombies) {
            if (!parent.isZombie()) {
                if (zombie instanceof FlamingZombie) {
                    zombie.getTexture().dispose();
                    zombie.setTexture(flamingZombieHuman);
                } else if (zombie instanceof ZombieFast) {
                    zombie.getTexture().dispose();
                    zombie.setTexture(zombieFastHuman);
                } else {
                    zombie.getTexture().dispose();
                    zombie.setTexture(zombieHuman);
                }
            }
            if (!zombie.isRunning()) {
                zombie.toggleRunning();
            }
        }
        this.getTexture().dispose();
    }

    @Override
    public void deactivate(){
        super.deactivate();
        super.player.isImmune = false;
        for (Character zombie : currentLevel.aliveZombies){
            zombie.setHealth(0);
        }
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
