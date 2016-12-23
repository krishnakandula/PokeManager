package com.canvas.krish.pokemanager.data;

import com.canvas.krish.pokemanager.data.models.Pokemon;
import com.canvas.krish.pokemanager.data.models.PokemonList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Krishna Chaitanya Kandula on 12/22/2016.
 */

interface PokemonEndpointAPI {
    @GET("/api/v2/pokemon/")
    Call<PokemonList> loadPokemonList(@Query("limit") int loadLimit, @Query("offset") int offset);
}
