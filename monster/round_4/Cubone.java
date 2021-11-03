package com.mygdx.monster.round_4;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the cubone in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Cubone extends Monster
{
    public Cubone(float x, float y, Stage s, String[] files) { super(x, y, s, files); }

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

        CuboneBone bone = new CuboneBone(0, 0, getStage());
        bone.centerAtActor(this);
        bone.setScaleX(this.getScaleX());
        bone.setMotionAngle(180);
    }
}
