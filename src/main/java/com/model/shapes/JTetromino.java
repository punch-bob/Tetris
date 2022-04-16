package com.model.shapes;

import java.awt.Color;

public class JTetromino extends Tetromino
{
    public JTetromino(Color color, boolean[][] block)
    {
        this.color = color;
        type = TetrominosEnum.J;
        this.block = block;  
    }
}
