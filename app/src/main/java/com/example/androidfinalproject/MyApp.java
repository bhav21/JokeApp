package com.example.androidfinalproject;

import android.app.Application;

public class MyApp  extends Application {
    NetworkingManager networkingManager = new NetworkingManager();
    MultithreadingManager multithreadingManager = new MultithreadingManager();
    DatabaseManager databaseManager = new DatabaseManager();
}
