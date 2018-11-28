package com.tests;




import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Assert;

public class ImageActorTests {
	
	public JUnitTester j;
	
	public ImageActorTests(JUnitTester j) {
		this.j = j;
		System.out.println("Testing ImageActor:");
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testImageActor() {
		Assert.assertEquals(0, j.i.getX(), 0);
		Assert.assertEquals(0, j.i.getY(), 0);
		Assert.assertEquals(0, j.i.getWidth(), 0);
		Assert.assertEquals(0, j.i.getHeight(), 0);
		Assert.assertNull(j.i.getImage());
		Assert.assertNull(j.i.getBoundingPolygon());
		
		System.out.println("    testImageActor() : passed");
		
	}

	@Test
	public void testImageActorString() {
		Assert.assertEquals(256, j.j.getWidth(),0);
		Assert.assertEquals(256, j.j.getHeight(),0);
		Assert.assertNotNull(j.j.getImage());
		
		System.out.println("    testImageActor(String) : passed");
	}

	@Test
	public void testSetImage() {
		Assert.assertEquals(256, j.i.getWidth(),0);
		Assert.assertEquals(256, j.i.getHeight(),0);
		Assert.assertNotNull(j.i.getImage());
		
		System.out.println("    testSetImage(String) : passed");
	}

	@Test
	public void testGetImage() {
		Assert.assertNull(j.i.getImage());
		Assert.assertNotNull(j.j.getImage());
		
		System.out.println("    testGetImage() : passed");
	}

	@Test
	public void testCloneImageActor() {
		Assert.assertEquals(j.i.getImage(), j.j.getImage());
		Assert.assertEquals(j.i.getWidth(), j.j.getWidth(), 0);
		Assert.assertEquals(j.i.getHeight(), j.j.getHeight(), 0);
		
		System.out.println("    testClone(ImageActor) : passed");
	}

	@Test
	public void testSetOrigin() {
		Assert.assertEquals(128, j.j.getOriginX(), 0);
		Assert.assertEquals(128, j.j.getOriginY(), 0);
		
		System.out.println("    testSetOrigin() : passed");
	}

	@Test
	public void testSetRectangleBoundary() {
		float[] vertices = {0,0,256, 0,256,256,0,256};
		Assert.assertArrayEquals(vertices, j.j.getBoundingPolygon().getVertices(), 0);
		
		System.out.println("    testSetRectangleBoundary() : passed");
	}

	@Test
	public void testSetEllipseBoundary() {
		Assert.assertEquals(8, j.j.getBoundingPolygon().getVertices().length,0);
		
		System.out.println("    testSetEllipseBoundary() : passed");
	}

	@Test
	public void testSetBoundingPolygon() {
		float[] vertices = {0,0,0,256,256,256,256,0};
		Assert.assertArrayEquals(vertices, j.j.getBoundingPolygon().getVertices(), 0);
		
		System.out.println("    testSetBoundingPolygon() : passed");
	}

	@Test
	public void testGetBoundingPolygon() {
		Assert.assertNull(j.i.getBoundingPolygon());
		Assert.assertNotNull(j.j.getBoundingPolygon());
		
		System.out.println("    testGetBoundingPolygon() : passed");
	}

	@Test
	public void testOverlaps() {
		Assert.assertTrue(j.i.overlaps(j.j,true));
		Assert.assertFalse(j.i.overlaps(j.j, false));
		
		System.out.println("    testOverlaps() : passed");
	}

}
