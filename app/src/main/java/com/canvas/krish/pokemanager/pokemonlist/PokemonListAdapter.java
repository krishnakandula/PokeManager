package com.canvas.krish.pokemanager.pokemonlist;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.canvas.krish.pokemanager.R;
import com.canvas.krish.pokemanager.data.PokemonRepositories;
import com.canvas.krish.pokemanager.data.PokemonRepository;
import com.canvas.krish.pokemanager.data.models.PokemonListItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import static com.google.common.base.Preconditions.*;

/**
 * Created by Krishna Chaitanya Kandula on 12/19/2016.
 */

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ListViewHolder> {

    private List<PokemonListItem> mPokemonList;
    private Context mContext;

    public PokemonListAdapter(List<PokemonListItem> pokemonList, Context context) {
        setList(pokemonList);
        this.mContext = context;
    }

    private void setList(List<PokemonListItem> pokemonList) {
        mPokemonList = checkNotNull(pokemonList);
    }

    @Override
    public PokemonListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.pokemon_list_item_view, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        PokemonListItem pokemon = mPokemonList.get(position);
        holder.bind(pokemon);
    }

    @Override
    public int getItemCount() {
        return mPokemonList.size();
    }

    public void replaceData(List<PokemonListItem> newList) {
        this.mPokemonList = newList;
        notifyDataSetChanged();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        private final String LOG_TAG = ListViewHolder.class.getSimpleName();
        TextView mIdTextView;
        TextView mNameTypeTextView;
        ImageView mArtworkImageView;

        public ListViewHolder(View view) {
            super(view);
            mIdTextView = (TextView) view.findViewById(R.id.pokemon_list_item_id_textview);
            mNameTypeTextView = (TextView) view.findViewById(R.id.pokemon_list_item_name_type_textview);
            mArtworkImageView = (ImageView) view.findViewById(R.id.pokemon_list_item_artwork_imageview);
        }

        public void bind(@NonNull PokemonListItem pokemon) {
            mIdTextView.setText("" + pokemon.getId());
            StringBuilder nameType = new StringBuilder(String.format("%s \n%s", pokemon.getName(), pokemon.getType1()));
            if (pokemon.getType2() != null)
                nameType.append(String.format("/%s", pokemon.getType2()));
            mNameTypeTextView.setText(nameType.toString());

            Log.v(LOG_TAG, "Grabbing artwork.");
            PokemonRepositories.getInMemoryPokemonRepository().getArtworkUri(pokemon.getId(), new PokemonRepository.GetArtworkUriCallback() {
                @Override
                public void onArtworkUriLoaded(Uri uri) {
                    updateArtwork(uri);
                }
            });
        }

        public void updateArtwork(Uri artworkUri) {
            Picasso.with(mContext).load(artworkUri.toString())
                    .fit().centerCrop().noPlaceholder().into(mArtworkImageView);
        }
    }
}