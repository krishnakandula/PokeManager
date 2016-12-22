package com.canvas.krish.pokemanager.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Krishna Chaitanya Kandula on 12/22/2016.
 */

interface PokemonEndpointAPI {

    @GET("/pokemon/")
    Call<List<Pokemon>> loadPokemonList(@Query("limit") int loadLimit);
}
