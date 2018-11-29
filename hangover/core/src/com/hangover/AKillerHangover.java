package com.hangover;

import com.badlogic.gdx.Game;
import com.tests.JUnitTester;
public class AKillerHangover extends Game {
	public void create () {
		FirstAreaDemo t = new FirstAreaDemo(this, new ResourceManager());
		setScreen(t);
	}

}
