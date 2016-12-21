package com.canvas.krish.pokemanager.data;

/**
 * Created by Krishna Chaitanya Kandula on 12/19/2016.
 */

import android.support.annotation.NonNull;

/**
 * Immutable model class for a Pokemon
 */
public final class Pokemon {
    private int mId;

    public Pokemon(@NonNull int id){
        this.mId = id;
    }

    public int getId() {
        return mId;
    }
}
