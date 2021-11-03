package com.mygdx.tilemap;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.mygdx.bases.BaseActor;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class encapsulates the basic functions and components of the tile map.
 * @author Boning Li
 * @version 1.0
 */
public class TileMapActor extends Actor
{
    // size of the window
    public static int windowWidth = 1200;
    public static int windowHeight = 640;

    private TiledMap tiledMap;  // the tile map file
    private OrthographicCamera tiledCamera; // the camera that projects the objects in the map
    private OrthoCachedTiledMapRenderer tiledMapRenderer;   // object used to render the tile map

    /**
     * Constructor of the tile map.
     * @param fileName  The name of the tile map file.
     * @param theStage The stage storing the tile map object.
     */
    public TileMapActor(String fileName, Stage theStage)
    {
        tiledMap = new TmxMapLoader().load(fileName);   // load the tile map file from the name

        // get properties of the tile map object
        // size for single tile
        int tileWidth = (int) tiledMap.getProperties().get("tilewidth");
        int tileHeight = (int) tiledMap.getProperties().get("tileheight");
        // numbers of tiles in each row and column
        int numTilesHorizontal = (int) tiledMap.getProperties().get("width");
        int numTileVertical = (int) tiledMap.getProperties().get("height");
        // calculate the size of the map
        int mapWidth = tileWidth * numTilesHorizontal;
        int mapHeight = tileHeight * numTileVertical;

        BaseActor.setWorldBounds(mapWidth, mapHeight);  // set the map as the world bound

        // initialise the renderer and the camera of the tile map
        tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
        tiledMapRenderer.setBlending(true);
        tiledCamera = new OrthographicCamera();
        tiledCamera.setToOrtho(false, windowWidth, windowHeight);
        tiledCamera.update();

        theStage.addActor(this);    // add the tile map to the stage
    }

    /**
     * Override the act() method in Actor class.
     * @param dt Delta time.
     */
    @Override
    public void act(float dt) { super.act(dt); }

    /**
     * This method overrides the draw() method in the Actor class.
     * @param batch The batch used to draw the texture.
     * @param parentAlpha Alpha value.
     */
    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        // synchronise the tile camera with the stage camera
        Camera mainCamera = getStage().getCamera();
        tiledCamera.position.x = mainCamera.position.x;
        tiledCamera.position.y = mainCamera.position.y;
        tiledCamera.update();
        tiledMapRenderer.setView(tiledCamera);

        // render the camera
        batch.end();
        tiledMapRenderer.render();
        batch.begin();
    }

    /**
     * This method gets all the rectangles in the tile map specified by the property.
     * @param propertyName The name of the property to be selected.
     * @return The list containing all the target rectangles
     */
    public ArrayList<MapObject> getRectangleList(String propertyName)
    {
        ArrayList<MapObject> list = new ArrayList<>();  // initialise an ArrayList

        // retrieve all the layers and objects to find the target
        for(MapLayer layer : tiledMap.getLayers())
            for(MapObject obj : layer.getObjects())
            {
                // if not a rectangle, continue
                if(!(obj instanceof RectangleMapObject))
                    continue;

                MapProperties props = obj.getProperties();  // get the object's property

                // if its name matches with property, add it to the list
                if(props.containsKey("name") && props.get("name").equals(propertyName))
                    list.add(obj);
            }

        return list;    // return the list
    }

    /**
     * This method gets all the tiles in the tile map specified by the property.
     * @param propertyName The name of the property to be selected.
     * @return  The list containing all the target tiles.
     */
    public ArrayList<MapObject> getTileList(String propertyName)
    {
        ArrayList<MapObject> list = new ArrayList<>();  // initialise an ArrayList

        // retrieve all the layers and objects to find the target
        for(MapLayer layer : tiledMap.getLayers())
            for(MapObject obj : layer.getObjects())
            {
                // if not a tiled map object, continued
                if(!(obj instanceof TiledMapTileMapObject))
                    continue;

                MapProperties props = obj.getProperties();  // get the property of the object

                // get the property of the tile
                TiledMapTileMapObject tmtmo = (TiledMapTileMapObject) obj;
                TiledMapTile t = tmtmo.getTile();
                MapProperties defaultProps = t.getProperties();

                // if its name matches with the property, add it to the list
                if(defaultProps.containsKey("name") && defaultProps.get("name").equals(propertyName))
                    list.add(obj);

                // put the key-value pair into the properties, if it is not in it
                Iterator<String> propertyKeys = defaultProps.getKeys();

                while(propertyKeys.hasNext())
                {
                    String key = propertyKeys.next();

                    if (!props.containsKey(key))
                    {
                        Object value = defaultProps.get(key);
                        props.put(key, value);
                    }
                }
            }

        return list;    // return the list
    }
}
