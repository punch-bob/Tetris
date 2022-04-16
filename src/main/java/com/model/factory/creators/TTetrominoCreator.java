package com.model.factory.creators;

import java.awt.Color;

import com.model.shapes.TTetromino;
import com.model.shapes.Tetromino;


public class TTetrominoCreator implements TetrominoCreator
{
    @Override
    public Tetromino create() 
    {
        Color color = Color.ORANGE;

        boolean[][] block = new boolean[4][4];
        block[1][1] = true;
        block[1][0] = true;
        block[1][2] = true;
        block[0][1] = true; 
        return new TTetromino(color, block);
    }
}
