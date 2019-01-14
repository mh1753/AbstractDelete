package com.hangover;

public class Character extends Entity {
	
	//Initialises Character
	public Character() {
		super();
	}
	
	//Initialises Character and loads animations for character using name provided
	public Character(String name, ResourceManager r) {
		super(name, r);
	}
	
	//Sets angle that player is facing in
	public void setAngle(float a) {
		angle = a;
	}
	
	//Takes in mouse coordinates and has player face the mouse
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
