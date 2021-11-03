package com.mygdx.monster.round_4;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the marowak in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Marowak extends Monster
{
    public Marowak(float x, float y, Stage s, String[] files)
    {
        super(x, y, s, files);
        if(getX() >= 3000)
            setScaleX(getScaleX()*(-1));
    }

    public void act(float dt)
    {
        super.act(dt);

        if(timer >= 3)
        {
            throwBone();
            timer = 0;
        }
        timer += dt;

        applyPhysics(dt);
        boundToWorld();
    }

    public void throwBone()
    {
        if(getStage() == null)
            return;

        MarowakBone bone = new MarowakBone(0, 0, getStage());
        bone.centerAtActor(this);
        bone.setScaleX(this.getScaleX());
        if(this.getX() >= 3000)
            bone.setMotionAngle(0);
        else
            bone.setMotionAngle(180);
    }
}
