package com.canvas.krish.pokemanager.pokemondetail.viewpager;

import com.canvas.krish.pokemanager.BasePresenter;
import com.canvas.krish.pokemanager.data.models.PokemonDetail;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;

/**
 * Created by krishnakandula on 1/23/17.
 */

public interface InfoContract {
    interface View {
        void showPokemonInfo(PokemonDetail pokemonDetail, PokemonListItem pokemonListItem);
    }
    interface Presenter extends BasePresenter{
        void getPokemonDetails();
    }
}
