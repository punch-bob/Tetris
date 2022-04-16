package com.model.factory.creators;

import java.awt.Color;

import com.model.shapes.JTetromino;
import com.model.shapes.Tetromino;

public class JTetrominoCreator implements TetrominoCreator
{
    @Override
    public Tetromino create() 
    {
        Color color = Color.YELLOW;

        boolean[][] block = new boolean[4][4];
        block[0][1] = true;
        block[1][1] = true;
        block[2][1] = true;
        block[2][0] = true;
        return new JTetromino(color, block);
    }
}
