package com.canvas.krish.pokemanager.pokemonlist;

import android.support.annotation.NonNull;

import com.canvas.krish.pokemanager.data.models.Pokemon;

import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 12/19/2016.
 */

public interface PokemonListContract {

    interface View {

        void showPokemonList(List<Pokemon> pokemonList);

        void showPokemonDetail(String pokemonId);
    }

    interface UserActionsListener {

        void loadPokemon();

        void openPokemonDetails(@NonNull Pokemon requestedPokemon);
    }
}
