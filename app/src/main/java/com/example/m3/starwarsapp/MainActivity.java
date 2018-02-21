package com.example.m3.starwarsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Context mContext;
    String radioButtonResult;
    private ArrayList<Movie> movieList;
    MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        // data to display
        movieList = Movie.getMoviesFromFile("movies.json", this);

        // Create our adapter
        adapter = new MovieAdapter(this, movieList);

        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Movie selectedMovie = movieList.get(position);
                Intent movieDetailIntent = new Intent(mContext, MovieDetailActivity.class);

                movieDetailIntent.putExtra("title", selectedMovie.title);
                movieDetailIntent.putExtra("episode_number", selectedMovie.episode_number);
                movieDetailIntent.putExtra("description", selectedMovie.description);
                movieDetailIntent.putExtra("poster_url", selectedMovie.posterUrl);
                movieDetailIntent.putExtra("position", position);

                startActivityForResult(movieDetailIntent, 1);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                radioButtonResult = data.getStringExtra("checkedRadioButton");
                Integer pos = data.getIntExtra("position",0);
                Movie foo = movieList.get(pos);
                foo.hasSeenUpdated = true;
                foo.hasSeenString = radioButtonResult;
                movieList.set(pos, foo);
                adapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                radioButtonResult = "something went wrong :(";
            }
        }
    }



    // Some questions I have
    // What is context exactly?
    // Margin and fitXY in .xml


}
