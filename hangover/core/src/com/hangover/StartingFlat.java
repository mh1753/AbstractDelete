package com.hangover;

import com.badlogic.gdx.math.Vector2;

public class StartingFlat extends PlayScreen {

	public StartingFlat(AKillerHangover g, ResourceManager r) {
		super(g, r);
	}

	@Override
	public void create() {
		super.create("player1", new Vector2(128, 192), "assets//maps//Flat.tmx");
		
		entityStage.addActor(c);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
	}

}
