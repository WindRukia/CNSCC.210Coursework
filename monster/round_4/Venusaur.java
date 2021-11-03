package com.mygdx.monster.round_4;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.actor.SuperRabbit;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the venusaur in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Venusaur extends Monster
{
    private SuperRabbit actor = null;

    public Venusaur(float x, float y, Stage s, String file) { super(x, y, s, file); }

    public void act(float dt)
    {
        super.act(dt);

        if(actor == null)
            actor = (SuperRabbit) BaseActor.getList(this.getStage(), "com.mygdx.actor.SuperRabbit").get(0);

        if(actor.getX()>=1856 && actor.getY()>=246)
        {
            setSpeed(80);
            if(actor.getX()<=this.getX())
                setMotionAngle(180);
            else
                setMotionAngle(0);
        }
        else
            setSpeed(0);

        applyPhysics(dt);
        boundToWorld();
    }
}
