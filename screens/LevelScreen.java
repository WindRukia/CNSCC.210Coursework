package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.maps.*;
import com.mygdx.actor.SuperRabbit;
import com.mygdx.bases.BaseActor;
import com.mygdx.bases.BaseGame;
import com.mygdx.bases.BaseScreen;
import com.mygdx.game.ParadiseGame;
import com.mygdx.monster.round_1.*;
import com.mygdx.tilemap.TileMapActor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This Screen represents base screen for the first set of maps
 * @author Yang Tang
 * @version 1.0
 */
public class LevelScreen extends BaseScreen {
    protected SuperRabbit jack; // Leading actor
    protected boolean gameOver; // Determine if the game is over
    protected float time; // Time remaining
    protected float deadTime; // Time after death
    protected Label timeLabel; // Display time
    protected Label messageLabel; // Display information
    protected Label powerLabel; // Display power of character
    protected Table keyTable;
    protected static boolean isDeathSoundPermitted = true;
    protected static String start = "Start"; // Entrance
    protected static String fileName = null; // Tilemap file path
    protected static String nextScreenClass = null; // Class name of the next screen
    protected static String thisScreenClass = null; // Class name of the current screen
    protected static boolean isInfinitePower = false; // Determine if character gets infinite power
    protected static boolean digPower = false; // Determine if character gets ability to dig soil
    protected static boolean isSucceed = false; // Determine if the user has succeeded

    @Override
    public void initialize() {
        // Initialize the object that interacts with tilemap
        TileMapActor tma = new TileMapActor(fileName, mainStage);
        // Get Solid from tilemap and create Solid objects
        for (MapObject obj : tma.getRectangleList("Solid")) {
            MapProperties props = obj.getProperties();
            new Solid((float) props.get("x"), (float) props.get("y"), (float) props.get("width"),
                    (float) props.get("height"), mainStage);
        }
        // Get Timer from tilemap and create Timer objects
        for (MapObject obj : tma.getTileList("Timer")) {
            MapProperties props = obj.getProperties();
            new Timer((float) props.get("x"), (float) props.get("y"), mainStage);
        }
        // Get Power from tilemap and create Power objects
        for (MapObject obj : tma.getTileList("Power")) {
            MapProperties props = obj.getProperties();
            new Power((float) props.get("x"), (float) props.get("y"), mainStage);
        }
        // Get tips in tilemap and create corresponding objects, and set positions
        for (MapObject obj : tma.getTileList("Tip1")) {
            MapProperties props = obj.getProperties();
            new Tip1((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip1.x = (float) props.get("x");
            Tip1.y = (float) props.get("y");
        }

        for (MapObject obj : tma.getTileList("Tip2")) {
            MapProperties props = obj.getProperties();
            new Tip2((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip2.x = (float) props.get("x");
            Tip2.y = (float) props.get("y");
        }

        for (MapObject obj : tma.getTileList("Tip3")) {
            MapProperties props = obj.getProperties();
            new Tip3((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip3.x = (float) props.get("x");
            Tip3.y = (float) props.get("y");
        }

        for (MapObject obj : tma.getTileList("Tip4")) {
            MapProperties props = obj.getProperties();
            new Tip4((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip4.x = (float) props.get("x");
            Tip4.y = (float) props.get("y");
        }

        for (MapObject obj : tma.getTileList("Tip5")) {
            MapProperties props = obj.getProperties();
            new Tip5((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip5.x = (float) props.get("x");
            Tip5.y = (float) props.get("y");
        }

        for (MapObject obj : tma.getTileList("Tip6")) {
            MapProperties props = obj.getProperties();
            new Tip6((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip6.x = (float) props.get("x");
            Tip6.y = (float) props.get("y");
        }

        for (MapObject obj : tma.getTileList("Tip7")) {
            MapProperties props = obj.getProperties();
            new Tip7((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip7.x = (float) props.get("x");
            Tip7.y = (float) props.get("y");
        }

        for (MapObject obj : tma.getTileList("Tip8")) {
            MapProperties props = obj.getProperties();
            new Tip8((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip8.x = (float) props.get("x");
            Tip8.y = (float) props.get("y");
        }

        for (MapObject obj : tma.getTileList("Tip9")) {
            MapProperties props = obj.getProperties();
            new Tip9((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip9.x = (float) props.get("x");
            Tip9.y = (float) props.get("y");
        }

        for (MapObject obj : tma.getTileList("Tip10")) {
            MapProperties props = obj.getProperties();
            new Tip10((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip10.x = (float) props.get("x");
            Tip10.y = (float) props.get("y");
        }

        for (MapObject obj : tma.getTileList("Tip11")) {
            MapProperties props = obj.getProperties();
            new Tip11((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip11.x = (float) props.get("x");
            Tip11.y = (float) props.get("y");
        }

        for (MapObject obj : tma.getTileList("Tip12")) {
            MapProperties props = obj.getProperties();
            new Tip12((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip12.x = (float) props.get("x");
            Tip12.y = (float) props.get("y");
        }

        for (MapObject obj : tma.getTileList("Tip13")) {
            MapProperties props = obj.getProperties();
            new Tip13((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip13.x = (float) props.get("x");
            Tip13.y = (float) props.get("y");
        }

        for (MapObject obj : tma.getTileList("Tip14")) {
            MapProperties props = obj.getProperties();
            new Tip14((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip14.x = (float) props.get("x");
            Tip14.y = (float) props.get("y");
        }

        for (MapObject obj : tma.getTileList("Tip15")) {
            MapProperties props = obj.getProperties();
            new Tip15((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip15.x = (float) props.get("x");
            Tip15.y = (float) props.get("y");
        }

        for (MapObject obj : tma.getTileList("Tip16")) {
            MapProperties props = obj.getProperties();
            new Tip16((float) props.get("x"), (float) props.get("y"), mainStage);
            Tip16.x = (float) props.get("x");
            Tip16.y = (float) props.get("y");
        }
        // Get Monster in tilemap and create corresponding objects
        for (MapObject obj : tma.getTileList("Mon1")) {
            MapProperties props = obj.getProperties();
            String[] files = {"monster/round_1/monster_1/1-1.png", "monster/round_1/monster_1/1-2.png"};
            new Charmander((float) props.get("x"), (float) props.get("y"), mainStage, files);
        }

        for (MapObject obj : tma.getTileList("Mon2")) {
            MapProperties props = obj.getProperties();
            String[] files = {"monster/round_1/monster_6/6-1.png", "monster/round_1/monster_6/6-2.png"};
            new Growlithe((float) props.get("x"), (float) props.get("y"), mainStage, "monster/round_1/monster_6/6-1.png");
        }

        for (MapObject obj : tma.getTileList("Mon3")) {
            MapProperties props = obj.getProperties();
            new Vulpix((float) props.get("x"), (float) props.get("y"), mainStage, "monster/round_1/monster_4/4-1.png");
        }

        for (MapObject obj : tma.getTileList("Mon4")) {
            MapProperties props = obj.getProperties();
            String[] files = {"monster/round_1/monster_8/8-1.png", "monster/round_1/monster_8/8-2.png"};
            new Ponyta((float) props.get("x"), (float) props.get("y"), mainStage, "monster/round_1/monster_8/8-1.png");
        }

        for (MapObject obj : tma.getTileList("Mon5")) {
            MapProperties props = obj.getProperties();
            String[] files = {"monster/round_1/monster_9/9-1.png", "monster/round_1/monster_9/9-2.png"};
            new Rapidash((float) props.get("x"), (float) props.get("y"), mainStage, "monster/round_1/monster_9/9-1.png");
        }

        for (MapObject obj : tma.getTileList("Mon6")) {
            MapProperties props = obj.getProperties();
            new Ninetales((float) props.get("x"), (float) props.get("y"), mainStage, "monster/round_1/monster_5/5-1.png");
        }

        for (MapObject obj : tma.getTileList("Mon7")) {
            MapProperties props = obj.getProperties();
            new Hitmonlee((float) props.get("x"), (float) props.get("y"), mainStage, "monster/round_1/monster_11/11.png");
        }

        for (MapObject obj : tma.getTileList("Mon8")) {
            MapProperties props = obj.getProperties();
            new Hitmonchan((float) props.get("x"), (float) props.get("y"), mainStage, "monster/round_1/monster_10/10.png");
        }

        for (MapObject obj : tma.getTileList("Mon9")) {
            MapProperties props = obj.getProperties();
            String[] files = {"monster/round_1/monster_3/3-1.png", "monster/round_1/monster_3/3-2.png"};
            new Charizard((float) props.get("x"), (float) props.get("y"), mainStage, "monster/round_1/monster_3/3-1.png");
        }

        for (MapObject obj : tma.getTileList("Mon10")) {
            MapProperties props = obj.getProperties();
            String[] files = {"monster/round_1/monster_7/7-1.png", "monster/round_1/monster_7/7-2.png"};
            new Arcanine((float) props.get("x"), (float) props.get("y"), mainStage, "monster/round_1/monster_7/7-1.png");
        }

        for (MapObject obj : tma.getTileList("Mon11")) {
            MapProperties props = obj.getProperties();
            String[] files = {"monster/round_1/monster_2/2-1.png", "monster/round_1/monster_2/2-2.png"};
            new Charmeleon((float) props.get("x"), (float) props.get("y"), mainStage, files);
        }
        // Get fake door represented by Trick in tilemap and create objects
        for (MapObject obj : tma.getRectangleList("Trick")) {
            MapProperties props = obj.getProperties();
            new Trick((float) props.get("x"), (float) props.get("y"), mainStage);
        }
        // Get exit in tilemap and create objects
        for (MapObject obj : tma.getRectangleList("End")) {
            MapProperties props = obj.getProperties();
            new End((float) props.get("x"), (float) props.get("y"), mainStage);
        }

        for (MapObject obj : tma.getRectangleList("End1")) {
            MapProperties props = obj.getProperties();
            new End1((float) props.get("x"), (float) props.get("y"), mainStage);
        }
        // Get death trap represented by Lava in tilemap and create objects
        for (MapObject obj : tma.getRectangleList("Lava")) {
            MapProperties props = obj.getProperties();
            new Lava((float) props.get("x"), (float) props.get("y"), mainStage);
        }
        // Get Wire in tilemap and create objects
        for (MapObject obj : tma.getRectangleList("Wire")) {
            MapProperties props = obj.getProperties();
            new Wire((float) props.get("x"), (float) props.get("y"), mainStage);
        }
        // Get the position that the character is upon enter the game, and create character object
        MapObject startPoint = tma.getRectangleList("Start").get(0);
        MapProperties startProps = startPoint.getProperties();
        jack = new SuperRabbit((float) startProps.get("x"), (float) startProps.get("y"), mainStage);
        // Set initial value for properties
        jack.toFront();
        gameOver = false;
        time = 60;
        deadTime = 0;
        powerLabel = new Label("Power: " + jack.getPower(), BaseGame.labelStyle);
        powerLabel.setColor(Color.RED);
        keyTable = new Table();
        timeLabel = new Label("Time: " + (int) time, BaseGame.labelStyle);
        timeLabel.setColor(Color.LIGHT_GRAY);
        messageLabel = new Label("Message", BaseGame.labelStyle);
        messageLabel.setVisible(false);

        // Set user interface elements
        UITable.pad(20);
        UITable.add(powerLabel);
        UITable.add(keyTable).expandX();
        UITable.add(timeLabel);
        UITable.row();
        UITable.add(messageLabel).colspan(3).expandY();
    }

    public void update(float dt) {
        // If the user overlaps with the door and presses key "E", then he enters the next stage
        for (BaseActor end : BaseActor.getList(mainStage, "com.mygdx.maps.End")) {
            if (jack.overlaps(end) && Gdx.input.isKeyPressed(Keys.E)) {
                try {
                    ParadiseGame.setActiveScreen((BaseScreen) Class.forName(nextScreenClass).newInstance());
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        for (BaseActor end1 : BaseActor.getList(mainStage, "com.mygdx.maps.End1")) {
            if (jack.overlaps(end1)) {
                start = "Start";
                ParadiseGame.setActiveScreen(new Screen1_11());
            }
        }
        // If the user enters the tricky door, he would restart the current stage
        for (BaseActor trick : BaseActor.getList(mainStage, "com.mygdx.maps.Trick"))
            if (jack.overlaps(trick) && Gdx.input.isKeyPressed(Keys.E)) {
                try {
                    ParadiseGame.setActiveScreen((BaseScreen) Class.forName(thisScreenClass).newInstance());
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        // If the user overlaps with the wire and presses key "R", he would be able to scramble the wire
        for (BaseActor wire : BaseActor.getList(mainStage, "com.mygdx.maps.Wire"))
            if (jack.overlaps(wire)) {
                if (Gdx.input.isKeyPressed(Keys.R)) {
                    jack.scramble();
                }
            }
        // If the user touches the lava, he dies, and game over
        for (BaseActor lava : BaseActor.getList(mainStage, "com.mygdx.maps.Lava")) {
            if (jack.overlaps(lava)) {
                ParadiseGame.backgroundMusic.pause();
                if (isDeathSoundPermitted) {
                    ParadiseGame.deathSound.play();
                    isDeathSoundPermitted = false;
                }
                messageLabel.setText("Dead!");
                messageLabel.setColor(Color.RED);
                messageLabel.setVisible(true);
                jack.remove();
                gameOver = true;
            }
        }

        // If the user collides with monsters, he dies and game over
        for (BaseActor monster : BaseActor.getList(mainStage, "com.mygdx.monster.round_1.Charmander")) {
            if (jack.overlaps(monster)) {
                ParadiseGame.backgroundMusic.pause();
                if (isDeathSoundPermitted) {
                    ParadiseGame.deathSound.play();
                    isDeathSoundPermitted = false;
                }
                messageLabel.setText("Dead!");
                messageLabel.setColor(Color.RED);
                messageLabel.setVisible(true);
                jack.remove();
                gameOver = true;
            }
        }

        for (BaseActor monster : BaseActor.getList(mainStage, "com.mygdx.monster.round_1.CharmanderFire")) {
            if (jack.overlaps(monster)) {
                ParadiseGame.backgroundMusic.pause();
                if (isDeathSoundPermitted) {
                    ParadiseGame.deathSound.play();
                    isDeathSoundPermitted = false;
                }
                messageLabel.setText("Dead!");
                messageLabel.setColor(Color.RED);
                messageLabel.setVisible(true);
                jack.remove();
                gameOver = true;
            }
        }

        for (BaseActor monster : BaseActor.getList(mainStage, "com.mygdx.monster.round_1.CharmeleonFire")) {
            if (jack.overlaps(monster)) {
                ParadiseGame.backgroundMusic.pause();
                if (isDeathSoundPermitted) {
                    ParadiseGame.deathSound.play();
                    isDeathSoundPermitted = false;
                }
                messageLabel.setText("Dead!");
                messageLabel.setColor(Color.RED);
                messageLabel.setVisible(true);
                jack.remove();
                gameOver = true;
            }
        }

        for (BaseActor monster : BaseActor.getList(mainStage, "com.mygdx.monster.round_1.Growlithe")) {
            if (jack.overlaps(monster)) {
                ParadiseGame.backgroundMusic.pause();
                if (isDeathSoundPermitted) {
                    ParadiseGame.deathSound.play();
                    isDeathSoundPermitted = false;
                }
                messageLabel.setText("Dead!");
                messageLabel.setColor(Color.RED);
                messageLabel.setVisible(true);
                jack.remove();
                gameOver = true;
            }
        }

        for (BaseActor monster : BaseActor.getList(mainStage, "com.mygdx.monster.round_1.Rapidash")) {
            if (jack.overlaps(monster)) {
                ParadiseGame.backgroundMusic.pause();
                if (isDeathSoundPermitted) {
                    ParadiseGame.deathSound.play();
                    isDeathSoundPermitted = false;
                }
                messageLabel.setText("Dead!");
                messageLabel.setColor(Color.RED);
                messageLabel.setVisible(true);
                jack.remove();
                gameOver = true;
            }
        }

        for (BaseActor monster : BaseActor.getList(mainStage, "com.mygdx.monster.round_1.Hitmonlee")) {
            if (monster.isVisible() && jack.overlaps(monster) && jack.getY()<=384) {
                ParadiseGame.backgroundMusic.pause();
                if (isDeathSoundPermitted) {
                    ParadiseGame.deathSound.play();
                    isDeathSoundPermitted = false;
                }
                messageLabel.setText("Dead!");
                messageLabel.setColor(Color.RED);
                messageLabel.setVisible(true);
                jack.remove();
                gameOver = true;
            }
        }

        for (BaseActor monster : BaseActor.getList(mainStage, "com.mygdx.monster.round_1.Hitmonchan")) {
            if (monster.isVisible() && jack.overlaps(monster) && jack.getY()<=384) {
                ParadiseGame.backgroundMusic.pause();
                if (isDeathSoundPermitted) {
                    ParadiseGame.deathSound.play();
                    isDeathSoundPermitted = false;
                }
                messageLabel.setText("Dead!");
                messageLabel.setColor(Color.RED);
                messageLabel.setVisible(true);
                jack.remove();
                gameOver = true;
            }
        }

        for (BaseActor monster : BaseActor.getList(mainStage, "com.mygdx.monster.round_1.Charizard")) {
            if (jack.overlaps(monster)) {
                ParadiseGame.backgroundMusic.pause();
                if (isDeathSoundPermitted) {
                    ParadiseGame.deathSound.play();
                    isDeathSoundPermitted = false;
                }
                messageLabel.setText("Dead!");
                messageLabel.setColor(Color.RED);
                messageLabel.setVisible(true);
                jack.remove();
                gameOver = true;
            }
        }

        for (BaseActor monster : BaseActor.getList(mainStage, "com.mygdx.monster.round_1.Arcanine")) {
            if (jack.overlaps(monster) && jack.getY()<=192) {
                ParadiseGame.backgroundMusic.pause();
                if (isDeathSoundPermitted) {
                    ParadiseGame.deathSound.play();
                    isDeathSoundPermitted = false;
                }
                messageLabel.setText("Dead!");
                messageLabel.setColor(Color.RED);
                messageLabel.setVisible(true);
                jack.remove();
                gameOver = true;
            }
        }

        for (BaseActor monster : BaseActor.getList(mainStage, "com.mygdx.monster.round_1.Charmeleon")) {
            if (jack.overlaps(monster)) {
                ParadiseGame.backgroundMusic.pause();
                if (isDeathSoundPermitted) {
                    ParadiseGame.deathSound.play();
                    isDeathSoundPermitted = false;
                }
                messageLabel.setText("Dead!");
                messageLabel.setColor(Color.RED);
                messageLabel.setVisible(true);
                jack.remove();
                gameOver = true;
            }
        }
        // Set label infomation
        if (time >= 0) time -= dt;
        timeLabel.setText("Time: " + (int) time);
        powerLabel.setText("Power: " + (int) jack.getPower());
        // If the user overlaps with Timer, more time got
        for (BaseActor timer : BaseActor.getList(mainStage, "com.mygdx.maps.Timer"))
            if (jack.overlaps(timer)) {
                time += 20;
                timer.remove();
            }
        // If the user overlaps with the tip box and presses "Q", then the box opens and displays the tip
        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip1")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip1.x, Tip1.y, mainStage, "items/tips/map_1/tip1.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip2")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip2.x, Tip2.y, mainStage, "items/tips/map_1/tip2.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip3")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip3.x, Tip3.y, mainStage, "items/tips/map_1/tip3.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip4")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip4.x, Tip4.y, mainStage, "items/tips/map_1/tip4.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip5")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip5.x, Tip5.y - 100, mainStage, "items/tips/map_1/tip5.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip6")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip6.x, Tip6.y, mainStage, "items/tips/map_1/tip6.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip7")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip7.x, Tip7.y, mainStage, "items/tips/map_1/tip7.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip8")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip8.x, Tip8.y, mainStage, "items/tips/map_1/tip8.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip9")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip9.x, Tip9.y, mainStage, "items/tips/map_1/tip9.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip10")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip10.x, Tip10.y, mainStage, "items/tips/map_1/tip10.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip11")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip11.x, Tip11.y, mainStage, "items/tips/map_1/tip11.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip12")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip12.x, Tip12.y, mainStage, "items/tips/map_1/tip12.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip13")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip13.x, Tip13.y, mainStage, "items/tips/map_1/tip13.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip14")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip14.x, Tip14.y, mainStage, "items/tips/map_1/tip14.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip15")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip15.x-300, Tip15.y, mainStage, "items/tips/map_1/tip15.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip16")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Keys.Q)) {
                new TipActor(Tip16.x, Tip16.y, mainStage, "items/tips/map_1/tip16.png");
                tip.remove();
            }
        }

        for (BaseActor power : BaseActor.getList(mainStage, "com.mygdx.maps.Power"))
            if (jack.overlaps(power) && Gdx.input.isKeyPressed(Keys.Q)) {
                jack.addPower();
                powerLabel.setText("Power" + (int) jack.getPower());
                power.remove();
            }
        // If the time is up before the user arrives at the exit, game over
        if (time <= 0) {
            ParadiseGame.backgroundMusic.pause();
            if (isDeathSoundPermitted) {
                ParadiseGame.deathSound.play();
                isDeathSoundPermitted = false;
            }
            messageLabel.setText("Time Up - Game Over");
            messageLabel.setColor(Color.RED);
            messageLabel.setVisible(true);
            jack.remove();
            gameOver = true;
        }
        // Set operations for user after game over, presses "ENTER" to restart, and presses "ESC" to quit the game
        if (gameOver) {
            deadTime += dt;
            if (deadTime >= 3) {
                messageLabel.setText("Press \"ENTER\" to Restart Or \"ESC\" to Quit...");
                messageLabel.setColor(Color.GREEN);
                messageLabel.setVisible(true);
            }
            if (Gdx.input.isKeyPressed(Keys.ENTER)) {
                deadTime = 0;
                gameOver = false;
                messageLabel.setVisible(false);
                isDeathSoundPermitted = true;
                ParadiseGame.backgroundMusic.play();
                try {
                    ParadiseGame.setActiveScreen((BaseScreen) Class.forName(thisScreenClass).newInstance());
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("savings.txt"));
                    bw.write("com.mygdx.screens.Screen1_1");
                    bw.flush();
                    bw.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

                Gdx.app.exit();
            }
        }
        // Handle movements of the character once it overlaps with the Solid
        for (BaseActor actor : BaseActor.getList(mainStage, "com.mygdx.maps.Solid")) {
            Solid solid = (Solid) actor;

            if (jack.overlaps(solid) && solid.isEnabled()) {
                Vector2 offset = jack.preventOverlap(solid);
                if (offset != null) {
                    if (Math.abs(offset.x) > Math.abs(offset.y))
                        jack.velocityVec.x = 0;
                    else
                        jack.velocityVec.y = 0;
                }
            }
        }
    }

    public boolean keyDown(int keyKode) {
        if (gameOver)
            return false;
        if (keyKode == Keys.SPACE) {
            if (jack.isOnSolid())
                jack.jump();
        }
        return false;
    }

}
