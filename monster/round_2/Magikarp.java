package com.mygdx.monster.round_2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the magikarp in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Magikarp extends Monster
{
    public Magikarp(float x, float y, Stage s, String file)
    {
        super(x, y, s, file);
        setMovement(64, 180);
    }

    public void act(float dt)
    {
        super.act(dt);

        if(timer >= 2)
        {
            setScaleX(getScaleX()*(-1));
            setMotionAngle((getMotionAngle()+180)%360);
            timer = 0;
        }
        timer += dt;

        applyPhysics(dt);
        boundToWorld();
    }
}
