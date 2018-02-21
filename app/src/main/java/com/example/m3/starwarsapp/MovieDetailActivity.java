package com.example.m3.starwarsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * Created by m3 on 2/15/18.
 */

public class MovieDetailActivity extends AppCompatActivity {

    private Context mContext;
    private TextView titleTextView;
    private ImageView posterImageView;
    private TextView descriptionTextView;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private Button submitButton;
    private Integer position;
    String checkedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);

        mContext = this;
        String title = this.getIntent().getExtras().getString("title");
        String description = this.getIntent().getExtras().getString("description");
        String posterUrl = this.getIntent().getExtras().getString("poster_url");
        position = this.getIntent().getExtras().getInt("position");

        titleTextView = findViewById(R.id.movie_detail_title_textView);
        posterImageView = findViewById(R.id.movie_poster_detail_imageView);
        descriptionTextView = findViewById(R.id.movie_description_textView);
        radioButton1 = findViewById(R.id.radio_button_1);
        radioButton2 = findViewById(R.id.radio_button_2);
        radioButton3 = findViewById(R.id.radio_button_3);
        submitButton = findViewById(R.id.movie_detail_button);


        setTitle(title);

        titleTextView.setText(title);
        descriptionTextView.setText(description);

        Picasso.with(mContext).load(posterUrl).into(posterImageView);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent movieDetailReturnIntent = new Intent();

                movieDetailReturnIntent.putExtra("position", position);
                movieDetailReturnIntent.putExtra("checkedRadioButton", checkedButton);

                setResult(Activity.RESULT_OK, movieDetailReturnIntent);
                finish();
            }
        });

    }



    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_button_1:
                if (checked)
                    checkedButton = radioButton1.getText().toString();
                    break;
            case R.id.radio_button_2:
                if (checked)
                    checkedButton = radioButton2.getText().toString();
                    break;
            case R.id.radio_button_3:
                if (checked)
                    checkedButton = radioButton3.getText().toString();
                    break;

        }
    }
}
