package com.geeselightning.zepr;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BonusGoose extends Sprite{
	
	private Texture leftOne = new Texture("gooseLeft.png");
	// Change starts: BONUSGAMEOPTIMIZATION
	private Random rand = new Random();
	// Change ends: BONUSGAMEOPTIMIZATION
	private int velocityX = 0;
	private int velocityY = 0;
	private int speed = 60;
	private float timer = 0;
	private int direction = 0;
	private int directionCopy = 0;
	private boolean startedMovement = false;


	/**
	 * Constructor for the geese in the bonus game
	 *
	 * @param x the initial horizontal position
	 * @param y the initial vertical position
	 */
	public BonusGoose(int x, int y) {
		super(new Sprite(new Texture("gooseLeft.png")));
        setX(x);
        setY(y);
	}
	
	@Override
	public void draw(Batch batch) {
        // Calls the update method of the character. To update its properties, i.e. position.
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
	}

	/**
	 * When the goose is hit:
	 * randomly chooses a position in bounds, and places the goose there
	 */
	public void respawn() {
		int randX = rand.nextInt(925);
		int randY = rand.nextInt(600);
		setX(randX);
        setY(randY);
	}


	/**
	 * Dictates actions of the goose
	 *
	 * @param delta change in time
	 */
	public void update(float delta) {
        // Update x, y position of goose.
        setX(getX() + velocityX * delta);
        setY(getY() + velocityY * delta);

        // Start goose movement
        if (!startedMovement) {
        	velocityX = -speed;
		    velocityY = speed;
		    startedMovement = true;
        }

        // Change starts: BONUSGOOSEDIRECTIONFIX
		// If the goose hits the boundary, reverse the direction ob the respective axis
		if(getX() >= 925) {
			setX(925);
			velocityX = -velocityX;
		} else if(getX() <= 280) {
			velocityX = - velocityX;
			setX(280);
		} 
		
		if(getY() >= 600) {
			setY(600);
			velocityY = -velocityY;
		} else if(getY() <= 250) {
			velocityY = -velocityY;
			setY(250);
		}
		// Change ends: BONUSGOOSEDIRECTIONFIX
		
        // Increments timer
		timer += delta;
		
		// Changes direction and increases speed every 4 seconds
		if (timer > 4) {
			speed += 2;
			// Resets timer
			timer = 0;
			
			// Avoids repeats in direction
			while(direction == directionCopy) {
				direction = rand.nextInt(7);				
			}
			directionCopy = direction;

			switch (direction) {
				case(1): // N
				velocityX = 0;
		    	velocityY = speed;
				break;
				case(2): // NE
				velocityX = speed;
	    		velocityY = speed;
	    		break;
	    		case(3): // E
				velocityX = +speed;
	    		velocityY = 0;
	    		break;
	    		case(4): // SE
				velocityX = speed;
				velocityY = -speed;
				break;
				case(5): // S
				velocityX = 0;
				velocityY = -speed;
				break;
				case(6): // SW
				velocityX = -speed;
				velocityY = -speed;
				break;
				case(7): // W
				velocityX = -speed;
				velocityY = 0;
				break;
				default: // NW
				velocityX = -speed;
			    velocityY = speed;
				break;
			}
		} 
	}


	/**
	 * Dispose of any textures
	 */
	public void dispose() {
		leftOne.dispose();
		// Change starts: BONUSGAMEOPTIMIZATION
		this.getTexture().dispose();
		// Change ends: BONUSGAMEOPTIMIZATION
	}
}
