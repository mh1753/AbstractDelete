package com.hangover;

public class NPC extends Entity {

	boolean friendly;
	
	public NPC() {
		super();
		friendly = true;
	}
	
	public void setFriendly(boolean f) {
		friendly = f;
	}
	
	public boolean getFriendly() {
		return friendly;
	}
	
}
