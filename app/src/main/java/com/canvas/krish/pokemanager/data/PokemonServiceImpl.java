package com.canvas.krish.pokemanager.data;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Krishna Chaitanya Kandula on 12/22/2016.
 */

class PokemonServiceImpl implements PokemonServiceApi {
    private static final String LOG_TAG = PokemonServiceImpl.class.getSimpleName();
    private static final String BASE_URL = "http://pokeapi.co";
    private static final String INTITAL_DATA_PATH = "initial_data.json";

    @Override
    public void getPokemonList(int minId, int maxId, Context context, final PokemonServiceCallback<JSONArray> callback) {
        try {
            InputStream is = context.getAssets().open(INTITAL_DATA_PATH);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonData = new String(buffer, "UTF-8");
            JSONArray initialDataArray = new JSONArray(jsonData);
            callback.onLoaded(initialDataArray);
        } catch (IOException e){
            Log.e(LOG_TAG, e.getMessage());
        } catch (JSONException e){
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    @Override
    public void getPokemon(int id, PokemonServiceCallback<JSONObject> callback) {
        //TODO: Write PokemonServiceImpl.getPokemon()
    }
}
