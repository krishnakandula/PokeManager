package com.canvas.krish.pokemanager.pokemondetail;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.canvas.krish.pokemanager.R;
import com.canvas.krish.pokemanager.data.PokemonRepositories;
import com.canvas.krish.pokemanager.data.PokemonRepository;
import com.canvas.krish.pokemanager.data.models.PokemonDetail;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;
import com.canvas.krish.pokemanager.utils.PicassoCache;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

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
    @BindView(R.id.fragment_pokemon_detail_artwork) CircleImageView mArtworkImageView;
    @BindView(R.id.fragment_pokemon_detail_app_bar) AppBarLayout mAppBarLayout;
    @BindView(R.id.fragment_pokemon_detail_name) TextView mNameTextView;
    @BindView(R.id.fragment_pokemon_detail_id) TextView mIdTextView;
    @BindView(R.id.fragment_pokemon_detail_description) TextView mDescriptionTextView;
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showPokemonDetails(@NonNull PokemonDetail pokemonDetail) {
        Log.v(LOG_TAG, pokemonDetail.toString());
    }

    @Override
    public void showPokemonHeader(@NonNull PokemonListItem pokemonListItem) {
        updateArtwork(pokemonListItem);
        updateUiColors(pokemonListItem.getBackgroundColor());

        mNameTextView.setText(pokemonListItem.getName());
        mIdTextView.setText(String.format("#%d", pokemonListItem.getId()));
        mDescriptionTextView.setText(pokemonListItem.getDescription());
    }

    private void updateArtwork(PokemonListItem pokemonListItem){
        final Picasso picasso = PicassoCache.getPicassoInstance(getContext());
//        picasso.setIndicatorsEnabled(true);
//        picasso.setLoggingEnabled(true);
        PokemonRepositories.getInMemoryPokemonRepository().getArtworkUri(pokemonListItem.getId(), new PokemonRepository.GetArtworkUriCallback() {
            @Override
            public void onArtworkUriLoaded(Uri uri) {
                picasso.with(getActivity().getApplicationContext())
                        .load(uri)
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .resizeDimen(R.dimen.detail_artwork_image_view_height, R.dimen.detail_artwork_image_view_height)
                        .centerCrop()
                        .into(mArtworkImageView);
            }
        });
    }

    private void updateUiColors(int primaryColor){
        getActivity().getWindow().setStatusBarColor(primaryColor);
        mToolbar.setBackgroundColor(primaryColor);
        mAppBarLayout.setBackground(new ColorDrawable(primaryColor));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
