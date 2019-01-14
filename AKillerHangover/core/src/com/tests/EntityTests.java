package com.tests;

import org.junit.Assert;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.hangover.ResourceManager;

public class EntityTests {

	JUnitTester j;
	ResourceManager r;
	
	public EntityTests(JUnitTester j, ResourceManager r) {
		this.j = j;
		this.r = r;
		System.out.println("Testing Entity:");
	}
	

	@Test
	public void testEntity() {
		Assert.assertEquals(0, j.e.getSpeed(), 0);
		Assert.assertEquals(0, j.e.getMaxHealth(), 0);
		Assert.assertEquals(0, j.e.getHealth(), 0);
		Assert.assertEquals(0, j.e.getAngle(), 0);
		Assert.assertEquals("Default", j.e.getType());
		
		System.out.println("    testEntity() : passed");
	}

	@Test
	public void testEntityStringResourceManager() {
		Assert.assertEquals(r.getEntity("badlogic").getSpeed(), j.e.getSpeed(), 0);
		Assert.assertEquals(r.getEntity("badlogic").getMaxHealth(), j.e.getMaxHealth(), 0);
		Assert.assertEquals(r.getEntity("badlogic").getHealth(), j.e.getHealth(), 0);
		Assert.assertEquals(r.getEntity("badlogic").getType(), j.e.getType());
		
		System.out.println("    testEntityStringResourceManager() : passed");
	}

	@Test
	public void testSetSpeed() {
		j.e.setSpeed(100);
		Assert.assertEquals(100, j.e.getSpeed(),0);
		
		System.out.println("    testSetSpeed() : passed");
	}

	@Test
	public void testGetSpeed() {
		j.e.setSpeed(10);
		Assert.assertEquals(10, j.e.getSpeed(),0);
		
		System.out.println("    testGetSpeed() : passed");
	}

	@Test
	public void testSetAngle() {
		j.e.setAngle(100);
		Assert.assertEquals(100, j.e.getAngle(),0);
		
		System.out.println("    testSetAngle() : passed");
	}

	@Test
	public void testGetAngle() {
		j.e.setAngle(10);
		Assert.assertEquals(10, j.e.getAngle(),0);
		
		System.out.println("    testGetAngle() : passed");
	}

	@Test
	public void testSetMaxHealth() {
		j.e.setMaxHealth(10000);
		Assert.assertEquals(10000, j.e.getMaxHealth(), 0);
		
		System.out.println("    testSetMaxHealth() : passed");
	}

	@Test
	public void testGetMaxHealth() {
		j.e.setMaxHealth(100);
		Assert.assertEquals(100, j.e.getMaxHealth(), 0);
		
		System.out.println("    testGetMaxHealth() : passed");
	}

	@Test
	public void testAddHealth() {
		j.e.addHealth(1);
		Assert.assertEquals(100, j.e.getHealth(), 0);
		j.e.addHealth(-1);
		Assert.assertEquals(99, j.e.getHealth(), 0);
		j.e.addHealth(1);
		Assert.assertEquals(100, j.e.getHealth(), 0);
		System.out.println("    testAddHealth() : passed");
	}

	@Test
	public void testTakeHealth() {
		j.e.takeHealth(101);
		Assert.assertEquals(0, j.e.getHealth(), 0);
		j.e.takeHealth(-1);
		Assert.assertEquals(1, j.e.getHealth(), 0);
		j.e.takeHealth(1);
		Assert.assertEquals(0, j.e.getHealth(), 0);
		System.out.println("    testTakeHealth() : passed");
	}

	@Test
	public void testGetHealth() {
		Assert.assertEquals(0, j.e.getHealth(), 0);
		System.out.println("    testGetHealth() : passed");
	}

	@Test
	public void testSetType() {
		j.e.setType("Default");
		Assert.assertEquals("Default", j.e.getType());
		System.out.println("    testSetType() : passed");
	}

	@Test
	public void testGetType() {
		Assert.assertEquals("Default", j.e.getType());
		System.out.println("    testGetType() : passed");
	}

	@Test
	public void testCloneEntity() {
		j.e.clone(r.getEntity("badlogic"));
		Assert.assertEquals(r.getEntity("badlogic").getSpeed(), j.e.getSpeed(), 0);
		Assert.assertEquals(r.getEntity("badlogic").getMaxHealth(), j.e.getMaxHealth(), 0);
		Assert.assertEquals(r.getEntity("badlogic").getHealth(), j.e.getHealth(), 0);
		Assert.assertEquals(r.getEntity("badlogic").getType(), j.e.getType());
		System.out.println("    testCloneEntity() : passed");
	}

	@Test
	public void testSetVelocityFromAngle() {
		j.e.setVelocityFromAngle();
		Assert.assertEquals(new Vector2((float)(Math.sin(j.e.getAngle()) * j.e.getSpeed()),(float)Math.cos(j.e.getAngle()) * j.e.getSpeed()), j.e.getVelocity());
		
		System.out.println("    testSetVelocityFromAngle() : passed");
	}
	
	@Test
	public void testAct() {
		j.e.setAngle(-j.e.getAngle());
		j.e.act(1);
		Assert.assertNotEquals(new Vector2((float)(Math.cos(j.e.getAngle()) * j.e.getSpeed()),(float)Math.sin(j.e.getAngle()) * j.e.getSpeed()), 
				new Vector2(j.e.getX(), j.e.getY()));
		
		System.out.println("    testAct() : passed");
	}

}
