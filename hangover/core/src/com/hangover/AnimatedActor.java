package com.hangover;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class AnimatedActor extends ImageActor {
	
	//Stores animations
	HashMap<String, Animation<TextureRegion>> anims = new HashMap<String, Animation<TextureRegion>>();
	
	//Stores current animation
	public Animation<TextureRegion> currentAnim;
	
	//Stores current time
	public float timing;
	
	//Initialises AnimatedActor
	public AnimatedActor() {
		super();
		currentAnim = null;
		setFrameSize(1, 1);
		timing = 0;
	}
	

	
	//Sets the size of the frame
	public void setFrameSize(float x, float y) {
		setWidth(x);
		setHeight(y);
	}
	
	//Returns size of frame
	public Vector2 getFrameSize() {
		return new Vector2(getWidth(), getHeight());
	}
	
	//Sets the animation. Works by taking an image url and then splitting this into frames
	public void storeAnim(String url, String name , float width, float fr) {
		setImage(url);
		setFrameSize(width, t.getRegionHeight());
		TextureRegion[][] frames = t.split((int)(this.getWidth()), (int)(this.getHeight()));
		Animation<TextureRegion> a = new Animation<TextureRegion>(1/fr, frames[0]);
		a.setPlayMode(Animation.PlayMode.LOOP);
		anims.put(name, a);
		if(currentAnim == null) {
			currentAnim = a;
		}
		setOrigin();
	}
	
	//Returns stores animations
	public HashMap<String, Animation<TextureRegion>> getAnims(){
		return anims;
	}
	
	//Sets an animation from anims based on a name
	public void setAnim(String name) {
		if(anims.containsKey(name)) {
			currentAnim = anims.get(name);
			setWidth(currentAnim.getKeyFrame(0).getRegionWidth());
			setHeight(currentAnim.getKeyFrame(0).getRegionHeight());
			t = currentAnim.getKeyFrame(0);
			setRectangleBoundary();
		}
	}
	
	//draws the animation by deciding which frame to display based on time
	public void draw(Batch b, float dt) {
		if(isLiving()) {
			if(currentAnim == null) {
				b.draw(t, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
			}
			else {
				b.draw(currentAnim.getKeyFrame(timing), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());				
				
			}
		}
	}
	
	//Updates the animation timing
	public void act(float dt) {
		super.act(dt);
		timing += dt;
	}
	
	//Takes an animated actor and makes this actor identical
	public void clone(AnimatedActor a) {
		super.clone(a);
		anims = a.anims;
		currentAnim = a.currentAnim;
		setWidth(currentAnim.getKeyFrame(0).getRegionWidth());
		setHeight(currentAnim.getKeyFrame(0).getRegionHeight());
		setOrigin(a.getOriginX(),a.getOriginY());
	}
	
	//Makes this animated actor identical to the image actor provided
	public void clone(ImageActor i) {
		super.clone(i);
	}
	
}
