package com.tae.dagger2withmvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tae.dagger2withmvp.R;
import com.tae.dagger2withmvp.model.itunesmodel.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Eduardo on 05/02/2016.
 */
public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

    private List<Result> artists;
    private Context context;

    public ArtistAdapter(Context context) {
        this.context = context;
        artists = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_artist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(artists.get(position).getArtworkUrl100()).into(holder.imageView);
        holder.textViews.get(0).setText(artists.get(position).getArtistName());
        holder.textViews.get(1).setText(artists.get(position).getCollectionName());
        holder.textViews.get(2).setText(artists.get(position).getTrackName());
    }

    @Override
    public int getItemCount() {
        return artists.isEmpty() ? 0 : artists.size();
    }


    /**
     * Add item in the last index
     * @param artist The item to be inserted
     */
    public void addItem(Result artist) {
        if (artist == null)
            throw new NullPointerException("The item cannot be null");

        artists.add(artist);
        notifyItemInserted(getItemCount() - 1);
    }

    /**
     * Add item in determined index
     * @param artist    The event to be inserted
     * @param position Index for the new event
     */
    public void addItem(Result artist, int position) {
        if (artist == null)
            throw new NullPointerException("The item cannot be null");

        if (position < getItemCount() || position > getItemCount())
            throw new IllegalArgumentException("The position must be between 0 and lastIndex + 1");

        artists.add(position, artist);
        notifyItemInserted(position);
    }

    /**
     * Add a bunch of items
     * @param artists Collection to add
     * */
    public void addAll(List<Result> artists) {
        if (artists == null)
            throw new NullPointerException("The items cannot be null");

        this.artists.addAll(artists);
        notifyItemRangeInserted(getItemCount() - 1, artists.size());
    }

    public void replace(List<Result> artists){
        this.artists = artists;
        notifyDataSetChanged();
    }

    /**
     * Delete all the items
     * */
    public void clear() {
        if (!artists.isEmpty()) {
            artists.clear();
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.img_artist)
        CircleImageView imageView;
        @Bind({R.id.tv_artist, R.id.tv_collection_name, R.id.tv_song_name})
        List<TextView> textViews;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
