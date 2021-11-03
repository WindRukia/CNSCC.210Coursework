package com.mygdx.monster.round_1;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.bases.BaseActor;
import com.mygdx.monster.Monster;

/**
 * This class represents the rapidash in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Rapidash extends Monster
{
    private BaseActor actor = null;
    private boolean active = false;

    public Rapidash(float x, float y, Stage s, String file) { super(x, y, s, file); }

    public void act(float dt)
    {
        super.act(dt);

        if(actor == null)
            actor = BaseActor.getList(this.getStage(), "com.mygdx.actor.SuperRabbit").get(0);

        if(!active && actor.getX()>=1920)
        {
            active = true;
            addAction(Actions.run(() -> {
                setAnimation(loadTexture("monster/round_1/monster_9/9-2.png"));
                setSize(64, 64);
                setScaleX(getScaleX()*(-1));
            }));
            addAction(Actions.after(Actions.moveBy(400, 0, 2f)));
            addAction(Actions.after(Actions.run(() -> setScaleX(getScaleX()*(-1)))));
            addAction(Actions.after(Actions.moveBy(-400, 0, 2f)));
            addAction(Actions.after(Actions.run(() -> setScaleX(getScaleX()*(-1)))));
            addAction(Actions.after(Actions.moveBy(400, 0, 2f)));
            addAction(Actions.after(Actions.run(() -> setScaleX(getScaleX()*(-1)))));
            addAction(Actions.after(Actions.moveBy(-400, 0, 2f)));
            addAction(Actions.after(Actions.moveBy(-320, -128, 0.5f)));
            addAction(Actions.after(Actions.fadeOut(0.5f)));
            addAction(Actions.after(Actions.removeActor()));
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
