package com.canvas.krish.pokemanager.data;

import android.content.Context;

import com.canvas.krish.pokemanager.data.models.PokemonDetail;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Krishna Chaitanya Kandula on 12/22/2016.
 */

interface PokemonServiceApi {

    interface PokemonServiceCallback<T> {
        void onLoaded(T pokemon);
    }

    void getPokemonList(int minId, int maxId, Context context, PokemonServiceCallback<JSONArray> callback);

    void getPokemon(int id, PokemonServiceCallback<PokemonDetail> callback);

    void getPokemon(String name, PokemonServiceCallback<PokemonDetail> callback);
}
