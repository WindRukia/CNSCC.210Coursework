package com.mygdx.monster.round_4;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This represents the ivysaur of the game.
 * @author Boning Li
 * @version 1.0
 */
public class Ivysaur extends Monster
{
    public Ivysaur(float x, float y, Stage s, String file)
    {
        super(x, y, s, file);
        setMovement(150, 0);
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
