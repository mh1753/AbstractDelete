package com.hangover;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HealthBar extends Actor {

	//Information required to draw healthbar
	public int maxHealth;
	public int currentHealth;
	
	//Draws the healthbar
	protected ShapeRenderer s;
	
	public HealthBar(int maxHealth, int currentHealth) {
		
		//sets the stats
		this.maxHealth = maxHealth;
		this.currentHealth = currentHealth;
		
		//initialises ShapeRenderer
		s = new ShapeRenderer();
		s.setAutoShapeType(true);
	}
	
	@Override
	public void draw(Batch b, float a) {
		//ends batch so shaperenderer can run
		b.end();
		
		//starts shaperenderer
		s.begin();
		
		//first draws red section of the healthbar
		s.set(ShapeType.Filled);
		s.setColor(Color.RED);
		s.rect(40 + (currentHealth) * 2, 600, (maxHealth- currentHealth) * 2, 10);
		
		//Then draws green section of healthbar
		s.setColor(Color.GREEN);
		s.rect(40, 600, (currentHealth) * 2, 10);
		
		//stops shaperenderer
		s.end();
		
		//restarts the batch
		b.begin();
	}
	
	//update current health
	public void updateHealth(int ch) {
		currentHealth = ch;
	}
	
}
