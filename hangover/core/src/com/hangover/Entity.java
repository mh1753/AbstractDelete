package com.hangover;

public class Entity extends MovingActor{
	
	float speed;
	int maxHealth;
	int currentHealth;
	float angle;
	String type;
	
	public Entity() {
		super();
		speed = 0;
		maxHealth = 0;
		currentHealth = 0;
		type = "Default";
		angle = 0;
	}
	
	public Entity(String t, ResourceManager r) {
		super();
		type = t;
		try {
			clone(r.getEntity(t));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void setSpeed(float s) {
		speed = s;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setAngle(float a) {
		angle = a;
		setRotation((float) (a * 180/Math.PI));
	}
	
	public float getAngle() {
		return angle;
	}
	
	public void setMaxHealth(int h) {
		maxHealth = h;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void addHealth(int h) {
		if(currentHealth + h > maxHealth) {
			currentHealth = maxHealth;
		}
		else if(h < 0) {
			takeHealth(-h);
		}
		else {
			currentHealth += h;
		}
	}
	
	public void takeHealth(int h) {
		if(currentHealth - h < 0) {
			currentHealth = 0;
		}
		else if(h < 0) {
			addHealth(-h);
		}
		else {
			currentHealth -= h;
		}
	}
	
	public int getHealth() {
		return currentHealth;
	}
	
	public void setType(String t) {
		type = t;
	}
	
	public String getType() {
		return type;
	}
	
	public void clone(Entity e) {
		super.clone(e);
		speed = e.speed;
		currentHealth = e.maxHealth;
		maxHealth = e.maxHealth;
	}
	
	public void setVelocityFromAngle() {
		setVelX((float) (speed * Math.sin(angle)));
		setVelY((float) (speed * Math.cos(angle)));
	}
	
	public void act(float dt) {
		super.act(dt);
		setVelocityFromAngle();
	}
	
}
