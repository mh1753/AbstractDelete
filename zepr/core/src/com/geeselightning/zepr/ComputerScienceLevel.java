package com.geeselightning.zepr;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Arrays;

public class ComputerScienceLevel extends Level {

    private static final String mapLocation = "maps/computer_science.tmx";
    private static final Vector2 playerSpawn = new Vector2();
    private static final Vector2 powerSPawn = new Vector2();

    // Defining possible zombie spawning locations on this map
    public static final ArrayList<Vector2> zombieSpawnPoints = new ArrayList<Vector2>(
            Arrays.asList(new Vector2())
    );

    // Defining the number of zombies to be spawned for each wave
    private static final int[] waves = new int[]{8};

    public ComputerScienceLevel(Zepr zepr) {
        super(zepr, mapLocation, playerSpawn, zombieSpawnPoints, waves, powerSPawn);
    }

    @Override
    public void complete(){
        // update progress
        if (parent.progress == parent.COMPUTERSCIENCE){
            parent.progress = parent.GLASSHOUSE;
        }
        // The stage is being replayed
    }

}
