package com.canvas.krish.pokemanager.pokemonlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canvas.krish.pokemanager.R;
import com.canvas.krish.pokemanager.data.models.Pokemon;
import com.canvas.krish.pokemanager.data.PokemonRepositories;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Krishna Chaitanya Kandula on 12/19/2016.
 */

public class PokemonListFragment extends Fragment implements PokemonListContract.View{
    private static final String LOG_TAG = PokemonListFragment.class.getSimpleName();
    private static final String TWO_PANE_UI_KEY = "TWO_PANE_UI_KEY";
    private boolean mTwoPaneUI;

    private RecyclerView mPokemonRecyclerView;
    private PokemonListAdapter mPokemonListAdapter;
    private PokemonListContract.UserActionsListener mActionsListener;

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
        mActionsListener = new PokemonListPresenter(PokemonRepositories.getInMemoryPokemonRepository(),
                this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pokemon_item_list, container, false);
        mPokemonRecyclerView = (RecyclerView) view.findViewById(R.id.pokemon_list_recycler_view);
        setupRecyclerView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mActionsListener.loadPokemon();
    }

    private void setupRecyclerView(){
        mPokemonListAdapter = new PokemonListAdapter(new ArrayList<Pokemon>());
        mPokemonRecyclerView.setAdapter(mPokemonListAdapter);
        mPokemonRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void showPokemonList(List<Pokemon> pokemonList) {
        mPokemonListAdapter.replaceData(pokemonList);
    }

    @Override
    public void showPokemonDetail(String pokemonId) {
        //TODO: Write PokemonListFragment.showPokemonDetail()
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
