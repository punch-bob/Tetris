package com.model.shapes;

import java.awt.Color;
import java.util.Arrays;

import com.model.SideEnum;

public class ITetromino extends Tetromino
{

    public ITetromino(Color color, boolean[][] block)
    {
        this.color = color;
        type = TetrominosEnum.I;
        this.block = block;
    }
    
    @Override
    public void rotate(SideEnum side)
    {
        boolean[][] tempList = new boolean[4][4];
        for (int i = 0; i < block.length; i++) 
        {
            tempList[i] = Arrays.copyOf(block[i], 4);
        }

        for (int i = 0; i < block.length; i++)
        {
            for (int j = 0; j < block.length; j++)
            {
                if(side == SideEnum.R)
                {
                    block[3 - j][i] = tempList[i][j];
                }
                else
                {
                    block[i][3 - j] = tempList[j][i];
                }
                
            }
        
        }
    }
}
