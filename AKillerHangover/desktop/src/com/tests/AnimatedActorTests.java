package com.tests;

import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.hangover.AnimatedActor;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

public class AnimatedActorTests {
	
	private static Application application;
	public static AnimatedActor ai;
	public static AnimatedActor aj;

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
		ai = new AnimatedActor();
		aj = new AnimatedActor();
    }
	
	@AfterClass
    public static void cleanUp() {
        application.exit();
        application = null;
    }

	@Test
	public void testAnimatedActor() {
		ai = new AnimatedActor();
		Assert.assertNull(ai.currentAnim);
		Assert.assertEquals(0, ai.timing, 0);
	}
	
	@Test
	public void testSetFrameSize() {
		ai = new AnimatedActor();
		ai.setFrameSize(32, 32);
		Assert.assertEquals(32, ai.getWidth(), 0);
		Assert.assertEquals(32, ai.getHeight(), 0);
	}
	
	@Test
	public void testGetFrameSize() {
		ai = new AnimatedActor();
		ai.setFrameSize(32, 32);
		Assert.assertEquals(new Vector2(32, 32), ai.getFrameSize());
	}
	
	@Test
	public void testStoreAnim() {
		ai.storeAnim("badlogic.jpg", "badlogic", 10, 10);
		Assert.assertNotNull(ai.getAnims());
	}
	
	@Test
	public void testGetAnims() {
		aj = new AnimatedActor();
		Assert.assertTrue(aj.getAnims().values().isEmpty());
		aj.storeAnim("badlogic.jpg", "badlogic", 10, 10);
		Assert.assertFalse(aj.getAnims().values().isEmpty());
	}

	@Test
	public void testSetAnim() {
		aj = new AnimatedActor();
		aj.storeAnim("badlogic.jpg", "badlogic", 10, 10);
		aj.setAnim("badlogic");
		Assert.assertNotNull(aj.currentAnim);
	}
	
	@Test
	public void testCloneAnimatedActor() {
		aj = new AnimatedActor();
		ai = new AnimatedActor();
		ai.storeAnim("badlogic.jpg", "badlogic", 10, 10);
		aj.clone(ai);
		Assert.assertFalse(aj.getAnims().values().isEmpty());
	}
	
	@Test
	public void testAct() {
		ai = new AnimatedActor();
		ai.act(10);
		Assert.assertNotEquals(ai.timing,aj.timing,0);
	}

}
