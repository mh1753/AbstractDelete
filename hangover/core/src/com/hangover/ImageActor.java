package com.hangover;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ImageActor extends Actor {

	//Stores the actor's image
	protected TextureRegion t;
	
	//Actor's boundary for use in collision detection
	protected Polygon boundingPolygon;
	
	//Is actor living?
	protected boolean living;
	
	//Is actor's image subject to a filter?
	protected boolean filteredImage;
	
	//Initialises ImageActor
	public ImageActor() {
		super();
		filteredImage = false;
		living = true;
		setWidth(0);
		setHeight(0);
		setX(0);
		setY(0);
		setRectangleBoundary();
	}
	
	//Initialises image actor and loads image from url
	public ImageActor(String url) {
		//loads image from url
		super();
		living = true;
		setX(0);
		setY(0);
		boundingPolygon = null;
		setImage(url);
	}
	
	//loads image from url
	public void setImage(String url) {
		t = new TextureRegion(new Texture(url));
		setWidth(t.getRegionWidth());
		setHeight(t.getRegionHeight());
		setOrigin();
		setRectangleBoundary();
	}
	
	//returns image
	public TextureRegion getImage() {
		return t;
	}
	
	//Sets whether the image has a colour filter or not
	public void setFiltered(boolean f) {
		filteredImage = f;
	}
	
	//Checks if image is filtered or not
	public boolean isFiltered() {
		return filteredImage;
	}
	
	//handles drawing of the image
	@Override
	public void draw(Batch b, float dt) {
		if(t != null && isLiving()) {
			Color prev;
			if(isFiltered()) {
				//Stores batch current filter
				prev = b.getColor();
				
				//sets batch filter to this image's filter and draws image
				b.setColor(getColor());
				b.draw(t, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
				
				//Restores batch's original filter
				b.setColor(prev);
			}
			else {
				b.draw(t, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
			}
		}
	}
	
	//Makes this imageActor a copy of the one given
	public void clone(ImageActor a) {
		t = a.getImage();
		setWidth(t.getRegionWidth());
		setHeight(t.getRegionHeight());
		if(boundingPolygon != null) {
			boundingPolygon = a.getBoundingPolygon();
		}
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
		boundingPolygon.setOrigin(getWidth()/2, getHeight()/2);
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

	//set the collision boundary from an array of vertices
	public void setBoundingPolygon(float[] vertices){
		boundingPolygon = new Polygon(vertices);
		boundingPolygon.setOrigin(getOriginX(), getOriginY());
	}

	//return the current collision boundary
	public Polygon getBoundingPolygon(){
		if(boundingPolygon != null) {
			boundingPolygon.setPosition(getX(), getY());
			boundingPolygon.setRotation(getRotation());
		}
		return boundingPolygon;
	}
	
	//Sets if imageActor is living
	public void setLiving(boolean l) {
		living = l;
	}
	
	//Checks if imageActor is living
	public boolean isLiving() {
		return living;
	}

	/**
	 * Determine if 2 collision polygons overlap
	 * Return true if overlap occurs.
	 * If resolve == true, then when overlap occurs, move this actor along the minimum translation
	 * vector until there is no longer overlap.
	 */
	public boolean overlaps(ImageActor o, boolean resolve){
		if(!isLiving() || !o.isLiving()) {
			return false;
		}
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
	
	//Same overlaps function as above but with a polygon as parameter
	public boolean overlaps(Polygon poly, boolean resolve){
		if(!isLiving()) {
			return false;
		}
		Polygon poly1 = this.getBoundingPolygon();
		if (!Intersector.overlapConvexPolygons(boundingPolygon, poly)){
			return false;
		}

		MinimumTranslationVector mtv = new MinimumTranslationVector();
		boolean polyOverlap = Intersector.overlapConvexPolygons(poly1, poly, mtv);
		if (polyOverlap && resolve){
			this.moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth);
		}
		float significant = 0.5f;
		return (polyOverlap && (mtv.depth > significant));
	}
	
	//Same as other overlaps but with rectangles
	public boolean overlaps(Rectangle rect, boolean resolve){
		if(!isLiving()) {
			return false;
		}
		Polygon poly1 = this.getBoundingPolygon();
		if ( !poly1.getBoundingRectangle().overlaps(rect)){
			return false;
		}

		MinimumTranslationVector mtv = new MinimumTranslationVector();
		Polygon p = new Polygon();
		p.setVertices(new float[] {rect.x, rect.y, rect.x + rect.width, rect.y,
				rect.x + rect.width, rect.y + rect.height, rect.x, rect.y + rect.height	
		});
		boolean polyOverlap = Intersector.overlapConvexPolygons(poly1, p, mtv);
		if (polyOverlap && resolve){
			this.moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth);
		}
		float significant = 0.5f;
		return (polyOverlap && (mtv.depth > significant));
	}

}
