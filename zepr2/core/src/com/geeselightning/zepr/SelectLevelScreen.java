package com.geeselightning.zepr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.Color;

public class SelectLevelScreen implements Screen {

    private Zepr parent;
    private Stage stage;
    private Label stageDescription;
    private Label characterDescription;
    private int stageLink = -1;
    private boolean playerSet = false;
    Player player = Player.getInstance();

    //Change starts: INITSELECTLEVELVARS
    // Importing the necessary assets for the button textures.
    Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

    private Label title = new Label("", skin, "subtitle");
    private String townDescription = "";
    private String halifaxDescription = "";
    private String courtyardDescription = "";
    private String libraryDescription = "";
    private String physicsDescription = "";
    private String centralHallDescription = "";
    private String defaultDescription = "";

    private TextButton town = new TextButton("", skin);
    private TextButton halifax = new TextButton("", skin);
    private TextButton courtyard = new TextButton("", skin);
    private TextButton library = new TextButton("", skin);
    private TextButton physics = new TextButton("", skin);
    private TextButton centralHall = new TextButton("", skin);
    //Change ends: INITSELECTLEVELVARS

    public SelectLevelScreen(Zepr zepr) {

        parent = zepr;

        // The stage is the controller which will react to inputs from the user.
        this.stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        // Send any input from the user to the stage.
        Gdx.input.setInputProcessor(stage);

        // Creating stage buttons.
        //Change starts: ZOMBIESTORY
        if (parent.isZombie()){
            town.setText("BRAIN");
            halifax.setText("Brain");
            courtyard.setText("Brainbrain");
            library.setText("Brain?");
            physics.setText("Braaaiiin");
            centralHall.setText("Brain Brain");
        } else {
            town.setText("Town");
            halifax.setText("Halifax");
            courtyard.setText("Courtyard");
            library.setText("Library");
            physics.setText("Physics");
            centralHall.setText("Central Hall");
        }
        //Change ends: ZOMBIESTORY

        //Change starts: SELECTLEVELUIFIX
        /*town.setTransform(true);
        halifax.setTransform(true);
        courtyard.setTransform(true);
        library.setTransform(true);
        physics.setTransform(true);
        centralHall.setTransform(true);
        town.setScale(0.7f);
        halifax.setScale(0.7f);
        courtyard.setScale(0.7f);
        library.setScale(0.7f);
        physics.setScale(0.7f);
        centralHall.setScale(0.7f);
         */
        //Change ends: SELECTLEVELUIFIX

        // Creating character buttons.
        TextButton nerdy = new TextButton("Nerdy",skin);
        TextButton sporty = new TextButton("Sporty",skin);
        // Added for third player type
        TextButton drama = new TextButton("Drama",skin);

        // Creating other buttons.
        TextButton play = new TextButton("Play", skin);
        TextButton save = new TextButton("Save", skin);
        TextButton load = new TextButton("Load", skin);
        TextButton back = new TextButton("Back", skin);
        TextButton bonusGame = new TextButton("Bonus Game", skin);

        // Creating stage descriptions.
        //Change starts: ZOMBIESTORY
        if (parent.isZombie()){
            title.setText("Brains brain, brains.");
            townDescription = "Brains... BRAINS!";
            halifaxDescription = "Brains brains brains.";
            courtyardDescription = "Brain, brain, brain.";
            libraryDescription = "Brains... ?";
            physicsDescription = "Braaaaaaiiiiiins.";
            centralHallDescription = "BRAINS! Brain brain brains, brain brain.";
            defaultDescription = "Brains brain";
        } else {
            title.setText("Choose a stage and character.");
            townDescription = "You wake up hungover in town to discover there is a zombie apocalypse.";
            halifaxDescription = "You need to get your laptop with the work on it from your accomodation.";
            courtyardDescription = "You should go to Courtyard and get some breakfast.";
            libraryDescription = "Take a break from the zombies to study.";
            physicsDescription = "You go to Physics to try and find something to help stop the zombies.";
            centralHallDescription = "Stop the source of the zombie horde by beating the boss in Central hall.";
            defaultDescription = "Select a stage from the buttons above.";
        }
        //Change ends: ZOMBIESTORY
        stageDescription = new Label(defaultDescription, skin);
        stageDescription.setWrap(true);
        stageDescription.setWidth(100);
        stageDescription.setAlignment(Align.center);

        // Creating character descriptions.
        // Changed descriptions to incorporate player abilities
        final String nerdyDescription = "Construct a mech suit for yourself so you can take more hits. Ability: Power Punch (increases attack briefly)";
        final String sportyDescripton = "Work out so you run faster. Ability: Sprint (run quickly for a short time)";
        // Added for third player type
        final String dramaDescripton = "Borrow a sword prop from the drama department. Ability: Fake Damage (restores 10HP)";
        final String defaultCharacterDescription = "Select a type of student from the buttons above.";
        characterDescription = new Label(defaultCharacterDescription,skin);
        characterDescription.setWrap(true);
        characterDescription.setWidth(100);
        characterDescription.setAlignment(Align.center);

        // Adding menu bar.
        Table menuBar = new Table();
        menuBar.setFillParent(true);
        // menuBar.setDebug(true); // Adds borders for the table.
        stage.addActor(menuBar);

        menuBar.top().left();
        menuBar.row();
        menuBar.add(back).pad(10);
        menuBar.add(save).pad(10);
        menuBar.add(load).pad(10);
        menuBar.add(bonusGame).pad(10);

        // Adding stage selector buttons.
        Table stageSelect = new Table();
        stageSelect.setFillParent(true);
        // stageSelect.setDebug(true); // Adds borders for the table.
        stage.addActor(stageSelect);

        stageSelect.center();

        //Change starts: SELECTLEVELUIFIX
        stageSelect.row();
        stageSelect.add(title).padTop(50).colspan(3);
        
        stageSelect.row().pad(50,0,5,0);
        stageSelect.add(town).pad(10);
        stageSelect.add(halifax).pad(10);
        stageSelect.add(courtyard).pad(10);
        // Added buttons for new levels
        stageSelect.row().padBottom(100);
        stageSelect.add(library).pad(10);
        stageSelect.add(physics).pad(10);
        stageSelect.add(centralHall).pad(10);
        //Change ends: SELECTLEVELUIFIX

        stageSelect.row();
        stageSelect.add(stageDescription).width(1000f).colspan(3);

        // Adding select character Buttons
        stageSelect.row().center();
        stageSelect.add(nerdy).pad(10);
        stageSelect.add(sporty).pad(10);
        // Added to support Third Player type
        stageSelect.add(drama).pad(10);

        //Change starts: SELECTLEVELUIFIX
        // Add character Descriptions
        Table charDescription = new Table();
        charDescription.setFillParent(true);

        stage.addActor(charDescription);

        charDescription.center().top().padTop(630);
        charDescription.row();
        charDescription.add(characterDescription).width(900f).colspan(3);
        //Change ends: SELECTLEVELUIFIX

        // Adding play button at the bottom.
        Table bottomTable = new Table();
        bottomTable.setFillParent(true);
        // bottomTable.setDebug(true); // Adds borders for the table.
        stage.addActor(bottomTable);

        //Change starts: SELECTLEVELUIFIX
        bottomTable.bottom().right();
        bottomTable.add(play).pad(10);
        //Change ends: SELECTLEVELUIFIX

        // Adding button logic.

        // Defining actions for the back button.
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(Zepr.MENU);
            }
        });
        
        // Added to implement saving requirement
        save.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            	try {
            		Writer writer = new FileWriter("save.txt", false);
            		//Change starts: PROGRESSFUNCS
            		writer.write(Integer.toString(parent.getProgress()));
            		//Change ends: PROGRESSFUNCS
            		writer.close();
            	} catch (IOException e) {
					e.printStackTrace();
            	}
            }
        });

        // Added to implement saving requirement
        load.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            	try {
                	File file = new File("save.txt");
                	Scanner scanner = new Scanner(file);
            		int progress = scanner.nextInt();
            		scanner.close();
            		//Change starts: PROGRESSFUNCS
            		parent.setProgress(progress);
            		//Change ends: PROGRESSFUNCS
            		// Reloads page to update buttons
                    parent.changeScreen(Zepr.SELECT);
            	} catch (FileNotFoundException e) {
					e.printStackTrace();
				} 
            }
        });
        
        // Added to change the screen to the minigame
        bonusGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(Zepr.BONUS);
            }
        });

        // Defining actions for the town button.
        town.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stageDescription.setText(townDescription);
                stageLink = Zepr.TOWN;
            }
        });

        //Change starts: PROGRESSFUNCS
        if (parent.getProgress() <= parent.TOWN) {
            //Change ends: PROGRESSFUNCS
            halifax.setColor(Color.DARK_GRAY);
            halifax.getLabel().setColor(Color.DARK_GRAY);
        } else {
            // Defining actions for the halifax button.
            halifax.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    stageDescription.setText(halifaxDescription);
                    stageLink = Zepr.HALIFAX;
                }
            });
        }

        //Change starts: PROGRESSFUNCS
        if (parent.getProgress() <= parent.HALIFAX) {
            //Change ends: PROGRESSFUNCS
            courtyard.setColor(Color.DARK_GRAY);
            courtyard.getLabel().setColor(Color.DARK_GRAY);
        } else {
            // Defining actions for the courtyard button.
            courtyard.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    stageDescription.setText(courtyardDescription);
                    stageLink = Zepr.COURTYARD;
                }
            });
        }
        
        // Added for LibraryLevel
        //Change starts: PROGRESSFUNCS
        if (parent.getProgress() <= parent.COURTYARD) {
            //Change ends: PROGRESSFUNCS
            library.setColor(Color.DARK_GRAY);
            library.getLabel().setColor(Color.DARK_GRAY);
        } else {
            // Defining actions for the courtyard button.
            library.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    stageDescription.setText(libraryDescription);
                    stageLink = Zepr.LIBRARY;
                }
            });
        }
        
        // Added for physicsLevel
        //Change starts: PROGRESSFUNCS
        if (parent.getProgress() <= parent.LIBRARY) {
            //Change ends: PROGRESSFUNCS
            physics.setColor(Color.DARK_GRAY);
            physics.getLabel().setColor(Color.DARK_GRAY);
        } else {
            // Defining actions for the courtyard button.
        	physics.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    stageDescription.setText(physicsDescription);
                    stageLink = Zepr.PHYSICS;
                }
            });
        }
        
        // Added for CentralHallLevel
        //Change starts: PROGRESSFUNCS
        if (parent.getProgress() <= parent.PHYSICS) {
            //Change ends: PROGRESSFUNCS
            centralHall.setColor(Color.DARK_GRAY);
            centralHall.getLabel().setColor(Color.DARK_GRAY);
        } else {
            // Defining actions for the courtyard button.
        	centralHall.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    stageDescription.setText(centralHallDescription);
                    stageLink = Zepr.CENTRALHALL;
                }
            });
        }

        //Change starts: ZOMBIESTORY
        if (parent.isZombie()){
            nerdy.setColor(Color.DARK_GRAY);
            nerdy.getLabel().setColor(Color.DARK_GRAY);
            sporty.setColor(Color.DARK_GRAY);
            sporty.getLabel().setColor(Color.DARK_GRAY);
            drama.setColor(Color.DARK_GRAY);
            drama.getLabel().setColor(Color.DARK_GRAY);
            characterDescription.setText("Brains");
            player.setType("zombie");
            playerSet = true;
        } else {
            //Defining actions for the nerdy button.
            nerdy.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    characterDescription.setText(nerdyDescription);
                    player.setType("nerdy");
                    parent.setLastKnownCharacter("nerdy");
                    playerSet = true;
                }
            });
            //Defining actions for the sporty button
            sporty.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    characterDescription.setText(sportyDescripton);
                    player.setType("sporty");
                    parent.setLastKnownCharacter("sporty");
                    playerSet = true;
                }
            });
            //Defining actions for the drama button
            drama.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    characterDescription.setText(dramaDescripton);
                    player.setType("drama");
                    parent.setLastKnownCharacter("drama");
                    playerSet = true;
                }
            });
        }
        //Change ends: ZOMBIESTORY

        // Defining actions for the play button.
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if ((stageLink != -1) && (playerSet == true)) {
                    parent.changeScreen(stageLink);
                }
            }
        });

    }

    @Override
    public void render(float delta) {
        // Clears the screen to black.
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draws the stage.
        this.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        this.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Update the screen when the window resolution is changed.
        this.stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        // Dispose of assets when they are no longer needed.
        stage.dispose();
    }

}
