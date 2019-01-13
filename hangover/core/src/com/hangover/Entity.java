package com.hangover;

public class Entity extends MovingActor{
	
	//Entity stats
	float speed;
	int maxHealth;
	int currentHealth;
	float angle;
	String type;
	
	
	//Initialises Entity
	public Entity() {
		super();
		speed = 0;
		maxHealth = 0;
		currentHealth = 0;
		type = "Default";
		angle = 0;
	}
	
	//Initialises entity and loads animations based on name provided
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
	
	//Sets entity speed
	public void setSpeed(float s) {
		speed = s;
	}
	
	//gets entity speed
	public float getSpeed() {
		return speed;
	}

	//Sets angle entity is facing in
	public void setAngle(float a) {
		angle = a;
		setRotation((float) -(a * 180/Math.PI));
	}
	
	//Gets angle entity is facing
	public float getAngle() {
		return angle;
	}
	
	//Sets entity max health
	public void setMaxHealth(int h) {
		maxHealth = h;
		currentHealth = h;
	}
	
	//Gets entity max health
	public int getMaxHealth() {
		return maxHealth;
	}
	
	//Adds health up to maximum. Calls takeHealth if parameter negative
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
	
	//Takes health down to 0. calls addHealth if parameter negative
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
	
	//Gets entity current health
	public int getHealth() {
		return currentHealth;
	}
	
	//Sets the entity's name
	public void setType(String t) {
		type = t;
	}
	
	//Gets the entity's name
	public String getType() {
		return type;
	}
	
	//Makes this entity identical to given entity
	public void clone(Entity e) {
		super.clone(e);
		speed = e.speed;
		currentHealth = e.maxHealth;
		maxHealth = e.maxHealth;
		type = e.getType();
	}
	
	//Sets x and y velocities based on angle faced and speed
	public void setVelocityFromAngle() {
		setVelX((float) (speed * Math.sin(angle)));
		setVelY((float) (speed * Math.cos(angle)));
	}
	
	//updates the animation to walking if moving or default if not
	public void updateAnimation() {
		if(moving) {
			setAnim(getType().toLowerCase() + "walking");
		}
		else {
			setAnim(getType().toLowerCase());
		}
	}
	
	//Makes sure that entity stops moving if dead and updates the velocity
	public void act(float dt) {
		if(currentHealth <= 0) {
			setMoving(false);
			setLiving(false);
		}
		super.act(dt);
		setVelocityFromAngle();
	}
	
}
