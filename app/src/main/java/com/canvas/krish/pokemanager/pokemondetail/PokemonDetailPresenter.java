package com.canvas.krish.pokemanager.pokemondetail;

import com.canvas.krish.pokemanager.data.models.PokemonListItem;

import javax.inject.Inject;

import dagger.Module;

/**
 * Created by Krishna Chaitanya Kandula on 1/1/2017.
 */
@Module
public class PokemonDetailPresenter implements PokemonDetailContract.UserActionsListener {
    private final PokemonDetailContract.View mDetailView;
    private String pokemonDetails;

    @Inject
    public PokemonDetailPresenter(PokemonDetailContract.View detailView){
        mDetailView = detailView;
        mDetailView.setPresenter(this);
    }

    @Override
    public void getPokemonDetails(PokemonListItem pokemonListItem) {
        pokemonDetails = pokemonListItem.getName();
    }

    @Override
    public void start() {
//        getPokemonDetails();
    }
}
