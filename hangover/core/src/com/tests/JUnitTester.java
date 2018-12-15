package com.tests;

import com.badlogic.gdx.Game;
import com.hangover.AKillerHangover;
import com.hangover.AnimatedActor;
import com.hangover.BaseScreen;
import com.hangover.Entity;
import com.hangover.ImageActor;
import com.hangover.MovingActor;
import com.hangover.NPC;
import com.hangover.ResourceManager;

public class JUnitTester extends BaseScreen {

	
	//ImageActor test variables
	public ImageActor i;
	public ImageActor j;
	public ImageActorTests tester;
	
	//AnimatedActor test variables
	public AnimatedActor ai;
	public AnimatedActor aj;
	public AnimatedActorTests atester;
	
	//MovingActor test variables
	public MovingActor m;
	public MovingActorTests mtester;
	
	//Entity test variables
	public Entity e;
	public EntityTests etester;
	
	//NPC test variables
	public NPC n;
	public NPCTests ntester;
	
	
	public JUnitTester(AKillerHangover g, ResourceManager r) {
		super(g, r);
	}

	@Override
	public void create() {
		
		/*
		 * ImageActor test code
		 */
		tester = new ImageActorTests(this);
		i = new ImageActor();
		tester.testImageActor();
		
		j = new ImageActor("assets//badlogic.jpg");
		tester.testImageActorString();
		
		i.setImage("assets//badlogic.jpg");
		tester.testSetImage();
		
		i = new ImageActor();
		tester.testGetImage();
		
		j.setPosition(30, 30);
		i.clone(j);
		tester.testCloneImageActor();
		
		j.setOrigin();
		tester.testSetOrigin();
		
		j.setRectangleBoundary();
		tester.testSetRectangleBoundary();
		
		j.setEllipseBoundary();
		tester.testSetEllipseBoundary();
		
		float [] vertices = {0,0,0,256,256,256,256,0};
		j.setBoundingPolygon(vertices);
		tester.testSetBoundingPolygon();
		
		tester.testGetBoundingPolygon();
		
		i.setRectangleBoundary();
		j.setRectangleBoundary();
		tester.testOverlaps();
		
		System.out.println("ImageActor Test over\n");
		
		/*
		 * AnimatedActor test code
		 */
		atester = new AnimatedActorTests(this);
		ai = new AnimatedActor();
		aj = new AnimatedActor();
		atester.testAnimatedActor();
		
		ai.setFrameSize(32, 32);
		atester.testSetFrameSize();
		
		atester.testGetFrameSize();
		
		ai.storeAnim("assets//badlogic.jpg", "badlogic", 10, 10);
		atester.testStoreAnim();
		
		atester.testGetAnims();
		
		aj.storeAnim("assets//badlogic.jpg", "badlogic", 10, 10);
		aj.setAnim("badlogic");
		atester.testSetAnim();
		
		aj = new AnimatedActor();
		aj.clone(ai);
		atester.testCloneAnimatedActor();
		
		ai.act(10);
		atester.testAct();
		
		
		System.out.println("AnimatedActor Test over\n");
		
		/*
		 * MovingActor test code
		 */
		mtester = new MovingActorTests(this);
		m = new MovingActor();
		m.setAcceleration(1, 1);
		m.setVelocity(1, 1);
		m.setAngularVel(1);
		m.setPosition(0,0);
		mtester.test();
		
		System.out.println("MovingActor Test over\n");
		
		/*
		 * Entity test code
		 */
		etester = new EntityTests(this, r);
		e = new Entity();
		etester.testEntity();
		
		e = new Entity("badlogic", r);
		etester.testEntityStringResourceManager();
		
		etester.testSetSpeed();
		
		etester.testGetSpeed();
		
		etester.testSetAngle();
		
		etester.testGetAngle();
		
		etester.testSetMaxHealth();
		
		etester.testGetMaxHealth();
		
		etester.testAddHealth();
		
		etester.testTakeHealth();
		
		etester.testGetHealth();
		
		etester.testSetType();
		
		etester.testGetType();
		
		etester.testCloneEntity();
		
		etester.testSetVelocityFromAngle();
		
		etester.testAct();
		
		System.out.println("Entity Test over\n");
		
		/*
		 * NPC test code
		 */
		ntester = new NPCTests(this);
		
		ntester.testNPC();
		
		ntester.testSetFriendly();
		
		ntester.testSimpleChasePlayer();
		
		System.out.println("NPC Test over\n");
		
		
		
		
	}
	
	@Override
	public void update(float dt) {
		
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
