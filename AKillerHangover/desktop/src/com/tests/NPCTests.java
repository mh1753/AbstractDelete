package com.tests;

import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.hangover.NPC;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

public class NPCTests {
	
	private static Application application;
	static NPC n;

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
        
        n = new NPC();
    }
	
	@AfterClass
    public static void cleanUp() {
        application.exit();
        application = null;
    }
	
	@Test
	public void testNPC() {
		n = new NPC();
		Assert.assertEquals(true, n.getFriendly());
		
		System.out.println("    testNPC() : passed");
	}
	
	@Test
	public void testSetFriendly() {
		n = new NPC();
		n.setFriendly(false);
		Assert.assertEquals(false, n.getFriendly());
		
		System.out.println("    testSetFriendly() : passed");
	}
	
	@Test
	public void testGetFriendly() {
		n = new NPC();
		Assert.assertEquals(false, n.getFriendly());
		
		System.out.println("    testGetFriendly() : passed");
	}
	
	@Test
	public void testSimpleChasePlayer() {
		n = new NPC();
		n.simpleChasePlayer(1, -1);
		n.setSpeed(10);
		n.setVelocityFromAngle();
		Assert.assertEquals(7, n.getVelocity().x, 0.1);
		Assert.assertEquals(-7, n.getVelocity().y, 0.1);
		
		n.simpleChasePlayer(-1, -1);
		n.setVelocityFromAngle();
		Assert.assertEquals(-7, n.getVelocity().x, 0.1);
		Assert.assertEquals(-7, n.getVelocity().y, 0.1);
		
		n.simpleChasePlayer(1, 1);
		n.setVelocityFromAngle();
		Assert.assertEquals(7, n.getVelocity().x, 0.1);
		Assert.assertEquals(7, n.getVelocity().y, 0.1);
		
		n.simpleChasePlayer(-1, 1);
		n.setVelocityFromAngle();
		Assert.assertEquals(-7, n.getVelocity().x, 0.1);
		Assert.assertEquals(7, n.getVelocity().y, 0.1);
		
		System.out.println("    testSimpleChasePlayer() : passed");
	}

}
