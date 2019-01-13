package com.hangover;



import com.badlogic.gdx.math.Vector2;

public class Goodricke extends PlayScreen {

	public Goodricke(AKillerHangover g, ResourceManager r) {
		super(g, r);
	}

	@Override
	public void create() {
		//Starts player with player1 type at the appropriate location
		super.create("player1", new Vector2(1520, 64), "assets//maps//Goodricke.tmx");
		
		//Game tries to maintain 12 zombies
		maxEnemyNo = 12;
		
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
	
	//calls PlayScreen's update
	@Override
	public void update(float dt) {
		super.update(dt);
	}

	//Loads next location if player goes through door
	@Override
	public void enterDoor() {
		Nucleus nucleus = new Nucleus(g, r);
		g.setScreen(nucleus, false);
		
	}

}
