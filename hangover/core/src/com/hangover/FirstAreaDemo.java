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
		super.create("playername", new Vector2(10, 10));
		try {
			FileHandle fh = Gdx.files.internal("assets//FirstAreaDemo.txt");
			BufferedReader f = new BufferedReader(fh.reader());
			String line = f.readLine();
			int lineNo = 0;
			String[] tiles = line.split(" ");
			while(line != null) {
				for(int i = 0; i < tiles.length; i++) {
					ImageActor block = new ImageActor();
					if(tiles[i] == "0") {
						
					}
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		for(ImageActor i: background) {
			backStage.addActor(i);
		}
		entityStage.addActor(c);
		
	}
	
	@Override
	public void update(float dt) {
		
	}

}
