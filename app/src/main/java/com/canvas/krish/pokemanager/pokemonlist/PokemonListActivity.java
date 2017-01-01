package com.canvas.krish.pokemanager.pokemonlist;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.canvas.krish.pokemanager.BaseNavActivity;
import com.canvas.krish.pokemanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PokemonListActivity extends BaseNavActivity {
    private static final String LOG_TAG = PokemonListActivity.class.getSimpleName();
    private final String LIST_FRAGMENT_TAG = "POKEMON_LIST_FRAGMENT_TAG";

    @BindView(R.id.activity_pokemon_list_toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.nav_drawer_item_pokedex);

        setupNavDrawer(this);

        boolean mTwoPane = (findViewById(R.id.pokemon_detail_container) != null);
        initFragment(PokemonListFragment.newInstance(mTwoPane));
    }

    private void initFragment(Fragment pokemonListFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(LIST_FRAGMENT_TAG);
        if (fragment == null) {
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
