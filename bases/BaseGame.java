package com.mygdx.bases;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.graphics.Texture;

/**
 * This class encapsulate the basic functions and components for the game.
 * @author Boning Li
 * @version 1.0
 */
public abstract class BaseGame extends Game
{
    protected static BaseGame game; // the game
    public static LabelStyle labelStyle;    // not used
    public static TextButtonStyle textButtonStyle;  // not used

    /**
     * Constructor of base game.
     */
    public BaseGame() { game = this; }

    /**
     * This method sets the screen shown in the window to the given one.
     * @param screen The screen to be displayed.
     */
    public static void setActiveScreen(BaseScreen screen) { game.setScreen(screen); }

    /**
     * This method override the create() method in the Game class.
     * It will be invoked when the game starts.
     */
    @Override
    public void create()
    {
        // set the input processor
        Gdx.input.setInputProcessor(new InputMultiplexer());

        // following piece of code is responsible for the style of the button in the welcome screen.

        labelStyle = new LabelStyle();
        labelStyle.font = new BitmapFont();

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(
                Gdx.files.internal("core/assets/OpenSans.ttf"));

        FreeTypeFontParameter fontParameters = new FreeTypeFontParameter();
        fontParameters.size = 24;
        fontParameters.color = Color.WHITE;
        fontParameters.borderWidth = 2;
        fontParameters.borderColor = Color.BLACK;
        fontParameters.borderStraight = true;
        fontParameters.minFilter = TextureFilter.Linear;
        fontParameters.magFilter = TextureFilter.Linear;

        BitmapFont customFont = fontGenerator.generateFont(fontParameters);
        labelStyle.font = customFont;

        textButtonStyle = new TextButtonStyle();
        Texture buttonTex = new Texture("button.png");
        NinePatch buttonPatch = new NinePatch(buttonTex, 24, 24, 24, 24);
        textButtonStyle.up = new NinePatchDrawable(buttonPatch);
        textButtonStyle.font = customFont;
        textButtonStyle.fontColor = Color.GRAY;
    }

    /**
     * This method is invoked when the game is shut down.
     */
    @Override
    public void dispose() { System.exit(0); }
}
