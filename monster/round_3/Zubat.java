package com.mygdx.monster.round_3;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.actor.SuperRabbit;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the zubat in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Zubat extends Monster
{
    private SuperRabbit actor = null;

    public Zubat(float x, float y, Stage s, String file)
    {
        super(x, y, s, file);
        setSpeed(80);
        setDeceleration(0);
    }

    public void act(float dt)
    {
        super.act(dt);

        if(actor == null)
            actor = (SuperRabbit) BaseActor.getList(this.getStage(), "com.mygdx.actor.SuperRabbit").get(0);

        Vector2 vector = new Vector2(actor.getX()-this.getX(), actor.getY()-this.getY());
        setMotionAngle(vector.angleDeg());
        if(getMotionAngle()>=90 && getMotionAngle()<=270)
            setScaleX(1);
        else
            setScaleX(-1);

        applyPhysics(dt);
        boundToWorld();
    }
}
