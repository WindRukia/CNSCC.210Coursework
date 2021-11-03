package com.mygdx.monster.round_4;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.actor.SuperRabbit;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the victreebel in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Victreebel extends Monster
{
    private boolean active = false;
    private SuperRabbit actor = null;

    public Victreebel(float x, float y, Stage s, String file) { super(x, y, s, file); }

    public void act(float dt)
    {
        super.act(dt);

        if(actor == null)
            actor = (SuperRabbit) BaseActor.getList(this.getStage(), "com.mygdx.actor.SuperRabbit").get(0);

        if(!active && actor.getX()>=2304 && actor.getY()<=192)
        {
            active = true;
            setScaleX(getScaleX()*(-1));
            addAction(Actions.moveBy(640, -64, 2f));
            addAction(Actions.after(Actions.fadeOut(0.5f)));
            addAction(Actions.after(Actions.removeActor()));
            timer += dt;
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
