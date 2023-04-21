package com.example.androidfinalproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Joke.class}, version = 1)
public abstract class JokesDatabase extends RoomDatabase {
    public abstract JokeDAO getJokeDao();
}
