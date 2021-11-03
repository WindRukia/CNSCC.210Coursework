package com.mygdx.monster.round_2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the seaking in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Seaking extends Monster
{
    private char direction = '\0';

    public Seaking(float x, float y, Stage s, String file) { super(x, y, s, file); }

    public void setDirection(char direction) { this.direction = direction; }

    public void act(float dt)
    {
        super.act(dt);

        if(timer >= 3)
        {
            if(direction == 'h')
                setScaleX(getScaleX()*(-1));
            setMotionAngle((getMotionAngle()+180)%360);
            timer = 0;
        }
        timer += dt;

        applyPhysics(dt);
        boundToWorld();
    }
}
