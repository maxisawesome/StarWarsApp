package com.example.m3.starwarsapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by m3 on 2/15/18.
 */

public class Movie {
    public String title;
    public int episode_number;
    public ArrayList<String> mainCharacters;
    public String description;
    public String posterUrl;
    public String url;
    public boolean hasSeenUpdated = false;
    public String hasSeenString = "Has seen?";


    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context){
        ArrayList<Movie>  movieList = new ArrayList<>();
        try {
            String jsonString = loadJsonFromAsset(filename, context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");

            for(int i = 0; i < movies.length(); i++){
                Movie movie = new Movie();

                movie.title = movies.getJSONObject(i).getString("title");
                movie.episode_number = movies.getJSONObject(i).getInt("episode_number");
                movie.description = movies.getJSONObject(i).getString("description");
                movie.posterUrl = movies.getJSONObject(i).getString("poster");
                movie.url = movies.getJSONObject(i).getString("url");

                JSONArray main_characters = movies.getJSONObject(i).getJSONArray("main_characters");
                ArrayList<String> tempArray = new ArrayList<>();
                for(int j = 0; j < 3; j++){
                    if(main_characters != null) {
                        tempArray.add(main_characters.get(j).toString());
                    }
                }
                movie.mainCharacters = tempArray;

                movieList.add(movie);
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;



    }


    // helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}
