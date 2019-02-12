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

    private MinigameVars vars = new MinigameVars();
    private Stage stage;
    private Zepr parent;
    private ArrayList<TextButton> cells = new ArrayList<TextButton>(Arrays.asList(
            vars.b0, vars.b1, vars.b2,
            vars.b3, vars.b4, vars.b5,
            vars.b6, vars.b7, vars.b8)
    );
    private ArrayList<TextButton> noughts = new ArrayList<TextButton>();
    private ArrayList<TextButton> crossed = new ArrayList<TextButton>();
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

        if (turn % 2 == 0){
            if (!cells.isEmpty()) {
                int decider = MathUtils.random(cells.size()-1);
                cells.get(decider).setText("X");
                crossed.add(cells.get(decider));
                cells.remove(decider);
                turn++;
            }
        }

        for (int i = 0; i < vars.endCondition.length; i++){
            if (noughts.contains(vars.endCondition[i].get(0)) &&
                    noughts.contains(vars.endCondition[i].get(1)) &&
                    noughts.contains(vars.endCondition[i].get(2))){
                parent.setScreen(new TextScreen(parent, "Well done, you're not a total failure"));
                parent.addPoints(Constant.FINISHMINIGAMEPOINTS);
            }
        }

        for (int i = 0; i < vars.endCondition.length; i++){
            if (crossed.contains(vars.endCondition[i].get(0)) &&
                    crossed.contains(vars.endCondition[i].get(1)) &&
                    crossed.contains(vars.endCondition[i].get(2))){
                parent.setScreen(new TextScreen(parent, "Wow, you really suck"));
            }
        }

        this.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        this.stage.draw();
    }

    public void applyButtonListener(final TextButton b1) {
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

    }
}
