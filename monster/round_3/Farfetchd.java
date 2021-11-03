package com.mygdx.monster.round_3;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the farfetchd in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Farfetchd extends Monster
{
    private boolean start = false;

    public Farfetchd(float x, float y, Stage s, String file) { super(x, y, s, file); }

    public void act(float dt)
    {
        super.act(dt);

        if(!start && timer>1)
        {
            start = true;
            setMovement(600, getRotation()+180);
        }
        else if(!start)
        {
            timer += dt;
            setSize(getWidth()+0.5f, getHeight()+0.5f);
        }

        applyPhysics(dt);
        wrapAroundWorld();
    }
}
