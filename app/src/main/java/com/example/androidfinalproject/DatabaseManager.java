package com.example.androidfinalproject;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class DatabaseManager {

    interface DatabaseCallBackInterface{
        void databaseManagerCompleteWithListOfJokes(List<Joke> dbList);
        void databaseManagerCompleteDeletingJoke();
    }

    DatabaseCallBackInterface listener;
    static JokesDatabase jokesDatabase;

    public static JokesDatabase getDB(Context context) {
        if (jokesDatabase == null) {
            return jokesDatabase = Room.databaseBuilder(context,
                    JokesDatabase.class, "jokeDB").build();
        } else
            return jokesDatabase;
    }

    public void insertNewJoke(Joke newJoke) {
        MultithreadingManager.executorService.execute(new Runnable() {
            @Override
            public void run() {
                DatabaseManager.jokesDatabase.getJokeDao().insertNewJoke(newJoke);
            }
        });
    }

    public void getAllJokes() {
        MultithreadingManager.executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Joke> list = DatabaseManager.jokesDatabase.getJokeDao().getAllJokesFromDB();
                MultithreadingManager.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.databaseManagerCompleteWithListOfJokes(list);
                    }
                });
            }
        });
    }

    public void deleteJoke(Joke jokeToDelete) {
        MultithreadingManager.executorService.execute(new Runnable() {
            @Override
            public void run() {
                DatabaseManager.jokesDatabase.getJokeDao().deleteJoke(jokeToDelete);
                MultithreadingManager.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.databaseManagerCompleteDeletingJoke();
                    }
                });
            }
        });
    }

}
