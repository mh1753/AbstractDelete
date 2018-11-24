package com.hangover;

public class MovingActor extends AnimatedActor {
	
	//all variables required for movement
	private float velX = 0;
	private float velY = 0;
	private float accX = 0;
	private float accY = 0;
	private float vrot = 0;
	
	boolean moving = true;
	
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
	
	public void setAngularVel(float a) {
		vrot = a;
	}
	
	
	//calculates movement
	public void act(float dt) {
		super.act(dt);
		if(moving) {
			setX(getX() + (velX * dt));
			setY(getY() + (velY*dt));
			velX += (accX*dt);
			velY += (accY*dt);
			setRotation(getRotation() + (vrot * dt));
			bounds.setX(getX());
			bounds.setY(getY());
		}
	}
}
