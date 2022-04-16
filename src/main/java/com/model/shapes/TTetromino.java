package com.model.shapes;

import java.awt.Color;

public class TTetromino extends Tetromino
{
    public TTetromino(Color color, boolean[][] block)
    {
        this.color = color;
        type = TetrominosEnum.T;
        this.block = block;
    }
}
