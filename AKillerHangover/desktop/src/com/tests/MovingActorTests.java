package com.tests;


import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.hangover.MovingActor;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

public class MovingActorTests {
	
	private static Application application;
	public static MovingActor m;
	
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
        m = new MovingActor();
    }
	
	@AfterClass
    public static void cleanUp() {
        application.exit();
        application = null;
    }

	@Test
	public void test() {
		m = new MovingActor();
		m.setAcceleration(1, 1);
		m.setVelocity(1, 1);
		m.setAngularVel(1);
		m.setPosition(0,0);
		
		m.setMoving(true);
		Assert.assertEquals(new Vector2(1, 1), m.getVelocity());
		Assert.assertEquals(new Vector2(1, 1), m.getAcceleration());
		Assert.assertEquals(1, m.getAngularVel(), 0);
		m.act(1);
		Assert.assertEquals(new Vector2(1, 1), new Vector2(m.getX(), m.getY()));
		Assert.assertEquals(new Vector2(2, 2), m.getVelocity());
		Assert.assertEquals(1, m.getRotation(), 0);
		m.setMoving(false);
		m.act(1);
		Assert.assertEquals(new Vector2(1, 1), new Vector2(m.getX(), m.getY()));
		Assert.assertEquals(new Vector2(2, 2), m.getVelocity());
		Assert.assertEquals(1, m.getRotation(), 0);
	}

}
