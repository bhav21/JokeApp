package com.example.androidfinalproject;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonManager {

    static Joke fromJsonStringToJoke(String json) {
        Joke joke = new Joke();
        try {
            JSONObject jokeJsonObject = new JSONObject(json);
            joke.joke = jokeJsonObject.getString("joke");
            joke.category = jokeJsonObject.getString("category");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return joke;
    }

}
