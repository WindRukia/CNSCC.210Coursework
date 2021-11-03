package com.mygdx.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.bases.BaseActor;

public class TipActor2 extends BaseActor {



    public TipActor2(float x, float y, Stage s, String file) {
        super(x, y, s);
        loadTexture(file);
        setBoundaryPolygon(8);
        setSize(320, 256);

    }
    public void act(float dt) {
        super.act(dt);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            setVisible(false);
            remove();
        }
    }
}
