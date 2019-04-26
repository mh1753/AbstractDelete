/**
 * Added by Shaun of the Devs to meet the requirement of a minigame
 */
package com.geeselightning.zepr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class BonusLevel implements Screen {

    // Change starts: BONUSGAMEOPTIMIZATION
    // Importing the necessary assets for the button textures.
    private Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    // Change ends: BONUSGAMEOPTIMIZATION

	protected Zepr parent;
    private Stage stage;
    private Stage updateStage;
    private SpriteBatch renderer;
    private int score;
    private int goalScore;
    private Table gameInfo;
    // Change starts: BONUSGAMEOPTIMIZATION
    // Change ends: BONUSGAMETOPTIMIZATION
    private BonusGoose goose1;
    private BonusGoose goose2;
    private BonusGoose goose3;
    // Change starts: BONUSGAMEOPTMIZATION
    // Targets placed an equidistance apart
    private int target1X = 428;
    private int target2X = 608;
    private int target3X = 788;
    private int targetY = 390;
    // Change ends: BONUSGAMEOPTIMIZATION
    private float timer = 60;

    // Change starts: BONUSGAMEOPTIMIZATION
    // Load in necessary textures
    private Texture cannonTex = new Texture("cannon.png");
    private Texture targetTex = new Texture("target.png");
    // Change ends: BONUSGAMEOPTIMIZATION
	
	public BonusLevel(Zepr zepr) {
		this.parent = zepr;

        // The stage is the controller which will react to inputs from the user.
        this.stage = new Stage(new ScreenViewport());
        this.updateStage = new Stage(new ScreenViewport());
        
        // Score to win minigame
        this.goalScore = Constant.BONUSGOAL;
        
        // For table to display score
        gameInfo = new Table();
        
        // To render sprites for the game.
        renderer = new SpriteBatch();
        
        // Geese settings with arbitrary spawn points
        goose1 = new BonusGoose(target1X + 100, targetY - 50);
        goose2 = new BonusGoose(target2X + 100, targetY + 20);
        goose3 = new BonusGoose(target3X + 100, targetY + 100);
	}
	
	@Override
	public void show() {
        // Send any input from the user to the stage.
        Gdx.input.setInputProcessor(stage);

        // Change starts: BONUSGAMEOPTIMIZATION
        // Change ends: BONUSGAMEOPTIMIZATION
        
        // Adding buttons to the screen
        TextButton back = new TextButton("Back", skin);

        TextButton right = new TextButton("Right", skin);
        TextButton middle = new TextButton("Middle", skin);
        TextButton left = new TextButton("Left", skin);
        
        Label fire = new Label("Fire Cannon", skin, "subtitle");
        fire.setWrap(true);
        fire.setWidth(100);
        fire.setAlignment(Align.center);
        
        // Adding menu bar.
        Table menuBar = new Table();
        menuBar.setFillParent(true);
        // menuBar.setDebug(true); // Adds borders for the table.
        stage.addActor(menuBar);

        menuBar.top().left();
        menuBar.row();
        menuBar.add(back).pad(10);
        
        // Adding fire buttons at the bottom.
        Table bottomTable = new Table();
        bottomTable.setFillParent(true);
        // bottomTable.setDebug(true); // Adds borders for the table.
        stage.addActor(bottomTable);
        
        bottomTable.bottom();
        bottomTable.row();
        bottomTable.add(fire).width(1000f).colspan(3);
        bottomTable.row().center();
        bottomTable.add(left).pad(10);
        bottomTable.add(middle).pad(10);
        bottomTable.add(right).pad(10);
        
        // Defining actions for the back button.
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(Zepr.SELECT);
            }
        });
        
        // Defining actions for the left button.
        //Change starts: BONUSHITBOXFIX
        left.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (Math.sqrt((goose1.getX() - target1X) * (goose1.getX() - target1X) +
                        (goose1.getY() - targetY) * (goose1.getY() - targetY)) < (64)){
                    score += 1;
                    goose1.respawn();
                }
                if (Math.sqrt((goose2.getX() - target1X) * (goose2.getX() - target1X) +
                        (goose2.getY() - targetY) * (goose2.getY() - targetY)) < (64)){
                    score += 1;
                    goose2.respawn();
                }
                if (Math.sqrt((goose3.getX() - target1X) * (goose3.getX() - target1X) +
                        (goose3.getY() - targetY) * (goose3.getY() - targetY)) < (64)){
                    score += 1;
                    goose3.respawn();
                }
            }
        });
        
        // Defining actions for the middle button.
        middle.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            	if (Math.sqrt((goose1.getX() - target2X) * (goose1.getX() - target2X) +
                        (goose1.getY() - targetY) * (goose1.getY() - targetY)) < (64)){
            	    score += 1;
            	    goose1.respawn();
                }
                if (Math.sqrt((goose2.getX() - target2X) * (goose2.getX() - target2X) +
                        (goose2.getY() - targetY) * (goose2.getY() - targetY)) < (64)){
                    score += 1;
                    goose2.respawn();
                }
                if (Math.sqrt((goose3.getX() - target2X) * (goose3.getX() - target2X) +
                        (goose3.getY() - targetY) * (goose3.getY() - targetY)) < (64)){
                    score += 1;
                    goose3.respawn();
                }
            }
        });
        
        // Defining actions for the right button.
        right.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (Math.sqrt((goose1.getX() - target3X) * (goose1.getX() - target3X) +
                        (goose1.getY() - targetY) * (goose1.getY() - targetY)) < (64)){
                    score += 1;
                    goose1.respawn();
                }
                if (Math.sqrt((goose2.getX() - target3X) * (goose2.getX() - target3X) +
                        (goose2.getY() - targetY) * (goose2.getY() - targetY)) < (64)){
                    score += 1;
                    goose2.respawn();
                }
                if (Math.sqrt((goose3.getX() - target3X) * (goose3.getX() - target3X) +
                        (goose3.getY() - targetY) * (goose3.getY() - targetY)) < (64)){
                    score += 1;
                    goose3.respawn();
                }
            }
        });
        //Change ends: BONUSHITBOXFIX
        
		
	}

	@Override
	public void render(float delta) {
        // Clears the screen to black.
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        // Rendering for sprites
        renderer.begin();
        
        // Behind geese
        // Change starts: BONUSGAMEOPTIMIZATION
        renderer.draw(new Texture("gooseHuntBackground.png"), 280, 180);
        // Change ends: BONUSGAMEOPTIMIZATION
        
        // Geese
        goose1.draw(renderer);
        goose2.draw(renderer);
        goose3.draw(renderer);
        
        // In front of geese
        // Change starts: BONUSGAMEOPTIMIZATION
        renderer.draw(cannonTex, target1X-32, 720/4+10);
        renderer.draw(cannonTex, target2X-32, 720/4+10);
        renderer.draw(cannonTex, target3X-32, 720/4 + 10);
        renderer.draw(targetTex, target1X, targetY);
        renderer.draw(targetTex, target2X, targetY);
        renderer.draw(targetTex, target3X, targetY);
        // Change ends: BONUSGAMEOPTIMIZATION
        
        renderer.end();

        // Change starts: BONUSGAMEOPTIMIZATION
        // Change ends: BONUSGAMEOPTIMIZATION
        
        // Creating game labels for gameInfo
        String scoreString = ("Score: " + score);
        String goalString = ("Goal: " + goalScore);
        String timerString = ("Timer: " + (int)timer);
        Label title = new Label("Goose Hunt", skin, "subtitle");
        Label scoreLabel = new Label(scoreString, skin);
        Label goalLabel = new Label(goalString, skin);
        Label timerLabel = new Label(timerString, skin);

        timer -= delta;
        
        // Adding game information
        gameInfo.clear();
        gameInfo.setFillParent(true);
        // menuBar.setDebug(true); // Adds borders for the table.
        updateStage.addActor(gameInfo);
        
        gameInfo.center().top();
        gameInfo.add(title).pad(30);
        gameInfo.add(scoreLabel).pad(10);
        gameInfo.add(goalLabel).pad(10);
        gameInfo.add(timerLabel).pad(10);

        // Draws the stage.
        this.updateStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        this.updateStage.draw();
        this.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        this.stage.draw();

        // Change starts: BONUSGAMEOPTIMIZATION
        /**
         * Game over conditions:
         * score >= goalScore player wins, BONUSGAMEPOINTS added to overall score, return to selectLevelScreen
         * else player returned to the selectLevelScreen
         */
        if (score >= goalScore) {
            // Change ends: BONUSGAMEOPTIMIZATION
            //Change starts: ADDBONUSGAMEPOINTS
            parent.addPoints(Constant.BONUSGAMEPOINTS);
            // Change ends: ADDBONUSGAMEPOINTS
            // Change starts: CURESPAWNCONDITION
            parent.addCureProg(Constant.BONUSGAMEPOINTS);
            // Change ends: CURESPAWNCONDITION
            parent.setScreen(new TextScreen(parent, "Bonus game completed."));
            // Change starts: BONUSGAMEOPTIMIZATION
            dispose();
            // Change ends: BONUSGAMEOPTIMIZATION
        }
        if (timer <= 0) {
        	parent.setScreen(new TextScreen(parent, "Bonus game failed."));
        	// Change starts: BONUSGAMEOPTIMIZATION
            dispose();
        	// Change ends: BONUSGAMEOPTIMIZATION
        }
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void dispose() {
	    // Change starts: BONUSGAMEOPTIMIZATION
	    skin.dispose();
		if (stage != null){
		    stage.dispose();
		    stage = null;
        }
        cannonTex.dispose();
        targetTex.dispose();
        // Change ends: BONUSGAMEOPTIMIZATION
		goose1.dispose();
		goose2.dispose();
		goose3.dispose();
	}

}
