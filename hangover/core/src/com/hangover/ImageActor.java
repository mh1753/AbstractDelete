package com.hangover;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ImageActor extends Actor {

	//Stores the actor's image
	protected TextureRegion t;
	
	//Actor's boundary for use in collision detection
	protected Polygon boundingPolygon;
	
	public ImageActor() {
		super();
		setWidth(0);
		setHeight(0);
		setX(0);
		setY(0);
	}
	
	public ImageActor(String url) {
		//loads image from url
		setX(0);
		setY(0);
		setImage(url);
	}
	
	//loads image from url
	public void setImage(String url) {
		t = new TextureRegion(new Texture(url));
		setWidth(t.getRegionWidth());
		setHeight(t.getRegionHeight());
		setOrigin();
	}
	
	//returns image
	public TextureRegion getImage() {
		return t;
	}
	
	//handles drawing of the image
	@Override
	public void draw(Batch b, float dt) {
		b.draw(t, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	public void clone(ImageActor a) {
		t = a.getImage();
		setWidth(t.getRegionWidth());
		setHeight(t.getRegionHeight());
	}

	//sets origin to centre of the actor
	public void setOrigin() {
		setOrigin(getWidth()/2, getHeight()/2);
	}

	//set the collision boundary to a rectangle around the actor
	public void setRectangleBoundary(){
		float w = getWidth();
		float h = getHeight();
		float[] vertices = {0,0, w,0, w,h, 0,h};
		boundingPolygon = new Polygon(vertices);
	}

	//set the collision boundary to an ellipse around the actor
	public void setEllipseBoundary(){
		int n = 8; //the number of vertices
		float w = getWidth();
		float h = getHeight();
		float[] vertices = new float[2*n];
		for (int i = 0; i < n; i++){
			float t = i * 6.28f / n;
			//x-coordinates
			vertices[2*i] = w/2 * MathUtils.cos(t) + w/2;
			//y-coordinates
			vertices[2*i +1] = h/2 * MathUtils.sin(t) + h/2;
		}
	}

	public void setBoundingPolygon(float[] vertices){
		boundingPolygon = new Polygon(vertices);
		boundingPolygon.setOrigin(getOriginX(), getOriginY());
	}

	public Polygon getBoundingPolygon(){
		boundingPolygon.setPosition(getX(), getY());
		boundingPolygon.setRotation(getRotation());
		return boundingPolygon;
	}

	/**
	 * Determine if 2 collision polygons overlap
	 * Return true if overlap occurs.
	 * If resolve == true, then when overlap occurs, move this actor along the minimum translation
	 * vector until there is no longer overlap.
	 */
	public boolean overlaps(ImageActor o, boolean resolve){
		Polygon poly1 = this.getBoundingPolygon();
		Polygon poly2 = o.getBoundingPolygon();
		if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle())){
			return false;
		}

		MinimumTranslationVector mtv = new MinimumTranslationVector();
		boolean polyOverlap = Intersector.overlapConvexPolygons(poly1, poly2, mtv);
		if (polyOverlap && resolve){
			this.moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth);
		}
		float significant = 0.5f;
		return (polyOverlap && (mtv.depth > significant));
	}

}
