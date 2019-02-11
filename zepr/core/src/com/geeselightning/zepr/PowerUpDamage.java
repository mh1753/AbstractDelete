//Change starts ; DAMAGEPOWERUP
package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;

public class PowerUpDamage extends PowerUp {

    public float timeRemaining = Constant.DAMAGEUPTIME;

    public PowerUpDamage(Level currentLevel) {
        super(2, new Texture("damage.png"), currentLevel);
    }

    @Override
    public void activate() {
        super.activate();
        super.player.attackDamage += Constant.DAMAGEUP;
        this.getTexture().dispose();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        super.player.attackDamage -= Constant.DAMAGEUP;
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
//Change ends ; DAMAGEPOWERUP