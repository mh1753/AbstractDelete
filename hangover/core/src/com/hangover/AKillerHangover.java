package com.hangover;

import com.badlogic.gdx.Game;
public class AKillerHangover extends Game {
	public void create () {
		
		TestScreen t = new TestScreen(this, new ResourceManager());
		setScreen(t);
	}

}
