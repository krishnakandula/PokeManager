package com.canvas.krish.pokemanager.pokemondetail;

import com.canvas.krish.pokemanager.data.models.PokemonDetail;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;

/**
 * Created by Krishna Chaitanya Kandula on 12/31/2016.
 */

public interface PokemonDetailContract {
    interface View {
        void showPokemonDetails(PokemonListItem pokemonListItem, PokemonDetail pokemonDetail);
    }

    interface UserActionsListener {
        void getPokemonDetails(PokemonListItem pokemonListItem);
    }
}
