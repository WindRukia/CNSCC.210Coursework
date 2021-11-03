package com.mygdx.maps;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.bases.BaseActor;

public class Tip14 extends BaseActor {

    public static float x;
    public static float y;

    public Tip14(float x, float y, Stage s)
    {
        super(x, y, s);
        loadTexture("items/tips.png");

        Action pulse = Actions.sequence(Actions.scaleTo(1.1f ,1.1f, 0.5f),
                Actions.scaleTo(1.0f, 1.0f, 0.5f));
        addAction(Actions.forever(pulse));
    }

}
