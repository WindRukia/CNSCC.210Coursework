package com.mygdx.monster.round_1;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the hitmonlee in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Hitmonlee extends Monster
{
    Monster hitmonchan = null;
    BaseActor actor = null;

    public Hitmonlee(float x, float y, Stage s, String file)
    {
        super(x, y, s, file);
        setVisible(false);
    }

    public void act(float dt)
    {
        super.act(dt);

        if(this.getSpeed()==0 && active())
        {
            if(hitmonchan == null)
                hitmonchan = (Monster) BaseActor.getList(getStage(),
                        "com.mygdx.monster.round_1.Hitmonchan").get(0);
            hitmonchan.setMovement(300, hitmonchan.getRotation()+180);
            setVisible(true);
            setMovement(300, getRotation());
        }
        else if(this.getSpeed() > 0)
            if(hitmonchan.overlaps(this))
            {
                hitmonchan.addAction(Actions.rotateBy(-90, 0.5f));
                this.addAction(Actions.rotateBy(-90, 0.5f));
                hitmonchan.addAction(Actions.after(Actions.fadeOut(0.5f)));
                this.addAction(Actions.after(Actions.fadeOut(0.5f)));
                hitmonchan.addAction(Actions.after(Actions.removeActor()));
                this.addAction(Actions.after(Actions.removeActor()));
            }

        applyPhysics(dt);
        boundToWorld();
    }

    public boolean active()
    {
        if(actor == null)
            actor = BaseActor.getList(this.getStage(), "com.mygdx.actor.SuperRabbit").get(0);
        float distance = actor.getX() - this.getX();

        return distance>350;
    }
}
