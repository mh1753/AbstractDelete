package com.hangover;

public class Entity extends MovingActor{
	
	float speed;
	
	public Entity() {
		super();
		speed = 0;
	}
	
	public void setSpeed(float s) {
		speed = s;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void clone(Entity e) {
		super.clone(e);
		speed = e.speed;
	}
	
}
