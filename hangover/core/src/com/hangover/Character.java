package com.hangover;

public class Character extends Entity {
	
	public Character() {
		super();
	}
	
	public Character(String name, ResourceManager r) {
		super(name, r);
	}
	
	public void setAngle(float a) {
		angle = a;
	}
	
	public void updatePlayerRot(float mx, float my) {
		float xdiff = mx - getX() - 32;
		float ydiff = my - getY() - 32;
		
		double a = Math.atan2(ydiff,xdiff);
		float angle = (float)a;
		angle *= (180/Math.PI);
		angle -= 90;
		setRotation(angle);
	}
	
}
