package com.hangover;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

//Class to allow a TiledMapRenderer to be added to a stage
public class TileMapRendererActor extends Actor {

	//Stores a renderer
	private OrthogonalTiledMapRenderer renderer;
	
	//Initialises renderer using tiledmap
	public TileMapRendererActor(TiledMap t) {
		renderer = new OrthogonalTiledMapRenderer(t);
	}
	
	//Sets renderer's view
	public void setView(OrthographicCamera c) {
		renderer.setView(c);
	}
	
	//Sets the tiledmap
	public void setMap(TiledMap t) {
		renderer.setMap(t);
	}
	
	//calls the renderer's render function
	@Override
	public void draw(Batch b, float a) {
		renderer.render();
	}
}
