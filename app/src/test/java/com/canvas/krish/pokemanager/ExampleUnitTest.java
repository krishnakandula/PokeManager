package com.canvas.krish.pokemanager;

import com.canvas.krish.pokemanager.data.PokemonRepositories;
import com.canvas.krish.pokemanager.data.PokemonRepository;

import org.junit.Test;

import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void networking_test(){
        PokemonRepository repository = PokemonRepositories.getInMemoryPokemonRepository();
         repository.getPokemonList(new PokemonRepository.LoadPokemonCallback() {
             @Override
             public void onPokemonLoaded(List<Pokemon> pokemonList) {
                 assert(pokemonList != null && !pokemonList.isEmpty());
             }
         });
    }
}