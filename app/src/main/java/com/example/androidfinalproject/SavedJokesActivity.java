package com.example.androidfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class SavedJokesActivity extends AppCompatActivity
        implements JokesRecyclerViewAdapter.JokeClickListener, DatabaseManager.DatabaseCallBackInterface {

    ArrayList<Joke> jokes = new ArrayList<>(0);
    RecyclerView rv;
    JokesRecyclerViewAdapter adapter;
    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_jokes);

        rv = findViewById(R.id.jokesList);
        adapter = new JokesRecyclerViewAdapter(jokes, this);
        adapter.listener = this;

        DatabaseManager.getDB(this);
        databaseManager = ((MyApp)getApplication()).databaseManager;

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                databaseManager.deleteJoke(adapter.list.get(position));
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rv);

    }

    @Override
    public void onResume() {
        super.onResume();
        databaseManager.listener = this;
        databaseManager.getAllJokes();
        adapter.listener = this;
    }

    @Override
    public void onJokeClicked(Joke selectedJoke) {
//        Intent intent = new Intent(this,JokeFragment.);
//        intent.putExtra("joke",selectedJoke);
//        startActivity(intent);
        JokeFragment jokeFragment = new JokeFragment();
        Bundle args = new Bundle();
        args.putString("joke_text", selectedJoke.joke);
        args.putString("joke_category", selectedJoke.category);
        jokeFragment.setArguments(args);
        jokeFragment.show(getSupportFragmentManager().beginTransaction(), JokeFragment.TAG);
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
    public void databaseManagerCompleteWithListOfJokes(List<Joke> dbList) {
        adapter.list = new ArrayList<>(dbList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void databaseManagerCompleteDeletingJoke() {
        databaseManager.getAllJokes();
    }
}