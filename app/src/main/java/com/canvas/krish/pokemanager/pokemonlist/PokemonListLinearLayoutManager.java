package com.canvas.krish.pokemanager.pokemonlist;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Krishna Chaitanya Kandula on 1/3/2017.
 */

public class PokemonListLinearLayoutManager extends LinearLayoutManager{

    private OrientationHelper mOrientationHelper;

    @Override
    protected int getExtraLayoutSpace(RecyclerView.State state) {
        //Number of items to generate in list. Default is 1 page
        int itemsOnPage = mOrientationHelper.getEndAfterPadding() - mOrientationHelper.getStartAfterPadding();
        if(state.hasTargetScrollPosition()){
            return 2 * itemsOnPage;
        } else{
            return itemsOnPage;
        }
    }

    public PokemonListLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        mOrientationHelper = OrientationHelper.createVerticalHelper(this);
    }
}
