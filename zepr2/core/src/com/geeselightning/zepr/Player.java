package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player extends Character {

    private static final Player instance = new Player(new Sprite(new Texture("player01.png")), new Vector2(0, 0));
    int attackDamage = Constant.PLAYERDMG;
    // Change starts: PLAYERCLASSOPTIMIZATION
    private float hitDuration = 0;
    private Texture mainTexture;
    private Texture attackTexture;
    float HPMult;
    private float dmgMult;
    private float speedMult;
    private String playertype;
    boolean isImmune;
    // Change ends: PLAYERCLASSOPTIMIZATION
    boolean attack = false;
    boolean abilityActivated = false;
    float abilityCooldown = 0;
    float abilityDuration = 0;


    private Player(Sprite sprite, Vector2 playerSpawn) {
        super(sprite, playerSpawn, null);
        //Change starts: INITPLAYERBOUND
        setBoundRect();
        //Change ends: INITPLAYERBOUND
    }

    //Change starts: SETPLAYERBOUND
    public void setBoundRect(){
        boundRect.setPosition(this.getX() + this.getRegionWidth()/10f, this.getY() + this.getRegionHeight()/10f);
        boundRect.setSize(4 * this.getRegionWidth()/5f, 4 * this.getRegionHeight()/5f);
        boundRect.setCenter(this.getCenter());
    }
    //Change starts: SETPLAYERBOUND

    public static Player getInstance(){
        return instance;
    }

    public void setType(String playertype){
        this.playertype = playertype;
    }

    public String getType() {
        return playertype;
    }

    // Changed to stop player from constantly attacking
    @Override
    public void attack(Character zombie, float delta) {
        // Change starts: PLAYERCLASSOPTIMIZATION
        if (canHitGlobal(zombie, Constant.PLAYERRANGE) &&
                hitRefresh > Constant.PLAYERHITCOOLDOWN && hitDuration >= 0) {
            // Change ends: PLAYERCLASSOPTIMIZATION
            zombie.takeDamage(attackDamage);
            //Change starts: APPLYKNOCKBACK
            zombie.knockback();
            //Change ends: APPLYKNOCKBACK
            hitRefresh = 0;
        } else {
        	hitRefresh += delta;
        }
    }
    
    // Added to implement player abilities
    public void activateAbility() {
        // Change starts: PLAYERCLASSOPTIMIZATION
    	if (playertype.equals("nerdy")) {
    		abilityCooldown = Constant.NERDYABILITYCOOLDOWN;
    		abilityDuration = Constant.NERDYABILITYDURATION;
            dmgMult = Constant.NERDYABILITYDMGMULT;
    		this.attackDamage = (int)(Constant.PLAYERDMG * dmgMult);
            mainTexture = new Texture("player01_power.png");
            attackTexture = new Texture("player01_attack_power.png");
    	}
    	if (playertype.equals("sporty")) {
    		abilityCooldown = Constant.SPORTYABILITYCOOLDOWN;
    		abilityDuration = Constant.SPORTYABILITYDURATION;
            speedMult = Constant.SPORTYABILITYSPEEDMULT;
    		this.speed = (int)(Constant.PLAYERSPEED * speedMult);
    	}
    	if (playertype.equals("drama")) {
    		if ((this.health + 10) > (int)(this.HPMult * Constant.PLAYERMAXHP)) {
        		abilityCooldown = Constant.DRAMAABILITYCOOLDOWN;
        		abilityDuration = Constant.DRAMAABILITYDURATION;
    			this.health = (int)(this.HPMult * Constant.PLAYERMAXHP);
    		} else {
        		abilityCooldown = Constant.DRAMAABILITYCOOLDOWN;
        		abilityDuration = Constant.DRAMAABILITYDURATION;
        		this.health += 10;
    		}
            mainTexture = new Texture("player03_heal.png");
            attackTexture = new Texture("player03_attack_heal.png");
            // Change ends: PLAYERCLASSOPTIMIZATION
    	}
    }
    
    // Added to implement player abilities
    public void deactivateAbility() {
        // Change starts: PLAYERCLASSOPTIMIZATION
    	if (playertype.equals("nerdy")) {
            dmgMult = Constant.NERDYDMGMULT;
            this.attackDamage = (int)(Constant.PLAYERDMG * dmgMult);
            mainTexture = new Texture("player01.png");
            attackTexture = new Texture("player01_attack.png");
    	}
    	if (playertype.equals("sporty")) {
            speedMult = Constant.SPORTYSPEEDMULT;
    		this.speed = (int)(Constant.PLAYERSPEED * speedMult);
    	}
    	if (playertype.equals("drama")) {
            mainTexture = new Texture("player03.png");
            attackTexture = new Texture("player03_attack.png");
    	}
    	// Change ends: PLAYERCLASSOPTIMIZATION
    }

    public void respawn(Vector2 playerSpawn, Level level){
        setX(playerSpawn.x);
        setY(playerSpawn.y);
        // Added rest information below so player doesn't move and attack when they spawn
        abilityDuration = 0;
        this.deactivateAbility();
        abilityCooldown = 0;
        attack = false;
        this.velocity = new Vector2(0, 0);
        // Change starts: PLAYERCLASSOPTIMIZATION
        assert playertype != null;
        if (playertype.equals("nerdy")){
            dmgMult = Constant.NERDYDMGMULT;
            HPMult = Constant.NERDYHPMULT;
            speedMult = Constant.NERDYSPEEDMULT;
        }
        else if (playertype.equals("sporty")){
            dmgMult = Constant.SPORTYDMGMULT;
            HPMult = Constant.SPORTYHPMULT;
            speedMult = Constant.SPORTYSPEEDMULT;
        }
        // Added for third player type
        else if (playertype.equals("drama")){
            dmgMult = Constant.DRAMADMGMULT;
            HPMult = Constant.DRAMAHPMULT;
            speedMult = Constant.DRAMASPEEDMULT;
        }
        //Change starts: ZOMBIESTORY
        else if (playertype.equals("zombie")){
            dmgMult = Constant.ZOMBIEDMGMULT;
            HPMult = Constant.ZOMBIEHPMULT;
            speedMult = Constant.ZOMBIESPEEDMULT;
        }
        //Change ends: ZOMBIESTORY
        this.attackDamage = (int)(Constant.PLAYERDMG * dmgMult);
        this.speed = (int)(Constant.PLAYERSPEED * speedMult);
        this.health = (int)(HPMult * Constant.PLAYERMAXHP);
        this.currentLevel = level;

        if (playertype.equals("nerdy")) {
            mainTexture = new Texture("player01.png");
            attackTexture = new Texture("player01_attack.png");
            this.setTexture(mainTexture);
        } else if (playertype.equals("sporty")){
            mainTexture = new Texture("player02.png");
            attackTexture = new Texture("player02_attack.png");
            this.setTexture(mainTexture);
        } else if (playertype.equals("drama")){
            mainTexture = new Texture("player03.png");
            attackTexture = new Texture("player03_attack.png");
            this.setTexture(mainTexture);
        }
        //Change starts: ZOMBIESTORY
        else if (playertype.equals("zombie")){
            if (currentLevel.parent.getLastKnownCharacter().equals("nerdy")){
                mainTexture = new Texture("nerdZombie.png");
                this.setTexture(mainTexture);
            } else if (currentLevel.parent.getLastKnownCharacter().equals("sporty")){
                mainTexture = new Texture("sportyZombie.png");
                this.setTexture(mainTexture);
            } else if (currentLevel.parent.getLastKnownCharacter().equals("drama")){
                mainTexture = new Texture("dramaZombie.png");
                this.setTexture(mainTexture);
            } else {
                mainTexture = new Texture("zombie01.png");
                this.setTexture(mainTexture);
            }
        }
        // Change ends: ZOMBIESTORY
        // Change ends: PLAYERCLASSOPTIMIZATION
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        // Update the direction the player is facing.
        direction = getDirectionTo(currentLevel.getMouseWorldCoordinates());


        // When you die, end the level.
        if (health <= 0) {
            currentLevel.gameOver();
        }

        // Changed to stop player from constantly attacking
        // When hitDuration == 0 player can attack
        if (attack && hitDuration == 0) {
        	hitDuration = 0.2f;
        }
        if (hitDuration > 0) {
            // Change starts: ZOMBIESTORY
            // If the player is a zombie, don't need to perform an attack animation
            if (!currentLevel.parent.isZombie()) {
                this.setTexture(attackTexture);
            }
            // Change ends: ZOMBIESTORY
        	hitDuration -= delta;
        } else {
            // Change starts: ZOMBIESTORY
            // If the player is a zombie, they will always use the main texture
            if (!currentLevel.parent.isZombie()) {
                this.setTexture(mainTexture);
            }
            // Change ends: ZOMBIESTORY
        	attack = false;
        	hitDuration = 0;
        }
        
        // Added to implement player abilities
        if (abilityDuration <= 0 && abilityActivated) {
        	abilityActivated = false;
        	this.deactivateAbility();
        } else if (abilityDuration > 0) {
        	abilityDuration -= delta;
        }
        
        // Added to implement player abilities
        if (abilityCooldown <= 0) {
        	if (abilityActivated) {
            	this.activateAbility();
            }
        } else {
        	abilityCooldown -= delta;
        }
    }

    @Override
    public void takeDamage(int dmg){
        if(!isImmune){
            //If powerUpImmunity is activated
            health -= dmg;
        }
    }

    // Change starts; PLAYERGETDAMAGE
    public float getDamage(){
        return attackDamage;
    }
    // Change ends; PLAYERGETDAMAGE

    // Change starts; PLAYERGETABILITYCOOLDOWN
    public float getAbilityCooldown(){
        return abilityCooldown;
    }
    // Change ends; PLAYERGETABILITYCOOLDOWN

    // Change starts; PLAYERGETABILITYDURATION
    public float getAbilityDuration(){
        return abilityDuration;
    }
    // Change ends; PLAYERGETABILITYDURATION
}
