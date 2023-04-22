package com.example.androidfinalproject;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonManager {

    static Joke fromJsonStringToJoke(String json) {
        Joke joke = new Joke();
        try {
            JSONObject jokeJsonObject = new JSONObject(json);
            if (jokeJsonObject.getBoolean("error")) {
                joke.joke = "Oh no! There are no jokes containing the selected words :(";
                joke.category = "None";
            }
            else {
                if (jokeJsonObject.getString("type").equals("single")) {
                    joke.joke = jokeJsonObject.getString("joke");
                } else {
                    joke.joke = jokeJsonObject.getString("setup") + "\n\n" + jokeJsonObject.getString("delivery");
                }
                joke.category = jokeJsonObject.getString("category");
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return joke;
    }

}
