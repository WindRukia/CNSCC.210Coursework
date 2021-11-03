package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.bases.BaseActor;
import com.mygdx.bases.BaseGame;
import com.mygdx.bases.BaseScreen;
import com.mygdx.game.ParadiseGame;

import java.io.*;
import java.util.Properties;

public class WelcomeScreen extends BaseScreen
{
    public void initialize()
    {
        BaseActor background = new BaseActor(0, 0, mainStage);
        background.loadAnimationFromFiles(new String[] {"start/1.png", "start/0.png", "start/2.png", "start/0.png",
                "start/3.png", "start/0.png", "start/4.png"}, 0.2f, true);
        background.setSize(1200, 640);

        TextButton startButton = new TextButton("Start Over", BaseGame.textButtonStyle);
        startButton.addListener(
                (Event e) ->
                {
                    if(!(e instanceof InputEvent) || !((InputEvent)e).getType().equals(InputEvent.Type.touchDown))
                        return false;

                    ParadiseGame.setActiveScreen(new Screen1_5());
                    return true;
                }
        );

        TextButton continueButton = new TextButton("Continue", BaseGame.textButtonStyle);
        continueButton.addListener(
                (Event e) ->
                {
                    if(!(e instanceof InputEvent) || !((InputEvent)e).getType().equals(InputEvent.Type.touchDown))
                        return false;

                    BufferedReader bufferedReader;
                    try {
                        bufferedReader = new BufferedReader(new FileReader("savings.txt"));
                        ParadiseGame.setActiveScreen((BaseScreen) Class.forName(bufferedReader.readLine()).newInstance());
                    } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }

                    return true;
                }
        );

        TextButton quitButton = new TextButton("Quit", BaseGame.textButtonStyle);
        quitButton.addListener(
                (Event e) ->
                {
                    if(!(e instanceof InputEvent) || !((InputEvent)e).getType().equals(InputEvent.Type.touchDown))
                        return false;

                    Gdx.app.exit();
                    return true;
                }
        );

        Table temp = new Table();
        temp.add(startButton).padRight(250);
        temp.add(continueButton);
        temp.add(quitButton).padLeft(250);
        UITable.add(temp);
        UITable.bottom();
    }

    public void update(float dt)
    {
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            ParadiseGame.setActiveScreen(new Screen1_1());
    }

    @Override
    public boolean keyDown(int keycode)
    {
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
            ParadiseGame.setActiveScreen(new Screen1_1());
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
        return false;
    }
}
