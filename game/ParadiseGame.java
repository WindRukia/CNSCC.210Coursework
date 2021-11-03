package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.bases.BaseGame;
import com.mygdx.screens.*;

public class ParadiseGame extends BaseGame
{
	public static Music backgroundMusic;
	public static Sound deathSound;

	@Override
	public void create()
	{
		super.create();
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/background.mp3"));
		backgroundMusic.setLooping(true);
		backgroundMusic.play();

		deathSound = Gdx.audio.newSound(Gdx.files.internal("sound/death.wav"));

		setActiveScreen(new WelcomeScreen());
	}

	@Override
	public void render() { super.render(); }

	@Override
	public void dispose() { super.dispose(); }
}
