package com.canvas.krish.pokemanager.pokemondetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canvas.krish.pokemanager.R;
import com.canvas.krish.pokemanager.data.models.PokemonDetail;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Krishna Chaitanya Kandula on 1/1/2017.
 */

public class PokemonDetailFragment extends Fragment implements PokemonDetailContract.View {
    public static final String DETAIL_FRAGMENT_TAG = "POKEMON_DETAIL_FRAGMENT_TAG";
    public static final String POKEMON_ITEM_ID = "POKEMON_ITEM_ID";
    private static final String LOG_TAG = PokemonDetailFragment.class.getSimpleName();
    private Unbinder mUnbinder;
    private int mPokemonId;

    @BindView(R.id.fragment_pokemon_detail_toolbar) Toolbar mToolbar;
    PokemonDetailContract.UserActionsListener mPresenter;

    public static PokemonDetailFragment newInstance(@NonNull int pokemonId) {
        Bundle args = new Bundle();
        args.putInt(POKEMON_ITEM_ID, pokemonId);
        PokemonDetailFragment fragment = new PokemonDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mPokemonId = args.getInt(POKEMON_ITEM_ID);
        mPresenter = new PokemonDetailPresenter(this, mPokemonId, getContext().getApplicationContext());
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);
        mUnbinder = ButterKnife.bind(this, view);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
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
    public void showPokemonDetails(@NonNull PokemonListItem pokemonListItem, @NonNull PokemonDetail pokemonDetail) {
        Log.d(LOG_TAG, pokemonListItem.getDescription());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
