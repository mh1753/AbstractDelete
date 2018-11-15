package com.hangover;

public class MovingActor extends AnimatedActor {
	
	private float velX = 0;
	private float velY = 0;
	private float accX = 0;
	private float accY = 0;
	private float vrot = 0;
	
	public MovingActor() {
		super();
	}
	
	public MovingActor(String url, float fr, float fw) {
		super(url, fr, fw);
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
	
	public void act(float dt) {
		super.act(dt);
		setX(getX() + (velX * dt));
		setY(getY() + (velY*dt));
		velX += (accX*dt);
		velY += (accY*dt);
		setRotation(getRotation() + (vrot * dt));
		bounds.setX(getX());
		bounds.setY(getY());
		
	}
}
