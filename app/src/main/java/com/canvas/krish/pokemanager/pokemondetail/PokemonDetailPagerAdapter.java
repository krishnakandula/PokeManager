package com.canvas.krish.pokemanager.pokemondetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.canvas.krish.pokemanager.pokemondetail.viewpager.InfoFragment;
import com.canvas.krish.pokemanager.pokemondetail.viewpager.MovesFragment;

/**
 * Created by krishnakandula on 1/23/17.
 */

public class PokemonDetailPagerAdapter extends FragmentPagerAdapter{
    private static final int NUMBER_OF_PAGES = 2;
    private static final String[] pageTitles = {"Information", "Moves"};
    Fragment[] pagerFragments;

    protected PokemonDetailPagerAdapter(FragmentManager fragmentManager, int pokemonId){
        super(fragmentManager);
        pagerFragments = new Fragment[]{InfoFragment.newInstance(pokemonId), new MovesFragment()};
    }

    @Override
    public Fragment getItem(int position) {
        return pagerFragments[position];
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
}
