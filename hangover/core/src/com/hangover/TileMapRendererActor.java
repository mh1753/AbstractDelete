package com.hangover;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TileMapRendererActor extends Actor {

	private OrthogonalTiledMapRenderer renderer;
	
	public TileMapRendererActor(TiledMap t) {
		renderer = new OrthogonalTiledMapRenderer(t);
	}
	
	public void setView(OrthographicCamera c) {
		renderer.setView(c);
	}
	
	public void setMap(TiledMap t) {
		renderer.setMap(t);
	}
	
	@Override
	public void draw(Batch b, float a) {
		renderer.render();
	}
}
