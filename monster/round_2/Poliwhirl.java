package com.mygdx.monster.round_2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.actor.SuperRabbit;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the poliwhirl in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Poliwhirl extends Monster
{
    private SuperRabbit actor = null;
    private boolean crashed = false;

    public Poliwhirl(float x, float y, Stage s, String file) { super(x, y, s, file); }

    public void act(float dt)
    {
        super.act(dt);

        if(actor == null)
            actor = (SuperRabbit) BaseActor.getList(getStage(), "com.mygdx.actor.SuperRabbit").get(0);
        else
        {
            if(this.overlaps(actor))
            {
                crashed = true;
                actor.addAction(Actions.moveBy(-400, 0, 0.5f));
            }
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
