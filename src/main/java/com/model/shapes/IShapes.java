package com.model.shapes;

import java.awt.Color;
import java.awt.Point;

import com.model.SideEnum;

public interface IShapes 
{ 
    Color getColor();  
    
    boolean[][] getBlock();

    void rotate(SideEnum side);

    void setBlock(boolean[][] block);

    Point getPosition();

    TetrominosEnum getType();

    void movePosition(int x, int y);
}
