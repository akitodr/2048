package com.example.marinadelara.a2048.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.marinadelara.a2048.Board;

/**
 * Created by 1513 X-MXTI on 13/04/2018.
 */

public class GameBoard extends View {
    Board board = new Board();

    private int totalScore;

    public GameBoard(Context context) {
        super(context);
        init();
    }

    public GameBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameBoard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        totalScore = 0;
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
        //adiciona pontuação aqui
        totalScore = board.getSum();
        System.out.println("Score: "+totalScore);
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
        left();
        board.transpose();
    }

    public void down() {
        Log.d("GameBoard", "Swiped: DOWN");
        board.transpose();
        right();
        board.transpose();
    }

    public void reset() {
        board.getBoardState().clear();
        board = new Board();
        init();
    }

    public void movementBeforeCurrent() {
        board.getBoardState().pop();
        board.setBoard(board.getBoardState().lastElement());
    }

    public int getTotalScore() {
        return totalScore;
    }
}
