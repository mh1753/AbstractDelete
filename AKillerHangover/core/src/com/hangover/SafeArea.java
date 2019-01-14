package com.hangover;

public abstract class SafeArea extends PlayScreen {
	
	//Stores whether safe area has been visited before
	public boolean visited = false;
	
	//Number of points given for arriving at safe area for the first time
	public int points = 0;

	public SafeArea(AKillerHangover g, ResourceManager r) {
		super(g, r);
		
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		
		//if area not visited, give points and make zombies spawn quicker
		if(!visited) {
			visited = true;
			spawnRate *= 0.8;
			g.addPoints(points);
		}
	}

}
