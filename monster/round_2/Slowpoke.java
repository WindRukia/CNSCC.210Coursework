package com.mygdx.monster.round_2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents slowpoke.
 * @author Boning Li
 * @version 1.0
 */
public class Slowpoke extends Monster
{
    private Shellder shellder = null;
    private Slowbro slowbro = null;
    private boolean started = false;

    public Slowpoke(float x, float y, Stage s, String file) { super(x, y, s, file); }

    public void act(float dt)
    {
        super.act(dt);

        if(shellder == null)
            shellder = (Shellder) BaseActor.getList(this.getStage(),
                    "com.mygdx.monster.round_2.Shellder").get(0);

        if(shellder.overlaps(this) && !started)
        {
            started = true;
            slowbro = new Slowbro(0, 0, this.getStage(), "monster/round_2/monster_12/12-2.png");
            slowbro.centerAtActor(this);
            shellder.addAction(Actions.removeActor());
            this.addAction(Actions.removeActor());
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
