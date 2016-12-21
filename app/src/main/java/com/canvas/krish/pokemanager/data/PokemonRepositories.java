package com.canvas.krish.pokemanager.data;

/**
 * Created by Krishna Chaitanya Kandula on 12/20/2016.
 * Singleton class that retrieves the appropriate repository
 */

public class PokemonRepositories {
    private static PokemonRepository sRepositories = null;

    private PokemonRepositories(){}

    //TODO: Write getInMemoryPokemonRepository()
    public synchronized static PokemonRepository getInMemoryPokemonRepository(){
//        com.google.common.base.Preconditions.checkNotNull()
        if(sRepositories == null)
            sRepositories = new InMemoryPokemonRepository();

        return sRepositories;
    }
}
