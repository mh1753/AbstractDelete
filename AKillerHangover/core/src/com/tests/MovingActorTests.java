package com.tests;


import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import org.junit.Assert;

public class MovingActorTests {
	
	JUnitTester j;
	
	public MovingActorTests(JUnitTester j) {
		this.j = j;
		System.out.println("Testing MovingActor:");
	}

	@Test
	public void test() {
		j.m.setMoving(true);
		Assert.assertEquals(new Vector2(1, 1), j.m.getVelocity());
		Assert.assertEquals(new Vector2(1, 1), j.m.getAcceleration());
		Assert.assertEquals(1, j.m.getAngularVel(), 0);
		j.m.act(1);
		Assert.assertEquals(new Vector2(1, 1), new Vector2(j.m.getX(), j.m.getY()));
		Assert.assertEquals(new Vector2(2, 2), j.m.getVelocity());
		Assert.assertEquals(1, j.m.getRotation(), 0);
		j.m.setMoving(false);
		j.m.act(1);
		Assert.assertEquals(new Vector2(1, 1), new Vector2(j.m.getX(), j.m.getY()));
		Assert.assertEquals(new Vector2(2, 2), j.m.getVelocity());
		Assert.assertEquals(1, j.m.getRotation(), 0);
		
		System.out.println("    testMovingActor() : passed");
	}

}
