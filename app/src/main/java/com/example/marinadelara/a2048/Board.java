package com.example.marinadelara.a2048;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;

import java.util.Random;


/**
 * Created by 1513 X-MXTI on 13/04/2018.
 */

public class Board {

    Paint paint = new Paint();

    private final int cols = 4;
    private final int rows = 4;
    private int sum = 0;

    private int[][] board = new int[rows][cols];

    private int textColor;
    private int tileColor;

    Context context;

    public Board() {}

    public Board(Context context) {
        this.context = context;

        textColor = ContextCompat.getColor(context, R.color.textColor);
        tileColor = ContextCompat.getColor(context, R.color.baseTileColor);
    }

    public Board(Board copy) {
        setBoard(copy.board);
    }

    public void moveLeft() {
        for (int y = 0; y < rows; y++) { // Each line
            int[] line = new int[cols];
            int pos = 0;
            for (int x = 0; x < cols; x++) {
                if (board[y][x] != 0) {
                    line[pos] = board[y][x];
                    pos++;
                }
            }
            board[y] = line;
        }
    }

    public void mergeLeft() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (board[y][x] != 0) {
                    for (int i = x + 1; i < cols; i++) {
                        if (board[y][i] == 0)
                            break;
                        if (board[y][i] != board[y][x])
                            break;
                        board[y][x] += board[y][i];
                        board[y][i] = 0;
                        break;
                    }
                }
            }
        }
        boardSum();
    }

    public void flip() {
        for (int y = 0; y < rows; y++) {
            int[] flipped = new int[cols];
            for (int x = 0; x < cols; x++) {
                flipped[cols - x - 1] = board[y][x];
            }
            board[y] = flipped;
        }
    }

    public void transpose() {
        int[][] newBoard = new int[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                newBoard[y][x] = board[x][y];
            }
        }
        board = newBoard;
    }

    // Adds a 2 or a 4 on a random available spot
    // 2 -> 90% chance
    // 4 -> 10% chance
    public void addRandom() {
        if(!isFull()) {
            Random rand = new Random();
            int randX;
            int randY;
            do {
                randX = rand.nextInt(4);
                randY = rand.nextInt(4);
            } while (board[randY][randX] != 0);

            //10% chance de cair 4
            //90% chance de cair 2
            int chance = rand.nextInt(10);
            board[randY][randX] = chance == 0 ? 4 : 2;
        }
    }

    public boolean isFull() {
        for (int y = 0; y < rows; y++)
            for (int x = 0; x < cols; x++)
                if(board[y][x] == 0)
                    return false;
        return true;
    }

    public void onDraw(Canvas canvas) {
        paint.setTextSize(100);

        float w = canvas.getWidth() / cols;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if(board[y][x] != 0) {
                    paint.setColor(tileColor);
                    canvas.drawRoundRect(x * w, y * w, x * w + w, y * w + w, 20, 20, paint);
                    paint.setColor(textColor);
                    drawTextCentered(canvas, paint, String.valueOf(board[y][x]), x * w + w / 2, y * w + w / 2);
                }
            }
        }
    }

    // TODO: move this to an utility class
    private void drawTextCentered(Canvas canvas, Paint paint, String text, float cx, float cy){
        Rect textBounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.drawText(text, cx - textBounds.exactCenterX(), cy - textBounds.exactCenterY(), paint);
    }

    private void boardSum() {
        sum = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                sum += board[y][x];
            }
        }
    }

    public boolean equals(Board other) {
        for (int y = 0; y < rows; y++)
            for (int x = 0; x < cols; x++)
                if(board[y][x] != other.board[y][x])
                    return false;
        return true;
    }

    // For console printing
    public String toString() {
        String text = "";
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                text += " " + board[y][x];
            }
            text += "\n";
        }
        return text;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        for (int y = 0; y < rows; y++)
            for (int x = 0; x < cols; x++)
                this.board[y][x] = board[y][x];
    }

    public int getSum() {
        return sum;
    }
}
