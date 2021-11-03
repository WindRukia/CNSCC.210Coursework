package com.mygdx.bases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Event;

/**
 * This class encapsulates the basic functions and components for a single screen.
 * @author Boning Li
 * @version 1.0
 */
public abstract class BaseScreen implements Screen, InputProcessor
{
    protected Stage mainStage;  // the mainStage storing the actors
    protected Stage UIStage;    // the UIStage storing start menu, if any
    protected Table UITable;    // the table used to manage the layout of UI

    /**
     * The constructor of the base screen.
     */
    public BaseScreen()
    {
        // initialise two stages
        mainStage = new Stage();
        UIStage = new Stage();

        // initialise the table and add it to the UIStage
        UITable = new Table();
        UITable.setFillParent(true);
        UIStage.addActor(UITable);

        // invoke initialize()
        initialize();
    }


    /**
     * This abstract method will be overrides by the subclasses to initialize the components.
     */
    public abstract void initialize();

    /**
     * This abstract method will be overrides by the subclasses to update the screen.
     * @param dt Delta time.
     */
    public abstract void update(float dt);

    /**
     * Override the render method in Screen interface.
     * @param dt Delta time.
     */
    @Override
    public void render(float dt)
    {
        dt = Math.min(dt, 1/30f);   // make dt less than or equal to 1/30

        // invoke act() methods in two stages
        UIStage.act(dt);
        mainStage.act(dt);

        update(dt); // invoke the update method overridden by the subclasses

        // clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw the stages
        mainStage.draw();
        UIStage.draw();
    }

    // Following methods are not used in the game.

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}

    @Override
    public void show()
    {
        InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
        im.addProcessor(this);
        im.addProcessor(UIStage);
        im.addProcessor(mainStage);
    }

    @Override
    public void hide()
    {
        InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
        im.removeProcessor(this);
        im.removeProcessor(UIStage);
        im.removeProcessor(mainStage);
    }

    public boolean isTouchDownEvent(Event e)
    {
        return (e instanceof  InputEvent) && ((InputEvent)e).getType().equals(Type.touchDown);
    }

    @Override
    public boolean keyDown(int keycode) { return false; }

    @Override
    public boolean keyUp(int keycode) { return false; }

    @Override
    public boolean keyTyped(char character) { return false; }

    @Override
    public boolean mouseMoved(int screenX, int screenY) { return false; }

    @Override
    public boolean scrolled(float amountX, float amountY) { return false; }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
}
