package com.canvas.krish.pokemanager.data;

import android.content.Context;
import android.net.Uri;
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
        void onPokemonLoaded(PokemonDetail pokemonDetail, PokemonListItem pokemonListItem);
    }

    interface GetArtworkUriCallback {
        void onArtworkUriLoaded(Uri uri);
    }

    void getPokemonList(Context context, @NonNull LoadPokemonCallback callback);

    void getPokemon(@NonNull int pokemonId, @NonNull GetPokemonCallback callback);

    void getArtworkUri(@NonNull int pokemonId, @NonNull GetArtworkUriCallback callback);

    void refreshData();
}
