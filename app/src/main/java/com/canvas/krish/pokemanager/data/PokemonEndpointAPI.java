package com.canvas.krish.pokemanager.data;

import com.canvas.krish.pokemanager.data.models.PokemonDetail;
import com.canvas.krish.pokemanager.data.models.PokemonList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Krishna Chaitanya Kandula on 12/22/2016.
 */

interface PokemonEndpointAPI {
    //TODO: Change this endpoint. Pokemon list no longer needed
    @GET("/api/v2/pokemon/")
    Call<PokemonList> loadPokemonList(@Query("limit") int loadLimit, @Query("offset") int offset);

    @GET("/api/v2/pokemon/{name}")
    Call<PokemonDetail> loadPokemon(@Path("name") String pokemonName);

    @GET("/api/v2/pokemon/{id}")
    Call<PokemonDetail> loadPokemon(@Path("id") int pokemonId);
}
