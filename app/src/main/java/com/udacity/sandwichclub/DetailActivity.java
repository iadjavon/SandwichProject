package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    public String temp;
    TextView placeOfBirth;
    TextView description;
    TextView detail;
    TextView variousNames;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }


    //got to populate yooho
    private void populateUI(Sandwich sandwich) {

        Toast.makeText(this, " yoooooooo", Toast.LENGTH_SHORT).show();
        //finding the origin text view
        placeOfBirth = (TextView) findViewById(R.id.place_of_origine_tv);
        //set the text on it
        placeOfBirth.append(" " + sandwich.getPlaceOfOrigin());
        //find the description view
        description = (TextView) findViewById(R.id.description_tv);
        //set the text on ti
        description.append(" " + sandwich.getDescription());
        //find the ingredient text view
        detail = (TextView) findViewById(R.id.detail_ingredients);
        //setting the text appropriately
        list = sandwich.getIngredients();

        for (int j = 0 ; j < sandwich.getIngredients().size();j++){


            detail.append( " " + sandwich.getIngredients().get(j));
        }
        //finding the also known text view
        variousNames = (TextView)findViewById(R.id.also_known_tv);

        for (int k = 0 ; k < sandwich.getAlsoKnownAs().size();k++){


            variousNames.append( " " + sandwich.getAlsoKnownAs().get(k) );
        }



    }
}
