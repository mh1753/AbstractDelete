package com.geeselightning.zepr;

import com.badlogic.gdx.Game;

public class Zepr extends Game {

	private LoadingScreen loadingScreen;
	private MenuScreen menuScreen;
	private Level level;
	private SelectLevelScreen selectLevelScreen;

	//Change starts ; Reference POINTSVAR
	//Stores number of points earned by player
	private float points = 0;
	//Change ends ; Reference POINTSVAR

	// The progress is the integer representing the last level completed. i.e. 3 for Town
	public int progress = 3;

	public final static int MENU = 0;
	public final static int SELECT = 2;
	public final static int TOWN = 3;
	public final static int HALIFAX = 4;
	public final static int COURTYARD = 5;
	//Change starts ; Reference LEVELPROGRESS
	public final static int BUSSTOP = 6;
	public final static int COMPUTERSCIENCE = 7;
	public final static int GLASSHOUSE = 8;
	public final static int COMPLETE = 9;
	//Change ends ; Reference LEVELPROGRESS


	public void changeScreen(int screen) {
		switch(screen) {
			case MENU:
				if (menuScreen == null) menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
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
				//Change starts ; Reference LEVELLOAD
			case BUSSTOP:
				level = new BusStopLevel(this);
				this.setScreen(level);
				break;
			case COMPUTERSCIENCE:
				level = new ComputerScienceLevel(this);
				this.setScreen(level);
				break;
			case GLASSHOUSE:
				level = new GlasshouseLevel(this);
				this.setScreen(level);
				break;
				//Change ends ; Reference LEVELLOAD
		}
	}

	//Change starts ; Reference IMPLEMENTPOINTS
	//Sets points to p
	public void setPoints(float p) { points = p;}

	//Increments points by p
	public void addPoints(float p){
		points += p;
	}

	//Returns current number of points
	public int getPoints(){
		return Math.round(points);
	}
	//Change ends ; Reference IMPLEMENTPOINTS

	@Override
	public void create() {
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}
}