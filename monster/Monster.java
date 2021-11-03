package com.mygdx.monster;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.bases.BaseActor;

/**
 *The class represents the monsters in the game.
 * @author Boning Li
 * @version 3.0
 */
public class Monster extends BaseActor
{
    protected float timer = 0; // time that haa elapsed

    /**
     * Constructor of the monster to initialise its coordinates and animation.
     * @param x The x coordinate of the monster.
     * @param y The y coordinate of the monster.
     * @param s The stage storing the monster.
     * @param files Names of files representing the monster.
     */
    public Monster(float x, float y, Stage s, String[] files)
    {
        super(x, y, s);
        loadAnimationFromFiles(files, 0.2f, true);
        setBoundaryPolygon(8);
        setSize(64, 64);
    }

    /**
     * Constructor of the monster to initialise its coordinates and texture.
     * @param x The x coordinate of the monster.
     * @param y The y coordinate of the monster.
     * @param s The stage storing the monster.
     * @param file Name of the file representing the monster.
     */
    public Monster(float x, float y, Stage s, String file)
    {
        super(x, y, s);
        loadTexture(file);
        setBoundaryPolygon(8);
        setSize(64, 64);
    }

    /**
     * The act() method invoked by render().
     * @param dt Delta time.
     */
    public void act(float dt) { super.act(dt); }

    /**
     * Set the motion of the monster.
     * @param speed The moving speed of the monster.
     * @param angle The moving angle of the monster.
     */
    public void setMovement(float speed, float angle)
    {
        setSpeed(speed);
        setMotionAngle(angle);
    }
}
