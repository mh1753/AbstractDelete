package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;

public class PowerUpCure extends PowerUp{

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
        for (Character zombie : currentLevel.aliveZombies){
            if (!parent.isZombie()) {
                if (zombie instanceof FlamingZombie) {
                    zombie.setTexture(new Texture("player03.png"));
                } else if (zombie instanceof ZombieFast) {
                    zombie.setTexture(new Texture("player02.png"));
                } else {
                    zombie.setTexture(new Texture("player01.png"));
                }
            }
            zombie.toggleRunning();
        }
        if (parent.isZombie()){
            player.setType(parent.getLastKnownCharacter());
            parent.setZombie(false);
            player.respawn(player.getCenter(), currentLevel);
        }
        super.player.isImmune = true;
        this.getTexture().dispose();
    }

    @Override
    public void deactivate(){
        super.deactivate();
        super.player.isImmune = false;
        for (Character zombie : currentLevel.aliveZombies){
            zombie.setHealth(0);
        }
    }

    @Override
    public void update(float delta){
        if (timeRemaining < 0){
            deactivate();
        } else if (active){
            timeRemaining -= delta;
        }
    }

}
