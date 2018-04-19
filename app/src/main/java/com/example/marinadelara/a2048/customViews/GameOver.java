package com.example.marinadelara.a2048.customViews;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.marinadelara.a2048.R;

/**
 * Created by marinadelara on 18/04/18.
 */

public class GameOver extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int w = displayMetrics.widthPixels;
        int h = displayMetrics.heightPixels;

        getWindow().setLayout((int)(w * 0.8f), (int)(h * 0.45f));

        View decorView = getWindow().getDecorView();
        int opt = View.SYSTEM_UI_FLAG_FULLSCREEN|
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(opt);
        setContentView(R.layout.activity_lose);
    }
}
