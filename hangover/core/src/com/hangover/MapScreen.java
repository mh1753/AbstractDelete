package com.hangover;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MapScreen extends BaseScreen {

	public MapScreen(AKillerHangover g, ResourceManager r) {
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
		
        BitmapFont font = new BitmapFont();
        String instructionText = " Goodricke ";
        Label.LabelStyle style = new Label.LabelStyle(font, Color.GREEN);
        Label instructions = new Label(instructionText, style);
        instructions.setFontScale(2);
        instructions.setPosition(20,600);
        instructions.addListener(new ClickListener() {
        	@Override
        	public void clicked(InputEvent event, float x, float y){
        		if(event.getButton() == Input.Buttons.LEFT) {
        		FirstAreaDemo f = new FirstAreaDemo(g, r);
        		g.setScreen(f, true);
        		}
        	}
        });
        uiStage.addActor(instructions);
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
