package com.canvas.krish.pokemanager.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.canvas.krish.pokemanager.data.models.Pokemon;

import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 12/20/2016.
 */

public interface PokemonRepository {

    /**
     * Callback for retrieving all Pokemon in a repository
     */
    interface LoadPokemonCallback {
        void onPokemonLoaded(List<Pokemon> pokemonList);
    }

    /**
     * Callback for retrieving a single Pokemon in a repository
     */
    interface GetPokemonCallback {
        void onPokemonLoaded(Pokemon pokemon);
    }

    void getPokemonList(Context context, @NonNull LoadPokemonCallback callback);

    void getPokemon(@NonNull int pokemonId, @NonNull GetPokemonCallback callback);

    void savePokemon(@NonNull Pokemon pokemon);

    void refreshData();
}
