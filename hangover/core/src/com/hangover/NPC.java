package com.hangover;

public class NPC extends Entity {

	boolean friendly;
	
	public NPC() {
		super();
		friendly = true;
	}
	
	public void setFriendly(boolean f) {
		friendly = f;
	}
	
	public boolean getFriendly() {
		return friendly;
	}
	
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
