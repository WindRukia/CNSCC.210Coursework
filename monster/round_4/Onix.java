package com.mygdx.monster.round_4;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.actor.SuperRabbit;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the onix in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Onix extends Monster
{
    private boolean active = false;
    private SuperRabbit actor = null;

    public Onix(float x, float y, Stage s, String file)
    {
        super(x, y, s, file);
        setVisible(false);
    }

    public void act(float dt)
    {
        super.act(dt);

        if(actor == null)
            actor = (SuperRabbit) BaseActor.getList(this.getStage(), "com.mygdx.actor.SuperRabbit").get(0);

        if(!active && actor.overlaps(this))
        {
            active = true;
            setVisible(true);
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
