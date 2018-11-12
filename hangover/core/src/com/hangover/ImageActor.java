package com.hangover;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ImageActor extends Actor {

	private TextureRegion t;
	
	public ImageActor() {
		super();
		setWidth(0);
		setHeight(0);
		setX(0);
		setY(0);
	}
	
	public ImageActor(String url) {
		t = new TextureRegion(new Texture(url));
		setWidth(t.getRegionWidth());
		setHeight(t.getRegionHeight());
		setX(0);
		setY(0);
	}
	
	public void setImage(String url) {
		t = new TextureRegion(new Texture(url));
		setWidth(t.getRegionWidth());
		setHeight(t.getRegionHeight());
	}
	
	public TextureRegion getImage() {
		return t;
	}
	
	@Override
	public void draw(Batch b, float dt) {
		b.draw(t, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
}
