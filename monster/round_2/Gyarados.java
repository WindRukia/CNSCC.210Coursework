package com.mygdx.monster.round_2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.actor.SuperRabbit;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the gyarados in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Gyarados extends Monster
{
    private boolean started = false;
    private SuperRabbit actor = null;

    public Gyarados(float x, float y, Stage s, String[] files) { super(x, y, s, files); }

    public void act(float dt)
    {
        super.act(dt);

        if(actor == null)
            actor = (SuperRabbit) BaseActor.getList(this.getStage(), "com.mygdx.actor.SuperRabbit").get(0);

        if(actor.getX()>2880 && !started)
        {
            started = true;
            setSize(512, 512);
            addAction(Actions.moveBy(0, 512, 1.0f));
            addAction(Actions.after(Actions.fadeOut(0.5f)));
            addAction(Actions.after(Actions.removeActor()));
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
