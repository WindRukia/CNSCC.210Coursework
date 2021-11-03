package com.mygdx.monster.round_3;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the foffing in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Foffing extends Monster
{
    private boolean centre = false;

    public Foffing(float x, float y, Stage s, String file)
    {
        super(x, y, s, file);
        if(getX() >= 2240)
            setMovement(200, 0);
        else if(getX() <= 1600)
        {
            setScaleX(getScaleX()*(-1));
            setMovement(200, 180);
        }
        else
        {
            setScaleX(getScaleX()*(-1));
            setMovement(150, 180);
            centre = true;
        }
    }

    public void act(float dt)
    {
        super.act(dt);

        if(timer >= 3)
        {
            if(centre)
            {
                centre = false;
                setScaleX(getScaleX()*(-1));
                setMovement(300, 0);
            }
            else
            {
                setScaleX(getScaleX()*(-1));
                setMovement(getSpeed(), (getMotionAngle()+180)%360);
            }
            timer = 0;
        }
        timer += dt;

        applyPhysics(dt);
        boundToWorld();
    }
}
