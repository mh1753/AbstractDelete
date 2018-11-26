package com.hangover;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public abstract class PlayScreen extends BaseScreen {
	
	public ArrayList<ImageActor> background;
	
	public ArrayList<Integer> keysPressed;
	
	public Character c;
	
	public PlayScreen(Game g, ResourceManager r) {
		super(g, r);
	}
	

	public void create(String charName, Vector2 playerLoc) {
		background = new ArrayList<ImageActor>();
		keysPressed = new ArrayList<Integer>();
		c = new Character(charName, r);
		if (playerLoc != null) {
			c.setPosition(playerLoc.x, playerLoc.y);
		}
	}

	@Override
	public void update(float dt) {
		
	}

	@Override
	public boolean keyDown(int keycode) {
		if(!keysPressed.contains(keycode)) {
			keysPressed.add(keycode);
		}
		c.setMoving(true);
		if(keysPressed.contains(Keys.W)) {
			if(keysPressed.contains(Keys.D)) {
				c.setAngle((float) (Math.PI/4));
			}
			else if(keysPressed.contains(Keys.A)) {
				c.setAngle((float)(7*Math.PI/4));
			}
			else{
				c.setAngle(0);
			}
		}
		else if(keysPressed.contains(Keys.S)) {
			if(keysPressed.contains(Keys.D)) {
				c.setAngle((float) (3*Math.PI/4));
			}
			else if(keysPressed.contains(Keys.A)) {
				c.setAngle((float)(5*Math.PI/4));
			}
			else{
				c.setAngle((float)(Math.PI));
			}
		}
		else if(keysPressed.contains(Keys.D)) {
			c.setAngle((float)(Math.PI/2));
		}
		else if(keysPressed.contains(Keys.A)) {
			c.setAngle((float)(3*Math.PI/2));
		}
		else {
			c.setMoving(false);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keysPressed.contains(keycode)) {
			keysPressed.remove(keysPressed.indexOf(keycode));
		}
		if(keysPressed.contains(Keys.W)) {
			if(keysPressed.contains(Keys.D)) {
				c.setAngle((float) (Math.PI/4));
			}
			else if(keysPressed.contains(Keys.A)) {
				c.setAngle((float)(7*Math.PI/4));
			}
			else{
				c.setAngle(0);
			}
		}
		else if(keysPressed.contains(Keys.S)) {
			if(keysPressed.contains(Keys.D)) {
				c.setAngle((float) (3*Math.PI/4));
			}
			else if(keysPressed.contains(Keys.A)) {
				c.setAngle((float)(5*Math.PI/4));
			}
			else{
				c.setAngle((float)(Math.PI));
			}
		}
		else if(keysPressed.contains(Keys.D)) {
			c.setAngle((float)(Math.PI/2));
		}
		else if(keysPressed.contains(Keys.A)) {
			c.setAngle((float)(3*Math.PI/2));
		}
		else if(!(keysPressed.contains(Keys.W) || keysPressed.contains(Keys.A)|| keysPressed.contains(Keys.S) || keysPressed.contains(Keys.D))){
			c.setMoving(false);
		}
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
	
	public void loadCharacter() {
		try {
			c.clone(r.getEntity(c.getType()));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


}
