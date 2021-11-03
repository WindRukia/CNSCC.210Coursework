package com.mygdx.monster.round_3;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the butterfree in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Butterfree extends Monster
{
    public Butterfree(float x, float y, Stage s, String file)
    {
        super(x, y, s, file);
        setScaleX(getScaleX()*(-1));
        setMovement(512, 0);
    }

    public void act(float dt)
    {
        super.act(dt);

        if(timer >= 3)
        {
            setScaleX(getScaleX()*(-1));
            setMovement(512, (getMotionAngle()+180)%360);
            timer = 0;
        }
        timer += dt;

        applyPhysics(dt);
        boundToWorld();
    }
}
