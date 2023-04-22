package com.example.androidfinalproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ContainingWordsFragment extends DialogFragment {

    interface ContainingWordsFragmentCallBackInterface {
        void onPhraseSent(String str);
    }

    public static String TAG = "containingWordsFragment";
    ContainingWordsFragment.ContainingWordsFragmentCallBackInterface listener;
    String containingWords;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.containing_words_layout, container, false);
        TextView desc = v.findViewById(R.id.filterDescription);
        EditText enterText = v.findViewById(R.id.enterWord);
        Button btn_ok_words = v.findViewById(R.id.ok_word);
        containingWords = "";

        btn_ok_words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                containingWords = enterText.getText().toString();
                listener.onPhraseSent(containingWords);
                dismiss();
            }
        });

        return v;
    }
}
