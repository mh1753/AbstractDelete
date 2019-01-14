package com.hangover;

import com.badlogic.gdx.math.Vector2;

public class MovingActor extends AnimatedActor {
	
	//all variables required for movement
	private float velX = 0;
	private float velY = 0;
	private float accX = 0;
	private float accY = 0;
	private float vrot = 0;
	
	//Is actor allowed to move?
	boolean moving = false;
	
	public MovingActor() {
		super();
	}
	
	//Sets if actor allowed to move
	public void setMoving(boolean m) {
		moving = m;
	}
	
	//Gets if actor allowed to move
	public boolean getMoving() {
		return moving;
	}
	
	//Sets velocity
	public void setVelocity(float vx, float vy) {
		velX = vx;
		velY = vy;
	}
	
	//Gets velocity
	public Vector2 getVelocity() {
		return new Vector2(velX, velY);
		
	}
	
	//Sets x velocity
	public void setVelX(float vx) {
		velX = vx;
	}
	
	//Sets y velocity
	public void setVelY(float vy) {
		velY = vy;
	}
	
	//Sets acceleration
	public void setAcceleration(float ax, float ay) {
		accX = ax;
		accY = ay;
	}
	
	//Gets acceleration
	public Vector2 getAcceleration() {
		return new Vector2(accY, accY);
	}
	
	//Sets angular velocity
	public void setAngularVel(float a) {
		vrot = a;
	}
	
	//Gets angular velocity
	public float getAngularVel() {
		return vrot;
	}
	
	
	//calculates movement
	public void act(float dt) {
		super.act(dt);
		if(moving && isLiving()) {
			setX(getX() + (velX * dt));
			setY(getY() + (velY*dt));
			velX += (accX*dt);
			velY += (accY*dt);
			setRotation(getRotation() + (vrot * dt));
			if(boundingPolygon != null) {
				boundingPolygon.setPosition(getX(), getY());
				boundingPolygon.setRotation(getRotation());
			}
		}
	}
}
