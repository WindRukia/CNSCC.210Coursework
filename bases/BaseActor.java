package com.mygdx.bases;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * This class encapsulate the basic functions for an actor in this game.
 * @author Boning Li
 * @version 1.0
 */
public class BaseActor extends Group
{
    private Animation<TextureRegion> animation; // animation for the actor
    private float elapsedTime;  // time period that has elapsed
    private boolean animationPaused;    // true if animation is paused
    public Vector2 velocityVec; // a two dimensional vector representing the velocity of the actor
    protected Vector2 accelerationVec;  // a two dimensional vector representing the acceleration of the actor
    private float acceleration; // the magnitude of the acceleration vector
    private float maxSpeed; // the upper bound of the speed
    private float deceleration; // the magnitude of the deceleration of the actor
    private Polygon boundaryPolygon;    // the boundary of the actor
    private static Rectangle worldBounds;   // the boundary of the game
    protected String start = "Start";

    /**
     * Constructor for the BaseActor.
     * @param x The x coordinate of the actor.
     * @param y The y coordinate of the actor.
     * @param s The stage storing the actor.
     */
    public BaseActor(float x, float y, Stage s)
    {
        // initialise the actor, set its position and then add it to the given stage
        super();
        setPosition(x, y);
        s.addActor(this);

        // initialise all the attributes
        animation = null;
        elapsedTime = 0;
        animationPaused = false;
        velocityVec = new Vector2(0, 0);
        accelerationVec = new Vector2(0, 0);
        acceleration = 0;
        maxSpeed = 1000;
        deceleration = 0;
    }

    /**
     * This static method return an ArrayList that holds all the objects of the type specified by className
     * @param stage The stage storing the actors.
     * @param className The type name of the objects to be returned.
     * @return An ArrayList holding all the objects of the type specified by className
     */
    public static ArrayList<BaseActor> getList(Stage stage, String className)
    {
        ArrayList<BaseActor> list = new ArrayList<>();  // create a new ArrayList object

        // get the class of the actor
        Class<?> theClass = null;
        try { theClass = Class.forName(className); }
        catch(Exception error) { error.printStackTrace(); }

        // select all the actors with the given type from the stage
        for(Actor actor : stage.getActors())
        {
            assert theClass != null;
            if(theClass.isInstance(actor))
                list.add((BaseActor)actor);
        }

        return list;    // return the list
    }

    /**
     * This static method returns the number of objects of the type specified by className.
     * @param stage The stage storing the actors.
     * @param className The type name of the objects to be returned.
     * @return The number of objects of the type specified by className.
     */
    public static int count(Stage stage, String className) { return getList(stage, className).size(); }

    /**
     * This static method sets a bound for all the actors.
     * @param width The width of the bound.
     * @param height The height of the bound.
     */
    public static void setWorldBounds(float width, float height)
    {
        worldBounds = new Rectangle(0, 0, width, height);   // create the game boundary
    }

    /**
     * This static method returns the boundary of the game.
     * @return The boundary rectangle of the world.
     */
    public static Rectangle getWorldBounds() { return worldBounds; }

    /**
     * This static method initialise the world bound with a given actor.
     * @param ba The actor representing the boundary.
     */
    public static void setWorldBounds(BaseActor ba) { setWorldBounds(ba.getWidth(), ba.getHeight()); }

    /**
     * This method specifies the animation of the actor.
     * @param animation The animation of the actor.
     */
    public void setAnimation(Animation<TextureRegion> animation)
    {
        // change the animation to the given one
        this.animation = animation;

        // set the size and location of the actor
        TextureRegion tr = this.animation.getKeyFrame(0);
        float width = tr.getRegionWidth();
        float height = tr.getRegionHeight();
        setSize(width, height);
        setOrigin(width/2, height/2);

        if(boundaryPolygon == null)
            setBoundaryRectangle();
    }

    /**
     * This method is used to pause the animation.
     * @param pause Animation paused if true.
     */
    public void setAnimationPaused(boolean pause) { animationPaused = pause; }

    /**
     * This method sets the moving speed of the actor.
     * @param speed The speed of the actor.
     */
    public void setSpeed(float speed)
    {
        if(velocityVec.len() == 0)
            velocityVec.set(speed, 0);
        else
            velocityVec.setLength(speed);
    }

    /**
     * This method returns the speed of the actor.
     * @return The speed of the actor.
     */
    public float getSpeed() { return velocityVec.len(); }

    /**
     * This method sets the angle toward which the actor faces.
     * @param angle The angle to which the actor faces.
     */
    public void setMotionAngle(float angle) { velocityVec.setAngleDeg(angle); }

    /**
     * This method returns the moving angle of the actor.
     * @return The moving angle of the actor.
     */
    public float getMotionAngle() { return velocityVec.angleDeg(); }

    /**
     * This method specifies the actions to take in each render.
     * @param dt Delta time.
     */
    @Override
    public void act(float dt)
    {
        super.act(dt);  // invoke act() method in Group class

        // add dt to elapsedTime if animation continues
        if(!animationPaused)
            elapsedTime += dt;
    }

    /**
     * This method set the magnitude of the acceleration.
     * @param acceleration The magnitude of the acceleration.
     */
    public void setAcceleration(float acceleration) { this.acceleration = acceleration; }

    /**
     * This method sets the direction of the acceleration.
     * @param angle The direction of the acceleration.
     */
    public void accelerateAtAngle(float angle)
    {
        accelerationVec.add(new Vector2(acceleration, 0).setAngleDeg(angle));
    }

    /**
     * This method makes the actor accelerate at the direction in which it faces.
     */
    public void accelerateForward() { accelerateAtAngle(getRotation()); }

    /**
     * This method restricts the upper bound of the actor's speed.
     * @param maxSpeed The upper bound of the actor's speed.
     */
    public void setMaxSpeed(float maxSpeed) { this.maxSpeed = maxSpeed; }

    /**
     * This method sets the magnitude of the deceleration of the actor.
     * @param deceleration The magnitude of the deceleration of the actor.
     */
    public void setDeceleration(float deceleration) { this.deceleration = deceleration; }

    /**
     * This method locates the centre of the actor at the given place.
     * @param x The x coordinate of the centre of the actor.
     * @param y The y coordinate of the centre of the actor.
     */
    public void centerAtPosition(float x, float y) { setPosition(x-getWidth()/2, y-getHeight()/2); }

    /**
     * This method makes this actor centred at another actor.
     * @param other The actor at which this actor will be centred.
     */
    public void centerAtActor(BaseActor other)
    {
        centerAtPosition(other.getX()+other.getWidth()/2, other.getY()+other.getHeight()/2);
    }

    /**
     * This method applies the practical physics for the actor.
     * @param dt Delta time.
     */
    public void applyPhysics(float dt)
    {
        // accelerate the actor
        velocityVec.add(accelerationVec.x*dt, accelerationVec.y*dt);

        // recalculate the speed of the actor
        float speed = getSpeed();
        if(accelerationVec.len() == 0)
            speed -= deceleration * dt;
        speed = MathUtils.clamp(speed, 0, maxSpeed);    // limit the speed in the interval
        setSpeed(speed);

        moveBy(velocityVec.x *dt, velocityVec.y*dt);    // move the actor

        accelerationVec.set(0, 0);  // set the acceleration vector to (0, 0)
    }

    /**
     * This method limits the actor inside the game world.
     */
    public void boundToWorld()
    {
        // limit the actor horizontally
        if(getX() < 0)
            setX(0);
        if(getX()+getWidth() > worldBounds.width)
            setX(worldBounds.width - getWidth());
        // limit the actor vertically
        if(getY() < 0)
            setY(0);
        if(getY()+getHeight() > worldBounds.height)
            setY(worldBounds.height - getHeight());
    }

    /**
     * This method overrides the draw() method in the Group class.
     * @param batch The batch used to draw the actor.
     * @param parentAlpha The alpha value.
     */
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);

        // draw the animation
        if(animation!=null && isVisible())
            batch.draw(animation.getKeyFrame(elapsedTime), getX(), getY(), getOriginX(), getOriginY(), getWidth(),
                    getHeight(), getScaleX(), getScaleY(), getRotation());

        super.draw(batch, parentAlpha); // invoke draw() in the Group class
    }

    /**
     * This method located the camera of the stage centred at this actor.
     */
    public void alignCamera()
    {
        // get the camera and view port from the stage
        Camera camera = this.getStage().getCamera();
        Viewport viewport = this.getStage().getViewport();

        // make the camera centred at the actor and keep it in the boundary of the game
        camera.position.set(this.getX()+this.getOriginX(), this.getY()+this.getOriginY(), 0);
        camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth/2,
                worldBounds.width - camera.viewportWidth/2);
        camera.position.y = MathUtils.clamp(camera.position.y, camera.viewportHeight/2,
                worldBounds.height - camera.viewportHeight/2);
        camera.update();    // update the camera view
    }

    /**
     * This method loads the animation from a series of files.
     * @param fileNames A String array storing all the files of the animation.
     * @param frameDuration The time interval for one frame.
     * @param loop True if the animation is played forever.
     * @return The animation created from the given files.
     */
    public Animation<TextureRegion> loadAnimationFromFiles(String[] fileNames, float frameDuration, boolean loop)
    {
        Array<TextureRegion> textureArray = new Array<>(); //initialise an ArrayList to hold the animation

        // for each file, create a texture and add it to the ArrayList object
        for (String fileName : fileNames)
        {
            Texture texture = new Texture(fileName);
            texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
            textureArray.add(new TextureRegion(texture));
        }

        // create an animation object with the duration and texture array
        Animation<TextureRegion> animation = new Animation<>(frameDuration, textureArray);

        // set whether the animation will be played forever
        if(loop)
            animation.setPlayMode(PlayMode.LOOP);
        else
            animation.setPlayMode(PlayMode.NORMAL);

        if(this.animation == null)
            setAnimation(animation);

        return animation;   // return the animation
    }

    /**
     * This method loads the animation from a sheet which holds many pictures.
     * @param fileName The file name of the sheet.
     * @param rows The number of rows of the sheet.
     * @param columns The number of columns of the sheet.
     * @param frameDuration The time interval for one frame.
     * @param loop True if the animation is played forever.
     * @return The animation created from the sheet.
     */
    public Animation<TextureRegion> loadAnimationFromSheet
            (String fileName, int rows, int columns, float frameDuration, boolean loop)
    {
        // load the texture from the file and compute the size of each picture
        Texture texture = new Texture(fileName);
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        int frameWidth = texture.getWidth() / columns;  // width of single picture
        int frameHeight = texture.getHeight() / rows;   // height of single picture

        // separate the texture into multi frames
        TextureRegion[][] temp = TextureRegion.split(texture, frameWidth, frameHeight);

        Array<TextureRegion> textureArray = new Array<>();  // the array to hold the textures

        // add each frame to the texture array
        for(int r=0;r<rows;r++)
            for(int c=0;c<columns;c++)
                textureArray.add(temp[r][c]);

        Animation<TextureRegion> animation = new Animation<>(frameDuration, textureArray);  // animation

        // set the play mode of the animation, played forever if loop
        if(loop)
            animation.setPlayMode(PlayMode.LOOP);
        else
            animation.setPlayMode(PlayMode.NORMAL);

        if(this.animation == null)
            setAnimation(animation);

        return animation;   // return the animation
    }

    /**
     * This method loads single picture as the animation for the actor.
     * @param fileName The file name of the animation.
     * @return The animation created from the file.
     */
    public Animation<TextureRegion> loadTexture(String fileName)
    {
        // invoke loadAnimationFromFiles() method with a string array of length 1 to create the animation
        String[] fileNames = new String[1];
        fileNames[0] = fileName;
        return loadAnimationFromFiles(fileNames, 1, true);
    }

    /**
     * This method justifies whether the animation of the actor has finished.
     * @return True if the animation is finished.
     */
    public boolean isAnimationFinished() { return animation.isAnimationFinished(elapsedTime); }

    /**
     * This method justifies whether the actor is moving currently.
     * @return True if the actor is moving.
     */
    public boolean isMoving() { return getSpeed()>0; }

    /**
     * This method sets the opacity of the actor.
     * @param opacity The opacity of the actor.
     */
    public void setOpacity(float opacity) { this.getColor().a = opacity; }

    /**
     * This method sets the boundary rectangle of the actor.
     */
    public void setBoundaryRectangle()
    {
        // get the basic attributes of the rectangle and create the polygon with given vertices
        float w = getWidth();
        float h = getHeight();
        float[] vertices = {0,0, w,0, w,h, 0,h};
        boundaryPolygon = new Polygon(vertices);
    }

    /**
     * This method creates the boundary polygon of the actor according to the given number of sides
     * @param numSides The numbers of the sides of the polygon
     */
    public void setBoundaryPolygon(int numSides)
    {
        // set the size of the polygon
        float width = getWidth();
        float height = getHeight();

        // create the polygon with the given number of sides
        float[] vertices = new float[numSides*2];
        for(int i=0;i<numSides;i++)
        {
            float angle = i*6.28f/numSides;
            vertices[2*i] = width/2 * MathUtils.cos(angle) + width/2;
            vertices[2*i+1] = height/2 * MathUtils.sin(angle) + height;
        }
        boundaryPolygon = new Polygon(vertices);
    }

    /**
     * This method returns the boundary of the actor.
     * @return The boundary of the actor.
     */
    public Polygon getBoundaryPolygon()
    {
        // set the basic attributes of the boundary
        boundaryPolygon.setPosition(getX(), getY());
        boundaryPolygon.setOrigin(getOriginX(), getOriginY());
        boundaryPolygon.setRotation(getRotation());
        boundaryPolygon.setScale(getScaleX(), getScaleY());
        return boundaryPolygon; // return the boundary
    }

    /**
     * This method wraps the actor around the world.
     */
    public void wrapAroundWorld()
    {
        // wrap the actor horizontally
        if(getX()+getWidth() < 0)
            setX(worldBounds.width);
        if(getX() > worldBounds.width)
            setX(-getWidth());
        // warp the actor vertically
        if(getY()+getHeight() < 0)
            setY(worldBounds.height);
        if(getY() > worldBounds.height)
            setY(-getHeight());
    }

    /**
     * This method justifies whether two actors overlap.
     * @param other The actor needs to be justified.
     * @return True if two actors overlap.
     */
    public boolean overlaps(BaseActor other)
    {
        // get two boundaries of two actors
        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        // return false if two rectangles do not overlap
        if(!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
            return false;
        return Intersector.overlapConvexPolygons(poly1, poly2); // true if two polygons overlap
    }

    /**
     * This method whether the distance between two actors is less than a given value.
     * @param distance The distance between two actors.
     * @param other Another actor used to calculate the distance.
     * @return True if the distance is less than the given value.
     */
    public boolean isWithinDistance(float distance, BaseActor other)
    {
        // get the boundary of this actor and enlarge or shrink it to the size specified by the distance
        Polygon poly1 = this.getBoundaryPolygon();
        float scaleX = (this.getWidth() + 2*distance) / this.getWidth();
        float scaleY = (this.getHeight() + 2*distance) / this.getHeight();
        poly1.setScale(scaleX, scaleY);

        Polygon poly2 = other.getBoundaryPolygon(); // get the boundary of another actor

        // if two rectangles do not overlap, return false
        if(!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
            return false;

        return Intersector.overlapConvexPolygons(poly1, poly2); // true if two polygons overlap
    }

    /**
     * This method prevents the actor from overlapping with another actor.
     * @param other The actor to be prevented from overlapping.
     * @return A vector used to translate the actor.
     */
    public Vector2 preventOverlap(BaseActor other)
    {
        // get the boundaries of the two actors
        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        // if their boundary rectangles do not overlap, then return null
        if(!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
            return null;

        // if rectangles overlap, justify whether the polygons overlap
        MinimumTranslationVector mtv = new MinimumTranslationVector();
        boolean polygonOverlap = Intersector.overlapConvexPolygons(poly1, poly2, mtv);

        // if not overlapped, return null
        if(!polygonOverlap)
            return null;

        // move the actor and return the translation vector
        this.moveBy(mtv.normal.x*mtv.depth, mtv.normal.y*mtv.depth);
        return mtv.normal;
    }
}
