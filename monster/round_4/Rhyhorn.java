package com.mygdx.monster.round_4;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.actor.SuperRabbit;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the rhyhorn in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Rhyhorn extends Monster
{
    private SuperRabbit actor = null;
    private boolean active = false;

    public Rhyhorn(float x, float y, Stage s, String file) { super(x, y, s, file); }

    public void act(float dt)
    {
        super.act(dt);

        if(actor == null)
            actor = (SuperRabbit) BaseActor.getList(this.getStage(), "com.mygdx.actor.SuperRabbit").get(0);

        if(!active && actor.getX()>=768)
        {
            active = true;
            setMovement(64, 180);
            timer += dt;
        }
        else if(active)
        {
            if(timer >= 3)
            {
                setScaleX(getScaleX()*(-1));
                setMovement(64, (getMotionAngle()+180)%360);
                timer = 0;
            }
            timer += dt;
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
