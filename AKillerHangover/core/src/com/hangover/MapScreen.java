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
		
		//Fills in background with blue tiles
		for(int i = 0; i < Gdx.graphics.getHeight()/32; i++) {
			for(int j = 0; j < Gdx.graphics.getWidth()/32; j++) {
				ImageActor block = new ImageActor();
				block.clone(r.getImageActor("flatm"));
				block.setPosition(64 * j, 64 * i);
				backStage.addActor(block);
			}
		}
		
		//Sets Goodricke button on map screen
        BitmapFont font = new BitmapFont();
        String destText = " Goodricke ";
        Label.LabelStyle style = new Label.LabelStyle(font, Color.GREEN);
        Label goodricke = new Label(destText, style);
        goodricke.setFontScale(2);
        goodricke.setPosition(20,600);
        goodricke.addListener(new ClickListener() {
        	@Override
        	public void clicked(InputEvent event, float x, float y){
        		if(event.getButton() == Input.Buttons.LEFT) {
        		Goodricke f = new Goodricke(g, r);
        		g.setScreen(f, true);
        		}
        	}
        });
        
        //sets Flat button on map screen
        destText = " Flat ";
        style = new Label.LabelStyle(font, Color.BLUE);
        Label flat = new Label(destText, style);
        flat.setFontScale(2);
        flat.setPosition(20,570);
        flat.addListener(new ClickListener() {
        	@Override
        	public void clicked(InputEvent event, float x, float y){
        		if(event.getButton() == Input.Buttons.LEFT) {
        		StartingFlat f = new StartingFlat(g, r);
        		g.setScreen(f, true);
        		}
        	}
        });
        
      //sets Langwith button on map screen
        destText = "Langwith ";
        style = new Label.LabelStyle(font, Color.YELLOW);
        Label langwith = new Label(destText, style);
        langwith.setFontScale(2);
        langwith.setPosition(20,540);
        langwith.addListener(new ClickListener() {
        	@Override
        	public void clicked(InputEvent event, float x, float y){
        		if(event.getButton() == Input.Buttons.LEFT) {
        		Langwith l = new Langwith(g, r);
        		g.setScreen(l, true);
        		}
        	}
        });
        
        destText = "Constantine ";
        style = new Label.LabelStyle(font, Color.PINK);
        Label constantine = new Label(destText, style);
        constantine.setFontScale(2);
        constantine.setPosition(20,510);
        constantine.addListener(new ClickListener() {
        	@Override
        	public void clicked(InputEvent event, float x, float y){
        		if(event.getButton() == Input.Buttons.LEFT) {
        		Constantine l = new Constantine(g, r);
        		g.setScreen(l, true);
        		}
        	}
        });
        
        //Adds clickable text to the uistage
        uiStage.addActor(flat);
        uiStage.addActor(goodricke);
        uiStage.addActor(langwith);
        uiStage.addActor(constantine);
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
