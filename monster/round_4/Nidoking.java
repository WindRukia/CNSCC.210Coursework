package com.mygdx.monster.round_4;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.actor.SuperRabbit;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the nidoking in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Nidoking extends Monster
{
    private SuperRabbit actor = null;

    public Nidoking(float x, float y, Stage s, String file)
    {
        super(x, y, s, file);
        setMovement(200, 180);
    }

    public void act(float dt)
    {
        super.act(dt);

        if(actor == null)
            actor = (SuperRabbit) BaseActor.getList(this.getStage(), "com.mygdx.actor.SuperRabbit").get(0);

        if(timer >= 3)
        {
            setScaleX(getScaleX()*(-1));
            setMovement(getSpeed(), (getMotionAngle()+180)%360);
            timer = 0;
        }
        timer += dt;

        applyPhysics(dt);
        boundToWorld();
    }
}
