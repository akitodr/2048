package com.example.marinadelara.a2048;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.marinadelara.a2048.Utils.OnSwipeListener;
import com.example.marinadelara.a2048.customViews.GameBoard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class GameActivity extends AppCompatActivity {

    GestureDetector gestureDetector;
    GameBoard gameBoard;
    ImageButton revertButton, resetButton;
    TextView scoreText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gestureDetector = new GestureDetector(this, new SwipeListener());
        gameBoard = findViewById(R.id.gameBoard);

        //BUtton
        revertButton = findViewById(R.id.revertButton);
        resetButton = findViewById(R.id.resetButton);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameBoard.reset();
            }
        });

        revertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameBoard.movementBeforeCurrent();
            }
        });
        //--end Button

        //Score
        scoreText = findViewById(R.id.scoreText);
        System.out.println(gameBoard.getTotalScore());
        scoreText.append(" " + String.valueOf(gameBoard.getTotalScore()));
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

            return super.onSwipe(direction);
        }
    }
}
