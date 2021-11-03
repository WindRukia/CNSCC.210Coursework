package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.actor.SuperRabbit;
import com.mygdx.bases.BaseActor;
import com.mygdx.bases.BaseGame;
import com.mygdx.bases.BaseScreen;
import com.mygdx.game.ParadiseGame;
import com.mygdx.maps.*;
import com.mygdx.monster.Monster;
import com.mygdx.monster.round_1.Charmeleon;
import com.mygdx.monster.round_2.Psyduck;
import com.mygdx.monster.round_2.Shellder;
import com.mygdx.monster.round_2.Slowpoke;
import com.mygdx.monster.round_2.Squirtle;
import com.mygdx.tilemap.TileMapActor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * This Screen represents map 2-1
 * @author Yang Tang
 * @version 1.0
 */
public class Screen2_1 extends LevelScreen {
    static {
        fileName = "maps/map_2/Map 2-1.tmx";
        nextScreenClass = "com.mygdx.screens.Screen2_2";
        thisScreenClass = "com.mygdx.screens.Screen2_1";
    }

    @Override
    public void initialize() {
        TileMapActor tma = new TileMapActor(fileName, mainStage);

        for (MapObject obj : tma.getRectangleList("Solid")) {
            MapProperties props = obj.getProperties();
            new Solid((float) props.get("x"), (float) props.get("y"), (float) props.get("width"),
                    (float) props.get("height"), mainStage);
        }

        for (MapObject obj : tma.getTileList("Timer")) {
            MapProperties props = obj.getProperties();
            new Timer((float) props.get("x"), (float) props.get("y"), mainStage);
        }

        for (MapObject obj : tma.getTileList("Power")) {
            MapProperties props = obj.getProperties();
            new Power((float) props.get("x"), (float) props.get("y"), mainStage);
        }

        for (MapObject obj : tma.getRectangleList("Trick")) {
            MapProperties props = obj.getProperties();
            new Trick((float) props.get("x"), (float) props.get("y"), mainStage);
        }

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

        for (MapObject obj : tma.getTileList("Mon12")) {
            MapProperties props = obj.getProperties();
            String[] files = {"monster/round_2/monster_1/1-1.png", "monster/round_2/monster_1/1-2.png", "monster/round_2/monster_1/1-3.png"};
            new Squirtle((float) props.get("x"), (float) props.get("y"), mainStage, files);
        }

        for (MapObject obj : tma.getTileList("Mon13")) {
            MapProperties props = obj.getProperties();
            String[] files = {"monster/round_2/monster_4/4-1.png", "monster/round_2/monster_4/4-2.png"};
            new Psyduck((float) props.get("x"), (float) props.get("y"), mainStage, files);
        }

        for (MapObject obj : tma.getTileList("Mon14")) {
            MapProperties props = obj.getProperties();
            new Slowpoke((float) props.get("x"), (float) props.get("y"), mainStage, "monster/round_2/monster_11/11-1.png");
        }

        for (MapObject obj : tma.getTileList("Mon15")) {
            MapProperties props = obj.getProperties();
            new Shellder((float) props.get("x"), (float) props.get("y"), mainStage, "monster/round_2/monster_23/23-1.png");
        }

        for (MapObject obj : tma.getRectangleList("End")) {
            MapProperties props = obj.getProperties();
            new End((float) props.get("x"), (float) props.get("y"), mainStage);
        }

        for (MapObject obj : tma.getRectangleList("End1")) {
            MapProperties props = obj.getProperties();
            new End1((float) props.get("x"), (float) props.get("y"), mainStage);
        }

        for (MapObject obj : tma.getRectangleList("End2")) {
            MapProperties props = obj.getProperties();
            new End2((float) props.get("x"), (float) props.get("y"), mainStage);
        }

        for (MapObject obj : tma.getRectangleList("Lava")) {
            MapProperties props = obj.getProperties();
            new Lava((float) props.get("x"), (float) props.get("y"), mainStage);
        }

        for (MapObject obj : tma.getRectangleList("Wire")) {
            MapProperties props = obj.getProperties();
            new Wire((float) props.get("x"), (float) props.get("y"), mainStage);
        }

        MapObject startPoint = tma.getRectangleList("Start").get(0);
        MapProperties startProps = startPoint.getProperties();
        jack = new SuperRabbit((float) startProps.get("x"), (float) startProps.get("y"), mainStage);

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

        UITable.pad(20);
        UITable.add(powerLabel);
        UITable.add(keyTable).expandX();
        UITable.add(timeLabel);
        UITable.row();
        UITable.add(messageLabel).colspan(3).expandY();
    }

    public void update(float dt) {

        for (BaseActor end : BaseActor.getList(mainStage, "com.mygdx.maps.End")) {
            if (jack.overlaps(end) && Gdx.input.isKeyPressed(Input.Keys.E)) {
                try {
                    ParadiseGame.setActiveScreen((BaseScreen) Class.forName(nextScreenClass).newInstance());
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        for (BaseActor end1 : BaseActor.getList(mainStage, "com.mygdx.maps.End1")) {
            if (jack.overlaps(end1)) {
                start = "Start1";
                ParadiseGame.setActiveScreen(new Screen2_4());
            }
        }


        for (BaseActor end2 : BaseActor.getList(mainStage, "com.mygdx.maps.End2")) {
            if (jack.overlaps(end2)) {
                start = "Start2";
                ParadiseGame.setActiveScreen(new Screen2_4());
            }
        }


        for (BaseActor trick : BaseActor.getList(mainStage, "com.mygdx.maps.Trick"))
            if (jack.overlaps(trick) && Gdx.input.isKeyPressed(Input.Keys.E)) {
                try {
                    ParadiseGame.setActiveScreen((BaseScreen) Class.forName(thisScreenClass).newInstance());
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        for (BaseActor wire : BaseActor.getList(mainStage, "com.mygdx.maps.Wire"))
            if (jack.overlaps(wire)) {
                if (Gdx.input.isKeyPressed(Input.Keys.R)) {
                    jack.scramble();
                }
            }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip1")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Input.Keys.Q)) {
                new TipActor(Tip1.x, Tip1.y, mainStage, "items/tips/map_2/tip1.png");
                tip.remove();
            }
        }

        for (BaseActor tip : BaseActor.getList(mainStage, "com.mygdx.maps.Tip2")) {
            if (jack.overlaps(tip) && Gdx.input.isKeyPressed(Input.Keys.Q)) {
                new TipActor(Tip2.x, Tip2.y - 100, mainStage, "items/tips/map_2/tip2.png");
                tip.remove();
            }
        }

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

        for (BaseActor monster : BaseActor.getList(mainStage, "com.mygdx.monster.round_2.Squirtle")) {
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

        for (BaseActor monster : BaseActor.getList(mainStage, "com.mygdx.monster.round_2.Psyduck")) {
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

        for (BaseActor monster : BaseActor.getList(mainStage, "com.mygdx.monster.round_2.Slowpoke")) {
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

        for (BaseActor power : BaseActor.getList(mainStage, "com.mygdx.maps.Power"))
            if (jack.overlaps(power) && Gdx.input.isKeyPressed(Input.Keys.Q)) {
                jack.addPower();
                powerLabel.setText("Power" + (int) jack.getPower());
                power.remove();
            }

        if (time >= 0) time -= dt;
        timeLabel.setText("Time: " + (int) time);
        powerLabel.setText("Power: " + (int) jack.getPower());

        for (BaseActor timer : BaseActor.getList(mainStage, "com.mygdx.maps.Timer"))
            if (jack.overlaps(timer)) {
                time += 20;
                timer.remove();
            }

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

        if (gameOver) {
            deadTime += dt;
            if (deadTime >= 3) {
                messageLabel.setText("Press \"ENTER\" to Restart Or \"ESC\" to Quit...");
                messageLabel.setColor(Color.GREEN);
                messageLabel.setVisible(true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
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
            } else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("savings.txt"));
                    bw.write("com.mygdx.screens.Screen2_1");
                    bw.flush();
                    bw.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

                Gdx.app.exit();
            }
        }

        for (BaseActor actor : BaseActor.getList(mainStage, "com.mygdx.maps.Solid")) {
            Solid solid = (Solid) actor;

            if (solid instanceof Platform) {
                if (jack.isJumping() && jack.overlaps(solid)) {
                    solid.setEnabled(false);
                }
                if (jack.isJumping() && !jack.overlaps(solid)) {
                    solid.setEnabled(true);
                }
                if (jack.isFalling() && !jack.overlaps(solid) && !jack.belowOverlaps(solid)) {
                    solid.setEnabled(true);
                }
            }

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
}
