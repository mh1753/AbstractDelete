package com.hangover;

import com.badlogic.gdx.math.Vector2;

public class Langwith extends PlayScreen {

	public Langwith(AKillerHangover g, ResourceManager r) {
		super(g, r);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enterDoor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() {
		//Starts player at the appropriate location
		super.create(g.playerType, new Vector2(64, 1280), "maps//Langwith.tmx");
		
		//Game tries to maintain 15 zombies
		maxEnemyNo = 15;
		
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
