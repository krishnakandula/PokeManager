package com.canvas.krish.pokemanager.pokemonlist;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canvas.krish.pokemanager.R;
import com.canvas.krish.pokemanager.data.InMemoryPokemonRepository;
import com.canvas.krish.pokemanager.data.PokemonRepositories;
import com.canvas.krish.pokemanager.data.PokemonRepository;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;
import com.canvas.krish.pokemanager.pokemondetail.PokemonDetailFragment;
import com.canvas.krish.pokemanager.utils.PicassoCache;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
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
    public static final String LIST_FRAGMENT_TAG = "POKEMON_LIST_FRAGMENT_TAG";

    @BindView(R.id.pokemon_list_recycler_view) RecyclerView mPokemonRecyclerView;
    @BindView(R.id.pokemon_list_toolbar) Toolbar mToolbar;
    @BindColor(R.color.colorPrimaryDark) int mColorPrimaryDark;

    private PokemonListAdapter mPokemonListAdapter;
    private PokemonListContract.UserActionsListener mActionsListener;
    private Unbinder mUnbinder;

    public static PokemonListFragment newInstance(){
        PokemonListFragment fragment = new PokemonListFragment();

        Bundle bundle = new Bundle();

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionsListener = new PokemonListPresenter(PokemonRepositories.getInMemoryPokemonRepository(),
                this, getActivity().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pokemon_item_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setupRecyclerView();
        mToolbar.setTitle(R.string.nav_drawer_item_pokedex);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mActionsListener.loadPokemon();
        updateUiColors();
        //Resume loading of images
        PicassoCache.getPicassoInstance(getActivity().getApplicationContext())
                .resumeTag(PokemonListAdapter.POKEMON_ARTWORK_LIST_TAG);
    }

    private void setupRecyclerView(){
        int animationTime = 300;
        int itemViewCacheSize = 30;

        mPokemonListAdapter = new PokemonListAdapter(new ArrayList<PokemonListItem>(), getActivity().getApplicationContext(), mListItemListener);
        PokemonListLinearLayoutManager layoutManager = new PokemonListLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

//        Set animation adapter for scale in and alpha in
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(mPokemonListAdapter);
        animationAdapter.setDuration(animationTime);

//        Set divider to separate items
        DividerItemDecoration dividerDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
//        mPokemonRecyclerView.addItemDecoration(dividerDecoration);

        mPokemonRecyclerView.setAdapter(new AlphaInAnimationAdapter(animationAdapter));
        mPokemonRecyclerView.setHasFixedSize(true);
        mPokemonRecyclerView.setDrawingCacheEnabled(true);
        mPokemonRecyclerView.setItemViewCacheSize(itemViewCacheSize);
        mPokemonRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        mPokemonRecyclerView.setLayoutManager(layoutManager);
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
            fragment = PokemonDetailFragment.newInstance(pokemonListItem.getId());
            fm.beginTransaction()
                    .replace(R.id.pokemon_list_container, fragment, PokemonDetailFragment.DETAIL_FRAGMENT_TAG)
                    .addToBackStack(LIST_FRAGMENT_TAG)
                    .commit();
        }
    }

    private void updateUiColors(){
        getActivity().getWindow().setStatusBarColor(mColorPrimaryDark);
    }

    private PokemonListAdapter.PokemonListItemClickListener mListItemListener = new PokemonListAdapter.PokemonListItemClickListener() {
        @Override
        public void onPokemonClick(PokemonListItem pokemonListItem) {
            PokemonRepositories.getInMemoryPokemonRepository().getArtworkUri(pokemonListItem.getId(), new PokemonRepository.GetArtworkUriCallback() {
                @Override
                public void onArtworkUriLoaded(Uri uri) {
                    PicassoCache.getPicassoInstance(getActivity().getApplicationContext()).load(uri).fetch();
                }
            });
            mActionsListener.openPokemonDetails(pokemonListItem);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        //Stop loading of images
        PicassoCache.getPicassoInstance(getActivity().getApplicationContext())
                .cancelTag(PokemonListAdapter.POKEMON_ARTWORK_LIST_TAG);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
