package com.canvas.krish.pokemanager.data;

import android.util.Log;

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
    private static final String BASE_URL = "http://pokeapi.co/api/v2/";

    @Override
    public void getPokemonList(int minId, int maxId, PokemonServiceCallback<List<Pokemon>> callback) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
        PokemonEndpointAPI api = retrofit.create(PokemonEndpointAPI.class);

        Call<List<Pokemon>> call = api.loadPokemonList(10);
        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                Log.d(LOG_TAG, response.toString());
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getPokemon(int id, PokemonServiceCallback<Pokemon> callback) {
        //TODO: Write PokemonServiceImpl.getPokemon()
    }
}
