package com.example.marinadelara.a2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.marinadelara.a2048.Utils.OnSwipeListener;
import com.example.marinadelara.a2048.customViews.GameBoard;

public class GameActivity extends AppCompatActivity {

    GestureDetector gestureDetector;

    GameBoard gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gestureDetector = new GestureDetector(this, new SwipeListener());

        gameBoard = findViewById(R.id.gameBoard);
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
