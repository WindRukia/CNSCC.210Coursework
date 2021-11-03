package com.mygdx.monster.round_4;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the golem in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Golem extends Monster
{
    public Golem(float x, float y, Stage s, String file)
    {
        super(x, y, s, file);
        if(getY() >= 192)
            setMovement(128, 180);
        else
            setMovement(234, 180);
    }

    public void act(float dt)
    {
        super.act(dt);

        if(timer >= 3)
        {
            setScaleX(getScaleX()*(-1));
            setMovement(getSpeed(), (getMotionAngle()+180)%360);
            timer = 0;
        }
        timer += dt;

        applyPhysics(dt);
        boundToWorld();
    }
}
