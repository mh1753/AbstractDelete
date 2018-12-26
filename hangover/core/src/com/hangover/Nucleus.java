package com.hangover;

import com.badlogic.gdx.math.Vector2;

public class Nucleus extends SafeArea {

	public Nucleus(AKillerHangover g, ResourceManager r) {
		super(g, r);
	}

	@Override
	public void create() {
		super.create("player1", new Vector2(37 * 32, 27 * 32), "assets//maps//Nucleus.tmx");
		
		points = 1000;
		
		entityStage.addActor(c);
		
	}

	@Override
	public void enterDoor() {
		
		
	}

}
