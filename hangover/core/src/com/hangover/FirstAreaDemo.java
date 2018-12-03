package com.hangover;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;

public class FirstAreaDemo extends PlayScreen {

	public FirstAreaDemo(Game g, ResourceManager r) {
		super(g, r);
	}

	@Override
	public void create() {
		super.create("player1", new Vector2(0, 0), "assets//maps//OtherTest.tmx");
		
		
		
		for(ImageActor i: background) {
			backStage.addActor(i);
		}
		entityStage.addActor(c);
		
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
	}

}
