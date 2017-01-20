package com.canvas.krish.pokemanager.pokemondetail;

import com.canvas.krish.pokemanager.BasePresenter;
import com.canvas.krish.pokemanager.BaseView;
import com.canvas.krish.pokemanager.data.models.PokemonDetail;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;

import dagger.Component;

/**
 * Created by Krishna Chaitanya Kandula on 12/31/2016.
 */

public interface PokemonDetailContract {
    interface View extends BaseView<UserActionsListener>{
        void showPokemonHeader(PokemonListItem pokemonListItem);
        void showPokemonDetails(PokemonDetail pokemonDetail);
    }

    interface UserActionsListener extends BasePresenter {
        void getPokemonDetails();
    }
}
