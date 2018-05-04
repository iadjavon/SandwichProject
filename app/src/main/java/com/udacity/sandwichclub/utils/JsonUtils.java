package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich lovinIt = new Sandwich();
        ArrayList<String> listOfIngredient;
        ArrayList<String> knownName;
        String [] names;

        if(json!=null){

            try {

                //making a json object from the string json
                JSONObject jData =  new JSONObject(json);
                //getting the name object
                JSONObject nameObject = jData.getJSONObject("name");
                //from the name object getting the main name and setting it in the
                //sandwich object
                lovinIt.setMainName(nameObject.getString("mainName"));
                //now getting also known as field
                JSONArray goodness = nameObject.getJSONArray("alsoKnownAs");

                if(goodness!= null){

                    knownName = new ArrayList<String>();

                    for(int j = 0 ; j < goodness.length(); j++){

                        knownName.add(goodness.getString(j));
                    }

                    lovinIt.setAlsoKnownAs(knownName);
                }

                //getting place of origin json object
                String placeOfOriginObject = jData.getString("placeOfOrigin");
                if(placeOfOriginObject.equals("")){

                    lovinIt.setPlaceOfOrigin("we do not know");

                }
                else {

                    lovinIt.setPlaceOfOrigin(jData.getString("placeOfOrigin"));

                }

                //getting the description field
               // String description = jData.getString("description");
                lovinIt.setDescription(jData.getString("description"));

                //getting the image field
                String imageUrl = jData.getString("image");
                lovinIt.setImage(imageUrl);

                //getting the list of ingredients
                JSONArray arrayOfIngredients = jData.getJSONArray("ingredients");


                if(arrayOfIngredients!=null){

                listOfIngredient = new ArrayList<String>();

                for(int i = 0 ; i < arrayOfIngredients.length(); i++){

                    listOfIngredient.add(arrayOfIngredients.getString(i));
                }

                    lovinIt.setIngredients(listOfIngredient);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return lovinIt;
    }
}
