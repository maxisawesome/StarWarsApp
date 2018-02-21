package com.example.m3.starwarsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by m3 on 2/15/18.
 */

public class MovieAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflator;

    //constructor
    public MovieAdapter(Context mContext, ArrayList<Movie> mMovieList){
        //initialize instances variables
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflator = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount(){
        return mMovieList.size();
    }

    @Override
    public Object getItem(int position){
        return mMovieList.get(position);
    }

    // returns the row id associated with the specific position in the list
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate
            convertView = mInflator.inflate(R.layout.list_view_item_layout, parent, false);
            // add the views to the golder
            viewHolder = new ViewHolder();
            // views
            viewHolder.titleTextView = convertView.findViewById(R.id.titleTextView);
            viewHolder.descriptionTextView= convertView.findViewById(R.id.descriptionTextView);
            viewHolder.posterImageView = convertView.findViewById(R.id.moviePoster);
            viewHolder.actorsTextView = convertView.findViewById(R.id.actorTextView);
            viewHolder.hasSeenView = convertView.findViewById(R.id.finalTextView);
            // add the holder to the view
            // for the future use
            convertView.setTag(viewHolder);

        } else {
            // get the view holder from convertView
            viewHolder = (ViewHolder)convertView.getTag();
        }

        TextView titleTextView = viewHolder.titleTextView;
        TextView descriptionTextView = viewHolder.descriptionTextView;
        ImageView thumbnailImageView = viewHolder.posterImageView;
        TextView actorsTextView = viewHolder.actorsTextView;
        TextView hasSeenView = viewHolder.hasSeenView;

        Movie movie = (Movie)getItem(position);

        titleTextView.setText(movie.title);
        descriptionTextView.setText(movie.description);
        Picasso.with(mContext).load(movie.posterUrl).into(thumbnailImageView);

        String char1 = movie.mainCharacters.get(0);
        String char2 = movie.mainCharacters.get(1);
        String char3 = movie.mainCharacters.get(2);
        String characters = char1 + ", " + char2 + ", " + char3;
        actorsTextView.setText(characters);

        if(movie.hasSeenUpdated)
            hasSeenView.setText(movie.hasSeenString);


        return convertView;
    }



    private static class ViewHolder {
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView actorsTextView;
        public ImageView posterImageView;
        public TextView hasSeenView;
    }
}
