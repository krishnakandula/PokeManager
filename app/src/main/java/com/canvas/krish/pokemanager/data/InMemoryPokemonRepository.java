package com.canvas.krish.pokemanager.data;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.canvas.krish.pokemanager.Config;
import com.canvas.krish.pokemanager.data.models.PokemonDetail;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;

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

    List<PokemonListItem> mCachedPokemon;
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
                            PokemonListItem p = new PokemonListItem();
                            JSONObject jsonObject = pokemon.getJSONObject(i);

                            p.setId(jsonObject.getInt("_id"));
                            p.setName(capitalizeFirstLetter(jsonObject.getString("_name")));
                            p.setType1(capitalizeFirstLetter(jsonObject.getString("_type1")));
                            if(jsonObject.has("_type2"))
                                p.setType2(capitalizeFirstLetter(jsonObject.getString("_type2")));

                            p.setDescription(jsonObject.getString("_description"));
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
    public void getPokemon(@NonNull int pokemonId, @NonNull final GetPokemonCallback callback) {
        //TODO: Write InMemoryPokemonRepository.getPokemon()
        //Check if mCachedPokemon.get(pokemonId) is null
        //If not, callback.onPokemonLoaded(mCachedPokemon.get(pokemonId).getPokemonDetail())
        //If null, pokemonServiceApi.get(pokemonId, new PokemonServiceCallback...), then parse JsonObject
        mPokemonServiceApi.getPokemon(pokemonId, new PokemonServiceApi.PokemonServiceCallback<PokemonDetail>() {
            @Override
            public void onLoaded(PokemonDetail pokemon) {
                callback.onPokemonLoaded(pokemon);
            }
        });
    }

    @Override
    public void getArtworkUri(@NonNull int pokemonId, @NonNull final GetArtworkUriCallback callback) {
        Uri uri = Uri.parse(String.format(Config.BASE_IMAGE_URL, pokemonId));
        callback.onArtworkUriLoaded(uri);
    }

    @Override
    public void refreshData() {

    }

    @NonNull
    public String capitalizeFirstLetter(String str){
        if(!str.isEmpty()) {
            StringBuilder builder = new StringBuilder(str.substring(0, 1).toUpperCase());
            if (str.length() >= 1)
                builder.append(str.substring(1));

            return builder.toString();
        }

        return str;
    }
}
