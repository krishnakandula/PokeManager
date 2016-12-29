package com.canvas.krish.pokemanager.test;

import com.canvas.krish.pokemanager.data.InMemoryPokemonRepository;
import com.canvas.krish.pokemanager.data.PokemonRepositories;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Krishna Chaitanya Kandula on 12/29/2016.
 */
public class InMemoryPokemonRepositoryTest {
    private InMemoryPokemonRepository mRepository;

    @Before
    public void setUp() throws Exception {
        mRepository = (InMemoryPokemonRepository) PokemonRepositories.getInMemoryPokemonRepository();
    }

    @Test
    public void capitalizeFirstLetter() throws Exception {
        String testString = "";
        assertEquals(mRepository.capitalizeFirstLetter(testString), "");
    }

}