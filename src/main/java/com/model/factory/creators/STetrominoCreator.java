package com.model.factory.creators;

import java.awt.Color;

import com.model.shapes.STetromino;
import com.model.shapes.Tetromino;

public class STetrominoCreator implements TetrominoCreator
{
    @Override
    public Tetromino create() 
    {
        Color color = Color.CYAN;

        boolean[][] block = new boolean[4][4];
        block[1][1] = true;
        block[1][0] = true;
        block[0][1] = true;
        block[0][2] = true;   
        return new STetromino(color, block);
    }
}
