package com.hangover;



import com.badlogic.gdx.math.Vector2;

public class Goodricke extends PlayScreen {

	public Goodricke(AKillerHangover g, ResourceManager r) {
		super(g, r);
	}

	@Override
	public void create() {
		super.create("player1", new Vector2(1520, 64), "assets//maps//Goodricke.tmx");
		
		maxEnemyNo = 12;
		
		
		for(ImageActor i: background) {
			backStage.addActor(i);
		}
		for(NPC n : enemies) {
			entityStage.addActor(n);
		}
		entityStage.addActor(c);
		
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
	}

	@Override
	public void enterDoor() {
		Nucleus nucleus = new Nucleus(g, r);
		g.setScreen(nucleus, false);
		
	}

}
