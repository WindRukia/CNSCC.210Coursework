package com.mygdx.monster.round_1;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the arcanine in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Arcanine extends Monster
{
    public Arcanine(float x, float y, Stage s, String file) { super(x, y, s, file); }

    public void act(float dt)
    {
        super.act(dt);

        if(getSpeed() == 0)
        {
            setAnimation(loadTexture("monster/round_1/monster_7/7-2.png"));
            setSize(96, 96);
            setMovement(300, this.getRotation());
        }
        else
        {
            timer += dt;
            if(timer >= 2.5)
            {
                setScaleX(getScaleX()*(-1));
                setMotionAngle((getMotionAngle()+180)%360);
                timer = 0;
            }
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
