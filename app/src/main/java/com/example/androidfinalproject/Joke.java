package com.example.androidfinalproject;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Joke implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    String joke;
    String category;

    public Joke() {
    }

    public Joke(int id, String joke, String category) {
        this.id = id;
        this.joke = joke;
        this.category = category;
    }

    protected Joke(Parcel in) {
        id = in.readInt();
        joke = in.readString();
        category = in.readString();
    }



    public static final Creator<Joke> CREATOR = new Creator<Joke>() {
        @Override
        public Joke createFromParcel(Parcel in) {
            return new Joke(in);
        }

        @Override
        public Joke[] newArray(int size) {
            return new Joke[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(joke);
        parcel.writeString(category);
    }
}
