package com.mygdx.monster.round_4;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.bases.BaseActor;

public class CuboneBone extends BaseActor
{
    public CuboneBone(float x, float y, Stage s)
    {
        super(x, y, s);
        loadTexture("monster/round_4/monster_17/17-3.png");
        setSize(32, 32);

        addAction(Actions.delay(1));
        addAction(Actions.after(Actions.fadeOut(0.5f)));
        addAction(Actions.after(Actions.removeActor()));

        setSpeed(400);
        setMaxSpeed(400);
        setDeceleration(0);
    }

    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
        boundToWorld();
    }
}
