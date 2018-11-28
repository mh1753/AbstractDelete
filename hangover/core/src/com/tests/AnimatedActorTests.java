package com.tests;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import org.junit.Assert;

public class AnimatedActorTests {
	
public JUnitTester j;
	
	public AnimatedActorTests(JUnitTester j) {
		this.j = j;
		System.out.println("Testing AnimatedActor:");
	}

	@Test
	public void testAnimatedActor() {
		Assert.assertNull(j.ai.currentAnim);
		Assert.assertEquals(0, j.ai.timing, 0);
		
		System.out.println("    testAnimatedActor() : passed");
	}
	
	@Test
	public void testSetFrameSize() {
		Assert.assertEquals(32, j.ai.getWidth(), 0);
		Assert.assertEquals(32, j.ai.getHeight(), 0);
		
		System.out.println("    testSetFrameSize() : passed");
	}
	
	@Test
	public void testGetFrameSize() {
		Assert.assertEquals(new Vector2(32, 32), j.ai.getFrameSize());
		
		System.out.println("    testGetFrameSize() : passed");
	}
	
	@Test
	public void testStoreAnim() {
		Assert.assertNotNull(j.ai.getAnims());
		
		System.out.println("    testStoreAnim() : passed");
	}
	
	@Test
	public void testGetAnims() {
		Assert.assertTrue(j.aj.getAnims().values().isEmpty());
		
		System.out.println("    testGetAnims() : passed");
	}

	@Test
	public void testSetAnim() {
		Assert.assertNotNull(j.aj.currentAnim);
		
		System.out.println("    testSetAnim() : passed");
	}
	
	@Test
	public void testCloneAnimatedActor() {
		Assert.assertFalse(j.aj.getAnims().values().isEmpty());
		
		System.out.println("    testCloneAnimatedActor() : passed");
	}
	
	@Test
	public void testAct() {
		Assert.assertNotEquals(j.ai.timing,j.aj.timing,0);
		
		System.out.println("    testAct() : passed");
	}

}
