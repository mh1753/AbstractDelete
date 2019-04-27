/**
 * Added by Shaun of the Devs to meet the requirement of different 6 locations
 */
package com.geeselightning.zepr;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Arrays;

public class LibraryLevel extends Level {

    private static final String mapLocation = "maps/library - new.tmx";
    private static final Vector2 playerSpawn = new Vector2(1300, 1300);
    private static final Vector2 powerSpawn = new Vector2(1400, 1400);

    // Defining possible zombie spawn locations on this map
    public static final ArrayList<Vector2> zombieSpawnPoints = new ArrayList<Vector2>(
            Arrays.asList(new Vector2(1200,1200), new Vector2(2500,2000),
                    new Vector2(2000,1200), new Vector2(700,2350))
    );

    // Defining the number of zombies to be spawned for each wave
    //Change starts: SAFEAREAWAVES
    // There are no zombies to spawn, as this is the safe area
    private static final int[] waves = new int[]{0, 0, 0};
    //Change ends: SAFEAREAWAVES

    public LibraryLevel(Zepr zepr) {
        super(zepr, mapLocation, playerSpawn, zombieSpawnPoints, waves, powerSpawn);
    }

    @Override
    public void complete() {
        // Update progress
        //Change starts: PROGRESSFUNCS
        if (parent.getProgress() == Zepr.LIBRARY) {
            parent.incProgress();
        }
        //Change ends: PROGRESSFUNCS
        // The stage is being replayed
    }
}