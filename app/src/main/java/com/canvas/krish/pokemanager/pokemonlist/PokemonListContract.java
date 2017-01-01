package com.canvas.krish.pokemanager.pokemonlist;

import android.support.annotation.NonNull;

import com.canvas.krish.pokemanager.data.models.PokemonDetail;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;

import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 12/19/2016.
 */

public interface PokemonListContract {

    interface View {

        void showPokemonList(List<PokemonListItem> pokemonList);

        void showPokemonDetail(String pokemonId);
    }

    interface UserActionsListener {

        void loadPokemon();

        void openPokemonDetails(@NonNull PokemonListItem pokemonListItem);
    }
}
