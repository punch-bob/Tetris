package com.model.shapes;

import java.awt.Color;

import com.model.SideEnum;

public class OTetromino extends Tetromino
{
    public OTetromino(Color color, boolean[][] block)
    {
        this.color = color;
        type = TetrominosEnum.O;
        this.block = block;
    }
    
    @Override
    public void rotate(SideEnum side) {}
}
