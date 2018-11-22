package com.hangover;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;


//Screen for both demonstrating how to use BaseScreen
public class
TestScreen extends BaseScreen {
	
	Entity im;
	
	public TestScreen(Game g, ResourceManager r) {
		super(g, r);
	}

	public void create() {
		
		//This is how to initialise a moving actor
		im = new Entity();
		im.clone(r.getEntity("badlogic"));
		
		//Actors need to be added to the stage to be drawn
		entityStage.addActor(im);
	}

	@Override
	public void update(float dt) {
		
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.ESCAPE){
		    g.setScreen(new StartScreen(g));
        }

        if (keycode == Input.Keys.P){
            togglePaused();
        }
	    return false;
	}

	@Override
	public boolean keyUp(int keycode) {
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
