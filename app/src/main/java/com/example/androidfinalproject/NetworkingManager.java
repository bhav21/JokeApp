package com.example.androidfinalproject;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class NetworkingManager {

    interface NetworkingCallBackInterface {
        void NetworkManagerCompleteWithJsonString(String jsonString);
    }

    NetworkingCallBackInterface listener;

    String jokeURL = "https://v2.jokeapi.dev/joke/";

    public void getJoke(String query, Boolean hasSearchString, Boolean diffLang) {
        MultithreadingManager.executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                URL url;
                try {
                    if (hasSearchString || diffLang) {
                        url = new URL(jokeURL + query + "&safe-mode");
                    } else {
                        url = new URL(jokeURL + query + "?safe-mode");
                    }

                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setRequestProperty("content-Type", "application/json");

                    // get input
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    int value = 0;
                    String jsonString = "";
                    while ((value = reader.read()) != -1) {
                        jsonString += (char)value;
                    }

                    final String json = jsonString;

                    MultithreadingManager.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.NetworkManagerCompleteWithJsonString(json);
                        }
                    });

                    Log.d("joke", jsonString);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    urlConnection.disconnect();
                }
            }
        });
    }

}
