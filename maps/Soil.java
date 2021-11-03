package com.mygdx.maps;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Soil extends Solid
{
    public Soil(float x, float y, Stage s)
    {
        super(x, y, 32, 16, s);
        loadTexture("items/soil.png");
    }
}
