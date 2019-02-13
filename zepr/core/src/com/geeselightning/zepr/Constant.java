package com.geeselightning.zepr;

import com.badlogic.gdx.math.Vector2;

public final class Constant {
    public static final Vector2 ORIGIN = new Vector2(0, 0);
    public static final float PLAYERSPEED = 120;
    public static final int PLAYERMAXHP = 100;
    public static final int PLAYERDMG = 20;
    public static final int PLAYERRANGE = 50;
    public static final float PLAYERHITCOOLDOWN = 0.2f;
    public static final float ZOMBIEBASESPEED = 80;
    //Change starts ; Reference ZOMBHEALTH
    public static final int ZOMBIEBASEMAXHP = 100;
    //Change starts ; Reference ZOMBHEALTH
    //Change starts ; Reference ZOMBSTATMOD
    public static final float ZOMBIESTATMODIFIER = 0.5f;
    //Change ends ; Reference ZOMBSTATMOD
    public static final int ZOMBIEDMG = 10;
    public static final int ZOMBIERANGE = 20;
    public static final float ZOMBIEHITCOOLDOWN = 1;
    //Change starts ; Reference FIRSTBOSSSTATS
    public static final float FIRSTBOSSSTATMODIFIER = 2f;
    public static final float FIRSTBOSSRANGEMODIFIER = 2f;
    //Change ends ; Reference FIRSTBOSSSTATS
    //Change starts ; Reference FINALBOSSSTATS
    public static final float FINALBOSSHEALTHMODIFIER = 4f;
    public static final float FINALBOSSSPEEDMODIFIER = 0.2f;
    public static final float FINALBOSSRANGEMODIFIER = 2f;
    public static final float FINALBOSSDAMAGEMODIFIER = 1.5f;
    //Change ends ; Reference FINALBOSSSTATS
    public static final float NERDYHPMULT = 1.5f;
    public static final float NERDYDMGMULT = 1;
    public static final float NERDYSPEEDMULT = 1;
    public static final float SPORTYHPMULT = 1;
    public static final float SPORTYDMGMULT = 1;
    public static final float SPORTYSPEEDMULT = 1.5f;
    //Change starts ; Reference STJOHNSTATS
    public static final float STJOHNHPMULT = 0.75f;
    public static final float STJOHNDMGMULT = 0.75f;
    public static final float STJOHNSPEEDMULT = 0.75f;
    //Change ends ; Reference STJOHNSTATS
    public static final int HEALUP = 30;
    //Change starts ; Reference DAMPOWERMAGNITUDE
    public static final int DAMAGEUP = 15;
    //Change ends ; Reference DAMPOWERMAGNITUDE
    public static final int SPEEDUP = 50;
    //Change starts ; Reference SLOWMAGNITUDE
    public static final int SLOW = 50;
    //Change ends ; Reference SLOWMAGNITUDE
    //Change starts ; Reference DAMPOWERTIME
    public static final float DAMAGEUPTIME = 5;
    //Change ends ; Reference DAMPOWERTIME
    public static final float SPEEDUPTIME = 10;
    //Change starts ; Reference SLOWTIME
    public static final float SLOWTIME = 15;
    //Change ends ; Reference SLOWTIME
    public static final float IMMUNITYTIME = 5;
    //Change starts ; Reference POINTGAINVALUES
    public static final int ZOMBIEKILLPOINTS = 100;
    public static final int SAFEAREAPOINTS = 1000;
    public static final int FINISHMINIGAMEPOINTS = 1000;
    public static final int AVOIDPOINTS = 5;
    //Change ends ; Reference POINTGAINVALUES
    //Change starts ; Reference AVOIDTIMER
    public static final float AVOIDTIMER = 3;
    //Change ends ; Reference AVOIDTIMER
}
