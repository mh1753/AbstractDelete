package com.hangover;

public abstract class SafeArea extends PlayScreen {
	
	public boolean visited = false;
	
	public int points = 0;

	public SafeArea(AKillerHangover g, ResourceManager r) {
		super(g, r);
		
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		if(!visited) {
			visited = true;
			spawnRate *= 0.8;
			g.addPoints(points);
		}
	}

}
