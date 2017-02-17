package com.canvas.krish.pokemanager.pokemondetail.viewpager;

import com.canvas.krish.pokemanager.data.PokemonRepositories;
import com.canvas.krish.pokemanager.data.PokemonRepository;
import com.canvas.krish.pokemanager.data.models.PokemonDetail;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;

/**
 * Created by krishnakandula on 1/23/17.
 */

public class InfoPresenter implements InfoContract.Presenter {
    private int pokemonId;
    private InfoContract.View view;

    protected InfoPresenter(InfoContract.View view, int pokemonId){
        this.pokemonId = pokemonId;
        this.view = view;
    }

    @Override
    public void start() {
        getPokemonDetails();
    }

    @Override
    public void getPokemonDetails() {

        PokemonRepositories.getInMemoryPokemonRepository().getPokemon(pokemonId, new PokemonRepository.GetPokemonCallback() {
            @Override
            public void onPokemonLoaded(PokemonDetail pokemonDetail, PokemonListItem pokemonListItem) {
                view.showPokemonInfo(pokemonDetail, pokemonListItem);
            }
        });
    }
}
