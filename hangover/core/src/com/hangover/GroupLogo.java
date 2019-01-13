package com.hangover;

import com.badlogic.gdx.Gdx;

public class GroupLogo extends BaseScreen {

	//Abstract Delete logo ImageActor
	public ImageActor logo;
	
	//Counts current time
	public float time = 0;
	
	public GroupLogo(AKillerHangover g, ResourceManager r) {
		super(g, r);
	}
	
	@Override
	public void create() {
		
		//Sets background as blue tiles
		for(int i = 0; i < Gdx.graphics.getHeight()/32; i++) {
			for(int j = 0; j < Gdx.graphics.getWidth()/32; j++) {
				ImageActor block = new ImageActor();
				block.clone(r.getImageActor("flatm"));
				block.setPosition(64 * j, 64 * i);
				backStage.addActor(block);
			}
		}
		
		//Puts logo on screen, initially transparent
		logo = new ImageActor();
		logo.clone(r.getImageActor("logo"));
		logo.setOrigin();
		logo.setPosition(152, -40);
		logo.setFiltered(true);
		logo.setColor(1, 1, 1, 0);
		
		//Adds logo to screen
		entityStage.addActor(logo);
	}

	@Override
	public void update(float dt) {
		
		//Makes the logo less transparent and then more transparent. Loads next screen afterwards.
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
			StartingFlat start = new StartingFlat(g, r);
			g.setScreen(start, true);
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
