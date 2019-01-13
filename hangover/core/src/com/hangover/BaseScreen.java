package com.hangover;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class BaseScreen implements Screen, InputProcessor {
	
	//The game that is running this screen
	public AKillerHangover g;
	
	//The resource manger
	public ResourceManager r;
	
	//Stages for actors
	public Stage backStage;
	public Stage entityStage;
	public Stage uiStage;
	
	//Decides whether actors should act or not
	public boolean paused;
	
	
	//Screen size
	public final int viewWidth = 1024;
	public final int viewHeight = 640;
	
	public BaseScreen(AKillerHangover g, ResourceManager r) {
		this.g = g;
		
		this.r = r;
		
		//Initialises paused
		paused = false;
		
		//inialises stages so they're the size of the screen
		backStage = new Stage(new FitViewport(viewWidth, viewHeight));
		entityStage = new Stage(new FitViewport(viewWidth, viewHeight));
		uiStage = new Stage(new FitViewport(viewWidth, viewHeight));
		
		//Handles input
		InputMultiplexer im = new InputMultiplexer(this, backStage, entityStage, uiStage);
		Gdx.input.setInputProcessor(im);
		
		//Runs create
		create();
	}
	
	public abstract void create() ;
	
	public abstract void update(float dt) ;
	
	

	@Override
	public void show() {
		
	}
	
	
	//handles the drawing and acting of the stages
	@Override
	public void render(float dt) {
		//Updates uistage
		uiStage.act(dt);
		if(!paused) {
			//if not paused, update backstage and entities
			backStage.act(dt);
			entityStage.act(dt);
			update(dt);
		}
		
		//Clears screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Draws stages in order
		backStage.draw();
		entityStage.draw();
		uiStage.draw();

		
	}

	//Code to be run when resizing screen
	@Override
	public void resize(int width, int height) {
		
		
	}
	
	//Returns paused
	public boolean isPaused(){
	    return paused;
    }

	//Sets paused
    public void setPaused(boolean b){
	    paused = b;
    }

    //Toggles paused
    public void togglePaused(){
	    paused = !paused;
    }

    //pauses
	@Override
	public void pause() {
		paused = true;
		
	}

	//unpauses
	@Override
	public void resume() {
		paused = false;
		
	}

	@Override
	public void hide() {
		
		
	}

	
	//Clears up when screen disposed of
	@Override
	public void dispose() {
		System.out.println("Disposing");
		backStage.dispose();
		entityStage.dispose();
		uiStage.dispose();
		
	}

}
