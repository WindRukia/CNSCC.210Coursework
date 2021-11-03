package com.mygdx.monster.round_2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.actor.SuperRabbit;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the tentacruel in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Tentacruel extends Monster
{
    private SuperRabbit actor = null;
    private boolean active = false;

    public Tentacruel(float x, float y, Stage s, String[] files)
    {
        super(x, y, s, files);
        setVisible(false);
    }

    public void act(float dt)
    {
        super.act(dt);

        if(actor == null)
            actor = (SuperRabbit) BaseActor.getList(this.getStage(), "com.mygdx.actor.SuperRabbit").get(0);

        if(actor.overlaps(this) && !active)
        {
            setVisible(true);
            active = true;
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
