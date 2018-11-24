package com.hangover;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;


//Screen for both demonstrating how to use BaseScreen
public class TestScreen extends PlayScreen {
	
	Entity im;
	
	public TestScreen(Game g, ResourceManager r) {
		super(g, r);
	}

	public void create() {
		//This is how to initialise a class when using PlayScreen
		super.create();
		
		//The entity gotten depends on which character is being used.
		c.clone(r.getEntity("badlogic"));
		c.setSpeed(100);
		for(ImageActor i: background) {
			backStage.addActor(i);
		}
		entityStage.addActor(c);
		/*This is how to initialise a moving actor
		im = new Entity();
		im.clone(r.getEntity("badlogic"));
		im.setSpeed(100);
		im.setAngle((float) Math.PI/2);
		im.setMoving(true);
		
		//Actors need to be added to the stage to be drawn
		entityStage.addActor(im);
		*/
	}

	@Override
	public void update(float dt) {
	}

	@Override
	public boolean keyDown(int keycode) {
		super.keyDown(keycode);
		if (keycode == Input.Keys.ESCAPE){
		    g.setScreen(new StartScreen(g, r));
        }

        if (keycode == Input.Keys.P){
            togglePaused();
        }
	    return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		super.keyUp(keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		
		return false;
	}

}
