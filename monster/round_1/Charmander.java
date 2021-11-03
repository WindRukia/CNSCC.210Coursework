package com.mygdx.monster.round_1;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.monster.Monster;

/**
 * This class represents the Charmander in the game.
 * @author Boning Li
 * @version 1.0
 */
public class Charmander extends Monster
{
    public Charmander(float x, float y, Stage s, String[] files) { super(x, y, s, files); }

    public void act(float dt)
    {
        super.act(dt);
        timer += dt;

        if(timer >= 3)
        {
            openFire();
            timer = 0;
        }

        applyPhysics(dt);
        boundToWorld();
    }

    public void openFire()
    {
        if(getStage() == null)
            return;

        CharmanderFire fire = new CharmanderFire(0, 0, this.getStage());
        fire.centerAtActor(this);
        fire.setRotation(this.getRotation());
        fire.setMotionAngle((this.getRotation()+180) % 360);
    }
}
