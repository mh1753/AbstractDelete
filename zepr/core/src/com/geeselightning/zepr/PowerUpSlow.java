//Change starts ; Reference SLOWPOWERUP
package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;

public class PowerUpSlow extends PowerUp {

    public float timeRemaining = Constant.SLOWTIME;

    public PowerUpSlow(Level currentLevel) {
        super(2, new Texture("slow.png"), currentLevel);
    }

    @Override
    public void activate() {
        super.activate();
        super.player.speed -= Constant.SLOW;
        this.getTexture().dispose();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        super.player.speed += Constant.SLOW;
    }

    @Override
    public void update(float delta) {
        if (active) {
            timeRemaining -= delta;
        }
        if (timeRemaining < 0) {
            deactivate();
        }
    }
}
//Change ends ; SLOWPOWERUP