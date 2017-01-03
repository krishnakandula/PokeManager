package com.canvas.krish.pokemanager.pokemondetail;

import android.content.Context;

import com.canvas.krish.pokemanager.data.PokemonRepositories;
import com.canvas.krish.pokemanager.data.PokemonRepository;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;

/**
 * Created by Krishna Chaitanya Kandula on 1/1/2017.
 */
@Module
public class PokemonDetailPresenter implements PokemonDetailContract.UserActionsListener {
    private final PokemonDetailContract.View mDetailView;
    private int mPokemonId;
    private Context mContext;

    @Inject
    public PokemonDetailPresenter(PokemonDetailContract.View detailView, int pokemonId, Context context){
        mDetailView = detailView;
        mPokemonId = pokemonId;
        mContext = context;
    }

    @Override
    public void getPokemonDetails() {
        PokemonRepositories.getInMemoryPokemonRepository()
                .getPokemonList(mContext, new PokemonRepository.LoadPokemonCallback() {
                    @Override
                    public void onPokemonLoaded(List<PokemonListItem> pokemonList) {
                        mDetailView.showPokemonDetails(pokemonList.get(mPokemonId - 1), null);
                    }
                });
    }

    @Override
    public void start() {
        getPokemonDetails();
    }
}
