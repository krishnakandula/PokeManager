package com.canvas.krish.pokemanager.data;

import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 12/22/2016.
 */

interface PokemonServiceApi {

    interface PokemonServiceCallback<T> {
        void onLoaded(T pokemon);
    }

    void getPokemonList(int minId, int maxId, PokemonServiceCallback<List<Pokemon>> callback);

    void getPokemon(int id, PokemonServiceCallback<Pokemon> callback);
}
