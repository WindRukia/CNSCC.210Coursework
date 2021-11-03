package com.mygdx.monster.round_2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the goldeen in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Goldeen extends Monster
{
    public Goldeen(float x, float y, Stage s, String file) { super(x, y, s, file); }


    public void act(float dt)
    {
        super.act(dt);

        if(timer >= 2)
        {
            setMovement(200, (getMotionAngle()+180)%360);
            timer = 0;
        }
        timer += dt;

        applyPhysics(dt);
        boundToWorld();
    }
}
