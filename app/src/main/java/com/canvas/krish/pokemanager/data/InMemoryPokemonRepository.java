package com.canvas.krish.pokemanager.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.canvas.krish.pokemanager.data.models.Pokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 12/20/2016.
 */

public class InMemoryPokemonRepository implements PokemonRepository {
    private static final String LOG_TAG = InMemoryPokemonRepository.class.getSimpleName();

    List<Pokemon> mCachedPokemon;
    private PokemonServiceApi mPokemonServiceApi;

    protected InMemoryPokemonRepository(PokemonServiceApi pokemonServiceApi){
        this.mPokemonServiceApi = pokemonServiceApi;
    }

    @Override
    public void getPokemonList(Context context, @NonNull final LoadPokemonCallback callback) {
        if(mCachedPokemon == null || mCachedPokemon.isEmpty()){
            mCachedPokemon = new ArrayList<>();
            mPokemonServiceApi.getPokemonList(0, 810, context, new PokemonServiceApi.PokemonServiceCallback<JSONArray>() {
                @Override
                public void onLoaded(JSONArray pokemon) {
                    try {
                        //Iterate through JSONArray, creating pokemon objects and storing
                        //them into cachedData
                        for (int i = 0; i < pokemon.length(); i++) {
                            Pokemon p = new Pokemon();
                            JSONObject jsonObject = pokemon.getJSONObject(i);

                            p.setId(jsonObject.getInt("_id"));
                            p.setName(jsonObject.getString("_name"));
                            p.setType1(jsonObject.getString("_type1"));
                            if(jsonObject.has("_type2"))
                                p.setType2(jsonObject.getString("_type2"));

                            mCachedPokemon.add(p);
                        }
                    } catch (JSONException e){
                        Log.e(LOG_TAG, e.getMessage());
                    }
                    callback.onPokemonLoaded(mCachedPokemon);
                }
            });
        } else{
            callback.onPokemonLoaded(mCachedPokemon);
        }
    }

    @Override
    public void getPokemon(@NonNull int pokemonId, @NonNull GetPokemonCallback callback) {
        if(mCachedPokemon != null){
            callback.onPokemonLoaded(mCachedPokemon.get(0));
        }
    }

    @Override
    public void savePokemon(@NonNull Pokemon pokemon) {

    }

    @Override
    public void refreshData() {

    }
}
