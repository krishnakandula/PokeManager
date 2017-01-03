package com.canvas.krish.pokemanager.pokemonlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canvas.krish.pokemanager.R;
import com.canvas.krish.pokemanager.data.PokemonRepositories;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;
import com.canvas.krish.pokemanager.pokemondetail.PokemonDetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;


/**
 * Created by Krishna Chaitanya Kandula on 12/19/2016.
 */

public class PokemonListFragment extends Fragment implements PokemonListContract.View{
    private static final String LOG_TAG = PokemonListFragment.class.getSimpleName();
    private static final String TWO_PANE_UI_KEY = "TWO_PANE_UI_KEY";
    public static final String LIST_FRAGMENT_TAG = "POKEMON_LIST_FRAGMENT_TAG";
    private boolean mTwoPaneUI;

    @BindView(R.id.pokemon_list_recycler_view) RecyclerView mPokemonRecyclerView;
    private PokemonListAdapter mPokemonListAdapter;
    private PokemonListContract.UserActionsListener mActionsListener;
    private Unbinder mUnbinder;

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
                this, getActivity().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pokemon_item_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setupRecyclerView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mActionsListener.loadPokemon();
    }

    private void setupRecyclerView(){
        int animationTime = 300;
        int itemViewCacheSize = 30;

        mPokemonListAdapter = new PokemonListAdapter(new ArrayList<PokemonListItem>(), getContext(), mListItemListener);

//        Set animation adapter for scale in and alpha in
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(mPokemonListAdapter);
        animationAdapter.setDuration(animationTime);

        mPokemonRecyclerView.setAdapter(new AlphaInAnimationAdapter(animationAdapter));
        mPokemonRecyclerView.setHasFixedSize(true);
        mPokemonRecyclerView.setDrawingCacheEnabled(true);
        mPokemonRecyclerView.setItemViewCacheSize(itemViewCacheSize);
        mPokemonRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        mPokemonRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void showPokemonList(List<PokemonListItem> pokemonList) {
        mPokemonListAdapter.replaceData(pokemonList);
    }

    @Override
    public void showPokemonDetail(PokemonListItem pokemonListItem) {
        //TODO: Write PokemonListFragment.showPokemonDetail()
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentByTag(PokemonDetailFragment.DETAIL_FRAGMENT_TAG);
        if(fragment == null){
            fragment = PokemonDetailFragment.newInstance(pokemonListItem);
            if(mTwoPaneUI){
                fm.beginTransaction()
                        .replace(R.id.pokemon_detail_container, fragment, PokemonDetailFragment.DETAIL_FRAGMENT_TAG)
                        .commit();
            } else {
                fm.beginTransaction()
                        .replace(R.id.pokemon_list_container, fragment, PokemonDetailFragment.DETAIL_FRAGMENT_TAG)
                        .addToBackStack(LIST_FRAGMENT_TAG)
                        .commit();
            }
        }
    }

    private PokemonListAdapter.PokemonListItemClickListener mListItemListener = new PokemonListAdapter.PokemonListItemClickListener() {
        @Override
        public void onPokemonClick(PokemonListItem pokemonListItem) {
            mActionsListener.openPokemonDetails(pokemonListItem);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
