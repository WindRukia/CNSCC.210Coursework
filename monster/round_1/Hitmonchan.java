package com.mygdx.monster.round_1;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the hitmonchan in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Hitmonchan extends Monster
{
    public Hitmonchan(float x, float y, Stage s, String file)
    {
        super(x, y, s, file);
    }

    public void act(float dt)
    {
        super.act(dt);

        applyPhysics(dt);
        boundToWorld();
    }
}
