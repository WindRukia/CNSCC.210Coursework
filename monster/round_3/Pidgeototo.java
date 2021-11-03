package com.mygdx.monster.round_3;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the pidgeototo in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Pidgeototo extends Monster
{
    public Pidgeototo(float x, float y, Stage s, String file)
    {
        super(x, y, s, file);
        setMovement(128, 270);
    }

    public void act(float dt)
    {
        super.act(dt);

        if(timer >= 30)
        {
            setMovement(12.8f, (getMotionAngle()+180)%360);
            timer = 0;
        }
        timer += dt;

        applyPhysics(dt);
        boundToWorld();
    }
}
