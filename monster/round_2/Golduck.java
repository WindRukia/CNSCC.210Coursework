package com.mygdx.monster.round_2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the golduck in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Golduck extends Monster
{
    public Golduck(float x, float y, Stage s, String[] files)
    {
        super(x, y, s, files);
        setAnimation(loadAnimationFromFiles(files, 0.2f, true));
    }
}
