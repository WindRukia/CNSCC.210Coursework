package com.mygdx.monster.round_1;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the growlithe in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Growlithe extends Monster
{
    private BaseActor actor = null;
    private boolean active = false;

    public Growlithe(float x, float y, Stage s, String file) { super(x, y, s, file); }

    public void act(float dt)
    {
        super.act(dt);

        if(actor == null)
            actor = BaseActor.getList(this.getStage(), "com.mygdx.actor.SuperRabbit").get(0);

        if(!active && actor.getX()>=1600)
        {
            active = true;
            addAction(Actions.moveBy(-384, 0, 2f));
            addAction(Actions.after(Actions.run(() -> {
                setAnimation(loadTexture("monster/round_1/monster_6/6-2.png"));
                setSize(64, 64);
            })));
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
