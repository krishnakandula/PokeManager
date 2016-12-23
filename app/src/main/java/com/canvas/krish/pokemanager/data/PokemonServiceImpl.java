package com.canvas.krish.pokemanager.data;

import android.util.Log;

import com.canvas.krish.pokemanager.data.models.Pokemon;
import com.canvas.krish.pokemanager.data.models.PokemonList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Krishna Chaitanya Kandula on 12/22/2016.
 */

class PokemonServiceImpl implements PokemonServiceApi {
    private static final String LOG_TAG = PokemonServiceImpl.class.getSimpleName();
    private static final String BASE_URL = "http://pokeapi.co";

    @Override
    public void getPokemonList(int minId, int maxId, final PokemonServiceCallback<List<Pokemon>> callback) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        PokemonEndpointAPI api = retrofit.create(PokemonEndpointAPI.class);
        Call<PokemonList> call = api.loadPokemonList(10);
        call.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                if(response.code() != 200){
                    Throwable t = new Throwable("Could not retrieve data. Error code: " + response.code());
                    onFailure(call, t);
                } else {
                    callback.onLoaded(response.body().getPokemonList());
                }
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                Log.d(LOG_TAG, "ERROR: " + t.getMessage());
            }
        });
    }

    @Override
    public void getPokemon(int id, PokemonServiceCallback<Pokemon> callback) {
        //TODO: Write PokemonServiceImpl.getPokemon()
    }
}
