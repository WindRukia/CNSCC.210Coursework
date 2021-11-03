package com.mygdx.monster.round_1;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.bases.BaseActor;

/**
 * This class represents the fire shot by the Charizard in the game.
 * @author Boning Li
 * @version 1.0
 */
public class CharmeleonFire extends BaseActor
{
    public CharmeleonFire(float x, float y, Stage s)
    {
        super(x, y, s);
        loadTexture("monster/round_1/monster_2/fire_2.png");
        setSize(32, 32);

        addAction(Actions.delay(1));
        addAction(Actions.after(Actions.fadeOut(0.5f)));
        addAction(Actions.after(Actions.removeActor()));

        setSpeed(300);
        setMaxSpeed(300);
        setDeceleration(0);
    }

    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
        boundToWorld();
    }
}
