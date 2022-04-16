package com.model.shapes;

import java.awt.Color;

public class LTetromino extends Tetromino
{
    public LTetromino(Color color, boolean[][] block)
    {
        this.color = color;
        type = TetrominosEnum.L;
        this.block = block;
    }
}
