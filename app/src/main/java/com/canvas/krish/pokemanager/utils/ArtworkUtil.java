package com.canvas.krish.pokemanager.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.canvas.krish.pokemanager.data.PokemonRepositories;
import com.canvas.krish.pokemanager.data.PokemonRepository;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Krishna Chaitanya Kandula on 1/3/2017.
 */

public class ArtworkUtil {
    private static final String LOG_TAG = ArtworkUtil.class.getSimpleName();

    public interface ArtworkCallback {
        void onArtworkLoaded(ImageView imageView);
    }

    public static void updatePokemonArtwork(int pokemonId, final Context context, final ImageView view, final ArtworkCallback callback){
        PokemonRepositories.getInMemoryPokemonRepository().getArtworkUri(pokemonId, new PokemonRepository.GetArtworkUriCallback() {
            @Override
            public void onArtworkUriLoaded(Uri uri) {
                Picasso.with(context).cancelRequest(view);
                Picasso.with(context).load(uri).noPlaceholder().fit().centerCrop().into(view, new Callback() {
                    @Override
                    public void onSuccess() {
                        callback.onArtworkLoaded(view);
                    }

                    @Override
                    public void onError() {
                        String errorMessage = String.format("ERROR: Could not load image.");
                        Log.e(LOG_TAG, errorMessage);
                    }
                });

            }
        });
    }
}
