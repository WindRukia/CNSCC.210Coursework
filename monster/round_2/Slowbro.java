package com.mygdx.monster.round_2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the slowbro in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Slowbro extends Monster
{
    private int counter = 1;

    public Slowbro(float x, float y, Stage s, String file)
    {
        super(x, y, s, file);
        timer = 1;
    }

    public void act(float dt)
    {
        super.act(dt);

        if(timer >= 1)
        {
            if(counter == 1)
            {
                setAnimation(loadTexture("monster/round_2/monster_12/12-2.png"));
                setMovement(200, getRotation()%360);
                counter = 1 - counter;
            }
            else if(counter == 0)
            {
                setAnimation(loadTexture("monster/round_2/monster_12/12-1.png"));
                setMovement(200, (getRotation()+180)%360);
                counter = 1- counter;
            }
            timer = 0;
        }
        timer += dt;

        applyPhysics(dt);
        boundToWorld();
    }
}
