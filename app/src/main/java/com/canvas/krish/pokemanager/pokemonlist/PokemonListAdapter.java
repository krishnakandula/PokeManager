package com.canvas.krish.pokemanager.pokemonlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.canvas.krish.pokemanager.R;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * Created by Krishna Chaitanya Kandula on 12/19/2016.
 */

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListViewHolder>{

    private List<PokemonListItem> mPokemonList;
    private Context mContext;

    public PokemonListAdapter(List<PokemonListItem> pokemonList, Context context){
        setList(pokemonList);
        this.mContext = context;
    }

    private void setList(List<PokemonListItem> pokemonList){
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
        PokemonListItem pokemon = mPokemonList.get(position);
        holder.bind(pokemon, mContext);
    }

    @Override
    public int getItemCount() {
        return mPokemonList.size();
    }

    public void replaceData(List<PokemonListItem> newList){
        this.mPokemonList = newList;
        notifyDataSetChanged();
    }
}

class PokemonListViewHolder extends RecyclerView.ViewHolder{
    private static final String LOG_TAG = PokemonListViewHolder.class.getSimpleName();
    TextView mIdTextView;
    TextView mNameTypeTextView;
    ImageView mSpriteImageView;

    public PokemonListViewHolder(View view){
        super(view);
        mIdTextView = (TextView) view.findViewById(R.id.pokemon_list_item_id_textview);
        mNameTypeTextView = (TextView) view.findViewById(R.id.pokemon_list_item_name_type_textview);
        mSpriteImageView = (ImageView) view.findViewById(R.id.pokemon_list_item_sprite_imageview);
    }

    public void bind(@NonNull PokemonListItem pokemon, Context context){
        mIdTextView.setText("" + pokemon.getId());
        StringBuilder nameType = new StringBuilder(String.format("%s \n%s", pokemon.getName(), pokemon.getType1()));
        if(pokemon.getType2() != null)
            nameType.append(String.format("/%s", pokemon.getType2()));
        mNameTypeTextView.setText(nameType.toString());
        Log.d(LOG_TAG, "Grabbing sprite.");
        Picasso.with(context).load(pokemon.getSprite_front_default_uri())
                .fit().centerCrop().noPlaceholder().into(mSpriteImageView);
    }
}