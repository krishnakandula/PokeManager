package com.canvas.krish.pokemanager.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.canvas.krish.pokemanager.data.models.PokemonDetail;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;

import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 12/20/2016.
 */

public interface PokemonRepository {

    /**
     * Callback for retrieving all Pokemon in a repository
     */
    interface LoadPokemonCallback {
        void onPokemonLoaded(List<PokemonListItem> pokemonList);
    }

    /**
     * Callback for retrieving a single Pokemon in a repository
     */
    interface GetPokemonCallback {
        void onPokemonLoaded(PokemonDetail pokemon);
    }

    void getPokemonList(Context context, @NonNull LoadPokemonCallback callback);

    void getPokemon(@NonNull int pokemonId, @NonNull GetPokemonCallback callback);

    void refreshData();
}
