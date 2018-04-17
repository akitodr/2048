package com.example.marinadelara.a2048.customViews;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.marinadelara.a2048.Board;
import com.example.marinadelara.a2048.GameActivity;
import com.example.marinadelara.a2048.R;

import java.io.Serializable;

/**
 * Created by marinadelara on 16/04/18.
 */

public class PopView extends Activity {

    Button yesButton, noButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_pop);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int w = displayMetrics.widthPixels;
        int h = displayMetrics.heightPixels;

        getWindow().setLayout((int)(w * 0.8f), (int)(h * 0.8f));

        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                finish();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
