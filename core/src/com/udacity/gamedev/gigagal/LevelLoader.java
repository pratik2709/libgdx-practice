package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.udacity.gamedev.gigagal.entities.Enemy;
import com.udacity.gamedev.gigagal.entities.Platform;
import com.udacity.gamedev.gigagal.util.Constants;
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

//        DelayedRemovalArray<Enemy> enemies = level.getEnemies();
//        enemies.add(new Enemy(new Platform(10,10,10,10)));
//        Gdx.app.log(TAG, level.getEnemies().toString());

        FileHandle filehandle = Gdx.files.internal(pathToLevelFile);
        JSONParser jsonParser = new JSONParser();

        try{

            JSONObject rootJsonObject = (JSONObject) jsonParser.parse(filehandle.reader());

            JSONObject composite = (JSONObject) rootJsonObject.get(Constants.LEVEL_COMPOSITE);

            JSONArray platforms = (JSONArray) composite.get(Constants.LEVEL_9PATCHES);
            loadPlatforms(platforms, level);

        } catch (Exception ex){
            //log level ??
            Gdx.app.error(TAG, ex.getMessage());
            Gdx.app.error(TAG, Constants.LEVEL_ERROR_MESSAGE);
        }

        return level;
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