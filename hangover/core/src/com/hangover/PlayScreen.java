package com.hangover;

import java.util.ArrayList;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public abstract class PlayScreen extends BaseScreen {
	
	public ArrayList<ImageActor> background;
	
	public ArrayList<MovingActor> bullets;
	public float timeSinceShot = 0;
	
	public ArrayList<NPC> enemies;
	
	public ArrayList<Integer> keysPressed;
	
	public TiledMap tilemap;
	
	public TileMapRendererActor renderer;
	
	public AssetManager manager;
	
	public boolean tiling = false;
	
	public int tileWidth, tileHeight, mapWidthTiles, mapHeightTiles, mapWidthPixels, mapHeightPixels;
	
	public OrthographicCamera camera;
	
	public Label currentPoints;
	
	public Character c;
	
	public PlayScreen(AKillerHangover g, ResourceManager r) {
		super(g, r);
	}
	

	public void create(String charName, Vector2 playerLoc, String tileMap) {
		
		//Initialise all arraylists
		background = new ArrayList<ImageActor>();
		bullets = new ArrayList<MovingActor>();
		enemies = new ArrayList<NPC>();
		keysPressed = new ArrayList<Integer>();
		
		BitmapFont font = new BitmapFont();
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        currentPoints = new Label("Points: " + String.valueOf(g.points), style);
        currentPoints.setFontScale(2);
        currentPoints.setPosition(800,600);
        uiStage.addActor(currentPoints);
		
		
		//Character initialisation
		c = new Character(charName, r);
		c.setType(charName);
		c.setOrigin();
		if (playerLoc != null) {
			c.setPosition(playerLoc.x, playerLoc.y);
		}
		
		
		//initialise tilemap
		tilemap = new TiledMap();
		TmxMapLoader loader = new TmxMapLoader();
		
		
		//load tilemap
		if(tileMap != null) {
			
	    	tilemap = loader.load(tileMap);
			if(tilemap != null) {
				tiling = true;
				tileWidth = tilemap.getProperties().get("tilewidth", Integer.class);
				tileHeight = tilemap.getProperties().get("tileheight", Integer.class);
	        	mapWidthTiles = tilemap.getProperties().get("width", Integer.class);
	        	mapHeightTiles = tilemap.getProperties().get("height", Integer.class);
	        	mapWidthPixels = mapWidthTiles  * tileWidth;
	        	mapHeightPixels = mapHeightTiles * tileHeight;
	        	camera = (OrthographicCamera) entityStage.getCamera();
	        	camera.position.set( c.getX() + c.getOriginX(),
	        			c.getY() + c.getOriginY(), 0 );
	        	renderer = new TileMapRendererActor(tilemap);
	        	renderer.setView(camera);
	        	renderer.setOrigin(renderer.getWidth()/2, renderer.getHeight()/2);
	        	backStage.addActor(renderer);
			}
		}
	}

	@Override
	public void update(float dt) {
		
		ArrayList<MovingActor> deadBullets = new ArrayList<MovingActor>();
		ArrayList<NPC> deadEnemies = new ArrayList<NPC>();
		
		for(MovingActor b : bullets) {
			for(NPC e : enemies) {
				if(e.overlaps(b, false)) {
					e.takeHealth(30);
					if(e.getHealth() <= 0) {
						g.addPoints(100);
						entityStage.getActors().removeValue(b, true);
						deadBullets.add(b);
						b.setLiving(false);
						deadEnemies.add(e);
						e.setLiving(false);
					}
				}
			}
		}
		bullets.removeAll(deadBullets);
		enemies.removeAll(deadEnemies);
		
		for(NPC e : enemies) {
			if( c.overlaps(e, true)){
				c.takeHealth(30);
			}
			if(c.isLiving()) {
				e.simpleChasePlayer(c.getX(), c.getY());
			}
		}
		
		timeSinceShot += dt;
		
		currentPoints.setText("Points: " + String.valueOf(g.points));
		
		camera.position.set( MathUtils.clamp(c.getX() + c.getOriginX(), c.getOriginX(), mapWidthPixels - c.getOriginX()),
    			MathUtils.clamp(c.getY() + c.getOriginY(), 0, mapHeightPixels - c.getOriginY()), 0 );
		camera.update();
		renderer.setView(camera);
		c.updateAnimation();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(!keysPressed.contains(keycode)) {
			keysPressed.add(keycode);
		}
		if(c.getHealth() > 0) {
			c.setMoving(true);
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
		else {
			c.setMoving(false);
		}
		
		//Deals with shooting
		if(keysPressed.contains(Keys.SPACE)) {
			if(timeSinceShot > 1) {
				MovingActor bullet = new MovingActor();
				bullet.clone(r.getImageActor("bullet"));
				Vector2 bPos = new Vector2();
				bPos.x = c.getX() + 32 - 32 * MathUtils.sin(c.getRotation() * MathUtils.degreesToRadians);
				bPos.y = c.getY() + 32 + 32 * MathUtils.cos(c.getRotation() * MathUtils.degreesToRadians);
				bullet.setVelocity(-300 * MathUtils.sin(c.getRotation()* MathUtils.degreesToRadians),
						300 * MathUtils.cos(c.getRotation()* MathUtils.degreesToRadians));
				bullet.setOrigin(3, 0);
				bullet.setPosition(bPos.x, bPos.y);
				bullet.setRotation(c.getRotation());
				bullet.setMoving(true);
				bullets.add(bullet);
				entityStage.addActor(bullet);
				timeSinceShot = 0;
			}
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
		Vector3 v = new Vector3(screenX, screenY, 0);
		camera.unproject(v, backStage.getViewport().getScreenX(), backStage.getViewport().getScreenY(),
				backStage.getViewport().getScreenWidth(), backStage.getViewport().getScreenHeight());
		c.updatePlayerRot(v.x, v.y);
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
	
	public void dispose() {
		super.dispose();
		manager.dispose();
	}


}
