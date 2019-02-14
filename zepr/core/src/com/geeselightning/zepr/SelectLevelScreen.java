package com.geeselightning.zepr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
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
import com.geeselightning.zepr.minigame.TicTacToeScreen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public class SelectLevelScreen implements Screen {

    private Zepr parent;
    private Stage stage;
    //Change starts ; Reference SELECTLEVELPOINTLABEL
    private Label pointCounter;
    //Change ends ; Reference SELECTLEVELPOINTLABEL
    private Label stageDescription;
    private Label characterDescription;
    private int stageLink = -1;
    private boolean playerSet = false;
    Player player = Player.getInstance();

    public SelectLevelScreen(Zepr zepr) {

        parent = zepr;

        // The stage is the controller which will react to inputs from the user.
        this.stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        // Send any input from the user to the stage.
        Gdx.input.setInputProcessor(stage);

        // Importing the necessary assets for the button textures.
        Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        //Change starts ; Reference INITSELECTLEVELPOINTCOUNTER
        //Creating point counter label.
        pointCounter = new Label("Points : " + Integer.toString(parent.getPoints()), skin, "subtitle");
        //Change ends ; Reference INITSELECTLEVELPOINTCOUNTER

        // Creating stage buttons.
        final TextButton town = new TextButton("Town", skin);
        TextButton halifax = new TextButton("Halifax", skin);
        TextButton courtyard = new TextButton("Courtyard", skin);
        //Change starts ; Reference INITNEWLEVELBUTTONS
        TextButton busStop = new TextButton("Bus Stop", skin);
        TextButton computerScience = new TextButton("Computer Science", skin);
        TextButton glasshouse = new TextButton("Glasshouse", skin);
        //Change ends ; Reference INITNEWLEVELBUTTONS

        // Creating character buttons.
        TextButton nerdy = new TextButton("Nerdy",skin);
        TextButton sporty = new TextButton("Sporty",skin);
        //Change starts ; Reference INITNEWCHARBUTTON
        TextButton stJohn = new TextButton("St John", skin);
        //Change ends ; Reference INITNEWCHARBUTTON

        // Creating other buttons.
        TextButton play = new TextButton("Play", skin);
        TextButton save = new TextButton("Save", skin);
        TextButton load = new TextButton("Load", skin);
        TextButton back = new TextButton("Back", skin);
        final TextButton minigame = new TextButton("minigame", skin);

        // Creating stage descriptions.
        Label title = new Label("Choose a stage and character.", skin, "subtitle");
        final String townDescription = "You wake up hungover in town to discover there is a zombie apocalypse.";
        final String halifaxDescription = "You need to get your laptop with the work on it from your accomodation.";
        final String courtyardDescription = "You should go to Courtyard and get some breakfast.";
        //Change starts ; Reference NEWLEVELDESCRIPTIONS
        final String bustStopDescription = "Time to go hand in your work. Better head to campus east.";
        final String computerScienceDescription = "No one's here! Oh well, at least you got the work done.";
        final String glasshouseDecription = "Go to the pub, have a pint, and wait for all this to blow over.";
        //Change ends ; Reference NEWLEVELDESCRIPTIONS
        final String lockedDescription = "This stage is locked until you complete the previous one.";
        final String defaultDescription ="Select a stage from the buttons above.";
        stageDescription = new Label(defaultDescription, skin);
        stageDescription.setWrap(true);
        stageDescription.setWidth(100);
        stageDescription.setAlignment(Align.center);

        // Creating character descriptions.
        final String nerdyDescription = "All that studying let's you take more damage, for some reason.";
        final String sportyDescripton = "All that working out helps you run faster.";
        //Change starts ; Reference NEWCHARDESCRIPTION
        final String stJohnDescription = "You might be good at something. Probably not.";
        //Change ends ; Reference NEWCHARDESCRIPTION
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
        //Change starts ; Reference POINTCOUNTERMENUBAR
        menuBar.add(pointCounter).pad(10);
        menuBar.add(minigame).pad(10);
        //Change ends ; Reference POINTCOUNTERMENUBAR

        // Adding stage selector buttons.
        Table stageSelect = new Table();
        stageSelect.setFillParent(true);
        // stageSelect.setDebug(true); // Adds borders for the table.
        stage.addActor(stageSelect);

        stageSelect.center();

        stageSelect.row();
        stageSelect.add(title).padTop(50).colspan(3);

        stageSelect.row().pad(50,0,5,0);
        stageSelect.add(town).pad(10);
        stageSelect.add(halifax).pad(10);
        stageSelect.add(courtyard).pad(10);

        //Change starts ; Reference DISPLAYNEWLEVELBUTTONS
        stageSelect.row().pad(0, 0, 100, 0);
        stageSelect.add(busStop).pad(10);
        stageSelect.add(computerScience).pad(10);
        stageSelect.add(glasshouse).pad(10);
        //Change ends ; Reference DISPLAYNEWLEVELBUTTONS

        stageSelect.row();
        stageSelect.add(stageDescription).width(1000f).colspan(3);

        // Adding select character Buttons
        stageSelect.row().center();
        stageSelect.add(nerdy).pad(10);
        stageSelect.add(sporty).pad(10);
        //Change starts ; Reference DISPLAYNEWCHARBUTTON
        stageSelect.add(stJohn).pad(10);
        //Change ends ; Reference DISPLAYNEWCHARBUTTON

        stageSelect.row().center();
        stageSelect.add(characterDescription).width(1000f).colspan(3);

        // Adding play button at the bottom.
        Table bottomTable = new Table();
        bottomTable.setFillParent(true);
        // bottomTable.setDebug(true); // Adds borders for the table.
        stage.addActor(bottomTable);

        bottomTable.right().bottom();
        bottomTable.add(play).pad(10);

        // Adding button logic.

        // Defining actions for the back button.
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(Zepr.MENU);
            }
        });

        //Change starts ; Reference IMPLEMENTSAVE
        // Defining actions for the save button.
        save.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    FileHandle file = (Gdx.files.external("PlayerRecord.txt"));
                    BufferedWriter writer = new BufferedWriter(file.writer(false));
                    writer.write(Integer.toString(parent.getPoints()));
                    writer.newLine();
                    writer.write(Integer.toString(parent.progress));
                    writer.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
        //Change ends ; Reference IMPLEMENTSAVE

        //Change starts ; Reference IMPLEMENTLOAD
        // Defining actions for the load button.
        load.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    FileHandle file = (Gdx.files.external("PlayerRecord.txt"));
                    BufferedReader reader = new BufferedReader(file.reader());
                    parent.setPoints(Integer.valueOf(reader.readLine()));
                    parent.progress = Integer.parseInt(reader.readLine());
                    reader.close();
                    parent.changeScreen(Zepr.SELECT);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
        //Change ends ; Reference IMPLEMENTLOAD

        minigame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setScreen(new TicTacToeScreen(parent));
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

        if (parent.progress <= parent.TOWN) {
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

        if (parent.progress <= parent.HALIFAX) {
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
        //Change starts ; Reference LEVELBUTTONCLICKLISTENERS
        if (parent.progress <= parent.COURTYARD) {
            busStop.setColor(Color.DARK_GRAY);
            busStop.getLabel().setColor(Color.DARK_GRAY);
        } else {
            // Defining actions for the bus stop button.
            busStop.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    stageDescription.setText(bustStopDescription);
                    stageLink = Zepr.BUSSTOP;
                }
            });
        }

        if (parent.progress <= parent.BUSSTOP) {
            computerScience.setColor(Color.DARK_GRAY);
            computerScience.getLabel().setColor(Color.DARK_GRAY);
        } else {
            computerScience.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    stageDescription.setText(computerScienceDescription);
                    stageLink = Zepr.COMPUTERSCIENCE;
                }
            });
        }

        if (parent.progress <= parent.COMPUTERSCIENCE) {
            glasshouse.setColor(Color.DARK_GRAY);
            glasshouse.getLabel().setColor(Color.DARK_GRAY);
        } else {
            glasshouse.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    stageDescription.setText(glasshouseDecription);
                    stageLink = Zepr.GLASSHOUSE;
                }
            });
        }
        //Change ends ; Reference LEVELBUTTONCLICKLISTENERS

        //Defining actions for the nerdy button.

        nerdy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                characterDescription.setText(nerdyDescription);
                player.setType("nerdy");
                playerSet = true;
            }
        });
        sporty.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                characterDescription.setText(sportyDescripton);
                player.setType("sporty");
                playerSet = true;
            }
        });
        //Change starts ; Reference CHARBUTTONCLICKLISTENER
        stJohn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                characterDescription.setText(stJohnDescription);
                player.setType("stJohn");
                playerSet = true;
            }
        });
        //Change ends ; Reference CHARBUTTONCLICKLISTENER


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

        //Change starts ; Reference UPDATESELECTLEVELPOINTS
        //Updates the pointCounter if the number of points has changed
        if(Integer.toString(parent.getPoints()).equals(pointCounter.getText()
                .substring("Points : ".length() - 1))){
            pointCounter.setText("Points : " + Integer.toString(parent.getPoints()));
        }
        //Change ends ; Reference UPDATESELECTLEVELPOINTS

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
