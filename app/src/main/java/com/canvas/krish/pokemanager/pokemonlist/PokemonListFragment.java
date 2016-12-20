package com.canvas.krish.pokemanager.pokemonlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.canvas.krish.pokemanager.R;
import com.canvas.krish.pokemanager.data.Pokemon;

import java.util.List;

/**
 * Created by Krishna Chaitanya Kandula on 12/19/2016.
 */

public class PokemonListFragment extends Fragment implements PokemonListContract.View{
    private static final String LOG_TAG = PokemonListFragment.class.getSimpleName();
    private static final String TWO_PANE_UI_KEY = "TWO_PANE_UI_KEY";
    private boolean mTwoPaneUI;

    //TODO: Add parameters
    public static PokemonListFragment newInstance(boolean isUiTwoPane){
        PokemonListFragment fragment = new PokemonListFragment();

        Bundle bundle = new Bundle();
        bundle.putBoolean(TWO_PANE_UI_KEY, isUiTwoPane);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTwoPaneUI = (boolean) getArguments().get(TWO_PANE_UI_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pokemon_item_list, container, false);

        return view;
    }

    @Override
    public void showPokemonList(List<Pokemon> pokemonList) {

    }

    @Override
    public void showPokemonDetail(String pokemonId) {

    }
}
