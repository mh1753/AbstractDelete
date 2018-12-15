package com.hangover;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
public class AKillerHangover extends Game {
	
	public int points = 0;
	
	public void create () {
		Pixmap pm = new Pixmap(Gdx.files.internal("assets//cursor.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 8, 8));
		pm.dispose();
		FirstAreaDemo t = new FirstAreaDemo(this, new ResourceManager());
		setScreen(t);
	}
	
	public void addPoints(int p) {
		points += p;
	}
	
	public void takePoints(int p) {
		if(points - p < 0) {
			points = 0;
		}
		else {
			points -= p;
		}
	}

}
