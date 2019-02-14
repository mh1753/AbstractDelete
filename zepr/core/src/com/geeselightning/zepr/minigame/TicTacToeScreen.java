//Change start ; Reference TICTACTOE
package com.geeselightning.zepr.minigame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.geeselightning.zepr.Constant;
import com.geeselightning.zepr.TextScreen;
import com.geeselightning.zepr.Zepr;

import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToeScreen implements Screen {

    // load in a new instance of the game's variables
    private MinigameVars vars = new MinigameVars();

    private Stage stage;
    private Zepr parent;

    // load textButtons into an array of cells, used to check if the cell is empty or not
    private ArrayList<TextButton> cells = new ArrayList<TextButton>(Arrays.asList(
            vars.b0, vars.b1, vars.b2,
            vars.b3, vars.b4, vars.b5,
            vars.b6, vars.b7, vars.b8)
    );

    // arrays used to test the win conditions
    private ArrayList<TextButton> noughts = new ArrayList<TextButton>();
    private ArrayList<TextButton> crossed = new ArrayList<TextButton>();

    private Boolean win = false;
    private int turn = 1;
    private Label turnIndicator;

    public TicTacToeScreen(Zepr game){
        parent = game;
        this.stage = new Stage(new ScreenViewport());

        turnIndicator = new Label("Turn: " + turn, vars.skin);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        turnIndicator.setPosition(50, 50);
        stage.addActor(turnIndicator);

        Table board = new Table();

        vars.initialiseEndConditions();

        board.setFillParent(true);
        board.center();
        addRow(board, vars.b0, vars.b1, vars.b2);
        addRow(board, vars.b3, vars.b4, vars.b5);
        addRow(board, vars.b6, vars.b7, vars.b8);

        for (TextButton c : cells) {
            applyButtonListener(c);
        }

        TextButton exit = new TextButton("Exit", vars.skin);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(parent.SELECT);
            }
        });

        stage.addActor(board);
        stage.addActor(exit);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        turnIndicator.setText("Turn: " +  turn);

        // runs the AI player's turn, when the turn number is an even number
        if (!cells.isEmpty()) {
            if (turn % 2 == 0){
                int decider = MathUtils.random(cells.size()-1);
                cells.get(decider).setText("X");
                crossed.add(cells.get(decider));
                cells.remove(decider);
                turn++;
            }
        }

        // tests the win condition, adds points if won and sets the screen to a new TextScreen
        for (int i = 0; i < vars.endCondition.length; i++){
            if (noughts.contains(vars.endCondition[i].get(0)) &&
                    noughts.contains(vars.endCondition[i].get(1)) &&
                    noughts.contains(vars.endCondition[i].get(2))){
                win = true;
                parent.setScreen(new TextScreen(parent, "Well done, you're not a total failure"));
                parent.addPoints(Constant.FINISHMINIGAMEPOINTS);
                dispose();
            }
        }

        // tests the lose condition and, if lost, sets the screen to a new TextScreen
        for (int i = 0; i < vars.endCondition.length; i++){
            if (crossed.contains(vars.endCondition[i].get(0)) &&
                    crossed.contains(vars.endCondition[i].get(1)) &&
                    crossed.contains(vars.endCondition[i].get(2))){
                if (win == false) {
                    parent.setScreen(new TextScreen(parent, "Wow, you really suck"));
                    dispose();
                }
            }
        }

        if (turn == 10 && win == false){
            parent.setScreen(new TextScreen(parent, "A draw! the most exciting of endings"));
            dispose();
        }

        this.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        this.stage.draw();
    }

    /**
     * Applies a listener to a TextButton. When clicked, checks if the button is in cells (is EMPTY) and
     * if it is, moves it to noughts and sets the text to "0"
     *
     * @param b1 the button to add the listener to
     */
    private void applyButtonListener(final TextButton b1) {
        b1.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (cells.contains(b1)) {
                    noughts.add(b1);
                    cells.remove(b1);
                    b1.setText("0");
                    turn++;
                }
                return false;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
            }
        });
    }

    /**
     * Adds a row of 3 buttons to a table. Used to avoid duplicate code
     *
     * @param table the table you wish to add the rows to
     *
     *              The three buttons you wish to add to the row:
     * @param b3
     * @param b4
     * @param b5
     */
    private void addRow(Table table, TextButton b3, TextButton b4, TextButton b5) {
        table.row().pad(0,0,5,0);
        table.add(b3).pad(5);
        table.add(b4).pad(5);
        table.add(b5).pad(5);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
//Change ends ; Reference TICTACTOE
