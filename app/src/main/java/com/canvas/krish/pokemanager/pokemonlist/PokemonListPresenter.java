package com.canvas.krish.pokemanager.pokemonlist;

import android.support.annotation.NonNull;

import com.canvas.krish.pokemanager.data.models.Pokemon;
import com.canvas.krish.pokemanager.data.PokemonRepository;

import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * Created by Krishna Chaitanya Kandula on 12/19/2016.
 */

public class PokemonListPresenter implements PokemonListContract.UserActionsListener {

    private final PokemonRepository mPokemonRepository;
    private final PokemonListContract.View mPokemonListView;

    public PokemonListPresenter(@NonNull PokemonRepository repository, @NonNull PokemonListContract.View view){
        mPokemonRepository = checkNotNull(repository, "PokemonRepository can't be null.");
        mPokemonListView = checkNotNull(view, "PokemonListContract.View can't be null.");
    }

    @Override
    public void loadPokemon() {
        mPokemonRepository.getPokemonList(new PokemonRepository.LoadPokemonCallback() {
            @Override
            public void onPokemonLoaded(List<Pokemon> pokemonList) {
                mPokemonListView.showPokemonList(pokemonList);
            }
        });
    }

    @Override
    public void openPokemonDetails(@NonNull Pokemon requestedPokemon) {

    }
}
