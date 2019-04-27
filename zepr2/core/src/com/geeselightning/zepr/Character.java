package com.geeselightning.zepr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Arrays;
import static java.lang.Math.abs;

public class Character extends Sprite {

    Vector2 velocity = new Vector2(); // 2D vector
    public float speed;
    int health = 100;
    // direction is a bearing in radians
    double direction = 0;
    Level currentLevel;
    // All characters start ready to hit.
    float hitRefresh = 2;
    int maxHealth;
    // Change starts: BOUNDRECT
    Rectangle boundRect = new Rectangle();
    // Change ends: BOUNDRECT
    // Change starts: CUREEFFECT
    // If the cure is active, indicates that this character is running away from the player
    private boolean running;
    // Change ends: CUREEFFECT

    public Character(Sprite sprite, Vector2 spawn, Level currentLevel) {
        super(sprite);
        setX(spawn.x);
        setY(spawn.y);
        // Change starts: COLLISIONUPDATE
        setBoundRect();
        // Change ends: COLLISIONUPDATE
        // Change starts: CUREEFFECT
        this.running = false;
        // Change ends: CUREEFFECT
        this.currentLevel = currentLevel;
    }

    // Change starts: COLLISIONUPDATE
    public void setBoundRect(){
        boundRect.setPosition(this.getX(), this.getY());
        boundRect.setSize(this.getRegionWidth(), this.getRegionHeight());
        boundRect.setCenter(this.getCenter());
    }
    // Change ends: COLLISIONUPDATE

    public double getDirection() {
        return direction;
    }

    public double getHealth() {
        return health;
    }

    // Change starts: SETHEALTH
    public void setHealth(int h){
        this.health = h;
    }
    // Change ends: SETHEALTH
    
    public void attack(Character character, float delta) {
    	// Implemented in higher classes
    }

    /**
     * Uses circles with diameter to determine if this character collides with the passed character.
     *
     * @param character Character to check if this collides with
     * @return boolean true if they collide, false otherwise
     */
    //Change starts: COLLISIONUPDATE
    public boolean collidesWith(Character character, boolean resolve) {
        character.boundRect.setPosition(character.getX(), character.getY());
        boundRect.setPosition(getX(), getY());
        if (resolve) {
            Intersector.MinimumTranslationVector mtv = new Intersector.MinimumTranslationVector();
            float[] verts1 = {boundRect.x, boundRect.y, boundRect.x + boundRect.width, boundRect.y,
                    boundRect.x + boundRect.width, boundRect.y + boundRect.height, boundRect.x, boundRect.y + boundRect.height};
            float[] verts2 = {character.boundRect.x, character.boundRect.y, character.boundRect.x + character.boundRect.width,
                    character.boundRect.y, character.boundRect.x + character.boundRect.width, character.boundRect.y +
                    character.boundRect.height, character.boundRect.x, character.boundRect.y + character.boundRect.height};
            if (Intersector.overlapConvexPolygons(verts1, verts2, mtv)) {
                setX(getX() + mtv.normal.x);
                setY(getY() + mtv.normal.y);
                return true;
            } else {
                return false;
            }

        } else {
            return Intersector.overlaps(boundRect,character.boundRect);
        }
    }

    public boolean collidesWith(Rectangle rect, boolean resolve) {
        boundRect.setPosition(getX(), getY());
        if (resolve) {
            Intersector.MinimumTranslationVector mtv = new Intersector.MinimumTranslationVector();
            float[] verts1 = {boundRect.x, boundRect.y, boundRect.x + boundRect.width, boundRect.y,
                    boundRect.x + boundRect.width, boundRect.y + boundRect.height, boundRect.x, boundRect.y + boundRect.height};
            float[] verts2 = {rect.x, rect.y, rect.x + rect.width, rect.y, rect.x + rect.width, rect.y + rect.height,
                    rect.x, rect.y + rect.height};
            if (Intersector.overlapConvexPolygons(verts1, verts2, mtv)) {
                setX(getX() + mtv.normal.x);
                setY(getY() + mtv.normal.y);
                return true;
            } else {
                return false;
            }

        } else {
            return Intersector.overlaps(boundRect,rect);
        }
    }
    //Change ends: COLLISIONUPDATE

    @Override
    public void draw(Batch batch) {
        // Calls the update method of the character. To update its properties, i.e. position.
        // The method is given the parameter delta so it can calculate the character new position.
        update(Gdx.graphics.getDeltaTime());

        super.draw(batch);

        setRotation((float) Math.toDegrees(- direction));
    }

    // hitRange has to be passed by the subclass from the canHit method.
    protected boolean canHitGlobal(Character character, int hitRange) {
        double directionToCharacter = this.getDirectionTo(character.getCenter());
        double angle = abs(directionToCharacter - direction);
        double distance = this.getCenter().sub(character.getCenter()).len();
        //Change starts: ANGLEFIX
        if(angle > MathUtils.PI) {
            angle -= MathUtils.PI2;
        }
        //Change ends: ANGLEFIX
        if (angle < 0.8 && distance < hitRange) {
            return true;
        } else {
            return false;
        }
    }

    public Vector2 getCenter() {
        return new Vector2(getX() + (getHeight() / 2), getY() + (getWidth() / 2));
    }

    /**
     * Finds the direction (in radians) that an object is in relative to the character.
     *
     * @param coordinate 2d vector representing the position of the object
     * @return bearing   double in radians of the bearing from the character to the coordinate
     */
    public double getDirectionTo(Vector2 coordinate) {
        Vector2 charCenter = new Vector2(this.getX() + (getWidth()/ 2),
                this.getY()+ (getHeight() / 2));

        // atan2 is uses the signs of both variables the determine the correct quadrant (relative to the character) of the
        // result.
        // Modulus 2pi of the angle must be taken as the angle is negative for the -x quadrants.
        // The angle must first be displaced by 2pi because the Java modulus function can return a -ve value.

        return(Math.atan2((coordinate.x - charCenter.x), (coordinate.y - charCenter.y)) + (2 * Math.PI))
                % (2 * Math.PI);
    }

    /**
     * Calculates a normailized vector that points torwards given coordinate.
     *
     * @param coordinate Vector2 representing the position of the object
     * @return normalised Vector2 that from this will point towards given coordinate
     */
    public Vector2 getDirNormVector(Vector2 coordinate) {
        Vector2 charCenter = new Vector2(this.getX() + (getWidth() / 2),
                this.getY() + (getHeight() / 2));
        // create vector that is the difference between character and the coordinate, and return it normalised
        Vector2 diffVector = new Vector2((coordinate.x - charCenter.x), (coordinate.y - charCenter.y));
        return diffVector.nor();
    }

    /**
     * This method updates all the characters properties.
     *
     * @param delta the time passed since the last frame (render() call)
     */
    public void update(float delta) {
        // Update x, y position of character.
        // New position is the old position plus the distance moved as a result of the velocity
        // Change starts: CHARACTERCLASSOPTIMIZATION
        // Change ends: CHARACTERCLASSOPTIMIZATION

        setX(getX() + velocity.x * delta);
        setY(getY() + velocity.y * delta);

        // Get all characters in the currentLevel
        ArrayList<Character> otherCharacters = currentLevel.getCharacters();
        // Remove this character otherwise it will collide with itself
        otherCharacters.remove(this);

        // Change starts: COLLISIONUPDATE
        for (Character character : otherCharacters) {
            collidesWith(character, true);
        }

        if(this.speed >= 300){
            currentLevel.isBlocked(this);
            currentLevel.isBlocked(this);
        }
        currentLevel.isBlocked(this);
        // Change ends: COLLISIONUPDATE

    }

    //Change starts: KNOCKBACK
    public void knockback(){
        float oldX = getX(), oldY = getY();

        setX(getX() - (0.1f * velocity.x));
        setY(getY() - (0.1f * velocity.y));
        if(currentLevel != null) {
            currentLevel.isBlocked(this);
        }

    }
    //Change ends; KNOCKBACK

    //Change starts: CUREEFFECT
    public boolean isRunning(){
        return running;
    }

    // When the cure is activated/deactivated, toggles running
    public void toggleRunning(){
        if (running){
            running = false;
        } else {
            running = true;
        }
    }
    //Change ends: CUREEFFECT

    // Decreases health by value of dmg
    public void takeDamage(int dmg){
        health -= dmg;
    }
}
