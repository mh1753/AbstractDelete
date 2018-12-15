package com.hangover;

import com.badlogic.gdx.math.Vector2;

public class MovingActor extends AnimatedActor {
	
	//all variables required for movement
	private float velX = 0;
	private float velY = 0;
	private float accX = 0;
	private float accY = 0;
	private float vrot = 0;
	
	boolean moving = false;
	
	public MovingActor() {
		super();
	}
	
	public void setMoving(boolean m) {
		moving = m;
	}
	
	public boolean getMoving() {
		return moving;
	}
	
	public void setVelocity(float vx, float vy) {
		velX = vx;
		velY = vy;
	}
	
	public Vector2 getVelocity() {
		return new Vector2(velX, velY);
		
	}
	
	public void setVelX(float vx) {
		velX = vx;
	}
	
	public void setVelY(float vy) {
		velY = vy;
	}
	
	public void setAcceleration(float ax, float ay) {
		accX = ax;
		accY = ay;
	}
	
	public Vector2 getAcceleration() {
		return new Vector2(accY, accY);
	}
	
	public void setAngularVel(float a) {
		vrot = a;
	}
	
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
		}
	}
}
