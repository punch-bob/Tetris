package com.model.shapes;

import java.awt.Color;

public class ZTetromino extends Tetromino
{
    public ZTetromino(Color color, boolean[][] block)
    {
        this.color = color;
        type = TetrominosEnum.Z;
        this.block = block;
    }
}
