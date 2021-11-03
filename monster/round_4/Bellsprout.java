package com.mygdx.monster.round_4;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.actor.SuperRabbit;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the bellsprout in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Bellsprout extends Monster
{
    private boolean started = false;
    private SuperRabbit actor = null;

    public Bellsprout(float x, float y, Stage s, String file) { super(x, y, s, file); }

    public void act(float dt)
    {
        super.act(dt);

        if(actor == null)
            actor = (SuperRabbit) BaseActor.getList(this.getStage(), "com.mygdx.actor.SuperRabbit").get(0);

        if(!started && actor.getX()>=960 && actor.getY()<=320)
        {
            setMovement(150, 180);
            started = true;
        }
        else if(started)
        {
            if(timer >= 3)
            {
                setScaleX(getScaleX()*(-1));
                setMovement(150, (getMotionAngle()+180)%360);
                timer = 0;
            }
            timer += dt;
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
