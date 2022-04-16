package com.model.factory.creators;

import java.awt.Color;

import com.model.shapes.ITetromino;
import com.model.shapes.Tetromino;

public class ITetrominoCreator implements TetrominoCreator
{
    @Override
    public Tetromino create() 
    {
        Color color = Color.RED;

        boolean[][] block = new boolean[4][4];
        block[2][0] = true;
        block[2][1] = true;
        block[2][2] = true;
        block[2][3] = true;
        return new ITetromino(color, block);
    }
}
