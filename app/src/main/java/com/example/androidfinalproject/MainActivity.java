package com.example.androidfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements
        CategoriesFragment.CategoriesFragmentCallBackInterface,
        ContainingWordsFragment.ContainingWordsFragmentCallBackInterface,
        NetworkingManager.NetworkingCallBackInterface,
        DatabaseManager.DatabaseCallBackInterface {

    TextView tv_joke;
    TextView tv_filterCategories;
    TextView tv_filterPhrase;
    Button btn_save;
    Button generateNewJoke;
    Button btn_category;
    Button btn_word;
    NetworkingManager networkingManager;
    DatabaseManager databaseManager;
    Joke joke;
    String selectedCategories;
    String containingPhrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_joke = findViewById(R.id.jokeText);
        tv_filterCategories = findViewById(R.id.filterCategories);
        tv_filterPhrase = findViewById(R.id.filterPhrase);
        generateNewJoke = findViewById(R.id.generateNewJoke);
        btn_category = findViewById(R.id.byCategory);
        btn_word = findViewById(R.id.byWord);
        btn_save = findViewById(R.id.save);
        selectedCategories = "Any";
        containingPhrase = "";

        networkingManager = ((MyApp)getApplication()).networkingManager;
        networkingManager.listener = this;

        databaseManager = ((MyApp)getApplication()).databaseManager;
        databaseManager.listener = MainActivity.this;
        DatabaseManager.getDB(this);

        btn_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoriesFragment fragment = new CategoriesFragment();
                fragment.listener = MainActivity.this;
                fragment.show(getSupportFragmentManager().beginTransaction(), CategoriesFragment.TAG);
            }
        });

        btn_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContainingWordsFragment fragment = new ContainingWordsFragment();
                fragment.listener = MainActivity.this;
                fragment.show(getSupportFragmentManager().beginTransaction(), ContainingWordsFragment.TAG);
            }
        });

        generateNewJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (containingPhrase == "") {
                    networkingManager.getJoke(selectedCategories, false);
                } else {
                    networkingManager.getJoke(selectedCategories + "?contains=" + containingPhrase, true);
                }

            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseManager.insertNewJoke(joke);
                Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                // finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        // MenuItem generateJokeMenu = menu.findItem(R.id.generateJoke);
        // MenuItem favourites = menu.findItem(R.id.favourites);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.generateJoke:{
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            }
            case R.id.favourites:{
                Intent i = new Intent(this, SavedJokesActivity.class);
                startActivity(i);
                break;
            }
        }
        return true;
    }



    @Override
    public void onStringSent(String str) {
        selectedCategories = str;
        tv_filterCategories.setText(String.format("%s%s", getString(R.string.selectedCategories), selectedCategories));
    }

    @Override
    public void NetworkManagerCompleteWithJsonString(String jsonString) {
        joke = JsonManager.fromJsonStringToJoke(jsonString);
        if (Objects.equals(joke.category, "None")) {
            tv_joke.setTextColor(getColor(R.color.red));
            btn_save.setEnabled(false);

        } else {
            tv_joke.setTextColor(getColor(R.color.black));
            btn_save.setEnabled(true);
        }
        containingPhrase = "";
        tv_filterPhrase.setText(null);
        tv_joke.setText(joke.joke);
    }

    @Override
    public void databaseManagerCompleteWithListOfJokes(List<Joke> dbList) {

    }

    @Override
    public void databaseManagerCompleteDeletingJoke() {

    }

    @Override
    public void onPhraseSent(String str) {
        containingPhrase = str;
        tv_filterPhrase.setText(String.format("%s%s", getString(R.string.must_contain), containingPhrase));
    }
}