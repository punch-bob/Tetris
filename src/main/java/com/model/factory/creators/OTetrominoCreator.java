package com.model.factory.creators;

import java.awt.Color;

import com.model.shapes.OTetromino;
import com.model.shapes.Tetromino;

public class OTetrominoCreator implements TetrominoCreator
{
    @Override
    public Tetromino create() 
    {
        Color color = Color.MAGENTA;

        boolean[][] block = new boolean[4][4];
        block[2][1] = true;
        block[2][2] = true;
        block[3][1] = true;
        block[3][2] = true;   
        return new OTetromino(color, block);
    }
}
