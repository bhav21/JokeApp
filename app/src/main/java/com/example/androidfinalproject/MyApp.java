package com.example.androidfinalproject;

import android.app.Application;

public class MyApp  extends Application {
    NetworkingManager networkingManager = new NetworkingManager();
    MultithreadingManager multithreadingManager = new MultithreadingManager();
    DatabaseManager databaseManager = new DatabaseManager();

//    NetworkingManager networkingManager;
//    MultithreadingManager multithreadingManager;
//    DatabaseManager databaseManager;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        networkingManager = new NetworkingManager();
//        multithreadingManager = new MultithreadingManager();
//        databaseManager = new DatabaseManager();
//    }
//
//    public NetworkingManager getNetworkingManager() {
//        return networkingManager;
//    }
//
//    public MultithreadingManager getMultithreadingManager() {
//        return multithreadingManager;
//    }
//
//    public DatabaseManager getDatabaseManager() {
//        return databaseManager;
//    }

}
