package com.canvas.krish.pokemanager.pokemondetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canvas.krish.pokemanager.R;
import com.canvas.krish.pokemanager.data.models.PokemonDetail;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Krishna Chaitanya Kandula on 1/1/2017.
 */

public class PokemonDetailFragment extends Fragment implements PokemonDetailContract.View {
    public static final String DETAIL_FRAGMENT_TAG = "POKEMON_DETAIL_FRAGMENT_TAG";
    public static final String DETAIL_FRAGMENT_POKEMON_ITEM_PARCEL = "DETAIL_FRAGMENT_POKEMON_ITEM_PARCEL";

    private Unbinder mUnbinder;

    @Inject
    PokemonDetailContract.UserActionsListener mPresenter;

    public static PokemonDetailFragment newInstance(@NonNull PokemonListItem pokemonListItem) {
        Bundle args = new Bundle();
        args.putParcelable(DETAIL_FRAGMENT_POKEMON_ITEM_PARCEL, pokemonListItem);
        PokemonDetailFragment fragment = new PokemonDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showPokemonDetails(PokemonListItem pokemonListItem, PokemonDetail pokemonDetail) {

    }

    @Override
    public void setPresenter(PokemonDetailContract.UserActionsListener presenter) {
        mPresenter = presenter;
    }
}
