package com.example.marinadelara.a2048.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.marinadelara.a2048.Board;
import com.example.marinadelara.a2048.Utils.SoundPlayer;

/**
 * Created by 1513 X-MXTI on 13/04/2018.
 */

public class GameBoard extends View {
    Board board;
    Board previousBoard = new Board();

    SoundPlayer soundPlayer = new SoundPlayer();
    int clickSound;

    Context context;

    private int totalScore;

    public GameBoard(Context context) {
        super(context);
        init(context);
    }

    public GameBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameBoard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(@Nullable Context context) {
        this.context = context;
        board = new Board(context);

        totalScore = 0;
        board.addRandom();
        board.addRandom();
        previousBoard.setBoard(board.getBoard());

        clickSound = soundPlayer.loadSoundEffect(context.getAssets(), "click.wav");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        board.onDraw(canvas);
        postInvalidate();
    }

    public void left() {
        Log.d("GameBoard", "Swiped: LEFT");
        Board auxBoard = new Board(board);

        board.moveLeft();
        board.mergeLeft();
        board.moveLeft();

        if(!board.equals(auxBoard)) {
            soundPlayer.playSoundEffect(clickSound);
            board.addRandom();
            previousBoard.setBoard(auxBoard.getBoard());  // Saves previous board before moving
        }

        //adiciona pontuação aqui
        totalScore = board.getSum();
       // System.out.println("Score: "+totalScore);
    }

    public void right() {
        Log.d("GameBoard", "Swiped: RIGHT");
        Board auxBoard = new Board(board);

        board.flip();

        board.moveLeft();
        board.mergeLeft();
        board.moveLeft();

        board.flip();

        if(!board.equals(auxBoard)) {
            soundPlayer.playSoundEffect(clickSound);
            board.addRandom();
            previousBoard.setBoard(auxBoard.getBoard());  // Saves previous board before moving
        }

        //adiciona pontuação aqui
        totalScore = board.getSum();
    }

    public void up() {
        Log.d("GameBoard", "Swiped: UP");
        Board auxBoard = new Board(board);

        board.transpose();

        board.moveLeft();
        board.mergeLeft();
        board.moveLeft();

        board.transpose();

        if(!board.equals(auxBoard)) {
            soundPlayer.playSoundEffect(clickSound);
            board.addRandom();
            previousBoard.setBoard(auxBoard.getBoard());  // Saves previous board before moving
        }

        //adiciona pontuação aqui
        totalScore = board.getSum();
    }

    public void down() {
        Log.d("GameBoard", "Swiped: DOWN");
        Board auxBoard = new Board(board);

        board.transpose();
        board.flip();

        board.moveLeft();
        board.mergeLeft();
        board.moveLeft();

        board.flip();
        board.transpose();

        if(!board.equals(auxBoard)) {
            soundPlayer.playSoundEffect(clickSound);
            board.addRandom();
            previousBoard.setBoard(auxBoard.getBoard());  // Saves previous board before moving
        }

        //adiciona pontuação aqui
        totalScore = board.getSum();
    }

    public void reset() {
        totalScore = 0;
        System.out.println(totalScore);
        board = new Board();
        init(context);
    }

    public void movementBeforeCurrent() {
        Log.d("", "going to previous board");
        board.setBoard(previousBoard.getBoard());
    }

    public int getTotalScore() {
        return totalScore;
    }
}
