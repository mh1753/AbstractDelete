package com.geeselightning.zepr.minigame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MinigameVars {

    final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

    // the TextButtons used as cells for the game
    TextButton b0 = new TextButton("1", skin);
    TextButton b1 = new TextButton("2", skin);
    TextButton b2 = new TextButton("3", skin);
    TextButton b3 = new TextButton("4", skin);
    TextButton b4 = new TextButton("5", skin);
    TextButton b5 = new TextButton("6", skin);
    TextButton b6 = new TextButton("7", skin);
    TextButton b7 = new TextButton("8", skin);
    TextButton b8 = new TextButton("9", skin);


    // the conditions to end the game, represented as an array of ArrayList
    ArrayList[] endCondition = new ArrayList[8];
    void initialiseEndConditions(){
        endCondition[0] = new ArrayList<TextButton>(Arrays.asList(b0, b1, b2));
        endCondition[1] = new ArrayList<TextButton>(Arrays.asList(b3, b4, b5));
        endCondition[2] = new ArrayList<TextButton>(Arrays.asList(b6, b7, b8));
        endCondition[3] = new ArrayList<TextButton>(Arrays.asList(b0, b3, b6));
        endCondition[4] = new ArrayList<TextButton>(Arrays.asList(b1, b4, b7));
        endCondition[5] = new ArrayList<TextButton>(Arrays.asList(b2, b5, b8));
        endCondition[6] = new ArrayList<TextButton>(Arrays.asList(b0, b4, b8));
        endCondition[7] = new ArrayList<TextButton>(Arrays.asList(b2, b4, b6));
    }

}
