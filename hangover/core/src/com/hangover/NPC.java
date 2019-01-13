package com.hangover;

public class NPC extends Entity {

	//Decides if entity is friendly
	boolean friendly;
	
	//Initialises NPC
	public NPC() {
		super();
		friendly = false;
	}
	
	//Initialises NPC and sets animations depending on name
	public NPC(String string, ResourceManager r) {
		super(string, r);
		friendly = false;
	}

	//Sets if entity is friendly
	public void setFriendly(boolean f) {
		friendly = f;
	}
	
	//Gets if entity is friendly
	public boolean getFriendly() {
		return friendly;
	}
	
	//Algorithm that makes the NPC look at the player and follow the player based on x and y coordinates.
	public void simpleChasePlayer(float px, float py) {
		float xdiff = px - getX();
		float ydiff = py - getY();
		
		if(!(xdiff == 0 && ydiff == 0)) {
			setMoving(true);
			double a = 0;
			if(ydiff == 0) {
				a = Math.PI/2;
				if(xdiff < 0) {
					a += Math.PI;
				}
			}
			else {
				if(ydiff < 0 && xdiff > 0) {
					a = Math.PI + Math.atan(xdiff/ydiff);
				}
				else if(ydiff >= 0  && xdiff > 0) {
					a = Math.atan(xdiff/ydiff);
				}
				else if(ydiff < 0 && xdiff < 0) {
					a = Math.PI + Math.atan(xdiff/ydiff);
				}
				else if(ydiff >= 0 && xdiff < 0) {
					a = 2 * Math.PI + Math.atan(xdiff/ydiff);
				}
			}
			setAngle((float)a);
		}
		else {
			setAngle(0);
			setMoving(false);
		}	
	}
	
}
