package com.canvas.krish.pokemanager.pokemondetail;

import android.os.Bundle;

import com.canvas.krish.pokemanager.BaseNavActivity;
import com.canvas.krish.pokemanager.R;

public class PokemonDetailActivity extends BaseNavActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
    }
}
