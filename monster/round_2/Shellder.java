package com.mygdx.monster.round_2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.actor.SuperRabbit;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the shellder in the game.
 * @author Boniing Li
 * @version 1.0
 */
public class Shellder extends Monster
{
    private SuperRabbit actor = null;
    private Slowpoke slowpoke = null;
    private boolean started = false;

    public Shellder(float x, float y, Stage s, String file) { super(x, y, s, file); }

    public void act(float dt)
    {
        super.act(dt);

        if(actor == null)
            actor = (SuperRabbit) BaseActor.getList(this.getStage(), "com.mygdx.actor.SuperRabbit").get(0);
        if(slowpoke == null)
            slowpoke = (Slowpoke) BaseActor.getList(this.getStage(),
                    "com.mygdx.monster.round_2.Slowpoke").get(0);

        if(actor.overlaps(this) && !started)
        {
            started = true;
            addAction(Actions.moveTo(slowpoke.getX()+30, slowpoke.getY()-5, 0.5f));
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
