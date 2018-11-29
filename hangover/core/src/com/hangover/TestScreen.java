package com.hangover;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;


//Screen for both demonstrating how to use BaseScreen
public class TestScreen extends PlayScreen {
	
	Entity im;
	
	public TestScreen(Game g, ResourceManager r) {
		super(g, r);
	}

	public void create() {
		//This is how to initialise a class when using PlayScreen
		super.create("badlogic", new Vector2(100, 100), null);
		
		for(ImageActor i: background) {
			backStage.addActor(i);
		}
		entityStage.addActor(c);
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
