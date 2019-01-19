package com.tests;




import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.hangover.ImageActor;

import org.junit.Assert;

public class ImageActorTests {
	
	static Application application;
	public static ImageActor i;
	public static ImageActor j;
	
	@BeforeClass
    public static void init() {
        application = new HeadlessApplication(new ApplicationListener() {
            @Override public void create() {}
            @Override public void resize(int width, int height) {}
            @Override public void render() {}
            @Override public void pause() {}
            @Override public void resume() {}
            @Override public void dispose() {}
        });

        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
        i = new ImageActor();
        j = new ImageActor();
    }
	
	@AfterClass
    public static void cleanUp() {
        application.exit();
        application = null;
    }



	@Test
	public void testImageActor() {
		i = new ImageActor();

		Assert.assertEquals(0, i.getX(), 0);
		Assert.assertEquals(0, i.getY(), 0);
		Assert.assertEquals(0, i.getWidth(), 0);
		Assert.assertEquals(0, i.getHeight(), 0);
		Assert.assertNull(i.getImage());
		Assert.assertNotNull(i.getBoundingPolygon());
		
		
	}

	@Test
	public void testImageActorString() {
		j = new ImageActor("badlogic.jpg");

		Assert.assertEquals(256, j.getWidth(),0);
		Assert.assertEquals(256, j.getHeight(),0);
		Assert.assertNotNull(j.getImage());
		
	}

	@Test
	public void testSetImage() {
		i = new ImageActor();
		i.setImage("badlogic.jpg");

		Assert.assertEquals(256, i.getWidth(),0);
		Assert.assertEquals(256, i.getHeight(),0);
		Assert.assertNotNull(i.getImage());
		
	}

	@Test
	public void testGetImage() {
		i = new ImageActor();
		j = new ImageActor("badlogic.jpg");

		Assert.assertNull(i.getImage());
		Assert.assertNotNull(j.getImage());
		
	}

	@Test
	public void testCloneImageActor() {
		i = new ImageActor();
		j = new ImageActor();
		j.setPosition(30, 30);
		j.setImage("badlogic.jpg");
		i.clone(j);
		Assert.assertEquals(i.getImage(), j.getImage());
		Assert.assertEquals(i.getX(), j.getX(), 0);
		Assert.assertEquals(i.getY(), j.getY(), 0);
		Assert.assertEquals(i.getWidth(), j.getWidth(), 0);
		Assert.assertEquals(i.getHeight(), j.getHeight(), 0);
		
	}

	@Test
	public void testSetOrigin() {
		j = new ImageActor("badlogic.jpg");
		j.setOrigin();
		Assert.assertEquals(128, j.getOriginX(), 0);
		Assert.assertEquals(128, j.getOriginY(), 0);
		
	}

	@Test
	public void testSetRectangleBoundary() {
		j.setImage("badlogic.jpg");
		float[] vertices = {0,0,256, 0,256,256,0,256};
		j.setRectangleBoundary();

		Assert.assertArrayEquals(vertices, j.getBoundingPolygon().getVertices(), 0);
		
	}

	@Test
	public void testSetEllipseBoundary() {
		j.setEllipseBoundary();
		Assert.assertEquals(8, j.getBoundingPolygon().getVertices().length,0);
		
	}

	@Test
	public void testSetBoundingPolygon() {
		j.setImage("badlogic.jpg");
		float[] vertices = {0,0,0,256,256,256,256,0};
		
		j.setBoundingPolygon(vertices);

		Assert.assertArrayEquals(vertices, j.getBoundingPolygon().getVertices(), 0);
		
	}

	@Test
	public void testGetBoundingPolygon() {
		j = new ImageActor();
		j.setRectangleBoundary();
		Assert.assertNotNull(j.getBoundingPolygon());
		
	}

	@Test
	public void testOverlaps() {
		j = new ImageActor("badlogic.jpg");
		i = new ImageActor("badlogic.jpg");
		i.setRectangleBoundary();
		j.setRectangleBoundary();
		Assert.assertTrue(i.overlaps(j,true));
		Assert.assertFalse(i.overlaps(j, false));
		
	}

}
