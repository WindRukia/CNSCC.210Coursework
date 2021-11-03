package com.mygdx.monster.round_3;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the weezing in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Weezing extends Monster
{
    public Weezing(float x, float y, Stage s, String file)
    {
        super(x, y, s, file);
        if(getX() <= 1600)
        {
            setScaleX(getScaleX()*(-1));
            setMovement(340, 0);
        }
        else if(getScaleX() >= 2240)
            setMovement(340, 180);
        else
            setMovement(85, 270);
    }

    public void act(float dt)
    {
        super.act(dt);

        if(timer >= 3)
        {
            if(getMotionAngle()==180 || getMotionAngle()==0)
                setScaleX(getScaleX()*(-1));
            setMovement(getSpeed(), (getMotionAngle()+180)%360);
            timer = 0;
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
