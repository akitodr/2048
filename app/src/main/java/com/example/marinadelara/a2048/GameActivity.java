package com.example.marinadelara.a2048;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.marinadelara.a2048.Utils.OnSwipeListener;
import com.example.marinadelara.a2048.customViews.GameBoard;
import com.example.marinadelara.a2048.customViews.GameOver;
import com.example.marinadelara.a2048.customViews.PopView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class GameActivity extends AppCompatActivity implements Serializable{

    GestureDetector gestureDetector;
    GameBoard gameBoard;
    ImageButton revertButton, resetButton;
    TextView scoreText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        View decorView = getWindow().getDecorView();
        int opt = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(opt);

        gestureDetector = new GestureDetector(this, new SwipeListener());
        gameBoard = findViewById(R.id.gameBoard);

        //BUtton
        revertButton = findViewById(R.id.revertButton);
        resetButton = findViewById(R.id.resetButton);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, PopView.class);
//                intent.putExtra("board",gameBoard);
                startActivityForResult(intent, 0);
            }
        });

        revertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gameBoard.movementBeforeCurrent();
                Intent intent = new Intent(GameActivity.this, GameOver.class);
                startActivity(intent);
            }
        });
        //--end Button


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1)
            gameBoard.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class SwipeListener extends OnSwipeListener {
        @Override
        public boolean onSwipe(Direction direction) {

            switch (direction) {
                case up:
                    gameBoard.up();
                    break;
                case down:
                    gameBoard.down();
                    break;
                case left:
                    gameBoard.left();
                    break;
                case right:
                    gameBoard.right();
                    break;
            }

            //Score
            scoreText = findViewById(R.id.scoreText);
            System.out.println(gameBoard.getTotalScore());
            scoreText.setText("Score: " + String.valueOf(gameBoard.getTotalScore()));

            return super.onSwipe(direction);
        }
    }
}
