package com.hangover;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
public class AKillerHangover extends Game {
	
	public int points = 0;
	
	public ArrayList<Screen> screens = new ArrayList<Screen>();
	
	public void create () {
		Pixmap pm = new Pixmap(Gdx.files.internal("assets//cursor.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 8, 8));
		pm.dispose();
		GroupLogo t = new GroupLogo(this, new ResourceManager());
		setScreen(t, true);
	}
	
	public void setScreen(Screen s, boolean overwrite) {
		if(!overwrite) {
		boolean creating = false;
			for(Screen sc: screens) {
				if (sc.getClass().equals(s.getClass())){
					s = sc;
					creating = true;
				}
			}
			if(!creating) {
				screens.add(s);
			}
		}
		else {
			for(Screen sc: screens) {
				if(sc.getClass().equals(s.getClass())) {
					sc = s;
				}
			}
		}
		super.setScreen(s);
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
