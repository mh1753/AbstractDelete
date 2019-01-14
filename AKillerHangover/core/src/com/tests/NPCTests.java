package com.tests;

import org.junit.Test;
import com.hangover.NPC;

import org.junit.Assert;

public class NPCTests {

	JUnitTester j;
	
	public NPCTests(JUnitTester j) {
		this.j = j;
		System.out.println("Testing NPC:");
	}
	
	@Test
	public void testNPC() {
		j.n = new NPC();
		Assert.assertEquals(true, j.n.getFriendly());
		
		System.out.println("    testNPC() : passed");
	}
	
	@Test
	public void testSetFriendly() {
		j.n.setFriendly(false);
		Assert.assertEquals(false, j.n.getFriendly());
		
		System.out.println("    testSetFriendly() : passed");
	}
	
	@Test
	public void testGetFriendly() {
		Assert.assertEquals(false, j.n.getFriendly());
		
		System.out.println("    testGetFriendly() : passed");
	}
	
	@Test
	public void testSimpleChasePlayer() {
		j.n.simpleChasePlayer(1, -1);
		j.n.setSpeed(10);
		j.n.setVelocityFromAngle();
		Assert.assertEquals(7, j.n.getVelocity().x, 0.1);
		Assert.assertEquals(-7, j.n.getVelocity().y, 0.1);
		
		j.n.simpleChasePlayer(-1, -1);
		j.n.setVelocityFromAngle();
		Assert.assertEquals(-7, j.n.getVelocity().x, 0.1);
		Assert.assertEquals(-7, j.n.getVelocity().y, 0.1);
		
		j.n.simpleChasePlayer(1, 1);
		j.n.setVelocityFromAngle();
		Assert.assertEquals(7, j.n.getVelocity().x, 0.1);
		Assert.assertEquals(7, j.n.getVelocity().y, 0.1);
		
		j.n.simpleChasePlayer(-1, 1);
		j.n.setVelocityFromAngle();
		Assert.assertEquals(-7, j.n.getVelocity().x, 0.1);
		Assert.assertEquals(7, j.n.getVelocity().y, 0.1);
		
		System.out.println("    testSimpleChasePlayer() : passed");
	}

}
