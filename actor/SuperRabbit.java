package com.mygdx.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.bases.BaseActor;
import com.mygdx.maps.Solid;

public class SuperRabbit extends BaseActor
{
    private Animation<TextureRegion> stand;
    private Animation<TextureRegion> walk;
    private Animation<TextureRegion> jump;
    private Animation<TextureRegion> squat;
    private Animation<TextureRegion> fly;
    private Animation<TextureRegion> glide;
    private float walkAcceleration;
    private float walkDeceleration;
    private float maxHorizontalSpeed;
    private float gravity;
    private float maxVerticalSpeed;
    private float jumpSpeed;
    private BaseActor belowSensor;
    private float power;

    public SuperRabbit(float x, float y, Stage s)
    {
        super(x, y, s);

        stand = loadTexture("character/stand.png");

        String[] walkFileNames = {"character/walk_1.png", "character/walk_2.png"};
        walk = loadAnimationFromFiles(walkFileNames, 0.2f, true);

        squat = loadTexture("character/squat.png");

        fly = loadTexture("character/fly.png");


        glide = loadTexture("character/glide.png");

        maxHorizontalSpeed = 150;
        walkAcceleration = 200;
        walkDeceleration = 200;
        gravity = 700;
        maxVerticalSpeed = 1000;

        setBoundaryPolygon(8);

        jump = loadTexture("character/jump.png");
        jumpSpeed = 450;

        belowSensor = new BaseActor(0, 0, s);
        belowSensor.loadTexture("white.png");
        belowSensor.setSize(this.getWidth()-8, 8);
        belowSensor.setBoundaryRectangle();
        belowSensor.setVisible(false);
        power = 100;

    }

    public void act(float dt)
    {
        super.act(dt);

        if(Gdx.input.isKeyPressed(Keys.LEFT))
            accelerationVec.add(-walkAcceleration, 0);
        if(Gdx.input.isKeyPressed(Keys.RIGHT))
            accelerationVec.add(walkAcceleration, 0);
        if (Gdx.input.isKeyPressed(Keys.F) && power > 0) {
            accelerationVec.add(0, 500);
            power -= 33.3 * dt;
        }


        if(!Gdx.input.isKeyPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.LEFT))
        {
            float decelerationAmount = walkDeceleration*dt;

            float walkDirection;
            if(velocityVec.x > 0)
                walkDirection = 1;
            else
                walkDirection = -1;

            float walkSpeed = Math.abs(velocityVec.x);

            walkSpeed -= decelerationAmount;

            if(walkSpeed < 0)
                walkSpeed = 0;

            velocityVec.x = walkSpeed * walkDirection;
        }

        accelerationVec.add(0, -gravity);

        velocityVec.add(accelerationVec.x*dt, accelerationVec.y*dt);

        velocityVec.x = MathUtils.clamp(velocityVec.x, -maxHorizontalSpeed, maxHorizontalSpeed);
        velocityVec.y = MathUtils.clamp(velocityVec.y, -maxVerticalSpeed, maxVerticalSpeed);

        if (Gdx.input.isKeyPressed(Keys.G) && power > 0) {
            if (velocityVec.x > 0) velocityVec.x = 300;
            else if (velocityVec.x < 0) velocityVec.x = -300;
            power -= 33.3 * dt;
        }

        moveBy(velocityVec.x*dt, velocityVec.y*dt);
        accelerationVec.set(0, 0);
        belowSensor.setPosition(getX()+4, getY()-8);


        if(this.isOnSolid())
        {


            if (velocityVec.x == 0) setAnimation(stand);
            else if (Gdx.input.isKeyPressed(Keys.G) && power > 0) setAnimation(glide);
            else setAnimation(walk);

            if (Gdx.input.isKeyPressed(Keys.DOWN)) setAnimation(squat);

        }
        else {
            if (Gdx.input.isKeyPressed(Keys.F) && power > 0) {
                setAnimation(fly);
            }
            else setAnimation(jump);
        }

        if(velocityVec.x > 0)
            setScaleX(1);
        if(velocityVec.x < 0)
            setScaleX(-1);

        alignCamera();
        boundToWorld();
    }

    public boolean belowOverlaps(BaseActor actor) { return belowSensor.overlaps(actor); }

    public boolean isOnSolid()
    {
        for(BaseActor actor : BaseActor.getList(getStage(), "com.mygdx.maps.Solid"))
        {
            Solid solid = (Solid) actor;
            if(belowOverlaps(solid) && solid.isEnabled())
                return true;
        }

        return false;
    }

    public void jump() {velocityVec.y = jumpSpeed; }

    public void scramble() {
        velocityVec.x = 0;
        velocityVec.y = 100;
    }

    public void swim() {
        velocityVec.y = 250;
        gravity = 200;
    }

    public boolean isFalling() { return velocityVec.y < 0; }

    public void spring() { velocityVec.y = 1.5f * jumpSpeed; }

    public boolean isJumping() { return velocityVec.y > 0; }

    public void setInfinitePower() { power = Integer.MAX_VALUE; }

    public void addPower() {
        power += 80;
        if (power > 100) power = 100;
    }
    public float getPower() { return power; }
}
