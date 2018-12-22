package com.hangover;

import com.badlogic.gdx.Gdx;

public class GroupLogo extends BaseScreen {

	public ImageActor logo;
	
	public float time = 0;
	
	public GroupLogo(AKillerHangover g, ResourceManager r) {
		super(g, r);
	}
	
	@Override
	public void create() {
		
		for(int i = 0; i < Gdx.graphics.getHeight()/32; i++) {
			for(int j = 0; j < Gdx.graphics.getWidth()/32; j++) {
				ImageActor block = new ImageActor();
				block.clone(r.getImageActor("flatm"));
				block.setPosition(64 * j, 64 * i);
				backStage.addActor(block);
			}
		}
		
		logo = new ImageActor();
		logo.clone(r.getImageActor("logo"));
		logo.setOrigin();
		logo.setPosition(152, -40);
		logo.setFiltered(true);
		logo.setColor(1, 1, 1, 0);
		
		entityStage.addActor(logo);
	}

	@Override
	public void update(float dt) {
		time += dt;
		if(time <= 0.5) {
			logo.setColor(1, 1, 1, time);
		}
		else if(time > 0.5 && time < 1.5) {
			logo.setColor(1, 1, 1, 1);
		}
		else if(time >= 1.5 && time <= 2) {
			logo.setColor(1, 1, 1, (2 - time) * 2);
		}
		else {
			logo.setColor(1, 1, 1, 0);
		}
		if(time > 3) {
			FirstAreaDemo test = new FirstAreaDemo(g, r);
			g.setScreen(test, true);
		}
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
