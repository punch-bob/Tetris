package com.model.shapes;

import java.awt.Color;

public class STetromino extends Tetromino
{
    public STetromino(Color color, boolean[][] block)
    {
        this.color = color;
        type = TetrominosEnum.S;
        this.block = block;
    }
}
