package com.hangover;

import com.badlogic.gdx.math.Vector2;

public class Nucleus extends SafeArea {

	public Nucleus(AKillerHangover g, ResourceManager r) {
		super(g, r);
	}

	@Override
	public void create() {
		//Sets player type as player1, puts the player at the east entrance to the nucleus
		super.create("player1", new Vector2(37 * 32, 27 * 32), "maps//Nucleus.tmx");
		
		//Arriving here is worth 1000 points
		points = 1000;
		
		//Adds player to stage
		entityStage.addActor(c);
		
	}

	@Override
	public void enterDoor() {
		
		
	}

}
