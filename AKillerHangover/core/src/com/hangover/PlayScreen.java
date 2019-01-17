package com.hangover;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public abstract class PlayScreen extends BaseScreen {
	
	public int maxEnemyNo;
	
	public int spawnRate;
	
	public double hitCounter;
	
	public ArrayList<ImageActor> background;
	public ArrayList<Rectangle> obstacles;
	public ArrayList<Rectangle> doors;
	public ArrayList<MovingActor> bullets;
	
	public float timeSinceShot = 1;
	
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
		
		
		//Sets the number of enemies the game will try to maintain and the rate of spawning
		maxEnemyNo = 0;
		spawnRate = g.spawnrate;
		
		hitCounter = 0;
		
		
		//Displays the current number of points
		BitmapFont font = new BitmapFont();
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        currentPoints = new Label("Points: " + String.valueOf(g.points), style);
        currentPoints.setFontScale(2);
        currentPoints.setPosition(800, 600);
        currentPoints.setVisible(true);
        uiStage.addActor(currentPoints);
		
		
		
		//Character initialisation
		c = new Character(charName, r);
		c.setType(charName);
		c.setOrigin();
		if (playerLoc != null) {
			c.setPosition(playerLoc.x, playerLoc.y);
		}
		
		//Keeps the player's health constant between screens
		if(g.playerHealth == null) {
			g.playerHealth = new HealthBar(c.getMaxHealth(), c.getHealth());
		}
		else {
			c.takeHealth(g.playerHealth.maxHealth - g.playerHealth.currentHealth);
		}
		
		//adds health bar to screen
		uiStage.addActor(g.playerHealth);
		
		
		
		//initialise tilemap
		tilemap = new TiledMap();
		TmxMapLoader loader = new TmxMapLoader();
		
		
		//load tilemap
		if(tileMap != null) {
			
	    	tilemap = loader.load(tileMap);
			if(tilemap != null) {
				tiling = true;
				//get tilemap properties
				tileWidth = tilemap.getProperties().get("tilewidth", Integer.class);
				tileHeight = tilemap.getProperties().get("tileheight", Integer.class);
	        	mapWidthTiles = tilemap.getProperties().get("width", Integer.class);
	        	mapHeightTiles = tilemap.getProperties().get("height", Integer.class);
	        	mapWidthPixels = mapWidthTiles  * tileWidth;
	        	mapHeightPixels = mapHeightTiles * tileHeight;
	        	
	        	//get tilemap camera
	        	camera = (OrthographicCamera) entityStage.getCamera();
	        	camera.position.set( c.getX() + c.getOriginX(),
	        			c.getY() + c.getOriginY(), 0 );
	        	renderer = new TileMapRendererActor(tilemap);
	        	renderer.setView(camera);
	        	renderer.setOrigin(renderer.getWidth()/2, renderer.getHeight()/2);
	        	backStage.addActor(renderer);
	        	
	        	//get collision map and door map from tilemap
	        	if (tilemap.getLayers().size() > 1) {
	        		obstacles = new ArrayList<Rectangle>();
	        		MapLayer layer = tilemap.getLayers().get("collision");
	        		MapObjects layerObjects = layer.getObjects();
	        		for(RectangleMapObject o : layerObjects.getByType(RectangleMapObject.class)) {
	        			obstacles.add(o.getRectangle());
	        		}
	        		
	        		doors = new ArrayList<Rectangle>();
	        		layer = tilemap.getLayers().get("door");
	        		if(layer != null) {
	        			layerObjects = layer.getObjects();
	        			for(RectangleMapObject o : layerObjects.getByType(RectangleMapObject.class)) {
	        				doors.add(o.getRectangle());
	        			}
	        		}
	        	}
			}
		}
	}

	@Override
	public void update(float dt) {
		
		//Check if player has won.
		g.checkWin();
		
		//If the player leaves the map, navigate to the map screen
		if(c.getX() <= 0 || c.getX() + c.getWidth() >= mapWidthPixels || c.getY() <= 0 
				|| c.getY() + c.getHeight() >= mapHeightPixels) {
			MapScreen mapscreen = new MapScreen(g, r);
			g.setScreen(mapscreen, false);
		}
		
		ArrayList<MovingActor> deadBullets = new ArrayList<MovingActor>();
		ArrayList<NPC> deadEnemies = new ArrayList<NPC>();
		
		
		//Spawns more enemies if the number of enemies goes below a certain number
		if(enemies.size() - maxEnemyNo < 0) {
			for(int i = 0; i < maxEnemyNo - enemies.size(); i++) {
				int spawn = MathUtils.random(0, spawnRate);
				float range = (dt * 1000);
				if(spawn <= range) {
					double zombietype = MathUtils.random();
					float x = renderer.getX() + (MathUtils.random(0, mapWidthPixels));
					float y = renderer.getY() + (MathUtils.random(0, mapHeightPixels));
					while((c.getX() - x < 200 && c.getX() - x > -200 &&
							c.getY() - y < 200 && c.getY() - y > -200)){
						x = renderer.getX() + (MathUtils.random(0, mapWidthPixels));
						y = renderer.getY() + (MathUtils.random(0, mapHeightPixels));
					}
					NPC z;
					if(zombietype < 0.5) {
						z = new NPC("zombie", r);
						z.setType("zombie");
					}
					else {
						z = new NPC("zombie2", r);
						z.setType("zombie2");
					}
					z.setPosition(x, y);
					entityStage.addActor(z);
					enemies.add(z);
				}
			}
		}
		
		//Makes sure enemies and player don't pass through walls
		if(obstacles != null) {
			for(Rectangle r : obstacles) {
								
				for(NPC e : enemies) {
					e.overlaps(r, true);
				}
				c.overlaps(r, true);
				for(MovingActor b: bullets) {
					if(b.overlaps(r, false)) {
						deadBullets.add(b);
						b.setLiving(false);
					}
				}
			}

		}
		
		//Allows player to use doors to go to new places
		if(doors != null) {
			for(Rectangle r: doors) {
				if(c.overlaps(r, false)) {
					enterDoor();
				}
			}
		}
		
		//checks if a bullet has hit an enemy
		for(MovingActor b : bullets) {
			for(NPC e : enemies) {
				if(e.overlaps(b, false)) {
					e.takeHealth(30);
					if(e.getHealth() <= 0) {
						g.addPoints(100);
						deadBullets.add(b);
						b.setLiving(false);
						deadEnemies.add(e);
						e.setLiving(false);
					}
				}
			}
		}
		//removes all bullets that have hit an enemy and all enemies that have died
		bullets.removeAll(deadBullets);
		enemies.removeAll(deadEnemies);
		deadBullets.clear();
		deadEnemies.clear();
		
		//updates the enemy animation, checks if they've hit the player and sets the velocity
		for(NPC e : enemies) {
			e.updateAnimation();
			if( e.overlaps(c, true) && hitCounter <= 0){
				c.takeHealth(30);
				hitCounter = 1.5;
				g.playerHealth.updateHealth(c.getHealth());
			}
			if(c.isLiving()) {
				e.simpleChasePlayer(c.getX(), c.getY());
			}
		}
		
		//Time elapsed since the last time the player fired a bullet
		timeSinceShot += dt;
		
		//Counts down time until player can be hit again
		hitCounter -= dt;
		
		//updates the points counter
		currentPoints.setText("Points: " + String.valueOf(g.points));
		
		//updates the camera based on player's location
		camera.position.set( MathUtils.clamp(c.getX() + c.getOriginX(), 512, mapWidthPixels - 512),
    			MathUtils.clamp(c.getY() + c.getOriginY(), 320, mapHeightPixels - 320), 0 );
		camera.update();
		renderer.setView(camera);
		
		//updates player's animation
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
		if(button == Input.Buttons.LEFT) {
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
	
	public abstract void enterDoor();
	
	public void dispose() {
		super.dispose();
		manager.dispose();
	}


}
