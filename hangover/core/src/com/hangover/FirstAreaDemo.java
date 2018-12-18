package com.hangover;



import com.badlogic.gdx.math.Vector2;

public class FirstAreaDemo extends PlayScreen {

	public FirstAreaDemo(AKillerHangover g, ResourceManager r) {
		super(g, r);
	}

	@Override
	public void create() {
		super.create("player1", new Vector2(1520, 64), "assets//maps//Goodricke.tmx");
		
		maxEnemyNo = 0;
		NPC zombie1 = new NPC("zombie", r);
		zombie1.setPosition(400, 600);
		enemies.add(zombie1);
		
		NPC zombie2 = new NPC("zombie", r);
		zombie2.setPosition(900, 300);
		enemies.add(zombie2);
		
		NPC zombie3 = new NPC("zombie", r);
		zombie3.setPosition(300, 300);
		enemies.add(zombie3);
		
		
		for(ImageActor i: background) {
			backStage.addActor(i);
		}
		for(NPC n : enemies) {
			entityStage.addActor(n);
		}
		entityStage.addActor(c);
		
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
	}

}
