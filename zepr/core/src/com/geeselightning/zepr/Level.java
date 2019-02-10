package com.geeselightning.zepr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;


public class Level implements Screen {

    protected Zepr parent;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Player player;
    protected ArrayList<Zombie> aliveZombies = new ArrayList<Zombie>();
    private String mapLocation;
    private Vector2 playerSpawn;
    public ArrayList<Vector2> zombieSpawnPoints;
    private ZeprInputProcessor inputProcessor = new ZeprInputProcessor();
    protected boolean isPaused;
    private Stage stage;
    private Table table;
    private Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private int[] waves;
    private int currentWave = 1;
    protected int zombiesRemaining; // the number of zombies left to kill to complete the wave
    private int zombiesToSpawn; // the number of zombies that are left to be spawned this wave
    private boolean pauseButton = false;
    Texture blank;
    Vector2 powerSpawn;
    PowerUp currentPowerUp = null;
    //Change starts ; Reference BOSSSPAWNED
    boolean bossSpawned = false;
    //Change ends ; Reference BOSSSPAWNED

    //Create labels for displaying info that player needs
    Label progressLabel = new Label("", skin);
    Label healthLabel = new Label("", skin);
    Label powerupLabel = new Label("", skin);
    //Change starts ; Reference POINTCOUNTERLABEL
    Label pointCounter = new Label("", skin);
    //Change ends ; Reference POINTCOUNTERLABEL

    //Change starts ; Reference INITAVOIDTIMER
    //Timer until player gains points for avoiding zombies
    public float avoidTimer = 0;
    //Change ends ; Reference INITAVOIDTIMER

    public Level(Zepr zepr, String mapLocation, Vector2 playerSpawn, ArrayList<Vector2> zombieSpawnPoints,
                 int[] waves, Vector2 powerSpawn) {
        parent = zepr;
        this.mapLocation = mapLocation;
        this.playerSpawn = playerSpawn;
        this.zombieSpawnPoints = zombieSpawnPoints;
        this.isPaused = false;
        this.blank = new Texture("blank.png");
        this.powerSpawn = powerSpawn;

        // Set up data for first wave of zombies
        this.waves = waves;
        this.zombiesRemaining = waves[0];
        this.zombiesToSpawn = zombiesRemaining;

        // Creating a new libgdx stage to contain the pause menu and in game UI
        this.stage = new Stage(new ScreenViewport());

        // Creating a table to hold the UI and pause menu
        this.table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
    }


    /**
     * Called once the stage is complete to update the game progress
     */
    protected void complete() {
        // implemented in subclasses
    }


    /**
     * Called when the player's health <= 0 to end the stage.
     */
    public void gameOver() {
        isPaused = true;
        parent.setScreen(new TextScreen(parent, "You died."));
    }


    /**
     * Used for collision detection between characters in Character.update()
     *
     * @return ArrayList containing all the characters currently in the level
     */
    public ArrayList<Character> getCharacters() {
        ArrayList<Character> characters = new ArrayList<Character>();

        for (Zombie zombie : aliveZombies) {
            characters.add(zombie);
        }

        characters.add(player);

        return characters;
    }

    /**
     * Spawns multiple zombies cycling through spawnPoints until the given amount have been spawned.
     *
     * @param spawnPoints locations where zombies should be spawned on this stage
     * @param amount number of zombies to spawn
     *
     * @return the number of zombies that failed to spawn
     */
    public int spawnZombies(int amount, ArrayList<Vector2> spawnPoints) {
        int notSpawned = 0;

        for (int i = 0; i < amount; i++) {
            //Change starts ; Reference SPAWNRANDOMZOMBIE
            //Gets a random number from 0 to 1 for deciding on zombie type
            float decider = MathUtils.random(3);
            Zombie zombie;
            //If statement to choose zombie type
            if(decider <= 1){
                zombie = (new Zombie(new Sprite(new Texture("zombie01.png")),
                        spawnPoints.get(i % spawnPoints.size()), this));
            }
            else if (decider <= 2){
                zombie = (new Zombie(new Sprite(new Texture("zombie02.png")),
                        spawnPoints.get(i % spawnPoints.size()), this));
                zombie.health /= Constant.ZOMBIESTATMODIFIER;
                zombie.speed *= Constant.ZOMBIESTATMODIFIER;
            }
            else{
                zombie = (new Zombie(new Sprite(new Texture("zombie03.png")),
                        spawnPoints.get(i % spawnPoints.size()), this));
                zombie.health *= Constant.ZOMBIESTATMODIFIER;
                zombie.speed /= Constant.ZOMBIESTATMODIFIER;
            }
            //Change ends ; Reference SPAWNRANDOMZOMBIE

            // Check there isn't already a zombie there, or they will be stuck
            boolean collides = false;
            for (Zombie otherZombie : aliveZombies) {
                if (zombie.collidesWith(otherZombie)) {
                    collides = true;
                    // Decrement counter as it didn't spawn.
                    notSpawned++;
                }
            }

            if (!collides) {
                aliveZombies.add(zombie);
            }
        }

        return notSpawned;
    }

    //Change starts ; Reference FIRSTBOSSSPAWN
    /**
     * Used to spawn the first boss of the game
     */
    public void spawnFirstBoss(ArrayList<Vector2> spawnPoints){
        Zombie zombie = new Zombie(new Sprite(new Texture("zomboss.png")),
                spawnPoints.get(1), this);
        zombie.health *= Constant.FIRSTBOSSSTATMODIFIER;
        zombie.speed *= Constant.FIRSTBOSSSTATMODIFIER;
        zombie.hitRange *= Constant.FIRSTBOSSRANGEMODIFIER;
        aliveZombies.add(zombie);
        zombiesRemaining++;
        bossSpawned = true;
    }
    //Change ends ; Reference FIRSTBOSSSPAWN
    //Change starts ; Reference FINALBOSSSPAWN
    /**
     * Used to spawn the final boss of the game
     */
    public void spawnFinalBoss(ArrayList<Vector2> spawnPoints){
        Zombie zombie = new Zombie(new Sprite(new Texture("zomboss.png")),
                spawnPoints.get(1), this);
        zombie.health *= Constant.FINALBOSSHEALTHMODIFIER;
        zombie.speed *= Constant.FINALBOSSSPEEDMODIFIER;
        zombie.attackDamage *= Constant.FINALBOSSDAMAGEMODIFIER;
        zombie.hitRange *= Constant.FINALBOSSRANGEMODIFIER;
        aliveZombies.add(zombie);
        zombiesRemaining++;
        bossSpawned = true;
    }
    //Change ends ; Reference FINALBOSSSPAWN

    /**
     * Used for collision detection between the player and map
     *
     * @return boolean if the point (x, y) is in a blocked tile
     */
    public boolean isBlocked(float x, float y) {
        TiledMapTileLayer collisionLayer = (TiledMapTileLayer) map.getLayers().get("collisionLayer");
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));

        // have to include this in case this cell is transparent on the collisionLayer
        if (cell == null || cell.getTile() == null) {
            return false;
        }

        MapProperties properties = cell.getTile().getProperties();
        // we have to add the property now because the properties don't load from the map file
        properties.put("solid", null);

        return properties.containsKey("solid");
    }


    /**
     * Converts the mousePosition which is a Vector2 representing the coordinates of the mouse within the game window
     * to a Vector2 of the equivalent coordinates in the world.
     *
     * @return Vector2 of the mouse position in the world.
     */
    public Vector2 getMouseWorldCoordinates() {
        // Must first convert to 3D vector as camera.unproject() does not take 2D vectors.
        Vector3 screenCoordinates = new Vector3(inputProcessor.mousePosition.x, inputProcessor.mousePosition.y, 0);
        Vector3 worldCoords3 = camera.unproject(screenCoordinates);

        return new Vector2(worldCoords3.x, worldCoords3.y);
    }

    @Override
    public void show() {
        // Start the stage unpaused.
        isPaused = false;

        // Loads the testmap.tmx file as map.
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load(mapLocation);

        // renderer renders the .tmx map as an orthogonal (top-down) map.
        renderer = new OrthogonalTiledMapRenderer(map);
        // It is only possible to view the render of the map through an orthographic camera.
        camera = new OrthographicCamera();

        //retrieve player instance and reset it
        player = Player.getInstance();
        player.respawn(playerSpawn, this);

        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render(float delta) {
        if (isPaused) {
            // Clears the screen to black.
            Gdx.gl.glClearColor(0f, 0f, 0f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            TextButton resume = new TextButton("Resume", skin);
            TextButton exit = new TextButton("Exit", skin);

            if (!pauseButton) {

                table.clear();

                table.center();
                table.add(resume).pad(10);
                table.row();
                table.add(exit);
                pauseButton = true;

            }

            // Input processor has to be changed back once unpaused.
            Gdx.input.setInputProcessor(stage);

            // Defining actions for the resume button.
            resume.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    isPaused = false;
                    // Change input processor back
                    Gdx.input.setInputProcessor(inputProcessor);
                    pauseButton = false;
                }
            });

            // Defining actions for the exit button.
            exit.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    parent.changeScreen(Zepr.SELECT);
                }
            });

            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
        } else {
            // Clears the screen to black.
            Gdx.gl.glClearColor(0f, 0f, 0f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            table.clear();

            // Try to spawn all zombie in the stage and update zombiesToSpawn with the amount that failed to spawn
            zombiesToSpawn = spawnZombies(zombiesToSpawn, zombieSpawnPoints);

            // Spawn a power up and the end of a wave, if there isn't already a powerUp on the level
            if (zombiesRemaining == 0 && currentPowerUp == null) {
                //Change starts ; Reference SPAWNPOWERUPS
                int random = (int )(Math.random() * 5 + 1);
                if (random == 1) {
                    currentPowerUp = new PowerUpHeal(this);
                } else if (random == 2){
                    // random == 2
                    currentPowerUp = new PowerUpSpeed(this);
                } else if (random == 3) {
                    currentPowerUp = new PowerUpImmunity(this);
                } else if (random == 4) {
                    currentPowerUp = new PowerUpSlow(this);
                } else{
                    currentPowerUp = new PowerUpDamage(this);
                }
                //Change ends ; Reference SPAWNPOWERUPS
            }

            if (zombiesRemaining == 0) {
                // Wave complete, increment wave number
                currentWave++;
                if (currentWave > waves.length) {
                    //Change starts ; Reference SPAWNBOSSES
                    // Spawn Boss if current level is level 3 or level 6.
                    if(this.getClass().equals(CourtyardLevel.class) && !bossSpawned) {
                        spawnFirstBoss(this.zombieSpawnPoints);
                        bossSpawned = true;
                    }
                    else if(this.getClass().equals(GlasshouseLevel.class) && !bossSpawned) {
                        spawnFinalBoss(this.zombieSpawnPoints);
                        bossSpawned = true;
                    }
                    //Change ends ; Reference SPAWNBOSSES
                    //Change starts ; Reference SAFEAREA
                    // On computer science level, wait for a while until completion is triggered
                    else if (this.getClass().equals(ComputerScienceLevel.class)){
                        table.setVisible(false);
                        if (currentWave == 500){
                            parent.addPoints(Constant.SAFEAREAPOINTS);
                            levelComplete();
                        }
                    }
                    //Change ends ; Reference SAFEAREA
                    //Change starts ; Reference ENDLEVEL
                    // Level completed, back to select screen and complete stage.
                    // If stage is being replayed complete() will stop progress being incremented.
                    else {
                        levelComplete();
                    }
                    //Change ends ; Reference ENDLEVEL
                } else {
                    // Update zombiesRemaining with the number of zombies of the new wave
                    zombiesRemaining = waves[currentWave - 1];
                    zombiesToSpawn = zombiesRemaining;
                }
            }

            // Keep the player central in the screen.
            camera.position.set(player.getCenter().x, player.getCenter().y, 0);
            camera.update();

            renderer.setView(camera);
            renderer.render();

            renderer.getBatch().begin();

            player.draw(renderer.getBatch());

            // Resolve all possible attacks
            for (int i = 0; i < aliveZombies.size(); i++) {
                Zombie zombie = aliveZombies.get(i);
                // Zombies will only attack if they are in range, the attack has cooled down, and they are
                // facing a player.
                // Player will only attack in the reverse situation but player.attack must also be true. This is
                //controlled by the ZeprInputProcessor. So the player will only attack when the user clicks.
                if (player.attack) {
                    player.attack(zombie, delta);
                }
                zombie.attack(player, delta);

                // Draw zombies
                zombie.draw(renderer.getBatch());

                // Draw zombie health bars
                int fillAmount = (int)( (zombie.getHealth() / 100) * 30);
                renderer.getBatch().setColor(Color.BLACK);
                //Change starts ; Reference BETTERHEALTHBARS
                renderer.getBatch().draw(blank, Math.round(zombie.getX() -
                                (32/100f * zombie.getHealth() - 32)/2f),
                        zombie.getY()+zombie.getHeight(), fillAmount + 2, 3);
                renderer.getBatch().setColor(Color.RED);
                renderer.getBatch().draw(blank, Math.round(zombie.getX() -
                        (32/100f * zombie.getHealth() - 32)/2f +1),
                        zombie.getY()+zombie.getHeight() + 1, fillAmount, 1);
                //Change ends ; Reference BETTERHEALTHBARS
                renderer.getBatch().setColor(Color.WHITE);
            }
            //Change starts ; Reference UPDATEAVOIDTIMER
            // if Player hasn't been hit lately, grant points. Else, decrease timer
            if(avoidTimer <= 0){
                parent.addPoints(Constant.AVOIDPOINTS * delta);
            }
            else{
                avoidTimer -= delta;
            }
            //Change ends ; Reference UPDATEAVOIDTIMER

            if (currentPowerUp != null) {
                // Activate the powerup up if the player moves over it and it's not already active
                if (currentPowerUp.overlapsPlayer() && !currentPowerUp.active) {
                    currentPowerUp.activate();
                }
                // Only render the powerup if it is not active, otherwise it disappears
                if (!currentPowerUp.active) {
                    currentPowerUp.draw(renderer.getBatch());
                }
                currentPowerUp.update(delta);
            }

            renderer.getBatch().end();


            String progressString = ("Wave " + Integer.toString(currentWave) + ", " + Integer.toString(zombiesRemaining) + " zombies remaining.");
            String healthString = ("Health: " + Integer.toString(player.health) + "HP");
            //Change starts ; Reference POINTCOUNTERSTRING
            String pointString = ("Points: " + Integer.toString(parent.getPoints()));
            //Change ends ; Reference POINTCOUNTERSTRING

            progressLabel.setText(progressString);
            healthLabel.setText(healthString);
            //Change starts ; Reference SETPOINTCOUNTERSTRING
            pointCounter.setText(pointString);
            //Change ends ; Reference SETPOINTCOUNTERSTRING

            table.top().left();
            table.add(progressLabel).pad(10);
            table.row().pad(10);
            table.add(healthLabel).pad(10).left();
            //Change starts ; Reference DISPLAYPOINTCOUNTER
            table.row();
            table.add(pointCounter).pad(10).left();
            //Change ends ; Reference DISPLAYPOINTCOUNTER
            table.row();
            table.add(powerupLabel);
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
        }
    }

    //Change starts ; Reference LEVELCOMPLETE
    private void levelComplete() {
        isPaused = true;
        complete();
        if (parent.progress == parent.COMPLETE) {
            parent.setScreen(new TextScreen(parent, "Game completed."));
        } else {
            parent.setScreen(new TextScreen(parent, "Level completed."));
        }
    }
    //Change ends ; Reference LEVELCOMPLETE

    @Override
    public void resize(int width, int height) {
        // Resize the camera depending the size of the window.
        camera.viewportHeight = height;
        camera.viewportWidth = width;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
        map.dispose();
        renderer.dispose();
        if (currentPowerUp != null) {
            currentPowerUp.getTexture().dispose();
        }
        player.getTexture().dispose();
        for (Zombie zombie : aliveZombies) {
            zombie.getTexture().dispose();
        }
    }
}
