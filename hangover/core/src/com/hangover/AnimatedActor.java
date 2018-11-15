package com.hangover;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class AnimatedActor extends ImageActor {

	private float framerate;
	private float timing;
	private TextureRegion[][] frames;
	private float frameTime;
	
	public AnimatedActor() {
		super();
		setFramerate(1);
		setFrameSize(1, 1);
		timing = 0;
	}
	
	public AnimatedActor(String url) {
		super(url);
		setFramerate(1);
		setFrameSize(this.getWidth(), this.getHeight());
		timing = 0;
	}
	
	public AnimatedActor(String url, float fr, float fw) {
		super(url);
		setFramerate(fr);
		setFrameSize(fw, this.getHeight());
		frames = t.split((int)(this.getWidth()), (int)(this.getHeight()));
		timing = 0;
	}
	
	public void setFramerate(float f) {
		framerate = f;
		frameTime = 1/f;
	}
	
	public float getFramerate() {
		return framerate;
	}
	
	public void setFrameSize(float x, float y) {
		setWidth(x);
		setHeight(y);
	}
	
	public Vector2 getFramesize() {
		return new Vector2(getWidth(), getHeight());
	}
	
	public void setAnim(String url, float width) {
		setImage(url);
		setFrameSize(width, t.getRegionHeight());
		setWidth(width);
		frames = t.split((int)(this.getWidth()), (int)(this.getHeight()));
		setRectBounds();
	}
	
	public void draw(Batch b, float parentAlpha) {
		if(timing >= (frameTime * frames[0].length)){
			timing -= frameTime * frames[0].length;
		}
		int currentFrame = (int) Math.floor(timing / frameTime);
		b.draw(frames[0][currentFrame], getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	public void act(float dt) {
		super.act(dt);
		timing += dt;
	}
	
}
