package com.canvas.krish.pokemanager.pokemonlist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.canvas.krish.pokemanager.R;

public class PokemonListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        boolean mTwoPane = (findViewById(R.id.pokemon_detail_container) != null);
        initFragment(PokemonListFragment.newInstance(mTwoPane));
    }

    private void initFragment(Fragment pokemonListFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.pokemon_list_container, pokemonListFragment)
                .commit();
    }
}
