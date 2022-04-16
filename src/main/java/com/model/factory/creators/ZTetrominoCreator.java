package com.model.factory.creators;

import java.awt.Color;

import com.model.shapes.ZTetromino;
import com.model.shapes.Tetromino;

public class ZTetrominoCreator implements TetrominoCreator
{
    @Override
    public Tetromino create() 
    {
        Color color = Color.BLUE;

        boolean[][] block = new boolean[4][4];
        block[1][1] = true;
        block[1][2] = true;
        block[0][0] = true;
        block[0][1] = true; 
        return new ZTetromino(color, block);
    }
}
