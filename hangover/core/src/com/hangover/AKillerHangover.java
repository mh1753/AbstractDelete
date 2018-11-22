package com.hangover;

import com.badlogic.gdx.Game;
public class AKillerHangover extends Game {
	public void create () {
		StartScreen ss = new StartScreen(this);
		setScreen(ss);
	}

}
