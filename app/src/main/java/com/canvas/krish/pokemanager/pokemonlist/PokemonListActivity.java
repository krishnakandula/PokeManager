package com.canvas.krish.pokemanager.pokemonlist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.canvas.krish.pokemanager.BaseNavActivity;
import com.canvas.krish.pokemanager.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PokemonListActivity extends BaseNavActivity {
    private static final String LOG_TAG = PokemonListActivity.class.getSimpleName();
    private Unbinder mUnbinder;

    @BindView(R.id.activity_pokemon_list_toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);
        mUnbinder = ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.nav_drawer_item_pokedex);

        setupNavDrawer(mDrawerItemClickListener);

        boolean mTwoPane = (findViewById(R.id.pokemon_detail_container) != null);
        initFragment(PokemonListFragment.newInstance(mTwoPane));
    }

    private void initFragment(Fragment pokemonListFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(PokemonListFragment.LIST_FRAGMENT_TAG);
        if (fragment == null) {
            fragmentManager.beginTransaction().
                    replace(R.id.pokemon_list_container, pokemonListFragment, PokemonListFragment.LIST_FRAGMENT_TAG)
                    .commit();
        }
    }

    private Drawer.OnDrawerItemClickListener mDrawerItemClickListener = new Drawer.OnDrawerItemClickListener() {
        @Override
        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
            switch (position) {
                case TEAMS_DRAWER_ITEM_IDENTIFIER:
                    return true;
                default:
                    return false;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}