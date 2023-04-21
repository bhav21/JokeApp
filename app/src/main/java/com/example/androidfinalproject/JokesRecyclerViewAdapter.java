package com.example.androidfinalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JokesRecyclerViewAdapter
        extends RecyclerView.Adapter<JokesRecyclerViewAdapter.JokesViewHolder>  {

    ArrayList<Joke> list;
    Context context;

    interface JokeClickListener {
        void onJokeClicked(Joke selectedJoke);
    }

    JokeClickListener listener;

    public JokesRecyclerViewAdapter(ArrayList<Joke> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public JokesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.joke_row_layout, parent, false);
        return new JokesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JokesViewHolder holder, int position) {
        // holder.id.setText(list.get(position).id + 1);
        holder.joke.setText(list.get(position).joke);
        holder.category.setText(list.get(position).category);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class JokesViewHolder extends RecyclerView.ViewHolder {
        // TextView id;
        TextView joke;
        TextView category;

        public JokesViewHolder(@NonNull View itemView) {
            super(itemView);
            // id = itemView.findViewById(R.id.jokeID);
            joke = itemView.findViewById(R.id.jokePreview);
            category = itemView.findViewById(R.id.jokeCategory);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onJokeClicked(list.get(getAdapterPosition()));
                }
            });
        }

    }

}
