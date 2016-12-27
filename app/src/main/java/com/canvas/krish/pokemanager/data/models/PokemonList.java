package com.canvas.krish.pokemanager.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krishnakandula on 12/22/16.
 */

public class PokemonList {
    private int count;
    private String previous;

    private List<PokemonListItem> pokemonList;
    private String next;

    public PokemonList(){
        pokemonList = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PokemonListItem> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<PokemonListItem> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}