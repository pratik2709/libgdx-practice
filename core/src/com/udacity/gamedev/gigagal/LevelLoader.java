package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.udacity.gamedev.gigagal.util.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;

public class LevelLoader {

    public static final String TAG = LevelLoader.class.toString();

    public static Level load(String level1, ExtendViewport extendViewport) {
        String pathToLevelFile = Constants.LEVEL_DIR + File.separator +
                "Level1" + "." +
                Constants.LEVEL_FILE_EXTENSION;
        Level level = new Level(extendViewport);
        try{
            //level file handle object
            FileHandle filehandle = Gdx.files.internal(pathToLevelFile);
            JSONParser jsonParser = new JSONParser();

//            //root json object
            JSONObject rootJsonObject = (JSONObject) jsonParser.parse(filehandle.reader());

            //keys in json object
            Gdx.app.log(TAG, rootJsonObject.keySet().toString());

            //composite object
            JSONObject composite = (JSONObject) rootJsonObject.get(Constants.LEVEL_COMPOSITE);
            Gdx.app.log(TAG, composite.keySet().toString());

            //jsonarray level9patches
            JSONArray jsonArray = (JSONArray) composite.get(Constants.LEVEL_9PATCHES);
            JSONObject first_platform = (JSONObject)  jsonArray.get(0);

            Gdx.app.log(TAG, first_platform.keySet().toString());



        } catch (Exception ex){
            //log level ??
            Gdx.app.error(TAG, ex.getMessage());
            Gdx.app.error(TAG, Constants.LEVEL_ERROR_MESSAGE);
        }

        return level;
    }
}