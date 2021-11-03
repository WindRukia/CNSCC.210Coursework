package com.mygdx.monster.round_3;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the pidgey in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Pidgey extends Monster
{
    public Pidgey(float x, float y, Stage s, String file)
    {
        super(x, y, s, file);
        setMovement(200, 225);
        setRotation(45);
    }

    public void act(float dt)
    {
        super.act(dt);

        if(timer >= 30)
        {
            setScaleX(getScaleX()*(-1));
            setMovement(20, (getMotionAngle()+180)%360);
            timer = 0;
        }
        timer += dt;

        applyPhysics(dt);
        boundToWorld();
    }
}
