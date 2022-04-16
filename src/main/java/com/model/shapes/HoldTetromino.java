package com.model.shapes;

import java.awt.Color;

import com.model.factory.TetrominoFactory;
import com.publisher.Publisher;

public class HoldTetromino extends Publisher
{
    private IShapes tetromino;

    public IShapes getTetromino()
    {
        return tetromino;
    }

    public void setTetromino(IShapes tetromino)
    {
        this.tetromino = tetromino;
        publishNotify();
    }

    public void setTetromino(TetrominosEnum type)
    {
        tetromino = TetrominoFactory.create(type);
        publishNotify();
    }

    public TetrominosEnum getType()
    {
        return tetromino.getType();
    }

    public Color getColor()
    {
        return tetromino.getColor();
    }

    public boolean[][] getBlock()
    {
        return tetromino.getBlock();
    }
}
