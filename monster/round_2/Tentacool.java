package com.mygdx.monster.round_2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the tentacool in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Tentacool extends Monster
{
    public Tentacool(float x, float y, Stage s, String[] files)
    {
        super(x, y, s, files);
        setMovement(150, 270);
    }

    public void act(float dt)
    {
        super.act(dt);

        if(timer >= 2)
        {
            setMotionAngle((getMotionAngle()+180)%360);
            timer = 0;
        }
        timer += dt;

        applyPhysics(dt);
        boundToWorld();
    }
}
