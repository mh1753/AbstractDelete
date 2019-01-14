package com.hangover;

import com.badlogic.gdx.math.Vector2;

public class StartingFlat extends PlayScreen {

	public StartingFlat(AKillerHangover g, ResourceManager r) {
		super(g, r);
	}

	@Override
	public void create() {
		//Sets player type to player1, starts player in their bedroom
		super.create("player1", new Vector2(128, 192), "maps//Flat.tmx");
		
		//Area tries to maintain a total of 5 zombies
		maxEnemyNo = 5;
		
		entityStage.addActor(c);
	}

	@Override
	public void enterDoor() {
		
	}

}
