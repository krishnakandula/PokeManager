package com.canvas.krish.pokemanager.pokemonlist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.canvas.krish.pokemanager.R;

public class PokemonListActivity extends AppCompatActivity {
    private static final String LOG_TAG = PokemonListActivity.class.getSimpleName();
    private final String LIST_FRAGMENT_TAG = "POKEMON_LIST_FRAGMENT_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        boolean mTwoPane = (findViewById(R.id.pokemon_detail_container) != null);

        initFragment(PokemonListFragment.newInstance(mTwoPane));
    }

    private void initFragment(Fragment pokemonListFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(LIST_FRAGMENT_TAG);
        if(fragment == null) {
            fragmentManager.beginTransaction().
                    replace(R.id.pokemon_list_container, pokemonListFragment, LIST_FRAGMENT_TAG).
                    commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
