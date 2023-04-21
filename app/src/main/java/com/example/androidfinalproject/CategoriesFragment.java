package com.example.androidfinalproject;

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
        void addCategoriesListener(ArrayList<String> categoryList);
    }

    public static String TAG = "categoriesFragment";
    CategoriesFragmentCallBackInterface listener;

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

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return v;
    }
}
