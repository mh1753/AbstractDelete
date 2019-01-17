package com.hangover;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PickCharScreen extends BaseScreen {

	public PickCharScreen(AKillerHangover g, ResourceManager r) {
		super(g, r);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
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
        String destText = " Player 1 ";
        Label.LabelStyle style = new Label.LabelStyle(font, Color.GREEN);
        Label player1 = new Label(destText, style);
        player1.setFontScale(2);
        player1.setPosition(20,600);
        player1.addListener(new ClickListener() {
        	@Override
        	public void clicked(InputEvent event, float x, float y){
        		if(event.getButton() == Input.Buttons.LEFT) {
        			g.playerType = "player1";
        			StartingFlat f = new StartingFlat(g, r);
        			g.setScreen(f, true);
        		}
        	}
        });
        
        //sets player2 button on map screen
        destText = " Player 2 ";
        style = new Label.LabelStyle(font, Color.BLUE);
        Label player2 = new Label(destText, style);
        player2.setFontScale(2);
        player2.setPosition(20,570);
        player2.addListener(new ClickListener() {
        	@Override
        	public void clicked(InputEvent event, float x, float y){
        		if(event.getButton() == Input.Buttons.LEFT) {
        			g.playerType = "player2";
        			StartingFlat f = new StartingFlat(g, r);
        			g.setScreen(f, true);
        		}
        	}
        });
        
        //Adds clickable text to the uistage
        uiStage.addActor(player1);
        uiStage.addActor(player2);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

}
