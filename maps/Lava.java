package com.mygdx.maps;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.bases.BaseActor;

public class Lava extends BaseActor
{
    public Lava(float x, float y, Stage s)
    {
        super(x, y, s);
        loadTexture("items/Lava.png");
    }
}
