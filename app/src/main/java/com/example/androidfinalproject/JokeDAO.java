package com.example.androidfinalproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JokeDAO {

    @Insert
    void insertNewJoke(Joke j);

    @Query("select * from Joke")
    List<Joke> getAllJokesFromDB();

    @Query("select * from Joke where category is :category")
    List<Joke> getAllJokesInCategory(String category);

    @Delete
    void deleteJoke(Joke j);
}
