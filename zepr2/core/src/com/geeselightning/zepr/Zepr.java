package com.geeselightning.zepr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.MathUtils;

public class Zepr extends Game {

	private LoadingScreen loadingScreen;
	private MenuScreen menuScreen;
	private Level level;
	private BonusLevel bonusLevel;
	private SelectLevelScreen selectLevelScreen;

	//Change starts: POINTSINIT
	private float points = 5000;
	//Change ends: POINTSINIT

	//Change starts: ZOMBIESTORY
	private boolean isZombie = false;
	//Change ends: ZOMBIESTORY

	//Change starts: CUREZOMBIESTORY
	private String lastKnownCharacter;
	//Change ends: CUREZOMBIESTORY

	// The progress is the integer representing the last level completed. i.e. 3 for Town
	private int progress = 9;

	//Change starts: INITCURRENTSCREEN
	public int currentScreen;
	//Change ends: INITCURRENTSCREEN

	public final static int MENU = 0;
	public final static int BONUS = 1;
	public final static int SELECT = 2;
	public final static int TOWN = 3;
	public final static int HALIFAX = 4;
	public final static int COURTYARD = 5;
	public final static int LIBRARY = 6;
	public final static int PHYSICS = 7;
	public final static int CENTRALHALL = 8;
	public final static int COMPLETE = 9;

	//Change starts: GETPOINTS
	public int getPoints(){ return MathUtils.floor(points);}
	//Change ends: GETPOINTS

	//Change starts: ADDPOINTS
	public void addPoints(float p){
		points += p;
	}
	//Change ends: ADDPOINTS

	//Change starts: ZEROPOINTS
	public void zeroPoints(){
		points = 0;
	}
	//Change ends: ZEROPOINTS

	public void changeScreen(int screen) {
		//Change starts: STORESCREEN
		currentScreen = screen;
		//Change ends: STORESCREEN
		switch(screen) {
			case MENU:
				if (menuScreen == null) menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
				break;
			case BONUS:
				bonusLevel = new BonusLevel(this);
				this.setScreen(bonusLevel);
				break;
			case SELECT:
				selectLevelScreen = new SelectLevelScreen(this);
				this.setScreen(selectLevelScreen);
				break;
			case TOWN:
				level = new TownLevel(this);
				this.setScreen(level);
				break;
			case HALIFAX:
				level = new HalifaxLevel(this);
				this.setScreen(level);
				break;
			case COURTYARD:
				level = new CourtyardLevel(this);
				this.setScreen(level);
				break;
			case LIBRARY:
				level = new LibraryLevel(this);
				this.setScreen(level);
				break;
			case PHYSICS:
				level = new PhysicsLevel(this);
				this.setScreen(level);
				break;
			case CENTRALHALL:
				level = new CentralHallLevel(this);
				this.setScreen(level);
				break;
		}
	}

	//Change starts: ZOMBIESTORY
	public boolean isZombie(){
		return isZombie;
	}

	public void setZombie(boolean b){
		isZombie = b;
	}
	//Change ends: ZOMBIESTORY

	//Change starts: PROGRESSFUNCS
	public int getProgress(){
		return progress;
	}

	public void setProgress(int p){
		progress = p;
	}

	public void incProgress(){
		progress += 1;
	}

	public void decProgress(){
		progress -= 1;
	}
	//Change ends: PROGRESSFUNCS

	//Change starts: CUREZOMBIESTORYFUNCS
	public String getLastKnownCharacter(){
		return lastKnownCharacter;
	}

	public void setLastKnownCharacter(String lkc){
		lastKnownCharacter = lkc;
	}
	//Change ends: CUREZOMBIESTORYFUNCS

	@Override
	public void create() {
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}
}