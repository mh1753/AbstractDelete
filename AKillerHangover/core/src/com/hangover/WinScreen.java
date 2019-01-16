package com.hangover;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class WinScreen extends BaseScreen {

	public WinScreen(AKillerHangover g, ResourceManager r) {
		super(g, r);
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

	@Override
	public void create() {
		BitmapFont font = new BitmapFont();
        String instructionText = " You Win! ";
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        Label instructions = new Label(instructionText, style);
        instructions.setFontScale(2);
        instructions.setPosition(100,500);
        uiStage.addActor(instructions);
	}

	@Override
	public void update(float dt) {
		
	}

}
