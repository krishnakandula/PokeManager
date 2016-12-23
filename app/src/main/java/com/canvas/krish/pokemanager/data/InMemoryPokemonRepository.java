package com.canvas.krish.pokemanager.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.canvas.krish.pokemanager.data.models.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 12/20/2016.
 */

public class InMemoryPokemonRepository implements PokemonRepository {

    List<Pokemon> mCachedPokemon;
    private PokemonServiceApi mPokemonServiceApi;

    protected InMemoryPokemonRepository(PokemonServiceApi pokemonServiceApi){
        this.mPokemonServiceApi = pokemonServiceApi;
    }

    @Override
    public void getPokemonList(@NonNull final LoadPokemonCallback callback) {
        if(mCachedPokemon == null || mCachedPokemon.isEmpty()){
            mCachedPokemon = new ArrayList<>();
            mPokemonServiceApi.getPokemonList(0, 810, new PokemonServiceApi.PokemonServiceCallback<List<Pokemon>>() {
                @Override
                public void onLoaded(List<Pokemon> pokemon) {
                    mCachedPokemon = pokemon;
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
