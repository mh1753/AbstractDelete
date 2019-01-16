package com.tests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.hangover.Entity;
import com.hangover.ResourceManager;

public class EntityTests {

	ResourceManager r = new ResourceManager();
	private static Application application;
	public static Entity e;
	
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
        e = new Entity();
    }
	
	@AfterClass
    public static void cleanUp() {
        application.exit();
        application = null;
    }
	

	@Test
	public void testEntity() {
        e = new Entity();

		Assert.assertEquals(0, e.getSpeed(), 0);
		Assert.assertEquals(0, e.getMaxHealth(), 0);
		Assert.assertEquals(0, e.getHealth(), 0);
		Assert.assertEquals(0, e.getAngle(), 0);
		Assert.assertEquals("Default", e.getType());
		
		System.out.println("    testEntity() : passed");
	}

	@Test
	public void testEntityStringResourceManager() {
        e = new Entity();
        
        e = new Entity("badlogic", r);

		Assert.assertEquals(r.getEntity("badlogic").getSpeed(), e.getSpeed(), 0);
		Assert.assertEquals(r.getEntity("badlogic").getMaxHealth(), e.getMaxHealth(), 0);
		Assert.assertEquals(r.getEntity("badlogic").getHealth(), e.getHealth(), 0);
		Assert.assertEquals(r.getEntity("badlogic").getType(), e.getType());
		
	}

	@Test
	public void testSetSpeed() {
        e = new Entity();

		e.setSpeed(100);
		Assert.assertEquals(100, e.getSpeed(),0);
		
	}

	@Test
	public void testGetSpeed() {
        e = new Entity();

		e.setSpeed(10);
		Assert.assertEquals(10, e.getSpeed(),0);
		
	}

	@Test
	public void testSetAngle() {
        e = new Entity();

		e.setAngle(100);
		Assert.assertEquals(100, e.getAngle(),0);
		
	}

	@Test
	public void testGetAngle() {
        e = new Entity();

		e.setAngle(10);
		Assert.assertEquals(10, e.getAngle(),0);
		
	}

	@Test
	public void testSetMaxHealth() {
        e = new Entity();

		e.setMaxHealth(10000);
		Assert.assertEquals(10000, e.getMaxHealth(), 0);
		
	}

	@Test
	public void testGetMaxHealth() {
        e = new Entity();

		e.setMaxHealth(100);
		Assert.assertEquals(100, e.getMaxHealth(), 0);
		
	}

	@Test
	public void testAddHealth() {
        e = new Entity();

		e.addHealth(1);
		Assert.assertEquals(100, e.getHealth(), 0);
		e.addHealth(-1);
		Assert.assertEquals(99, e.getHealth(), 0);
		e.addHealth(1);
		Assert.assertEquals(100, e.getHealth(), 0);
		System.out.println("    testAddHealth() : passed");
	}

	@Test
	public void testTakeHealth() {
        e = new Entity();

		e.takeHealth(101);
		Assert.assertEquals(0, e.getHealth(), 0);
		e.takeHealth(-1);
		Assert.assertEquals(1, e.getHealth(), 0);
		e.takeHealth(1);
		Assert.assertEquals(0, e.getHealth(), 0);
		System.out.println("    testTakeHealth() : passed");
	}

	@Test
	public void testGetHealth() {
        e = new Entity();

		Assert.assertEquals(0, e.getHealth(), 0);
	}

	@Test
	public void testSetType() {
        e = new Entity();

		e.setType("Default");
		Assert.assertEquals("Default", e.getType());
	}

	@Test
	public void testGetType() {
        e = new Entity();

		Assert.assertEquals("Default", e.getType());
	}

	@Test
	public void testCloneEntity() {
        e = new Entity();

		e.clone(r.getEntity("badlogic"));
		Assert.assertEquals(r.getEntity("badlogic").getSpeed(), e.getSpeed(), 0);
		Assert.assertEquals(r.getEntity("badlogic").getMaxHealth(), e.getMaxHealth(), 0);
		Assert.assertEquals(r.getEntity("badlogic").getHealth(), e.getHealth(), 0);
		Assert.assertEquals(r.getEntity("badlogic").getType(), e.getType());
	}

	@Test
	public void testSetVelocityFromAngle() {
        e = new Entity();

		e.setVelocityFromAngle();
		Assert.assertEquals(new Vector2((float)(Math.sin(e.getAngle()) * e.getSpeed()),(float)Math.cos(e.getAngle()) * e.getSpeed()), e.getVelocity());
		
	}
	
	@Test
	public void testAct() {
        e = new Entity();

		e.setAngle(-e.getAngle());
		e.act(1);
		Assert.assertNotEquals(new Vector2((float)(Math.cos(e.getAngle()) * e.getSpeed()),(float)Math.sin(e.getAngle()) * e.getSpeed()), 
				new Vector2(e.getX(), e.getY()));
		
	}

}
