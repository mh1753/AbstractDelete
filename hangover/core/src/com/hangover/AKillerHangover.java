package com.hangover;

import com.badlogic.gdx.Game;
import com.tests.JUnitTester;
public class AKillerHangover extends Game {
	public void create () {
		JUnitTester t = new JUnitTester(this, new ResourceManager());
		setScreen(t);
	}

}
