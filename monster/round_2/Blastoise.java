package com.mygdx.monster.round_2;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.actor.SuperRabbit;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the blastoise in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Blastoise extends Monster
{
    private SuperRabbit actor = null;

    public Blastoise(float x, float y, Stage s, String file)
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

        if(actor.getX() >= 1088)
        {
            Vector2 vector = new Vector2(actor.getX()-this.getX(), actor.getY()-this.getY());
            setMotionAngle(vector.angleDeg());
            if(getMotionAngle()>=90 && getMotionAngle()<=270)
                setAnimation(loadTexture("monster/round_2/monster_3/3-2.png"));
            else
                setAnimation(loadTexture("monster/round_2/monster_3/3-1.png"));
            setSize(256, 256);
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
