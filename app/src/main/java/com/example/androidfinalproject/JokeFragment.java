package com.example.androidfinalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.w3c.dom.Text;

public class JokeFragment extends DialogFragment {

    TextView savedJokeText;
    Button savedJokeCategory;
    public static String TAG = "jokeFragment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.joke_fragment, container, false);
        savedJokeText = v.findViewById(R.id.favJokeText);
        savedJokeCategory = v.findViewById(R.id.favJokeCategory);

        Bundle args = getArguments();
        if (args != null) {
            String jokeText = args.getString("joke_text");
            String jokeCategory = args.getString("joke_category");
            savedJokeText.setText(jokeText);
            savedJokeCategory.setText(jokeCategory);
        }

        return v;
    }

//    @Override
//    public void onJokeClicked(Joke selectedJoke) {
//        JokeFragment jokeFragment = new JokeFragment();
//        savedJokeText.setText(selectedJoke.joke);
//        savedJokeCategory.setText(selectedJoke.category);
//        jokeFragment.show(getChildFragmentManager(), JokeFragment.TAG);
//    }
}
