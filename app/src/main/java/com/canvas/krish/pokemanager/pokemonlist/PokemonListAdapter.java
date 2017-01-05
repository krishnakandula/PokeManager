package com.canvas.krish.pokemanager.pokemonlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
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
import com.canvas.krish.pokemanager.utils.PicassoCache;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.google.common.base.Preconditions.*;

/**
 * Created by Krishna Chaitanya Kandula on 12/19/2016.
 */

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ListViewHolder> {
    public static final String POKEMON_ARTWORK_LIST_TAG = "pokemon_artwork";
    private static final String LOG_TAG = PokemonListAdapter.class.getSimpleName();
    private List<PokemonListItem> mPokemonList;
    private Context mContext;
    private PokemonListItemClickListener mListItemClickListener;

    public PokemonListAdapter(List<PokemonListItem> pokemonList, Context context, PokemonListItemClickListener itemClickListener) {
        setList(pokemonList);
        this.mContext = context;
        this.mListItemClickListener = itemClickListener;
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
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mPokemonList.size();
    }

    public void replaceData(List<PokemonListItem> newList) {
        this.mPokemonList = newList;
        notifyDataSetChanged();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final String LOG_TAG = ListViewHolder.class.getSimpleName();
        @BindView(R.id.pokemon_list_item_id_textview) TextView mIdTextView;
        @BindView(R.id.pokemon_list_item_name_type_textview) TextView mNameTypeTextView;
        @BindView(R.id.pokemon_list_item_artwork_imageview) CircleImageView mArtworkImageView;
        @BindView(R.id.pokemon_list_item_cardview) CardView mCardView;
        @BindView(R.id.pokemon_list_item_description_textview) TextView mDescriptionTextView;
        int position;
        private PokemonListItem pokemon;
        final String defaultBackgroundColor = "#FFFFFF";  //White: default CardView background color

        public ListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        public void bind(@NonNull int position) {
            this.position = position;
            pokemon = mPokemonList.get(position);
            mIdTextView.setText("#" + pokemon.getId());
            StringBuilder nameType = new StringBuilder(String.format("%s \n%s", pokemon.getName(), pokemon.getType1()));
            if (pokemon.getType2() != null)
                nameType.append(String.format("/%s", pokemon.getType2()));
            mNameTypeTextView.setText(nameType.toString());
            mDescriptionTextView.setText(pokemon.getDescription());

            PokemonRepositories.getInMemoryPokemonRepository()
                    .getArtworkUri(pokemon.getId(), new PokemonRepository.GetArtworkUriCallback() {
                        @Override
                        public void onArtworkUriLoaded(Uri uri) {
                            updateArtwork(uri);
                        }
                    });
        }

        public void updateArtwork(final Uri artworkUri) {
            Picasso picasso = PicassoCache.getPicassoInstance(mContext);
//            picasso.setIndicatorsEnabled(true);
//            picasso.setLoggingEnabled(true);
            picasso.with(mContext)
                    .load(artworkUri.toString())
                    .tag(POKEMON_ARTWORK_LIST_TAG)
                    .resizeDimen(R.dimen.list_artwork_image_view_height, R.dimen.list_artwork_image_view_height)
                    .centerCrop()
                    .into(mArtworkImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            //Update drawable color asynchronously
                            Bitmap imageBitmap = ((BitmapDrawable) mArtworkImageView.getDrawable()).getBitmap();
                            Palette.from(imageBitmap).generate(new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(Palette palette) {
                                    pokemon.setBackgroundColor(palette.getMutedColor(Color.parseColor(defaultBackgroundColor)));
                                    mCardView.setBackgroundColor(pokemon.getBackgroundColor());
                                }
                            });
                        }

                        @Override
                        public void onError() {
                            String errorMessage = String.format("ERROR: Could not load image with URI %s", artworkUri);
                            Log.e(LOG_TAG, errorMessage);
                        }
                    });
        }

        @Override
        public void onClick(View v) {
            mListItemClickListener.onPokemonClick(mPokemonList.get(position));
        }
    }

    interface PokemonListItemClickListener {
        void onPokemonClick(PokemonListItem pokemonListItem);
    }
}