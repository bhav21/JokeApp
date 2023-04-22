package com.example.androidfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class CategoriesFragment extends DialogFragment {

    interface CategoriesFragmentCallBackInterface {
        void onStringSent(String str);
    }

    public static String TAG = "categoriesFragment";
    CategoriesFragmentCallBackInterface listener;
    String categories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.categories_layout, container, false);
        CheckBox chk_programming = v.findViewById(R.id.programming);
        CheckBox chk_pun = v.findViewById(R.id.pun);
        CheckBox chk_christmas = v.findViewById(R.id.christmas);
        CheckBox chk_spooky = v.findViewById(R.id.spooky);
        CheckBox chk_misc = v.findViewById(R.id.misc);
        Button btn_ok = v.findViewById(R.id.ok);
        categories = "";



        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (chk_programming.isChecked()) {
                    if (categories != "") {
                        categories += ",";
                    }
                    categories += "Programming";
                }

                if (chk_pun.isChecked()) {
                    if (categories != "") {
                        categories += ",";
                    }
                    categories += "Pun";
                }

                if (chk_christmas.isChecked()) {
                    if (categories != "") {
                        categories += ",";
                    }
                    categories += "Christmas";
                }

                if (chk_spooky.isChecked()) {
                    if (categories != "") {
                        categories += ",";
                    }
                    categories += "Spooky";
                }

                if (chk_misc.isChecked()) {
                    if (categories != "") {
                        categories += ",";
                    }
                    categories += "Miscellaneous";
                }

                if (categories == "") {
                    categories = "Any";
                }
                listener.onStringSent(categories);
                dismiss();
            }
        });

        return v;
    }
}
