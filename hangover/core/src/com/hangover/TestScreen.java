package com.hangover;

import com.badlogic.gdx.Game;

public class TestScreen extends BaseScreen {
	
	MovingActor im;
	
	public TestScreen(Game g) {
		super(g);
	}

	public void create() {
		im = new MovingActor("assets//badlogic.jpg", 10, 20);
		im.setVelocity(0, 0);
		im.setAcceleration(0, 0);
		im.setAngularVel(0);
		entityStage.addActor(im);
	}

	@Override
	public void update(float dt) {
		
	}

	@Override
	public boolean keyDown(int keycode) {
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
