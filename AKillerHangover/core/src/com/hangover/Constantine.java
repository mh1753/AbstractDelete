package com.hangover;

import com.badlogic.gdx.math.Vector2;

public class Constantine extends PlayScreen {

	public Constantine(AKillerHangover g, ResourceManager r) {
		super(g, r);
	}

	@Override
	public void enterDoor() {
		
	}

	@Override
	public void create() {
		//Starts player at the appropriate location
			super.create(g.playerType, new Vector2(64, 21 * 32), "maps//Constantine.tmx");
				
			//Game tries to maintain 20 zombies
			maxEnemyNo = 20;
				
			//Adds background imageActors to background if there are any
			for(ImageActor i: background) {
				backStage.addActor(i);
			}
				
			//Adds enemies to scene.
			for(NPC n : enemies) {
				entityStage.addActor(n);
			}
				
			//Adds character to scene
			entityStage.addActor(c);
		
	}

}
