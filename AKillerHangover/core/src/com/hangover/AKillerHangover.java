package com.hangover;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
public class AKillerHangover extends Game {
	
	//Allows points to be accessed from every screen
	public int points = 0;
	
	//Stores the type of character being used
	public String playerType = "";
	
	//Stores spawn rate of zombies in all levels
	public int spawnrate = 50000;
	
	//Allows health to be constant between screens
	public HealthBar playerHealth;
	
	//Stores screens so there's no need to keep re-initialising them
	public ArrayList<Screen> screens = new ArrayList<Screen>();
	
	public void create () {
		playerHealth = null;
		
		//sets cursor
		System.out.println(Gdx.files.internal("cursor.png"));
		Pixmap pm = new Pixmap(Gdx.files.internal("cursor.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 8, 8));
		pm.dispose();
		
		//sets screen size
		Gdx.graphics.setWindowedMode(1024, 640);
		
		//Starts game by showing the logo
		GroupLogo t = new GroupLogo(this, new ResourceManager());
		setScreen(t, true);
	}
	
	//Method for setting screen that allows for the storing of screens in the arraylist. Also decides whether to overwrite or not
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
	
	//Adds to points
	public void addPoints(int p) {
		points += p;
	}
	
	//Takes from points
	public void takePoints(int p) {
		if(points - p < 0) {
			points = 0;
		}
		else {
			points -= p;
		}
	}
	
	public void checkWin() {
		for(Screen sc: screens) {
			if(sc.getClass() == Nucleus.class && points >= 2000) {
				setScreen(new WinScreen(this, new ResourceManager()), true);
			}
		}
	}

}
