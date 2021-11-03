package com.mygdx.monster.round_1;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.actor.SuperRabbit;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the ponyta in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Ponyta extends Monster
{
    private SuperRabbit actor = null;
    private boolean crashed = false;

    public Ponyta(float x, float y, Stage s, String file) { super(x, y, s, file); }

    public void act(float dt)
    {
        super.act(dt);

        if(actor == null)
            actor = (SuperRabbit) BaseActor.getList(getStage(), "com.mygdx.actor.SuperRabbit").get(0);
        else
        {
            if(this.overlaps(actor) && !crashed && this.getX()+50<actor.getX())
            {
                crashed = true;
                setAnimation(loadTexture("monster/round_1/monster_8/8-2.png"));
                setSize(64, 64);
                setScaleX(getScaleX()*(-1));
                actor.addAction(Actions.moveBy(400, 500, 0.5f));
            }
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
