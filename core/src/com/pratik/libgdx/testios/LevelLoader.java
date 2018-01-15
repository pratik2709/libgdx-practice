package com.pratik.libgdx.testios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.pratik.libgdx.testios.entities.Enemy;
import com.pratik.libgdx.testios.entities.Platform;
import com.pratik.libgdx.testios.util.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.util.Comparator;

public class LevelLoader {

    public static final String TAG = LevelLoader.class.toString();

    public static Level load(String level1, ExtendViewport extendViewport) {
        String pathToLevelFile = Constants.LEVEL_DIR + File.separator +
                level1 + "." +
                Constants.LEVEL_FILE_EXTENSION;

        Level level = new Level(extendViewport);

        FileHandle filehandle = Gdx.files.internal(pathToLevelFile);
        JSONParser jsonParser = new JSONParser();

        try{

            JSONObject rootJsonObject = (JSONObject) jsonParser.parse(filehandle.reader());

            JSONObject composite = (JSONObject) rootJsonObject.get(Constants.LEVEL_COMPOSITE);

            JSONArray platforms = (JSONArray) composite.get(Constants.LEVEL_9PATCHES);
            loadPlatforms(platforms, level);

            JSONArray nonPlatformObjects = (JSONArray) composite.get(Constants.LEVEL_IMAGES);
            loadNonPlatformEntities(nonPlatformObjects, level);

        } catch (Exception ex){
            //log level ??
            Gdx.app.error(TAG, ex.getMessage());
            Gdx.app.error(TAG, Constants.LEVEL_ERROR_MESSAGE);
        }

        return level;
    }

    private static void loadNonPlatformEntities(JSONArray nonPlatformObjects, Level level) {
        for(Object o: nonPlatformObjects){
            JSONObject item = (JSONObject) o;

            final float x = safeGetFloat(item, Constants.LEVEL_X_KEY);
            final float y = safeGetFloat(item, Constants.LEVEL_Y_KEY);

            Vector2 lowerLeftCorner = new Vector2(x,y);

            //check if object is gigagal
            if(item.get(Constants.LEVEL_IMAGENAME_KEY).equals(Constants.STANDING_RIGHT)){
                final Vector2 gigagalPosition = lowerLeftCorner.add(Constants.GIGAGAL_EYE_POSITION);
                level.setGigaGal(gigagalPosition);
            }

            else if(item.get(Constants.LEVEL_IMAGENAME_KEY).equals(Constants.EXIT_PORTAL_SPRITE_1)){
                final Vector2 position = lowerLeftCorner.add(Constants.EXIT_PORTAL_CENTER);
                level.setExitPortal(position);
            }

            else if(item.get(Constants.LEVEL_IMAGENAME_KEY).equals(Constants.POWERUP_SPRITE)){
                final Vector2 position = lowerLeftCorner.add(Constants.POWERUP_CENTER);
                level.setPowerups(position);
            }
        }
    }

    private static void loadPlatforms(JSONArray platforms, Level level) {
        Array<Platform> platformArray = new Array<Platform>();

        for(Object object: platforms){
            final JSONObject platformObject = (JSONObject) object;
            // Also note that if the platform is at (0, 0), the x and y keys will
            // be missing from the JSON
            // Hence the need for the safeGetFloat() method defined above
            final float x = safeGetFloat(platformObject, Constants.LEVEL_X_KEY);
            final float y = safeGetFloat(platformObject, Constants.LEVEL_Y_KEY);
            final float width = ((Number) platformObject.get(Constants.LEVEL_WIDTH_KEY)).floatValue();
            final float height = ((Number) platformObject.get(Constants.LEVEL_HEIGHT_KEY)).floatValue();

            final Platform platform = new Platform(x, y + height, width, height);
            platformArray.add(new Platform(x, y+height, width, height));
            final String identifier = (String)platformObject.get(Constants.LEVEL_IDENTIFIER_KEY);

            if(identifier!=null && identifier.equals(Constants.LEVEL_ENEMY_TAG)){
                final Enemy enemy = new Enemy(platform);
                level.getEnemies().add(enemy);
            }

        }

        //sort platform array
        platformArray.sort(new Comparator<Platform>() {
            @Override
            public int compare(Platform o1, Platform o2) {
                if(o1.top < o2.top){
                    return 1;
                }
                else if(o1.top > o2.top){
                    return -1;
                }
                return 0;
            }
        });
        level.getPlatformArray().addAll(platformArray);

    }

    private static float safeGetFloat(JSONObject platformObject, String levelXKey) {

        //why number?
        Number number = (Number) platformObject.get(levelXKey);

        return (number == null)? 0: number.floatValue();
    }

}