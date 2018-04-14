package com.example.marinadelara.a2048.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.marinadelara.a2048.Board;
import com.example.marinadelara.a2048.Utils.OnSwipeListener;

/**
 * Created by 1513 X-MXTI on 13/04/2018.
 */

public class GameBoard extends View {
    Board board = new Board();

    public GameBoard(Context context) {
        super(context);
        init(context, null);
    }

    public GameBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GameBoard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        board.addRandom();
        board.addRandom();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        board.onDraw(canvas);
        postInvalidate();
    }

    public void left() {
        Log.d("GameBoard", "Swiped: LEFT");
        board.moveLeft();
        board.mergeLeft();
        board.moveLeft();
        board.addRandom();
    }

    public void right() {
        Log.d("GameBoard", "Swiped: RIGHT");
        board.flip();
        left();
        board.flip();
    }

    public void up() {
        Log.d("GameBoard", "Swiped: UP");
        board.transpose();
        //left();
        board.transpose();
    }

    public void down() {
        Log.d("GameBoard", "Swiped: DOWN");
    }
}
