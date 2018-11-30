package com.hangover;

import java.io.BufferedReader;
import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

public class FirstAreaDemo extends PlayScreen {

	public FirstAreaDemo(Game g, ResourceManager r) {
		super(g, r);
	}

	@Override
	public void create() {
		super.create("zombie", new Vector2(0, 0), "assets//maps//OtherTest.tmx");
		
		
		
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
