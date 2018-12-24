package com.hangover;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HealthBar extends Actor {

	public int maxHealth;
	public int currentHealth;
	protected ShapeRenderer s;
	
	public HealthBar(int maxHealth, int currentHealth) {
		this.maxHealth = maxHealth;
		this.currentHealth = currentHealth;
		s = new ShapeRenderer();
		s.setAutoShapeType(true);
	}
	
	@Override
	public void draw(Batch b, float a) {
		b.end();
		s.begin();
		s.set(ShapeType.Filled);
		s.setColor(Color.RED);
		s.rect(40 + (currentHealth) * 2, 600, (maxHealth- currentHealth) * 2, 10);
		s.setColor(Color.GREEN);
		s.rect(40, 600, (currentHealth) * 2, 10);
		s.end();
		b.begin();
	}
	
	public void updateHealth(int ch) {
		currentHealth = ch;
	}
	
}
