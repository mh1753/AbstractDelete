package com.hangover;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
public class AKillerHangover extends Game {
	public void create () {
		Pixmap pm = new Pixmap(Gdx.files.internal("assets//cursor.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 8, 8));
		pm.dispose();
		FirstAreaDemo t = new FirstAreaDemo(this, new ResourceManager());
		setScreen(t);
	}

}
