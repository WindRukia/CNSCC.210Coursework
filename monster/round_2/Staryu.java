package com.mygdx.monster.round_2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the staryu in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Staryu extends Monster
{
    public Staryu(float x, float y, Stage s, String file)
    {
        super(x, y, s, file);
        timer = 3;
        if(getX() <= 960)
        {
            setSpeed(150);
            setScaleX(getScaleX() * (-1));
        }
        else
            setMovement(20, 180);
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
