package com.canvas.krish.pokemanager.pokemonlist;

import android.content.Context;
import android.support.annotation.NonNull;

import com.canvas.krish.pokemanager.data.PokemonRepository;
import com.canvas.krish.pokemanager.data.models.PokemonDetail;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;

import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * Created by Krishna Chaitanya Kandula on 12/19/2016.
 */

public class PokemonListPresenter implements PokemonListContract.UserActionsListener {

    private final PokemonRepository mPokemonRepository;
    private final PokemonListContract.View mPokemonListView;
    private Context mContext;

    public PokemonListPresenter(@NonNull PokemonRepository repository, @NonNull PokemonListContract.View view, Context context){
        mPokemonRepository = checkNotNull(repository, "PokemonRepository can't be null.");
        mPokemonListView = checkNotNull(view, "PokemonListContract.View can't be null.");
        mContext = context;
    }

    @Override
    public void loadPokemon() {
        mPokemonRepository.getPokemonList(mContext, new PokemonRepository.LoadPokemonCallback() {
            @Override
            public void onPokemonLoaded(List<PokemonListItem> pokemonList) {
                mPokemonListView.showPokemonList(pokemonList);
            }
        });
    }

    @Override
    public void openPokemonDetails(@NonNull PokemonListItem pokemonListItem) {
        mPokemonListView.showPokemonDetail(pokemonListItem);
    }
}
