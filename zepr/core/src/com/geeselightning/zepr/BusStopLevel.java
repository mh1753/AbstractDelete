package com.geeselightning.zepr;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Arrays;

public class BusStopLevel extends Level {

    private static final String mapLocation = "maps/Bus_stop.tmx";
    private static final Vector2 playerSpawn = new Vector2(400, 400);
    private static final Vector2 powerSPawn = new Vector2(720, 610);

    // Defining possible zombie spawning locations on this map
    public static final ArrayList<Vector2> zombieSpawnPoints = new ArrayList<Vector2>(
            Arrays.asList(new Vector2(48, 288), new Vector2(752, 512),
                    new Vector2(48, 512), new Vector2(752, 288))
    );

    // Defining the number of zombies to be spawned for each wave
    private static final int[] waves = new int[]{8, 13, 18};

    public BusStopLevel(Zepr zepr) {
        super(zepr, mapLocation, playerSpawn, zombieSpawnPoints, waves, powerSPawn);
    }

    @Override
    public void complete(){
        // update progress
        if (parent.progress == parent.BUSSTOP){
            parent.progress = parent.COMPUTERSCIENCE;
        }
        // The stage is being replayed
    }

}
