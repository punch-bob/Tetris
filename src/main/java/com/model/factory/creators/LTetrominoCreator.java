package com.model.factory.creators;

import java.awt.Color;

import com.model.shapes.LTetromino;
import com.model.shapes.Tetromino;

public class LTetrominoCreator implements TetrominoCreator
{
    @Override
    public Tetromino create() 
    {
        Color color = Color.GREEN;

        boolean[][] block = new boolean[4][4];
        block[0][1] = true;
        block[1][1] = true;
        block[2][1] = true;
        block[2][2] = true;
        return new LTetromino(color, block);
    }
}
