package com.mygdx.monster.round_1;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.monster.Monster;

/**
 * This class represents the charizard in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Charizard extends Monster
{
    private boolean start = false;

    public Charizard(float x, float y, Stage s, String file) { super(x, y, s, file); }

    public void act(float dt)
    {
        super.act(dt);

        if(!start && timer>1)
        {
            start = true;
            setAnimation(loadTexture("monster/round_1/monster_3/3-2.png"));
            setSize(128, 128);
            setMovement(400, getRotation()+180);
        }
        else if(!start)
        {
            timer += dt;
            setY(getY()+0.5f);
        }

        applyPhysics(dt);
        wrapAroundWorld();
    }
}
