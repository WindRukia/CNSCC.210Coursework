package com.mygdx.monster.round_2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.monster.Monster;

/**
 * This class represents the psyduck in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Psyduck extends Monster
{
    private int counter = 0;
    private boolean started = false;

    public Psyduck(float x, float y, Stage s, String[] files) { super(x, y, s, files); }

    public void act(float dt)
    {
        super.act(dt);

        if(getSpeed()==0 && counter==0)
            setMovement(100, getRotation()+180);
        else
        {
            timer += dt;
            if(timer>=2 && counter<=4)
            {
                setScaleX(getScaleX()*(-1));
                setMotionAngle((getMotionAngle()+180) % 360);
                counter++;
                timer = 0;
            }
            else if(counter>4 && !started)
            {
                started = true;
                setSpeed(0);
                addAction(Actions.moveBy(-100, -150, 0.5f));
                addAction(Actions.after(Actions.fadeOut(0.5f)));
                addAction(Actions.after(Actions.removeActor()));
            }
        }

        applyPhysics(dt);
        boundToWorld();
    }
}
