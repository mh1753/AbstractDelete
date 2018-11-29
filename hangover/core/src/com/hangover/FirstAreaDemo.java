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
		super.create("badlogic", new Vector2(160, 640 - 160));
		try {
			FileHandle fh = Gdx.files.internal("assets//FirstAreaDemo.txt");
			BufferedReader f = new BufferedReader(fh.reader());
			String line = f.readLine();
			int lineNo = 0;
			String[] tiles = line.split(" ");
			while(line != null) {
				for(int i = 0; i < tiles.length; i++) {
					ImageActor block = new ImageActor();
					tiles[i] = tiles[i].replace("\n", "");
					System.out.print(tiles[i]);
					if(tiles[i].equals("t")) {
						block.clone(r.getImageActor("flatt"));
					}
					//else if(tiles[i].equals("tr")) {
					//	block.clone(r.getImageActor("flattr"));
					//}
					//else if(tiles[i].equals("r")) {
					//	block.clone(r.getImageActor("flatr"));
					//}
					//else if(tiles[i].equals("br")) {
					//	block.clone(r.getImageActor("flatbr"));
					//}
					//else if(tiles[i].equals("b")) {
					//	block.clone(r.getImageActor("flatb"));
					//}
					//else if(tiles[i].equals("bl")) {
					//	block.clone(r.getImageActor("flatbl"));
					//}
					//else if(tiles[i].equals("l")) {
					//	block.clone(r.getImageActor("flatl"));
					//}
					//else if(tiles[i].equals("tl")) {
					//	block.clone(r.getImageActor("flattl"));
					//}
					else if(tiles[i].equals("m")) {
						block.clone(r.getImageActor("flatm"));
					}
					block.setOrigin();
					block.setX(64 * (i -1) + 32);
					block.setY(640 - (64 * (lineNo) + 32));
					background.add(block);
				}
				lineNo += 1;
				line = f.readLine();
				if(line != null) {
					tiles = line.split(" ");
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
