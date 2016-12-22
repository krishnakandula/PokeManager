package com.canvas.krish.pokemanager.pokemonlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.canvas.krish.pokemanager.R;
import com.canvas.krish.pokemanager.data.Pokemon;

import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * Created by Krishna Chaitanya Kandula on 12/19/2016.
 */

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListViewHolder>{

    private List<Pokemon> mPokemonList;

    public PokemonListAdapter(List<Pokemon> pokemonList){
        setList(pokemonList);
    }

    private void setList(List<Pokemon> pokemonList){
        mPokemonList = checkNotNull(pokemonList);
    }

    @Override
    public PokemonListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.pokemon_list_item_view, parent, false);
        return new PokemonListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonListViewHolder holder, int position) {
        Pokemon pokemon = mPokemonList.get(position);
        holder.bind(pokemon);
    }

    @Override
    public int getItemCount() {
        return mPokemonList.size();
    }

    public void replaceData(List<Pokemon> newList){
        this.mPokemonList = newList;
        notifyDataSetChanged();
    }
}

class PokemonListViewHolder extends RecyclerView.ViewHolder{
    TextView mIdTextView;

    public PokemonListViewHolder(View view){
        super(view);
//        Unbinder unbinder = ButterKnife.bind(this, view);
        mIdTextView = (TextView) view.findViewById(R.id.pokemon_list_item_id_textview);
    }

    public void bind(@NonNull Pokemon pokemon){
        mIdTextView.setText("" + pokemon.getId());
    }
}