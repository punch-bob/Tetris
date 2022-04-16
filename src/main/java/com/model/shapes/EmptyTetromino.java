package com.model.shapes;

import java.awt.Color;

public class EmptyTetromino extends Tetromino
{
    public EmptyTetromino()
    {
        color = Color.GRAY;
    }

    @Override
    public TetrominosEnum getType()
    {
        return null;
    }
}
