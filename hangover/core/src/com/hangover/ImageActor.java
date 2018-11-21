package com.hangover;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ImageActor extends Actor {

	//Stores the actor's image
	protected TextureRegion t;
	
	//Actor's boundary for use in collision detection
	protected Rectangle bounds;
	
	public ImageActor() {
		super();
		setWidth(0);
		setHeight(0);
		setX(0);
		setY(0);
		setRectBounds();
	}
	
	public ImageActor(String url) {
		//loads image from url
		setX(0);
		setY(0);
		setImage(url);
	}
	
	//loads image from url
	public void setImage(String url) {
		t = new TextureRegion(new Texture(url));
		setWidth(t.getRegionWidth());
		setHeight(t.getRegionHeight());
		setRectBounds();
	}
	
	//returns image
	public TextureRegion getImage() {
		return t;
	}
	
	//sets boundary for collision detection
	public void setRectBounds() {
		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	//returns boundary for collision detection
	public Rectangle getBounds() {
		return bounds;
	}
	
	
	//handles drawing of the image
	@Override
	public void draw(Batch b, float dt) {
		b.draw(t, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
}
